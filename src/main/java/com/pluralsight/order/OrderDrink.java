package com.pluralsight.order;

import com.pluralsight.controller.OrderSystem;
import com.pluralsight.controller.ReceiptWriter;
import com.pluralsight.fooditem.Drink;
import com.pluralsight.toppings.Size;

import static com.pluralsight.controller.OrderSystem.scanner;
import static java.lang.System.out;

public class OrderDrink {
    //add drink method
    public static boolean addDrink() {
        //select size of drink
        out.println("What size would you like?: " +
                "1) small\n" +
                "2) medium\n" +
                "3) large\n");
        int size = scanner.nextInt();
        scanner.nextLine();
        Size drinkSize;
        switch (size) {
            case 1 -> drinkSize = Size.SMALL;
            case 2 -> drinkSize = Size.MEDIUM;
            case 3 -> drinkSize = Size.LARGE;
            default -> {
                out.println("Invalid choice, defaulting to SMALL");
                drinkSize = Size.SMALL;
            }
        }
        //select flavor of drink
        out.println("What flavor drink?: " +
                "1) Rose Lemonade \n" +
                "2) Strawberry Lemonade\n" +
                "3) Cherry Lemonade\n");
        int flavorDrink = scanner.nextInt();
        scanner.nextLine();
        String drinkFlavor;
        switch (flavorDrink) {
            case 1 -> drinkFlavor = "Rose Lemonade";
            case 2 -> drinkFlavor = "Strawberry Lemonade";
            case 3 -> drinkFlavor = "Cherry Lemonade";
            default -> {
                out.println("Invalid choice, defaulting to Rose Lemonade");
                drinkFlavor = "Rose Lemonade";
            }
        }
        //add drink to current order
        Drink drink2 = new Drink(drinkFlavor, drinkSize);
        OrderSystem.currentOrder.add(0, drink2);

        //check out input
        while (true) {
            out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine();
            if (checkout.equalsIgnoreCase("yes")) {
                out.println("Checking out...");
                return ReceiptWriter.checkout(scanner, System.out, checkout);
            } else if (checkout.equalsIgnoreCase("no")) {
                out.println("Add more items to your order...");
                return OrderSystem.orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                out.println("Order canceled. Returning to home screen.");
                return OrderSystem.homeScreen();
            } else {
                out.println("not an option!");
            }

        }
    }

}
