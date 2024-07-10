package com.frk.crd.events.model;

import com.frk.crd.core.JsonAware;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CSMSecurity implements JsonAware {
  private String secId, secTypeCode;

  public boolean isSwaption() {
    return false;
  }
}