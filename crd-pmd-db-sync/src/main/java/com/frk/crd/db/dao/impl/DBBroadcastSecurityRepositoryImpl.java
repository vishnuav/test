package com.frk.crd.db.dao.impl;

import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.model.DBSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DBBroadcastSecurityRepositoryImpl implements DBBroadcastSecurityRepository {
  final String CHILD_SECURITY_SQL = "select sec_id from crd_owner.csm_security where parent_sec_id = ? ";

  final String SECURITY_FOR_SEC_ID_SQL = "SELECT "
      + " s.sec_id, "
      + " s.sec_name, "
      + " s.sec_typ_cd, "
      + " s.accrual_typ_cd, "
      + " s.asset_crrncy_cd, "
      + " s.cntrct_size, "
      + " s.coupon_dom, "
      + " s.coupon_eom_flag, "
      + " s.coupon_rate, "
      + " s.coupon_wom, "
      + " s.cusip, "
      + " s.day_basis, "
      + " s.ext_sec_id, "
      + " s.first_payment_date, "
      + " s.frst_exercise_date, "
      + " s.expire_date, "
      + " s.expire_settle_date, "
      + " s.issue_date, "
      + " s.cntry_of_risk, "
      + " s.issue_cntry_cd, "
      + " s.issuer_cd, "
      + " s.expire_time_zone_rgn_cd, "
      + " s.last_mdfy_by, "
      + " s.last_payment_date, "
      + " s.list_exch_cd, "
      + " s.loc_crrncy_cd, "
      + " s.mature_date, "
      + " s.mkt_price, "
      + " s.mkt_price_datetime, "
      + " s.parent_sec_id, "
      + " s.payment_freq, "
      + " s.rate_basis_sec_id, "
      + " s.reset_dow, "
      + " s.reset_margin, "
      + " s.reset_lag_days, "
      + " s.sedol, "
      + " s.spread, "
      + " s.strike_price, "
      + " s.swap_leg_ind, "
      + " s.swap_sec_ind, "
      + " s.ticker, "
      + " s.vrdn_freq_cd, "
      + " s.incorp_cntry_cd, "
      + " scst.OPTN_EXPIRE_TYPE, "
      + " scst.FIXING_DATE, "
      + " scst.OTC_CLEAR_ELIG_IND, "
      + " scst.MAT_DATE_ADJ, "
      + " scst.ISSUE_DATE_ADJ, "
      + " scst.PAY_DT_HOL_EXCH_CD, "
      + " term.EXERCISE_SETTLE_LAG, "
      + " scst.attach_pt, "
      + " scst.detach_pt, "
      + " scst.udf_char23, "
      + " scst.prin_exch_cd, "
      + " scst.swap_sub_type, "
      + " scst.sec_issue_date, "
      + " scst.fixing_rate, "
      + " i.issuer_name, "
      + " i.parent_issuer_cd, "
      + " term.CALC_AGENT, "
      + " term.BUS_DAY_CONV "
      + " FROM "
      + " crd_owner.csm_security s, "
      + " crd_owner.csm_security_cust scst, "
      + " crd_owner.csm_issuer i, "
      + " crd_owner.csm_security_term term "
      + " where s.sec_id = scst.sec_id "
      + " and s.issuer_cd = i.issuer_cd (+) "
      + " and (scst.udf_char10 IS NULL OR scst.udf_char10 = 'I') "
      + " and s.sec_id = term.sec_id (+) "
      + "and s.sec_id = ? ";

  final String SECURITY_FOR_ORDER_ID = "select "
      + " s.sec_id, "
      + " s.sec_name, "
      + " s.sec_typ_cd, "
      + " s.accrual_typ_cd, "
      + " s.asset_crrncy_cd, "
      + " s.cntrct_size, "
      + " s.coupon_dom, "
      + " s.coupon_eom_flag, "
      + " s.coupon_rate, "
      + " s.coupon_wom, "
      + " s.cusip, "
      + " s.day_basis, "
      + " s.ext_sec_id, "
      + " s.first_payment_date, "
      + " s.frst_exercise_date, "
      + " s.expire_date, "
      + " s.expire_settle_date, "
      + " s.issue_date, "
      + " s.cntry_of_risk, "
      + " s.issue_cntry_cd, "
      + " s.issuer_cd, "
      + " s.expire_time_zone_rgn_cd, "
      + " s.last_mdfy_by, "
      + " s.last_payment_date, "
      + " s.list_exch_cd, "
      + " s.loc_crrncy_cd, "
      + " s.mature_date, "
      + " s.mkt_price, "
      + " s.mkt_price_datetime, "
      + " s.parent_sec_id, "
      + " s.payment_freq, "
      + " s.rate_basis_sec_id, "
      + " s.reset_dow, "
      + " s.reset_margin, "
      + " s.reset_lag_days, "
      + " s.sedol, "
      + " s.spread, "
      + " s.strike_price, "
      + " s.swap_leg_ind, "
      + " s.swap_sec_ind, "
      + " s.ticker, "
      + " s.vrdn_freq_cd, "
      + " s.incorp_cntry_cd, "
      + " usscst.clip, "
      + " scst.OPTN_EXPIRE_TYPE, "
      + " scst.FIXING_DATE, "
      + " scst.OTC_CLEAR_ELIG_IND, "
      + " scst.MAT_DATE_ADJ, "
      + " scst.ISSUE_DATE_ADJ, "
      + " scst.PAY_DT_HOL_EXCH_CD, "
      + " term.EXERCISE_SETTLE_LAG, "
      + " scst.attach_pt, "
      + " scst.detach_pt, "
      + " scst.udf_char23, "
      + " scst.prin_exch_cd, "
      + " scst.swap_sub_type, "
      + " scst.sec_issue_date, "
      + " scst.fixing_rate, "
      + " i.issuer_name, "
      + " i.parent_issuer_cd, "
      + " us.underly_sec_id, "
      + " us.sec_id, "
      + " uss.udf_char1, "
      + " term.CALC_AGENT, "
      + " term.BUS_DAY_CONV "
      + " from crd_owner.ts_order o, crd_owner.csm_security s, crd_owner.csm_security_cust scst, crd_owner.csm_issuer i,"
      + " crd_owner.csm_underlying_security us, crd_owner.csm_security_cust usscst, crd_owner.csm_security uss,"
      + " crd_owner.csm_security_term term"
      + " where o.order_id = ? "
      + " and o.sec_id = s.sec_id  "
      + " and s.sec_id = scst.sec_id "
      + " and ( scst.udf_char10 is null or scst.udf_char10 = 'I' )"
      + " and s.issuer_cd = i.issuer_cd"
      + " and s.sec_id = us.sec_id"
      + " and us.underly_sec_id = usscst.sec_id"
      + " and us.underly_sec_id = uss.sec_id"
      + " and s.sec_id = term.sec_id (+)"
      + " and (scst.udf_char10 IS NULL OR scst.udf_char10 = 'I')"
//      + " and o.status = 'ACCT' "
//      + " and EXISTS ( "
//      + " SELECT  1 FROM crd_owner.au_order audit_o "
//      + " WHERE  o.order_id = audit_o.order_id "
//      + " and audit_o.au_change_ind = 'U' "
//      + "--            AND   audit_o.au_bc_status = 'W' "
//      + "--            AND   audit_o.au_change_date > SYSDATE - 1 "
//      + " and ( audit_o.status <> 'ACCT' and o.status = 'ACCT' ) "
//      + " ) "
      ;

  private final JdbcTemplate jdbcTemplate;

  public DBBroadcastSecurityRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public DBSecurity getSecurityForOrderId(String orderId) {
    final DBSecurity security = new DBSecurity();
    jdbcTemplate.query(this.SECURITY_FOR_ORDER_ID,
        preparedStatementSetter -> preparedStatementSetter.setString(1, orderId),
        resultSet -> {
          security.setSecId(resultSet.getString(1));
          security.setSecurityName(resultSet.getString(2));
          security.setSecurityTypeCode(resultSet.getString(3));
          security.setAccrualTypeCode(resultSet.getString(4));
          security.setAssetCurrencyCode(resultSet.getString(5));
          security.setContractSize(resultSet.getDouble(6));
          security.setCouponDom(resultSet.getDouble(7));
          security.setCouponEOMFlag(resultSet.getString(8));
          security.setCouponRate(resultSet.getDouble(9));
          security.setCouponWom(resultSet.getDouble(10));
          security.setCusip(resultSet.getString(11));
          security.setDayBasis(resultSet.getDouble(12));
          security.setExternalSecId(resultSet.getString(13));
          security.setFirstPaymentDate(resultSet.getDate(14));
          security.setFirstExerciseDate(resultSet.getDate(15));
          security.setExpireDate(resultSet.getDate(16));
          security.setExpireSettleDate(resultSet.getDate(17));
          security.setIssueDate(resultSet.getDate(18));
          security.setCountryOfRisk(resultSet.getString(19));
          security.setIssueCountryCode(resultSet.getString(20));
          security.setIssuerCode(resultSet.getString(21));
          security.setExpireTimeZoneRegionCode(resultSet.getString(22));
          security.setLastModifyBy(resultSet.getString(23));
          security.setLastPaymentDate(resultSet.getDate(24));
          security.setListExchangeCode(resultSet.getString(25));
          security.setLocationCurrencyCode(resultSet.getString(26));
          security.setMaturityDate(resultSet.getDate(27));
          security.setMarketPx(resultSet.getDouble(28));
          security.setMarketPxDateTime(resultSet.getDate(29));
          security.setParentSecId(resultSet.getString(30));
          security.setPaymentFrequency(resultSet.getString(31));
          security.setRateBasisSecId(resultSet.getString(32));
          security.setResetDow(resultSet.getDouble(33));
          security.setResetMargin(resultSet.getDouble(34));
          security.setResetLagDays(resultSet.getDouble(35));
          security.setSedol(resultSet.getString(36));
          security.setSpread(resultSet.getDouble(37));
          security.setStrikePx(resultSet.getDouble(38));
          security.setSwapLegIndicator(resultSet.getString(39));
          security.setSwapSecIndicator(resultSet.getString(40));
          security.setTicker(resultSet.getString(41));
          security.setVrdnFrequencyCode(resultSet.getString(42));
          security.setIncorporatedCountryCode(resultSet.getString(43));
          security.setClip(resultSet.getString(44));
          security.setOptionExpireType(resultSet.getString(45));
          security.setFixingDate(resultSet.getDate(46));
          security.setOtcClearingEligibilityIndicator(resultSet.getString(47));
          security.setMaturityAdjustment(resultSet.getString(48));
          security.setIssueDateAdjustment(resultSet.getString(49));
          security.setPayDateHolExchangeCode(resultSet.getString(50));
          security.setExerciseSettleLag(resultSet.getString(51));
          security.setAttachPt(resultSet.getDouble(52));
          security.setDetachPt(resultSet.getDouble(53));
          security.setUdfChar23(resultSet.getString(54));
          security.setPrincipalExchangeCode(resultSet.getString(55));
          security.setSwapSubType(resultSet.getString(56));
          security.setSecurityIssueDate(resultSet.getDate(57));
          security.setFixingRate(resultSet.getDouble(58));
          security.setIssuerName(resultSet.getString(59));
          security.setParentIssuerCode(resultSet.getString(60));
          security.setUnderlySecId(resultSet.getString(61));

          security.setUnderlyingSecId(resultSet.getString(62));
          security.setSwapSecId(resultSet.getString(62));

          security.setUdfChar1(resultSet.getString(63));
          security.setCalcAgent(resultSet.getString(64));
          security.setBusinessDayConversion(resultSet.getString(65));
        });
    return security;
  }

  @Override
  public DBSecurity getSecurityForSecId(String secId) {
    final DBSecurity security = new DBSecurity();
    jdbcTemplate.query(this.SECURITY_FOR_SEC_ID_SQL,
        preparedStatementSetter -> preparedStatementSetter.setString(1, secId),
        resultSet -> {
          security.setSecId(resultSet.getString(1));
          security.setSecurityName(resultSet.getString(2));
          security.setSecurityTypeCode(resultSet.getString(3));
          security.setAccrualTypeCode(resultSet.getString(4));
          security.setAssetCurrencyCode(resultSet.getString(5));
          security.setContractSize(resultSet.getDouble(6));
          security.setCouponDom(resultSet.getDouble(7));
          security.setCouponEOMFlag(resultSet.getString(8));
          security.setCouponRate(resultSet.getDouble(9));
          security.setCouponWom(resultSet.getDouble(10));
          security.setCusip(resultSet.getString(11));
          security.setDayBasis(resultSet.getDouble(12));
          security.setExternalSecId(resultSet.getString(13));
          security.setFirstPaymentDate(resultSet.getDate(14));
          security.setFirstExerciseDate(resultSet.getDate(15));
          security.setExpireDate(resultSet.getDate(16));
          security.setExpireSettleDate(resultSet.getDate(17));
          security.setIssueDate(resultSet.getDate(18));
          security.setCountryOfRisk(resultSet.getString(19));
          security.setIssueCountryCode(resultSet.getString(20));
          security.setIssuerCode(resultSet.getString(21));
          security.setExpireTimeZoneRegionCode(resultSet.getString(22));
          security.setLastModifyBy(resultSet.getString(23));
          security.setLastPaymentDate(resultSet.getDate(24));
          security.setListExchangeCode(resultSet.getString(25));
          security.setLocationCurrencyCode(resultSet.getString(26));
          security.setMaturityDate(resultSet.getDate(27));
          security.setMarketPx(resultSet.getDouble(28));
          security.setMarketPxDateTime(resultSet.getDate(29));
          security.setParentSecId(resultSet.getString(30));
          security.setPaymentFrequency(resultSet.getString(31));
          security.setRateBasisSecId(resultSet.getString(32));
          security.setResetDow(resultSet.getDouble(33));
          security.setResetMargin(resultSet.getDouble(34));
          security.setResetLagDays(resultSet.getDouble(35));
          security.setSedol(resultSet.getString(36));
          security.setSpread(resultSet.getDouble(37));
          security.setStrikePx(resultSet.getDouble(38));
          security.setSwapLegIndicator(resultSet.getString(39));
          security.setSwapSecIndicator(resultSet.getString(40));
          security.setTicker(resultSet.getString(41));
          security.setVrdnFrequencyCode(resultSet.getString(42));
          security.setIncorporatedCountryCode(resultSet.getString(43));
          security.setOptionExpireType(resultSet.getString(44));
          security.setFixingDate(resultSet.getDate(45));
          security.setOtcClearingEligibilityIndicator(resultSet.getString(46));
          security.setMaturityAdjustment(resultSet.getString(47));
          security.setIssueDateAdjustment(resultSet.getString(48));
          security.setPayDateHolExchangeCode(resultSet.getString(49));
          security.setExerciseSettleLag(resultSet.getString(50));
          security.setAttachPt(resultSet.getDouble(51));
          security.setDetachPt(resultSet.getDouble(52));
          security.setUdfChar23(resultSet.getString(53));
          security.setPrincipalExchangeCode(resultSet.getString(54));
          security.setSwapSubType(resultSet.getString(55));
          security.setSecurityIssueDate(resultSet.getDate(56));
          security.setFixingRate(resultSet.getDouble(57));
          security.setIssuerName(resultSet.getString(58));
          security.setParentIssuerCode(resultSet.getString(59));
          security.setCalcAgent(resultSet.getString(60));
          security.setBusinessDayConversion(resultSet.getString(61));
        });
    return security;
  }

  @Override
  public List<String> getChildSecurities(String secId) {
    final List<String> securityIds = new ArrayList<>();
    jdbcTemplate.query(this.CHILD_SECURITY_SQL,
        preparedStatementSetter -> preparedStatementSetter.setString(1, secId),
        resultSet -> {
          securityIds.add(resultSet.getString(1));
        });
    return securityIds;
  }
}