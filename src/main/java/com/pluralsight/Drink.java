package com.pluralsight;

import java.util.Optional;

import static com.pluralsight.Size.*;

public class Drink implements OrderItem{
    private String flavor;
    private Size size;

    public Drink(String flavor, Size size) {
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public static Optional<Size> getSize2(Size size){
        return Optional.ofNullable(size);
    }

    public Size getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return getSize2(size)
                .map(s -> switch (s) {
                    case SMALL -> 4.0;
                    case MEDIUM -> 5.5;
                    case LARGE -> 6.0;
                })
                .orElse(0.0);

    }
}
