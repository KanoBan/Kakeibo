package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    private TextArea textArea;

    @FXML
    private Button saveButton;

    private Model model;

    public void initModel(Model model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model;

        // Model の text プロパティを TextArea とバインド
        textArea.textProperty().bindBidirectional(model.text);

        // Save ボタンのアクションを設定
        saveButton.setOnAction(event -> model.save());
    }
}
