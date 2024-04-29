package com.frk.crd.db.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface FromJsonAware extends CRDEligible {

  default Optional<? extends FromJsonAware> fromJson(String json) {
    return CustomJsonMessageConverter.fromJson(json, getClass());
  }
}