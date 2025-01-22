package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private Button removeCategoryButton;

    @FXML
    private ListView<String> categoryListView;

    @FXML
    private Button nextButton;

    private final List<String> categories = new ArrayList<>();

    @FXML
    public void initialize() {
        categories.add("食費");
        categories.add("交通費");
        categories.add("娯楽");
        categories.add("その他");
        categoryListView.getItems().addAll(categories);

        nextButton.disableProperty().bind(Bindings.isEmpty(balanceField.textProperty()));

        addCategoryButton.setOnAction(event -> addCategory());
        removeCategoryButton.setOnAction(event -> removeCategory());
        nextButton.setOnAction(event -> saveAndProceed());
    }

    private void addCategory() {
        String category = categoryInputField.getText().trim();
        if (!category.isEmpty() && !categories.contains(category)) {
            categories.add(category);
            categoryListView.getItems().add(category);
            categoryInputField.clear();
        } else {
            showAlert("エラー", "カテゴリーを正しく入力してください。");
        }
    }

    private void removeCategory() {
        String selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            categories.remove(selectedCategory);
            categoryListView.getItems().remove(selectedCategory);
        } else {
            showAlert("エラー", "削除するカテゴリーを選択してください。");
        }
    }

    private void saveAndProceed() {
        try {
            double balance = Double.parseDouble(balanceField.getText());
            int payday = Integer.parseInt(paydayField.getText());

            if (payday < 1 || payday > 31) {
                throw new NumberFormatException("Invalid payday");
            }

            User user = new User(balance, payday, categories);
            JSONUtility.saveUserData(user, "user_data.json");

            SceneSwitcher.switchTo("/com/example/main.fxml");
        } catch (NumberFormatException e) {
            showAlert("エラー", "所持金や給料日は正しい形式で入力してください。");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
