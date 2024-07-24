package com.frk.crd.broadcast;

import com.frk.crd.core.JsonAware;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CRDBroadCastEvent implements JsonAware {
  private final String orderId;

  // To be hydrated
  private String secId;
  private List<BroadcastSecurity> securities = new ArrayList<>();
  private BroadcastOrder order;
  private List<BroadcastAllocation> allocations = new ArrayList<>();

  public CRDBroadCastEvent(long orderId) {
    this.orderId = String.valueOf(orderId);
  }
}