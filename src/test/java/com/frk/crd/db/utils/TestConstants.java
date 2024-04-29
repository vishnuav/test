package com.frk.crd.db.utils;

import com.frk.crd.utils.AwaitilityWaiter;
import org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

public class TestConstants {
  public static final String LOGIN_MESSAGE = "{\"response\":[{\"service\":\"ADMIN\",\"requestid\":\"1\",\"command\":\"LOGIN\",\"timestamp\":1682334000000,\"content\":{\"code\":0,\"msg\":\"04-2\"}}]}";
  public static final String HEARTBEAT_MESSAGE = "{\"notify\":[{\"heartbeat\":\"1674011769604\"}]}";
  public static final String STREAMER_TIMEOUT_MESSAGE = "{\"notify\":[{\"service\":\"ADMIN\",\"timestamp\":1674011775644,\"content\":{\"code\":30,\"msg\":\"Stop streaming due to empty subscription\"}}]}";
  public static final String QUOTE_SUBSCRIPTION_SUCCESS_MESSAGE = "{\"response\":[{\"service\":\"QUOTE\",\"requestid\":\"4\",\"command\":\"SUBS\",\"timestamp\":1674011807220,\"content\":{\"code\":0,\"msg\":\"SUBS command succeeded\"}}]}";
  public static final String STREAM_QUOTES_MESSAGE = "{\"data\":[{\"service\":\"QUOTE\", \"timestamp\":1674011807268,\"command\":\"SUBS\",\"content\":[{\"key\":\"NUTX\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"67079U108\",\"1\":1.5,\"2\":1.52,\"3\":1.52,\"4\":19,\"5\":9,\"8\":1730884,\"9\":1,\"15\":1.71,\"48\":\"Normal\",\"49\":1.5,\"51\":1674003596805},{\"key\":\"AVIR\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"04683R106\",\"1\":4.9,\"2\":5.36,\"3\":4.9,\"4\":1,\"5\":2,\"8\":307609,\"15\":4.93,\"48\":\"Closed\",\"49\":4.92,\"51\":1673989757665},{\"key\":\"AAPL\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"037833100\",\"1\":135.45,\"2\":135.51,\"3\":135.45,\"4\":4,\"5\":1,\"8\":63646627,\"15\":134.76,\"48\":\"Normal\",\"49\":135.51,\"51\":1674003599054},{\"key\":\"MSFT\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"594918104\",\"1\":239.36,\"2\":239.52,\"3\":239.36,\"4\":4,\"5\":14,\"8\":29831257,\"15\":239.23,\"48\":\"Normal\",\"49\":239.52,\"51\":1674003596450},{\"key\":\"BBIG\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"927330100\",\"1\":0.7889,\"2\":0.79,\"3\":0.8,\"4\":1,\"5\":20,\"8\":32902156,\"15\":0.6749,\"48\":\"Normal\",\"49\":0.79,\"51\":1674003594451},{\"key\":\"COGT\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"19240Q201\",\"1\":12.35,\"2\":14,\"3\":13.29,\"4\":4,\"5\":1,\"8\":872341,\"15\":13.03,\"48\":\"Normal\",\"49\":12.66,\"51\":1673995344907},{\"key\":\"SIDU\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"826165102\",\"1\":1.13,\"2\":1.14,\"3\":1.14,\"4\":237,\"5\":41,\"8\":732338,\"15\":1.34,\"48\":\"Normal\",\"49\":1.14,\"51\":1674002899052},{\"key\":\"PHUN\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"71948P100\",\"1\":1,\"2\":1.04,\"3\":1.02,\"4\":387,\"5\":43,\"8\":1590629,\"9\":1,\"15\":1.06,\"48\":\"Normal\",\"49\":1.02,\"51\":1674003540728},{\"key\":\"STSS\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"82003F101\",\"1\":1.48,\"2\":1.53,\"3\":1.51,\"4\":1,\"5\":12,\"8\":161224,\"15\":1.63,\"48\":\"Normal\",\"49\":1.51,\"51\":1674003463307}]}]}";

