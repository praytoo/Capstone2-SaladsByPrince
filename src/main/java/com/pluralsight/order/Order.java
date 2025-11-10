package com.pluralsight.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static List<OrderItem> items = new ArrayList<>();
    ;
    private LocalDateTime orderDateTime;

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
    //calculate total by item
    public static double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getCost();
        }
        return total;
    }

    //generate receipt
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("--- Receipt ---\n");
        receipt.append("Order Time: ").append(orderDateTime).append("\n\n");

        for (OrderItem item : items) {
            receipt.append(item.toString()).append("\n");
        }

        receipt.append("\nTotal: $").append(String.format("%.2f", calculateTotal()));
        return receipt.toString();
    }
}
