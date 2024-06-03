package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Allocation implements CrdIDEligible, OperationEligible {
  private long id;
  private String operation;
  private String fundManager;
  @JsonAlias("targetQty")
  private double targetQuantity;
  @JsonAlias("execQty")
  private double executingQuantity;

  @JsonCreator
  public Allocation(@JsonProperty("crdId") long id, @JsonProperty("op") String operation, @JsonProperty("fundManager") String fundManager,
                    @JsonProperty("targetQty") double targetQuantity, @JsonProperty("execQty") double executingQuantity) {
    this.id = id;
    this.operation = operation;
    this.fundManager = fundManager;
    this.targetQuantity = targetQuantity;
    this.executingQuantity = executingQuantity;
  }
}