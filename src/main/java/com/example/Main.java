package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private ExpenseManager expenseManager = new ExpenseManager();
    private IncomeManager incomeManager = new IncomeManager();
    private BalanceCalculator balanceCalculator = new BalanceCalculator(expenseManager, incomeManager);
    private Label dateLabel;
    private Label balanceLabel; // 残高ラベルをインスタンス変数として定義
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        
        // ファイルメニュー
        Menu fileMenu = new Menu("ファイル");
        MenuItem exitMenuItem = new MenuItem("終了");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(exitMenuItem);

        // 表示メニュー
        Menu viewMenu = new Menu("表示");
        MenuItem monthlyReportMenuItem = new MenuItem("月次レポート");
        monthlyReportMenuItem.setOnAction(e -> showMonthlyReport());

        MenuItem fullScreenItem = new MenuItem("フルスクリーン");
        MenuItem windowedFullScreenItem = new MenuItem("ウィンドウフルスクリーン");
        MenuItem exitFullScreenItem = new MenuItem("フルスクリーン解除");

        fullScreenItem.setOnAction(e -> primaryStage.setFullScreen(true));
        windowedFullScreenItem.setOnAction(e -> {
            primaryStage.initStyle(StageStyle.UNDECORATED);
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(screenBounds.getMinX());
            primaryStage.setY(screenBounds.getMinY());
            primaryStage.setWidth(screenBounds.getWidth());
            primaryStage.setHeight(screenBounds.getHeight());
        });
        exitFullScreenItem.setOnAction(e -> {
            primaryStage.setFullScreen(false);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();
        });

        viewMenu.getItems().addAll(monthlyReportMenuItem, fullScreenItem, windowedFullScreenItem, exitFullScreenItem);

        // テーマ切り替えメニュー
        Menu themeMenu = new Menu("テーマ");
        MenuItem blueTheme = new MenuItem("水色系");
        blueTheme.setOnAction(e -> setTheme("blue_theme.css"));

        MenuItem redTheme = new MenuItem("赤系");
        redTheme.setOnAction(e -> setTheme("red_theme.css"));

        MenuItem greenTheme = new MenuItem("緑系");
        greenTheme.setOnAction(e -> setTheme("green_theme.css"));

        MenuItem yellowTheme = new MenuItem("黄色系");
        yellowTheme.setOnAction(e -> setTheme("yellow_theme.css"));

        MenuItem purpleTheme = new MenuItem("紫系");
        purpleTheme.setOnAction(e -> setTheme("purple_theme.css"));

        MenuItem monochromeTheme = new MenuItem("黒灰白系");
        monochromeTheme.setOnAction(e -> setTheme("monochrome_theme.css"));

        themeMenu.getItems().addAll(blueTheme, redTheme, greenTheme, yellowTheme, purpleTheme, monochromeTheme);

        // メニューバーに追加
        menuBar.getMenus().addAll(fileMenu, viewMenu, themeMenu);

        // 現在の日付を表示
        LocalDate today = LocalDate.now();
        dateLabel = new Label("今日の日付: " + formatLocalDate(today));
        dateLabel.setId("dateLabel");

        // 残高ラベル
        balanceLabel = new Label("残高: " + balanceCalculator.calculateBalance() + "円");

        // 収入を追加するボタン
        Button addIncomeButton = new Button("収入を追加");
        addIncomeButton.setOnAction(e -> addSampleIncome());

        VBox mainContent = new VBox(10, dateLabel, balanceLabel, addIncomeButton);
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(mainContent);

        scene = new Scene(root, 800, 600);

        // 初期テーマを設定
        setTheme("blue_theme.css");

        primaryStage.setTitle("家計簿アプリ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 指定されたCSSテーマを適用する
     */
    private void setTheme(String cssFileName) {
        scene.getStylesheets().clear();
        String cssPath = getClass().getResource("/" + cssFileName).toExternalForm();
        if (cssPath != null) {
            scene.getStylesheets().add(cssPath);
        } else {
            System.err.println("CSSファイルが見つかりません: " + cssFileName);
        }
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

        // 残高の再計算
        balanceLabel.setText("残高: " + balanceCalculator.calculateBalance() + "円");
    }

    private String formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return date.format(formatter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
