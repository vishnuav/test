package com.frk.crd.model;

import java.util.Date;

public interface IOrder {
  String getOrderId();

  String getInstruction();

  String getBrokerReason();

  String getNetTradeIndicator();

  String getOrderDuration();

  String getExchangeCode();

  String getTrader();

  String getManager();

  String getCreateUser();

  String getLastUpdateUser();

  String getComments();

  String getTransactionType();

  String getInvoiceClassCode();

  String getStatus();

  String getIncludeInCash();

  String getDeliveryType();

  String getCounterparty();


  String getExecutingBroker();

  Date getTradeDate();

  Date getToTradeDate();

  String getIpo();

  String getOrderAccountCode();

  Double getTargetQuantity();

  Double getTargetAmount();

  String getTargetDiscountRate();

  String getExecutionDiscountRate();

  String getPrincipleLocalCurrencySecId();

  String getUdfFloat16();

  String getTaxLotSellConvention();

  Double getNetPrincipleAmount();

  Double getTargetNotionalBaseAmount();

  Double getTargetNotionalAmount();

  Double getExecutionPx();

  Double getExecutionQuantity();

  Double getExecutionAmount();

  Double getNetMoney();

  String getCommissionIndicator();

  Double getCommissionAmount();

  Double getCurrentBaseMarketValue();

  String getAccountCode();

  String getTransactionSubType();

  String getSecId();
}
