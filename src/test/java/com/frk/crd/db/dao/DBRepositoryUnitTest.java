package com.frk.crd.db.dao;

import com.frk.crd.configuration.CRDConfigurationTest;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.utils.CRDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
class DBRepositoryUnitTest extends CRDConfigurationTest {
  @Test
  void saveStreamedMessageWithEmptyConstructor() {
    DBStreamedMessage message = new DBStreamedMessage();
    message.setMessage(StringUtils.EMPTY);
    long aMinuteAgo = System.currentTimeMillis() - Duration.ofMillis(1).toMillis();
    message.setMessageDateTime(aMinuteAgo);
    dbStreamedMessageRepository.save(message);
    Optional<DBStreamedMessage> byId = dbStreamedMessageRepository.findById(aMinuteAgo);
    Assertions.assertTrue(byId.isPresent());
    DBStreamedMessage fromDB = byId.get();
    Assertions.assertNotNull(fromDB);
    Assertions.assertEquals(StringUtils.EMPTY, fromDB.getMessage());
    Assertions.assertEquals(aMinuteAgo, fromDB.getMessageDateTime());
    Assertions.assertEquals(CRDUtils.removeHours(aMinuteAgo), fromDB.getMessageDate());
  }

  @Test
  void saveStreamedMessageWithAllArgsConstructor() {
    long aMinuteAgo = System.currentTimeMillis() - Duration.ofMillis(1).toMillis();
    DBStreamedMessage message = new DBStreamedMessage(aMinuteAgo, StringUtils.EMPTY);
    dbStreamedMessageRepository.save(message);
    List<DBStreamedMessage> allMessages = dbStreamedMessageRepository.findAll();
    Assertions.assertNotNull(allMessages);
    Assertions.assertTrue(ObjectUtils.isNotEmpty(allMessages));
    Optional<DBStreamedMessage> byId = dbStreamedMessageRepository.findById(aMinuteAgo);
    Assertions.assertTrue(byId.isPresent());
    DBStreamedMessage fromDB = byId.get();
    Assertions.assertNotNull(fromDB);
    Assertions.assertEquals(StringUtils.EMPTY, fromDB.getMessage());
    Assertions.assertEquals(aMinuteAgo, fromDB.getMessageDateTime());
    Assertions.assertEquals(CRDUtils.removeHours(aMinuteAgo), fromDB.getMessageDate());
  }
}