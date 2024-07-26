package com.frk.crd.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.frk.crd.core.XMLParsingEligible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

  @JsonProperty("Security")
  private BroadcastSecurity security = new BroadcastSecurity();
  @JsonProperty("SWAP_SECURITY_MAIN")
  private BroadcastSecurity mainSecurity = null;
  @JsonProperty("SWAP_SECURITY_LEG1")
  private BroadcastSecurity securityLeg1 = null;
  @JsonProperty("SWAP_SECURITY_LEG2")
  private BroadcastSecurity securityLeg2 = null;

  @JsonProperty("Order")
  private BroadcastOrder order;
  private List<BroadcastAllocation> allocations = new ArrayList<>();

  public CRDBroadCastEvent(long orderId) {
    this.orderId = String.valueOf(orderId);
  }

  public void withOrder(String orderId) {
    if (StringUtils.equals(orderId, "5051098225")) {
      this.order = new BroadcastOrder("TS_ORDER", "5051098225", null, "MKT", "P", "A", "OTC", "SBIANCHI", "SBIANCHI",
          "SBIANCHI", "TISAPI", null, "BUYL", "SWPTN", "ACCT", "DTC",
          "BOFA0", "MULTI (3)", 100000000, 1300000, "702089284",
          0, "USD", 130, 1, "USD", "BANK OF AMERICA NA OTC",
          "BOFA0");
    } else {
      this.order = new BroadcastOrder();
    }
  }

  public void withAllocations(BroadcastAllocation broadcastAllocation) {
    this.allocations.add(broadcastAllocation);
  }
}