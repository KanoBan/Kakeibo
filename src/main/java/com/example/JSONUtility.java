package com.example;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // ユーザーデータを保存するメソッド
    public static void saveUserData(User user, String filePath) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, user); // ユーザーデータを JSON ファイルに書き込む
            System.out.println("User data saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ユーザーデータが保存済みか確認するメソッド
    public static boolean isInitialSetupDone() {
        File file = new File("user_data.json"); // デフォルトのユーザーデータファイルパス
        return file.exists(); // ファイルが存在する場合に true を返す
    }

    public static void resetUserData(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("User data file deleted: " + filePath);
            } else {
                System.err.println("Failed to delete user data file: " + filePath);
            }
        } else {
            System.out.println("User data file not found: " + filePath);
        }
    }
}
