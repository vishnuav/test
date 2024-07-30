package com.frk.crd.broadcast;

import static org.junit.jupiter.api.Assertions.*;

import com.frk.crd.converter.CustomJsonMessageConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BroadcastAllocationTest {
  private final String allocationsFromDBJson = " {"
      + "        \"originalOrderId\": \"5051098225\","
      + "        \"orderId\": \"5051098225\","
      + "        \"targetQuantity\": 2.5813449E7,"
      + "        \"targetNotionalAmount\": 0.0,"
      + "        \"executionPx\": 130.0,"
      + "        \"executionQuantity\": 2.5813449E7,"
      + "        \"executionAmount\": 335574.84,"
      + "        \"targetAmount\": 335574.84,"
      + "        \"netMoney\": 335574.84,"
      + "        \"commissionIndicator\": \"CPS\","
      + "        \"accountCode\": \"TEST7\","
      + "        \"commissionAmount\": 0.0"
      + "    }";

  @Test
  void serializeFromDBAllocation() {
    BroadcastAllocation broadcastAllocation = CustomJsonMessageConverter.fromJson(allocationsFromDBJson, BroadcastAllocation.class).orElse(null);
    Assertions.assertNotNull(broadcastAllocation);
  }
}