package com.frk.crd.db.dao;

import com.frk.crd.model.IAllocation;

import java.util.List;

public interface DBBroadcastAllocationRepository {
  List<IAllocation> getAllocations(String orderId);
}