package com.frk.crd.model;

import com.frk.crd.broadcast.BroadcastAllocation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
class BroadcastAllocationTest {
  private final String expected = "<CRDEvents><Order><Id>5041248783</Id><OriginalId>0</OriginalId><RefId>WARR-Order-06-05-2024</RefId><Status>PREOK</Status><TargetQty>40</TargetQty><Manager>RDONATE</Manager></Order></CRDEvents>";

  @Test
  void serializeFromWFRule() throws IOException {
    BroadcastAllocation allocation = new BroadcastAllocation();
    log.info(allocation.toXML());
    log.info(allocation.toJson());
  }
}