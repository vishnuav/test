package com.frk.crd.configuration;

import com.frk.crd.camel.CamelRoute;
import com.frk.crd.camel.Producer;
import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.service.DBSyncService;
import com.frk.crd.service.DBWriteService;
import com.frk.crd.service.impl.DBSyncServiceImpl;
import com.frk.crd.service.impl.DBWriteServiceImpl;
import org.apache.activemq.broker.BrokerService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import javax.jms.ConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "local")
@Sql(value = {"/db-script.sql"})
@SpringBootTest(classes = {CRDConfiguration.class, DBWriteServiceImpl.class, DBSyncServiceImpl.class, CamelRoute.class, Producer.class})
public class CRDConfigurationTest {
  @Value("${server.port}")
  protected int serverPort;
  @Value("${local.host}")
  protected String host;
  @Value("${crd.app.in.queue}")
  protected String inQueue;
  @Value("${crd.app.out.queue}")
  private String outQueue;
  @Value("${spring.activemq.broker-url}")
  private String brokerUrl;

  public static final String MESSAGE_FILE = "src/test/resources/Expected.xml";

  @Autowired
  protected CRDConfiguration CRDConfiguration;

  @Autowired
  protected StreamedMessageRepository dbStreamedMessageRepository;

  @Autowired
  protected DBWriteService dbWriteService;
  @Autowired
  protected DBSyncService dbSyncService;
  @Autowired
  protected WebClient webClient;
  @Autowired
  protected ConnectionFactory connectionFactory;
  @Autowired
  protected JmsTemplate jmsTemplate;
  @Autowired
  protected CamelRoute camelRoute;
  @Autowired
  protected Producer producer;
  @Autowired
  protected BrokerService embeddedBroker;

  @Profile("!prod")
  public WebTestClient marketWebTestClient() {
    String marketClientBaseURL = String.format("%s:%s", CRDConfiguration.marketClientHost, CRDConfiguration.marketClientPort);
    return WebTestClient
      .bindToServer()
      .responseTimeout(Duration.ofMillis(30000L))
      .baseUrl(marketClientBaseURL)
      .exchangeStrategies(CRDConfiguration.getExchangeStrategies())
      .build();
  }

  @Profile("!prod")
  public WebTestClient dbWebTestClient() {
    String marketClientBaseURL = String.format("%s:%s", host, serverPort);
    return WebTestClient
      .bindToServer()
      .responseTimeout(Duration.ofMinutes(4L))
      .baseUrl(marketClientBaseURL)
      .exchangeStrategies(CRDConfiguration.getExchangeStrategies())
      .build();
  }

  @Test
  void autowire() {
    // Configuration
    Assertions.assertNotNull(CRDConfiguration);
    Assertions.assertNotNull(CRDConfiguration.getMarketClientHost());
    Assertions.assertNotEquals(0, CRDConfiguration.getMarketClientPort());

    // DB Repositories
    Assertions.assertNotNull(dbStreamedMessageRepository);

    // Service
    Assertions.assertNotNull(dbWriteService);
    Assertions.assertNotNull(dbSyncService);

    // WebTestClient
    Assertions.assertNotNull(dbWebTestClient());
    Assertions.assertNotNull(marketWebTestClient());

    // Camel, JMS
    Assertions.assertFalse(StringUtils.isBlank(inQueue));
    Assertions.assertFalse(StringUtils.isBlank(outQueue));
    Assertions.assertFalse(StringUtils.isBlank(brokerUrl));
    Assertions.assertNotNull(embeddedBroker);
    Assertions.assertNotNull(connectionFactory);
    Assertions.assertNotNull(jmsTemplate);
    Assertions.assertNotNull(camelRoute);
    Assertions.assertNotNull(producer);
  }

  public static List<String> readFile(String fileName) throws IOException {
    File file = new File(fileName);
    Assertions.assertNotNull(file);
    Assertions.assertTrue(file.exists() && file.isFile() && file.canRead());
    return FileUtils.readLines(file, StandardCharsets.UTF_8.name());
  }
}