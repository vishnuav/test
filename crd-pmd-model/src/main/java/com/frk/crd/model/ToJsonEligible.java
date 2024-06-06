package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frk.crd.converter.CustomJsonMessageConverter;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ToJsonEligible extends CRDEligible {
  default String toJson() {
    return CustomJsonMessageConverter.toJson(this);
  }
}