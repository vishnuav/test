package com.jackson.xml.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class ISOFormatDateToEpochDeserializer extends StdDeserializer<Long> implements ContextualDeserializer {
  String UTC_DATE_TIME_FORMAT = "uuuu-MM-dd'T'HH:mm:ss'Z'";
  String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/New_York");

  public ISOFormatDateToEpochDeserializer() {
    super(Long.class);
  }

  public ISOFormatDateToEpochDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Long deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
    String value = jsonparser.getText();
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      log.trace("{} is not a number - attempting to parse for a predefined date format", value);
      LocalDateTime localDateTime = parseToLocalDateTime(value, DATE_TIME_FORMAT);
      log.trace("Parsed {} as {}", value, localDateTime);
      return toEpochMillis(localDateTime);
    }
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
    return this;
  }

  LocalDateTime parseToLocalDateTime(String dateTimeString, String pattern) {
    if (StringUtils.isBlank(dateTimeString) || StringUtils.isBlank(pattern)) {
      return null;
    } else {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
      try {
        return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
      } catch (Exception e) {
        log.error("Unable to format {} as {}", dateTimeString, pattern);
        return null;
      }
    }
  }

  long toEpochMillis(LocalDateTime localDateTime) {
    return localDateTime == null ? 0 : localDateTime.toInstant(DEFAULT_ZONE_ID.getRules().getOffset(localDateTime)).toEpochMilli();
  }
}