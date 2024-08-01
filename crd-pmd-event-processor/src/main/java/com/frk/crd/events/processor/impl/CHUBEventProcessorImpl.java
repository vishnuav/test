package com.frk.crd.events.processor.impl;

import static org.springframework.http.HttpHeaders.ACCEPT;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.BroadcastOrder;
import com.frk.crd.broadcast.BroadcastSecurity;
import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.CRDEvent;
import com.frk.crd.utilities.DiscoveryService;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Slf4j
@Component
public class CHUBEventProcessorImpl implements EventProcessor {
  private final WebClient crdDBWebclient;

  public CHUBEventProcessorImpl(WebClient crdDBWebclient) {
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
    BroadcastOrder order = getOrder(String.valueOf(orderId));
    log.info("Broadcast order\n{}", order.toXML());
    broadcastEvent.setOrder(order);

    // Get securities
    String secId = order.getSecId();
    BroadcastSecurity security = getSecurity(secId);
    log.info("Broadcast security\n {}", security.toXML());
    broadcastEvent.setSecurity(security);

    if (broadcastEvent.getSecurity().isSwaption()) {
      String underlyingSecId = security.getUnderlyingSecId();
      BroadcastSecurity underlyingSecurity = getSecurity(underlyingSecId);
      log.info("Underlying security\n {}", underlyingSecurity.toXML());
      broadcastEvent.setMainSecurity(underlyingSecurity);

      // get Child securities
      List<String> childSecurities = getChildSecurities(broadcastEvent.getSecId());
      if (childSecurities == null || childSecurities.isEmpty() || childSecurities.size() != 2) {
        log.error("Unable to get child/swap securities for parent sec id {}", broadcastEvent.getSecId());
      } else {
        log.info("Found {} child securities for underlying sec id {}", childSecurities.size(), underlyingSecId);
        String leg1SecId = childSecurities.get(0);
        BroadcastSecurity leg1Security = getSecurity(leg1SecId);
        log.info("Leg1 security\n {}", leg1Security.toXML());
        broadcastEvent.setSecurityLeg1(leg1Security);

        String leg2SecId = childSecurities.get(1);
        BroadcastSecurity leg2Security = getSecurity(leg2SecId);
        log.info("Leg2 security\n {}", leg2Security.toXML());
        broadcastEvent.setSecurityLeg2(leg2Security);
      }
    }

    // Get allocations
    List<BroadcastAllocation> allocations = getAllocations(String.valueOf(orderId));
    if (allocations == null || allocations.isEmpty()) {
      log.error("No allocations found for order id {}", orderId);
    } else {
      log.info("Found {} allocations for order id {}", allocations.size(), orderId);
      allocations.forEach(a -> {
        log.info("Allocation\n{}", a.toXML());
      });
      broadcastEvent.setAllocations(allocations);
    }
    log.info("Order Id {} processed for order, security and allocations\n{}", orderId, broadcastEvent.toXML());
    exchange.getOut().setBody(crdEvent.toXML());
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