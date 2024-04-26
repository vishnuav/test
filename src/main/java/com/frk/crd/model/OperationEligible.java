package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface OperationEligible extends XMLParsingEligible {
  @JsonAlias("op")
  String getOperation();
}
