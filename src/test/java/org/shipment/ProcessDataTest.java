package org.shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProcessDataTest {

    @Test
    void processDataInput() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/printresultdata.csv")
    void printResult(String data, String originalPrice, String discount, String expectedResult) {
        ProcessData processData = new ProcessData();
        BigDecimal originalPriceNumber = BigDecimal.valueOf(Double.parseDouble(originalPrice));
        BigDecimal discountNumber = BigDecimal.valueOf(Double.parseDouble(discount));
        String actualResult = processData.printResult(data,originalPriceNumber, discountNumber);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataignored.csv")
    void printIgnoredResult(String data, String expectedResult) {
        ProcessData processData = new ProcessData();;
        assertEquals(expectedResult, processData.printIgnoredResult(data));
    }
}