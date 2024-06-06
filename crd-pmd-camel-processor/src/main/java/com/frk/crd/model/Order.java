package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order implements XMLParsingEligible {
  private long id;
  private String operation;

  private String placeDate;
  private String settleDate;
  private String tradeDate;
  private String status;
  private String manager;
  private String trader;
  private String execBroker;

  public Order(@JsonProperty("crdId") long id, @JsonProperty("op") String operation, @JsonProperty("cusip") String cusip,
               @JsonProperty("sedol") String sedol, @JsonProperty("secId") String securityId, @JsonProperty("secTypCd") String securityTypeCode,
               @JsonProperty("invClassCd") String invClassCode, @JsonProperty("transType") String transactionType,
               @JsonProperty("targetQty") double targetQuantity, @JsonProperty("execQty") double executionQuantity,
               @JsonProperty("placeDate") String placeDate, @JsonProperty("settleDate") String settleDate,
               @JsonProperty("tradeDate") String tradeDate, @JsonProperty("status") String status, @JsonProperty("manager") String manager,
               @JsonProperty("trader") String trader, @JsonProperty("execBroker") String execBroker) {
    this.id = id;
    this.operation = operation;
    this.placeDate = placeDate;
    this.settleDate = settleDate;
    this.tradeDate = tradeDate;
    this.status = status;
    this.manager = manager;
    this.trader = trader;
    this.execBroker = execBroker;
  }
}