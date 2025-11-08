package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.Size;

public abstract class Topping {
    protected String name;
    protected double priceSmall;
    protected double priceMedium;
    protected double priceLarge;

    public Topping(String name, double s, double m, double l) {
        this.name = name;
        this.priceSmall = s;
        this.priceMedium = m;
        this.priceLarge = l;
    }

    public double getCost(Size size, boolean extra) {
        double base = switch(size) {
            case SMALL -> priceSmall;
            case MEDIUM -> priceMedium;
            default -> priceLarge;
        };
        return base;
    }

    public String getName() { return name; }
}

