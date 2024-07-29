package com.frk.crd.jms.model;

public class ActiveMQComponentBean implements JMSComponentBean {
  public static final String ACTIVEMQ_COMPONENT = "activemq";

  public String componentInfo() {
    return ACTIVEMQ_COMPONENT;
  }
}