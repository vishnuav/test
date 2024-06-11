package com.frk.crd.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import com.frk.crd.jms.configuration.CRDActiveMQConfiguration;
import com.frk.crd.jms.configuration.CRDJMSConfiguration;
import com.frk.crd.interceptor.HeaderInterceptor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.impl.DefaultMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@ActiveProfiles(value = "local")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CRDJMSConfiguration.class, CRDActiveMQConfiguration.class, HeaderInterceptor.class})
class HeaderInterceptorTest {
  public static final String CRD_EVENT_HEADER = "CRDEvent";
  @Autowired
  private HeaderInterceptor headerInterceptor;

  @Test
  void intercept() {
    final String validHeaderMessage = "<event><inputEvent>PDP-ORDER-STATUS</inputEvent></event>";

    Assertions.assertNotNull(headerInterceptor);
    CamelContext context = new DefaultCamelContext();

    Message message = new DefaultMessage(context);
    message.setBody(validHeaderMessage);
    Exchange exchange = new DefaultExchange(context);
    exchange.setIn(message);
    headerInterceptor.intercept(exchange);

    Assertions.assertNotNull(exchange);
    Map<String, Object> headers = exchange.getOut().getHeaders();
    Assertions.assertNotNull(headers);
    Assertions.assertTrue(headers.containsKey(CRD_EVENT_HEADER));
    Object eventHeader = headers.get(CRD_EVENT_HEADER);
    CRDEvent event = CustomJsonMessageConverter.fromXML(validHeaderMessage, CRDEvent.class).orElse(null);
    Assertions.assertNotNull(event);
    Assertions.assertEquals(event.getInputEvent(), eventHeader);
  }
}