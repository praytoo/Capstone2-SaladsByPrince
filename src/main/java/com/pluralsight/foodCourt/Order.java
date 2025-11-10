package com.pluralsight.foodCourt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items = new ArrayList<>();
    private LocalDateTime created = LocalDateTime.now();

    public void addItem(OrderItem item) {
        items.add(0, item);
    } // newest first

    public List<OrderItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getCost)
                .sum();
    }

    public LocalDateTime getCreated() {
        return created;
    }
}

