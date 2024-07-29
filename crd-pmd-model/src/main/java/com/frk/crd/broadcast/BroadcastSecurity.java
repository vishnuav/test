package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frk.crd.model.ISecurity;
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
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName;
  @JsonProperty("ASSET_CRRNCY_CD")
  private String assetCurrencyCode;
  @JsonProperty("BUS_DAY_CONV")
  private String businessDayConversion;
  @JsonProperty("CNTRY_OF_RISK")
  private String countryOfRisk;
  @JsonProperty("CUSIP")
  private String cusip;
  @JsonProperty("EXT_SEC_ID")
  private String externalSecId;
  @JsonProperty("SEC_ID")
  private String secId;
  @JsonProperty("SWAP_SEC_ID")
  private String swapSecId;
  @JsonProperty("UNDERLY_SEC_ID")
  private String underlyingSecId;
  @JsonProperty("PARENT_SEC_ID")
  private String parentSecId;
  @JsonProperty("ISSUER_CD")
  private String issuerCode;
  @JsonProperty("ISSUER_NAME")
  private String issuerName;
  @JsonProperty("ISSUE_CNTRY_CD")
  private String issueCountryCode;
  @JsonProperty("ISSUE_DATE_ADJ")
  private String issueDateAdjustment;
  @JsonProperty("LOC_CRRNCY_CD")
  private String locationCurrencyCode;
  private String maturityDateAdjustment;
  @JsonProperty("OPTN_EXPIRE_TYPE")
  private String optionExpireType;
  @JsonProperty("PARENT_ISSUER_CD")
  private String parentIssuerCode;
  @JsonProperty("PAY_DT_HOL_EXCH_CD")
  private String payDateHolExchangeCode;
  @JsonProperty("SEC_NAME")
  private String securityName;
  @JsonProperty("SEC_TYP_CD")
  private String securityTypeCode;
  @JsonProperty("SWAPTION_TERM")
  private String swaptionTerm;
  @JsonProperty("SWAP_LEG_IND")
  private String swapLegIndicator;
  private String expireTimeZoneRegionCode;
  @JsonProperty("INCORP_CNTRY_CD")
  private String incorporatedCountryCode;
  @JsonProperty("OTC_CLEAR_ELIG_IND")
  private String otcClearingEligibilityIndicator;
  @JsonProperty("EXERCISE_SETTLE_LAG")
  private String exerciseSettleLag;
  @JsonProperty("MAT_DATE_ADJ")
  private String maturityAdjustment;
  @JsonProperty("CALC_AGENT")
  private String calcAgent;

  @JsonProperty("MKT_PRICE")
  private Double marketPx;
  @JsonProperty("STRIKE_PRICE")
  private Double strikePx;

  @JsonProperty("EXPIRE_DATE")
  private Date expireDate;
  @JsonProperty("EXPIRE_SETTLE_DATE")
  private Date expireSettleDate;
  private Date fixingDate;
  @JsonProperty("FRST_EXERCISE_DATE")
  private Date firstExerciseDate;
  @JsonProperty("ISSUE_DATE")
  private Date issueDate;
  private Date securityIssueDate;

  public String getExerciseSettleLag() {
    return this.exerciseSettleLag;
  }

  @Override
  public String getOtcClearingEligibilityIndicator() {
    return otcClearingEligibilityIndicator;
  }

  public BroadcastSecurity(ISecurity security) {
    this("CSM_SECURITY", security.getAssetCurrencyCode(), null, security.getCountryOfRisk(), security.getCusip(),
      security.getExternalSecId(), security.getSecId(), security.getSwapSecId(), security.getUnderlyingSecId(), security.getParentSecId(),
      security.getIssuerCode(), security.getIssuerName(), security.getIssueCountryCode(), security.getIssueDateAdjustment(),
      security.getLocationCurrencyCode(), security.getMaturityAdjustment(), security.getOptionExpireType(), security.getParentIssuerCode(),
      security.getPayDateHolExchangeCode(), security.getSecurityName(), security.getSecurityTypeCode(), "swaptionTerm",
      "swapLegInd", security.getExpireTimeZoneRegionCode(), security.getIncorporatedCountryCode(),
      security.getOtcClearingEligibilityIndicator(), security.getExerciseSettleLag(),
      security.getMaturityAdjustment(), security.getCalcAgent(), security.getMarketPx(), security.getStrikePx(), security.getExpireDate(),
      security.getExpireSettleDate(), security.getFixingDate(), security.getFirstExerciseDate(), security.getIssueDate(),
      security.getSecurityIssueDate());
  }
}