package com.frk.crd.broadcast;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastSecurity implements BroadcastSecurityAware {
  private String crdTableName, assetCurrencyCode, businessDayConversion, calculationAgent, countryOfRisk, cusip, externalSecId, secId,
      issuerCode, issuerName, issueCountryCode, issueDateAdjustment, locationCurrencyCode, maturityDateAdjustment, optionExpireType,
      parentIssuerCode, payDateHolExchangeCode, secName, secTypeCode, swaptionTerm, swapLegIndicator,
      swapSecId, underlyingSecId;

  private String otcClearingEligibilityIndicator, exerciseSettleFlag;

  private double marketPx, strikePx;

  private Date expireDate, expireSettleDate, firstExerciseDate, issueDate, securityIssueDate;

  @Override
  public String getExerciseSettleFlag() {
    return this.exerciseSettleFlag;
  }

  @Override
  public String getOtcClearingEligibilityIndicator() {
    return otcClearingEligibilityIndicator;
  }
}