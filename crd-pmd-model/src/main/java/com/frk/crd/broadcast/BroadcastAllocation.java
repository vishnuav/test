package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frk.crd.core.XMLParsingEligible;
import com.frk.crd.model.IAllocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BroadcastAllocation implements IAllocation, XMLParsingEligible {
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName = "TS_ORDER_ALLOC";
  @JsonProperty("ORIG_ORDER_ID")
  private String orignalOrderName;
  @JsonProperty("ORDER_ID")
  private String orderId;
  @JsonProperty("INSTRUCTION")
  private String instructions;
  @JsonProperty("BROKER_REASON")
  private String brokerReason;
  @JsonProperty("NET_TRADE_IND")
  private String netTradeIndicator;
  @JsonProperty("ORDER_DURATION")
  private String orderDuration;
  @JsonProperty("EXCH_CD")
  private String exchangeCode;
  @JsonProperty("TRADER")
  private String trader;
  @JsonProperty("MANAGER")
  private String manager;
  @JsonProperty("CREATE_USER")
  private String createUser;
  @JsonProperty("LAST_UPD_USER")
  private String lastUpdateUser;
  @JsonProperty("COMMENTS")
  private String comments;
  @JsonProperty("TRANS_TYPE")
  private String transactionType;
  @JsonProperty("INV_CLASS_CD")
  private String invoiceClassCode;
  @JsonProperty("STATUS")
  private String status;
  @JsonProperty("INCLUDE_IN_CASH")
  private String includeInCash;
  @JsonProperty("DELIVERY_TYPE")
  private String deliveryType;
  @JsonProperty("EXEC_BROKER")
  private String executionBrokerCode;
  @JsonProperty("TRADE_DATE")
  private Date tradeDate;
  @JsonProperty("TO_TRADER_DATE")
  private Date toTraderDate;
  @JsonProperty("IPO")
  private String ipo;
  @JsonProperty("ORDER_ACCT_CD")
  private String orderAccountCode;
  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  private String principalLocalCurrencySecId;
  @JsonProperty("TAX_LOT_SELL_CNVTN")
  private String taxLotSellConvention;
  @JsonProperty("TARGET_QTY")
  private Double targetQuantity;
  @JsonProperty("NET_PRIN_AMT")
  private Double netPrincipalAmount;
  @JsonProperty("TARGET_NOTNL_BASE_AMT")
  private Double targetNotionalBaseAmount;
  @JsonProperty("TARGET_NOTNL_AMT")
  private Double targetNotionalAmount;
  @JsonProperty("EXEC_PRICE")
  private Double executionPx;
  @JsonProperty("EXEC_QTY")
  private Double executionQuantity;
  @JsonProperty("EXEC_AMT")
  private Double executionAmount;
  @JsonProperty("TARGET_AMT")
  private Double targetAmount;
  @JsonProperty("COMMISION_IND")
  private String commissionIndicator;
  @JsonProperty("CUR_BASE_MKT_VAL")
  private Double currentBaseMarketValue;
  @JsonProperty("TRANS_SUB_TYPE")
  private String transactionSubType = "OPEN";
  @JsonProperty("ORIG_ORDER_ID")
  private String originalOrderId;
  @JsonProperty("NET_MONEY")
  private Double netMoney;
  @JsonProperty("ACCT_CD")
  private String accountCode;
  @JsonProperty("COMMISON_AMOUNT")
  private Double commissionAmount;
}