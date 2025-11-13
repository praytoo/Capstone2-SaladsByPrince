package com.pluralsight.toppings;

public class Topping {
    private String name;

    //getter
    public String getName() {
        return name;
    }
//parent class constructor that all child classes include in their constructor
    public Topping(String name) {
        this.name = name;
    }
}
