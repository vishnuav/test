package com.frk.crd.model;

import java.util.List;

public interface IOrder {
  long getOrderId();

  List<Allocation> getAllocations();
}