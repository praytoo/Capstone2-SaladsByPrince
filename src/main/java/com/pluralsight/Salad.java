package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Salad implements OrderItem{
    private GreenType green;
    private Size size;
    private List<Topping> toppings;
    private Dressing dressing;
    private String meatName;
    private int extraMeat;
    private String premiumName;
    private int extraPremium;
    private String regularName;
    private int extraRegular;
    private String dressingType;
    private int extraDressing;
    private String quinoa;
    private int quinoaCount;

    public Salad(GreenType green, Size size, String meatName, int extraMeat, String premiumName, int extraPremium, String regularName, int extraRegular, String dressingType, int extraDressing, String quinoa, int quinoaCount) {
        this.green = green;
        this.size = size;
        this.meatName = meatName;
        this.extraMeat = extraMeat;
        this.premiumName = premiumName;
        this.extraPremium = extraPremium;
        this.regularName = regularName;
        this.extraRegular = extraRegular;
        this.dressingType = dressingType;
        this.extraDressing = extraDressing;
        this.quinoa = quinoa;
        this.quinoaCount = quinoaCount;
        this.toppings = new ArrayList<>();
        this.dressing = new Dressing(dressingType);
    }

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
                case SMALL -> price += 10;
                case MEDIUM -> price += 12;
                case LARGE -> price += 15;
            }

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

    Salad chickenCaesar = new Salad(
            Size.SMALL,
            GreenType.LETTUCE,
            List.of(new PremiumTopping("Chicken"), new RegularTopping("Croutons")),
            new Dressing("Caesar"));
    list.add(chickenCaesar);

    Salad oliveOil = new Salad(
            Size.SMALL,
            GreenType.SPINACH,
            List.of(new PremiumTopping("Tuna"), new RegularTopping("Avocado")),
            new Dressing("Olive oil and Lemon"));
    list.add(oliveOil);

    return list;
    }
}
