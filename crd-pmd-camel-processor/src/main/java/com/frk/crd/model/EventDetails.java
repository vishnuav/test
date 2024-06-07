package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class EventDetails {
  @JsonAlias("Id")
  private long id;
  @JsonAlias("RefId")
  private String refId;
  @JsonAlias("Status")
  private String status;
  @JsonAlias("TargetQty")
  private int targetQty;
  @JsonAlias("ExecQty")
  private int execQty;

  private String manager, trader, execBroker, fillBroker;

  @JsonAlias("Manager")
  public void setManager(String manager) {
    this.manager = validateStringValue(manager);
  }

  @JsonAlias("Trader")
  public void setTrader(String trader) {
    this.trader = validateStringValue(trader);
  }

  @JsonAlias("ExecBroker")
  public void setExecBroker(String execBroker) {
    this.execBroker = validateStringValue(execBroker);
  }

  @JsonAlias("FillBroker")
  public void setFillBroker(String fillBroker) {
    this.fillBroker = validateStringValue(fillBroker);
  }

  final String validateStringValue(String manager) {
    return StringUtils.isBlank(manager) || StringUtils.equalsIgnoreCase(manager, "null") ? null : manager;
  }
}