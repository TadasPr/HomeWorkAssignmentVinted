package org.shipment;

public class OriginalShipmentPrice {
    //Method is responsible to return shipping cost by packed size and shipping provider
    //before any discount (original prise for shipping)
    public static double originalPrice(String shipmentProvider, String packedSize) {
        return switch (PackedSize.valueOf(packedSize)) {
            case S -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> ShipmentCost.LAPOSTE_SMALL;
                case MR -> ShipmentCost.MONDIALRELAY_SMALL;
            };
            case M -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> ShipmentCost.LAPOSTE_MEDIUM;
                case MR -> ShipmentCost.MONDIALRELAY_MEDIUM;
            };
            case L -> switch (ShipmentProvider.valueOf(shipmentProvider)) {
                case LP -> ShipmentCost.LAPOSTE_LARGE;
                case MR -> ShipmentCost.MONDIALRELAY_LARGE;
            };
        };
    }
}
