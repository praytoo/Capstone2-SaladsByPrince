package com.pluralsight;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    @DisplayName("Get Cost for Drink Accuracy")
    void getCost() {
        //arrange
        Drink drink = new Drink("Cherry Lemonade", Size.MEDIUM);
        double expectedResult = 6.5;
        //act
        double result = drink.getCost();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Null Cost for Drink Accuracy")
    void getNullFlavorAndSize() {
        //arrange
        Drink drink = new Drink(null, null);
        double expectedResult = 0.0;
        //act
        double result = drink.getCost();
        //assert
        assertEquals(expectedResult, result);
    }
}