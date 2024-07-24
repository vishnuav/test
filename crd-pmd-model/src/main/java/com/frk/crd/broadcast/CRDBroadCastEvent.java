package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frk.crd.core.XMLParsingEligible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CRDBroadCastEvent implements XMLParsingEligible {
  @JsonIgnore
  private String orderId;

  @JsonIgnore
  private String secId;
  private List<BroadcastSecurity> securities = new ArrayList<>();
  private BroadcastOrder order;
  private List<BroadcastAllocation> allocations = new ArrayList<>();

  public CRDBroadCastEvent(long orderId) {
    this.orderId = String.valueOf(orderId);
  }

  public void withOrder(BroadcastOrder broadcastOrder) {
    this.order = broadcastOrder;
  }

  public void withAllocations(BroadcastAllocation broadcastAllocation) {
    this.allocations.add(broadcastAllocation);
  }

  public void withSecurity(BroadcastSecurity broadcastSecurity) {
    this.securities.add(broadcastSecurity);
  }
}