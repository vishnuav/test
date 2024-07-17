package com.frk.crd.events.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.frk.crd.core.JsonAware;
import com.frk.crd.core.XMLParsingEligible;
import org.apache.commons.lang3.StringUtils;

interface BroadcastSecurityAware extends JsonAware, XMLParsingEligible {
  String SWAPTION_IRS = "SWPTNIRS";
  String SWAPTION_CREDIT = "SWPTNCDI";

  @JsonProperty("CRD_TABLE_NAME")
  String getCrdTableName();

  @JsonProperty("SEC_ID")
  String getSecId();

  @JsonProperty("SEC_NAME")
  String getSecName();

  @JsonProperty("UNDERLYING_SEC_ID")
  String getUnderlyingSecId();

  @JsonProperty("SEC_TYP_CD")
  String getSecTypeCode();

  @JsonProperty("CUSIP")
  String getCusip();

  @JsonProperty("ISSUER_CNTRY_CD")
  String getIssueCountryCode();

  @JsonProperty("ASSET_CURRNCY_CD")
  String getAssetCurrencyCode();

  @JsonProperty("EXT_SEC_ID")
  String getExternalSecId();

  @JsonProperty("CNTRY_OF_RISK")
  String getCountryOfRisk();

  @JsonProperty("ISSUER_CD")
  String getIssuerCode();

  @JsonProperty("PARENT_ISSUER_CD")
  String getParentIssuerCode();

  @JsonProperty("LOC_CRRNCY_CD")
  String getLocationCurrencyCode();

  @JsonProperty("OPTN_EXPIRE_TYPE")
  String getOptionExpireType();

  @JsonProperty("MAT_DATE_ADJ")
  String getMaturityDateAdjustment();

  @JsonProperty("ISSUE_DATE_ADJ")
  String getIssuerDateAdjustment();

  @JsonProperty("OTC_CLEAR_ELIG_IND")
  boolean isOtcClearingEligibleIndicator();

  @JsonProperty("STRIKE_PRICE")
  double getStrikePx();

  @JsonProperty("MKT_PRICE")
  double getMarketPx();

  @JsonProperty("FRST_EXERCISE_DATE")
  long getFirstExerciseDate();

  @JsonProperty("EXPIRE_DATE")
  long getExpireDate();

  @JsonProperty("EXPIRE_SETTLE_DATE")
  long getExpireSettleDate();


  @JsonProperty("ISSUE_DATE")
  long getIssueDate();

  @JsonIgnore
  default boolean isSwaption() {
    return StringUtils.equals(getSecTypeCode(), SWAPTION_IRS) || StringUtils.equals(getSecTypeCode(), SWAPTION_CREDIT);
  }
}