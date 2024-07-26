package com.frk.crd.db.dao.impl;

import com.frk.crd.db.configuration.CRDDBSyncConfiguration;
import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.model.DBBroadcastSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "dev2")
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBBroadcastSecurityRepositoryImpl.class})
class DBBroadcastSecurityRepositoryImplTest {
  @Autowired
  private DBBroadcastSecurityRepository repository;

  @Test
  void testSecurity() {
    Assertions.assertNotNull(repository);
    String secId = "5051098226";
    DBBroadcastSecurity security = repository.getSecurity(secId);
    Assertions.assertNotNull(security);
    Assertions.assertEquals(secId, security.getSecId());

    Assertions.assertEquals(secId, security.getSecId());
    String usd = "USD";
    Assertions.assertEquals(usd, security.getAssetCurrencyCode());
    Assertions.assertEquals("SWPTN 1Y x 3Y9M EU 7/16/2025 to BOFA0", security.getSecurityName());
    Assertions.assertNull(security.getExternalSecId());
    Assertions.assertEquals("2025-07-16", security.getFirstExerciseDate().toString());
    Assertions.assertEquals("2025-07-16", security.getExpireDate().toString());
    Assertions.assertNull(security.getExpireTimeZoneRegionCode());
    String us = "US";
    Assertions.assertEquals(us, security.getCountryOfRisk());
    Assertions.assertNull(security.getIncorporatedCountryCode());
    Assertions.assertEquals("BOFA0", security.getIssuerCode());
    Assertions.assertEquals(0, security.getStrikePx());
    Assertions.assertNull(security.getExpireSettleDate());
    Assertions.assertEquals("SWPTNCDI", security.getSecurityTypeCode());
    Assertions.assertNull(security.getCusip());
    Assertions.assertNull(security.getIssueCountryCode());
    Assertions.assertEquals("2024-07-10", security.getIssueDate().toString());
    Assertions.assertEquals(usd, security.getLocationCurrencyCode());
    Assertions.assertEquals(130, security.getMarketPx());
    Assertions.assertNull(security.getParentSecId());
    Assertions.assertEquals("296290", security.getParentIssuerCode());
    Assertions.assertEquals("Bank of America N.A.", security.getIssuerName());
    Assertions.assertEquals("EURO", security.getOptionExpireType());
    Assertions.assertNull(security.getFixingDate());
    Assertions.assertEquals("N", security.getOtcClearingEligibility());
    Assertions.assertEquals("2024-07-10", security.getSecurityIssueDate().toString());
    Assertions.assertNull(security.getMaturityAdjustment());
    Assertions.assertNull(security.getIssueDateAdjustment());
    Assertions.assertEquals("111_EXCHS", security.getPayDateHolExchangeCode());
    Assertions.assertNull(security.getExerciseSettleLag());
    Assertions.assertNull(security.getCalcAgent());
    Assertions.assertNull(security.getBusinessDayConversion());
    Assertions.assertEquals("5051098227", security.getUnderlyingSecId());
    Assertions.assertEquals("5051098227", security.getSwapSecId());
  }
}