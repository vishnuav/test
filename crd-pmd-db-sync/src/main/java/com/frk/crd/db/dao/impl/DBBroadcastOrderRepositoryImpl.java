package com.frk.crd.db.dao.impl;

import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.model.DBOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBBroadcastOrderRepositoryImpl implements DBBroadcastOrderRepository {
  final String ORDER_SQL = " SELECT "
      + " s.ext_sec_id, "
      + " s.lifetime_cap, "
      + " s.lifetime_floor, "
      + " o.order_id, "
      + " o.auth_date, "
      + " o.auth_user, "
      + " o.broker_reason, "
      + " o.cntrct_size, "
      + " o.comments, "
      + " o.create_user, "
      + " o.delivery_type, "
      + " o.exch_cd, "
      + " o.exec_amt, "
      + " o.exec_broker, "
      + " o.exec_dirty_flag, "
      + " o.exec_dirty_price, "
      + " o.exec_disc_rate, "
      + " o.exec_qty, "
      + " o.exec_yield, "
      + " o.exec_yield_type, "
      + " o.factor, "
      + " o.fee_1_cd, "
      + " o.fee_1, "
      + " o.fee_2_cd, "
      + " o.fee_2, "
      + " o.fee_3_cd, "
      + " o.fee_3, "
      + " o.fee_4_cd, "
      + " o.fee_4, "
      + " o.fee_5_cd, "
      + " o.fee_5, "
      + " o.fee_6_cd, "
      + " o.fee_6, "
      + " o.from_crrncy, "
      + " o.instruction, "
      + " o.inv_class_cd, "
      + " o.ipo, "
      + " o.last_upd_date, "
      + " o.limit_price, "
      + " o.manager, "
      + " o.net_trade_ind, "
      + " o.place_date, "
      + " o.prin_local_crrncy, "
      + " o.reason_cd, "
      + " o.reference_price, "
      + " o.scenario_id, "
      + " o.sec_id, "
      + " o.settle_date, "
      + " o.special_inst, "
      + " o.status, "
      + " o.target_amt, "
      + " o.target_crrncy, "
      + " o.target_qty, "
      + " o.to_crrncy, "
      + " o.trade_date, "
      + " o.trader, "
      + " o.trans_type, "
      + " o.usr_class_cd_3, "
      + " o.usr_class_cd_4, "
      + " oss.udf_date2, "
      + " oss.crrncy_pair, "
      + " oss.deal_price, "
      + " oss.exec_spot_rate, "
      + " oss.idx_factor, "
      + " oss.ref_sec_id, "
      + " oss.swap_party_ind, "
      + " oss.trans_sub_type, "
      + " oss.udf_char3, "
      + " oss.udf_char4, "
      + " oss.udf_char5, "
      + " oss.udf_char6, "
      + " oss.udf_char10, "
      + " oss.udf_char22, "
      + " oss.udf_float5, "
      + " oss.upfront_pct, "
      + " oss.val_based, "
      + " oss.valuation_date, "
      + " oss.yield, "
      + " oss.series_orig_target_value, "
      + " o.clear_venue_cd, "
      + " scst.otc_clear_elig_ind, "
      + " oss.deal_spread, "
      + " oss.udf_char12, "
      + " o.trading_venue, "
      + " oss.udf_char13, "
      + " oss.udf_char17, "
      + " oss.udf_char19, "
      + " op.fix_udf_datetime2 execution_time "
      + " FROM "
      + " crd_owner.csm_security s, "
      + " crd_owner.csm_security_cust scst, "
      + " crd_owner.ts_order o, "
      + " crd_owner.ts_order_sec_spec oss, "
      + " crd_owner.ts_order_placement op, "
      + " crd_owner.ts_fill fil "
      + " WHERE o.order_id = oss.order_id "
      + " AND   o.order_id = fil.order_id "
      + " AND   o.sec_id = s.sec_id "
      + " AND   o.sec_id = scst.sec_id "
      + " AND   fil.placement_id = op.place_id "
//      + " AND   o.sent_acct_date > SYSDATE - 7 "
      + " AND   ( o.status = 'ACCT' OR    o.status = 'CNCLACCT' ) "
      + " AND   EXISTS ( "
      + "     SELECT "
      + "         1 "
      + "     FROM "
      + "         crd_owner.au_order audit_o "
      + "     WHERE "
      + "         o.order_id = audit_o.order_id "
      + "         AND   audit_o.au_change_ind = 'U' "
//      + " AND   audit_o.au_bc_status = 'W' "
//      + " AND   audit_o.au_change_date > SYSDATE - 1 "
      + "         AND   ( "
      + "             ( "
      + "                 audit_o.status <> 'ACCT' "
      + "                 AND   o.status = 'ACCT' "
      + "             ) "
      + "             OR    ( "
      + "                 audit_o.status <> 'CNCLACCT' "
      + "                 AND   o.status = 'CNCLACCT' "
      + "             ) "
      + "         ) "
      + " ) "
//      + " AND   frk_getorderflowtype(o.order_id) = 'SMEF' "
      + "  and o.order_id = ? ";

  private final JdbcTemplate jdbcTemplate;

  public DBBroadcastOrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public DBOrder getOrder(String orderId) {
    final DBOrder order = new DBOrder();
    jdbcTemplate.query(this.ORDER_SQL, preparedStatementSetter -> {
          preparedStatementSetter.setString(1, orderId);
        },
        resultSet -> {
          order.setExternalSecId(resultSet.getString(1));
          order.setLifetimeCap(resultSet.getString(2));
          order.setLifetimeFloor(resultSet.getString(3));
          order.setOrderId(resultSet.getString(4));
          order.setAuthDate(resultSet.getDate(5));
          order.setAuthUser(resultSet.getString(6));
          order.setBrokerReason(resultSet.getString(7));
          order.setContractSize(resultSet.getDouble(8));
          order.setComments(resultSet.getString(9));
          order.setCreateUser(resultSet.getString(10));
          order.setDeliveryType(resultSet.getString(11));
          order.setExchangeCode(resultSet.getString(12));
          order.setExecutionAmount(resultSet.getDouble(13));
          order.setExecutingBroker(resultSet.getString(14));
          order.setExecutionDirtyFlag(resultSet.getString(15));
          order.setExecutionDirtyPx(resultSet.getDouble(16));
          order.setExecutionDiscountRate(resultSet.getDouble(17));
          order.setExecutionQuantity(resultSet.getDouble(18));
          order.setExecutionYield(resultSet.getDouble(19));
          order.setExecutionYieldType(resultSet.getString(20));
          order.setFactor(resultSet.getDouble(21));
          order.setFee1Code(resultSet.getString(22));
          order.setFee1(resultSet.getDouble(23));
          order.setFee2Code(resultSet.getString(24));
          order.setFee2(resultSet.getDouble(25));
          order.setFee3Code(resultSet.getString(26));
          order.setFee3(resultSet.getDouble(27));
          order.setFee4Code(resultSet.getString(28));
          order.setFee4(resultSet.getDouble(29));
          order.setFee5Code(resultSet.getString(30));
          order.setFee5(resultSet.getDouble(31));
          order.setFee6Code(resultSet.getString(32));
          order.setFee6(resultSet.getDouble(33));
          order.setFromCurrency(resultSet.getString(34));
          order.setInstruction(resultSet.getString(35));
          order.setInvoiceClassCode(resultSet.getString(36));
          order.setIpo(resultSet.getString(37));
          order.setLastUpdateDate(resultSet.getDate(38));
          order.setLimitPx(resultSet.getDouble(39));
          order.setManager(resultSet.getString(40));
          order.setNetTradeIndicator(resultSet.getString(41));
          order.setPlaceDate(resultSet.getDate(42));
          order.setPrincipalLocalCurrency(resultSet.getString(43));
          order.setReasonCode(resultSet.getString(44));
          order.setReferencePx(resultSet.getDouble(45));
          order.setScenarioId(resultSet.getString(46));
          order.setSecId(resultSet.getString(47));
          order.setSettleDate(resultSet.getDate(48));
          order.setSpecialInstruction(resultSet.getString(49));
          order.setStatus(resultSet.getString(50));
          order.setTargetAmount(resultSet.getDouble(51));
          order.setTargetCurrency(resultSet.getString(52));
          order.setTargetQuantity(resultSet.getDouble(53));
          order.setToCurrency(resultSet.getString(54));
          order.setTradeDate(resultSet.getDate(55));
          order.setTrader(resultSet.getString(56));
          order.setTransactionType(resultSet.getString(57));
          order.setUserClassCode3(resultSet.getString(58));
          order.setUserClassCode4(resultSet.getString(59));
          order.setUdfDate2(resultSet.getDate(60));
          order.setCurrencyPair(resultSet.getString(61));
          order.setDealPx(resultSet.getDouble(62));
          order.setExecutionSpotRate(resultSet.getDouble(63));
          order.setIdxFactor(resultSet.getDouble(64));
          order.setReferenceSecId(resultSet.getString(65));
          order.setSwapPartyIndicator(resultSet.getString(66));
          order.setTransactionSubType(resultSet.getString(67));
          order.setUdFChar3(resultSet.getString(68));
          order.setUdFChar4(resultSet.getString(69));
          order.setUdFChar5(resultSet.getString(70));
          order.setUdFChar6(resultSet.getString(71));
          order.setUdFChar10(resultSet.getString(72));
          order.setUdFChar22(resultSet.getString(73));
          order.setUdfFloat5(resultSet.getDouble(74));
          order.setUpfrontPercent(resultSet.getDouble(75));
          order.setValueBased(resultSet.getString(76));
          order.setValuationDate(resultSet.getDate(77));
          order.setYield(resultSet.getDouble(78));
          order.setOriginalTargetValue(resultSet.getDouble(79));
          order.setClearingVenueCode(resultSet.getString(80));
          order.setOtcClearingEligibleIndicator(resultSet.getString(81));
          order.setDealSpread(resultSet.getDouble(82));
          order.setUdfChar12(resultSet.getString(83));
          order.setTradingVenue(resultSet.getString(84));
          order.setUdfChar13(resultSet.getString(85));
          order.setUdfChar17(resultSet.getString(86));
          order.setUdfChar19(resultSet.getString(87));
          order.setExecutionDateTime(resultSet.getDate(88));
        });
    return order;
  }
}