package com.jackson.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UDF implements XMLParsingEligible {
  private String type;
  private int id;
  private double value;
}