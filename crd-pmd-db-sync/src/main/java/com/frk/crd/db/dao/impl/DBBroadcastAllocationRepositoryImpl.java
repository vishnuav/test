package com.frk.crd.db.dao.impl;

import com.frk.crd.db.dao.DBBroadcastAllocationRepository;
import com.frk.crd.db.model.DBAllocation;
import com.frk.crd.model.IAllocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DBBroadcastAllocationRepositoryImpl implements DBBroadcastAllocationRepository {
  final String ALLOCATION_SQL = " select  "
    + " oa.ORIG_ORDER_ID, "
    + " oa.ORDER_ID, "
    + " oa.TARGET_QTY, "
    + " oa.TARGET_NOTNL_AMT, "
    + " oa.EXEC_PRICE, "
    + " oa.EXEC_QTY, "
    + " oa.EXEC_AMT, "
    + " oa.TARGET_AMT, "
    + " oa.NET_MONEY, "
    + " oa.COMMISION_IND, "
    + " oa.ACCT_CD, "
    + " oa.COMMISION_AMT "
    + " from crd_owner.ts_order o "
    + " join crd_owner.ts_order_alloc oa on o.order_id = oa.order_id "
    + " left join ( "
    + " select "
    + " b.tm_orig_id, b.ConfirmStatus, b.AllocAccount, b.TRANSACT_TIME "
    + " from( "
    + " select a.tm_orig_id, a.ConfirmStatus, coalesce(tranls.CS_FLD_CD, a.AllocAccount) AllocAccount, a.TRANSACT_TIME, a.recv_time "
    + " ,ROW_NUMBER() OVER (PARTITION BY a.tm_orig_id, a.ConfirmStatus, a.AllocAccount, a.TRANSACT_TIME ORDER BY a.recv_time DESC) RANKED_REC "
    + " from( "
    + " select  "
    + " tm_orig_id "
    + " ,case when INSTR(raw_msg, '665=') > 0 then cast(SUBSTR(SUBSTR(raw_msg, INSTR(raw_msg, '665=')), 5, INSTR(SUBSTR(raw_msg, INSTR(raw_msg, '665=')), '|')-5) as char(1)) else NULL end ConfirmStatus "
    + " ,case when INSTR(raw_msg, '79=') > 0 then cast(SUBSTR(SUBSTR(raw_msg, INSTR(raw_msg, '79=')), 4, INSTR(SUBSTR(raw_msg, INSTR(raw_msg, '79=')), '|')-4) as varchar2(20)) else NULL end AllocAccount "
    + " ,FIX_MSG_TYPE "
    + " ,TRANSACT_TIME "
    + " ,recv_time "
    + " from crd_owner.FIX_INCOMING "
    + " where "
    + " FIX_MSG_TYPE = 'conf') a "
    + " left join (select TRADE_FLD_CD, CS_FLD_CD from crd_owner.csm_translation where TRADE_SYST_CD = 'FIXMASTERSLEEVE' and DATA_TYP = 'ACCT_CD') tranls ON tranls.TRADE_FLD_CD = a.AllocAccount "
    + " where a.ConfirmStatus = 4) b "
    + " where b.RANKED_REC = 1) FXIN on o.order_id = FXIN.tm_orig_id and OA.ACCT_CD = FXIN.AllocAccount "
    + " where (o.status = 'ACCT' or o.status = 'CNCLACCT') "
    + " and o.order_id=? "
//      + " and crd_owner.FRK_GETORDERFLOWTYPE(o.order_id) = 'SMEF' "
    ;

  private final JdbcTemplate jdbcTemplate;

  public DBBroadcastAllocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<IAllocation> getAllocations(String orderId) {
    final List<IAllocation> allocations = new ArrayList<>();
    jdbcTemplate.query(this.ALLOCATION_SQL, preparedStatementSetter -> {
        preparedStatementSetter.setString(1, orderId);
      },
      resultSet -> {
        DBAllocation allocation = new DBAllocation();
        allocation.setOriginalOrderId(resultSet.getString(1));
        allocation.setOrderId(resultSet.getString(2));
        allocation.setTargetQuantity(resultSet.getDouble(3));
        allocation.setTargetNotionalAmount(resultSet.getDouble(4));
        allocation.setExecutionPx(resultSet.getDouble(5));
        allocation.setExecutionQuantity(resultSet.getDouble(6));
        allocation.setExecutionAmount(resultSet.getDouble(7));
        allocation.setTargetAmount(resultSet.getDouble(8));
        allocation.setNetMoney(resultSet.getDouble(9));
        allocation.setCommissionIndicator(resultSet.getString(10));
        allocation.setAccountCode(resultSet.getString(11));
        allocation.setCommissionAmount(resultSet.getDouble(12));
        allocations.add(allocation);
      });
    return allocations;
  }
}