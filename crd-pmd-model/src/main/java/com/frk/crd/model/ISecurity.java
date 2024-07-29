package com.frk.crd.model;

import java.util.Date;

public interface ISecurity {
  String getSecId();

  String getAssetCurrencyCode();

  String getSecurityName();

  String getExternalSecId();

  Date getFirstExerciseDate();

  Date getExpireDate();

  String getExpireTimeZoneRegionCode();

  String getCountryOfRisk();

  String getIncorporatedCountryCode();

  String getIssuerCode();

  Double getStrikePx();

  Date getExpireSettleDate();

  String getSecurityTypeCode();

  String getCusip();

  String getIssueCountryCode();

  Date getIssueDate();

  String getLocationCurrencyCode();

  Double getMarketPx();

  String getParentSecId();

  String getParentIssuerCode();

  String getIssuerName();

  String getOptionExpireType();

  Date getFixingDate();

  String getOtcClearingEligibilityIndicator();

  Date getSecurityIssueDate();

  String getMaturityAdjustment();

  String getIssueDateAdjustment();

  String getPayDateHolExchangeCode();

  String getExerciseSettleLag();

  String getCalcAgent();

  String getBusinessDayConversion();

  String getUnderlyingSecId();

  String getSwapSecId();
}
