package com.pluralsight;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SideTest {

    @Test
    @DisplayName("Get Cost for Side Accuracy")
    void getCost() {
        //arrange
        Side side = new Side(new Side ("Sweet Potato"), Size.SMALL);
        double expectedResult = 5;
        //act
        double result = side.getCost();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get Null Cost for Side Accuracy")
    void getNullCost() {
        //arrange
        Side side = new Side(new Side ("Sweet Potato"), null);
        double expectedResult = 0.0;
        //act
        double result = side.getCost();
        //assert
        assertEquals(expectedResult, result);
    }
}