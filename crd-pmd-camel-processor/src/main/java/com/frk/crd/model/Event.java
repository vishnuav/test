package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event implements XMLParsingEligible {
  @JsonAlias("inputEvent")
  private String inputEvent;
  @JsonAlias("eventDetails")
  private EventDetails eventDetails;
}