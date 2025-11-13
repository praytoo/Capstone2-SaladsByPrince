package com.pluralsight.order;

import com.pluralsight.controller.OrderSystem;
import com.pluralsight.controller.ReceiptWriter;
import com.pluralsight.fooditem.Salad;
import com.pluralsight.toppings.*;

import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.controller.OrderSystem.scanner;
import static java.lang.System.out;

public class OrderSalad {
    //add main salad method (boolean to make screen choice in while statement)
    //using switch cases to make salad choice decisions
    public static boolean addSalad() {
        // select greens
        out.println("Select your green type: 1) Arugula, 2) Spinach, or 3) Lettuce");
        int greenType = scanner.nextInt();
        scanner.nextLine();
        GreenType green;
        switch (greenType) {
            case 1 -> green = GreenType.ARUGULA;
            case 2 -> green = GreenType.SPINACH;
            case 3 -> green = GreenType.LETTUCE;
            default -> {
                out.println("Invalid choice, defaulting to Arugula");
                green = GreenType.ARUGULA;
            }
        }
        //select size
        out.println("What size would you like?: " +
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
                out.println("Invalid choice, defaulting to SMALL");
                saladSize = Size.SMALL;
            }
        }
        //select meats
        List<Topping> meatToppings = new ArrayList<>();
        String meatName;
        int extraMeat;
        while (true) {
            out.println("Which meat topping would you like?: " +
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
                    out.println("Invalid choice, defaulting to Chicken");
                    meatName = "Chicken";
                }
            }
            meatToppings.add(new MeatTopping(meatName));
            //extra meat?
            extraMeat = 0;
            out.println("Add extra meat? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraMeat++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        //select cheeses (Premium toppings)
        List<Topping> premiumToppings = new ArrayList<>();
        String premiumName;
        int extraPremium;
        while (true) {
            out.println("Which premium cheese topping would you like?: " +
                    "Premium topping: 1) Mozzarella\n" +
                    "2) Brie\n" +
                    "3) Goat cheese\n");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> premiumName = "Mozzarella";
                case 2 -> premiumName = "Brie";
                case 3 -> premiumName = "Goat cheese";
                default -> {
                    out.println("Invalid choice, defaulting to Mozzarella");
                    premiumName = "Mozzarella";
                }
            }
            premiumToppings.add(new PremiumTopping(premiumName));

            //extra premium toppings?
            extraPremium = 0;
            out.println("Add extra premium toppings? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraPremium++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        //select regular toppings
        List<Topping> regularToppings = new ArrayList<>();
        String regularName;
        int extraRegular;
        while (true) {
            out.println("Which regular topping would you like?: " +
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
                    out.println("Invalid choice, defaulting to Croutons");
                    regularName = "Croutons";
                }
            }
            regularToppings.add(new RegularTopping(regularName));
            //extra regular toppings?
            extraRegular = 0;
            out.println("Add extra regular toppings? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraRegular++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        //select dressing
        List<com.pluralsight.toppings.Dressing> dressing = new ArrayList<>();
        String dressingType;
        int extraDressing;
        while (true) {
            out.println("Which dressing would you like?: " +
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
                    out.println("Invalid choice, defaulting to Balsamic Vinaigrette and Honey");
                    dressingType = "Balsamic Vinaigrette and Honey";
                }
            }
            dressing.add(new com.pluralsight.toppings.Dressing(dressingType));

            //extra dressing?
            extraDressing = 0;
            out.println("Add extra dressing? yes/no");
            String extra = scanner.nextLine().trim();
            if (extra.equalsIgnoreCase("yes")) {
                extraDressing++;
                break;
            } else if (extra.equalsIgnoreCase("no")) {
                break;
            } else {
                out.println("Not an option! Please type 'yes' or 'no'.");
            }
        }
        //quinoa specialization?
        int quinoaCount = 0;
        String quinoa;
        while (true) {
            out.println("Add quinoa specialization? yes/no");
            quinoa = scanner.nextLine();
            if (quinoa.equalsIgnoreCase("yes")) {
                quinoaCount++;
                break;
            } else if (quinoa.equalsIgnoreCase("no")) {
                break;
            } else {
                out.println("not an option!");
            }
        }
        List<Topping> toppings = new ArrayList<>();

        //increasing extras count
        for (int i = 0; i < extraMeat; i++) toppings.add(new MeatTopping(meatName));
        for (int i = 0; i < extraPremium; i++) toppings.add(new PremiumTopping(premiumName));
        for (int i = 0; i < extraRegular; i++) toppings.add(new RegularTopping(regularName));
        for (int i = 0; i < extraDressing; i++) dressing.add(new com.pluralsight.toppings.Dressing(dressingType));
        for (int i = 0; i < quinoaCount; i++) toppings.add(new QuinoaTopping("Quinoa"));
        //adding toppings to order list
        for (Topping t : meatToppings){
            toppings.add(t);
        }
        for (Topping t : premiumToppings){
            toppings.add(t);
        }
        for (Topping t : regularToppings){
            toppings.add(t);
        }

        com.pluralsight.toppings.Dressing dressing2 = new com.pluralsight.toppings.Dressing(dressingType);

        //add salad to current order
        Salad salad = new Salad(saladSize, green, toppings, dressing2, extraMeat, extraPremium, extraRegular, extraDressing, quinoaCount);
        OrderSystem.currentOrder.add(0, salad);

        //checkout input caught in a while statement
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
                return false;
            } else {
                out.println("not an option!");
            }

        }
    }
}
