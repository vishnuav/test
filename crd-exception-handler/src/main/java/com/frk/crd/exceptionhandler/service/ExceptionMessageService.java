package com.frk.crd.exceptionhandler.service;

import com.frk.crd.model.IExceptionMessage;
import java.util.List;

public interface ExceptionMessageService {
  List<IExceptionMessage> replayExceptionMessages(List<IExceptionMessage> messages);
}