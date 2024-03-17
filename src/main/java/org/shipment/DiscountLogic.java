package org.shipment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountLogic {
    private static int lpLargeCounter = 0;
    private BigDecimal originalCost;
    private BigDecimal actualDiscount;
    private static int startingYear = 0;
    private static int startingMonth = 0;
    private static BigDecimal monthLimitAccumulated = new BigDecimal(10);

    public void discountCalculated(Shipment shipment) {
        boolean sameMonth = isMonthSame(shipment.getDate()); // storing value if same month
        originalCost = OriginalShipmentPrice.originalPrice(shipment.getShipmentProvider(), shipment.getPackedSize()); // storing value what is original price for shipping
        BigDecimal discountProvided = discountForPacked(shipment, sameMonth);
        if (discountProvided.compareTo(BigDecimal.valueOf(-1)) != 0) { //if discount was provided setting these values to use later for printing
            shipment.setShipmentPrice(afterDiscountPrice(discountProvided, sameMonth));
            shipment.setDiscountForShipment(actualDiscount);
        } else { // if discount not provided setting original price for shipping and discount 0
            shipment.setShipmentPrice(originalCost);
            shipment.setDiscountForShipment(BigDecimal.ZERO);
        }
    }

    private BigDecimal discountForPacked(Shipment shipment, boolean sameMonth) {
        ShipmentCost shipmentCost = new ShipmentCost();
        if (!sameMonth) {
            lpLargeCounter = 0; //Reset counter if new month
        }
        //Below rule to check if packed size is "L" and shipping provider is "LP", and how many times this repeated
        // if it's third time this month it will provide free shipping
        if (PackedSize.valueOf(shipment.getPackedSize()).equals(PackedSize.L) && ShipmentProvider.valueOf(shipment.getShipmentProvider()).equals(ShipmentProvider.LP)) {
            lpLargeCounter++;
            if (lpLargeCounter == 3) {
                return shipmentCost.getLaPosteLargeCost();
            }
        } else if (PackedSize.valueOf(shipment.getPackedSize()).equals(PackedSize.S)) { //If it's small packed it will provide lowes price for shipping
            return originalCost.subtract(shipmentCost.getLaPosteSmallCost().min(shipmentCost.getMondialRelaySmallCost()));
        }
        return BigDecimal.valueOf(-1);
    }

    private BigDecimal afterDiscountPrice(BigDecimal discountProvided, boolean sameMonth) {
        if (!sameMonth) {
            monthLimitAccumulated = BigDecimal.valueOf(10); //if month not same reset monthLimitAccumulated to 10
        }
        if (monthLimitAccumulated.compareTo(BigDecimal.ZERO) > 0) {
            //checking if monthLimitAccumulated is greater than or equal to discount provided if yes - full discountProvided is used
            //else if monthLimitAccumulated not enough - reducing cost what is left in monthLimitAccumulated
            BigDecimal afterDiscountCost = (monthLimitAccumulated.compareTo(discountProvided) >= 0) ? originalCost.subtract(discountProvided) : originalCost.subtract(monthLimitAccumulated);
            BigDecimal discountApplied = originalCost.subtract(afterDiscountCost);
            monthLimitAccumulated = monthLimitAccumulated.subtract(discountApplied);
            //saving what actual discount was provided
            actualDiscount = originalCost.subtract(afterDiscountCost);
            return afterDiscountCost;
        }
        actualDiscount = BigDecimal.ZERO;
        return originalCost;
    }

    //Method isMonthSame() return boolean value if month is still same or not
    public boolean isMonthSame(String date) {
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

