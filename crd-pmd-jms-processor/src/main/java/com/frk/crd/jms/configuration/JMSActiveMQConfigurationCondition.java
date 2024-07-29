package com.frk.crd.jms.configuration;

import com.frk.crd.jms.model.ActiveMQComponentBean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JMSActiveMQConfigurationCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return context.getEnvironment().acceptsProfiles(ActiveMQComponentBean.ACTIVEMQ_COMPONENT);
  }
}