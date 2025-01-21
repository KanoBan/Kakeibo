module mvcapp {
    requires transitive javafx.graphics; // 必須
    requires javafx.controls;            // HBox を使用するため
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example;
}
