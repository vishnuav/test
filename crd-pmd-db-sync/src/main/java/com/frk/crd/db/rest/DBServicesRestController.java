package com.frk.crd.db.rest;

import com.frk.crd.utilities.DiscoveryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface DBServicesRestController {
  @PostMapping(path = DiscoveryService.SYNC_MESSAGES, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Integer> syncMessages(@RequestBody Collection<String> messages);
}