package com.frk.crd.db.configuration;

import com.frk.crd.db.dao.ExceptionMessageRepository;
import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.db.service.DBReadService;
import com.frk.crd.db.service.DBSyncService;
import com.frk.crd.db.service.impl.DBReadServiceImpl;
import com.frk.crd.db.service.impl.DBSyncServiceImpl;
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
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBReadServiceImpl.class, DBSyncServiceImpl.class})
public class CRDDBSyncConfigurationTest {
  @Value("${server.port}")
  protected int serverPort;
  @Value("${local.host}")
  protected String host;

  @Autowired
  protected StreamedMessageRepository dbStreamedMessageRepository;
  @Autowired
  protected ExceptionMessageRepository dbExceptionMessageRepository;

  @Autowired
  protected DBSyncService dbSyncService;
  @Autowired
  protected DBReadService dbReadService;
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

  @Test
  void autowire() {
    // DB Repositories
    Assertions.assertNotNull(dbStreamedMessageRepository);
    Assertions.assertNotNull(dbExceptionMessageRepository);

    // Service
    Assertions.assertNotNull(dbReadService);
    Assertions.assertNotNull(dbSyncService);
  }
}