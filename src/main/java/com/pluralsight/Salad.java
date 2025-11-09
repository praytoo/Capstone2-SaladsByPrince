package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Salad(Size size, GreenType green, List<Topping> toppings, Dressing dressing, int extraMeat, int extraPremium, int extraRegular, int extraDressing, int quinoaCount) {
        this.size = size;
        this.green = green;
        this.toppings = toppings;
        this.dressing = dressing;
        this.extraMeat = extraMeat;
        this.extraPremium = extraPremium;
        this.extraRegular = extraRegular;
        this.extraDressing = extraDressing;
        this.quinoaCount = quinoaCount;
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
        if (extraDressing > 0) {
            price += switch (size) {
                case SMALL -> 0.5 * extraDressing;
                case MEDIUM -> 0.75 * extraDressing;
                case LARGE -> 1.0 * extraDressing;
            };
        }
        if (extraMeat > 0) {
            price += switch (size) {
                case SMALL -> 0.5 * extraMeat;
                case MEDIUM -> 0.75 * extraMeat;
                case LARGE -> 1.0 * extraMeat;
            };
        }
        if (extraPremium > 0) {
            price += switch (size) {
                case SMALL -> 0.5 * extraPremium;
                case MEDIUM -> 0.75 * extraPremium;
                case LARGE -> 1.0 * extraPremium;
            };
        }
        if (extraRegular > 0) {
            price += switch (size) {
                case SMALL -> 0.5 * extraRegular;
                case MEDIUM -> 0.75 * extraRegular;
                case LARGE -> 1.0 * extraRegular;
            };
        }
        if (quinoaCount > 0) {
            price += switch (size) {
                case SMALL -> 0.5 * quinoaCount;
                case MEDIUM -> 0.75 * quinoaCount;
                case LARGE -> 1.0 * quinoaCount;
            };
        }
        return price;
    }

    @Override
    public double getCost() {
        return calculatePrice();
    }

    public Optional<List<Topping>> getToppings2(){
        return Optional.ofNullable(toppings);
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

    @Override
    public String toString() {
        return size + " " + green + " salad with " + toppings.get(0).getName() + ", " + toppings.get(toppings.size() - 1).getName() + " and " + dressing.getName();
    }

}
