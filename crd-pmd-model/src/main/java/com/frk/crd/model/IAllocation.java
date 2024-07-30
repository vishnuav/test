package com.frk.crd.model;

import com.frk.crd.core.JsonAware;

public interface IAllocation extends JsonAware {
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