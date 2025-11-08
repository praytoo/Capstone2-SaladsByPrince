package com.pluralsight.foodCourt;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderItem {
    private Size size;
    private String bread;
    private List<Topping> toppings = new ArrayList<>();
    private boolean toasted;
    private String name;

    public Sandwich(Size size, String bread, List<Topping> toppings, boolean toasted) {
        this.size = size;
        this.bread = bread;
        if (toppings != null) this.toppings = new ArrayList<>(toppings);
        this.toasted = toasted;
        this.name = "Sandwich (" + bread + ")";
    }

    @Override
    public double getCost() {
        double base = switch(size) {
            case SMALL -> 5.00;
            case MEDIUM -> 7.50;
            case LARGE -> 9.00;
            default -> 9.00;
        };
        if (toasted) base += 0.75;
        for (Topping t : toppings) base += t.getCost(size, false);
        return base;
    }

    @Override
    public String getDisplayName() {
        return name + (toasted ? " [Toasted]" : "");
    }
}

