package com.frk.crd.jms.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JMSDefaultLocalConfigurationCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return context.getEnvironment().acceptsProfiles("local") || context.getEnvironment().acceptsProfiles("default");
  }
}
