package com.frk.crd.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JMSHigherEnvironmentConfigurationCondition implements Condition {
  @Override
  public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
    Condition condition = new JMSDefalutLocalConfigurationCondition();
    return !condition.matches(context, metadata);
  }
}