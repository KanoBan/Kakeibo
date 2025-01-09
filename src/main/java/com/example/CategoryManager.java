package com.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private List<String> categories;

    public CategoryManager() {
        categories = new ArrayList<>();
        initializeDefaultCategories();
    }

    private void initializeDefaultCategories() {
        categories.add("Food");
        categories.add("Transportation");
        categories.add("Utilities");
        categories.add("Entertainment");
        categories.add("Savings");
    }

    public void addCategory(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(String category) {
        categories.remove(category);
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }
}
