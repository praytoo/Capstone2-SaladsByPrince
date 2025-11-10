package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.PremiumTopping;
import com.pluralsight.foodCourt.RegularTopping;
import com.pluralsight.foodCourt.Topping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingService {

    public List<Topping> getRegularToppings() {
        return List.of(
                new RegularTopping("Croutons"),
                new RegularTopping("Raisins"),
                new RegularTopping("Carrots"),
                new RegularTopping("Walnuts"),
                new RegularTopping("Avocado"),
                new RegularTopping("Chickpeas"),
                new RegularTopping("Hard Boiled Egg")
        );
    }

    public List<Topping> getPremiumToppings() {
        return List.of(
                new PremiumTopping("Beef"),
                new PremiumTopping("Chicken"),
                new PremiumTopping("Tuna"),
                new PremiumTopping("Salmon"),
                new PremiumTopping("Prosciutto"),
                new PremiumTopping("Mozzarella"),
                new PremiumTopping("Brie"),
                new PremiumTopping("Goat Cheese")
        );
    }

    public static final List<Topping> toppings = List.of(
            new Topping(Category.SALAD, false, "Croutons"),
            new Topping(Category.SALAD, false, "Raisins"),
            new Topping(Category.SALAD, false, "Walnuts"),
            new Topping(Category.SALAD, false, "Hard Boiled Egg"),
            new Topping(Category.SALAD, false, "Avocado"),
            new Topping(Category.SALAD, false, "Carrots"),
            new Topping(Category.SALAD, false, "Chickpeas"),
            new Topping(Category.SALAD, true, "Chicken"),
            new Topping(Category.SALAD, true, "Beef"),
            new Topping(Category.SALAD, true, "Tuna"),
            new Topping(Category.SALAD, true, "Salmon"),
            new Topping(Category.SALAD, true, "Prosciutto"),
            new Topping(Category.SALAD, true, "Mozzarella"),
            new Topping(Category.SALAD, true, "Brie"),
            new Topping(Category.SALAD, true, "Goat Cheese"),
            new Topping(Category.PIZZA, false, "Pineapple"),
            new Topping(Category.PIZZA, false, "Garlic"),
            new Topping(Category.PIZZA, false, "Bell Peppers"),
            new Topping(Category.PIZZA, false, "Onions"),
            new Topping(Category.PIZZA, false, "Mushrooms"),
            new Topping(Category.PIZZA, false, "Jalapenos"),
            new Topping(Category.PIZZA, false, "Spinach"),
            new Topping(Category.PIZZA, true, "Chicken"),
            new Topping(Category.PIZZA, true, "Beef"),
            new Topping(Category.PIZZA, true, "Bacon"),
            new Topping(Category.PIZZA, true, "Anchovies"),
            new Topping(Category.PIZZA, true, "Prosciutto"),
            new Topping(Category.PIZZA, true, "Ricotta"),
            new Topping(Category.PIZZA, true, "Brie"),
            new Topping(Category.PIZZA, true, "Goat Cheese"),
            new Topping(Category.SANDWICH, false, "Avocado"),
            new Topping(Category.SANDWICH, false, "Garlic"),
            new Topping(Category.SANDWICH, false, "Bell Peppers"),
            new Topping(Category.SANDWICH, false, "Grilled Onions"),
            new Topping(Category.SANDWICH, false, "Tomatoes"),
            new Topping(Category.SANDWICH, false, "Lettuce"),
            new Topping(Category.SANDWICH, false, "Spinach"),
            new Topping(Category.SANDWICH, true, "Chicken"),
            new Topping(Category.SANDWICH, true, "Beef"),
            new Topping(Category.SANDWICH, true, "Bacon"),
            new Topping(Category.SANDWICH, true, "Salmon"),
            new Topping(Category.SANDWICH, true, "Prosciutto"),
            new Topping(Category.SANDWICH, true, "Mozzarella"),
            new Topping(Category.SANDWICH, true, "Brie"),
            new Topping(Category.SANDWICH, true, "Ricotta"),
            new Topping(Category.TACO, false, "Lettuce"),
            new Topping(Category.TACO, false, "Garlic"),
            new Topping(Category.TACO, false, "French Fries"),
            new Topping(Category.TACO, false, "Grilled Onions"),
            new Topping(Category.TACO, false, "Tomatoes"),
            new Topping(Category.TACO, false, "Mushroom"),
            new Topping(Category.TACO, false, "Jalapenos"),
            new Topping(Category.TACO, true, "Chicken"),
            new Topping(Category.TACO, true, "Beef"),
            new Topping(Category.TACO, true, "Bacon"),
            new Topping(Category.TACO, true, "Salmon"),
            new Topping(Category.TACO, true, "Pork"),
            new Topping(Category.TACO, true, "Mozzarella"),
            new Topping(Category.TACO, true, "Ricotta")
    );

    public List<Topping> getPizzaToppings() {
        return toppings.stream()
                .filter(t -> t.getCategory() == Category.PIZZA).toList();
    }

    public List<Topping> getTacoToppings() {
        return toppings.stream()
                .filter(t -> t.getCategory() == Category.TACO).toList();
    }

    public List<Topping> getSandwichToppings() {
        return toppings.stream()
                .filter(t -> t.getCategory() == Category.SANDWICH).toList();
    }

}

