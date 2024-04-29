package com.frk.crd.db.rest;

import com.frk.crd.configuration.CRDConfigurationTest;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.db.utils.TestConstants;
import com.frk.crd.utils.DiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class DBRestServiceUnitTest extends CRDConfigurationTest {
  @Test
  void syncMessages() {
    List<String> messages = new ArrayList<>();
//    messages.add(TestConstants.LOGIN_MESSAGE);
//    messages.add(TestConstants.HEARTBEAT_MESSAGE);
//    messages.add(TestConstants.STREAMER_TIMEOUT_MESSAGE);
//    messages.add(TestConstants.QUOTE_SUBSCRIPTION_SUCCESS_MESSAGE);
//    messages.add(TestConstants.STREAM_QUOTES_MESSAGE);
//    Integer responseBody = saveTheseMessages(messages);
//    Assertions.assertEquals(responseBody, messages.size());
//    dbSyncService.saveToDB();
//    List<DBStreamedMessage> all = dbStreamedMessageRepository.findAll();
//    Assertions.assertEquals(1, all.size());
  }

  protected Integer saveTheseMessages(List<String> messages) {
    return dbWebTestClient().post()
      .uri(DiscoveryService.SYNC_MESSAGES)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(messages)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON)
      .expectBody(Integer.class)
      .returnResult()
      .getResponseBody();
  }
}