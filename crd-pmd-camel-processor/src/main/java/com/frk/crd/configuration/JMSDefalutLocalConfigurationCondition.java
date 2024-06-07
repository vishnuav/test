package com.frk.crd.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class JMSDefalutLocalConfigurationCondition implements Condition {
  @Override
  public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
    Environment environment = context.getEnvironment();
    boolean accepted = environment.acceptsProfiles("local") || environment.acceptsProfiles("default");
    if (accepted) {
      log.info("Using local/default configuration");
    } else {
      log.info("Using non-local/default configuration");
    }
    return accepted;
  }
}