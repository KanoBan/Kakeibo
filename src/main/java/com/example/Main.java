package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ExpenseManager expenseManager = new ExpenseManager();
    private IncomeManager incomeManager = new IncomeManager();
    private BalanceCalculator balanceCalculator = new BalanceCalculator(expenseManager, incomeManager);
    private Label dateLabel;

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("ファイル");
        MenuItem exitMenuItem = new MenuItem("終了");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(exitMenuItem);

        Menu viewMenu = new Menu("表示");
        MenuItem monthlyReportMenuItem = new MenuItem("月次レポート");
        monthlyReportMenuItem.setOnAction(e -> showMonthlyReport());
        viewMenu.getItems().addAll(monthlyReportMenuItem);

        menuBar.getMenus().addAll(fileMenu, viewMenu);

        // 現在の日付を表示
        LocalDate today = LocalDate.now();
        dateLabel = new Label("今日の日付: " + formatLocalDate(today));
        dateLabel.setId("dateLabel");

        Label balanceLabel = new Label("残高: " + balanceCalculator.calculateBalance() + "円");

        Button addIncomeButton = new Button("収入を追加");
        addIncomeButton.setOnAction(e -> addSampleIncome());

        VBox mainContent = new VBox(10, dateLabel, balanceLabel, addIncomeButton);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setTitle("家計簿アプリ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMonthlyReport() {
        LocalDate today = LocalDate.now();
        MonthlyReport report = new MonthlyReport(expenseManager, incomeManager, today.getYear(), today.getMonthValue());
        report.generateReport();
    }

    private void addSampleIncome() {
        LocalDate today = LocalDate.now();
        Income sampleIncome = new Income(today, "給与", 200000, "今月の給与");
        incomeManager.addIncome(sampleIncome);
        System.out.println("収入を追加しました: " + sampleIncome.getAmount() + "円");
    }

    private String formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return date.format(formatter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
