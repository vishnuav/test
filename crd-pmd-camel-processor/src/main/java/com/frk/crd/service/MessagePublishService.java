package com.frk.crd.service;

public interface MessagePublishService {
  void sendToQueue(String queueName, String payload);
}
