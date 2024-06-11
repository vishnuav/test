package com.frk.crd.events.configuration;

import com.frk.crd.jms.configuration.CRDActiveMQConfiguration;
import com.frk.crd.jms.configuration.CRDIMBMQConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
@Import({CRDActiveMQConfiguration.class, CRDIMBMQConfiguration.class})
public class EventProcessorConfiguration {
}
