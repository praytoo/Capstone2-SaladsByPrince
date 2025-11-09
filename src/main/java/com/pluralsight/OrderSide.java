package com.pluralsight;

public class OrderSide implements OrderItem {
    private String sideType;
    private Size size;

    public OrderSide(String sideType, Size size) {
        this.sideType = sideType;
        this.size = size;
    }

    public String getSideType() {
        return sideType;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return switch (size) {
            case SMALL -> 5.0;
            case MEDIUM -> 7.5;
            case LARGE -> 9.0;
        };
    }

    @Override
    public String toString() {
        return size + " " + sideType + " side";
    }
}

