package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.Size;

public class Topping {
    protected String name;
    protected double priceSmall;
    protected double priceMedium;
    protected double priceLarge;
    private Category category;
    private boolean premium;

    public Topping(Category category, boolean premium, String name) {
        this.category = category;
        this.premium = premium;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

