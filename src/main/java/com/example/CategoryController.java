package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoryController {

    @FXML
    private ListView<String> categoryListView;

    @FXML
    private TextField categoryField;

    @FXML
    private Button addCategoryButton;

    @FXML
    private Button removeCategoryButton;

    @FXML
    private Button backButton;

    private final CategoryManager categoryManager = new CategoryManager();

    @FXML
    public void initialize() {
        // カテゴリーリストを初期化
        categoryListView.getItems().setAll(categoryManager.getCategories());

        // カテゴリー追加ボタンの動作
        addCategoryButton.setOnAction(event -> addCategory());

        // カテゴリー削除ボタンの動作
        removeCategoryButton.setOnAction(event -> removeCategory());

        // 戻るボタンの動作
        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/home.fxml"));
    }

    private void addCategory() {
        String newCategory = categoryField.getText().trim();
        if (newCategory.isEmpty()) {
            showAlert("エラー", "カテゴリー名を入力してください。");
            return;
        }
        if (categoryManager.addCategory(newCategory)) {
            categoryListView.getItems().add(newCategory);
            categoryField.clear();
        } else {
            showAlert("エラー", "そのカテゴリーは既に存在しています。");
        }
    }

    private void removeCategory() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory == null) {
            showAlert("エラー", "削除するカテゴリーを選択してください。");
            return;
        }
        if (categoryManager.removeCategory(selectedCategory)) {
            categoryListView.getItems().remove(selectedCategory);
        } else {
            showAlert("エラー", "カテゴリーを削除できませんでした。");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
