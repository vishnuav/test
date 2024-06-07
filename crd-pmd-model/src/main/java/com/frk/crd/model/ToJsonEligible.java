package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frk.crd.converter.CustomJsonMessageConverter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ToJsonEligible extends Serializable {
  default String toJson() {
    return CustomJsonMessageConverter.toJson(this);
  }
}