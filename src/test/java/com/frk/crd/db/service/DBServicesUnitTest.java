package com.frk.crd.db.service;


import com.frk.crd.configuration.CRDConfigurationTest;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.db.model.StreamedMessage;
import com.frk.crd.db.utils.TestConstants;
import com.frk.crd.utils.CRDUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DBServicesUnitTest extends CRDConfigurationTest {

  @Test
  void syncStreamedMessages() throws IOException {
    List<String> lines = readFile(MESSAGE_FILE);
    Assertions.assertNotNull(lines);
    Assertions.assertTrue(lines.size() > 0);
    final List<String> toSync = new ArrayList<>();
    lines.forEach(line -> {
      toSync.add(line);
      if (toSync.size() % 100 == 0) {
        syncMessages(toSync);
      }
    });
    long now = System.currentTimeMillis();
    syncMessages(toSync);
    List<DBStreamedMessage> savedMessages = dbStreamedMessageRepository.findAll();
    Assertions.assertNotNull(savedMessages);
    savedMessages.forEach(message -> {
      long messageDateTime = message.getMessageDateTime();
      Assertions.assertEquals(CRDUtils.removeHours(messageDateTime), message.getMessageDate());
    });
    Assertions.assertTrue(dbSyncService.getLastDBSyncTime() >= now);
    Assertions.assertTrue(dbSyncService.getLastDBSyncTime() <= now + 1000);
    Assertions.assertTrue(dbSyncService.getLastDBSaveTime() >= now);
    // TODO: 4/28/24  Assertions.assertTrue(dbSyncService.getLastDBSaveTime() <= now + 1000);
  }

  @Test
  void saveMessages() {
    Assertions.assertNotNull(dbWriteService);
    List<? extends StreamedMessage> afterSave = dbWriteService.saveMessages(null);
    Assertions.assertTrue(ObjectUtils.isEmpty(afterSave));

    afterSave = dbWriteService.saveMessages(List.of());
    Assertions.assertTrue(ObjectUtils.isEmpty(afterSave));

    List<String> messages = new ArrayList<>();
    messages.add(StringUtils.EMPTY);
    messages.add("Not a valid Json");
    messages.add(TestConstants.EXPECTED_XML);
    afterSave = dbWriteService.saveMessages(messages);
    Assertions.assertTrue(ObjectUtils.isNotEmpty(afterSave));
    Assertions.assertEquals(3, afterSave.size());
  }

  void syncMessages(List<String> toSync) {
    synchronized (toSync) {
      dbSyncService.syncStreamedMessages(toSync);
      dbSyncService.saveToDB();
      toSync.clear();
    }
  }
}