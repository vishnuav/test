package com.frk.crd.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeaderInterceptor {
  public void intercept(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    Event event = CustomJsonMessageConverter.fromXML(payload.toString(), Event.class).orElse(null);
    if (event != null && StringUtils.isNotBlank(event.getInputEvent())) {
      exchange.getIn().setHeader("CRDEvent", event.getInputEvent());
    } else {
      log.error("Unable to map payload to event or input event is not present {}", payload);
    }
  }
}