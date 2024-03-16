package org.shipment;

public class Validation {
    public static boolean isValidShipmentProvider(String provider) {
        if (ShipmentProvider.findByName(provider) != null) {
            return true;
        }
        return false;
    }

    public static boolean isValidPackedSize(String packedSize) {
        if (PackedSize.findByName(packedSize) != null) {
            return true;
        }
        return false;
    }
}
