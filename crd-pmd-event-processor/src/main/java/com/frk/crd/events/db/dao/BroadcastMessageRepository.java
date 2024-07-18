package com.frk.crd.events.db.dao;

import com.frk.crd.events.model.BroadcastAllocation;
import com.frk.crd.events.model.BroadcastOrder;
import com.frk.crd.events.model.BroadcastSecurity;

import java.util.List;

public interface BroadcastMessageRepository {
  String fetchSecurityId(String orderId);

  List<BroadcastSecurity> fetchUnderlyingSecurityId(String secId);

  List<BroadcastSecurity> fetchSecurity(String orderId);

  BroadcastOrder fetchOrder(String orderId);

  List<BroadcastAllocation> fetchAllocations(String orderId);
}