package com.pluralsight;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                1) Add salad
                2) Add drink
                3) Add Main Side
                4) Add Signature Salad
                5) Checkout
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
                    addSignatureSalad();
                    break;
                case 5:
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
        System.out.println("Select your green type: 1) Arugula, 2) Spinach, or 3) Lettuce");
        int greenType = scanner.nextInt();
        scanner.nextLine();
        GreenType green;
        switch (greenType) {
            case 1 -> green = GreenType.ARUGULA;
            case 2 -> green = GreenType.SPINACH;
            case 3 -> green = GreenType.LETTUCE;
            default -> {
                System.out.println("Invalid choice, defaulting to Arugula");
                green = GreenType.ARUGULA;
            }
        }
        System.out.println("What size would you like?: " +
                "1) small\n" +
                "2) medium\n" +
                "3) large\n");
        int size = scanner.nextInt();
        scanner.nextLine();
        Size saladSize;
        switch (size) {
            case 1 -> saladSize = Size.SMALL;
            case 2 -> saladSize = Size.MEDIUM;
            case 3 -> saladSize = Size.LARGE;
            default -> {
                System.out.println("Invalid choice, defaulting to SMALL");
                saladSize = Size.SMALL;
            }
        }
        java.util.List<Topping> meatToppings = new java.util.ArrayList<>();
        String meatName;
        int extraMeat;
        while (true) {
            System.out.println("Which meat topping would you like?: " +
                    "1) Chicken\n" +
                    "2) Tuna\n" +
                    "3) Salmon\n" +
                    "4) Beef\n" +
                    "5) Prosciutto\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> meatName = "Chicken";
                case 2 -> meatName = "Tuna";
                case 3 -> meatName = "Salmon";
                case 4 -> meatName = "Beef";
                case 5 -> meatName = "Prosciutto";
                default -> {
                    System.out.println("Invalid choice, defaulting to Chicken");
                    meatName = "Chicken";
                }
            }
            meatToppings.add(new MeatTopping(meatName));

            extraMeat = 0;
            System.out.println("Add extra meat? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraMeat++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        java.util.List<Topping> premiumToppings = new java.util.ArrayList<>();
        String premiumName;
        int extraPremium;
        while (true) {
            System.out.println("Which premium cheese topping would you like?: " +
                    "Premium topping: 1) Mozzarella\n" +
                    "2) Brie\n" +
                    "3) Goat cheese\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> premiumName = "Mozzarella";
                case 2 -> premiumName = "Brie";
                case 3 -> premiumName = "Goat cheese";
                default -> {
                    System.out.println("Invalid choice, defaulting to Mozzarella");
                    premiumName = "Mozzarella";
                }
            }
            premiumToppings.add(new PremiumTopping(premiumName));
            extraPremium = 0;
            System.out.println("Add extra premium toppings? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraPremium++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        java.util.List<Topping> regularToppings = new java.util.ArrayList<>();
        String regularName;
        int extraRegular;
        while (true) {
            System.out.println("Which regular topping would you like?: " +
                    "Regular toppings: 1) Croutons\n" +
                    "2) Raisins\n" +
                    "3) Carrots\n" +
                    "4) Hard Boiled Egg\n" +
                    "5) Walnuts\n" +
                    "6) Avocado\n" +
                    "7) Chickpeas\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> regularName = "Croutons";
                case 2 -> regularName = "Raisins";
                case 3 -> regularName = "Carrots";
                case 4 -> regularName = "Hard Boiled Egg";
                case 5 -> regularName = "Walnuts";
                case 6 -> regularName = "Avocado";
                case 7 -> regularName = "Chickpeas";
                default -> {
                    System.out.println("Invalid choice, defaulting to Croutons");
                    regularName = "Croutons";
                }
            }
            regularToppings.add(new RegularTopping(regularName));
            extraRegular = 0;
            System.out.println("Add extra regular toppings? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraRegular++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        java.util.List<Dressing> dressing = new java.util.ArrayList<>();
        String dressingType;
        int extraDressing;
        while (true) {
            System.out.println("Which dressing would you like?: " +
                    "Dressing: 1) Balsamic Vinaigrette and Honey\n" +
                    "2) Ranch with Yogurt\n" +
                    "3) Olive Oil with Lemon\n" +
                    "4) Caesar\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> dressingType = "Balsamic Vinaigrette and Honey";
                case 2 -> dressingType = "Ranch with Yogurt";
                case 3 -> dressingType = "Olive Oil with Lemon";
                case 4 -> dressingType = "Caesar";
                default -> {
                    System.out.println("Invalid choice, defaulting to Balsamic Vinaigrette and Honey");
                    dressingType = "Balsamic Vinaigrette and Honey";
                }
            }
            dressing.add(new Dressing(dressingType));
            extraDressing = 0;
            System.out.println("Add extra dressing? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraDressing++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        int quinoaCount = 0;
        String quinoa;
        while (true) {
            System.out.println("Add quinoa? yes/no");
            quinoa = scanner.nextLine();
            if (quinoa.equalsIgnoreCase("yes")) {
                quinoaCount++;
                break;
            } else if (quinoa.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("not an option!");
            }
        }
        List<Topping> toppings = new ArrayList<>();

        for (int i = 0; i < extraMeat; i++) toppings.add(new MeatTopping(meatName));
        for (int i = 0; i < extraPremium; i++) toppings.add(new PremiumTopping(premiumName));
        for (int i = 0; i < extraRegular; i++) toppings.add(new RegularTopping(regularName));
        for (int i = 0; i < extraDressing; i++) dressing.add(new Dressing(dressingType));
        for (int i = 0; i < quinoaCount; i++) toppings.add(new QuinoaTopping("Quinoa"));

        Dressing dressing2 = new Dressing(dressingType);

        Salad salad = new Salad(saladSize, green, toppings, dressing2, extraMeat, extraPremium, extraRegular, extraDressing, quinoaCount);
        currentOrder.add(0, salad);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return false;
            } else {
                System.out.println("not an option!");
            }

        }
    }

    public static boolean addSignatureSalad() {
        List<Salad> signatureSalads = Salad.getSignatureSalads();

        System.out.println("Choose a signature salad: ");
        for (int i = 0; i < signatureSalads.size(); i++) {
            System.out.println((i + 1) + ") " + signatureSalads.get(i));
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > signatureSalads.size()) {
            System.out.println("Invalid selection");
            return orderScreen();
        }

        Salad selectedSalad = signatureSalads.get(choice - 1);
        currentOrder.add(0, selectedSalad);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return false;
            } else {
                System.out.println("Not an option!");
            }
        }
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
        Drink drink2 = new Drink(drinkFlavor, drinkSize);
        currentOrder.add(0, drink2);

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
        Side sideName;
        switch (mainSide) {
            case 1 -> sideName = new Side("Sweet Potato");
            case 2 -> sideName = new Side ("Roasted Tomato");
            case 3 -> sideName = new Side ("Kimchi");
            default -> {
                System.out.println("Invalid choice, defaulting to Sweet Potato");
                sideName = new Side ("Sweet Potato");
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
        currentOrder.add(0, side);

        while (true) {
            System.out.println("Are you ready to check out?: yes: checkout, no: add more items, cancel: exit to homescreen");
            String checkout = scanner.nextLine().trim().toLowerCase();
            if (checkout.equalsIgnoreCase("yes")) {
                return checkout();
            } else if (checkout.equalsIgnoreCase("no")) {
                return orderScreen();
            } else if (checkout.equalsIgnoreCase("cancel")) {
                return false;
            } else {
                System.out.println("not an option!");
            }

        }
    }

    public static Side createMainSide(Side sideName, Size size) {
        return new Side(sideName, size);
    }

    public static boolean checkout() {
        String receiptOutput = generateReceiptText();
        System.out.println(receiptOutput);

        System.out.println("\nConfirm order? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            String receiptFile = "receipt_" + System.currentTimeMillis() + ".csv";

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(receiptFile), StandardCharsets.UTF_8)) {
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
                        line = "SIDE," + side.getSize() + "," + side.getSide();
                    }
                    writer.write(line);
                    writer.newLine();
                }

                writer.write("Total Price: " + receiptOutput.substring(receiptOutput.lastIndexOf("$")));
                System.out.println("\nReceipt saved as: " + receiptFile);
            } catch (IOException e) {
                System.out.println("Error creating receipt file: " + e.getMessage());
            }

            currentOrder.clear();
            return homeScreen();
        } else {
            System.out.println("Order canceled. Returning to home screen.");
            currentOrder.clear();
            return homeScreen();
        }
    }


    public static String generateReceiptText() {
        StringBuilder sb = new StringBuilder();
        double totalPrice = 0;

        sb.append("\n--- Your Order ---\n");

        for (OrderItem item : currentOrder) {
            if (item instanceof Salad salad) {
                double itemPrice = salad.calculatePrice();
                sb.append(salad.getGreen())
                        .append(" salad - Size: ").append(salad.getSize())
                        .append(" - Price: $").append(itemPrice).append("\n");
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
                        .append(side.getSide()).append(" side - Price: $").append(itemPrice).append("\n");
                totalPrice += itemPrice;
            }
        }

        sb.append("Total Price: $").append(totalPrice).append("\n");
        return sb.toString();
    }


}
