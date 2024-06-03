package com.frk.crd.model;

import java.time.Instant;
import java.time.ZoneId;

public interface IExceptionMessage extends JsonAware {

  String getSourceName();

  String getDestinationType();

  String getDestinationURL();

  String getPayload();

  String getStatus();

  default long getExceptionDate() {
    ZoneId zoneId = ZoneId.of("America/New_York");
    return Instant.ofEpochMilli(getExceptionDateTime()).atZone(zoneId).toLocalDateTime().withHour(0)
        .withSecond(0).withMinute(0).withNano(0).atZone(zoneId).toInstant().toEpochMilli();
  }

  long getExceptionDateTime();

  long getId();
}