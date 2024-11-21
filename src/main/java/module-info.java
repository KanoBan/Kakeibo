// Most minimal module-info.java file for JavaFX
// If you use FXML, you need more complex settings.

// 'myapp': Your module name
// 'com.example': Your package name

module myapp {
    requires javafx.controls;
    opens com.example to javafx.graphics;
}