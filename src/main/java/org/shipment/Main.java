package org.shipment;

public class Main {
    private static String pathToFile = "InputData/input.txt";

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        StringBuilder data = readFile.readFileData(pathToFile);
        ProcessData processData = new ProcessData();
        processData.processDataInput(data);
    }
}