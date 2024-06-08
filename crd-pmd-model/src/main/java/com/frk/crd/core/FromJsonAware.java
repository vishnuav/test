package com.frk.crd.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frk.crd.converter.CustomJsonMessageConverter;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface FromJsonAware {
  default Optional<? extends FromJsonAware> fromJson(String json) {
    return CustomJsonMessageConverter.fromJson(json, getClass());
  }
}