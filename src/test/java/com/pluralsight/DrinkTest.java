package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    void getCost() {
        //arrange
        Drink drink = new Drink("Cherry Lemonade", Size.MEDIUM);
        double expectedResult = 5.5;
        //act
        drink.getSize();
        double result = drink.getCost();
        //assert
        assertEquals(expectedResult, result);
    }
}