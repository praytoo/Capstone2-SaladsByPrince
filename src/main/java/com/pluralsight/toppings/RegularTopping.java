package com.pluralsight.toppings;

public class RegularTopping extends Topping {
    //extends Topping so super is called in child class constructor
    public RegularTopping(String name) {
        super(name);
    }
}
