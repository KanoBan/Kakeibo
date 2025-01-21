package com.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private final List<String> categories;

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
        saveCategories();
        return true;
    }

    public boolean removeCategory(String category) {
        boolean removed = categories.remove(category);
        if (removed) {
            saveCategories();
        }
        return removed;
    }

    private void saveCategories() {
        // カテゴリーリストを永続化するロジックを実装
        JSONUtility.saveCategories(categories, "categories.json");
    }
}
