package com.frk.crd.db.dao;

import com.frk.crd.db.configuration.CRDDBSyncConfigurationTest;
import com.frk.crd.db.model.DBExceptionMessage;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.model.IExceptionMessage;
import com.frk.crd.utilities.CRDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
class DBRepositoryUnitTest extends CRDDBSyncConfigurationTest {
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

  @Test
  void saveExceptionMessageWithInterfaceConstructor() throws UnsupportedEncodingException {
    long exceptionDateTime = System.currentTimeMillis();
    long aMinuteAgo = exceptionDateTime - Duration.ofMillis(1).toMillis();
    IExceptionMessage message = new DBExceptionMessage(aMinuteAgo, "CRD-PMD-CAMEL", "REST",
      "https://localhost", "test-payload", "new", exceptionDateTime);
    DBExceptionMessage dbMessage = new DBExceptionMessage(message);
    dbExceptionMessageRepository.save(dbMessage);
    Optional<DBExceptionMessage> byId = dbExceptionMessageRepository.findById(aMinuteAgo);
    Assertions.assertTrue(byId.isPresent());
    DBExceptionMessage fromDB = byId.get();
    Assertions.assertNotNull(fromDB);
    Assertions.assertEquals(aMinuteAgo, fromDB.getId());
    Assertions.assertEquals(message.getSourceName(), fromDB.getSourceName());
    Assertions.assertEquals(message.getDestinationType(), fromDB.getDestinationType());
    Assertions.assertEquals(message.getDestinationURL(), fromDB.getDestinationURL());
    Assertions.assertEquals(message.getPayload(), fromDB.getPayload());
    Assertions.assertEquals(message.getStatus(), fromDB.getStatus());
    Assertions.assertEquals(exceptionDateTime, fromDB.getExceptionDateTime());
  }
}