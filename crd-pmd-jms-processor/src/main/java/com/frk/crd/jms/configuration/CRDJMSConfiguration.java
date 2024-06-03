package com.frk.crd.jms.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class CRDJMSConfiguration {
  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory);
    // todo FixMe: Set this to false - for AMQ
    jmsTemplate.setPubSubDomain(true);
    jmsTemplate.setDeliveryPersistent(true);
    return jmsTemplate;
  }
}