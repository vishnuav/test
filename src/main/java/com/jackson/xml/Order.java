package com.jackson.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order implements CrdIDEligible, OperationEligible {
  private long crdId;
  private String version;
  private String operation;
  private String cusip;
  private String sedol;
  @JsonAlias("secId")
  private String securityId;
  @JsonAlias("secTypCd")
  private String securityTypeCode;
  @JsonAlias("invClassCd")
  private String inventoryClassCode;
  private String ipo;
  @JsonAlias("udfChar_28")
  private String udfChar28;
  private String trader;
  private String deliveryType;
  private String execBroker;
  @JsonAlias("udfDate_3")
  private String udfDate3;
  private String createUser;
  private String status;

  @JsonAlias("targetAmt")
  private double targetAmount;
  private double execDirtyPrice;
  private double execYield;
  private double execAmt;
  @JsonAlias("cntrctSize")
  private int contractSize;
  private double referencePrice;
  private double execQty;
  @JsonAlias("commisionInd")
  private String commisionIndicator;
  @JsonAlias("exchCd")
  private String exchangeCode;
  private String execYieldType;
  private String valBased;
  @JsonAlias("transType")
  private String transactionType;
  private String instruction;
  @JsonAlias("netTradeInd")
  private String netTradeIndicator;

  private double factor;
  @JsonAlias("prinLocalCrrncy")
  private String principalLocalCurrency;
  private String placeDate;
  @JsonAlias("targetQty")
  private String targetQuantity;
  private String manager;
  private String settleDate;
  private String tradeDate;
  private String brokerReason;

  private UserClassCD usrClassCd;
  private final List<Fee> fees = new ArrayList<>();

  @JsonAlias("fee")
  public void setFees(Fee fee) {
    if (fee != null) {
      fees.add(fee);
    }
  }
}