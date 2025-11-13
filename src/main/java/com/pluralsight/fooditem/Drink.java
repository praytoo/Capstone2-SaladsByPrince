package com.pluralsight.fooditem;

import com.pluralsight.order.OrderItem;
import com.pluralsight.toppings.Size;

import java.util.Optional;

//implements the OrderItem interface
public class Drink implements OrderItem {
    private String flavor;
    private Size size;

    //my constructors and getters

    public Drink(String flavor, Size size) {
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    //Optional<> method for JUnit test (which help you handle null scenarios)
    public static Optional<Size> getSize2(Size size) {
        return Optional.ofNullable(size);
    }

    public Size getSize() {
        return size;
    }

    //OrderItem overridden getCost() method
    @Override
    public double getCost() {
        return getSize2(size)
                .map(s -> switch (s) {
                    case SMALL -> 5.0;
                    case MEDIUM -> 6.5;
                    case LARGE -> 7.0;
                })
                .orElse(0.0);

    }
}
