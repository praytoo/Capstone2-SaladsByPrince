package com.pluralsight.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static List<OrderItem> items = new ArrayList<>();

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

}
