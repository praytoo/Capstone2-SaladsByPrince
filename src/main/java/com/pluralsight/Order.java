package com.pluralsight;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private LocalDateTime orderDateTime;

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderItem addItem(OrderItem i){
        return i; // change later
    }

    public double calculateTotal(){
        return 0; // change later
    }

    public String generateReceipt(){
        return ""; // change later
    }
}
