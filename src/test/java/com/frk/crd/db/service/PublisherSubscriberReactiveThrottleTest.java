package com.frk.crd.db.service;

import com.frk.crd.configuration.CRDConfigurationTest;
import com.frk.crd.db.utils.TestConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@Slf4j
class PublisherSubscriberReactiveThrottleTest extends CRDConfigurationTest {
  @Test
  public void whenLimitRateSet_thenSplitIntoChunks() {
    Flux<Integer> limit = Flux.range(1, 25).limitRate(15);

    ThrottlingReactiveSubscriber subscriber = new ThrottlingReactiveSubscriber();
    limit.subscribeOn(Schedulers.boundedElastic()).subscribe(subscriber);
    StepVerifier.create(limit)
      .expectSubscription()
      .thenRequest(15)
      .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      .expectNext(11, 12, 13, 14, 15)
      .thenRequest(10)
      .expectNext(16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
      .verifyComplete();
    TestConstants.await(subscriber);
  }
}