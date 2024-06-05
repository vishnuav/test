package com.frk.crd.configuration;

import javax.jms.ConnectionFactory;
import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSDefalutLocalConfigurationCondition.class})
public class CRDActiveMQConfiguration {
//  @Value(value = "${crd.app.rest.connector.host}")
//  @Getter
//  protected String marketClientHost;
//  @Value(value = "${crd.app.rest.connector.port}")
  @Getter
  protected int marketClientPort;
  @Value("${spring.activemq.broker-url}")
  @Getter
  private String BROKER_URL;
  @Value("${spring.activemq.user}")
  @Getter
  private String BROKER_USERNAME;
  @Value("${spring.activemq.password}")
  @Getter
  private String BROKER_PASSWORD;

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(BROKER_URL);
    connectionFactory.setPassword(BROKER_USERNAME);
    connectionFactory.setUserName(BROKER_PASSWORD);
    return connectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setPubSubDomain(true);
    template.setDeliveryPersistent(true);
    return template;
  }

  @Bean
  public BrokerService brokerService() throws Exception {
    BrokerService embeddedBroker = new BrokerService();
    embeddedBroker.addConnector(BROKER_URL);
    embeddedBroker.setPersistent(false);
    embeddedBroker.start(true);
    return embeddedBroker;
  }
}