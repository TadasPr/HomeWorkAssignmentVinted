package org.shipment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiscountLogic {
    protected static int lpLargeCounter = 0;
    protected static double originalCost;
    protected static double actualDiscount;
    protected static int startingYear = 0;
    protected static int startingMonth = 0;
    protected static double monthLimitAccumulated = DiscountLimitConstant.MONTHLY_LIMIT_DISCOUNT;

    public static void discountCalculated(ShipmentConstructor shipment) {
        originalCost = OriginalShipmentPrice.originalPrice(shipment.getShipmentProvider(), shipment.getPackedSize());
        double discountProvided = discountForPacked(shipment);
        if (discountProvided != -1.0) {
            shipment.setShipmentPrice(afterDiscountPrice(discountProvided, isMonthSame(shipment.date)));
            shipment.setDiscountForShipment(actualDiscount);
        } else {
            shipment.setShipmentPrice(originalCost);
            shipment.setDiscountForShipment(0.0);
        }
    }

    private static double discountForPacked(ShipmentConstructor shipment) {
        if (shipment.getPackedSize().equals(PackedSize.LARGE_PACKED) && shipment.getShipmentProvider().equals(ShipmentProviders.LA_POSTE)) {
            if (isMonthSame(shipment.getDate())) {
                lpLargeCounter++;
                if (lpLargeCounter == 3) {
                    return ShipmentCost.LAPOSTE_LARGE;
                }
            } else {
                lpLargeCounter = 0;
            }
        } else if (shipment.getPackedSize().equals(PackedSize.SMALL_PACKED)) {
            return originalCost - Math.min(ShipmentCost.MONDIALRELAY_SMALL, ShipmentCost.LAPOSTE_SMALL);
        }
        return -1.0;
    }

    private static double afterDiscountPrice(double discountProvided, boolean sameMonth) {
        if (!sameMonth) {
            monthLimitAccumulated = DiscountLimitConstant.MONTHLY_LIMIT_DISCOUNT;
        }
        if (monthLimitAccumulated > 0) {
            double afterDiscountCost = (monthLimitAccumulated >= discountProvided) ? originalCost - discountProvided : originalCost - monthLimitAccumulated;
            monthLimitAccumulated = monthLimitAccumulated - (originalCost - afterDiscountCost);
            actualDiscount = originalCost - afterDiscountCost;
            return afterDiscountCost;
        }
        return originalCost;
    }

    private static boolean isMonthSame(String date) {
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

