package com.frk.crd.model;

import com.frk.crd.broadcast.BroadcastAllocation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class BroadcastAllocationTest {
  @Test
  void serializeFromWFRule() {
    BroadcastAllocation allocation = new BroadcastAllocation();
    log.info(allocation.toXML());
    log.info(allocation.toJson());
  }
}