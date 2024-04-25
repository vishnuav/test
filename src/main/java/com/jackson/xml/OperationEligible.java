package com.jackson.xml;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface OperationEligible extends XMLParsingEligible {
  @JsonAlias("op")
  String getOperation();
}
