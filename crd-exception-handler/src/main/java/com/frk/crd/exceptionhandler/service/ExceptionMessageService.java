package com.frk.crd.exceptionhandler.service;

import com.frk.crd.model.IExceptionMessage;

public interface ExceptionMessageService {
  void replayExceptionMessages(IExceptionMessage message);
}