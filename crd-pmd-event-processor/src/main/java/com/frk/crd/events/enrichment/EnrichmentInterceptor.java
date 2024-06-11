package com.frk.crd.events.enrichment;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.model.Allocation;
import com.frk.crd.model.CRDEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EnrichmentInterceptor {
  public void withAllocations(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent event = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (event != null && StringUtils.isNotBlank(event.getInputEvent())) {
      String refId = event.getOrder().getRefId();
      List<Allocation> allocations = getAllocations(refId);
      event.getOrder().addAllocations(allocations);
    } else {
      log.error("Unable to map payload to event or input event is not present {}", payload);
    }
  }

  public void withOriginalOrderId(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent event = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (event != null && StringUtils.isNotBlank(event.getInputEvent())) {
      String refId = event.getOrder().getRefId();
      event.getOrder().setOriginalId(getOriginalOrderId(refId));
    } else {
      log.error("Unable to map payload to event or input event is not present {}", payload);
    }
  }

  long getOriginalOrderId(String refId) {
    return 123456789L;
  }

  List<Allocation> getAllocations(String refId) {
    Allocation allocation = new Allocation(1257291667L, refId, 36155L, 1000, 1000);
    List<Allocation> allocations = new ArrayList<>();
    allocations.add(allocation);
    allocation = new Allocation(allocation.getId() + 1, refId, allocation.getAccountCd(), 1000, 1000);
    allocations.add(allocation);
    return allocations;
  }
}