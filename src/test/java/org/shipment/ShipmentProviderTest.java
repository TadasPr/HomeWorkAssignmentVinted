package org.shipment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ShipmentProviderTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/dataprovider.csv")
    void testFindByNameProvider(String provider, String expected) {
        String actual = String.valueOf(ShipmentProvider.findByName(provider));
        assertEquals(expected, actual);
    }
}