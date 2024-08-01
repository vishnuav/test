package com.frk.crd.db.service.impl;

import com.frk.crd.core.ExceptionMessage;
import com.frk.crd.core.IExceptionMessage;
import com.frk.crd.db.dao.DBBroadcastAllocationRepository;
import com.frk.crd.db.dao.DBBroadcastOrderRepository;
import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.dao.ExceptionMessageRepository;
import com.frk.crd.db.model.DBExceptionMessage;
import com.frk.crd.model.IOrder;
import com.frk.crd.db.service.DBReadService;
import com.frk.crd.model.IAllocation;
import com.frk.crd.model.ISecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DBReadServiceImpl implements DBReadService {
  private final ExceptionMessageRepository exceptionMessageRepository;
  private final DBBroadcastAllocationRepository allocationRepository;
  private final DBBroadcastOrderRepository orderRepository;
  private final DBBroadcastSecurityRepository securityRepository;

  public DBReadServiceImpl(ExceptionMessageRepository exceptionMessageRepository, DBBroadcastAllocationRepository allocationRepository,
                           DBBroadcastOrderRepository orderRepository, DBBroadcastSecurityRepository securityRepository) {
    this.exceptionMessageRepository = exceptionMessageRepository;
    this.allocationRepository = allocationRepository;
    this.orderRepository = orderRepository;
    this.securityRepository = securityRepository;
  }

  @Override
  public List<IExceptionMessage> fetchAllExceptionMessages() {
    List<DBExceptionMessage> allNewExceptionMessages = exceptionMessageRepository.findAllNewExceptionMessages();
    return allNewExceptionMessages.stream().map(ExceptionMessage::new).collect(Collectors.toList());
  }

  @Override
  public List<IExceptionMessage> fetchExceptionMessagesForDate(long exceptionDate) {
    return fetchAllExceptionMessages().stream().filter(v -> v.getExceptionDate() > exceptionDate).collect(Collectors.toList());
  }

  @Override
  public ISecurity fetchSecurityForOrderId(String orderId) {
    return securityRepository.getSecurityForOrderId(orderId);
  }

  @Override
  public IOrder fetchOrder(String orderId) {
    return orderRepository.getOrder(orderId);
  }

  @Override
  public List<IAllocation> fetchAllocations(String orderId) {
    return allocationRepository.getAllocations(orderId);
  }

  @Override
  public List<String> fetchChildSecurities(String secId) {
    return securityRepository.getChildSecurities(secId);
  }
}