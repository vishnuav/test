package com.frk.crd.rest;

import com.frk.crd.service.MessagePublishService;
import com.frk.crd.utilities.DiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageProducerController {
  private final MessagePublishService publishService;

  public MessageProducerController(MessagePublishService publishService) {
    this.publishService = publishService;
  }

  @PostMapping(path = DiscoveryService.PUBLISH_MESSAGE, produces = MediaType.TEXT_PLAIN_VALUE)
  public void publishMessage(@RequestParam("queueName") String queueName, @RequestBody String payload) {
    publishService.sendToQueue(queueName, payload);
  }
}