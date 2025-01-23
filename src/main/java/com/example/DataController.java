package com.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class DataController {

    @FXML
    private TableView<Transaction> dataTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> descriptionColumn;

    @FXML
    private Button backButton;

    private ObservableList<Transaction> transactions;
    private ObservableList<String> categories;

    @FXML
    public void initialize() {
        // JSONからトランザクションとカテゴリーをロード
        transactions = FXCollections.observableArrayList(JSONUtility.loadTransactions("transactions.json"));
        reloadCategories();

        // テーブルの列にプロパティをバインド
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        amountColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAmount()).asObject());
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        // 編集可能に設定
        dataTable.setEditable(true);

        // カテゴリー列: コンボボックスセル
        categoryColumn.setCellFactory(ComboBoxTableCell.forTableColumn(categories));
        categoryColumn.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setCategory(event.getNewValue());
            saveChanges(); // 変更を保存
        });

        // 金額列: テキストフィールドセル（数値）
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        amountColumn.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setAmount(event.getNewValue());
            saveChanges(); // 変更を保存
        });

        // 説明列: テキストフィールドセル
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> {
            Transaction transaction = event.getRowValue();
            transaction.setDescription(event.getNewValue());
            saveChanges(); // 変更を保存
        });

        dataTable.setItems(transactions);

        backButton.setOnAction(event -> SceneSwitcher.switchTo("/com/example/main.fxml"));
    }

    // カテゴリーリストを再読み込み
    private void reloadCategories() {
        categories = FXCollections.observableArrayList(JSONUtility.loadCategories("categories.json"));
        categoryColumn.setCellFactory(ComboBoxTableCell.forTableColumn(categories));
    }

    // トランザクション変更を保存
    private void saveChanges() {
        JSONUtility.saveTransactions(transactions, "transactions.json");
    }
}
