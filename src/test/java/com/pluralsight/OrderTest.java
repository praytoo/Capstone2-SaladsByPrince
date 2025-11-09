package com.pluralsight;

import com.pluralsight.foodCourt.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("Get Cost for Full Order Accuracy")
    void calculateTotal() {
        //arrange
        Order order = new Order();
        Drink drink = new Drink("Rose Lemonade", Size.SMALL);
        Side side = new Side("Sweet Potato", Size.SMALL);
        double expectedResult = 10.0;
        //act
        order.addItem(drink);
        order.addItem(side);
        double result = Order.calculateTotal();
        //assert
        assertEquals(expectedResult, result);
    }
}