package com.frk.crd.jms.service;

import com.frk.crd.jms.configuration.CRDJMSConfigurationTest;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.Message;
import java.util.concurrent.TimeUnit;

class MessagePublishServiceTest extends CRDJMSConfigurationTest {
  @Value(value = "${crd.app.in.queue}")
  protected String queueName;

  @Test
  void publishMessage() {
    messagePublishService.sendToQueue(queueName, "Test-Payload");
    Awaitility.await().pollDelay(1, TimeUnit.SECONDS).until(() -> true);
    Message message = jmsTemplate.receive(queueName);
    Assertions.assertNotNull(message);
  }
}