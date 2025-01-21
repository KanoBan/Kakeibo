package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class InitialController {
    @FXML
    private TextField balanceField;
    @FXML
    private TextField paydayField;
    @FXML
    private TextField categoryInputField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private ListView<String> categoryListView;
    @FXML
    private Button nextButton;

    private List<String> categories = new ArrayList<>();

    public void initialize() {
        // デフォルトカテゴリーを追加
        categories.add("食費");
        categories.add("交通費");
        categories.add("娯楽");
        categories.add("その他");
        categoryListView.getItems().addAll(categories);

        // カテゴリーを追加するボタン
        addCategoryButton.setOnAction(event -> addCategory());
        categoryInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addCategory();
            }
        });

        // 次へボタンでホーム画面へ遷移
        nextButton.setOnAction(event -> saveAndProceed());
    }

    private void addCategory() {
        String category = categoryInputField.getText().trim();
        if (!category.isEmpty() && !categories.contains(category)) {
            categories.add(category);
            categoryListView.getItems().add(category);
            categoryInputField.clear();
        }
    }

    private void saveAndProceed() {
        try {
            double balance = Double.parseDouble(balanceField.getText());
            int payday = Integer.parseInt(paydayField.getText());

            if (payday < 1 || payday > 31) {
                throw new NumberFormatException("Invalid payday");
            }

            // データを JSON に保存
            User user = new User(balance, payday, categories);
            JSONUtility.saveUserData(user, "user_data.json");

            // メイン画面に遷移
            SceneSwitcher.switchTo("/com/example/main.fxml");
        } catch (NumberFormatException e) {
            System.out.println("入力値が正しくありません");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
