package com.frk.crd.events.processor.impl;

import com.frk.crd.broadcast.BroadcastAllocation;
import com.frk.crd.broadcast.BroadcastOrder;
import com.frk.crd.broadcast.BroadcastSecurity;
import com.frk.crd.broadcast.CRDBroadCastEvent;
import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.db.dao.DBBroadcastAllocationRepository;
import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.model.DBBroadcastAllocation;
import com.frk.crd.db.model.DBBroadcastOrder;
import com.frk.crd.db.model.DBBroadcastSecurity;
import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.model.CRDEvent;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventProcessorImpl implements EventProcessor {
  private DBBroadcastOrderRepository orderRepository;
  private DBBroadcastAllocationRepository allocationRepository;
  private DBBroadcastSecurityRepository securityRepository;

  public EventProcessorImpl(DBBroadcastOrderRepository orderRepository, DBBroadcastAllocationRepository allocationRepository, DBBroadcastSecurityRepository securityRepository) {
    this.orderRepository = orderRepository;
    this.allocationRepository = allocationRepository;
    this.securityRepository = securityRepository;
  }

  public void process(Exchange exchange) {
    Object payload = exchange.getIn().getBody();
    CRDEvent crdEvent = CustomJsonMessageConverter.fromXML(payload.toString(), CRDEvent.class).orElse(null);
    long orderId = crdEvent.getOrder().getId();
    CRDBroadCastEvent broadcastEvent = new CRDBroadCastEvent(orderId);
    hydrateOrder(broadcastEvent);
    hydrateAllocations(broadcastEvent);
    hydrateSecurity(broadcastEvent);
    log.info("broadcast order\n{}", broadcastEvent.getOrder().toXML());
    log.info("broadcast allocation\n{}", broadcastEvent.getAllocations().get(0).toXML());
    log.info("broadcast security\n{}", broadcastEvent.getSecurity().toXML());

    log.info("broadcast event\n{}", broadcastEvent.toXML());
  }

  private void hydrateOrder(CRDBroadCastEvent event) {
    DBBroadcastOrder order = orderRepository.getOrder(event.getOrderId());
    BroadcastOrder broadcastOrder = new BroadcastOrder("CRD_ORDER", order.getOrderId(), order.getSecId(),
        order.getInstruction(), order.getBrokerReason(), order.getNetTradeIndicator(), order.getExchangeCode(),
        order.getTrader(), order.getManager(), order.getCreateUser(), order.getLastUpdateUser(), order.getComments(),
        order.getTransactionType(), order.getInvoiceClassCode(), order.getStatus(), order.getDeliveryType(),
        order.getExecutingBroker(), order.getOrderAccountCode(), convert(order.getTargetQuantity()), convert(order.getTargetAmount()),
        order.getPrincipleLocalCurrencySecId(), 0, "", 0, 0,
        "USD", order.getExecutingBroker(), order.getCounterparty());
    event.setOrder(broadcastOrder);
    log.info("broadcast order\n{}", event.getOrder().toXML());
  }

  double convert(Double given) {
    return given == null ? 0.0 : given;
  }

  private void hydrateAllocations(CRDBroadCastEvent event) {
    List<DBBroadcastAllocation> allocations = allocationRepository.getAllocations(event.getOrderId());

    event.withAllocations(new BroadcastAllocation());
    log.info("broadcast allocation\n{}", event.getAllocations().get(0).toXML());
  }

  private void hydrateSecurity(CRDBroadCastEvent event) {
    DBBroadcastSecurity broadcastSecurity = securityRepository.getSecurity(event.getSecId());
    BroadcastSecurity security = new BroadcastSecurity("CRD_SECURITY", broadcastSecurity.getAssetCurrencyCode(),
        broadcastSecurity.getBusinessDayConversion(), broadcastSecurity.getCalcAgent(), broadcastSecurity.getCountryOfRisk(),
        broadcastSecurity.getCusip(), broadcastSecurity.getExternalSecId(), broadcastSecurity.getSecId(), broadcastSecurity.getIssuerCode(),
        broadcastSecurity.getIssuerName(), broadcastSecurity.getIssueCountryCode(), broadcastSecurity.getIssueDateAdjustment(),
        broadcastSecurity.getLocationCurrencyCode(), broadcastSecurity.getMaturityAdjustment(), broadcastSecurity.getOptionExpireType(),
        broadcastSecurity.getParentIssuerCode(), broadcastSecurity.getPayDateHolExchangeCode(), broadcastSecurity.getSecurityName(),
        broadcastSecurity.getSecurityTypeCode(), "", "", "", broadcastSecurity.getUnderlyingSecId(),
        broadcastSecurity.getOtcClearingEligibility(), broadcastSecurity.getExerciseSettleLag(), convert(broadcastSecurity.getMarketPx()),
        convert(broadcastSecurity.getStrikePx()), broadcastSecurity.getExpireSettleDate(), broadcastSecurity.getFirstExerciseDate(),
        broadcastSecurity.getIssueDate(), broadcastSecurity.getIssueDate(), broadcastSecurity.getIssueDate());
    event.setSecurity(security);
  }
}