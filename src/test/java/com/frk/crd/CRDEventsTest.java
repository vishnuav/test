package com.frk.crd;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.frk.crd.db.utils.TestConstants;
import com.frk.crd.model.Allocation;
import com.frk.crd.model.CRDEvents;
import com.frk.crd.model.Order;
import com.frk.crd.model.Security;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
class CRDEventsTest {

    @Test
    void serialize() throws IOException {
        File file = new File("src/test/resources/CRD_Order_Broadcast.xml");
        String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        xmlMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        xmlMapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            CRDEvents crdEvents = xmlMapper.readValue(xml, CRDEvents.class);
            Assertions.assertNotNull(crdEvents);
            Order order = crdEvents.getOrder();
            Assertions.assertNotNull(order);
            Assertions.assertEquals(5072922850L, order.getId());
            Assertions.assertEquals("update", order.getOperation());
            Assertions.assertEquals("2024-04-23 10:22:17.985", order.getPlaceDate());
            Assertions.assertEquals("2024-04-23 00:00:00", order.getSettleDate());
            Assertions.assertEquals("2024-04-23 00:00:00", order.getTradeDate());

            Security security = order.getSecurity();
            Assertions.assertNotNull(security);

            Assertions.assertEquals("160853MS3", security.getCusip());
            Assertions.assertEquals("BFXSKM7", security.getSedol());
            Assertions.assertEquals("669986", security.getSecurityId());
            Assertions.assertEquals("MUNI", security.getSecurityTypeCode());
            Assertions.assertEquals(7100000.0, security.getTargetQuantity());
            Assertions.assertEquals(7100000.0, security.getExecutionQuantity());


            List<Allocation> allocations = order.getAllocations();
            Assertions.assertFalse(allocations.isEmpty());
            Assertions.assertEquals(2, allocations.size());
            Allocation first = allocations.get(0);
            Assertions.assertNotNull(first);
            Assertions.assertEquals(1257055829L, first.getId());
            Assertions.assertEquals("update", first.getOperation());
            Assertions.assertEquals("FRIVERA", first.getFundManager());
            Assertions.assertEquals(6900000.0, first.getTargetQuantity());
            Assertions.assertEquals(6900000., first.getExecutingQuantity());

            Allocation second = allocations.get(1);
            Assertions.assertNotNull(second);
            Assertions.assertEquals(1257073760L, second.getId());
            Assertions.assertEquals("update", second.getOperation());
            Assertions.assertEquals("JWILEY", second.getFundManager());
            Assertions.assertEquals(200000, second.getTargetQuantity());
            Assertions.assertEquals(200000, second.getExecutingQuantity());

            String toXML = xmlMapper.writeValueAsString(crdEvents);
            Assertions.assertNotNull(toXML);
            Assertions.assertEquals(TestConstants.EXPECTED_XML, toXML);
        } catch (Throwable e) {
            log.error("Unable to parse XML", e);
            Assertions.fail("Unable to parse XML " + xml);
        }
    }
}