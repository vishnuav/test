package com.frk.crd.events.configuration;

import com.frk.crd.jms.configuration.CRDActiveMQConfiguration;
import com.frk.crd.jms.configuration.CRDIMBMQConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
@Import({CRDActiveMQConfiguration.class, CRDIMBMQConfiguration.class})
public class EventProcessorConfiguration {
  private static final String HTTP_CONNECTION_PROVIDER = "_provider";
  private static final int MAX_CONNECTIONS_COUNT = 5000;

  @Value("${crd.app.db.service.url}")
  private String dbServiceURL;

  @Bean
  public WebClient crdDBClient() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(dbServiceURL);
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
    return WebClient
      .builder()
      .uriBuilderFactory(factory)
      .clientConnector(getReactorClientHttpConnector("CRD_DB_SERVICE"))
      .baseUrl(dbServiceURL)
      .exchangeStrategies(getExchangeStrategies())
      .build();
  }

  ExchangeStrategies getExchangeStrategies() {
    return ExchangeStrategies
      .builder()
      .codecs(codecs -> codecs
        .defaultCodecs()
        .maxInMemorySize(Integer.MAX_VALUE))
      .build();
  }

  ReactorClientHttpConnector getReactorClientHttpConnector(String providerName) {
    ConnectionProvider connectionProvider = ConnectionProvider
      .builder(providerName + HTTP_CONNECTION_PROVIDER)
      .maxConnections(MAX_CONNECTIONS_COUNT)
      .pendingAcquireMaxCount(MAX_CONNECTIONS_COUNT)
      .build();
    return new ReactorClientHttpConnector(HttpClient.create(connectionProvider));
  }

}