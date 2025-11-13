package com.pluralsight.order;

import com.pluralsight.controller.OrderSystem;
import com.pluralsight.controller.ReceiptWriter;
import com.pluralsight.fooditem.Side;
import com.pluralsight.toppings.Size;

import static com.pluralsight.controller.OrderSystem.scanner;
import static java.lang.System.out;

public class OrderMainSide {
    //add main side method (boolean to make screen choice in while statement)
    //using switch cases to make side choice decisions
    public static boolean addMainSide() {
        //select your main side choice
        out.println("What do you want as your main side?: " +
                "1) sweet potato\n" +
                "2) roasted tomato \n" +
                "3) kimchi\n");
        int mainSide = scanner.nextInt();
        scanner.nextLine();
        String sideName;
        switch (mainSide) {
            case 1 -> sideName = "Sweet Potato";
            case 2 -> sideName = "Roasted Tomato";
            case 3 -> sideName = "Kimchi";
            default -> {
                out.println("Invalid choice, defaulting to Sweet Potato");
                sideName = "Sweet Potato";
            }
        }
        //select size of your main side dish
        out.println("What size would you like?: " +
                "1) small\n" +
                "2) medium\n" +
                "3) large\n");
        int size = scanner.nextInt();
        scanner.nextLine();
        Size sideSize;
        switch (size) {
            case 1 -> sideSize = Size.SMALL;
            case 2 -> sideSize = Size.MEDIUM;
            case 3 -> sideSize = Size.LARGE;
            default -> {
                out.println("Invalid choice, defaulting to SMALL");
                sideSize = Size.SMALL;
            }
        }
        //add main side to current order
        Side side = new Side(sideName, sideSize);
        OrderSystem.currentOrder.add(0, side);

        //ready to check out input using a while statement
        while (true) {
            out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine().trim().toLowerCase();
            if (checkout.equalsIgnoreCase("yes")) {
                out.println("Checking out...");
                return ReceiptWriter.checkout(scanner, System.out, checkout);
            } else if (checkout.equalsIgnoreCase("no")) {
                out.println("Add more items to your order...");
                return OrderSystem.orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                out.println("Order canceled. Returning to home screen.");
                return false;
            } else {
                out.println("not an option!");
            }

        }
    }
}
