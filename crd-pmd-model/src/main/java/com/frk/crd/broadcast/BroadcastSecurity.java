package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName;
  @JsonProperty("ASSET_CRRNCY_CD")
  @JsonAlias("assetCurrencyCode")
  private String assetCurrencyCode;
  @JsonProperty("BUS_DAY_CONV")
  @JsonAlias("businessDayConversion")
  private String businessDayConversion;
  @JsonProperty("CNTRY_OF_RISK")
  @JsonAlias("countryOfRisk")
  private String countryOfRisk;
  @JsonProperty("CUSIP")
  @JsonAlias("cusip")
  private String cusip;
  @JsonProperty("EXT_SEC_ID")
  @JsonAlias("externalSecId")
  private String externalSecId;
  @JsonProperty("SEC_ID")
  @JsonAlias("secId")
  private String secId;
  @JsonProperty("SWAP_SEC_ID")
  @JsonAlias("swapSecId")
  private String swapSecId;
  @JsonProperty("UNDERLY_SEC_ID")
  @JsonAlias("underlyingSecId")
  private String underlyingSecId;
  @JsonProperty("PARENT_SEC_ID")
  @JsonAlias("parentSecId")
  private String parentSecId;
  @JsonProperty("ISSUER_CD")
  @JsonAlias("issuerCode")
  private String issuerCode;
  @JsonProperty("ISSUER_NAME")
  @JsonAlias("issuerName")
  private String issuerName;
  @JsonProperty("ISSUE_CNTRY_CD")
  @JsonAlias("issueCountryCode")
  private String issueCountryCode;
  @JsonProperty("ISSUE_DATE_ADJ")
  @JsonAlias("issueDateAdjustment")
  private String issueDateAdjustment;
  @JsonProperty("LOC_CRRNCY_CD")
  @JsonAlias("locationCurrencyCode")
  private String locationCurrencyCode;
  @JsonProperty("MAT_DATE_ADJ")
  @JsonAlias("maturityDateAdjustment")
  private String maturityDateAdjustment;
  @JsonProperty("OPTN_EXPIRE_TYPE")
  @JsonAlias("optionExpireType")
  private String optionExpireType;
  @JsonProperty("PARENT_ISSUER_CD")
  @JsonAlias("parentIssuerCode")
  private String parentIssuerCode;
  @JsonProperty("PAY_DT_HOL_EXCH_CD")
  @JsonAlias("payDateHolExchangeCode")
  private String payDateHolExchangeCode;
  @JsonProperty("SEC_NAME")
  @JsonAlias("securityName")
  private String securityName;
  @JsonProperty("SEC_TYP_CD")
  @JsonAlias("securityTypeCode")
  private String securityTypeCode;
  @JsonProperty("SWAPTION_TERM")
  @JsonAlias("swaptionTerm")
  private String swaptionTerm;
  @JsonProperty("SWAP_LEG_IND")
  @JsonAlias("swapLegIndicator")
  private String swapLegIndicator;

  private String expireTimeZoneRegionCode;

  @JsonProperty("INCORP_CNTRY_CD")
  @JsonAlias("incorporatedCountryCode")
  private String incorporatedCountryCode;
  @JsonProperty("OTC_CLEAR_ELIG_IND")
  @JsonAlias("otcClearingEligibilityIndicator")
  private String otcClearingEligibilityIndicator;
  @JsonProperty("EXERCISE_SETTLE_LAG")
  @JsonAlias("exerciseSettleLag")
  private String exerciseSettleLag;
  @JsonProperty("CALC_AGENT")
  @JsonAlias("calcAgent")
  private String calcAgent;

  @JsonProperty("MKT_PRICE")
  @JsonAlias("marketPx")
  private Double marketPx;
  @JsonProperty("STRIKE_PRICE")
  @JsonAlias("strikePx")
  private Double strikePx;

  @JsonProperty("EXPIRE_DATE")
  @JsonAlias("expireDate")
  private Date expireDate;
  @JsonProperty("EXPIRE_SETTLE_DATE")
  @JsonAlias("expireSettleDate")
  private Date expireSettleDate;

  private Date fixingDate;
  @JsonProperty("FRST_EXERCISE_DATE")
  @JsonAlias("firstExerciseDate")
  private Date firstExerciseDate;
  @JsonProperty("ISSUE_DATE")
  @JsonAlias("issueDate")
  private Date issueDate;

  private Date securityIssueDate;

  public String getExerciseSettleLag() {
    return this.exerciseSettleLag;
  }

  @Override
  public String getOtcClearingEligibilityIndicator() {
    return otcClearingEligibilityIndicator;
  }
}