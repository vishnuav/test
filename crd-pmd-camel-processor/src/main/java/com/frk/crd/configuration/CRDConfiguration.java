package com.frk.crd.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.frk.crd.utilities.DiscoveryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
public class CRDConfiguration {
//  @Value(value = "${crd.app.rest.connector.host}")
//  @Getter
//  protected String marketClientHost;
//  @Value(value = "${crd.app.rest.connector.port}")
  @Getter
  protected int marketClientPort;
  @Value("${spring.activemq.broker-url}")
  @Getter
  private String BROKER_URL;
  @Value("${spring.activemq.user}")
  @Getter
  private String BROKER_USERNAME;
  @Value("${spring.activemq.password}")
  @Getter
  private String BROKER_PASSWORD;

  @Bean
  public ExchangeStrategies getExchangeStrategies() {
    return ExchangeStrategies
        .builder()
        .codecs(codecs -> codecs
            .defaultCodecs()
            .maxInMemorySize(Integer.MAX_VALUE))
        .build();
  }

//  @Bean
//  @Qualifier(DiscoveryService.DB_SERVICE_CLIENT)
//  public WebClient marketWebClient() {
//    String marketClientBaseURL = String.format("%s:%s", marketClientHost, marketClientPort);
//    return WebClient
//        .builder()
//        .codecs(ClientCodecConfigurer::defaultCodecs)
//        .baseUrl(marketClientBaseURL)
//        .exchangeStrategies(getExchangeStrategies())
//        .build();
//  }

  @Bean
  public MessageConverter messageConverter(JmsTemplate jmstemplate) {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setObjectMapper(objectMapper());
    jmstemplate.setMessageConverter(converter);
    return converter;
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }
}