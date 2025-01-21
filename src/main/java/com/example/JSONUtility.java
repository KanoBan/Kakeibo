package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // ユーザーデータを保存する
    public static void saveUserData(User user, String filePath) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, user);
            System.out.println("User data saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ユーザーデータを読み込む
    public static User loadUserData(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, User.class);
            } else {
                System.out.println("User data file not found.");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // カテゴリーリストを保存する
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

    // カテゴリーリストを読み込む
    public static List<String> loadCategories(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, List.class);
            }
        } catch (IOException e) {
            System.err.println("Failed to load categories: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>(); // 空のリストを返す
    }

    // ユーザーデータをリセットする
    public static void resetUserData(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("User data file deleted successfully.");
            } else {
                System.err.println("Failed to delete user data file.");
            }
        } else {
            System.out.println("User data file does not exist.");
        }
    }

    // 初期設定が完了しているか確認
    public static boolean isInitialSetupDone(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
