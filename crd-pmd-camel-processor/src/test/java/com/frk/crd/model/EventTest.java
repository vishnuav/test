package com.frk.crd.model;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class EventTest {
  @Test
  void serialize() throws IOException {
    File file = new File("src/test/resources/CRDEvent.xml");
    String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    xmlMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    EventWrapper eventWrapper = xmlMapper.readValue(xml, EventWrapper.class);
    Assertions.assertNotNull(eventWrapper);
  }
}