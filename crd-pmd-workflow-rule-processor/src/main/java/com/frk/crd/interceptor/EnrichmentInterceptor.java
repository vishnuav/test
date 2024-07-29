package com.frk.crd.interceptor;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.model.CRDEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnrichmentInterceptor {
  public void enrich(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent event = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (event != null && StringUtils.isNotBlank(event.getInputEvent())) {
      exchange.getOut().setHeader("CRDEvent", event.getInputEvent());
      exchange.getOut().setBody(event.toXML());
    } else {
      log.error("Unable to map payload to event or input event is not present {}", payload);
    }
  }
}