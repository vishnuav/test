package com.frk.crd.enrichment;

import com.frk.crd.model.Allocation;

import java.util.List;

public class OriginalOrderIdEnricher {
  public String enrichOriginalOrderId(String referenceId) {
    // TODO: 6/10/24 Complete Me add functionality to enrich original order service
    return "OriginalOrderId";
  }

  public List<Allocation> withAllocation(String originalOrderId) {
    // TODO: 6/10/24 Complete Me add functionality to enrich with allocation based on original order id
    return List.of();
  }
}