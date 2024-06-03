package com.frk.crd.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.frk.crd.exceptionhandler")
public class CRDExceptionHandlerApplication {
  public static void main(String[] args) {
    log.info("Starting CRD Exception Handler Application Application");
    try {
      SpringApplication.run(CRDExceptionHandlerApplication.class);
    } catch (Exception exception) {
      log.warn("Exception in running CRD DB Sync Application", exception);
    }
  }
}