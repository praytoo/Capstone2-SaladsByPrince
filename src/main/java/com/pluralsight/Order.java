package com.pluralsight;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private LocalDateTime orderDateTime;

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }

    public double calculateTotal(){
        double total = 0;
        for (OrderItem item : items) {
            total += item.getCost();
        }
        return total;
    }

    public String generateReceipt(){
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
