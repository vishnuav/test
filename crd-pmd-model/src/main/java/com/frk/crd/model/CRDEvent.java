package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.frk.crd.core.XMLParsingEligible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName("CRDEvents")
public class CRDEvent implements XMLParsingEligible {
  @JsonAlias("inputEvent")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String inputEvent;
  @JsonAlias("eventDetails")
  private Order order;
}