  public static final String USER_PRINCIPAL = "{\"userId\": \"vishnuswaroop\",\"userCdDomainId\": \"A000000080057455\",\"primaryAccountId\": \"454089579\",\"lastLoginTime\": \"2022-08-27T06:25:38+0000\",\"tokenExpirationTime\": \"2022-08-27T06:56:11+0000\",\"loginTime\": \"2022-08-27T06:26:11+0000\",\"accessLevel\": \"CUS\",\"stalePassword\": false,\"streamerInfo\": {\"streamerBinaryUrl\": \"streamer-bin.tdameritrade.com\",\"streamerSocketUrl\": \"streamer-ws.tdameritrade.com\",\"token\": \"2770bde09d094ad7c258588bf887076632aa51bc\",\"tokenTimestamp\": \"2022-08-27T06:26:12+0000\",\"userGroup\": \"ACCT\",\"accessLevel\": \"ACCT\",\"acl\": \"AKBPCCCNCVDRDTDWESF7G1G3G5G7GKGLH1H3H5LTM1MANSOLORPNQ2QSRFSDTETFTOTTUAURXBXNXOD2D4D6D8E2E4E6E8F2F4F6H8I2\",\"appId\": \"vishnuswaroo\"},\"professionalStatus\": \"NON_PROFESSIONAL\",\"quotes\": {\"isNyseDelayed\": false,\"isNasdaqDelayed\": false,\"isOpraDelayed\": false,\"isAmexDelayed\": false,\"isCmeDelayed\": true,\"isIceDelayed\": true,\"isForexDelayed\": true},\"streamerSubscriptionKeys\": {\"keys\": [{\"key\": \"bb600066bf18484fa7336581fceedf2b06faed2b35056af4d337831040101fdf10ae921cb61bc6a5f1a84cf91ac517a6f\"}]},\"exchangeAgreements\": {\"NASDAQ_EXCHANGE_AGREEMENT\": \"ACCEPTED\",\"OPRA_EXCHANGE_AGREEMENT\": \"ACCEPTED\",\"NYSE_EXCHANGE_AGREEMENT\": \"ACCEPTED\"},\"accounts\": [{\"accountId\": \"454089579\",\"displayName\": \"vishnuswaroop\",\"accountCdDomainId\": \"A000000080057454\",\"company\": \"AMER\",\"segment\": \"AMER\",\"acl\": \"AKBPCCCNCVDRDTDWESF7G1G3G5G7GKGLH1H3H5LTM1MANSOLORPNQ2QSRFSDTETFTOTTUAURXBXNXO\",\"authorizations\": {\"apex\": false,\"levelTwoQuotes\": true,\"stockTrading\": true,\"marginTrading\": true,\"streamingNews\": true,\"optionTradingLevel\": \"LONG\",\"streamerAccess\": true,\"advancedMargin\": true,\"scottradeAccount\": false}}]}";

  public static final String NOTIFICATION_MESSAGE = "{\"notify\":[{\"heartbeat\":\"1625764816397\"}]}";
  public static final String QUOTE_MESSAGE = "{\"data\":[{\"service\":\"QUOTE\", \"timestamp\":1658482788105,\"command\":\"SUBS\",\"content\":[{\"key\":\"TRON\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"G2426E104\",\"1\":10,\"2\":16.12,\"3\":10,\"15\":10.04,\"48\":\"Normal\",\"49\":10.08,\"51\":1658433994261},{\"key\":\"LMFA\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"502074404\",\"1\":0.98,\"2\":1.11,\"3\":1,\"4\":12,\"5\":5,\"15\":1.05,\"48\":\"Normal\",\"49\":1.05,\"51\":1658480638867},{\"key\":\"ACOR\",\"delayed\":false,\"assetMainType\":\"EQUITY\",\"cusip\":\"00484M601\",\"1\":0.4601,\"2\":0.4997,\"3\":0.4603,\"4\":3,\"5\":1,\"9\":11,\"15\":0.4727,\"48\":\"Normal\",\"49\":0.4727,\"51\":1658440380411}]}]}\n";
  public static final String CANDLE_MESSAGE = "{\"data\": [{\"service\": \"CHART_EQUITY\",\"timestamp\": 1658482789826,\"command\": \"SUBS\",\"content\": [{\"seq\": 662,\"key\": \"AAPL\",\"1\": 81.99,\"2\": 81.99,\"3\": 81.9,\"4\": 81.99,\"5\": 1313.0,\"6\": 779,\"7\": 1658447940000,\"8\": 19194},{\"seq\": 752,\"key\": \"MSFT\",\"1\": 13.16,\"2\": 13.19,\"3\": 13.14,\"4\": 13.19,\"5\": 401.0,\"6\": 779,\"7\": 1658447940000,\"8\": 19194}]}]}";

