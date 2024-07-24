package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frk.crd.core.JsonAware;
import com.frk.crd.core.XMLParsingEligible;
import com.frk.crd.broadcast.serialization.CRDBroadcastBooleanSerializer;
import com.frk.crd.broadcast.serialization.CRDBroadcastDateDeSerializer;
import com.frk.crd.broadcast.serialization.CRDBroadcastDateSerializer;
import org.apache.commons.lang3.StringUtils;


@JsonRootName("SECURITY")
public interface BroadcastSecurityAware extends JsonAware, XMLParsingEligible {
  String SWAPTION_IRS = "SWPTNIRS";
  String SWAPTION_CREDIT = "SWPTNCDI";

  @JsonProperty("ASSET_CRRNCY_CD")
  String getAssetCurrencyCode();

  @JsonProperty("BUS_DAY_CONV")
  String getBusinessDayConversion();

  @JsonProperty("CALC_AGENT")
  String getCalculationAgent();

  @JsonProperty("CNTRY_OF_RISK")
  String getCountryOfRisk();

  @JsonProperty("CRD_TABLE_NAME")
  String getCrdTableName();

  @JsonProperty("CUSIP")
  String getCusip();

  @JsonProperty("EXERCISE_SETTLE_LAG")
  boolean getExerciseSettleFlag();

  @JsonProperty("EXPIRE_DATE")
  @JsonSerialize(using = CRDBroadcastDateSerializer.class)
  @JsonDeserialize(using = CRDBroadcastDateDeSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddThh:mm:ss")
  long getExpireDate();

  @JsonProperty("EXPIRE_SETTLE_DATE")
  @JsonSerialize(using = CRDBroadcastDateSerializer.class)
  long getExpireSettleDate();

  @JsonProperty("EXT_SEC_ID")
  String getExternalSecId();

  @JsonProperty("SEC_ID")
  String getSecId();

  @JsonProperty("FRST_EXERCISE_DATE")
  @JsonSerialize(using = CRDBroadcastDateSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddThh:mm:ss")
  long getFirstExerciseDate();

  @JsonProperty("ISSUER_CD")
  String getIssuerCode();

  @JsonProperty("ISSUER_NAME")
  String getIssuerName();

  @JsonProperty("ISSUE_CNTRY_CD")
  String getIssueCountryCode();

  @JsonProperty("ISSUE_DATE")
  @JsonSerialize(using = CRDBroadcastDateSerializer.class)
  long getIssueDate();

  @JsonProperty("ISSUE_DATE_ADJ")
  String getIssueDateAdjustment();

  @JsonProperty("LOC_CRRNCY_CD")
  String getLocationCurrencyCode();

  @JsonProperty("MAT_DATE_ADJ")
  String getMaturityDateAdjustment();

  @JsonProperty("MKT_PRICE")
  double getMarketPx();

  @JsonProperty("OPTN_EXPIRE_TYPE")
  String getOptionExpireType();

  @JsonProperty("OTC_CLEAR_ELIG_IND")
  @JsonSerialize(using = CRDBroadcastBooleanSerializer.class)
  boolean getOtcClearingEligibilityIndicator();

  @JsonProperty("PARENT_ISSUER_CD")
  String getParentIssuerCode();

  @JsonProperty("PAY_DT_HOL_EXCH_CD")
  String getPayDateHolExchangeCode();

  @JsonProperty("SEC_ISSUE_DATE")
  long getSecurityIssueDate();

  @JsonProperty("SEC_NAME")
  String getSecName();

  @JsonProperty("SEC_TYP_CD")
  String getSecTypeCode();

  @JsonProperty("STRIKE_PRICE")
  double getStrikePx();

  @JsonProperty("SWAPTION_TERM")
  String getSwaptionTerm();

  @JsonProperty("SWAP_LEG_IND")
  String getSwapLegIndicator();

  @JsonProperty("SWAP_SEC_ID")
  String getSwapSecId();

  @JsonProperty("UNDERLY_SEC_ID")
  String getUnderlyingSecId();

  @JsonIgnore
  default boolean isSwaption() {
    return StringUtils.equals(getSecTypeCode(), SWAPTION_IRS) || StringUtils.equals(getSecTypeCode(), SWAPTION_CREDIT);
  }
}