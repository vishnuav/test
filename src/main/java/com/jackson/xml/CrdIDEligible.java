package com.jackson.xml;

import com.fasterxml.jackson.annotation.JsonAlias;

public interface CrdIDEligible extends XMLParsingEligible {
  @JsonAlias("crdId")
  long getCrdId();
}