package com.frk.crd.wfrule;

import com.frk.crd.interceptor.EnrichmentInterceptor;
import com.frk.crd.interceptor.HeaderInterceptor;
import com.frk.crd.jms.model.JMSComponentBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class WFRuleRoute extends RouteBuilder {
  public static final String IBMMQ_COMPONENT = "ibmmq";
  public static final String ACTIVEMQ_COMPONENT = "activemq";
  public static final String COMPONENT_SEPARATOR = ":";
  private final String inQueue;
  private final String outQueue;
  private final String messageFolder;
  private final JMSComponentBean jmsComponentBean;
  private final ConnectionFactory connectionFactory;
  private final EnrichmentInterceptor enrichmentInterceptor;
  private final HeaderInterceptor headerInterceptor;

  public WFRuleRoute(@Value("${crd.app.in.queue}") String inQueue, @Value("${crd.app.out.queue}") String outQueue,
                     @Value("${crd.app.message.folder}") String messageFolder, JMSComponentBean jmsComponentBean,
                     ConnectionFactory connectionFactory, HeaderInterceptor headerInterceptor, EnrichmentInterceptor enrichmentInterceptor) {
    this.inQueue = inQueue;
    this.outQueue = outQueue;
    this.messageFolder = messageFolder;
    this.jmsComponentBean = jmsComponentBean;
    this.connectionFactory = connectionFactory;
    this.enrichmentInterceptor = enrichmentInterceptor;
    this.headerInterceptor = headerInterceptor;
  }

  @Override
  public void configure() {
    getContext().setTypeConverterStatisticsEnabled(true);
    getContext().addComponent(jmsComponentBean.componentInfo(), JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    from(jmsComponentBean.routeInfo() + inQueue)
      .log("Found message in queue " + inQueue)
      .bean(enrichmentInterceptor, "enrich")
      .to(jmsComponentBean.routeInfo() + outQueue)
      .to("log:com.frk.crd?level=INFO&groupSize=10")
      .to("file://" + messageFolder);
  }
}