package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBOrder;

public interface DBBroadcastOrderRepository {
  DBOrder getOrder(String orderId);
}