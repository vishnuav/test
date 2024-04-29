package com.frk.crd.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Slf4j
@Component
public class Producer {
    private final JmsTemplate jmsTemplate;

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToQueue(String queue, String payload) {
        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(payload);
            jmsTemplate.send(queue, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                log.info("Sending message {}", message.getText());
                return message;
            });
        } catch (Exception ex) {
            log.error("ERROR in sending message to queue", ex);
        }
    }
}