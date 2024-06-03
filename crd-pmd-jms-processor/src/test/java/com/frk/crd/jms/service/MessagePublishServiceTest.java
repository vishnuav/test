package com.frk.crd.jms.service;

import com.frk.crd.jms.configuration.CRDJMSConfigurationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

class MessagePublishServiceTest extends CRDJMSConfigurationTest {
  @Value(value = "${crd.app.in.queue}")
  protected String queueName;

  @Test
  void publishMessage() {
    messagePublishService.sendToQueue(queueName, "Test-Payload");
//    Awaitility.await().pollDelay(1, TimeUnit.SECONDS).until(() -> true);
//    Message message = jmsTemplate.receive(queueName);
//    Assertions.assertNotNull(message);
  }
}