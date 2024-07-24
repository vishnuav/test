package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.BroadcastOrder;
import com.frk.crd.broadcast.BroadcastSecurity;
import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.CRDEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
public class EventProcessorImpl implements EventProcessor {
  public void process(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent crdEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    long orderId = crdEvent.getOrder().getId();
    CRDBroadCastEvent broadcastEvent = new CRDBroadCastEvent(orderId);
    hydrateOrder(broadcastEvent);
    log.info("Order:\n{}", broadcastEvent.getOrder().toXML());
    hydrateAllocations(broadcastEvent);
    log.info("Allocation:\n{}", broadcastEvent.getAllocations().get(0).toXML());
    hydrateSecurities(broadcastEvent);
    log.info("Security:\n{}", broadcastEvent.getSecurities().get(0).toXML());
    log.info("broadcast event\n{}", broadcastEvent.toXML());
    Flux.just(orderId)
      .map(CRDBroadCastEvent::new)
      .subscribeOn(Schedulers.boundedElastic()).parallel(24)
      .doOnNext(this::hydrateOrder)
      .doOnNext(this::hydrateSecurities)
      .doOnNext(this::hydrateAllocations)
      .doOnComplete(() -> {
      })
      .sequential()
      .onErrorResume(throwable -> {
        // TODO: 7/10/24 Add to exception handling mechanism with Exception Handler Rest call
        return Mono.empty();
      })
      .doFinally(signal -> {
        String message = exchange.getIn().getBody().toString();
        if (SignalType.ON_ERROR.equals(signal)) {
          log.info("Error processing event message {}", message);
        } else {
          log.info("Event message processed successfully {}", message);
        }
        // Add payload to DB
      })
      .blockLast();
  }

  private void hydrateOrder(CRDBroadCastEvent event) {
    event.withOrder(new BroadcastOrder());
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    event.withAllocations(new BroadcastAllocation());
  }

  private void hydrateSecurities(CRDBroadCastEvent event) {
    event.withSecurity(new BroadcastSecurity());
  }
}