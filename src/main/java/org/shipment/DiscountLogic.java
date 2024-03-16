package org.shipment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountLogic {
    private static int lpLargeCounter = 0;
    protected double originalCost;
    private double actualDiscount;
    private int startingYear = 0;
    private int startingMonth = 0;
    private double monthLimitAccumulated = 10;

    public void discountCalculated(Shipment shipment) {
        boolean sameMonth = isMonthSame(shipment.getDate()); // storing value if same month
        originalCost = OriginalShipmentPrice.originalPrice(shipment.getShipmentProvider(), shipment.getPackedSize()); // storing value what is original price for shipping
        double discountProvided = discountForPacked(shipment, sameMonth);
        if (discountProvided != -1) { //if discount was provided setting these values to use later for printing
            shipment.setShipmentPrice(afterDiscountPrice(discountProvided, sameMonth));
            shipment.setDiscountForShipment(actualDiscount);
        } else { // if discount not provided setting original price for shipping and discount 0
            shipment.setShipmentPrice(originalCost);
            shipment.setDiscountForShipment(0);
        }
    }

    private double discountForPacked(Shipment shipment, boolean sameMonth) {
        if (!sameMonth) {
            lpLargeCounter = 0; //Reset counter if new month
        }
        //Below rule to check if packed size is "L" and shipping provider is "LP", and how many times this repeated
        // if it's third time this month it will provide free shipping
        if (PackedSize.valueOf(shipment.getPackedSize()).equals(PackedSize.L) && ShipmentProvider.valueOf(shipment.getShipmentProvider()).equals(ShipmentProvider.LP)) {
            lpLargeCounter++;
            if (lpLargeCounter == 3) {
                return ShipmentCost.LAPOSTE_LARGE;
            }
        } else if (PackedSize.valueOf(shipment.getPackedSize()).equals(PackedSize.S)) { //If it's small packed it will provide lowes price for shipping
            return originalCost - Math.min(ShipmentCost.MONDIALRELAY_SMALL, ShipmentCost.LAPOSTE_SMALL);
        }
        return -1;
    }

    private double afterDiscountPrice(double discountProvided, boolean sameMonth) {
        if (!sameMonth) {
            monthLimitAccumulated = 10; //if month not same reset monthLimitAccumulated to 10
        }
        if (monthLimitAccumulated > 0) {
            //checking if monthLimitAccumulated is greater than or equal to discount provided if yes - full discountProvided is used
            //else if monthLimitAccumulated not enough - reducing cost what is left in monthLimitAccumulated
            double afterDiscountCost = (monthLimitAccumulated >= discountProvided) ? originalCost - discountProvided : originalCost - monthLimitAccumulated;
            monthLimitAccumulated = monthLimitAccumulated - (originalCost - afterDiscountCost);
            //saving what actual discount was provided
            actualDiscount = originalCost - afterDiscountCost;
            return afterDiscountCost;
        }
        actualDiscount = 0;
        return originalCost;
    }

    //Method isMonthSame() return boolean value if month is still same or not
    private boolean isMonthSame(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFormat = LocalDate.parse(date, format);
        if (startingYear == 0 && startingMonth == 0) {
            startingMonth = dateFormat.getMonthValue();
            startingYear = dateFormat.getYear();
            return true;
        } else if (startingYear == dateFormat.getYear() && startingMonth == dateFormat.getMonthValue()) {
            return true;
        }
        startingMonth = dateFormat.getMonthValue();
        startingYear = dateFormat.getYear();
        return false;
    }
}

