package com.sommelier.wine4you.model.enums;

public enum ShippingType {
    COURIER("Courier delivery"),
    OFFICES("Self-delivery from post offices"),
    MARKETPLACE("Self-delivery from WINE4YOU Marketplace");

    private String value;

    ShippingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
