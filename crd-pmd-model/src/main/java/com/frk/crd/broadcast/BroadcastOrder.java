package com.frk.crd.broadcast;

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
  private String orderId;

  @JsonProperty("SEC_ID")
  private String secId;

  @JsonProperty("INSTRUCTION")
  private String instruction;

  @JsonProperty("BROKER_REASON")
  private String brokerReason;

  @JsonProperty("NET_TRADE_IND")
  private String netTradeIndicator;

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

  @JsonProperty("DELIVERY_TYPE")
  private String deliverType;

  @JsonProperty("EXEC_BROKER")
  private String executionBroker;

  @JsonProperty("TRADE_DATE")
  private Date tradeDate;

  @JsonProperty("ORDER_ACCT_CD")
  private String orderAccountCode;

  @JsonProperty("TARGET_QTY")
  private double targetQuantity;

  @JsonProperty("TARGET_AMT")
  private double targetAmount;

  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  private String principalLocalCurrencySecId;

  @JsonProperty("CNTRCT_SIZE")
  private double contractSize;

  @JsonProperty("PRIN_LOCAL_CRRNCY")
  private String principalLocalCurrency;

  @JsonProperty("REFERENCE_PRICE")
  private double referencePx;

  @JsonProperty("SETTLE_DATE")
  private Date settleDate;

  @JsonProperty("PRIN_SETTLE_FX_RATE")
  private double principleSettleFxRate;

  @JsonProperty("PRIN_SETTLE_CRRNCY")
  private String principleSettleCurrency;

  @JsonProperty("BKR_NAME")
  private String brokerName;

  @JsonProperty("COUNTERPARTY")
  private String counterparty;
}