package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * ユーザーデータを保存
     * @param user ユーザーデータ
     * @param filePath 保存先のファイルパス
     */
    public static void saveUserData(User user, String filePath) {
        if (user == null) {
            System.err.println("Cannot save null User data.");
            return;
        }
        try {
            objectMapper.writeValue(new File(filePath), user);
            System.out.println("User data saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save user data to " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ユーザーデータを読み込み
     * @param filePath 読み込み元のファイルパス
     * @return 読み込んだユーザーデータ、存在しない場合はnull
     */
    public static User loadUserData(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("User data file not found at " + filePath);
            return null;
        }
        try {
            return objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            System.err.println("Failed to load user data from " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * トランザクションリストを保存
     * @param transactions トランザクションリスト
     * @param filePath 保存先のファイルパス
     */
    public static void saveTransactions(List<Transaction> transactions, String filePath) {
        if (transactions == null) {
            System.err.println("Cannot save null Transactions list.");
            return;
        }
        try {
            objectMapper.writeValue(new File(filePath), transactions);
            System.out.println("Transactions saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save transactions to " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * トランザクションリストを読み込み
     * @param filePath 読み込み元のファイルパス
     * @return 読み込んだトランザクションリスト、存在しない場合は空のリスト
     */
    public static List<Transaction> loadTransactions(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Transactions file not found at " + filePath);
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});
        } catch (IOException e) {
            System.err.println("Failed to load transactions from " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * カテゴリーリストを保存
     * @param categories カテゴリーリスト
     * @param filePath 保存先のファイルパス
     */
    public static void saveCategories(List<String> categories, String filePath) {
        if (categories == null) {
            System.err.println("Cannot save null Categories list.");
            return;
        }
        try {
            objectMapper.writeValue(new File(filePath), categories);
            System.out.println("Categories saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save categories to " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * カテゴリーリストを読み込み
     * @param filePath 読み込み元のファイルパス
     * @return 読み込んだカテゴリーリスト、存在しない場合は空のリスト
     */
    public static List<String> loadCategories(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Categories file not found at " + filePath);
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            System.err.println("Failed to load categories from " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * アプリの初期設定が完了しているか確認
     * @param filePath 確認対象のファイルパス
     * @return ファイルが存在すればtrue、存在しなければfalse
     */
    public static boolean isInitialSetupDone(String filePath) {
        File file = new File(filePath);
        boolean exists = file.exists();
        System.out.println("Initial setup check: " + (exists ? "Completed" : "Not completed"));
        return exists;
    }

    /**
     * ユーザーデータとカテゴリーを初期化
     * @param userFilePath ユーザーデータのファイルパス
     * @param categoriesFilePath カテゴリーデータのファイルパス
     */
    public static void resetAllData(String userFilePath, String categoriesFilePath) {
        List<String> defaultCategories = List.of("食費", "交通費", "娯楽", "その他");

        saveUserData(new User(0.0, 1, defaultCategories), userFilePath);
        saveCategories(defaultCategories, categoriesFilePath);

        saveTransactions(new ArrayList<>(), "transactions.json");
        System.out.println("User data and categories reset successfully.");
    }
}
