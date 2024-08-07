package com.frk.crd.db.service;

import com.frk.crd.core.IExceptionMessage;
import com.frk.crd.model.IOrder;
import com.frk.crd.model.IAllocation;
import com.frk.crd.model.ISecurity;

import java.util.List;

public interface DBReadService {
  List<IExceptionMessage> fetchExceptionMessagesForDate(long exceptionDate);

  List<IExceptionMessage> fetchAllExceptionMessages();

  ISecurity fetchSecurityForOrderId(String orderID);

  IOrder fetchOrder(String orderId);

  List<IAllocation> fetchAllocations(String orderId);

  List<String> fetchChildSecurities(String secId);
}