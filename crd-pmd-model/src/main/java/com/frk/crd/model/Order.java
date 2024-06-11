package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.frk.crd.core.XMLParsingEligible;
import com.frk.crd.deserializer.StringNullBlankAwareDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order implements XMLParsingEligible {
  private long id;
  @Setter
  private long originalId;
  private String refId;
  private String status;
  private int targetQty;

  @JsonDeserialize(using = StringNullBlankAwareDeserializer.class)
  private String manager;

  // Read only fields
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private int execQty;
  @JsonDeserialize(using = StringNullBlankAwareDeserializer.class)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String trader;
  @JsonDeserialize(using = StringNullBlankAwareDeserializer.class)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String execBroker;
  @JsonDeserialize(using = StringNullBlankAwareDeserializer.class)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String fillBroker;

  private final List<Allocation> allocations = new ArrayList<>();

  @JsonAlias("allocation")
  public void setAllocations(Allocation allocation) {
    if (allocation != null) {
      this.allocations.add(allocation);
    }
  }

  public void addAllocations(List<Allocation> allocations) {
    if (ObjectUtils.isNotEmpty(allocations)) {
      this.allocations.addAll(allocations);
    }
  }
}