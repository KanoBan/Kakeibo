package com.example;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Save Expenses to JSON file
    public static void saveExpensesToFile(List<Expense> expenses, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), expenses);
    }

    // Load Expenses from JSON file
    public static List<Expense> loadExpensesFromFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file, new TypeReference<List<Expense>>() {});
    }

    // Save User Data
    public static void saveUserData(User user, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), user);
    }

    // Load User Data
    public static User loadUserData(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return null;
        }

        return objectMapper.readValue(file, User.class);
    }
}
