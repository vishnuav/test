package com.frk.crd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringJMSCamelFileApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringJMSCamelFileApplication.class, args);
  }
}