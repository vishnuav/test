package com.frk.crd.db.model;

import java.util.Date;

import com.frk.crd.model.ISecurity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBSecurity implements ISecurity {
  private String secId;
  private String assetCurrencyCode;
  private String securityName;
  private String externalSecId;
  private Date firstExerciseDate;
  private Date expireDate;
  private String expireTimeZoneRegionCode;
  private String countryOfRisk;
  private String incorporatedCountryCode;
  private String issuerCode;
  private Double strikePx;
  private Date expireSettleDate;
  private String securityTypeCode;
  private String cusip;
  private String issueCountryCode;
  private Date issueDate;
  private String locationCurrencyCode;
  private Double marketPx;
  private String parentSecId;
  private String parentIssuerCode;
  private String issuerName;
  private String optionExpireType;
  private Date fixingDate;
  private String otcClearingEligibilityIndicator;
  private Date securityIssueDate;
  private String maturityAdjustment;
  private String issueDateAdjustment;
  private String payDateHolExchangeCode;
  private String exerciseSettleLag;
  private String calcAgent;
  private String businessDayConversion;
  private String underlyingSecId;
  private String swapSecId;
  private String accrualTypeCode;
  private Double contractSize;
  private Double couponDom;
  private String couponEOMFlag;
  private Double couponRate;
  private Double couponWom;
  private Double couponBasis;
  private Date firstPaymentDate;
  private String lastModifyBy;
  private Date lastPaymentDate;
  private String listExchangeCode;
  private Date maturityDate;
  private Date marketPxDateTime;
  private String paymentFrequency;
  private String rateBasisSecId;
  private Double resetDow;
  private Double resetMargin;
  private Double resetLagDays;
  private String sedol;
  private Double spread;
  private String swapLegIndicator;
  private String swapSecIndicator;
  private String ticker;
  private String vrdnFrequencyCode;
  private String clip;
  private Double attachPt;
  private Double detachPt;
  private String udfChar23;
  private String principalExchangeCode;
  private String swapSubType;
  private Double fixingRate;
  private String underlySecId;
  private String udfChar1;
  private Double dayBasis;
}