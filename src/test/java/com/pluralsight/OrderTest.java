package com.pluralsight;

import com.pluralsight.foodCourt.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void calculateTotal() {
        //arrange
        Order order = new Order();
        Drink drink = new Drink("Rose Lemonade", Size.SMALL);
        double expectedResult = 4.0;
        //act
        order.addItem(drink);
        double result = Order.calculateTotal();
        //assert
        assertEquals(expectedResult, result);
    }
}