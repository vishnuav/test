package com.frk.crd.camel;

import com.frk.crd.model.WFEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class CamelRoute extends RouteBuilder {
  public static final String IBMMQ_COMPONENT = "ibmmq";
  public static final String ACTIVEMQ_COMPONENT = "activemq";
  public static final String COMPONENT_SEPARATOR = ":";
  private final String inQueue;
  private final String outQueue;
  private final ConnectionFactory connectionFactory;

  public CamelRoute(@Value("${crd.app.in.queue}") String inQueue, @Value("${crd.app.out.queue}") String outQueue,
                    ConnectionFactory connectionFactory) {
    this.inQueue = inQueue;
    this.outQueue = outQueue;
    this.connectionFactory = connectionFactory;
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
    getContext().addComponent(ACTIVEMQ_COMPONENT, JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    from(ACTIVEMQ_COMPONENT + COMPONENT_SEPARATOR + inQueue)
      .unmarshal().json(JsonLibrary.Jackson, WFEvent.class)
      .choice()
      .when().simple("${body.inputEvent} == 'PDP-ORDER-STATUS'").setHeader("CRDEvent", () -> "PDP-ORDER-STATUS")
      .otherwise().setHeader("CRDEvent", () -> "Not-Available")
      .to(ACTIVEMQ_COMPONENT + COMPONENT_SEPARATOR + outQueue)
      .to("log:com.frk.crd?level=INFO&groupSize=10");

    from(ACTIVEMQ_COMPONENT + COMPONENT_SEPARATOR + outQueue).to("log:com.frk.crd?level=INFO&groupSize=10")
      .onException(Throwable.class).process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
          log.error("Unable to process message {}", exchange.getIn(), exchange.getException());

          // send to exception queue
          // update message in DB as failed status
        }
      });

//        onException()
//        from("activemq:" + inQueue)
//                .unmarshal().jacksonxml()
//                .to("log:com.frk.crd?level=INFO&groupSize=10");
  }
}