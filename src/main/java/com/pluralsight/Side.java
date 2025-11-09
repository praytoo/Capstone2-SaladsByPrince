package com.pluralsight;

public class Side implements OrderItem {
    private Side side;
    private double Price;
    private Size size;

    @Override
    public String toString() {
        return size + " " + side;
    }

    public Side(Side side, Size size) {
        this.side = side;
        this.size = size;
    }

    public Side(String side) {
        //this(side, Size.SMALL);
    }

    public Size getSize() {
        return size;
    }

    public Side getSide() {
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
