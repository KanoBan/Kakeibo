module MyAppModule {
    requires transitive javafx.graphics;  // Stage クラスを公開
    requires javafx.controls;            // コントロール UI (Button, TextArea)
    requires javafx.fxml;                // FXML サポート
    requires com.fasterxml.jackson.databind; // Jackson ライブラリ

    opens com.example to javafx.fxml;    // パッケージを FXML に公開
    exports com.example;                 // パッケージを他モジュールに公開
}
