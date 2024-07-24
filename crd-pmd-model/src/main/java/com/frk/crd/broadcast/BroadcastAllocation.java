package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frk.crd.core.JsonAware;
import com.frk.crd.core.XMLParsingEligible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BroadcastAllocation implements JsonAware, XMLParsingEligible {
  //        <></CRD_TABLE_NAME>
  @JsonProperty("CRD_TABLE_NAME")
  private String crdTableName = "TS_ORDER_ALLOC";

  //        <></ORIG_ORDER_ID>
  @JsonProperty("ORIG_ORDER_ID")
  private String orignalOrderName = "5047690053";

  //        <></ORDER_ID>
  @JsonProperty("ORDER_ID")
  private String orderId = "5047690053";

  //        <></INSTRUCTION>
  @JsonProperty("INSTRUCTION")
  private String instructions = "LIM";

  //        <></BROKER_REASON>
  @JsonProperty("BROKER_REASON")
  private String brokerReason = "P";

  //        <></NET_TRADE_IND>
  @JsonProperty("NET_TRADE_IND")
  private String netTradeIndicator = "A";

  //        <></ORDER_DURATION>
  @JsonProperty("ORDER_DURATION")
  private String orderDuration = "GTC";

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
  private String comments = "Instructions";

  //        <></TRANS_TYPE>
  @JsonProperty("TRANS_TYPE")
  private String transactionType = "BUYL";

  //        <></INV_CLASS_CD>
  @JsonProperty("INV_CLASS_CD")
  private String invoiceClassCode = "OPTN";

  //        <></STATUS>
  @JsonProperty("STATUS")
  private String status = "ACCT";

  //        <></INCLUDE_IN_CASH>
  @JsonProperty("INCLUDE_IN_CASH")
  private String includeInCash = "Y";

  //        <></DELIVERY_TYPE>
  @JsonProperty("DELIVERY_TYPE")
  private String deliveryType = "DTC";

  //        <></EXEC_BROKER>
  @JsonProperty("EXEC_BROKER")
  private String executionBrokerCode = "GSCO0";

  //        <></TRADE_DATE>
//  @JsonProperty("TRADE_DATE")
//  private LocalDateTime tradeDate = LocalDateTime.of(2024, 6, 28, 1, 1, 1, 0);

  //        <></TO_TRADER_DATE>
//  @JsonProperty("TO_TRADER_DATE")
//  private LocalDateTime toTraderDate = LocalDateTime.of(2024, 6, 28, 1, 1, 1, 0);

  //        <></IPO>
  @JsonProperty("IPO")
  private String ipo = "N";

  //        <></ORDER_ACCT_CD>
  @JsonProperty("ORDER_ACCT_CD")
  private String orderAccountCode = "TEST230";

  //        <></PRIN_LOCAL_CRRNCY_SEC_ID>
  @JsonProperty("PRIN_LOCAL_CRRNCY_SEC_ID")
  private String principalLocalCurrencySecId = "702089284";

  //        <></TAX_LOT_SELL_CNVTN>
  @JsonProperty("TAX_LOT_SELL_CNVTN")
  private String taxLotSellConvention = "LIFO";

  //        <></TARGET_QTY>
  @JsonProperty("TARGET_QTY")
  private double targetQuantity = 1000000;

  //        <></NET_PRIN_AMT>
  @JsonProperty("NET_PRIN_AMT")
  private double netPrincipalAmount = 6000000;

  //        <></TARGET_NOTNL_BASE_AMT>
  @JsonProperty("TARGET_NOTNL_BASE_AMT")
  private double targetNotionalBaseAmount = 134620000;

  //        <></TARGET_NOTNL_AMT>
  @JsonProperty("TARGET_NOTNL_AMT")
  private double targetNotionalAmount = 134620000;

  //        <></EXEC_PRICE>
  @JsonProperty("EXEC_PRICE")
  private double executionPx = 6;

  //        <></EXEC_QTY>
  @JsonProperty("EXEC_QTY")
  private double executionQuantity = 1000000;

  //        <></EXEC_AMT>
  @JsonProperty("EXEC_AMT")
  private double executionAmount = 6000000;

  //        <></TARGET_AMT>
  @JsonProperty("TARGET_AMT")
  private double targetAmount = 6000000;

  //        <></COMMISION_IND>
  @JsonProperty("COMMISION_IND")
  private String commissionIndicator = "CPS";

  //        <></CUR_BASE_MKT_VAL>
  @JsonProperty("CUR_BASE_MKT_VAL")
  private double currentBaseMarketValue = 6000000;

  //        <></TRANS_SUB_TYPE>
  @JsonProperty("TRANS_SUB_TYPE")
  private String transactionSubType = "OPEN";
}