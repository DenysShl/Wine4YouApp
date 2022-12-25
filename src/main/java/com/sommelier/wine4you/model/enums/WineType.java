package com.sommelier.wine4you.model.enums;

public enum WineType {
    WHITE("White"),
    RED("Red"),
    CHAMPAGNE_SPARKLING("Champagne sparkling"),
    ROSE("Rose");

    private String type;

    WineType(String value) {
        this.type = value;
    }

    public String getType() {
        return type;
    }
}
