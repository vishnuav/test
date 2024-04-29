package com.frk.crd.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.frk.crd.utils.DiscoveryService;
import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.frk.crd.db.model"})
@EnableJpaRepositories(basePackages = {"com.frk.crd.db.dao"})
public class CRDConfiguration {
  @Value(value = "${crd.app.rest.connector.host}")
  @Getter
  protected String marketClientHost;
  @Value(value = "${crd.app.rest.connector.port}")
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

  @Bean
  @Qualifier(DiscoveryService.MARKET_SERVICE_CLIENT)
  public WebClient marketWebClient() {
    String marketClientBaseURL = String.format("%s:%s", marketClientHost, marketClientPort);
    //.responseTimeout(Duration.ofMillis(30000))
    //HttpClient client = HttpClient.create()
    //  .responseTimeout(Duration.ofSeconds(1))
    //.clientConnector(new ReactorClientHttpConnector(httpClient))
    return WebClient
      .builder()
      .codecs(ClientCodecConfigurer::defaultCodecs)
      .baseUrl(marketClientBaseURL)
      .exchangeStrategies(getExchangeStrategies())
      .build();
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setTrustAllPackages(true);
    connectionFactory.setBrokerURL(BROKER_URL);
    connectionFactory.setPassword(BROKER_USERNAME);
    connectionFactory.setUserName(BROKER_PASSWORD);
    return connectionFactory;
  }

  @Bean
  public MessageConverter messageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setObjectMapper(objectMapper());
    return converter;
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setMessageConverter(messageConverter());
    template.setPubSubDomain(true);
    template.setDestinationResolver(destinationResolver());
    template.setDeliveryPersistent(true);
    return template;
  }

  @Bean
  public BrokerService brokerService() throws Exception {
    BrokerService embeddedBroker = new BrokerService();
    embeddedBroker.addConnector(BROKER_URL);
    embeddedBroker.setPersistent(false);
    embeddedBroker.start(true);
    return embeddedBroker;
  }

  @Bean
  DynamicDestinationResolver destinationResolver() {
    return new DynamicDestinationResolver() {
      @Override
      public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
        return super.resolveDestinationName(session, destinationName, destinationName.endsWith("Topic"));
      }
    };
  }
}