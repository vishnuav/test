package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.Order;
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
    if (exchange != null && exchange.getIn() != null && exchange.getIn().getBody() != null) {
      Order order = exchange.getIn().getBody(Order.class);
      Flux.just(order)
        .map(v -> new CRDBroadCastEvent(v.getId()))
        .subscribeOn(Schedulers.boundedElastic()).parallel(24)
        .doOnNext(this::hydrateOrder)
        .doOnNext(this::hydrateSecurities)
        .doOnNext(this::hydrateAllocations)
        .sequential()
        .onErrorResume(throwable -> {
          // TODO: 7/10/24 Add to exception handling mechanism with Exception Handler Rest call
          return Mono.empty();
        })
        .doFinally(signal -> {
          String payload = exchange.getIn().getBody().toString();
          if (SignalType.ON_ERROR.equals(signal)) {
            log.info("Error processing event message {}", payload);
          } else {
            log.info("Event message processed successfully {}", payload);
          }
          // Add payload to DB
        })
        .subscribe();
    }
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    // TODO: 7/10/24 Populate allocations with allocation query
  }

  private void hydrateSecurities(CRDBroadCastEvent event) {
    // TODO: 7/10/24 Populate Security Id with security query
    // TODO: 7/10/24 Populate Underlying if swaption with parent sec id as underying id
    // TODO: 7/10/24 Populate Legs if swaption with security query for each leg
  }

  private void hydrateOrder(CRDBroadCastEvent event) {
    // TODO: 7/10/24 Populate Order with order query
  }
}