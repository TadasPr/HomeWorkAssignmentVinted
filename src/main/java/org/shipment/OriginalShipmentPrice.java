package org.shipment;

import java.math.BigDecimal;

public class OriginalShipmentPrice {
    //Method is responsible to return shipping cost by packed size and shipping provider
    //before any discount (original prise for shipping)
    public static BigDecimal originalPrice(String shipmentProvider, String packedSize) {
        ShipmentCost shipmentCost = new ShipmentCost();
        return switch (PackedSize.valueOf(packedSize)) {
            case S -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> shipmentCost.getLaPosteSmallCost();
                case MR -> shipmentCost.getMondialRelaySmallCost();
            };
            case M -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> shipmentCost.getLaPosteMediumCost();
                case MR -> shipmentCost.getMondialRelayMediumCost();
            };
            case L -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> shipmentCost.getLaPosteLargeCost();
                case MR -> shipmentCost.getMondialRelayLargeCost();
            };
        };
    }
}
