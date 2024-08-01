package com.frk.crd.db.dao.impl;

import com.frk.crd.db.configuration.CRDDBSyncConfiguration;
import com.frk.crd.db.dao.DBBroadcastSecurityRepository;
import com.frk.crd.db.model.DBSecurity;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "oracle")
@SpringBootTest(classes = {CRDDBSyncConfiguration.class, DBBroadcastSecurityRepositoryImpl.class})
class DBSecurityRepositoryImplTest {
  @Autowired
  private DBBroadcastSecurityRepository repository;

  @Test
  void getSecurityForOrderId() {
    Assertions.assertNotNull(repository);
    String secId = "5051098226";
    String orderId = "5051098225";
    DBSecurity security = repository.getSecurityForOrderId(orderId);
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
    Assertions.assertEquals("N", security.getOtcClearingEligibilityIndicator());
    Assertions.assertEquals("2024-07-10", security.getSecurityIssueDate().toString());
    Assertions.assertNull(security.getMaturityAdjustment());
    Assertions.assertNull(security.getIssueDateAdjustment());
    Assertions.assertEquals("111_EXCHS", security.getPayDateHolExchangeCode());
    Assertions.assertNull(security.getExerciseSettleLag());
    Assertions.assertNull(security.getCalcAgent());
    Assertions.assertNull(security.getBusinessDayConversion());
    Assertions.assertEquals("5051098226", security.getUnderlyingSecId());
    Assertions.assertEquals("5051098226", security.getSwapSecId());
  }

  @Test
  void getSecurityForSecId() {
    Assertions.assertNotNull(repository);
    String secId = "5056576222";
    DBSecurity security = repository.getSecurityForSecId(secId);
    Assertions.assertNotNull(security);
    Assertions.assertEquals(secId, security.getSecId());

    Assertions.assertEquals(secId, security.getSecId());
    String usd = "USD";
    Assertions.assertEquals(usd, security.getAssetCurrencyCode());
    Assertions.assertEquals("Receive Fixed 3.85% Pay UNITED STATES SOFR SECURED OVERNIGHT FINANCING RATE INX 1 9/15/2034 SWAPIRS Interest Rate", security.getSecurityName());
    Assertions.assertNull(security.getExternalSecId());
    Assertions.assertNull(security.getFirstExerciseDate());
    Assertions.assertNull(security.getExpireDate());
    Assertions.assertNull(security.getExpireTimeZoneRegionCode());
    String us = "US";
    Assertions.assertEquals(us, security.getCountryOfRisk());
    Assertions.assertNull(security.getIncorporatedCountryCode());
    Assertions.assertEquals("CITL0", security.getIssuerCode());
    Assertions.assertEquals(0, security.getStrikePx());
    Assertions.assertNull(security.getExpireSettleDate());
    Assertions.assertEquals("SWAPIRS", security.getSecurityTypeCode());
    Assertions.assertNull(security.getCusip());
    Assertions.assertNull(security.getIssueCountryCode());
    Assertions.assertEquals("2024-09-15", security.getIssueDate().toString());
    Assertions.assertEquals(usd, security.getLocationCurrencyCode());
    Assertions.assertEquals(0, security.getMarketPx());
    Assertions.assertNull(security.getParentSecId());
    Assertions.assertEquals("207226", security.getParentIssuerCode());
    Assertions.assertEquals("CITIBANK NA LONDON", security.getIssuerName());
    Assertions.assertNull(security.getOptionExpireType());
    Assertions.assertNull(security.getFixingDate());
    Assertions.assertEquals("N", security.getOtcClearingEligibilityIndicator());
    Assertions.assertNull(security.getSecurityIssueDate());
    Assertions.assertEquals("NEXTBD_EOM", security.getMaturityAdjustment());
    Assertions.assertEquals("NOADJ", security.getIssueDateAdjustment());
    Assertions.assertNull(security.getPayDateHolExchangeCode());
    Assertions.assertNull(security.getExerciseSettleLag());
    Assertions.assertNull(security.getCalcAgent());
    Assertions.assertNull(security.getBusinessDayConversion());
    Assertions.assertNull(security.getUnderlyingSecId());
    Assertions.assertNull(security.getSwapSecId());
    Assertions.assertNull(security.getAccrualTypeCode());
    Assertions.assertEquals(1, security.getContractSize());
    Assertions.assertEquals(0, security.getCouponDom());
    Assertions.assertEquals("NOEOM", security.getCouponEOMFlag());
    Assertions.assertEquals(3.85, security.getCouponRate());
    Assertions.assertEquals(0, security.getCouponWom());
    Assertions.assertNull(security.getCouponBasis());
    Assertions.assertNull(security.getFirstPaymentDate());
    Assertions.assertEquals("RCOTTRE", security.getLastModifyBy());
    Assertions.assertNull(security.getLastPaymentDate());
    Assertions.assertEquals("OTC", security.getListExchangeCode());
    Assertions.assertEquals("2034-09-15", security.getMaturityDate().toString());
    Assertions.assertEquals("2024-07-25", security.getMarketPxDateTime().toString());
    Assertions.assertNull(security.getPaymentFrequency());
    Assertions.assertNull(security.getRateBasisSecId());
    Assertions.assertEquals(0, security.getResetDow());
    Assertions.assertEquals(0, security.getResetMargin());
    Assertions.assertEquals(0, security.getResetLagDays());
    Assertions.assertNull(security.getSedol());
    Assertions.assertEquals(0, security.getSpread());
    Assertions.assertEquals("R", security.getSwapLegIndicator());
    Assertions.assertEquals("P", security.getSwapSecIndicator());
    Assertions.assertNull(security.getTicker());
    Assertions.assertNull(security.getVrdnFrequencyCode());
    Assertions.assertNull(security.getClip());
    Assertions.assertEquals(0, security.getAttachPt());
    Assertions.assertEquals(0, security.getDetachPt());
    Assertions.assertNull(security.getUdfChar23());
    Assertions.assertEquals("NONE", security.getPrincipalExchangeCode());
    Assertions.assertEquals("VANILLA", security.getSwapSubType());
    Assertions.assertEquals(0, security.getFixingRate());
    Assertions.assertNull(security.getUnderlySecId());
    Assertions.assertNull(security.getUdfChar1());
    Assertions.assertEquals(0, security.getDayBasis());
  }

  @Test
  void getChildSecurities() {
    Assertions.assertNotNull(repository);
    String secId = "5056576222";
    List<String> childSecurities = repository.getChildSecurities(secId);
    Assertions.assertNotNull(childSecurities);
    Assertions.assertEquals(2, childSecurities.size());
    Assertions.assertTrue(childSecurities.contains("5056576226"));
    Assertions.assertTrue(childSecurities.contains("5056576230"));
  }
}