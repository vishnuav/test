package com.frk.crd.broadcast.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CRDBroadcastDateDeSerializer extends JsonDeserializer<Long> {
  @Override
  public Long deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
    String textValue = parser.getText();
    return null;
  }
}