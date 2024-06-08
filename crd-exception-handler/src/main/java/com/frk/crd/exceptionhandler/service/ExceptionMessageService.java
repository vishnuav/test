package com.frk.crd.exceptionhandler.service;

import com.frk.crd.core.IExceptionMessage;

public interface ExceptionMessageService {
  void replayExceptionMessages(IExceptionMessage message);
}