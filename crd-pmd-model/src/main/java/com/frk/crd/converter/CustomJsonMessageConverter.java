package com.frk.crd.converter;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.frk.crd.utilities.CRDConstants;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CustomJsonMessageConverter {
  private final static ObjectMapper jsonMapper;
  private final StringBuilder messageBuilder = new StringBuilder();

  static {
    jsonMapper = new ObjectMapper();
    JsonMapper build = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .build();
    jsonMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    SimpleModule simpleModule = new SimpleModule();
    SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
    simpleModule.setAbstractTypes(resolver);
    jsonMapper.registerModule(simpleModule);
  }

  public static <T> Optional<T> fromJson(String json, Class<T> clazz) {
    try {
      return StringUtils.isEmpty(json)
          ? Optional.empty()
          : Optional.of(jsonMapper.readValue(clearUnParsableChars(json), clazz));
    } catch (Throwable e) {
      log.warn("Unable to generate class {} for {}", clazz == null ? null : clazz.getName(), json, e);
      return Optional.empty();
    }
  }

  public static <T extends Serializable> String toJson(T object) {
    String json = StringUtils.EMPTY;
    try {
      json = jsonMapper.writeValueAsString(object);
    } catch (Throwable e) {
      log.trace("Unable to generate json for {}", object, e);
    }
    return json == null ? StringUtils.EMPTY : json;
  }

  public static <T> List<T> convertToList(String jsonString, Class<T> target) {
    if (StringUtils.isEmpty(jsonString)) {
      return List.of();
    }
    try {
      return new ObjectMapper().readValue(jsonString, new ObjectMapper().getTypeFactory().
          constructCollectionType(List.class, target));
    } catch (Throwable e) {
      log.error("Unable to convert to list of {} from json {} ", target.getName(), jsonString, e);
      return List.of();
    }
  }

  public static <T> String convertToJson(List<T> input) {
    try {
      return jsonMapper.writeValueAsString(input);
    } catch (Throwable e) {
      log.error("Unable to convert to json {}", input, e);
    }
    return null;
  }

  static String clearUnParsableChars(final String text) {
    String[] empty = {StringUtils.EMPTY};
    byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
    String replaced = new String(bytes);
    replaced = replaced.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
    replaced = StringUtils.replaceEachRepeatedly(replaced, new String[]{"\\\\\""}, empty);
    replaced = StringUtils.replaceEachRepeatedly(replaced, new String[]{"'"}, empty);

    // Replace ,, with ,
    String[] comma = {CRDConstants.COMMA_SEPERATOR};
    replaced = StringUtils.replaceEachRepeatedly(replaced, new String[]{",,"}, comma);

    return replaced;
  }

  public static String prettyFormat(String input) {
    try {
      return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(input);
    } catch (IOException e) {
      log.error("Unable to Pretty print {}", input);
    }
    return input;
  }
}