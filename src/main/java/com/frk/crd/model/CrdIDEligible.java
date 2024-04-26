package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface CrdIDEligible extends XMLParsingEligible {
  @JsonAlias("crdId")
  long getId();
}