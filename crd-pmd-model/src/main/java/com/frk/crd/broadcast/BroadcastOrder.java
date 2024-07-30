package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastOrder implements BroadcastOrderAware {
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName;

  @JsonProperty("ORDER_ID")
  @JsonAlias("orderId")
  private String orderId;

  @JsonProperty("SEC_ID")
  @JsonAlias("secId")
  private String secId;

  @JsonProperty("INSTRUCTION")
  @JsonAlias("instruction")
  private String instruction;

  @JsonProperty("BROKER_REASON")
  @JsonAlias("brokerReason")
  private String brokerReason;

  @JsonProperty("NET_TRADE_IND")
  @JsonAlias("netTradeIndicator")
  private String netTradeIndicator;

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

  @JsonProperty("DELIVERY_TYPE")
  @JsonAlias("deliverType")
  private String deliverType;

  @JsonProperty("EXEC_BROKER")
  @JsonAlias("executionBroker")
  private String executionBroker;

  @JsonProperty("TRADE_DATE")
  @JsonAlias("tradeDate")
  private Date tradeDate;

  @JsonProperty("ORDER_ACCT_CD")
  @JsonAlias("orderAccountCode")
  private String orderAccountCode;

  @JsonProperty("TARGET_QTY")
  @JsonAlias("targetQuantity")
  private double targetQuantity;

  @JsonProperty("TARGET_AMT")
  @JsonAlias("targetAmount")
  private double targetAmount;

  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  @JsonAlias("principalLocalCurrencySecId")
  private String principalLocalCurrencySecId;

  @JsonProperty("CNTRCT_SIZE")
  @JsonAlias("contractSize")
  private double contractSize;

  @JsonProperty("PRIN_LOCAL_CRRNCY")
  @JsonAlias("principalLocalCurrency")
  private String principalLocalCurrency;

  @JsonProperty("REFERENCE_PRICE")
  @JsonAlias("referencePx")
  private double referencePx;

  @JsonProperty("SETTLE_DATE")
  @JsonAlias("settleDate")
  private Date settleDate;

  @JsonProperty("PRIN_SETTLE_FX_RATE")
  @JsonAlias("principleSettleFxRate")
  private double principleSettleFxRate;

  @JsonProperty("PRIN_SETTLE_CRRNCY")
  @JsonAlias("principleSettleCurrency")
  private String principleSettleCurrency;

  @JsonProperty("BKR_NAME")
  @JsonAlias("brokerName")
  private String brokerName;

  @JsonProperty("COUNTERPARTY")
  @JsonAlias("counterparty")
  private String counterparty;
}