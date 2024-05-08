package com.frk.crd.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class CamelRoute extends RouteBuilder {
  public static final String ACTIVEMQ_COMPONENT = "activemq:";
  private final String inQueue;
  private final String outQueue;
  private final String fileMessageDestination;
  private final ConnectionFactory connectionFactory;

  public CamelRoute(@Value("${crd.app.in.queue}") String inQueue, @Value("${crd.app.out.queue}") String outQueue,
                    @Value("${crd.app.file.destination}") String fileMessageDestination,
                    ConnectionFactory connectionFactory) {
    this.inQueue = inQueue;
    this.outQueue = outQueue;
    this.fileMessageDestination = fileMessageDestination;
    this.connectionFactory = connectionFactory;
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
    getContext()
//      .addComponent("messageFile", "")
      .addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
      ;
    from(ACTIVEMQ_COMPONENT + inQueue).to(ACTIVEMQ_COMPONENT + outQueue).to("file://" + fileMessageDestination);
    from(ACTIVEMQ_COMPONENT + outQueue).to("log:com.frk.crd?level=INFO&groupSize=10");
  }
}