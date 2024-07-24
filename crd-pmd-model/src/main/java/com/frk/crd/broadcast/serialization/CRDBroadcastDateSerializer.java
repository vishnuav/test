package com.frk.crd.broadcast.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CRDBroadcastDateSerializer extends JsonSerializer<Long> {
  @Override
  public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    jsonGenerator.writeString(dateFormat.format(new Date()));
  }
}