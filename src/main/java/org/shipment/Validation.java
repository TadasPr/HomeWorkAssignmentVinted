package org.shipment;

public class Validation {
    public static boolean isValidShipmentProvider(String provider) {
        return switch (provider) {
            case ShipmentProviders.LA_POSTE, ShipmentProviders.MONDIAL_RELAY -> true;
            default -> false;
        };
    }

    public static boolean isValidPackedSize(String packedSize) {
        return switch (packedSize) {
            case PackedSize.SMALL_PACKED, PackedSize.MEDIUM_PACKED, PackedSize.LARGE_PACKED -> true;
            default -> false;
        };
    }
}
