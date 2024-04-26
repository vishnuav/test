package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order implements CrdIDEligible, OperationEligible {
  private long id;
  private String operation;

  private Security security;

  private String placeDate;
  private String settleDate;
  private String tradeDate;
  private String status;
  private String manager;
  private String trader;
  private String execBroker;

  private final List<Allocation> allocations = new ArrayList<>();

  public Order(@JsonProperty("crdId") long id, @JsonProperty("op") String operation, @JsonProperty("cusip") String cusip,
               @JsonProperty("sedol") String sedol, @JsonProperty("secId") String securityId, @JsonProperty("secTypCd") String securityTypeCode,
               @JsonProperty("invClassCd") String invClassCode, @JsonProperty("transType") String transactionType,
               @JsonProperty("targetQty") double targetQuantity, @JsonProperty("execQty") double executionQuantity,
               @JsonProperty("placeDate") String placeDate, @JsonProperty("settleDate") String settleDate,
               @JsonProperty("tradeDate") String tradeDate, @JsonProperty("status") String status, @JsonProperty("manager") String manager,
               @JsonProperty("trader") String trader, @JsonProperty("execBroker") String execBroker) {
    this.id = id;
    this.operation = operation;
    this.security = new Security(cusip, sedol, securityId, securityTypeCode, invClassCode, transactionType, targetQuantity, executionQuantity);
    this.placeDate = placeDate;
    this.settleDate = settleDate;
    this.tradeDate = tradeDate;
    this.status = status;
    this.manager = manager;
    this.trader = trader;
    this.execBroker = execBroker;
  }

  @JsonAlias("allocation")
  public void setAllocations(Allocation allocation) {
    if (allocation != null) {
      this.allocations.add(allocation);
    }
  }
}