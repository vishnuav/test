package com.frk.crd.jms.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IBMMQProperties {
  private String mqConnectionName;
  private String mqChannel;
  private String mqQueueManager;
  private String mqHost;
  private int mqPort;
}