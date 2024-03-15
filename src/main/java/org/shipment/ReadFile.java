package org.shipment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    protected static String pathToFile = "../input.txt";
    protected static String ignoredLineMessage = "Ignore";

    public static void fileReader() {
        String data;
        ArrayList<String> stringList = new ArrayList<>();
        try {
            File fileObj = new File(pathToFile);
            Scanner fileScanner = new Scanner(fileObj);
            while (fileScanner.hasNextLine()) {
                data = fileScanner.nextLine();
                for (String word : data.split(" ")) {
                    stringList.add(word);
                }
                if (!Validation.isValidPackedSize(stringList.get(1)) || !Validation.isValidShipmentProvider(stringList.get(2))) {
                    System.out.printf("%s %s%n", data, ignoredLineMessage);
                    stringList.clear();
                    continue;
                }
                ShipmentConstructor shipment = new ShipmentConstructor(stringList.get(0), stringList.get(1), stringList.get(2));
                DiscountLogic.discountCalculated(shipment);
                System.out.printf("%s %.2f %s%n", data, shipment.getShipmentPrice(),
                        shipment.getDiscountForShipment() == 0 ? "-" : String.format("%.2f", shipment.discountForShipment));
                stringList.clear();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
