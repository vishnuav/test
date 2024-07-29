package com.frk.crd.db.model;

import com.frk.crd.model.IAllocation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBAllocation implements IAllocation {
  private String originalOrderId;
  private String orderId;
  private Double targetQuantity;
  private Double targetNotionalAmount;
  private Double executionPx;
  private Double executionQuantity;
  private Double executionAmount;
  private Double targetAmount;
  private Double netMoney;
  private String commissionIndicator;
  private String accountCode;
  private Double commissionAmount;
}