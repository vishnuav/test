package com.frk.crd.jms.model;

import java.io.Serializable;

public interface JMSComponentBean extends Serializable {
  String componentInfo();

  default String routeInfo() {
    return componentInfo() + ":";
  }
}