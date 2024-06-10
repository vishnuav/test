package com.frk.crd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.frk.crd")
public class CRDCamelApplication {
  public static void main(String[] args) {
    log.info("Starting CRD Camel Application");
    try {
      SpringApplication.run(CRDCamelApplication.class);
    } catch (Exception exception) {
      log.warn("Exception when starting CRD Camel Application", exception);
    }
  }
}