package com.frk.crd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JMS2FileConfiguration.class, Producer.class, CamelRoute.class})
class CamelRouteTest extends JMS2FileConfigurationTest {
    @Test
    void testCamelRouteSuccessful() {
        producer.sendToQueue(inQueue, "Test");
    }
}