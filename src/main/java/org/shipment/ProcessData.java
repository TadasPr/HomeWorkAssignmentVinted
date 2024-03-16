package org.shipment;

import java.util.ArrayList;
import java.util.Collections;

public class ProcessData {
    public void processDataInput(StringBuilder data) {
        ArrayList<String> stringList = new ArrayList<>();
        //in this loop data iteration is done, every line of data is stored when new line is reached
        //each line of data is processed separately later
        for (String tempData : data.toString().split("\n")) {
            stringList.clear();
            //when one String line is received, each element separation is done where empty space exist-" " and stored in ArrayList
            Collections.addAll(stringList, tempData.split(" "));
            //Validation of packed size and shipping provider is done to check if it's valid
            if (!Validation.isValidPackedSize(stringList.get(1)) || !Validation.isValidShipmentProvider(stringList.get(2))) {
                System.out.println(printIgnoredResult(tempData));
                continue;
            }
            Shipment shipment = new Shipment(stringList.get(0), stringList.get(1), stringList.get(2));
            DiscountLogic discountLogic = new DiscountLogic();
            //All calculation is done in DiscountLogic class
            discountLogic.discountCalculated(shipment);
            System.out.println(printResult(tempData, shipment.getShipmentPrice(), shipment.getDiscountForShipment()));
        }
    }

    public String printResult(String data, double shipmentPrice, double discountForShipment) {
        //returns string format for data print, also check if discountForShipment was provided or not
        // if not provided instead of number 0 printing it will print symbol "-"
        return String.format("%s %.2f %s", data, shipmentPrice,
                discountForShipment == 0 ? "-" : String.format("%.2f", discountForShipment));
    }

    public String printIgnoredResult(String data) {
        //returns string format for data print, when packed size or shipping provider is unknown
        String ignoredLineMessage = "Ignored";
        if (data == null) {
            return String.format("%s", ignoredLineMessage);
        }
        return String.format("%s %s", data, ignoredLineMessage);
    }
}
