package com.pluralsight;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

import static com.pluralsight.Size.SMALL;
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
        OrderSystem.addSalad();

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
        String result = OrderSystem.generateReceiptText();
        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("User Doesn't Confirm Order")
    void readsValidOnFirstTry() {
        var input = new ByteArrayInputStream("no\n".getBytes(StandardCharsets.UTF_8));
        var scanner = new Scanner(input);
        var output = new ByteArrayOutputStream();
        var out = new PrintStream(output);

        boolean result = OrderSystem.checkout(scanner, out, "no");

        assertEquals(input, result);
        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("Order canceled. Returning to home screen."));
    }
}