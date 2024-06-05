package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Event implements XMLParsingEligible {
  @JsonAlias("inputEvent")
  private String inputEvent;
  private EventDetails eventDetails;
}