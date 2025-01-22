package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // ユーザーデータを保存
    public static void saveUserData(User user, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), user);
            System.out.println("User data saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ユーザーデータを読み込み
    public static User loadUserData(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, User.class);
            }
        } catch (IOException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // ユーザーデータをリセット
    public static void resetUserData(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.delete()) {
            System.out.println("User data file deleted successfully.");
        } else {
            System.err.println("Failed to delete user data file or file does not exist.");
        }
    }

    // 初期設定が完了しているか確認
    public static boolean isInitialSetupDone(String filePath) {
        File file = new File(filePath);
        return file.exists(); // ファイルが存在するかどうかを確認
    }

    // カテゴリーリストを保存
    public static void saveCategories(List<String> categories, String filePath) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, categories);
            System.out.println("Categories saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save categories: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // カテゴリーリストを読み込み
    public static List<String> loadCategories(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<String>>() {});
            }
        } catch (IOException e) {
            System.err.println("Failed to load categories: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>(); // ファイルが存在しない場合は空のリストを返す
    }
}
