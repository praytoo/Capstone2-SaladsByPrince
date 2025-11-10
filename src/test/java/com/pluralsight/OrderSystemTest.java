package com.pluralsight;

import com.pluralsight.controller.OrderSystem;
import com.pluralsight.controller.ReceiptWriter;
import com.pluralsight.fooditem.Salad;
import com.pluralsight.order.OrderSalad;
import com.pluralsight.toppings.Size;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrderSystemTest {

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        OrderSystem.currentOrder.clear();
    }

    private void provideInput(String input) {
        testIn = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
        OrderSystem.scanner = new java.util.Scanner(System.in);
    }
    @Test
    @DisplayName("Check For Salad Greens Accuracy")
    void addSalad() {
        //arrange
        String input = """
                1
                1
                1
                yes
                1
                yes
                1
                yes
                1
                yes
                yes
                cancel
                """;
        provideInput(input);

        OrderSystem.scanner = new Scanner(System.in);

        //act
        OrderSalad.addSalad();

        //assert
        assertEquals(1, OrderSystem.currentOrder.size());
        Salad salad = (Salad) OrderSystem.currentOrder.get(0);

        assertEquals(Size.SMALL, salad.getSize());
        assertEquals("ARUGULA", salad.getGreen().toString());
    }

    @Test
    @DisplayName("Make sure receipt prints accurately")
    void checkout_printsCorrectReceipt() {
        //arrange
        String expectedResult = "\n--- Your Order ---\n" + "Total Price: $0.0\n";
        //act
        String result = ReceiptWriter.generateReceiptText();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("User Doesn't Confirm Order")
    void readsValidOnFirstTry() {
        //arrange
        var input = new ByteArrayInputStream("no\n".getBytes(StandardCharsets.UTF_8));
        var scanner = new Scanner(input);
        var output = new ByteArrayOutputStream();
        var out = new PrintStream(output);
        boolean input2 = input.equals("no");
        //act
        boolean result = ReceiptWriter.checkout(scanner, out, "no");
        //assert
        assertEquals(input2, result);
        //String text = output.toString(StandardCharsets.UTF_8);
        //assertTrue(text.contains("Order canceled. Returning to home screen."));
    }
}