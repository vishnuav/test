package com.frk.crd.events.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CRDBroadcastBooleanSerializer extends JsonSerializer<Boolean> {
  @Override
  public void serialize(Boolean value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
    if (value != null) {
      jsonGenerator.writeString(value ? "Y" : "N");
    }
  }
}