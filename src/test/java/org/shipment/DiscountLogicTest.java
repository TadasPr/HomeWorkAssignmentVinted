package org.shipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountLogicTest {
    @Test
    void testDiscountCalculatedDiscountProvided() {
        Shipment shipment = new Shipment("2024-03-16", "L", "LP");
        DiscountLogic discountLogic = new DiscountLogic();
        discountLogic.discountCalculated(shipment);
        assertEquals(6.90, shipment.getShipmentPrice());
        assertEquals(0.0, shipment.getDiscountForShipment());
    }

    @Test
    void testDiscountCalculatedDiscountNotProvided() {
        Shipment shipment = new Shipment("2024-03-16", "M", "LP");
        DiscountLogic discountLogic = new DiscountLogic();
        discountLogic.discountCalculated(shipment);

        assertEquals(4.9, shipment.getShipmentPrice());
        assertEquals(0.0, shipment.getDiscountForShipment());
    }

    @Test
    void testIsMonthSameFirstMonth() {
        DiscountLogic discountLogic = new DiscountLogic();
        boolean result = discountLogic.isMonthSame("2024-03-16");
        assertTrue(result);
    }

    @Test
    void testIsMonthSameSameMonth() {
        DiscountLogic discountLogic = new DiscountLogic();
        discountLogic.isMonthSame("2024-03-16");
        boolean result = discountLogic.isMonthSame("2024-03-25");
        assertTrue(result);
    }

    @Test
    void testIsMonthSameNewMonth() {
        DiscountLogic discountLogic = new DiscountLogic();
        discountLogic.isMonthSame("2024-03-16");
        boolean result = discountLogic.isMonthSame("2024-04-01");
        assertFalse(result);
    }
}