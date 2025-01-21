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
        categories.add("食費");
        categories.add("交通費");
        categories.add("娯楽");
        categories.add("その他");
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }

    public boolean addCategory(String category) {
        if (categories.contains(category)) {
            return false; // 既に存在する場合は追加しない
        }
        categories.add(category);
        return true;
    }

    public boolean removeCategory(String category) {
        return categories.remove(category); // 存在しない場合は false
    }

    public boolean updateCategory(String oldCategory, String newCategory) {
        int index = categories.indexOf(oldCategory);
        if (index != -1) {
            categories.set(index, newCategory);
            return true;
        }
        return false;
    }
}
