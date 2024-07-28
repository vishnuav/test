package com.frk.crd.jms.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActiveMQProperties {
  private String brokerURL;
  private String user;
  private String password;
}