package com.frk.crd.exceptionhandler.service.impl;

import com.frk.crd.exceptionhandler.service.ExceptionMessageService;
import com.frk.crd.core.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;

@Slf4j
@Component
public class ExceptionMessageServiceImpl implements ExceptionMessageService {
  @Override
  public void replayExceptionMessages(IExceptionMessage message) {
    WebClient webClient = WebClient.builder().baseUrl(message.getDestinationURL())
//      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
    String payload = message.getPayload();
    webClient.post().bodyValue(BodyInserters.fromValue(payload)).retrieve()
      .toBodilessEntity()
      .subscribe(
        responseEntity -> {
          HttpStatus status = responseEntity.getStatusCode();
          URI location = responseEntity.getHeaders().getLocation();
          log.info("Status {} - successfully posted to {} with payload {}", status, message.getDestinationURL(), payload);
        },
        error -> {
          if (error instanceof WebClientResponseException) {
            WebClientResponseException ex = (WebClientResponseException) error;
            HttpStatus status = ex.getStatusCode();
            log.error("Error Status Code: " + status.value());
          } else {
            log.error("An unexpected error occurred: ", error);
          }
        }
      );
  }
}