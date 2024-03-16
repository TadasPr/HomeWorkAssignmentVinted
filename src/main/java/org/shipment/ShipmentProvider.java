package org.shipment;

public enum ShipmentProvider {
    MR,
    LP;

    public static ShipmentProvider findByName(String name) {
        //Shipping provider name is checked if it's exist name will be provided
        //otherwise null will be returned
        ShipmentProvider result = null;
        for (ShipmentProvider shipmentProvider : values()) {
            if (shipmentProvider.name().equalsIgnoreCase(name)) {
                result = shipmentProvider;
                break;
            }
        }
        return result;
    }
}

