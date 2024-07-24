package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.ActiveMQComponentBean;
import com.frk.crd.jms.model.JMSComponentBean;
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
  @Getter
  @Value(value = "${spring.activemq.brokerUrl}")
  private String brokerURL;
  @Getter
  @Value(value = "${spring.activemq.user}")
  private String brokerUser;
  @Getter
  @Value(value = "${spring.activemq.password}")
  private String brokerPassword;

  @Bean
  public JMSComponentBean jmsComponentBean() {
    return new ActiveMQComponentBean();
  }

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
    if (!embeddedBroker.isStarted()) {
      embeddedBroker.start(true);
    }
    return embeddedBroker;
  }
}