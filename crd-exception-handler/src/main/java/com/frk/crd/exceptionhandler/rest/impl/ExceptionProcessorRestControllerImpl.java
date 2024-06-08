package com.frk.crd.exceptionhandler.rest.impl;

import com.frk.crd.exceptionhandler.rest.ExceptionProcessorRestController;
import com.frk.crd.exceptionhandler.service.ExceptionMessageService;
import com.frk.crd.core.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class ExceptionProcessorRestControllerImpl implements ExceptionProcessorRestController {
  private final ExceptionMessageService exceptionMessageService;

  public ExceptionProcessorRestControllerImpl(ExceptionMessageService exceptionMessageService) {
    this.exceptionMessageService = exceptionMessageService;
  }

  @Override
  public ResponseEntity<Void> replayExceptionMessage(IExceptionMessage message) {
    exceptionMessageService.replayExceptionMessages(message);
    return ResponseEntity.of(Optional.empty());
  }

  @Override
  public ResponseEntity<Void> testReplay(String message) {
    log.info("Replayed payload {}", message);
    return ResponseEntity.of(Optional.empty());
  }
}
