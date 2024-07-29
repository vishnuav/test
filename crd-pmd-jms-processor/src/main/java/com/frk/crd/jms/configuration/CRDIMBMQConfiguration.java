package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.IBMMQComponentBean;
import com.frk.crd.jms.model.IBMMQProperties;
import com.frk.crd.jms.model.JMSComponentBean;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSHIBMMQConfigurationCondition.class})
public class CRDIMBMQConfiguration {
  @Bean
  @ConfigurationProperties(value = "ibm.mq")
  public IBMMQProperties ibmmqProperties() {
    return new IBMMQProperties();
  }

  @Bean
  public JMSComponentBean jmsComponent() {
    return new IBMMQComponentBean();
  }

  @Bean
  @Profile(value = {"ibmmq", "!activemq"})
  public ConnectionFactory connectionFactory(IBMMQProperties ibmmqProperties) throws JMSException {
    MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
    connectionFactory.setHostName(ibmmqProperties.getMqHost());
    connectionFactory.setChannel(ibmmqProperties.getMqChannel());
    connectionFactory.setQueueManager(ibmmqProperties.getMqQueueManager());
    connectionFactory.setPort(ibmmqProperties.getMqPort());
    connectionFactory.setStringProperty(WMQConstants.USERID, StringUtils.EMPTY);
    connectionFactory.setStringProperty(WMQConstants.PASSWORD, StringUtils.EMPTY);
    connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
    return connectionFactory;
  }
}