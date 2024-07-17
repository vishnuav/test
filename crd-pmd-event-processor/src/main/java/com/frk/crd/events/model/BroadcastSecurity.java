package com.frk.crd.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastSecurity implements BroadcastSecurityAware {
  private String crdTableName, assetCurrencyCode, businessDayConversion, calculationAgent, countryOfRisk, cusip, externalSecId, secId,
    issuerCode, issuerName, issueCountryCode, issueDateAdjustment, locationCurrencyCode, maturityDateAdjustment, optionExpireType,
    parentIssuerCode, payDateHolExchangeCode, secName, secTypeCode, swaptionTerm, swapLegIndicator,
    swapSecId, underlyingSecId;

  private boolean otcClearingEligibilityIndicator, exerciseSettleFlag;

  private double marketPx, strikePx;

  private long expireDate, expireSettleDate, firstExerciseDate, issueDate, securityIssueDate;

  @Override
  public boolean getExerciseSettleFlag() {
    return this.exerciseSettleFlag;
  }

  @Override
  public boolean getOtcClearingEligibilityIndicator() {
    return otcClearingEligibilityIndicator;
  }
}