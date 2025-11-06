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

    }

    public double calculateTotal(){

    }

    public String generateReceipt(){

    }
}
