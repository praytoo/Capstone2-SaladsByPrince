package com.pluralsight.order;

import com.pluralsight.controller.OrderSystem;
import com.pluralsight.controller.ReceiptWriter;
import com.pluralsight.fooditem.Salad;
import com.pluralsight.toppings.Topping;

import java.util.List;

import static com.pluralsight.controller.OrderSystem.scanner;
import static java.lang.System.out;

public class OrderSignatureSalad {
    //add main signature salad method (boolean to make screen choice in while statement)
    //using a for loop to select signature salad
    //using switch cases to make signature salad modification decisions
    public static boolean addSignatureSalad() {
        List<Salad> signatureSalads = Salad.getSignatureSalads();

        //choose your signature salad
        out.println("Choose a signature salad: ");
        for (int i = 0; i < signatureSalads.size(); i++) {
            out.println((i + 1) + ") " + signatureSalads.get(i));
        }
        int saladChoice = scanner.nextInt();
        scanner.nextLine();

        if (saladChoice < 1 || saladChoice > signatureSalads.size()) {
            out.println("Invalid selection");
            return OrderSystem.orderScreen();
        }
        //add toppings input
        out.println("Would you like to add any toppings?");
        String regularName;
        while (true) {
            out.println("Which regular topping would you like?: " +
                    "Regular toppings: 1) Croutons\n" +
                    "2) Raisins\n" +
                    "3) Carrots\n" +
                    "4) Hard Boiled Egg\n" +
                    "5) Walnuts\n" +
                    "6) Avocado\n" +
                    "7) Chickpeas\n" +
                    "8) No additional toppings\n");
            int toppingChoice = Integer.parseInt(scanner.nextLine().trim());
            switch (toppingChoice) {
                case 1 -> regularName = "Croutons";
                case 2 -> regularName = "Raisins";
                case 3 -> regularName = "Carrots";
                case 4 -> regularName = "Hard Boiled Egg";
                case 5 -> regularName = "Walnuts";
                case 6 -> regularName = "Avocado";
                case 7 -> regularName = "Chickpeas";
                case 8 -> regularName = "No additional toppings";
                default -> {
                    out.println("Invalid choice, defaulting to Croutons");
                    regularName = "Croutons";
                }
            }
            signatureSalads.add(new Salad(regularName));

            //add salad to current order
            Salad selectedSalad = signatureSalads.get(saladChoice - 1);

            // Show current toppings
            out.println("Current toppings: ");
            for (int i = 0; i < selectedSalad.getToppings().size(); i++) {
                out.println((i + 1) + ") " + selectedSalad.getToppings().get(i).getName());
            }

            // ask user if they want to remove any toppings
            //using (nested) if statements and while statements to do so
            out.println("Do you want to remove any toppings? (yes/no)");
            String removeChoice = scanner.nextLine().trim();

            if (removeChoice.equalsIgnoreCase("yes")) {
                while (true) {
                    out.println("Enter the number of the topping to remove (or 0 to finish):");
                    int toppingIndex = Integer.parseInt(scanner.nextLine().trim());

                    if (toppingIndex == 0) break;
                    if (toppingIndex < 1 || toppingIndex > selectedSalad.getToppings().size()) {
                        out.println("Invalid choice, try again.");
                    } else {
                        Topping removed = selectedSalad.getToppings().remove(toppingIndex - 1);
                        out.println("Removed: " + removed.getName());
                    }
                }
            }
            //add signature salad to current order
            OrderSystem.currentOrder.add(0, selectedSalad);

            //ready to check out input using a while statement
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
                    out.println("Not an option!");
                }
            }
        }
    }
}
