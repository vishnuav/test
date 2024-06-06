package com.frk.crd.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.frk.crd.model.Event;
import com.frk.crd.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
class EventTest {
  private final String expected = "<event><order><placeDate>2024-04-23 10:22:17.985</placeDate><settleDate>2024-04-23 00:00:00</settleDate><tradeDate>2024-04-23 00:00:00</tradeDate><status>ACCT</status><manager>RDONATE</manager><trader>RDONATE</trader><execBroker>JPMS5</execBroker><id>5072922850</id><operation>update</operation><security><cusip>160853MS3</cusip><sedol>BFXSKM7</sedol><securityId>669986</securityId><securityTypeCode>MUNI</securityTypeCode><invClassCode>DEBT</invClassCode><transactionType>SELLL</transactionType><targetQuantity>7100000.0</targetQuantity><executionQuantity>7100000.0</executionQuantity></security><allocations><allocations><fundManager>FRIVERA</fundManager><id>1257055829</id><operation>update</operation><targetQuantity>6900000.0</targetQuantity><executingQuantity>6900000.0</executingQuantity></allocations><allocations><fundManager>JWILEY</fundManager><id>1257073760</id><operation>update</operation><targetQuantity>200000.0</targetQuantity><executingQuantity>200000.0</executingQuantity></allocations></allocations></order></event>";

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
      Event event = xmlMapper.readValue(xml, Event.class);
      Assertions.assertNotNull(event);
      String toXML = xmlMapper.writeValueAsString(event);
      Assertions.assertNotNull(toXML);
//            Assertions.assertEquals(expected, toXML);
    } catch (Throwable e) {
      log.error("Unable to parse XML", e);
      Assertions.fail("Unable to parse XML " + xml);
    }
  }
}