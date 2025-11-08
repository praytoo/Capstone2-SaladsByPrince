package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final List<OrderItem> cart = new ArrayList<>();

    public void addItem(OrderItem item) {
        cart.add(item);
    }

    public List<OrderItem> getItems() {
        return cart;
    }

    public double getTotal() {
        return cart.stream().mapToDouble(OrderItem::getCost).sum();
    }

    public void clear() {
        cart.clear();
    }
}

