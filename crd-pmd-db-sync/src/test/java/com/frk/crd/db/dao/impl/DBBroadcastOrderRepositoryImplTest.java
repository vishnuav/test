package com.frk.crd.db.dao.impl;

import com.frk.crd.db.configuration.CRDDBSyncConfiguration;
import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.model.DBBroadcastOrder;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "dev2")
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBBroadcastOrderRepositoryImpl.class})
class DBBroadcastOrderRepositoryImplTest {
  @Autowired
  private DBBroadcastOrderRepository repository;

  @Test
  void testOrder() {
    Assertions.assertNotNull(repository);
    String orderId = "5047678919";
    DBBroadcastOrder order = repository.getOrder(orderId);
    Assertions.assertNotNull(order);
    Assertions.assertEquals(orderId, order.getOrderId());
    Assertions.assertEquals("MKT", order.getInstruction());
    Assertions.assertEquals("P", order.getBrokerReason());
    Assertions.assertEquals("A", order.getNetTradeIndicator());
    Assertions.assertEquals("GTD", order.getOrderDuration());
    Assertions.assertEquals("OTC", order.getExchangeCode());
    Assertions.assertEquals("SBIANCHI", order.getTrader());
    Assertions.assertEquals("SBIANCHI", order.getManager());
    Assertions.assertEquals("SBIANCHI", order.getCreateUser());
    Assertions.assertEquals("TISAPI", order.getLastUpdateUser());
    Assertions.assertEquals("Instrustions Here", order.getComments());
    Assertions.assertEquals("BUYL", order.getTransactionType());
    Assertions.assertEquals("OPTN", order.getInvoiceClassCode());
    Assertions.assertEquals("ACCT", order.getStatus());
    Assertions.assertEquals("Y", order.getIncludeInCash());
    Assertions.assertEquals("DTC", order.getDeliveryType());
    Assertions.assertNull(order.getCounterparty());
    Assertions.assertEquals("GSCO0", order.getExecutingBroker());
    Assertions.assertEquals("2024-06-28", order.getTradeDate().toString());
    Assertions.assertEquals("2024-06-28", order.getToTradeDate().toString());
    Assertions.assertEquals("N", order.getIpo());
    Assertions.assertEquals("Test55", order.getOrderAccountCode());
    Assertions.assertEquals(100000.0, order.getTargetQuantity());
    Assertions.assertEquals(1000000.0, order.getTargetAmount());
    Assertions.assertNull(order.getTargetDiscountRate());
    Assertions.assertNull(order.getExecutionDiscountRate());
    Assertions.assertEquals("702089284", order.getPrincipleLocalCurrencySecId());
    Assertions.assertEquals("200", order.getUdfFloat16());
    Assertions.assertEquals("LIFO", order.getTaxLotSellConvention());
    Assertions.assertEquals(1200039.0, order.getNetPrincipleAmount());
    Assertions.assertEquals(1.3462E7, order.getTargetNotionalBaseAmount());
    Assertions.assertEquals(1.3462E7, order.getTargetNotionalAmount());
    Assertions.assertEquals(5, order.getExecutionPx());
    Assertions.assertEquals(100000.0, order.getExecutionQuantity());
    Assertions.assertEquals(1000000.0, order.getExecutionAmount());
    Assertions.assertEquals(0, order.getNetMoney());
    Assertions.assertEquals("CPS", order.getCommissionIndicator());
    Assertions.assertEquals(200000.0, order.getCommissionAmount());
    Assertions.assertEquals(1000000.0, order.getCurrentBaseMarketValue());
    Assertions.assertEquals("Test55", order.getAccountCode());
    Assertions.assertEquals("OPEN", order.getTransactionSubType());
    Assertions.assertEquals("5047678918", order.getSecId());
  }

  @Test
  void bulkOrders() {
    DBBroadcastOrder order = repository.getOrder("5047678919");
    Assertions.assertNotNull(order);
  }
}