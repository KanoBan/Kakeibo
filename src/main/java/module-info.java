<<<<<<< HEAD
module MyAppModule {
    requires transitive javafx.graphics;  // Stage クラスを公開
    requires javafx.controls;            // コントロール UI (Button, TextArea)
    requires javafx.fxml;                // FXML サポート
    requires com.fasterxml.jackson.databind; // Jackson ライブラリ

    opens com.example to javafx.fxml;    // パッケージを FXML に公開
    exports com.example;                 // パッケージを他モジュールに公開
=======
module mvcapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example to javafx.fxml, com.fasterxml.jackson.databind;
>>>>>>> 14bab891c1200f6ddf9532abdf7926dcb80185f0
}
