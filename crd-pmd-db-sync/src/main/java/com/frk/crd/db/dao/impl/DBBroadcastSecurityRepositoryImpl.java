package com.frk.crd.db.dao.impl;

import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.model.DBBroadcastSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBBroadcastSecurityRepositoryImpl implements DBBroadcastSecurityRepository {
  final String SECURITY_SQL = "select  "
      + " s.SEC_ID SEC_ID, "
      + " s.ASSET_CRRNCY_CD ASSET_CRRNCY_CD,"
      + " s.SEC_NAME SEC_NAME,"
      + " s.EXT_SEC_ID EXT_SEC_ID,"
      + " s.FRST_EXERCISE_DATE FRST_EXERCISE_DATE,"
      + " s.EXPIRE_DATE EXPIRE_DATE,"
      + " s.EXPIRE_TIME_ZONE_RGN_CD EXPIRE_TIME_ZONE_RGN_CD,"
      + " s.CNTRY_OF_RISK CNTRY_OF_RISK,"
      + " s.INCORP_CNTRY_CD INCORP_CNTRY_CD,"
      + " s.ISSUER_CD ISSUER_CD,"
      + " s.STRIKE_PRICE STRIKE_PRICE,"
      + " s.EXPIRE_SETTLE_DATE EXPIRE_SETTLE_DATE,"
      + " s.SEC_TYP_CD SEC_TYP_CD,"
      + " s.CUSIP CUSIP,"
      + " s.ISSUE_CNTRY_CD ISSUE_CNTRY_CD,"
      + " s.ISSUE_DATE ISSUE_DATE,"
      + " s.LOC_CRRNCY_CD LOC_CRRNCY_CD,"
      + " s.MKT_PRICE MKT_PRICE,"
      + " s.PARENT_SEC_ID PARENT_SEC_ID, "
      + " i.PARENT_ISSUER_CD PARENT_ISSUER_CD,"
      + " i.ISSUER_NAME ISSUER_NAME,"
      + " scst.OPTN_EXPIRE_TYPE OPTN_EXPIRE_TYPE,"
      + " scst.FIXING_DATE FIXING_DATE,"
      + " scst.OTC_CLEAR_ELIG_IND OTC_CLEAR_ELIG_IND,"
      + " scst.SEC_ISSUE_DATE SEC_ISSUE_DATE,"
      + " scst.MAT_DATE_ADJ MAT_DATE_ADJ,"
      + " scst.ISSUE_DATE_ADJ ISSUE_DATE_ADJ,"
      + " scst.PAY_DT_HOL_EXCH_CD PAY_DT_HOL_EXCH_CD, "
      + " term.EXERCISE_SETTLE_LAG EXERCISE_SETTLE_LAG,"
      + " term.CALC_AGENT CALC_AGENT,"
      + " term.BUS_DAY_CONV BUS_DAY_CONV,"
      + " commstock.SEC_ID UNDERLY_SEC_ID,"
      + " commstock.SEC_ID SWAP_SEC_ID"
      + " from crd_owner.csm_security s, crd_owner.csm_security_cust scst, crd_owner.csm_issuer i,"
      + " crd_owner.csm_underlying_security us, crd_owner.csm_security_cust usscst, crd_owner.csm_security commstock,"
      + " crd_owner.csm_security_term term"
      + " where s.sec_id = ? "
      + " and s.sec_id = scst.sec_id "
      + " and ( scst.udf_char10 is null or scst.udf_char10 = 'I' )"
      + " and s.issuer_cd = i.issuer_cd"
      + " and s.sec_id = us.sec_id"
      + " and us.underly_sec_id = usscst.sec_id"
      + " and us.underly_sec_id = commstock.sec_id"
      + " and s.sec_id = term.sec_id (+)";

  final String SECURITY_SWAP_LEG_SQL = "select "
      + "'CSM_SECURITY' CRD_TABLE_NAME, "
      + "s.sec_id, "
      + "s.EXT_SEC_ID, "
      + "s.CNTRY_OF_RISK, "
      + "s.INCORP_CNTRY_CD, "
      + "s.ISSUER_CD, "
      + "s.STRIKE_PRICE, "
      + "s.EXPIRE_DATE, "
      + "s.EXPIRE_SETTLE_DATE, "
      + "s.SEC_TYP_CD, "
      + "s.CUSIP, "
      + "s.ISSUE_CNTRY_CD, "
      + "s.ISSUE_DATE, "
      + "s.LOC_CRRNCY_CD, "
      + "s.ASSET_CRRNCY_CD, "
      + "s.MKT_PRICE, "
      + "s.PARENT_SEC_ID, "
      + "i.PARENT_ISSUER_CD, "
      + "i.ISSUER_NAME, "
      + "scst.OPTN_EXPIRE_TYPE, "
      + "scst.FIXING_DATE, "
      + "scst.OTC_CLEAR_ELIG_IND, "
      + "scst.SEC_ISSUE_DATE, "
      + "scst.MAT_DATE_ADJ, "
      + "scst.ISSUE_DATE_ADJ, "
      + "scst.SWAPTION_TERM, "
      + "scst.PAY_DT_HOL_EXCH_CD, "
      + "term.EXERCISE_SETTLE_LAG, "
      + "term.CALC_AGENT, "
      + "term.BUS_DAY_CONV "
      + "  from crd_owner.csm_security s, crd_owner.csm_security_cust scst, crd_owner.csm_security_term term, crd_owner.csm_issuer i "
      + " where s.sec_id = ? "
      + "   and s.sec_id = scst.sec_id "
      + "   and s.issuer_cd = i.issuer_cd "
      + "   and s.sec_id = term.sec_id (+) "
      ;

  private final JdbcTemplate jdbcTemplate;

  public DBBroadcastSecurityRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public DBBroadcastSecurity getSecurity(String secId) {
    final DBBroadcastSecurity security = new DBBroadcastSecurity();
    jdbcTemplate.query(this.SECURITY_SQL, preparedStatementSetter -> {
          preparedStatementSetter.setString(1, secId);
        },
        resultSet -> {
          security.setSecId(resultSet.getString(1));
          security.setAssetCurrencyCode(resultSet.getString(2));
          security.setSecurityName(resultSet.getString(3));
          security.setExternalSecId(resultSet.getString(4));
          security.setFirstExerciseDate(resultSet.getDate(5));
          security.setExpireDate(resultSet.getDate(6));
          security.setExpireTimeZoneRegionCode(resultSet.getString(7));
          security.setCountryOfRisk(resultSet.getString(8));
          security.setIncorporatedCountryCode(resultSet.getString(9));
          security.setIssuerCode(resultSet.getString(10));
          security.setStrikePx(resultSet.getDouble(11));
          security.setExpireSettleDate(resultSet.getDate(12));
          security.setSecurityTypeCode(resultSet.getString(13));
          security.setCusip(resultSet.getString(14));
          security.setIssueCountryCode(resultSet.getString(15));
          security.setIssueDate(resultSet.getDate(16));
          security.setLocationCurrencyCode(resultSet.getString(17));
          security.setMarketPx(resultSet.getDouble(18));
          security.setParentSecId(resultSet.getString(19));
          security.setParentIssuerCode(resultSet.getString(20));
          security.setIssuerName(resultSet.getString(21));
          security.setOptionExpireType(resultSet.getString(22));
          security.setFixingDate(resultSet.getDate(23));
          security.setOtcClearingEligibility(resultSet.getString(24));
          security.setSecurityIssueDate(resultSet.getDate(25));
          security.setMaturityAdjustment(resultSet.getString(26));
          security.setIssueDateAdjustment(resultSet.getString(27));
          security.setPayDateHolExchangeCode(resultSet.getString(28));
          security.setExerciseSettleLag(resultSet.getString(29));
          security.setCalcAgent(resultSet.getString(30));
          security.setBusinessDayConversion(resultSet.getString(31));
          security.setUnderlyingSecId(resultSet.getString(32));
          security.setSwapSecId(resultSet.getString(33));
        });
    return security;
  }
}