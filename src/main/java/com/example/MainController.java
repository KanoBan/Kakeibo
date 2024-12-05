package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    private Button saveButton;

    @FXML
    private TextArea textArea;

    private Model model;
    
    public void initModel(Model model) {
		if (this.model != null)
			throw new IllegalStateException("Model can only be initialized once");
		
    	this.model = model;
    	
    	// Bind Model to View
		textArea.textProperty().bindBidirectional(model.text);
		
		// Event Handler
		saveButton.setOnAction(e -> {
			model.save();
        });			
    }
    
	public void initialize() {
		// Write your initialization code here
	}
}