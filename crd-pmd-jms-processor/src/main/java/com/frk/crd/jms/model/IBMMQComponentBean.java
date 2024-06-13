package com.frk.crd.jms.model;

public class IBMMQComponentBean implements JMSComponentBean {
  public static final String IBMMQ_COMPONENT = "ibmmq";

  public String componentInfo() {
    return IBMMQ_COMPONENT;
  }
}