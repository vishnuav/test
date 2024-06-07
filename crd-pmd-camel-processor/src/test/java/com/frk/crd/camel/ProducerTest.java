package com.frk.crd.camel;

import com.frk.crd.configuration.CRDActiveMQConfiguration;
import com.frk.crd.configuration.CRDConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.Message;
import javax.jms.TextMessage;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CRDConfiguration.class, CRDActiveMQConfiguration.class})
class ProducerTest {
  @Value(value = "${crd.app.in.queue}")
  private String inQueue;
  @Autowired
  private JmsTemplate jmsTemplate;

  @Test
  void sendToQueue() throws Exception {
    String expected = "Hello, world!";
    this.jmsTemplate.convertAndSend(inQueue, expected);
    this.jmsTemplate.setReceiveTimeout(10_000);
    Message message = jmsTemplate.receive(inQueue);
    String actual = ((TextMessage) message).getText();
    Assertions.assertEquals(expected, actual);
  }
}