package org.shipment;

public class OriginalShipmentPrice {
    public static double originalPrice(String shipmentProvider, String packedSize) {
        if (packedSize.equals(PackedSize.SMALL_PACKED)) {
            return switch (shipmentProvider) {
                case ShipmentProviders.LA_POSTE -> ShipmentCost.LAPOSTE_SMALL;
                case ShipmentProviders.MONDIAL_RELAY -> ShipmentCost.MONDIALRELAY_SMALL;
                default -> -1.0;
            };
        } else if (packedSize.equals(PackedSize.MEDIUM_PACKED)) {
            return switch (shipmentProvider) {
                case ShipmentProviders.LA_POSTE -> ShipmentCost.LAPOSTE_MEDIUM;
                case ShipmentProviders.MONDIAL_RELAY -> ShipmentCost.MONDIALRELAY_MEDIUM;
                default -> -1.0;
            };
        } else if (packedSize.equals(PackedSize.LARGE_PACKED)) {
            return switch (shipmentProvider) {
                case ShipmentProviders.LA_POSTE -> ShipmentCost.LAPOSTE_LARGE;
                case ShipmentProviders.MONDIAL_RELAY -> ShipmentCost.MONDIALRELAY_LARGE;
                default -> -1.0;
            };
        }
        return -1.0;
    }
}
