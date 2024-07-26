package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBBroadcastOrder;

public interface DBBroadcastOrderRepository {
  DBBroadcastOrder getOrder(String orderId);
}