package com.frk.crd.broadcast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastSecurity implements BroadcastSecurityAware {
  private String crdTableName, assetCurrencyCode, businessDayConversion, calculationAgent, countryOfRisk, cusip, externalSecId, secId,
    swapSecId, underlyingSecId, parentSecId, issuerCode, issuerName, issueCountryCode, issueDateAdjustment, locationCurrencyCode,
    maturityDateAdjustment, optionExpireType, parentIssuerCode, payDateHolExchangeCode, securityName, securityTypeCode, swaptionTerm,
    swapLegIndicator, expireTimeZoneRegionCode, incorporatedCountryCode, otcClearingEligibilityIndicator, exerciseSettleLag, maturityAdjustment,
    calcAgent;

  private Double marketPx, strikePx;

  private Date expireDate, expireSettleDate, fixingDate, firstExerciseDate, issueDate, securityIssueDate;

  public String getExerciseSettleLag() {
    return this.exerciseSettleLag;
  }

  @Override
  public String getOtcClearingEligibilityIndicator() {
    return otcClearingEligibilityIndicator;
  }
}