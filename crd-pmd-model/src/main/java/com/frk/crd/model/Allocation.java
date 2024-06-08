package com.frk.crd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.frk.crd.core.XMLParsingEligible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Allocation implements XMLParsingEligible {
  @JsonAlias("Id")
  private long id;
  @JsonAlias("OriginalId")
  private String originalId;
  @JsonAlias("AccountCd")
  private String accountCd;
  @JsonAlias("TargetQty")
  private double targetQty;
}