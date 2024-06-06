package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frk.crd.converter.CustomJsonMessageConverter;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface FromJsonAware extends CRDEligible {
  default Optional<? extends FromJsonAware> fromJson(String json) {
    return CustomJsonMessageConverter.fromJson(json, getClass());
  }
}