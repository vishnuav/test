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

  Date getLastUpdateDate();

  String getOrderAccountCode();

  Double getTargetQuantity();

  Double getTargetAmount();

  String getTargetDiscountRate();

  Double getExecutionDiscountRate();

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

  String getExternalSecId();

  String getLifetimeCap();

  String getLifetimeFloor();

  Date getAuthDate();

  String getAuthUser();

  Double getContractSize();

  String getExecutionDirtyFlag();

  Double getExecutionDirtyPx();

  Double getExecutionYield();

  String getExecutionYieldType();

  Double getFactor();

  String getFee1Code();

  Double getFee1();

  String getFee2Code();

  Double getFee2();

  String getFee3Code();

  Double getFee3();

  String getFee4Code();

  Double getFee4();

  String getFee5Code();

  Double getFee5();

  String getFee6Code();

  Double getFee6();

  String getFromCurrency();

  Date getFastUpdateDate();

  Double getLimitPx();

  Date getPlaceDate();

  String getPrincipalLocalCurrency();

  String getReasonCode();

  Double getReferencePx();

  String getScenarioId();

  Date getSettleDate();

  String getSpecialInstruction();

  String getTargetCurrency();

  String getToCurrency();

  String getUserClassCode3();

  String getUserClassCode4();

  Date getUdfDate2();

  String getCurrencyPair();

  Double getDealPx();

  Double getExecutionSpotRate();

  Double getIdxFactor();

  String getReferenceSecId();

  String getSwapPartyIndicator();

  String getUdFChar3();

  String getUdFChar4();

  String getUdFChar5();

  String getUdFChar6();

  String getUdFChar10();

  String getUdfChar13();

  String getUdfChar17();

  String getUdfChar19();

  String getUdFChar22();

  Double getUdfFloat5();

  Double getUpfrontPercent();

  String getValueBased();

  Date getValuationDate();

  Double getYield();

  Double getOriginalTargetValue();

  String getClearingVenueCode();

  String getOtcClearingEligibleIndicator();

  Double getDealSpread();

  String getUdfChar12();

  String getTradingVenue();

  Date getExecutionDateTime();
}
