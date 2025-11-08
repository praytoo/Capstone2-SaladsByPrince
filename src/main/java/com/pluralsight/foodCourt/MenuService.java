package com.pluralsight.foodCourt;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final List<MenuItem> menuItems = new ArrayList<>();

    public MenuService() {
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems); // return a copy
    }
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }
}

