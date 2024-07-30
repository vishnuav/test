package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonAlias;
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
  @JsonProperty("ORDER_ID")
  @JsonAlias("orderId")
  private String orderId;
  @JsonProperty("ORIG_ORDER_ID")
  @JsonAlias("originalOrderId")
  private String originalOrderId;
  @JsonProperty("INSTRUCTION")
  @JsonAlias("instructions")
  private String instructions;
  @JsonProperty("BROKER_REASON")
  @JsonAlias("brokerReason")
  private String brokerReason;
  @JsonProperty("NET_TRADE_IND")
  @JsonAlias("netTradeIndicator")
  private String netTradeIndicator;
  @JsonProperty("ORDER_DURATION")
  @JsonAlias("orderDuration")
  private String orderDuration;
  @JsonProperty("EXCH_CD")
  @JsonAlias("exchangeCode")
  private String exchangeCode;
  @JsonProperty("TRADER")
  @JsonAlias("trader")
  private String trader;
  @JsonProperty("MANAGER")
  @JsonAlias("manager")
  private String manager;
  @JsonProperty("CREATE_USER")
  @JsonAlias("createUser")
  private String createUser;
  @JsonProperty("LAST_UPD_USER")
  @JsonAlias("lastUpdateUser")
  private String lastUpdateUser;
  @JsonProperty("COMMENTS")
  @JsonAlias("comments")
  private String comments;
  @JsonProperty("TRANS_TYPE")
  @JsonAlias("transactionType")
  private String transactionType;
  @JsonProperty("INV_CLASS_CD")
  @JsonAlias("invoiceClassCode")
  private String invoiceClassCode;
  @JsonProperty("STATUS")
  @JsonAlias("status")
  private String status;
  @JsonProperty("INCLUDE_IN_CASH")
  @JsonAlias("includeInCash")
  private String includeInCash;
  @JsonProperty("DELIVERY_TYPE")
  @JsonAlias("deliveryType")
  private String deliveryType;
  @JsonProperty("EXEC_BROKER")
  @JsonAlias("executionBrokerCode")
  private String executionBrokerCode;
  @JsonProperty("TRADE_DATE")
  @JsonAlias("tradeDate")
  private Date tradeDate;
  @JsonProperty("TO_TRADER_DATE")
  @JsonAlias("toTraderDate")
  private Date toTraderDate;
  @JsonProperty("IPO")
  @JsonAlias("ipo")
  private String ipo;
  @JsonProperty("ORDER_ACCT_CD")
  @JsonAlias("orderAccountCode")
  private String orderAccountCode;
  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  @JsonAlias("principalLocalCurrencySecId")
  private String principalLocalCurrencySecId;
  @JsonProperty("TAX_LOT_SELL_CNVTN")
  @JsonAlias("taxLotSellConvention")
  private String taxLotSellConvention;
  @JsonProperty("TARGET_QTY")
  @JsonAlias("targetQuantity")
  private Double targetQuantity;
  @JsonProperty("NET_PRIN_AMT")
  @JsonAlias("netPrincipalAmount")
  private Double netPrincipalAmount;
  @JsonProperty("TARGET_NOTNL_BASE_AMT")
  @JsonAlias("targetNotionalBaseAmount")
  private Double targetNotionalBaseAmount;
  @JsonProperty("TARGET_NOTNL_AMT")
  @JsonAlias("targetNotionalAmount")
  private Double targetNotionalAmount;
  @JsonProperty("EXEC_PRICE")
  @JsonAlias("executionPx")
  private Double executionPx;
  @JsonProperty("EXEC_QTY")
  @JsonAlias("executionQuantity")
  private Double executionQuantity;
  @JsonProperty("EXEC_AMT")
  @JsonAlias("executionAmount")
  private Double executionAmount;
  @JsonProperty("TARGET_AMT")
  @JsonAlias("targetAmount")
  private Double targetAmount;
  @JsonProperty("COMMISION_IND")
  @JsonAlias("commissionIndicator")
  private String commissionIndicator;
  @JsonProperty("CUR_BASE_MKT_VAL")
  @JsonAlias("currentBaseMarketValue")
  private Double currentBaseMarketValue;
  @JsonProperty("TRANS_SUB_TYPE")
  @JsonAlias("transactionSubType")
  private String transactionSubType;
  @JsonProperty("NET_MONEY")
  @JsonAlias("netMoney")
  private Double netMoney;
  @JsonProperty("ACCT_CD")
  @JsonAlias("accountCode")
  private String accountCode;
  @JsonProperty("COMMISON_AMOUNT")
  @JsonAlias("commissionAmount")
  private Double commissionAmount;
}