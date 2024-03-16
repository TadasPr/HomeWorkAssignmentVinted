package org.shipment;

public class Shipment {
    private String date;
    private String packedSize;
    private String shipmentProvider;
    private double discountForShipment;
    private double shipmentPrice;

    public Shipment(String date, String packedSize, String shipmentProvider) {
        this.date = date;
        this.packedSize = packedSize;
        this.shipmentProvider = shipmentProvider;
        this.shipmentPrice = 0.0;
        this.discountForShipment = 0.0;
    }

    public String getDate() {
        return date;
    }

    public String getPackedSize() {
        return packedSize;
    }

    public String getShipmentProvider() {
        return shipmentProvider;
    }

    public double getDiscountForShipment() {
        return discountForShipment;
    }

    public void setDiscountForShipment(double discountForShipment) {
        this.discountForShipment = discountForShipment;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }
}

