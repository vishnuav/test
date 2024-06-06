package com.frk.crd.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeaderInterceptor {
  public void intercept(Exchange exchange) {
    try {
      String payload = exchange.getIn().getBody(String.class);
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
      xmlMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
      xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      Event event = xmlMapper.readValue(payload, Event.class);
      if (event != null && StringUtils.isNotBlank(event.getInputEvent())) {
        exchange.getIn().setHeader("CRDEvent", event.getInputEvent());
      } else {
        log.error("Unable to map payload to event or input event is not present {}", payload);
      }
    } catch (JsonProcessingException e) {
      log.error("Error processing exchange", e);
    }
  }
}
