package com.pluralsight.foodCourt;

import java.util.ArrayList;
import java.util.List;

public class Taco implements OrderItem {
    private Size size;
    private String shell;
    private List<Topping> toppings = new ArrayList<>();
    private boolean doubleShell;
    private String name;

    public Taco(Size size, String shell, List<Topping> toppings, boolean doubleShell) {
        this.size = size;
        this.shell = shell;
        if (toppings != null) this.toppings = new ArrayList<>(toppings);
        this.doubleShell = doubleShell;
        this.name = "Taco (" + shell + ")";
    }

    @Override
    public double getCost() {
        double base = switch(size) {
            case SMALL -> 3.50;
            case MEDIUM -> 6.00;
            case LARGE -> 8.00;
            default -> 8.00;
        };
        if (doubleShell) base += 1.00;
        for (Topping t : toppings) base += t.getCost(size, false);
        return base;
    }

    @Override
    public String getDisplayName() {
        return name + (doubleShell ? " [Double Shell]" : "");
    }
}

