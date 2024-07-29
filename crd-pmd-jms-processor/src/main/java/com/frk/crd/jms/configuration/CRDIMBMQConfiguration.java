package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.IBMMQComponentBean;
import com.frk.crd.jms.model.IBMMQProperties;
import com.frk.crd.jms.model.JMSComponentBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

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
}