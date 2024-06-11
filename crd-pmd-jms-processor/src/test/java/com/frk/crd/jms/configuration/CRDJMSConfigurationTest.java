package com.frk.crd.jms.configuration;

import com.frk.crd.jms.service.MessagePublishService;
import com.frk.crd.jms.service.impl.MessagePublishServiceImpl;
import org.apache.activemq.broker.BrokerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.ConnectionFactory;

@ActiveProfiles(value = "local")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CRDJMSConfiguration.class, CRDActiveMQConfiguration.class, MessagePublishServiceImpl.class})
public class CRDJMSConfigurationTest {
  @Autowired
  protected JmsTemplate jmsTemplate;
  @Autowired
  protected ConnectionFactory connectionFactory;
  @Autowired
  protected BrokerService embeddedBroker;
  @Autowired
  protected MessagePublishService messagePublishService;

  @Test
  void autowire() {
    Assertions.assertNotNull(connectionFactory);
    Assertions.assertNotNull(jmsTemplate);
    Assertions.assertNotNull(embeddedBroker);

    Assertions.assertNotNull(messagePublishService);
  }
}