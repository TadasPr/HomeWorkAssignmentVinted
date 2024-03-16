package org.shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class OriginalShipmentPriceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/shippingpricedata.csv")
    void testOriginalPrice(String packedSize,String shippingProvider, String expected) {
        double actual = OriginalShipmentPrice.originalPrice(shippingProvider,packedSize);
        double expectedValue = Double.parseDouble(expected);
        assertEquals(expectedValue, actual);
    }
}