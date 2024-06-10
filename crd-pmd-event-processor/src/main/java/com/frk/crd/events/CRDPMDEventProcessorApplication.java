package com.frk.crd.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.frk.crd")
public class CRDPMDEventProcessorApplication {
  public static void main(String[] args) {
    log.info("Starting CRD Camel Application");
    try {
      SpringApplication.run(CRDPMDEventProcessorApplication.class);
    } catch (Exception exception) {
      log.warn("Exception when starting CRD to PMD Event Processor Application", exception);
    }
  }
}