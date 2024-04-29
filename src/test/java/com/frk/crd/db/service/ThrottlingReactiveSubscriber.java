package com.frk.crd.db.service;

import com.frk.crd.utils.AwaitilityWaiter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThrottlingReactiveSubscriber implements Subscriber<Integer>, AwaitilityWaiter {
  private final AtomicInteger subscriptionCount = new AtomicInteger(0);
  private final AtomicInteger errorCountCount = new AtomicInteger(0);
  private final AtomicInteger completedCount = new AtomicInteger(0);
  private Subscription subscription = null;
  private final AtomicBoolean terminated = new AtomicBoolean(false);

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    subscription.request(10);
  }

  @Override
  public void onNext(Integer integer) {
    subscriptionCount.incrementAndGet();
    log.info("integer = {}", integer);
    completedCount.incrementAndGet();
    if (!terminated.get()) {
      subscription.request(10);
    }
    completedCount.incrementAndGet();
  }

  @Override
  public void onError(Throwable throwable) {
    errorCountCount.incrementAndGet();
    log.info("error", throwable);
  }

  @Override
  public void onComplete() {
    log.info("Finished!");
    terminated.set(true);
  }

  public int getSubscriptionCount() {
    return subscriptionCount.get();
  }

  public int getErrorCountCount() {
    return errorCountCount.get();
  }

  public int getCompletedCount() {
    return completedCount.get();
  }

  @Override
  public boolean isSubscriptionComplete() {
    return terminated.get();
  }
}