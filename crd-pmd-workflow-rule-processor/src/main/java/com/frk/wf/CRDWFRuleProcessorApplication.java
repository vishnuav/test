package com.frk.wf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.frk.wf", "com.frk.crd.db.dao", "com.frk.crd.events"})
public class CRDWFRuleProcessorApplication {
  public static void main(String[] args) {
    log.info("Starting CRD PMD WF Rule Processor Application");
    try {
      SpringApplication.run(CRDWFRuleProcessorApplication.class);
    } catch (Exception exception) {
      log.warn("Exception when starting CRD PMD WF Rule Processor Application", exception);
    }
  }
}