  // Since this is very used Symbol for tests
  public static final String MSFT_TICKER = "MSFT";
  public static final String AAPL_TICKER = "AAPL";
  public static final String TSLA_TICKER = "TSLA";
  public static final String PHUN_TICKER = "PHUN";
  public static final String LCID_TICKER = "LCID";
  public static final String VKTX_TICKER = "VKTX";
  public static final String AVIR_TICKER = "AVIR";
  public static final String STSS_TICKER = "STSS";
  public static final String NUTX_TICKER = "NUTX";
  public static final String BBIG_TICKER = "BBIG";
  public static final String SIDU_TICKER = "SIDU";
  public static final String COGT_TICKER = "COGT";
  public static final String GNS_TICKER = "GNS";
  public static final String QNCX_TICKER = "QNCX";
  public static final String OCEA_TICKER = "OCEA";
  public static final String CRFX_TICKER = "CRFX";
  // Spike from 1.23 to 70$ on
  public static final String CXAI_TICKER = "CXAI";
  // Issue with NaN in PX History for Day
  public static final String MCBN_TICKER = "MCBN";
  //Issue with getting history
  public static final String LEJU_TICKER = "LEJU";
  public static final String SVRE_TICKER = "SVRE";
  public static final String NNOX_TICKER = "NNOX";
  public static final String CVNA_TICKER = "CVNA";
  public static final String AKYA_TICKER = "AKYA";

  // Less than 50 1M candles a day
  public static final String DHHC_TICKER = "DHHC";
  public static final String HTCR_TICKER = "HTCR";

  public static final String ACAHU_TICKER = "ACAHU";
  // No longer trade eligible cause ticker no longer exists
  public static final String GACWF_TICKER = "GACWF";
  // Candles less than 180 for 1M and there by 5M
  public static final String BCSAU_TICKER = "BCSAU";
  public static final String BCSAW_TICKER = "BCSAW";

  // No longer trade eligible cause 1M history does not exist
  public static final String ETF_TICKER = "ETF";

  // No longer trade eligible
  public static final String MIC_TICKER = "MIC";

  // 3BP on 2/1/2023
  public static final String AMD_TICKER = "AMD";
  public static final String PTON_TICKER = "PTON";
  public static final String MGAM_TICKER = "MGAM";
  public static final String EBET_TICKER = "EBET";

  public static final String I_DONT_EXIST_TICKER = "I_DONT_EXIST";
  public static final String IDK_NON_EXISTENT = "IDK_NON_EXISTENT";
  public static final String NO_FUNDAMENTALS_TICKER = "NO_FUND";
  public static final String NO_INDICATORS_TICKER = "NO_IND";
  public static final String NOT_ELIGIBLE = "NELIG";
  public static final String PBLA_TICKER = "PBLA";
  public static final String FSR = "FSR";
  public static final String IBRX = "IBRX";
  public static final String NVDA_TICKER = "NVDA";
  public static final String MNK_TICKER = "MNK";
  public static final String NKLA_TICKER = "NKLA";
  public static final String LODE_TICKER = "LODE";

  public static void await(AwaitilityWaiter waiter) {
    Awaitility.await()
      .atLeast(10, TimeUnit.MILLISECONDS)
      .atMost(1, TimeUnit.MINUTES)
      .with()
      .pollInterval(10, TimeUnit.MILLISECONDS)
      .until(waiter::isSubscriptionComplete);
  }
}