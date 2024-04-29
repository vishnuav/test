package com.frk.crd.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CRDUtils {
  public static final String COMMA_SEPARATOR = ",";
  public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/New_York");
  public static final String MMM_DD_YYYY = "MMM-dd-YYYY";
  public static final String UTC_DATE_TIME_FORMAT = "uuuu-MM-dd'T'HH:mm:ss'Z'";
  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

  public static LocalDateTime convertDateToLocalDateTime(long dateTime) {
    return Instant.ofEpochMilli(dateTime).atZone(DEFAULT_ZONE_ID).toLocalDateTime();
  }

  public static LocalDate convertDateToLocalDate(long dateTime) {
    return Instant.ofEpochMilli(dateTime).atZone(DEFAULT_ZONE_ID).toLocalDate();
  }

  public static String epochToStr(long epoch) {
    return String.format("%s-%s", epoch, Instant.ofEpochMilli(epoch).atZone(DEFAULT_ZONE_ID).toString());
  }

  public static long toEpochMillis(LocalDateTime localDateTime) {
    return localDateTime
      .toInstant(DEFAULT_ZONE_ID.getRules().getOffset(localDateTime))
      .toEpochMilli();
  }

  public static long toEpochMillis(LocalDate localDate) {
    return localDate.atStartOfDay(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
  }

  public static LocalDateTime getLocalDateTime(long datetime) {
    Instant instant = Instant.ofEpochMilli(datetime);
    return instant.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
  }

  public static long removeSeconds(long candleDateTime) {
    final Instant instant = Instant.ofEpochMilli(candleDateTime);
    final LocalDateTime localDateTime = instant.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    return localDateTime
      .withSecond(0)
      .withNano(0)
      .atZone(DEFAULT_ZONE_ID)
      .toInstant()
      .toEpochMilli();
  }

  public static long removeMinutes(long candleDateTime) {
    final Instant instant = Instant.ofEpochMilli(candleDateTime);
    final LocalDateTime localDateTime = instant.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    return localDateTime
      .withMinute(0)
      .withSecond(0)
      .withNano(0)
      .atZone(DEFAULT_ZONE_ID)
      .toInstant()
      .toEpochMilli();
  }

  public static long removeHours(long from) {
    if (from == 0) {
      return 0;
    }
    final Instant instant = Instant.ofEpochMilli(from);
    LocalDateTime localDateTime = instant.atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    localDateTime = localDateTime.getHour() == 0 ? localDateTime : localDateTime.withHour(0);
    return localDateTime
      .withHour(0)
      .withMinute(0)
      .withSecond(0)
      .withNano(0)
      .atZone(DEFAULT_ZONE_ID)
      .toInstant()
      .toEpochMilli();
  }

  public static String getTimeInMillisSecondsMinutes(StopWatch stopWatch) {
    return stopWatch == null
      ? StringUtils.EMPTY
      : String.format("%s millis, %s seconds, %s minutes", stopWatch.getTime(TimeUnit.MILLISECONDS),
      stopWatch.getTime(TimeUnit.SECONDS), stopWatch.getTime(TimeUnit.MINUTES));
  }

  public static String getTimeInMillisAndSeconds(StopWatch stopWatch) {
    return stopWatch == null
      ? StringUtils.EMPTY
      : String.format("%s millis, %s seconds", stopWatch.getTime(TimeUnit.MILLISECONDS), stopWatch.getTime(TimeUnit.SECONDS));
  }

  public static String getTimeInMillis(StopWatch stopWatch) {
    return stopWatch == null
      ? StringUtils.EMPTY
      : String.format("%s millis", stopWatch.getTime(TimeUnit.MILLISECONDS));
  }

  public static LocalDateTime parseToLocalDateTime(String dateTimeString, String pattern) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
    try {
      return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
    } catch (Exception e) {
      log.error("Unable to format {} as {}", dateTimeString, pattern);
      return null;
    }
  }
}