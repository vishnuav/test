package com.jackson.xml.model.broadcast;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Security {
  private String cusip;
  private String sedol;
  @JsonAlias("secId")
  private String securityId;
  @JsonAlias("secTypCd")
  private String securityTypeCode;
  @JsonAlias("invClassCd")
  private String invClassCode;
  @JsonAlias("transType")
  private String transactionType;
  @JsonAlias("targetQty")
  private double targetQuantity;
  @JsonAlias("execQty")
  private double executionQuantity;
}