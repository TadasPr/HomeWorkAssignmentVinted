package org.shipment;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void testReadFileDataFileExists() throws IOException {
        ReadFile readFile = new ReadFile();
        String pathToFile = "src/test/resources/sample.txt";
        Files.writeString(Path.of(pathToFile), "Sample text data");
        StringBuilder result = readFile.readFileData(pathToFile);
        assertEquals("Sample text data\n", result.toString());
        Files.deleteIfExists(Path.of(pathToFile));
    }

    @Test
    void testReadFileDataFileDoesNotExist() {
        ReadFile readFile = new ReadFile();
        String pathToFile = "nonexistent-file.txt";
        StringBuilder result = readFile.readFileData(pathToFile);
        assertTrue(result.toString().isEmpty());
    }
}