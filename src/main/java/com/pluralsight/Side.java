package com.pluralsight;

import java.util.Optional;

import static com.pluralsight.Size.*;

public class Side implements OrderItem {
    private String side;
    private double Price;
    private Size size;

    @Override
    public String toString() {
        return size + " " + side;
    }

    public Side(String side, Size size) {
        this.side = side;
        this.size = size;
    }

    public Side(String side) {
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
    //optional method
    public static Optional<Size> getSize2(Size size) {
        return Optional.ofNullable(size);
    }


    @Override
    public double getCost() {
        return getSize2(size)
                .map(s -> switch (s) {
                    case SMALL -> 5.0;
                    case MEDIUM -> 7.5;
                    case LARGE -> 9.0;
                })
                .orElse(0.0);

    }
}
