package com.frk.crd.db.dao.impl;

import com.frk.crd.db.configuration.CRDDBSyncConfiguration;
import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.model.DBOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "oracle")
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBBroadcastOrderRepositoryImpl.class})
class DBOrderRepositoryImplTest {
  @Autowired
  private DBBroadcastOrderRepository repository;

  @Test
  void testOrder() {
    Assertions.assertNotNull(repository);
    String orderId = "5047678919";
    DBOrder order = repository.getOrder(orderId);
    Assertions.assertNotNull(order);
    Assertions.assertEquals(orderId, order.getOrderId());
    Assertions.assertEquals("MKT", order.getInstruction());
    Assertions.assertEquals("P", order.getBrokerReason());
    Assertions.assertEquals("A", order.getNetTradeIndicator());
    Assertions.assertNull(order.getOrderDuration());
    Assertions.assertEquals("OTC", order.getExchangeCode());
    Assertions.assertEquals("SBIANCHI", order.getTrader());
    Assertions.assertEquals("SBIANCHI", order.getManager());
    Assertions.assertEquals("SBIANCHI", order.getCreateUser());
    Assertions.assertNull(order.getLastUpdateUser());
    Assertions.assertEquals("Instrustions Here", order.getComments());
    Assertions.assertEquals("BUYL", order.getTransactionType());
    Assertions.assertEquals("OPTN", order.getInvoiceClassCode());
    Assertions.assertEquals("ACCT", order.getStatus());
    Assertions.assertNull(order.getIncludeInCash());
    Assertions.assertEquals("DTC", order.getDeliveryType());
    Assertions.assertNull(order.getCounterparty());
    Assertions.assertEquals("GSCO0", order.getExecutingBroker());
    Assertions.assertEquals("2024-06-28", order.getTradeDate().toString());
    Assertions.assertNull(order.getToTradeDate());
    Assertions.assertEquals("N", order.getIpo());
    Assertions.assertNull(order.getOrderAccountCode());
    Assertions.assertEquals(100000.0, order.getTargetQuantity());
    Assertions.assertEquals(1000000.0, order.getTargetAmount());
    Assertions.assertNull(order.getTargetDiscountRate());
    Assertions.assertEquals(0, order.getExecutionDiscountRate());
    Assertions.assertNull(order.getPrincipleLocalCurrencySecId());
  }
}