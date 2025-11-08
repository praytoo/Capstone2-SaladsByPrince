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
}

