package com.frk.crd.db.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class DBOrder implements IOrder {
  private String orderId;
  private String instruction;
  private String brokerReason;
  private String netTradeIndicator;
  private String orderDuration;
  private String exchangeCode;
  private String trader;
  private String manager;
  private String createUser;
  private String lastUpdateUser;
  private String comments;
  private String transactionType;
  private String invoiceClassCode;
  private String status;
  private String includeInCash;
  private String deliveryType;
  private String counterparty;
  private String executingBroker;
  private Date tradeDate;
  private Date toTradeDate;
  private String ipo;
  private String orderAccountCode;
  private Double targetQuantity;
  private Double targetAmount;
  private String targetDiscountRate;
  private String executionDiscountRate;
  private String principleLocalCurrencySecId;
  private String udfFloat16;
  private String taxLotSellConvention;
  private Double netPrincipleAmount;
  private Double targetNotionalBaseAmount;
  private Double targetNotionalAmount;
  private Double executionPx;
  private Double executionQuantity;
  private Double executionAmount;
  private Double netMoney;
  private String commissionIndicator;
  private Double commissionAmount;
  private Double currentBaseMarketValue;
  private String accountCode;
  private String transactionSubType;
  private String secId;
}