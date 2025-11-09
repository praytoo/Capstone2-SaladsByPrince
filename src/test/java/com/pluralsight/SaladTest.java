package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaladTest {

    @Test
    void calculatePrice() {
        //arrange
        Salad salad = new Salad(GreenType.ARUGULA, Size.SMALL, "Chicken", 1, "Mozzarella", 1, "Raisins", 1, "Caesar", 1, "yes", 1);
        double expectedResult = 12.5;
        //act
        double result = salad.getCost();
        //assert
        assertEquals(expectedResult, result);
    }
}