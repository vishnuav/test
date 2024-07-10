package com.frk.crd.sync.service;

import com.frk.crd.core.IExceptionMessage;

import java.util.List;

public interface DBReadService {
  List<IExceptionMessage> fetchExceptionMessagesForDate(long exceptionDate);

  List<IExceptionMessage> fetchAllExceptionMessages();
}