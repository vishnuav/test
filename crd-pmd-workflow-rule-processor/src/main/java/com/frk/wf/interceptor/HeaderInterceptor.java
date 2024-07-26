package com.frk.wf.interceptor;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.model.CRDEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeaderInterceptor {
  public void intercept(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent CRDEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (CRDEvent != null && StringUtils.isNotBlank(CRDEvent.getInputEvent())) {
      exchange.getOut().setHeader("CRDEvent", CRDEvent.getInputEvent());
    } else {
      log.error("Unable to map payload to event or input event is not present {}", payload);
    }
  }
}