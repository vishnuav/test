package com.frk.crd.service.impl;

import com.frk.crd.service.MessagePublishService;
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
      jmsTemplate.send(queueName, messageCreator -> {
        TextMessage message = messageCreator.createTextMessage();
        message.setText(payload);
        log.debug("Sending message {}", message.getText());
        return message;
      });
    } catch (JmsException e) {
      log.error("Error in sending message to queue name {}, payload {}", queueName, payload, e);
    }
  }
}