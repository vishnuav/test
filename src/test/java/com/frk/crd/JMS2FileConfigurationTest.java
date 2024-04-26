package com.frk.crd;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.ConnectionFactory;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JMS2FileConfigurationTest {
  @Value("application.in.queue")
  protected String inQueue;
  @Value("application.out.queue")
  private String outQueue;

  @Autowired
  protected ConnectionFactory connectionFactory;
  @Autowired
  protected JmsTemplate jmsTemplate;
  @Autowired
  protected CamelRoute camelRoute;
  @Autowired
  protected Producer producer;

  protected static BrokerService embeddedBroker = new BrokerService();

  @Test
  void autowire() {
    Assertions.assertFalse(StringUtils.isBlank(inQueue));
    Assertions.assertFalse(StringUtils.isBlank(outQueue));
    Assertions.assertNotNull(connectionFactory);
    Assertions.assertNotNull(jmsTemplate);
    Assertions.assertNotNull(camelRoute);
    Assertions.assertNotNull(producer);
  }
}