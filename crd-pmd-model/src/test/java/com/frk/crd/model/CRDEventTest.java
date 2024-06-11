package com.frk.crd.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class CRDEventTest {
  private final String expected = "<CRDEvents><Order><Id>5041248783</Id><OriginalId>0</OriginalId><RefId>WARR-Order-06-05-2024</RefId><Status>PREOK</Status><TargetQty>40</TargetQty><Manager>RDONATE</Manager></Order></CRDEvents>";

  @Test
  void serializeFromWFRule() throws IOException {
    File file = new File("src/test/resources/CRD-WorkFlowEvent.xml");
    String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    CRDEvent event = CustomJsonMessageConverter.fromXML(xml, CRDEvent.class).orElse(null);
    Assertions.assertNotNull(event);
    Assertions.assertEquals("PDP-ORDER-STATUS", event.getInputEvent());
    Order order = event.getOrder();
    Assertions.assertNotNull(order);
    Assertions.assertEquals(5041248783L, order.getId());
    Assertions.assertEquals("WARR-Order-06-05-2024", order.getRefId());
    Assertions.assertEquals("PREOK", order.getStatus());
    Assertions.assertEquals(40, order.getTargetQty());
    Assertions.assertEquals(0, order.getExecQty());
    Assertions.assertEquals("RDONATE", order.getManager());
    Assertions.assertEquals("KLEUNG2", order.getTrader());
    Assertions.assertNull(order.getExecBroker());
    Assertions.assertNull(order.getFillBroker());
    String actual = CustomJsonMessageConverter.toXML(event);
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected, actual);
  }
}