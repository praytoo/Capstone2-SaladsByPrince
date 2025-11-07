package com.pluralsight;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<OrderItem> currentOrder = new ArrayList<>();


    public static void main(String[] args) {
        boolean endProgram = false;
        while (!endProgram) {
            endProgram = homeScreen();
        }
    }
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
                System.out.println("that's not an option.");
                break;
        }
        return false;
    }
    public static int getNumberChoice(String options) {
        System.out.println(options);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    static String currentReceiptFileName = null;

    public static boolean orderScreen() {
        currentReceiptFileName = "receipt_" + System.currentTimeMillis() + ".csv";
        String options = """
                Please begin your order:
                Would you like to...
                1) Add item
                2) Add drink
                3) Add Main Side
                4) Checkout
                0) Cancel Order
                """;
            switch (getNumberChoice(options)) {
                case 1:
                    addSalad();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addMainSide();
                    break;
                case 4:
                    checkout();
                    break;
                case 0:
                    return true;
                default:
                    System.out.println("that's not an option.");
                    break;
            }
            return false;
        }

    public static boolean addSalad() {
        System.out.println("Select your green type: Arugula, Spinach, or Lettuce");
        String greenType = scanner.nextLine();
        System.out.println("What size would you like it: small, medium, or large");
        String size = scanner.nextLine();
        java.util.List<String> meatToppings = new java.util.ArrayList<>();
        String extraMeat;
        while (true) {
            System.out.println("Which meat topping would you like?: " +
                    "Meat: -chicken\n" +
                    "-tuna\n" +
                    "-salmon\n" +
                    "-beef\n" +
                    "-prosciutto\n");
            meatToppings.add(scanner.nextLine());
            System.out.println("Add extra meat? yes/no");
            extraMeat = scanner.nextLine();
            if (extraMeat.equalsIgnoreCase("no")) {
                break;
            }
        }
        java.util.List<String> premiumToppings = new java.util.ArrayList<>();
        String extraPremium;
        while (true) {
            System.out.println("Which premium cheese topping would you like?: " +
                    "Premium topping: -mozzarella\n" +
                    "-Brie\n" +
                    "-goat cheese\n");
            premiumToppings.add(scanner.nextLine());
            System.out.println("Add extra premium toppings? yes/no");
            extraPremium = scanner.nextLine();
            if (extraPremium.equalsIgnoreCase("no")) {
                break;
            }
        }
        String regularTopping = scanner.nextLine();
        java.util.List<String> regularToppings = new java.util.ArrayList<>();
        String extraReg;
        while (true) {
            System.out.println("Which regular topping would you like?: " +
                    "Other toppings: -croutons\n" +
                    "-raisins\n" +
                    "-carrots\n" +
                    "-hard boiled egg\n" +
                    "-walnuts\n" +
                    "-avocado\n" +
                    "-chickpeas\n");
            regularToppings.add(scanner.nextLine());
            System.out.println("Add extra regular toppings? yes/no");
            extraReg = scanner.nextLine();
            if (extraReg.equalsIgnoreCase("no")) {
                break;
            }
        }
        java.util.List<String> dressing = new java.util.ArrayList<>();
        String extraDressing;
        while (true) {
            System.out.println("Which dressing would you like?: " +
                    "Dressing: -balsamic vinaigrette and honey\n" +
                    "-ranch and yogurt\n" +
                    "-olive oil with lemon\n" +
                    "-Caesar\n");
            dressing.add(scanner.nextLine());
            System.out.println("Add extra dressing? yes/no");
            extraDressing = scanner.nextLine();
            if (extraDressing.equalsIgnoreCase("no")) {
                break;
            }
        }
        int quinoaCount = 0;
        while (true) {
            System.out.println("Add quinoa? yes/no");
            String quinoa = scanner.nextLine();

            if (quinoa.equalsIgnoreCase("yes")) {
                quinoaCount++;
            } else if (quinoa.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("not an option!");
            }
        }

        recordSalad(greenType, size, meatToppings, extraMeat, premiumToppings, extraPremium, regularToppings, extraReg, dressing, extraDressing, quinoaCount);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return homeScreen();
            } else {
                System.out.println("not an option!");
            }

        }
    }

    //Recording transactions
    public static boolean recordSalad(String greenType, String size, List<String> meatToppings, String extraMeat, List<String> premiumToppings, String extraPremium, List<String> regularToppings, String extraReg, List<String> dressing, String extraDressing, int quinoaCount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

        String line = date.format(df) + "|" + time.format(tf) + "|" + greenType + "|" + size + "|" + meatToppings + "|" + extraMeat + "|" + premiumToppings + "|" + extraPremium + "|" + regularToppings + "|" + extraReg + "|" + dressing + "|" + extraDressing + "|" + "quinoa x" + quinoaCount;

        Size saladSize = Size.valueOf(size.toUpperCase());
        GreenType green = GreenType.valueOf(greenType.toUpperCase());
        List<Topping> toppings = new ArrayList<>();
        for (String meat : meatToppings) toppings.add(new MeatTopping(meat));
        for (String premium : premiumToppings) toppings.add(new PremiumTopping(premium));
        for (String regular : regularToppings) toppings.add(new RegularTopping(regular));
        if (quinoaCount > 0) toppings.add(new QuinoaTopping("Quinoa"));
        Dressing saladDressing = new Dressing(extraDressing);

        Salad salad = new Salad(saladSize, green, toppings, saladDressing);
        currentOrder.add(salad);
        return true;
    }


    public static boolean addDrink() {
        System.out.println("What size would you like?: " +
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
                System.out.println("Invalid choice, defaulting to SMALL");
                drinkSize = Size.SMALL;
            }
        }
        System.out.println("What flavor drink?: " +
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
                System.out.println("Invalid choice, defaulting to Rose Lemonade");
                drinkFlavor = "Rose Lemonade";
            }
        }
        Drink drink = new Drink(drinkFlavor, drinkSize);
        currentOrder.add(drink);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return homeScreen();
            } else {
                System.out.println("not an option!");
            }

        }
    }

    public static boolean addMainSide() {
        System.out.println("What do you want as your main side?: " +
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
                System.out.println("Invalid choice, defaulting to Sweet Potato");
                sideName = "Sweet Potato";
            }
        }
        System.out.println("What size would you like?: " +
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
                System.out.println("Invalid choice, defaulting to SMALL");
                sideSize = Size.SMALL;
            }
        }
        Side side = new Side(sideName, sideSize);
        currentOrder.add(side);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine().trim().toLowerCase();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return homeScreen();
            } else {
                System.out.println("not an option!");
            }

        }
    }

    public static boolean checkout() {
        System.out.println("\n--- Your Order ---");
        double totalPrice = 0;

        // Print each item and calculate total price
        for (OrderItem item : currentOrder) {
            if (item instanceof Salad salad) {
                double itemPrice = salad.calculatePrice();
                System.out.println(salad.getGreen() + " salad - Size: " + salad.getSize() + " - Price: $" + itemPrice);
                totalPrice += itemPrice;
            } else if (item instanceof Drink drink) {
                double itemPrice = drink.getCost();
                System.out.println(drink.getSize() + " " + drink.getFlavor() + " - Price: $" + itemPrice);
                totalPrice += itemPrice;
            } else if (item instanceof Side side) {
                double itemPrice = side.getCost();
                System.out.println(side.getSize() + " side - Price: $" + itemPrice);
                totalPrice += itemPrice;
            }
        }

        System.out.println("Total Price: $" + totalPrice);
        System.out.println("\nConfirm order? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            // Write receipt CSV
            String receiptFile = "receipt_" + System.currentTimeMillis() + ".csv";
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(receiptFile), StandardCharsets.UTF_8)) {
                for (OrderItem item : currentOrder) {
                    String line = "";
                    if (item instanceof Salad salad) {
                        line = "SALAD," + salad.getGreen() + "," + salad.getSize() + "," +
                                salad.getDressing() + "," +
                                salad.getToppings().stream()
                                        .map(t -> t.getName())
                                        .reduce((a, b) -> a + "/" + b)
                                        .orElse("");
                    } else if (item instanceof Drink drink) {
                        line = "DRINK," + drink.getSize() + "," + drink.getFlavor();
                    } else if (item instanceof Side side) {
                        line = "SIDE," + side.getSize();
                    }
                    writer.write(line);
                    writer.newLine();
                }
                writer.write("Total Price: $" + totalPrice);
                System.out.println("\nReceipt saved as: " + receiptFile);
            } catch (IOException e) {
                System.out.println("Error creating receipt file: " + e.getMessage());
            }

            currentOrder.clear();
            return homeScreen(); // return to home screen

        } else {
            System.out.println("Order canceled. Returning to home screen.");
            currentOrder.clear();
            return homeScreen();
        }
    }

}
