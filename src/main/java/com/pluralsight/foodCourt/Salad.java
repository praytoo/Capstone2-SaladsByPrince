package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.GreenType;
import com.pluralsight.foodCourt.Size;
import java.util.ArrayList;
import java.util.List;

public class Salad implements OrderItem {
    private Size size;
    private GreenType green;
    private List<Topping> toppings = new ArrayList<>();
    private Dressing dressing;
    private String name; // optional display name

    public Salad(Size size, GreenType green, List<Topping> toppings, Dressing dressing) {
        this.size = size;
        this.green = green;
        if (toppings != null) this.toppings = new ArrayList<>(toppings);
        this.dressing = dressing;
        this.name = green.name() + " Salad";
    }

    public Salad(Size size, GreenType green, Dressing dressing) {
        this(size, green, null, dressing);
    }

    public void addTopping(Topping t) { toppings.add(t); }
    public Size getSize() { return size; }
    public GreenType getGreen() { return green; }
    public List<Topping> getToppings() { return toppings; }
    public Dressing getDressing() { return dressing; }

    @Override
    public double getCost() {
        double cost = switch(size) {
            case SMALL -> 3.50;
            case MEDIUM -> 8.50;
            default -> 9.00;
        };
        for (Topping t : toppings) {
            if (t instanceof PremiumTopping) {
                cost += t.getCost(size, false);
            } else {
                cost += t.getCost(size, false);
            }
        }
        return cost;
    }

    public static List<Salad> getSignatureSalads() {
        List<Salad> list = new ArrayList<>();
        list.add(new Salad(Size.SMALL, GreenType.ARUGULA,
                List.of(new PremiumTopping("Beef"), new RegularTopping("Raisins")),
                new Dressing("Balsamic Vinaigrette and Honey")));
        list.add(new Salad(Size.MEDIUM, GreenType.LETTUCE,
                List.of(new PremiumTopping("Chicken")),
                new Dressing("Caesar")));
        list.add(new Salad(Size.MEDIUM, GreenType.SPINACH,
                List.of(new PremiumTopping("Tuna")),
                new Dressing("Olive Oil and Lemon")));
        return list;
    }

    @Override
    public String getDisplayName() {
        return size + " " + green + " Salad";
    }
}

