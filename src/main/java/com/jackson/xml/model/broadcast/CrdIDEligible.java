package com.jackson.xml.model.broadcast;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface CrdIDEligible extends XMLParsingEligible {
  @JsonAlias("crdId")
  long getId();
}