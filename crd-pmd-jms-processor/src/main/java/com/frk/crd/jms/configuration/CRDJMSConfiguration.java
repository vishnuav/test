package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.ActiveMQProperties;
import com.frk.crd.jms.model.IBMMQProperties;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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