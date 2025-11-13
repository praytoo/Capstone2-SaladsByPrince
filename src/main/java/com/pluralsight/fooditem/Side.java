package com.pluralsight.fooditem;

import com.pluralsight.order.OrderItem;
import com.pluralsight.toppings.Size;

import java.util.Optional;

//implements the OrderItem interface
public class Side implements OrderItem {
    private String sideType;
    private Size size;
    private double Price;
    private String side;

    //my constructors and getters

    public Side(String sideType, Size size) {
        this.sideType = sideType;
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public double getPrice() {
        return Price;
    }

    public String getSideType() {
        return sideType;
    }

    //optional method to assist JUnit testing
    public static Optional<Size> getSize2(Size size) {
        return Optional.ofNullable(size);
    }

    //OrderItem overridden getCost() method
    @Override
    public double getCost() {
        return switch (size) {
            case SMALL -> 5.0;
            case MEDIUM -> 7.5;
            case LARGE -> 9.0;
        };
    }

    //overriding Object's toString() method
    @Override
    public String toString() {
        return size + " " + sideType + " side";
    }
}




