package com.pluralsight.foodCourt;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements OrderItem {
    private Size size;
    private String crust;
    private List<Topping> toppings = new ArrayList<>();
    private boolean stuffedCrust;
    private String name;

    public Pizza(Size size, String crust, List<Topping> toppings, boolean stuffedCrust) {
        this.size = size;
        this.crust = crust;
        if (toppings != null) this.toppings = new ArrayList<>(toppings);
        this.stuffedCrust = stuffedCrust;
        this.name = "Pizza (" + crust + ")";
    }

    @Override
    public double getCost() {
        double base = switch(size) {
            case SMALL -> 6.00;
            case MEDIUM -> 9.00;
            case LARGE -> 12.00;
            default -> 12.00;
        };
        if (stuffedCrust) base += 2.00;
        for (Topping t : toppings)
            base += t.getCost(size, false);
        return base;
    }

    @Override
    public String getDisplayName() {
        return size + " " + crust + " Pizza" + (stuffedCrust ? " [Stuffed Crust]" : "");
    }
}

