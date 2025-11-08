package com.pluralsight;

import static com.pluralsight.Size.*;

public class Side implements OrderItem {
    private String side;
    private double Price;
    private Size size;

    public Side(String side, Size size) {
        this.side = side;
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public String getSide() {
        return side;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public double getCost() {
        return switch(size) {
            case SMALL -> 5.0;
            case MEDIUM -> 7.5;
            case LARGE -> 9.0;
            default -> throw new IllegalStateException("Unexpected value: " + size);
        };
    }
}
