package com.frk.crd.db.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBBroadcastAllocation implements Serializable {
  private String origOrderId;
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