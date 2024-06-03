package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frk.crd.converter.converter.CustomJsonMessageConverter;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface FromJsonAware extends CrdIDEligible {

  default Optional<? extends FromJsonAware> fromJson(String json) {
    return CustomJsonMessageConverter.fromJson(json, getClass());
  }
}