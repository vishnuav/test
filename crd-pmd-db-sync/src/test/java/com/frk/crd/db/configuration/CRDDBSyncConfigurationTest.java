package com.frk.crd.db.configuration;

import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.db.service.DBSyncService;
import com.frk.crd.db.service.DBWriteService;
import com.frk.crd.db.service.impl.DBSyncServiceImpl;
import com.frk.crd.db.service.impl.DBWriteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "local")
@Sql(value = {"/db-script.sql"})
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBWriteServiceImpl.class, DBSyncServiceImpl.class})
public class CRDDBSyncConfigurationTest {
  @Value("${server.port}")
  protected int serverPort;
  @Value("${local.host}")
  protected String host;

  @Autowired
  protected CRDDBSyncConfiguration crddbSyncConfiguration;

  @Autowired
  protected StreamedMessageRepository dbStreamedMessageRepository;

  @Autowired
  protected DBWriteService dbWriteService;
  @Autowired
  protected DBSyncService dbSyncService;
//  @Autowired
//  protected WebClient webClient;

//  @Profile("!prod")
//  public WebTestClient marketWebTestClient() {
//    String marketClientBaseURL = String.format("%s:%s", CRDConfiguration.marketClientHost, CRDConfiguration.marketClientPort);
//    return WebTestClient
//        .bindToServer()
//        .responseTimeout(Duration.ofMillis(30000L))
//        .baseUrl(marketClientBaseURL)
//        .exchangeStrategies(CRDConfiguration.getExchangeStrategies())
//        .build();
//  }

//  @Profile("!prod")
//  public WebTestClient dbWebTestClient() {
//    String marketClientBaseURL = String.format("%s:%s", host, serverPort);
//    return WebTestClient
//        .bindToServer()
//        .responseTimeout(Duration.ofMinutes(4L))
//        .baseUrl(marketClientBaseURL)
//        .exchangeStrategies(CRDConfiguration.getExchangeStrategies())
//        .build();
//  }

  @Test
  void autowire() {
    // Configuration
    Assertions.assertNotNull(crddbSyncConfiguration);
//    Assertions.assertNotNull(CRDConfiguration.getMarketClientHost());
//    Assertions.assertNotEquals(0, CRDConfiguration.getMarketClientPort());

    // DB Repositories
    Assertions.assertNotNull(dbStreamedMessageRepository);

    // Service
    Assertions.assertNotNull(dbWriteService);
    Assertions.assertNotNull(dbSyncService);

    // WebTestClient
//    Assertions.assertNotNull(dbWebTestClient());
//    Assertions.assertNotNull(marketWebTestClient());
  }

//  public static List<String> readFile(String fileName) throws IOException {
//    File file = new File(fileName);
//    Assertions.assertNotNull(file);
//    Assertions.assertTrue(file.exists() && file.isFile() && file.canRead());
//    return FileUtils.readLines(file, StandardCharsets.UTF_8.name());
//  }
}