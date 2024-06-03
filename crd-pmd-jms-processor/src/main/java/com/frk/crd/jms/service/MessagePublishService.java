package com.frk.crd.jms.service;

public interface MessagePublishService {
  void sendToQueue(String queueName, String payload);
}
