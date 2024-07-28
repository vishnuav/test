package com.frk.crd.jms.configuration;

import com.frk.crd.jms.service.MessagePublishService;
import com.frk.crd.jms.service.impl.MessagePublishServiceImpl;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.jms.ConnectionFactory;

@ActiveProfiles(value = {"local", "activemq"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CRDJMSConfiguration.class, CRDActiveMQConfiguration.class, CRDIMBMQConfiguration.class, MessagePublishServiceImpl.class})
public class CRDJMSConfigurationTest {
  @Autowired
  protected JmsTemplate jmsTemplate;
  @Autowired
  protected ConnectionFactory connectionFactory;
  @Autowired
  protected BrokerService embeddedBroker;
  @Autowired
  protected MessagePublishService messagePublishService;

  @Value(value = "${crd.app.pmd.in.queue}")
  protected String pmdInQueue;
  @Value(value = "${crd.app.pmd.forward.queue}")
  protected String pmdForwardQueue;

  @Value(value = "${crd.app.chub.in.queue}")
  protected String chubInQueue;
  @Value(value = "${crd.app.chub.forward.queue}")
  protected String chubForwardQueue;

  @Test
  void autowire() {
    Assertions.assertNotNull(connectionFactory);
    Assertions.assertNotNull(jmsTemplate);
    Assertions.assertNotNull(embeddedBroker);

    Assertions.assertNotNull(messagePublishService);

    Assertions.assertFalse(StringUtils.isBlank(pmdInQueue));
    Assertions.assertFalse(StringUtils.isBlank(pmdForwardQueue));
    Assertions.assertFalse(StringUtils.isBlank(chubInQueue));
    Assertions.assertFalse(StringUtils.isBlank(chubForwardQueue));
  }
}