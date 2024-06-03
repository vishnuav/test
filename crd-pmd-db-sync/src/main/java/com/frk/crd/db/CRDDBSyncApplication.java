package com.frk.crd.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.frk.crd.db")
public class CRDDBSyncApplication {
  public static void main(String[] args) {
    log.info("Starting CRD DB Sync Application");
    try {
      SpringApplication.run(CRDDBSyncApplication.class);
    } catch (Exception exception) {
      log.warn("Exception in running CRD DB Sync Application", exception);
    }
  }
}