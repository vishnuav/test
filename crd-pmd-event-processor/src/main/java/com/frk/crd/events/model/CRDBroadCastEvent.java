package com.frk.crd.events.model;

import com.frk.crd.core.JsonAware;
import com.frk.crd.model.Allocation;
import com.frk.crd.model.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CRDBroadCastEvent implements JsonAware {
  private final String orderId;

  // To be hydrated
  private String secId;
  private List<CSMSecurity> securities = new ArrayList<>();
  private Order order;
  private List<Allocation> allocations = new ArrayList<>()


  public CRDBroadCastEvent(long orderId) {
    this.orderId = String.valueOf(orderId);
  }
}