package com.frk.crd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.frk.crd")
public class CRDApplication {
  public static void main(String[] args) {
    log.info("Starting CRD Application");
    try {
      SpringApplication marketConnectorApplication = new SpringApplication(CRDApplication.class);
      marketConnectorApplication.run(args);
    } catch (Exception exception) {
      log.warn("Exception in running CRD Application", exception);
    }
  }
}