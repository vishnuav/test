package com.frk.crd.exceptionhandler.rest;

import com.frk.crd.model.IExceptionMessage;
import com.frk.crd.utilities.DiscoveryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ExceptionProcessorRestController {
  @PostMapping(path = DiscoveryService.REPLAY_EXCEPTION_MESSAGE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Void> replayExceptionMessage(@RequestBody IExceptionMessage message);

  @PostMapping(path = "test", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Void> testReplay(@RequestBody String message);
}
