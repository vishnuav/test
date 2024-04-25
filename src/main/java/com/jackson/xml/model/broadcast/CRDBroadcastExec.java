package com.jackson.xml.model.broadcast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CRDBroadcastExec implements XMLParsingEligible {
  private Order order;
}