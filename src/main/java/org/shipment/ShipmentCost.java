package org.shipment;

import java.math.BigDecimal;

public class ShipmentCost {
    private static final BigDecimal LAPOSTE_SMALL_COST = new BigDecimal("1.50");
    private static final BigDecimal LAPOSTE_MEDIUM_COST = new BigDecimal("4.90");
    private static final BigDecimal LAPOSTE_LARGE_COST = new BigDecimal("6.90");
    private static final BigDecimal MONDIALRELAY_SMALL_COST = new BigDecimal("2.0");
    private static final BigDecimal MONDIALRELAY_MEDIUM_COST = new BigDecimal("3.0");
    private static final BigDecimal MONDIALRELAY_LARGE_COST = new BigDecimal("4.0");

    // Methods to get shipment costs
    public BigDecimal getLaPosteSmallCost() {
        return LAPOSTE_SMALL_COST;
    }

    public BigDecimal getLaPosteMediumCost() {
        return LAPOSTE_MEDIUM_COST;
    }

    public BigDecimal getLaPosteLargeCost() {
        return LAPOSTE_LARGE_COST;
    }

    public BigDecimal getMondialRelaySmallCost() {
        return MONDIALRELAY_SMALL_COST;
    }

    public BigDecimal getMondialRelayMediumCost() {
        return MONDIALRELAY_MEDIUM_COST;
    }

    public BigDecimal getMondialRelayLargeCost() {
        return MONDIALRELAY_LARGE_COST;
    }
}
