package com.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryController {
    @FXML
    private TableView<String> categoryTable;

    @FXML
    private TableColumn<String, String> categoryColumn;

    @FXML
    private TextField categoryInput;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button backButton;

    private final ObservableList<String> categories = FXCollections.observableArrayList();

    private final CategoryManager categoryManager = new CategoryManager();

    @FXML
    public void initialize() {
        // CategoryManagerからカテゴリをロード
        categories.addAll(categoryManager.getCategories());

        // TableViewの初期化
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        categoryTable.setItems(categories);

        // ボタンの動作を設定
        addButton.setOnAction(event -> addCategory());
        removeButton.setOnAction(event -> removeCategory());
        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }

    private void addCategory() {
        String newCategory = categoryInput.getText().trim();

        if (newCategory.isEmpty()) {
            showAlert("エラー", "カテゴリ名を入力してください。");
            return;
        }

        if (categories.contains(newCategory)) {
            showAlert("エラー", "このカテゴリは既に存在します。");
            return;
        }

        categoryManager.addCategory(newCategory); // CategoryManagerに追加
        categories.add(newCategory); // TableViewにも追加
        categoryInput.clear(); // 入力フィールドをクリア
    }

    private void removeCategory() {
        String selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            showAlert("エラー", "削除するカテゴリを選択してください。");
            return;
        }

        categoryManager.removeCategory(selectedCategory); // CategoryManagerから削除
        categories.remove(selectedCategory); // TableViewから削除
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
