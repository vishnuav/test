package com.frk.crd;

import com.frk.crd.configuration.CRDConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.broker.BrokerService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CRDConfiguration.class, Producer.class, CamelRoute.class})
class CamelRouteTest extends CRDConfigurationTest {
  @SneakyThrows
  @BeforeAll
  static void beforeAll() {
    embeddedBroker = new BrokerService();
    embeddedBroker.addConnector("tcp://localhost:61616");
    embeddedBroker.setPersistent(false);
    embeddedBroker.start(true);
  }

  @Test
  void testCamelRouteSuccessful() throws Exception {
    producer.sendToQueue(inQueue, "Test");
    Awaitility.await().atMost(Duration.ofSeconds(30)).pollDelay(Duration.ofMillis(100)).await();
  }
}