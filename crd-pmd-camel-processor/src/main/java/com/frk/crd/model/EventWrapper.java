package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EventWrapper implements XMLParsingEligible {
  @JsonAlias("event")
  private Event event;
}