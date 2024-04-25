package com.jackson.xml;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CRDBroadcastExec implements XMLParsingEligible {
  private Order order;
  private List<Allocation> allocations = new ArrayList<>();

  public List<Allocation> getAllocations() {
    return allocations;
  }

  @JsonAlias("allocation")
  public void setAllocations(List<Allocation> allocations) {
    if (allocations != null && !allocations.isEmpty()) {
      this.allocations.addAll(allocations);
    }
  }
}