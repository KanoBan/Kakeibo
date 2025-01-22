module mvcapp {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base; 

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example;
}
