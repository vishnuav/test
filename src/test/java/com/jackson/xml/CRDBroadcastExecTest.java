package com.jackson.xml;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
class CRDBroadcastExecTest {
  @Test
  void serialize() throws IOException, XMLStreamException {
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
      Assertions.assertEquals(5072922850L, order.getCrdId());
      Assertions.assertEquals("17,7", order.getVersion());
      Assertions.assertEquals("update", order.getOperation());
      Assertions.assertFalse(order.getFees().isEmpty());
      Assertions.assertEquals(2, order.getFees().size());
    } catch (Throwable e) {
      log.error("Unable to parse XML", e);
      throw e;
    }
  }
}