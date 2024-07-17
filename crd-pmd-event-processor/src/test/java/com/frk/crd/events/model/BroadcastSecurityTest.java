package com.frk.crd.events.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class BroadcastSecurityTest {
  final String SECURITY_XML = "<SECURITY><CRD_TABLE_NAME>CSM_SECURITY</CRD_TABLE_NAME><SEC_NAME>OTC Eq Index Call Option</SEC_NAME><EXT_SEC_ID>848430430</EXT_SEC_ID><FRST_EXERCISE_DATE>2024-07-26T00:00:00.000-0400</FRST_EXERCISE_DATE><EXPIRE_DATE>2024-07-26T00:00:00.000-0400</EXPIRE_DATE><SEC_ID>5047703478</SEC_ID><CNTRY_OF_RISK>US</CNTRY_OF_RISK><ISSUER_CD>MSIP0</ISSUER_CD><STRIKE_PRICE>530</STRIKE_PRICE><EXPIRE_SETTLE_DATE>2024-07-30T00:00:00.000-0400</EXPIRE_SETTLE_DATE><SEC_TYP_CD>IOPC</SEC_TYP_CD><CUSIP>99C234323</CUSIP><ISSUE_CNTRY_CD>US</ISSUE_CNTRY_CD><ISSUE_DATE>2024-06-28T00:00:00.000-0400</ISSUE_DATE><LOC_CRRNCY_CD>USD</LOC_CRRNCY_CD><MKT_PRICE>4.5</MKT_PRICE><PARENT_ISSUER_CD>438286</PARENT_ISSUER_CD><ISSUER_NAME>Morgan Stanley &amp; Co Intl Plc</ISSUER_NAME><OPTN_EXPIRE_TYPE>EURO</OPTN_EXPIRE_TYPE><OTC_CLEAR_ELIG_IND>N</OTC_CLEAR_ELIG_IND><MAT_DATE_ADJ>NOADJ</MAT_DATE_ADJ><ISSUE_DATE_ADJ>NOADJ</ISSUE_DATE_ADJ><UNDERLY_SEC_ID>703060930</UNDERLY_SEC_ID></SECURITY>";

  @Test
  void deserialize() {
    BroadcastSecurity security = new BroadcastSecurity("CSM_SECURITY", "5047703478", "OTC Eq Index Call Option",
        "703060930", BroadcastSecurityAware.SWAPTION_IRS, "99C234323", "US", "USD",
        "848430430", "US", "MSIP0", "438286", "USD", "EURP",
        "NOADJ", "NOADJ", false, 530, 4.5, 1L,
        1L, 1L, 1L);
    String actual = security.toXML();
    Assertions.assertNotNull(actual);
    Assertions.assertFalse(StringUtils.isBlank(actual));
    log.info("actual:   {}", actual);
    log.info("expected: {}", SECURITY_XML);
  }
}