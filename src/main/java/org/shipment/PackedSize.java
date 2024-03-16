package org.shipment;

public enum PackedSize {
    L,
    M,
    S;

    public static PackedSize findByName(String name) {
        //Packed size name is checked if it's exist name will be provided
        //otherwise null will be returned
        PackedSize result = null;
        for (PackedSize size : values()) {
            if (size.name().equalsIgnoreCase(name)) {
                result = size;
                break;
            }
        }
        return result;
    }
}

