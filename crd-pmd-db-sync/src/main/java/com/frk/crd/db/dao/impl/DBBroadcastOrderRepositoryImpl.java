package com.frk.crd.db.dao.impl;

import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.model.DBOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBBroadcastOrderRepositoryImpl implements DBBroadcastOrderRepository {
  final String ORDER_SQL = " select "
      + " o.ORDER_ID, "
      + " o.INSTRUCTION, "
      + " o.BROKER_REASON, "
      + " o.NET_TRADE_IND, "
      + " o.ORDER_DURATION, "
      + " o.EXCH_CD, "
      + " o.TRADER, "
      + " o.MANAGER, "
      + " o.CREATE_USER, "
      + " o.LAST_UPD_USER, "
      + " o.COMMENTS, "
      + " o.TRANS_TYPE, "
      + " o.INV_CLASS_CD, "
      + " o.STATUS, "
      + " o.INCLUDE_IN_CASH, "
      + " o.DELIVERY_TYPE, "
      + " o.COUNTERPARTY, "
      + " o.EXEC_BROKER, "
      + " o.TRADE_DATE, "
      + " o.TO_TRADER_DATE, "
      + " o.IPO, "
      + " o.ORDER_ACCT_CD, "
      + " o.TARGET_QTY, "
      + " o.TARGET_AMT, "
      + " o.TARGET_DISC_RATE, "
      + " o.EXEC_DISC_RATE, "
      + " o.PRIN_LOCAL_CRRNCY_SEC_ID, "
      + " oa.UDF_FLOAT16, "
      + " oa.TAX_LOT_SELL_CNVTN, "
      + " oa.NET_PRIN_AMT, "
      + " oa.TARGET_NOTNL_BASE_AMT, "
      + " oa.TARGET_NOTNL_AMT, "
      + " oa.EXEC_PRICE, "
      + " oa.EXEC_QTY, "
      + " oa.EXEC_AMT, "
      + " oa.NET_MONEY, "
      + " oa.COMMISION_IND, "
      + " oa.COMMISION_AMT, "
      + " oa.CUR_BASE_MKT_VAL, "
      + " oa.ACCT_CD, "
      + " oss.TRANS_SUB_TYPE, "
      + " o.SEC_ID "
      + "  from crd_owner.ts_order o "
      + "  join crd_owner.ts_order_sec_spec oss on o.order_id = oss.order_id "
      + "  join crd_owner.ts_order_alloc oa on o.order_id = oa.order_id "
      + "  join crd_owner.cs_fund f on OA.ACCT_CD = f.acct_cd "
      + "  left join ( "
      + " select "
      + " b.tm_orig_id, b.ConfirmStatus, b.AllocAccount, b.TRANSACT_TIME "
      + " from( "
      + " select a.tm_orig_id, a.ConfirmStatus, coalesce(tranls.CS_FLD_CD, a.AllocAccount) AllocAccount, a.TRANSACT_TIME, a.recv_time "
      + " ,ROW_NUMBER() OVER (PARTITION BY a.tm_orig_id, a.ConfirmStatus, a.AllocAccount, a.TRANSACT_TIME ORDER BY a.recv_time DESC) RANKED_REC "
      + " from( "
      + " select "
      + " tm_orig_id "
      + "  ,case when INSTR(raw_msg, '665=') > 0 then cast(SUBSTR(SUBSTR(raw_msg, INSTR(raw_msg, '665=')), 5, INSTR(SUBSTR(raw_msg, INSTR(raw_msg, '665=')), '|')-5) as char(1)) else NULL end ConfirmStatus "
      + "  ,case when INSTR(raw_msg, '79=') > 0 then cast(SUBSTR(SUBSTR(raw_msg, INSTR(raw_msg, '79=')), 4, INSTR(SUBSTR(raw_msg, INSTR(raw_msg, '79=')), '|')-4) as varchar2(20)) else NULL end AllocAccount "
      + "  ,FIX_MSG_TYPE "
      + "  ,TRANSACT_TIME "
      + "  ,recv_time "
      + " from crd_owner.FIX_INCOMING "
      + " where "
      + "  FIX_MSG_TYPE = 'conf') a "
      + " left join (select TRADE_FLD_CD, CS_FLD_CD from crd_owner.csm_translation where TRADE_SYST_CD = 'FIXMASTERSLEEVE' and DATA_TYP = 'ACCT_CD') tranls ON tranls.TRADE_FLD_CD = a.AllocAccount "
      + " where a.ConfirmStatus = 4) b "
      + "  where b.RANKED_REC = 1) FXIN on o.order_id = FXIN.tm_orig_id and OA.ACCT_CD = FXIN.AllocAccount "
      + "  where  (  o.status = 'ACCT' or o.status = 'CNCLACCT' ) "
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
          order.setOrderId(resultSet.getString(1));
          order.setInstruction(resultSet.getString(2));
          order.setBrokerReason(resultSet.getString(3));
          order.setNetTradeIndicator(resultSet.getString(4));
          order.setOrderDuration(resultSet.getString(5));
          order.setExchangeCode(resultSet.getString(6));
          order.setTrader(resultSet.getString(7));
          order.setManager(resultSet.getString(8));
          order.setCreateUser(resultSet.getString(9));
          order.setLastUpdateUser(resultSet.getString(10));
          order.setComments(resultSet.getString(11));
          order.setTransactionType(resultSet.getString(12));
          order.setInvoiceClassCode(resultSet.getString(13));
          order.setStatus(resultSet.getString(14));
          order.setIncludeInCash(resultSet.getString(15));
          order.setDeliveryType(resultSet.getString(16));
          order.setCounterparty(resultSet.getString(17));
          order.setExecutingBroker(resultSet.getString(18));
          order.setTradeDate(resultSet.getDate(19));
          order.setToTradeDate(resultSet.getDate(20));
          order.setIpo(resultSet.getString(21));
          order.setOrderAccountCode(resultSet.getString(22));
          order.setTargetQuantity(resultSet.getDouble(23));
          order.setTargetAmount(resultSet.getDouble(24));
          order.setTargetDiscountRate(resultSet.getString(25));
          order.setExecutionDiscountRate(resultSet.getString(26));
          order.setPrincipleLocalCurrencySecId(resultSet.getString(27));
          order.setUdfFloat16(resultSet.getString(28));
          order.setTaxLotSellConvention(resultSet.getString(29));
          order.setNetPrincipleAmount(resultSet.getDouble(30));
          order.setTargetNotionalBaseAmount(resultSet.getDouble(31));
          order.setTargetNotionalAmount(resultSet.getDouble(32));
          order.setExecutionPx(resultSet.getDouble(33));
          order.setExecutionQuantity(resultSet.getDouble(34));
          order.setExecutionAmount(resultSet.getDouble(35));
          order.setNetMoney(resultSet.getDouble(36));
          order.setCommissionIndicator(resultSet.getString(37));
          order.setCommissionAmount(resultSet.getDouble(38));
          order.setCurrentBaseMarketValue(resultSet.getDouble(39));
          order.setAccountCode(resultSet.getString(40));
          order.setTransactionSubType(resultSet.getString(41));
          order.setSecId(resultSet.getString(42));
        });
    return order;
  }
}