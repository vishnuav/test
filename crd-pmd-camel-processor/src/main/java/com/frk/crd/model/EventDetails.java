package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class EventDetails {
  private long id;
  private String refId, status, manager, trader, execBroker, fillBroker;
  private int targetQty, execQty;

  public EventDetails(@JsonProperty("Id") long id, @JsonProperty("RefId") String refId, @JsonProperty("Status") String status,
                      @JsonProperty("Manager") String manager, @JsonProperty("Trader") String trader,
                      @JsonProperty("ExecBroker") String execBroker, @JsonProperty("FillBroker") String fillBroker,
                      @JsonProperty("TargetQty") int targetQty, @JsonProperty("ExecQty") int execQty) {
    this.id = id;
    this.refId = refId;
    this.status = status;
    this.manager = validateStringValue(manager);
    this.trader = validateStringValue(trader);
    this.execBroker = validateStringValue(execBroker);
    this.fillBroker = validateStringValue(fillBroker);
    this.targetQty = targetQty;
    this.execQty = execQty;
  }

  final String validateStringValue(String manager) {
    return StringUtils.isBlank(manager) || StringUtils.equalsIgnoreCase(manager, "null") ? null : manager;
  }
}