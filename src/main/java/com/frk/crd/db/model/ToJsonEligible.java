package com.frk.crd.db.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ToJsonEligible extends CRDEligible {

  default String toJson() {
    return CustomJsonMessageConverter.toJson(this);
  }
}