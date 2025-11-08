package com.pluralsight;

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

    public Size getSize() {
        return size;
    }

    @Override
    public double getCost() {
        return switch(size) {
            case SMALL -> 4.0;
            case MEDIUM -> 5.5;
            case LARGE -> 6.0;
            default -> throw new IllegalStateException("Unexpected value: " + size);
        };
    }
}
