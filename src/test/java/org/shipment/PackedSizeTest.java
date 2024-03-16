package org.shipment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class PackedSizeTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/packedsizedata.csv")
    void testFindByNamePacked(String packedSize, String expectedResult) {
        String actual = String.valueOf(PackedSize.findByName(packedSize));
        assertEquals(expectedResult,actual);
    }
}