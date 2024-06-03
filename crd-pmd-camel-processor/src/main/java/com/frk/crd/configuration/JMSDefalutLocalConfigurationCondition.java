package com.frk.crd.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JMSDefalutLocalConfigurationCondition implements Condition {
  @Override
  public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
    return context.getEnvironment().acceptsProfiles("local")
        || context.getEnvironment().acceptsProfiles("default");
  }
}