package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBBroadcastAllocation;
import java.util.List;

public interface DBBroadcastAllocationRepository {
  List<DBBroadcastAllocation> getAllocations(String orderId);
}