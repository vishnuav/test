package com.frk.crd.jms.configuration;

import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSDefaultLocalConfigurationCondition.class})
public class CRDActiveMQConfiguration {
  @Value(value = "${spring.activemq.brokerUrl}")
  @Getter
  private String brokerURL;
  @Value(value = "${spring.activemq.user}")
  @Getter
  private String brokerUser;
  @Value(value = "${spring.activemq.password}")
  @Getter
  private String brokerPassword;

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(brokerURL);
    connectionFactory.setUserName(brokerUser);
    connectionFactory.setPassword(brokerPassword);
    return connectionFactory;
  }

  @Bean
  public BrokerService brokerService() throws Exception {
    BrokerService embeddedBroker = new BrokerService();
    embeddedBroker.addConnector(brokerURL);
    embeddedBroker.setPersistent(false);
    embeddedBroker.start(true);
    return embeddedBroker;
  }
}