package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDetails {
  private String id, refId, status, manager, trader, execBroker, fillBroker;
  private int targetQty, execQty;

  public EventDetails(@JsonProperty("Id") String id, @JsonProperty("RefId") String refId, @JsonProperty("Status") String status,
                      @JsonProperty("Manager") String manager, @JsonProperty("Trader") String trader,
                      @JsonProperty("ExecBroker") String execBroker, @JsonProperty("FillBroker") String fillBroker,
                      @JsonProperty("TargetQty") int targetQty, @JsonProperty("ExecQty") int execQty) {
    this.id = id;
    this.refId = refId;
    this.status = status;
    this.manager = manager;
    this.trader = trader;
    this.execBroker = execBroker;
    this.fillBroker = fillBroker;
    this.targetQty = targetQty;
    this.execQty = execQty;
  }
}