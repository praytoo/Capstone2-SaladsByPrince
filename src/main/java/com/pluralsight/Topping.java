package com.pluralsight;

public abstract class Topping {
    private String name;
    private double basePriceSmall;
    private double basePriceMedium;
    private double basePriceLarge;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBasePriceSmall() {
        return basePriceSmall;
    }

    public double getBasePriceMedium() {
        return basePriceMedium;
    }

    public double getBasePriceLarge() {
        return basePriceLarge;
    }

    public double getCost(Size size, boolean extra) {

    }
}
