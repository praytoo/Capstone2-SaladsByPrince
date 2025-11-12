package com.pluralsight.controller;

import com.pluralsight.fooditem.Drink;
import com.pluralsight.fooditem.Salad;
import com.pluralsight.fooditem.Side;
import com.pluralsight.order.OrderItem;
import com.pluralsight.toppings.Dressing;
import com.pluralsight.toppings.Topping;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.pluralsight.controller.OrderSystem.*;

public class ReceiptWriter {
    //check out method + receipt writer
    public static boolean checkout(Scanner in, PrintStream out, String choice) {
        //checking if $0.0 is total
        if (currentOrder.isEmpty()) {
            System.out.println("Must add a salad, side, or drink!");
        } else {
            String receiptOutput = generateReceiptText();
            System.out.println(receiptOutput);

            //order confirmation input
            System.out.println("\nConfirm order? (yes/no): ");
            choice = scanner.nextLine().trim().toLowerCase();

            //receipt writer
            if (choice.equals("yes")) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String receiptFile = "receipt_" + timestamp + ".txt";

                try {
                    Files.createDirectories(Paths.get("receipts"));
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("receipts/" + receiptFile), StandardCharsets.UTF_8)) {
                        for (OrderItem item : currentOrder) {
                            String line = "";
                            if (item instanceof Salad salad) {
                                line = "SALAD," + salad.getGreen() + "," + salad.getSize() + "," +
                                        salad.getDressing() + "," +
                                        salad.getToppings().stream()
                                                .map(Topping::getName)
                                                .reduce((a, b) -> a + "/" + b)
                                                .orElse("");
                            } else if (item instanceof Drink drink) {
                                line = "DRINK," + drink.getSize() + "," + drink.getFlavor();
                            } else if (item instanceof Side side) {
                                line = "SIDE," + side.getSize() + "," + side.getSideType();
                            }
                            writer.write(line);
                            writer.newLine();
                        }

                        writer.write("Total Price: " + receiptOutput.substring(receiptOutput.lastIndexOf("$")));
                        System.out.println("\nReceipt saved as: " + receiptFile);
                    } catch (IOException e) {
                        System.out.println("Error creating receipt file: " + e.getMessage());
                    }
                } catch (IOException e) {
                    System.out.println("Failed to create receipts directory.");
                    return homeScreen();
                }
                //clears current order after receipt prints to CLI
                currentOrder.clear();
                return homeScreen();
            } else {
                //solves for canceling the order + returns to home menu
                System.out.println("Order canceled. Returning to home screen.");
                currentOrder.clear();
                return homeScreen();
            }
        }
        return false;
    }

    //receipt text generator
    public static String generateReceiptText() {
        StringBuilder sb = new StringBuilder();
        double totalPrice = 0;

        sb.append("\n--- Your Order ---\n");

        for (OrderItem item : currentOrder) {
            if (item instanceof Salad salad) {
                double itemPrice = salad.calculatePrice();
                List<Topping> toppings = salad.getToppings();
                String toppingNames = toppings.stream()
                        .map(Topping::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                Optional<List<Dressing>> dressings = salad.getDressings();
                String dressingName = dressings.stream()
                        .map((List<Dressing> t) -> salad.getDressings(t))
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                Optional<List<Topping>> toppings2 = salad.getToppings2();
                String toppingNames2 = toppings.stream()
                        .map(Topping::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                sb.append(salad.getGreen());
                if (!toppingNames.isEmpty()) {
                    sb.append(" (").append(toppingNames).append(")")
                            .append(" salad - Size: ").append(salad.getSize())
                            .append(" - Price: $").append(itemPrice).append("\n");
                }
                totalPrice += itemPrice;
            } else if (item instanceof Drink drink) {
                double itemPrice = drink.getCost();
                sb.append(drink.getSize()).append(" ")
                        .append(drink.getFlavor())
                        .append(" - Price: $").append(itemPrice).append("\n");
                totalPrice += itemPrice;
            } else if (item instanceof Side side) {
                double itemPrice = side.getCost();
                sb.append(side.getSize()).append(" ")
                        .append(side.getSideType())
                        .append(" side - Price: $").append(itemPrice).append("\n");
                totalPrice += itemPrice;
            }
        }

        sb.append("Total Price: $").append(totalPrice).append("\n");
        return sb.toString();

    }
}
