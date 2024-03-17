package org.shipment;

import java.math.BigDecimal;

public class Shipment {
    private final String date;
    private final String packedSize;
    private final String shipmentProvider;
    private BigDecimal discountForShipment;
    private BigDecimal shipmentPrice;

    public Shipment(String date, String packedSize, String shipmentProvider) {
        this.date = date;
        this.packedSize = packedSize;
        this.shipmentProvider = shipmentProvider;
        this.shipmentPrice = BigDecimal.ZERO;
        this.discountForShipment = BigDecimal.ZERO;
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

    public BigDecimal getDiscountForShipment() {
        return discountForShipment;
    }

    public void setDiscountForShipment(BigDecimal discountForShipment) {
        this.discountForShipment = discountForShipment;
    }

    public BigDecimal getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(BigDecimal shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }
}

