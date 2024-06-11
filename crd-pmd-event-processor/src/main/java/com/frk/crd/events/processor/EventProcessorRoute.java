package com.frk.crd.events.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class EventProcessorRoute extends RouteBuilder {
  public static final String IBMMQ_COMPONENT = "ibmmq";
  public static final String ACTIVEMQ_COMPONENT = "activemq";
  public static final String COMPONENT_SEPARATOR = ":";
  private final String inQueue;
  private final String outQueue;
  private final ConnectionFactory connectionFactory;

  public EventProcessorRoute(@Value("${crd.app.in.queue}") String inQueue, @Value("${crd.app.out.queue}") String outQueue,
                             ConnectionFactory connectionFactory) {
    this.inQueue = inQueue;
    this.outQueue = outQueue;
    this.connectionFactory = connectionFactory;
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
    getContext().addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    from("activemq:" + inQueue)
      .to("log:com.frk.crd?level=INFO&groupSize=10")
      .log("Found message in queue " + inQueue);
  }
}