package com.jackson.xml;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jackson.xml.model.broadcast.Allocation;
import com.jackson.xml.model.broadcast.CRDBroadcastExec;
import com.jackson.xml.model.broadcast.Order;
import com.jackson.xml.model.broadcast.Security;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
class CRDBroadcastExecTest {
  private final String expected = "<CRDBroadcastExec><order><placeDate>2024-04-23 10:22:17.985</placeDate><settleDate>2024-04-23 00:00:00</settleDate><tradeDate>2024-04-23 00:00:00</tradeDate><status>ACCT</status><manager>RDONATE</manager><trader>RDONATE</trader><execBroker>JPMS5</execBroker><id>5072922850</id><operation>update</operation><security><cusip>160853MS3</cusip><sedol>BFXSKM7</sedol><securityId>669986</securityId><securityTypeCode>MUNI</securityTypeCode><invClassCode>DEBT</invClassCode><transactionType>SELLL</transactionType><targetQuantity>7100000.0</targetQuantity><executionQuantity>7100000.0</executionQuantity></security><allocations><allocations><fundManager>FRIVERA</fundManager><id>1257055829</id><operation>update</operation><targetQuantity>6900000.0</targetQuantity><executingQuantity>6900000.0</executingQuantity></allocations><allocations><fundManager>JWILEY</fundManager><id>1257073760</id><operation>update</operation><targetQuantity>200000.0</targetQuantity><executingQuantity>200000.0</executingQuantity></allocations></allocations></order></CRDBroadcastExec>";

  @Test
  void serialize() throws IOException {
    File file = new File("src/test/resources/CRD_Order_Broadcast.xml");
    String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    xmlMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    try {
      CRDBroadcastExec crdBroadcastExec = xmlMapper.readValue(xml, CRDBroadcastExec.class);
      Assertions.assertNotNull(crdBroadcastExec);
      Order order = crdBroadcastExec.getOrder();
      Assertions.assertNotNull(order);
      Assertions.assertEquals(5072922850L, order.getId());
      Assertions.assertEquals("update", order.getOperation());
      Assertions.assertEquals("2024-04-23 10:22:17.985", order.getPlaceDate());
      Assertions.assertEquals("2024-04-23 00:00:00", order.getSettleDate());
      Assertions.assertEquals("2024-04-23 00:00:00", order.getTradeDate());

      Security security = order.getSecurity();
      Assertions.assertNotNull(security);

      Assertions.assertEquals("160853MS3", security.getCusip());
      Assertions.assertEquals("BFXSKM7", security.getSedol());
      Assertions.assertEquals("669986", security.getSecurityId());
      Assertions.assertEquals("MUNI", security.getSecurityTypeCode());
      Assertions.assertEquals(7100000.0, security.getTargetQuantity());
      Assertions.assertEquals(7100000.0, security.getExecutionQuantity());


      List<Allocation> allocations = order.getAllocations();
      Assertions.assertFalse(allocations.isEmpty());
      Assertions.assertEquals(2, allocations.size());
      Allocation first = allocations.get(0);
      Assertions.assertNotNull(first);
      Assertions.assertEquals(1257055829L, first.getId());
      Assertions.assertEquals("update", first.getOperation());
      Assertions.assertEquals("FRIVERA", first.getFundManager());
      Assertions.assertEquals(6900000.0, first.getTargetQuantity());
      Assertions.assertEquals(6900000., first.getExecutingQuantity());

      Allocation second = allocations.get(1);
      Assertions.assertNotNull(second);
      Assertions.assertEquals(1257073760L, second.getId());
      Assertions.assertEquals("update", second.getOperation());
      Assertions.assertEquals("JWILEY", second.getFundManager());
      Assertions.assertEquals(200000, second.getTargetQuantity());
      Assertions.assertEquals(200000, second.getExecutingQuantity());

      String toXML = xmlMapper.writeValueAsString(crdBroadcastExec);
      Assertions.assertNotNull(toXML);
      Assertions.assertEquals(expected, toXML);
    } catch (Throwable e) {
      log.error("Unable to parse XML", e);
      Assertions.fail("Unable to parse XML " + xml);
    }
  }
}