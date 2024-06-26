package com.frk.crd.jms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frk.crd.jms.service.MessagePublishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Slf4j
@Component
public class MessagePublishServiceImpl implements MessagePublishService {
  private final JmsTemplate jmsTemplate;

  public MessagePublishServiceImpl(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Override
  public void sendToQueue(String queueName, String payload) {
    try {
      String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(payload);
      jmsTemplate.send(queueName, messageCreator -> {
        TextMessage message = messageCreator.createTextMessage();
        message.setText(json);
        log.debug("Sending message {}", message.getText());
        return message;
      });
    } catch (JmsException | JsonProcessingException e) {
      log.error("Error in sending message to queue name {}, payload {}", queueName, payload, e);
    }
  }
}