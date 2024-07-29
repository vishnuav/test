package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.frk.crd.core.XMLParsingEligible;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


@JsonRootName("SECURITY")
public interface BroadcastSecurityAware extends XMLParsingEligible {
  String SWAPTION_IRS = "SWPTNIRS";
  String SWAPTION_CREDIT = "SWPTNCDI";

  String getAssetCurrencyCode();

  String getBusinessDayConversion();

  String getCalcAgent();

  String getCountryOfRisk();

  String getCrdTableName();

  String getCusip();

  String getExerciseSettleLag();

  Date getExpireDate();

  Date getExpireSettleDate();

  String getExternalSecId();

  String getSecId();

  Date getFirstExerciseDate();

  String getIssuerCode();

  String getIssuerName();

  String getIssueCountryCode();

  Date getIssueDate();

  String getIssueDateAdjustment();

  String getLocationCurrencyCode();

  String getMaturityDateAdjustment();

  Double getMarketPx();

  String getOptionExpireType();

  String getOtcClearingEligibilityIndicator();

  String getParentIssuerCode();

  String getPayDateHolExchangeCode();

  Date getSecurityIssueDate();

  String getSecurityName();

  String getSecurityTypeCode();

  Double getStrikePx();

  String getSwaptionTerm();

  String getSwapLegIndicator();

  String getSwapSecId();

  String getUnderlyingSecId();

  @JsonIgnore
  default boolean isSwaption() {
    return StringUtils.equals(getSecurityTypeCode(), SWAPTION_IRS) || StringUtils.equals(getSecurityTypeCode(), SWAPTION_CREDIT);
  }
}