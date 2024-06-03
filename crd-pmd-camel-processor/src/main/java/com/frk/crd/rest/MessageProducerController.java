package com.frk.crd.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageProducerController {
  private final String queueName;
  private final JmsTemplate jmsTemplate;

  @Autowired
  public MessageProducerController(JmsTemplate jmsTemplate, @Value("${crd.app.in.queue}") String queueName) {
    this.jmsTemplate = jmsTemplate;
    this.queueName = queueName;
  }

  @GetMapping("send")
  public void sendMessage() {
    jmsTemplate.convertAndSend(queueName, "Test");
  }
}