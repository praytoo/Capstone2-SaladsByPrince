package com.pluralsight.controller;

import com.pluralsight.fooditem.Drink;
import com.pluralsight.fooditem.Salad;
import com.pluralsight.fooditem.Side;
import com.pluralsight.order.*;
import com.pluralsight.toppings.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class OrderSystem {
    //scanner for input
    public static Scanner scanner = new Scanner(in);
    public static List<OrderItem> currentOrder = new ArrayList<>();


    //to loop back to home
    public static void main(String[] args) {
        boolean endProgram = false;
        while (!endProgram) {
            endProgram = homeScreen();
        }
        scanner.close();
    }
    //homescreen menu switch case
    public static boolean homeScreen() {
        String options = """
                WELCOME TO SALADS BY PRINCE
                Would you like to...
                1) Start a new order
                0) Exit
                """;
        switch (getNumberChoice(options)) {
            case 1:
                orderScreen();
                break;
            case 0:
                System.exit(0);
            default:
                out.println("that's not an option.");
                break;
        }
        return false;
    }

    //to solve for number choice
    public static int getNumberChoice(String options) {
        out.println(options);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    //order screen
    public static boolean orderScreen() {
        String options = """
                Would you like to...
                1) Add salad
                2) Add drink
                3) Add Main Side
                4) Add Signature Salad
                5) Checkout
                0) Cancel Order
                """;
        switch (getNumberChoice(options)) {
            case 1:
                OrderSalad.addSalad();
                break;
            case 2:
                OrderDrink.addDrink();
                break;
            case 3:
                OrderMainSide.addMainSide();
                break;
            case 4:
                OrderSignatureSalad.addSignatureSalad();
                break;
            case 5:
                ReceiptWriter.checkout(scanner, System.out, options);
                break;
            case 0:
                return true;
            default:
                out.println("that's not an option.");
                break;
        }
        return false;
    }

}
