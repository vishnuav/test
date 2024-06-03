package com.frk.crd.configuration;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSHigherEnvironmentConfigurationCondition.class})
public class CRDIBMMQConfiguration {
  @Value("${ibm.mq.connName}")
  @Getter
  private String mqConnectionName;
  @Value("${ibm.mq.channel}")
  @Getter
  private String mqChannel;
  @Value("${ibm.mq.host}")
  @Getter
  private String mqHostName;
  @Value("${ibm.mq.port}")
  @Getter
  private int mqPort;
  @Value("${ibm.mq.queueManager}")
  @Getter
  private String mqQueueManager;

  @Bean
  public ConnectionFactory connectionFactory() throws JMSException {
    MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
    connectionFactory.setHostName(mqHostName);
    connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
    connectionFactory.setChannel(mqChannel);
    connectionFactory.setPort(mqPort);
    connectionFactory.setStringProperty(WMQConstants.USERID, "");
    connectionFactory.setStringProperty(WMQConstants.PASSWORD, "");
    connectionFactory.setQueueManager(mqQueueManager);
    connectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, false);
    return connectionFactory;
  }

  @Bean
  @Profile({"!local", "!default"})
  public JmsTemplate jmsTemplate() throws JMSException {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setPubSubDomain(true);
    template.setDeliveryPersistent(true);
    return template;
  }
}