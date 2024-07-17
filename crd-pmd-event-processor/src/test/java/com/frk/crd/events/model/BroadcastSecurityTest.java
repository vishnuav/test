package com.frk.crd.events.model;

import com.frk.crd.converter.CustomJsonMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class BroadcastSecurityTest {
  final String SECURITY_XML = "<SECURITY><CRD_TABLE_NAME>CSM_SECURITY</CRD_TABLE_NAME><ASSET_CRRNCY_CD>USD</ASSET_CRRNCY_CD><SEC_NAME>CALL YUM 120 OTCECO EURO 8/19/2024</SEC_NAME><FRST_EXERCISE_DATE>2024-08-19T00:00:00.000-0400</FRST_EXERCISE_DATE><EXPIRE_DATE>2024-08-19T00:00:00.000-0400</EXPIRE_DATE><SEC_ID>5047678918</SEC_ID><EXT_SEC_ID>234243314</EXT_SEC_ID><CNTRY_OF_RISK>US</CNTRY_OF_RISK><ISSUER_CD>MSIP0</ISSUER_CD><STRIKE_PRICE>120</STRIKE_PRICE><EXPIRE_SETTLE_DATE>2024-08-30T00:00:00.000-0400</EXPIRE_SETTLE_DATE><SEC_TYP_CD>OTCECO</SEC_TYP_CD><CUSIP>99C294301</CUSIP><ISSUE_CNTRY_CD>US</ISSUE_CNTRY_CD><ISSUE_DATE>2024-06-28T00:00:00.000-0400</ISSUE_DATE><LOC_CRRNCY_CD>USD</LOC_CRRNCY_CD><MKT_PRICE>5</MKT_PRICE><PARENT_ISSUER_CD>438286</PARENT_ISSUER_CD><ISSUER_NAME>Morgan Stanley &amp; Co Intl Plc</ISSUER_NAME><OPTN_EXPIRE_TYPE>EURO</OPTN_EXPIRE_TYPE><OTC_CLEAR_ELIG_IND>N</OTC_CLEAR_ELIG_IND><MAT_DATE_ADJ>NEXTBD</MAT_DATE_ADJ><ISSUE_DATE_ADJ>NEXTBD</ISSUE_DATE_ADJ><EXERCISE_SETTLE_LAG>1</EXERCISE_SETTLE_LAG><CALC_AGENT>BUYL</CALC_AGENT><BUS_DAY_CONV>NEXTBD</BUS_DAY_CONV><UNDERLY_SEC_ID>703035994</UNDERLY_SEC_ID></SECURITY>";

  @Test
  void serialize() {
    BroadcastSecurity security = CustomJsonMessageConverter.fromXML(SECURITY_XML, BroadcastSecurity.class).orElse(null);
    Assertions.assertNotNull(security);
  }

  @Test
  void deserialize() {
    BroadcastSecurity eqOptionCall = new BroadcastSecurity("CSM_SECURITY", "USD", "NEXTBD",
      "BUYL", "US", "99C294301", "234243314", "234243314", "MSIP0",
      "Morgan Stanley &amp; Co Intl Plc", null, null, "US",
      null, "EURO", "438286", null,
      "CALL YUM 120 OTCECO EURO 8/19/2024", "OTCECO", "payDateHolExchangeCode", null,
      null, "703035994", false, false, 5.0, 120.0,
      1L, 1724990400000L, 1L, 1719547200000L, 1L);

    String actual = eqOptionCall.toXML();
    Assertions.assertNotNull(actual);
    Assertions.assertFalse(StringUtils.isBlank(actual));
    log.info("actual:   {}", actual);
    log.info("expected: {}", SECURITY_XML);
  }
}