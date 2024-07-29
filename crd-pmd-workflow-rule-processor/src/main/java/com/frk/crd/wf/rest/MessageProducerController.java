package com.frk.crd.wf.rest;

import com.frk.crd.utilities.DiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.TextMessage;

@Slf4j
@RestController
public class MessageProducerController {
  private final JmsTemplate jmsTemplate;

  public MessageProducerController(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @PostMapping(path = DiscoveryService.PUBLISH_MESSAGE, produces = MediaType.TEXT_PLAIN_VALUE)
  public void publishMessage(@RequestParam("queueName") String queueName, @RequestBody String payload) {
    try {
      log.info("Publishing message to queue {} as {}", queueName, payload);
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