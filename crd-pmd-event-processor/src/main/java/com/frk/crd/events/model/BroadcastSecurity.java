package com.frk.crd.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BroadcastSecurity implements BroadcastSecurityAware {
  private String crdTableName;
  private String secId;
  private String secName;
  private String underlyingSecId;
  private String secTypeCode;
  private String cusip;
  private String issueCountryCode;
  private String assetCurrencyCode;
  private String externalSecId;
  private String countryOfRisk;
  private String issuerCode;
  private String parentIssuerCode;
  private String locationCurrencyCode;
  private String optionExpireType;
  private String maturityDateAdjustment;
  private String issuerDateAdjustment;

  private boolean otcClearingEligibleIndicator;

  private double strikePx;
  private double marketPx;

  private long issueDate;
  private long expireDate;
  private long firstExerciseDate;
  private long expireSettleDate;
}