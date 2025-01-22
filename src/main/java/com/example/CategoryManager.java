package com.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private User user;

    public CategoryManager() {
        user = JSONUtility.loadUserData("user_data.json");
        if (user == null) {
            user = new User(0.0, 1, new ArrayList<>());
        }
    }

    public List<String> getCategories() {
        return user.getCategories();
    }

    public void addCategory(String category) {
        if (!user.getCategories().contains(category)) {
            user.getCategories().add(category);
            saveCategories();
        }
    }

    public void removeCategory(String category) {
        user.getCategories().remove(category);
        saveCategories();
    }

    private void saveCategories() {
        JSONUtility.saveUserData(user, "user_data.json");
    }
}
