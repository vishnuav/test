package com.frk.crd.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class EventTest {
  private final String expected = "<Event><inputEvent>PDP-ORDER-STATUS</inputEvent><eventDetails><id>5041248783</id><refId>WARR-Order-06-05-2024</refId><status>PREOK</status><targetQty>40</targetQty><execQty>0</execQty><manager>RDONATE</manager><trader>KLEUNG2</trader><execBroker/><fillBroker/></eventDetails></Event>";

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
      EventDetails eventDetails = event.getEventDetails();
      Assertions.assertNotNull(eventDetails);
      Assertions.assertEquals(5041248783L, eventDetails.getId());
      Assertions.assertEquals("WARR-Order-06-05-2024", eventDetails.getRefId());
      Assertions.assertEquals("PREOK", eventDetails.getStatus());
      Assertions.assertEquals(40, eventDetails.getTargetQty());
      Assertions.assertEquals(0, eventDetails.getExecQty());
      Assertions.assertEquals("RDONATE", eventDetails.getManager());
      Assertions.assertEquals("KLEUNG2", eventDetails.getTrader());
      Assertions.assertNull(eventDetails.getExecBroker());
      Assertions.assertNull(eventDetails.getFillBroker());

      String toXML = xmlMapper.writeValueAsString(event);
      Assertions.assertNotNull(toXML);
      Assertions.assertEquals(expected, toXML);
    } catch (Throwable e) {
      Assertions.fail("Unable to parse XML " + xml, e);
    }
  }
}