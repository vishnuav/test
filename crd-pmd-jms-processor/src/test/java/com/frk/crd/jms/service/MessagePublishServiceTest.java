package com.frk.crd.jms.service;

import com.frk.crd.jms.configuration.CRDJMSConfigurationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class MessagePublishServiceTest extends CRDJMSConfigurationTest {
  @Test
  void publishMessage() throws InterruptedException {
    String sentMessage = "Test-Payload";
    messagePublishService.sendToQueue(pmdInQueue, sentMessage);
    Object message = jmsTemplate.receiveAndConvert(pmdInQueue);
    Assertions.assertNotNull(message);
    String receivedMessage = message.toString();
    Assertions.assertTrue(StringUtils.contains(receivedMessage, sentMessage));
  }
}