package com.frk.crd.db.rest;

import com.frk.crd.core.IExceptionMessage;
import com.frk.crd.model.IOrder;
import com.frk.crd.model.IAllocation;
import com.frk.crd.model.ISecurity;
import com.frk.crd.utilities.DiscoveryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

public interface DBServicesRestController {
  @PostMapping(path = DiscoveryService.SYNC_EXCEPTION_MESSAGE, produces = MediaType.APPLICATION_JSON_VALUE)
  default ResponseEntity<Integer> syncMessage(@RequestBody IExceptionMessage message) {
    return syncMessages(List.of(message));
  }

  @PostMapping(path = DiscoveryService.SYNC_EXCEPTION_MESSAGES, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Integer> syncMessages(@RequestBody Collection<IExceptionMessage> messages);

  @GetMapping(path = DiscoveryService.FETCH_ALL_EXCEPTION_MESSAGES, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<IExceptionMessage>> fetchAllExceptionMessages();

  @GetMapping(path = DiscoveryService.FETCH_EXCEPTION_MESSAGES_FOR_DATE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<IExceptionMessage>> fetchExceptionMessagesForDate(long exceptionDate);

  @GetMapping(path = DiscoveryService.GET_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<IOrder> getOrder(@RequestParam("orderId") String orderId);

  @GetMapping(path = DiscoveryService.GET_ALLOCATIONS)
  ResponseEntity<List<IAllocation>> getAllocations(@RequestParam("orderId") String orderId);

  @GetMapping(path = DiscoveryService.GET_SECURITY)
  ResponseEntity<ISecurity> getSecurity(@RequestParam("secId") String secId);

  @GetMapping(path = DiscoveryService.GET_CHILD_SECURITIES)
  ResponseEntity<List<String>> getChildSecurities(@RequestParam("secId") String secId);
}