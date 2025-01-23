package com.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private User user;
    private static final String USER_DATA_FILE = "user_data.json";
    private static final String CATEGORIES_FILE = "categories.json";

    public CategoryManager() {
        user = JSONUtility.loadUserData(USER_DATA_FILE);
        if (user == null) {
            user = new User(0.0, 1, new ArrayList<>());
        }
        syncCategoriesWithJson();
    }

    /**
     * 現在のカテゴリーリストを取得
     * @return カテゴリーリスト
     */
    public List<String> getCategories() {
        return user.getCategories();
    }

    /**
     * カテゴリーを追加
     * @param category 追加するカテゴリー
     */
    public void addCategory(String category) {
        if (!user.getCategories().contains(category)) {
            user.getCategories().add(category);
            saveCategories();
        }
    }

    /**
     * カテゴリーを削除
     * @param category 削除するカテゴリー
     */
    public void removeCategory(String category) {
        if (user.getCategories().remove(category)) {
            saveCategories();
        }
    }

    /**
     * カテゴリーを保存
     */
    private void saveCategories() {
        // ユーザーデータを保存
        JSONUtility.saveUserData(user, USER_DATA_FILE);

        // カテゴリーを categories.json に保存
        JSONUtility.saveCategories(user.getCategories(), CATEGORIES_FILE);
    }

    /**
     * categories.json と user_data.json を同期
     */
    private void syncCategoriesWithJson() {
        // categories.json からカテゴリーを読み込み
        List<String> categoriesFromJson = JSONUtility.loadCategories(CATEGORIES_FILE);

        // user_data.json のカテゴリーと統合
        for (String category : categoriesFromJson) {
            if (!user.getCategories().contains(category)) {
                user.getCategories().add(category);
            }
        }

        // 統合結果を両方に保存
        saveCategories();
    }
}
