package com.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private List<Category> categories;

    public CategoryManager() {
        this.categories = new ArrayList<>();
    }

    public void addCategory(String name) {
        if (categories.stream().noneMatch(c -> c.getName().equals(name))) {
            categories.add(new Category(name));
        }
    }

    public void removeCategory(String name) {
        categories.removeIf(c -> c.getName().equals(name));
    }

    public List<Category> getCategories() {
        return categories;
    }
}
