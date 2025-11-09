package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SideTest {

    @Test
    void getCost() {
        //arrange
        Side side = new Side(new Side ("Sweet Potato"), Size.SMALL);
        double expectedResult = 5;
        //act
        side.getSize();
        double result = side.getCost();
        //assert
        assertEquals(expectedResult, result);
    }
}