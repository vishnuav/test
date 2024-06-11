package com.frk.crd.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frk.crd.converter.CustomJsonMessageConverter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface XMLParsingEligible extends Serializable {
  default String toXML() {
    return CustomJsonMessageConverter.toXML(this);
  }
}