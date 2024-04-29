package com.frk.crd.camel;

import com.frk.crd.configuration.CRDConfigurationTest;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class CamelRouteTest extends CRDConfigurationTest {
  @Test
  void testCamelRouteSuccessful() {
    producer.sendToQueue(inQueue, "Test");
    Awaitility.await().atMost(Duration.ofSeconds(30)).pollDelay(Duration.ofMillis(100)).await();
  }
}