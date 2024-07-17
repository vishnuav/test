package com.frk.crd.events.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class BroadcastSecurityTest {
  @Test
  void deserialize() {
    BroadcastSecurity security = new BroadcastSecurity("CSM_SECURITY", "5047703478", "OTC Eq Index Call Option",
      "703060930", BroadcastSecurityAware.SWAPTION_IRS, "99C234323", "US", "USD",
      "848430430", "US", "MSIP0", "438286", "USD", "EURP",
      "NOADJ", "NOADJ", false, 530, 4.5, 1L,
      1L, 1L, 1L);
    String xml = security.toXML();
    Assertions.assertNotNull(xml);
    Assertions.assertFalse(StringUtils.isBlank(xml));
    log.info(xml);
  }
}