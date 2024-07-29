package com.frk.crd.wf.wfrule;

import com.frk.crd.jms.model.JMSComponentBean;
import com.frk.crd.wf.interceptor.EnrichmentInterceptor;
import com.frk.crd.wf.interceptor.HeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Slf4j
@Component
public class WFRuleRoute extends RouteBuilder {
  private final String pmdInQueue;
  private final String pmdForwardQueue;
  private final String chubInQueue;
  private final String chubForwardQueue;
  private final String messageFolder;
  private final JMSComponentBean jmsComponentBean;
  private final ConnectionFactory connectionFactory;
  private final EnrichmentInterceptor enrichmentInterceptor;
  private final HeaderInterceptor headerInterceptor;

  public WFRuleRoute(@Value("${crd.app.pmd.in.queue}") String pmdInQueue, @Value("${crd.app.pmd.in.queue}") String pmdForwardQueue,
                     @Value("${crd.app.chub.in.queue}") String chubInQueue, @Value("${crd.app.chub.in.queue}") String chubForwardQueue,
                     @Value("${crd.app.message.folder}") String messageFolder, JMSComponentBean jmsComponentBean,
                     ConnectionFactory connectionFactory, HeaderInterceptor headerInterceptor, EnrichmentInterceptor enrichmentInterceptor) {
    this.pmdInQueue = pmdInQueue;
    this.pmdForwardQueue = pmdForwardQueue;
    this.chubInQueue = chubInQueue;
    this.chubForwardQueue = chubForwardQueue;
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
    // CRD to Putnam WF Rule Processor
    from(jmsComponentBean.routeInfo() + pmdInQueue)
      .log("Found message in queue " + pmdInQueue)
      .bean(enrichmentInterceptor, "enrich")
      .to(jmsComponentBean.routeInfo() + pmdForwardQueue)
      .to("log:com.frk.crd?level=INFO&groupSize=10")
      .to("file://" + messageFolder);

    // CRD to Contract Hub WF Rule Processor
    from(jmsComponentBean.routeInfo() + chubInQueue)
      .log("Found message in queue " + chubInQueue)
      .to(jmsComponentBean.routeInfo() + chubForwardQueue)
      .to("log:com.frk.crd?level=INFO&groupSize=10")
      .to("file://" + messageFolder);
  }
}