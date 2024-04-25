package com.jackson.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Allocation implements CrdIDEligible, OperationEligible {
  private int id;
  private long crdId;
  private String operation;
  private String fundManager;
  @JsonAlias("targetAmt")
  private double targetAmount;
  private double incSettleAmount;
  private double execPrice;
  private UDF udf;
  @JsonAlias("origOrderId")
  private long originalOrderId;
  @JsonAlias("origTradeId")
  private long originalTradeId;
  @JsonAlias("execAmt")
  private double executionAmount;
  private double incBaseAmount;
  @JsonAlias("execOriginalFace")
  private double executionOriginalFace;
  @JsonAlias("commisionRate")
  private double commissionRate;
  @JsonAlias("primeBkrFeeAmt")
  private double primeBrokerFeeAmount;
  @JsonAlias("acctCd")
  private int accountCode;
  @JsonAlias("execBroker")
  private int executingBroker;
  @JsonAlias("netPrinBaseAmt")
  private double netPrincipalBaseAmt;
  @JsonAlias("targetIncBaseAmt")
  private double targetIncBaseAmount;
  @JsonAlias("targetBaseAmt")
  private double targetBaseAmount;
  @JsonAlias("netPrinAmt")
  private double netPrincipalAmount;
  @JsonAlias("targetQty")
  private double targetQuantity;
  @JsonAlias("refId2")
  private long refId2;
  @JsonAlias("targetIncAmt")
  private double targetIncAmount;
  @JsonAlias("incAmt")
  private double incAmount;

  @JsonAlias("createUser")
  private String createUser;
  @JsonAlias("commisionSettleAmt")
  private double commissionSettlementAmount;
  @JsonAlias("execQty")
  private double executingQuantity;
  @JsonAlias("commisionAmt")
  private double commissionAmount;
  @JsonAlias("prinSettleCrrncy")
  private String principalSettlementCurrency;
  @JsonAlias("netPrinSettleAmt")
  private double netPrincipalSettlementAmount;
  @JsonAlias("execBaseAmt")
  private double executionBaseAmount;
}