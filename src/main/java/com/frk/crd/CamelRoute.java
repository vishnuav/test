package com.frk.crd;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
public class CamelRoute extends RouteBuilder {
    private final String inQueue;
    private final String outQueue;
    private final ConnectionFactory connectionFactory;

    public CamelRoute(@Value("${application.in.queue}") String inQueue,
                      @Value("${application.out.queue}") String outQueue,
                      ConnectionFactory connectionFactory) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void configure() {
        getContext().setTypeConverterStatisticsEnabled(true);
        getContext().addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        from("activemq:" + inQueue).to("activemq:" + outQueue);
        from("activemq:" + outQueue).to("log:com.frk.crd?level=INFO&groupSize=10");
//        onException()


//        from("activemq:" + inQueue)
//                .unmarshal().jacksonxml()
//                .to("log:com.frk.crd?level=INFO&groupSize=10");
    }
}