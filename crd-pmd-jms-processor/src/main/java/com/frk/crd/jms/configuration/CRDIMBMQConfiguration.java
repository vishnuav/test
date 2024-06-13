package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.JMSComponentBean;
import com.frk.crd.jms.model.IBMMQComponentBean;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Conditional(value = {JMSHigherEnvironmentConfigurationCondition.class})
public class CRDIMBMQConfiguration {
  @Getter
  @Value(value = "${ibm.mq.connName}")
  private String mqConnectionName;
  @Getter
  @Value(value = "${ibm.mq.channel}")
  private String mqChannel;
  @Getter
  @Value(value = "${ibm.mq.queueManager}")
  private String mqQueueManager;
  @Getter
  @Value(value = "${ibm.mq.host}")
  private String mqHost;
  @Getter
  @Value(value = "${ibm.mq.port}")
  private int mqPort;

  @Bean
  public JMSComponentBean jmsComponent() {
    return new IBMMQComponentBean();
  }

  @Bean
  public ConnectionFactory connectionFactory() throws JMSException {
    MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
    connectionFactory.setHostName(mqHost);
    connectionFactory.setChannel(mqChannel);
    connectionFactory.setQueueManager(mqQueueManager);
    connectionFactory.setPort(mqPort);
    connectionFactory.setStringProperty(WMQConstants.USERID, StringUtils.EMPTY);
    connectionFactory.setStringProperty(WMQConstants.PASSWORD, StringUtils.EMPTY);
    connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
    return connectionFactory;
  }
}