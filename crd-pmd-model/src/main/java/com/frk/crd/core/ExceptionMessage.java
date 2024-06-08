package com.frk.crd.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage implements IExceptionMessage {
  private long id;
  private String sourceName;
  private String destinationType;
  private String destinationURL;
  private String payload;
  private String status;
  private long exceptionDateTime;

  public ExceptionMessage(IExceptionMessage message) {
    this(message.getId(), message.getSourceName(), message.getDestinationType(), message.getDestinationURL(), message.getPayload(),
      message.getStatus(), message.getExceptionDateTime());
  }
}