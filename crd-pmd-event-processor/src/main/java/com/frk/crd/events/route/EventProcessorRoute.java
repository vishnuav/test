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
  private final String pmdInQueue;
  private final String pmdOutQueue;
  private final String chubInQueue;
  private final String chubOutQueue;
  private final EventProcessor eventProcessor;

  private final JMSComponentBean jmsComponentBean;
  private final ConnectionFactory connectionFactory;

  public EventProcessorRoute(@Value("${crd.app.pmd.in.queue}") String pmdInQueue, @Value("${crd.app.pmd.in.queue}") String pmdOutQueue,
                             @Value("${crd.app.chub.in.queue}") String chubInQueue, @Value("${crd.app.chub.in.queue}") String chubOutQueue,
                             EventProcessor eventProcessor, JMSComponentBean jmsComponentBean, ConnectionFactory connectionFactory) {
    this.pmdInQueue = pmdInQueue;
    this.pmdOutQueue = pmdOutQueue;
    this.chubInQueue = chubInQueue;
    this.chubOutQueue = chubOutQueue;
    this.eventProcessor = eventProcessor;
    this.jmsComponentBean = jmsComponentBean;
    this.connectionFactory = connectionFactory;
    log.info("Activating {} {}", jmsComponentBean.getClass().getSimpleName(), jmsComponentBean.componentInfo());
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
//    getContext().addComponent(jmsComponentBean.componentInfo(), JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
//
//    // CRD to Putnam Route
//    from(jmsComponentBean.routeInfo() + pmdInQueue)
//      .bean(eventProcessor, "process")
//      .to("log:com.frk.crd?level=INFO&groupSize=10")
//      .log("Found message in queue " + pmdInQueue);
//
//    // CRD to Contract Hub Route
//    from(jmsComponentBean.routeInfo() + pmdInQueue)
//      .bean(eventProcessor, "process")
//      .to("log:com.frk.crd?level=INFO&groupSize=10")
//      .log("Found message in queue " + pmdInQueue);
  }
}