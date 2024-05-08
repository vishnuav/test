package com.frk.crd.db.utils;

import com.frk.crd.utils.AwaitilityWaiter;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;

public class TestConstants {

  public final static String EXPECTED_XML = "<CRDEvents><order><placeDate>2024-04-23 10:22:17.985</placeDate><settleDate>2024-04-23 00:00:00</settleDate><tradeDate>2024-04-23 00:00:00</tradeDate><status>ACCT</status><manager>RDONATE</manager><trader>RDONATE</trader><execBroker>JPMS5</execBroker><id>5072922850</id><operation>update</operation><security><cusip>160853MS3</cusip><sedol>BFXSKM7</sedol><securityId>669986</securityId><securityTypeCode>MUNI</securityTypeCode><invClassCode>DEBT</invClassCode><transactionType>SELLL</transactionType><targetQuantity>7100000.0</targetQuantity><executionQuantity>7100000.0</executionQuantity></security><allocations><allocations><fundManager>FRIVERA</fundManager><id>1257055829</id><operation>update</operation><targetQuantity>6900000.0</targetQuantity><executingQuantity>6900000.0</executingQuantity></allocations><allocations><fundManager>JWILEY</fundManager><id>1257073760</id><operation>update</operation><targetQuantity>200000.0</targetQuantity><executingQuantity>200000.0</executingQuantity></allocations></allocations></order></CRDEvents>";

  public static void await(AwaitilityWaiter waiter) {
    Awaitility.await()
        .atLeast(10, TimeUnit.MILLISECONDS)
        .atMost(1, TimeUnit.MINUTES)
        .with()
        .pollInterval(10, TimeUnit.MILLISECONDS)
        .until(waiter::isSubscriptionComplete);
  }
}