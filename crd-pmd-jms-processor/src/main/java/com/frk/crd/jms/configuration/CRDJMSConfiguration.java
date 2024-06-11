package com.frk.crd.jms.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class CRDJMSConfiguration {
  @Bean
  public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, DynamicDestinationResolver destinationResolver) {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(connectionFactory);
    jmsTemplate.setDestinationResolver(destinationResolver);
    // todo FixMe: Set this to false - for AMQ
    jmsTemplate.setPubSubDomain(true);
    jmsTemplate.setDeliveryPersistent(true);
    return jmsTemplate;
  }

  @Bean
  public DynamicDestinationResolver destinationResolver() {
    return new DynamicDestinationResolver() {
      @Override
      public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
        return super.resolveDestinationName(session, destinationName, false);
      }
    };
  }
}