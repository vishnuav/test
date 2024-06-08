package com.frk.crd.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class StringNullBlankAwareDeserializer extends StdDeserializer<String> {

  public StringNullBlankAwareDeserializer() {
    this(null);
  }

  public StringNullBlankAwareDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    if (node == null) {
      return null;
    }
    String given = node.asText();
    String actual = StringUtils.isBlank(given) || StringUtils.equalsIgnoreCase(given, "null") ? null : given;
    return actual;
  }
}