package com.pluralsight;

import java.util.Scanner;

public class OrderSystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;
        while (!endProgram) {
            endProgram = showHomeScreen();
        }
    }
    public static boolean showHomeScreen() {
        String options = """
                WELCOME TO SALADS BY PRINCE
                Would you like to...
                1) Start a new order
                0) Exit
                """;
        switch (getNumberChoice(Integer.parseInt(options))) {
            case "1":
                TransactionFileManager.addDeposit();
                break;
            case "0":
                return true;
            default:
                System.out.println("Prince, that's not an option.");
                break;
        }
        return false;
    }
    public static int getNumberChoice(int options) {
        System.out.println(options);
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

}
