package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty("SECURITY")
  private BroadcastSecurity security;
  @JsonProperty("SWAP_SECURITY_MAIN")
  private BroadcastSecurity mainSecurity;
  @JsonProperty("SWAP_SECURITY_LEG1")
  private BroadcastSecurity securityLeg1;
  @JsonProperty("SWAP_SECURITY_LEG2")
  private BroadcastSecurity securityLeg2;

  @JsonProperty("ORDER")
  private BroadcastOrder order;
  @JsonProperty("ALLOCATIONS")
  private List<BroadcastAllocation> allocations = new ArrayList<>();

  public CRDBroadCastEvent(long orderId) {
    this.orderId = String.valueOf(orderId);
  }
}