package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Salad implements OrderItem{
    private Size size;
    private GreenType green;
    private List<Topping> toppings;
    private Dressing dressing;

    public Salad(Size size, GreenType green, List<Topping> toppings, Dressing dressing) {
        this.size = size;
        this.green = green;
        this.toppings = toppings;
        this.dressing = dressing;
    }

    public void setDressing(Dressing dressing) {
        this.dressing = dressing;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public Size getSize() {
        return size;
    }

    public GreenType getGreen() {
        return green;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public Dressing getDressing() {
        return dressing;
    }

    public double calculatePrice(){
            double price = 0;

            // Base price by size
            switch (size) {
                case SMALL -> price += 8;
                case MEDIUM -> price += 10;
                case LARGE -> price += 12;
            }

            // Add toppings
            for (Topping topping : toppings) {
                if (topping instanceof MeatTopping) {
                    price += switch (size) {
                        case SMALL -> 2;
                        case MEDIUM -> 3;
                        case LARGE -> 4;
                    };
                } else if (topping instanceof PremiumTopping) {
                    price += switch (size) {
                        case SMALL -> 1.5;
                        case MEDIUM -> 2;
                        case LARGE -> 2.5;
                    };
                } else if (topping instanceof RegularTopping) {
                    price += switch (size) {
                        case SMALL -> 1;
                        case MEDIUM -> 1.5;
                        case LARGE -> 2;
                    };
                } else if (topping instanceof QuinoaTopping) {
                    price += switch (size) {
                        case SMALL -> 1;
                        case MEDIUM -> 1.5;
                        case LARGE -> 2;
                    };
                }
            }

            return price;
        }

    @Override
    public double getCost() {
        return calculatePrice();
    }

    public void addTopping(Topping topping){
        toppings.add(topping);
    }

    public static List<Salad> getSignatureSalads(){
    List<Salad> list = new ArrayList<>();

    Salad balsamicHoney = new Salad(
            Size.SMALL,
            GreenType.ARUGULA,
            List.of(new PremiumTopping("Beef"), new RegularTopping("Raisins")),
            new Dressing("Balsamic Vinaigrette and Honey"));
    list.add(balsamicHoney);

    Salad chickenCasear = new Salad(
            Size.SMALL,
            GreenType.LETTUCE,
            List.of(new PremiumTopping("Chicken"), new RegularTopping("Croutons")),
            new Dressing("Caesar"));
    list.add(chickenCasear);

    Salad oliveOil = new Salad(
            Size.SMALL,
            GreenType.SPINACH,
            List.of(new PremiumTopping("Tuna"), new RegularTopping("Avocado")),
            new Dressing("Olive oil and Lemon"));
    list.add(oliveOil);

    return list;
    }
}
