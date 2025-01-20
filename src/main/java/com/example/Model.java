package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	public StringProperty text = new SimpleStringProperty();
	
	private String filePath = "memo.txt";
	
	private void load() {
		try {
			text.set(Files.readString(Path.of(filePath)));
		} catch (NoSuchFileException e) {
			System.out.println("File not found: " + filePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
            Files.writeString(Path.of(filePath), text.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Model() {
		load();
	}
	
}
