package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.BroadcastOrder;
import com.frk.crd.broadcast.BroadcastSecurity;
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
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.ACCEPT;

@Slf4j
@Component
public class EventProcessorImpl implements EventProcessor {
  private final WebClient crdDBWebclient;

  public EventProcessorImpl(WebClient crdDBWebclient) {
    this.crdDBWebclient = crdDBWebclient;
  }

  public void process(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    if (payload == null || StringUtils.isBlank(payload.toString())) {
      log.error("Unable to parse empty/null payload in CRD Event");
    }
    CRDEvent crdEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    if (crdEvent == null || crdEvent.getOrder() == null || crdEvent.getOrder().getId() == 0) {
      log.error("Unable to parse CRD Event {}", payload);
    }
    long orderId = crdEvent.getOrder().getId();
    CRDBroadCastEvent broadcastEvent = new CRDBroadCastEvent(orderId);

    // Get orders
    hydrateOrder(broadcastEvent);
    log.info("broadcast order\n{}", broadcastEvent.getOrder().toXML());

    // Get securities
    hydrateSecurity(broadcastEvent);
    log.info("broadcast security\n{}", broadcastEvent.getSecurity().toXML());
    if (broadcastEvent.getSecurity().isSwaption()) {
      broadcastEvent.setMainSecurity(getSecurity(broadcastEvent.getSecurity().getUnderlyingSecId()));
      // get Child securities
      List<String> childSecurities = getChildSecurities(broadcastEvent.getSecId());
      if (childSecurities == null || childSecurities.isEmpty()) {
        log.error("Unable to get child/swap securities for parent sec id {}", broadcastEvent.getSecId());
      } else {
        broadcastEvent.setSecurityLeg1(getSecurity(childSecurities.get(0)));
        broadcastEvent.setSecurityLeg1(getSecurity(childSecurities.get(1)));
      }
    }

    // Get allocations
    hydrateAllocations(broadcastEvent);
    broadcastEvent.getAllocations().forEach(allocation -> log.info("broadcast allocation\n{}", broadcastEvent.toXML()));
    log.info("broadcast event\n{}", broadcastEvent.toXML());
  }

  private void hydrateOrder(CRDBroadCastEvent event) {
    event.setOrder(getOrder(event.getOrderId()));
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    event.setAllocations(getAllocations(event.getOrderId()));
  }

  private void hydrateSecurity(CRDBroadCastEvent event) {
    event.setSecurity(getSecurity(event.getSecId()));
  }

  BroadcastSecurity getSecurity(String secId) {
    return crdDBWebclient.get().uri(uriBuilder -> uriBuilder
        .path(DiscoveryService.GET_SECURITY)
        .queryParam("secId", secId).build())
      .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .retrieve()
      .onStatus(status -> {
        if (HttpStatus.TOO_MANY_REQUESTS.equals(status)) {
          log.error("Too many request getting Security from CRD DB Service");
        } else if (status.isError()) {
          log.error("Unable to get Security {} - status {}", secId, status);
        } else {
          log.debug("Security request successful for {}", secId);
        }
        return status.isError();
      }, response -> Mono.empty()).bodyToMono(BroadcastSecurity.class)
      .doOnError(throwable -> log.error("Security request failed for {}", secId, throwable))
      .doFinally(signalType -> {
        if (Objects.requireNonNull(signalType) == SignalType.ON_ERROR || signalType == SignalType.CANCEL) {
          log.debug("Security request failed for {}", secId);
        } else if (signalType == SignalType.ON_COMPLETE) {
          log.debug("Security request successful for {}", secId);
        }
      })
      .block();
  }

  BroadcastOrder getOrder(String orderId) {
    return crdDBWebclient.get().uri(uriBuilder -> uriBuilder
        .path(DiscoveryService.GET_ORDER)
        .queryParam("orderId", orderId).build())
      .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .retrieve()
      .onStatus(status -> {
        if (HttpStatus.TOO_MANY_REQUESTS.equals(status)) {
          log.error("Too many request getting order {} from CRD DB Service", orderId);
        } else if (status.isError()) {
          log.error("Unable to get Order {} - status {}", orderId, status);
        } else {
          log.debug("Order request successful for {}", orderId);
        }
        return status.isError();
      }, response -> Mono.empty()).bodyToMono(BroadcastOrder.class)
      .doOnError(throwable -> log.error("Order request failed for {}", orderId, throwable))
      .doFinally(signalType -> {
        if (Objects.requireNonNull(signalType) == SignalType.ON_ERROR || signalType == SignalType.CANCEL) {
          log.debug("Order request failed for {}", orderId);
        } else if (signalType == SignalType.ON_COMPLETE) {
          log.debug("Order request successful for {}", orderId);
        }
      })
      .block();
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

  List<String> getChildSecurities(String secId) {
    return crdDBWebclient.get().uri(uriBuilder -> uriBuilder
        .path(DiscoveryService.GET_CHILD_SECURITIES)
        .queryParam("secId", secId).build())
      .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
      .retrieve()
      .onStatus(status -> {
        if (HttpStatus.TOO_MANY_REQUESTS.equals(status)) {
          log.error("Too many request getting child securities CRD DB Service for security {}", secId);
        } else if (status.isError()) {
          log.error("Unable to get child securities {} - status {}", secId, status);
        } else {
          log.debug("Child securities request successful for {}", secId);
        }
        return status.isError();
      }, response -> Mono.empty()).bodyToFlux(String.class)
      .doOnError(throwable -> log.error("Child securities request failed for secId {}", secId, throwable))
      .doFinally(signalType -> {
        if (Objects.requireNonNull(signalType) == SignalType.ON_ERROR || signalType == SignalType.CANCEL) {
          log.debug("Child securities request failed for {}", secId);
        } else if (signalType == SignalType.ON_COMPLETE) {
          log.debug("Child securities request successful for {}", secId);
        }
      })
      .collectList().block();
  }
}