package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.CRDEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventProcessorImpl implements EventProcessor {
  public void process(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent crdEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    long orderId = crdEvent.getOrder().getId();
    CRDBroadCastEvent broadcastEvent = new CRDBroadCastEvent(orderId);
    hydrateOrder(broadcastEvent);
    hydrateAllocations(broadcastEvent);
    hydrateSecurity(broadcastEvent);
    log.info("broadcast event\n{}", broadcastEvent.toXML());
  }

  private void hydrateOrder(CRDBroadCastEvent event) {
    event.withOrder(event.getOrderId());
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    event.withAllocations(new BroadcastAllocation());
  }

  private void hydrateSecurity(CRDBroadCastEvent event) {
    event.withSecurity(event.getOrderId());
  }
}