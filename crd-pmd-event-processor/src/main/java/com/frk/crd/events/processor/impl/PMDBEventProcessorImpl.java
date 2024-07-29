package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.CRDEvent;
import com.frk.crd.utilities.DiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.ACCEPT;

@Slf4j
public class PMDBEventProcessorImpl implements EventProcessor {
  private final WebClient crdDBWebclient;

  public PMDBEventProcessorImpl(WebClient crdDBWebclient) {
    this.crdDBWebclient = crdDBWebclient;
  }

  public void process(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    if (payload == null || StringUtils.isBlank(payload.toString())) {
      log.error("Unable to parse empty/null payload in PMD Event");
    }
    CRDEvent crdEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (crdEvent == null || crdEvent.getOrder() == null || crdEvent.getOrder().getId() == 0) {
      log.error("Unable to parse PMD Event {}", payload);
    }
    long orderId = crdEvent.getOrder().getId();
    CRDBroadCastEvent broadcastEvent = new CRDBroadCastEvent(orderId);

    // Get allocations
    hydrateAllocations(broadcastEvent);
    broadcastEvent.getAllocations().forEach(allocation -> log.info("broadcast allocation\n{}", broadcastEvent.toXML()));
    log.info("broadcast event\n{}", broadcastEvent.toXML());
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    event.setAllocations(getAllocations(event.getOrderId()));
  }

  List<BroadcastAllocation> getAllocations(String orderId) {
    return crdDBWebclient.get().uri(uriBuilder -> uriBuilder
        .path(DiscoveryService.GET_ALLOCATIONS)
        .queryParam("orderId", orderId).build())
      .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .retrieve()
      .onStatus(status -> {
        if (HttpStatus.TOO_MANY_REQUESTS.equals(status)) {
          log.error("Too many request getting allocations CRD DB Service for order {}", orderId);
        } else if (status.isError()) {
          log.error("Unable to get allocations {} - status {}", orderId, status);
        } else {
          log.debug("Allocations request successful for {}", orderId);
        }
        return status.isError();
      }, response -> Mono.empty()).bodyToFlux(BroadcastAllocation.class)
      .doOnError(throwable -> log.error("Allocations request failed for order {}", orderId, throwable))
      .doFinally(signalType -> {
        if (Objects.requireNonNull(signalType) == SignalType.ON_ERROR || signalType == SignalType.CANCEL) {
          log.debug("Allocations request failed for {}", orderId);
        } else if (signalType == SignalType.ON_COMPLETE) {
          log.debug("Allocations request successful for {}", orderId);
        }
      })
      .collectList().block();
  }
}