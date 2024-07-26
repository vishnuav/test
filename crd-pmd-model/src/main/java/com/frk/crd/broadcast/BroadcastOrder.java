package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastOrder implements BroadcastOrderAware {
  //        <></CRD_TABLE_NAME>
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName = "TS_ORDER";

  //        <></ORDER_ID>
  @JsonProperty("ORDER_ID")
  private String orderId = "5047705350";

  //        <></SEC_ID>
  @JsonProperty("SEC_ID")
  private String secId = "5047703478";

  //        <></INSTRUCTION>
  @JsonProperty("INSTRUCTION")
  private String instruction = "LIM";

  //        <></BROKER_REASON>
  @JsonProperty("BROKER_REASON")
  private String brokerReason = "P";

  //        <></NET_TRADE_IND>
  @JsonProperty("NET_TRADE_IND")
  private String netTradeIndicator = "A";

  //        <></EXCH_CD>
  @JsonProperty("EXCH_CD")
  private String exchangeCode = "OTC";

  //        <></TRADER>
  @JsonProperty("TRADER")
  private String trader = "SBIANCHI";

  //        <></MANAGER>
  @JsonProperty("MANAGER")
  private String manager = "SBIANCHI";

  //        <></CREATE_USER>
  @JsonProperty("CREATE_USER")
  private String createUser = "SBIANCHI";

  //        <></LAST_UPD_USER>
  @JsonProperty("LAST_UPD_USER")
  private String lastUpdateUser = "TISAPI";

  //        <></COMMENTS>
  @JsonProperty("COMMENTS")
  private String comments = "OTC Eq Index Call Option Order";

  //        <></TRANS_TYPE>
  @JsonProperty("TRANS_TYPE")
  private String transactionType = "BUYL";

  //        <></INV_CLASS_CD>
  @JsonProperty("INV_CLASS_CD")
  private String invoiceClassCode = "OPTN";

  //        <></STATUS>
  @JsonProperty("STATUS")
  private String status = "ACCT";

  //        <></DELIVERY_TYPE>
  @JsonProperty("DELIVERY_TYPE")
  private String deliverType = "DTC";

  //        <></EXEC_BROKER>
  @JsonProperty("EXEC_BROKER")
  private String executionBroker = "GSCO0";

  //        <></TRADE_DATE>
//  @JsonProperty("TRADE_DATE")
//  private LocalDateTime tradeDate = LocalDateTime.of(2024, 06, 28, 0, 0, 0, 0);

  //        <></ORDER_ACCT_CD>
  @JsonProperty("ORDER_ACCT_CD")
  private String orderAccountCode = "Test55";

  //        <></TARGET_QTY>
  @JsonProperty("TARGET_QTY")
  private double targetQuantity = 1000000;

  //        <></TARGET_AMT>
  @JsonProperty("TARGET_AMT")
  private double targetAmount;

  //        <></PRIN_LOCAL_CRRNCY_SEC_ID>
  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  private String principalLocalCurrencySecId = "702089284";

  //        <></CNTRCT_SIZE>
  @JsonProperty("CNTRCT_SIZE")
  private double contractSize = 100;

  //        <></PRIN_LOCAL_CRRNCY>
  @JsonProperty("PRIN_LOCAL_CRRNCY")
  private String principalLocalCurrency = "USD";

  //        <></REFERENCE_PRICE>
  @JsonProperty("REFERENCE_PRICE")
  private double referencePx = 4.5;

  //        <></SETTLE_DATE>
//  @JsonProperty("SETTLE_DATE")
//  private LocalDateTime settleDate = LocalDateTime.of(2024, 7, 1, 0, 0, 0, 0);

  //        <></PRIN_SETTLE_FX_RATE>
  @JsonProperty("PRIN_SETTLE_FX_RATE")
  private double principleSettleFxRate = 1;

  //        <></PRIN_SETTLE_CRRNCY>
  @JsonProperty("PRIN_SETTLE_CRRNCY")
  private String principleSettleCurrency = "USD";

  //        <></BKR_NAME>
  @JsonProperty("BKR_NAME")
  private String brokerName = "BANK OF AMERICA NA OTC";

  //        <></COUNTERPARTY>
  @JsonProperty("COUNTERPARTY")
  private String counterparty = "BOFA0";
}