package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.ActiveMQProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.jms.ConnectionFactory;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSActiveMQConfigurationCondition.class})
public class CRDActiveMQConfiguration {
  @Bean
  @ConfigurationProperties("spring.activemq")
  public ActiveMQProperties activeMQProperties() {
    return new ActiveMQProperties();
  }

  @Bean
  public ConnectionFactory connectionFactory(ActiveMQProperties activeMQProperties) {
    return new ActiveMQConnectionFactory(activeMQProperties.getUser(), activeMQProperties.getPassword(), activeMQProperties.getBrokerURL());
  }

  @Bean
  public BrokerService brokerService(ActiveMQProperties activeMQProperties) throws Exception {
    BrokerService embeddedBroker = new BrokerService();
    embeddedBroker.addConnector(activeMQProperties.getBrokerURL());
    embeddedBroker.setPersistent(false);
    if (!embeddedBroker.isStarted()) {
      embeddedBroker.start(true);
    }
    return embeddedBroker;
  }
}