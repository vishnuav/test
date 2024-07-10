package com.frk.crd.events.route;

import com.frk.crd.events.processor.EventProcessor;
import com.frk.crd.jms.model.JMSComponentBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class EventProcessorRoute extends RouteBuilder {
  private final String inQueue;
  private final String outQueue;
  private final EventProcessor eventProcessor;

  private final JMSComponentBean jmsComponentBean;
  private final ConnectionFactory connectionFactory;

  public EventProcessorRoute(@Value("${crd.app.in.queue}") String inQueue, @Value("${crd.app.out.queue}") String outQueue,
                             EventProcessor eventProcessor, JMSComponentBean jmsComponentBean, ConnectionFactory connectionFactory) {
    this.inQueue = inQueue;
    this.outQueue = outQueue;
    this.eventProcessor = eventProcessor;
    this.jmsComponentBean = jmsComponentBean;
    this.connectionFactory = connectionFactory;
    log.info("Activating {} {}", jmsComponentBean.getClass().getSimpleName(), jmsComponentBean.componentInfo());
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
    getContext().addComponent(jmsComponentBean.componentInfo(), JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    from(jmsComponentBean.routeInfo() + inQueue)
      .bean(eventProcessor, "enrich")
      .to("log:com.frk.crd?level=INFO&groupSize=10")
      .log("Found message in queue " + inQueue);
  }
}