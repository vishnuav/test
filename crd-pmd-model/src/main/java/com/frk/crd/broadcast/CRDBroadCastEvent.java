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

  @JsonProperty("SECURITY")
  private BroadcastSecurity security = new BroadcastSecurity();
  @JsonProperty("SWAP_SECURITY_MAIN")
  private BroadcastSecurity mainSecurity = null;
  @JsonProperty("SWAP_SECURITY_LEG1")
  private BroadcastSecurity securityLeg1 = null;
  @JsonProperty("SWAP_SECURITY_LEG2")
  private BroadcastSecurity securityLeg2 = null;

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

  public void withSecurity(String orderId) {
    if (StringUtils.equals(orderId, "5051098225")) {
      this.security = new BroadcastSecurity("CSM_SECURITY", "USD", null, null,
        "US", null, null, "5051098226", "BOFA0", "Bank of America N.A.", null,
        null, "USD", null, "EURO", "296290",
        "111_EXCHS", null, "SWPTNCDI", "1Y", "P", "5051098227",
        "5051098227", "N", "N", 130.0, 0, 0, 0,
        0, 0, 0);
      this.mainSecurity = new BroadcastSecurity("CSM_SECURITY", "USD", null, null,
        "US", null, null, "5051098226", "BOFA0", "Bank of America N.A.", null,
        null, "USD", null, "EURO", "296290",
        "111_EXCHS", null, "SWPTNCDI", "1Y", "P", "5051098227",
        "5051098227", "N", "N", 130.0, 0, 0, 0,
        0, 0, 0);
      this.securityLeg1 = new BroadcastSecurity("CSM_SECURITY", "USD", null, null,
        "US", null, null, "5051098226", "BOFA0", "Bank of America N.A.", null,
        null, "USD", null, "EURO", "296290",
        "111_EXCHS", null, "SWPTNCDI", "1Y", "P", "5051098227",
        "5051098227", "N", "N", 130.0, 0, 0, 0,
        0, 0, 0);
      this.securityLeg2 = new BroadcastSecurity("CSM_SECURITY", "USD", null, null,
        "US", null, null, "5051098226", "BOFA0", "Bank of America N.A.", null,
        null, "USD", null, "EURO", "296290",
        "111_EXCHS", null, "SWPTNCDI", "1Y", "P", "5051098227",
        "5051098227", "N", "N", 130.0, 0, 0, 0,
        0, 0, 0);
    } else {
      this.security = new BroadcastSecurity();
    }
  }
}