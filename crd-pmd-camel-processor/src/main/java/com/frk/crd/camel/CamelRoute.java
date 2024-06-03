package com.frk.crd.camel;

import javax.jms.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CamelRoute extends RouteBuilder {
  public static final String IBMMQ_COMPONENT = "ibmmq:";
  public static final String ACTIVEMQ_COMPONENT = "activemq";
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
    from(ACTIVEMQ_COMPONENT + ":" + inQueue).to("activemq:" + outQueue);
    from(ACTIVEMQ_COMPONENT + ":" + outQueue).to("log:com.frk.crd?level=INFO&groupSize=10")
        .onException(Throwable.class).process(new Processor() {
          @Override
          public void process(Exchange exchange) throws Exception {
            log.error("Unable to process message {}", exchange.getIn(), exchange.getException());

            // send to exception queue
            // update message in DB as failed status
          }
        })
    ;

//        onException()
//        from("activemq:" + inQueue)
//                .unmarshal().jacksonxml()
//                .to("log:com.frk.crd?level=INFO&groupSize=10");
  }
}