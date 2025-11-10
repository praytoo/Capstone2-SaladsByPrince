package com.pluralsight.foodCourt;

import com.pluralsight.foodCourt.Order;
import com.pluralsight.foodCourt.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private Order currentOrder = new Order();
    private final List<Order> receipts = new ArrayList<>();

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void addItem(OrderItem item) {
        currentOrder.addItem(item);
    }

    public void checkout() {
        receipts.add(currentOrder);
        currentOrder = new Order();
    }

    public List<Order> getReceipts() {
        return receipts;
    }
}

