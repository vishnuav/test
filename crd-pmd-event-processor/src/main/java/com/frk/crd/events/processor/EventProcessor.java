package com.frk.crd.events.processor;

import org.apache.camel.Exchange;

public interface EventProcessor {
  void process(Exchange exchange);
}