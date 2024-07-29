package com.frk.crd.model;

public interface IAllocation {
  String getOriginalOrderId();

  String getOrderId();

  Double getTargetQuantity();

  Double getTargetNotionalAmount();

  Double getExecutionPx();

  Double getExecutionQuantity();

  Double getExecutionAmount();

  Double getTargetAmount();

  Double getNetMoney();

  String getCommissionIndicator();

  String getAccountCode();

  Double getCommissionAmount();
}