package org.shipment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/shipingproviderdata.csv")
    void testisValidShipmentProvider(String shippingProvider, boolean expectedResult) {
        assertEquals(expectedResult, Validation.isValidShipmentProvider(shippingProvider),"Expected result should match");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/packedtestdata.csv")
    void isValidPackedSizeTest(String packedSize, boolean expectedResult) {
        assertEquals(Validation.isValidPackedSize(packedSize), expectedResult, "Expected result should match");
    }
}