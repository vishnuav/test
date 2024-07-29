package com.frk.crd.db.dao.impl;

import com.frk.crd.db.configuration.CRDDBSyncConfiguration;
import com.frk.crd.db.dao.DBBroadcastAllocationRepository;
import com.frk.crd.model.IAllocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "dev2")
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBBroadcastAllocationRepositoryImpl.class})
class DBAllocationRepositoryImplTest {
  @Autowired
  private DBBroadcastAllocationRepository repository;

  @Test
  void testAllocations() {
    Assertions.assertNotNull(repository);
    String orderId = "5047391070";
    List<IAllocation> allocations = repository.getAllocations(orderId);
    Assertions.assertNotNull(allocations);
    Assertions.assertEquals(2, allocations.size());

    IAllocation allocation = allocations.get(0);
    Assertions.assertNotNull(allocation);
    String cps = "CPS";
    Assertions.assertEquals("5047391070", allocation.getOrderId());
    Assertions.assertEquals(1.0E7, allocation.getTargetQuantity());
    Assertions.assertEquals(0, allocation.getTargetNotionalAmount());
    Assertions.assertEquals(200.0, allocation.getExecutionPx());
    Assertions.assertEquals(1.0E7, allocation.getExecutionQuantity());
    Assertions.assertEquals(200000.0, allocation.getExecutionAmount());

    Assertions.assertEquals(200000.0, allocation.getTargetAmount());
    Assertions.assertEquals(200000.0, allocation.getNetMoney());
    Assertions.assertEquals(cps, allocation.getCommissionIndicator());
    Assertions.assertEquals("TEST230", allocation.getAccountCode());
    Assertions.assertEquals(0.0, allocation.getCommissionAmount());

    allocation = allocations.get(1);
    Assertions.assertNotNull(allocation);
    Assertions.assertEquals(5000000.0, allocation.getTargetQuantity());
    Assertions.assertEquals(0, allocation.getTargetNotionalAmount());
    Assertions.assertEquals(200.0, allocation.getExecutionPx());
    Assertions.assertEquals(5000000.0, allocation.getExecutionQuantity());
    Assertions.assertEquals(100000.0, allocation.getExecutionAmount());
    Assertions.assertEquals(100000.0, allocation.getTargetAmount());
    Assertions.assertEquals(100000.0, allocation.getNetMoney());
    Assertions.assertEquals(cps, allocation.getCommissionIndicator());
    Assertions.assertEquals("Test55", allocation.getAccountCode());
    Assertions.assertEquals(0.0, allocation.getCommissionAmount());
  }
}