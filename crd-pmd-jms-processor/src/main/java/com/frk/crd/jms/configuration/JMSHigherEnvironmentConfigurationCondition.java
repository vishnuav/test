package com.frk.crd.jms.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JMSHigherEnvironmentConfigurationCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Condition condition = new JMSDefaultLocalConfigurationCondition();
    return !condition.matches(context, metadata);
  }
}
