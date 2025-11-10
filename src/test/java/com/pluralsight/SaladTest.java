package com.pluralsight;

import com.pluralsight.fooditem.Salad;
import com.pluralsight.toppings.Dressing;
import com.pluralsight.toppings.GreenType;
import com.pluralsight.toppings.Size;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaladTest {

    @Test
    @DisplayName("Get Cost for Salad Accuracy")
    void calculatePrice() {
        //arrange
        Salad salad = new Salad(GreenType.ARUGULA, Size.SMALL, "Chicken", 1, "Mozzarella", 1, "Raisins", 1, "Caesar", 1, "yes", 1);
        double expectedResult = 12.5;
        //act
        double result = salad.getCost();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Empty Toppings for Salad Accuracy")
    void getEmptyToppings() {
        //arrange
        Salad salad = new Salad(Size.SMALL, GreenType.ARUGULA, null, new Dressing("Caesar"));
        //act
        //assert
        assertTrue(salad.getToppings2().isEmpty());
    }
}