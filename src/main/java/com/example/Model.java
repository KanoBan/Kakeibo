package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
<<<<<<< HEAD
    public StringProperty text = new SimpleStringProperty();

    private String filePath = "memo.txt";

    private void load() {
        try {
            text.set(Files.readString(Path.of(filePath)));
            System.out.println("File loaded successfully: " + filePath);
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + filePath + ". Creating a new file...");
            try {
                Files.writeString(Path.of(filePath), ""); // 空のファイルを作成
                System.out.println("New file created: " + filePath);
            } catch (IOException ioException) {
                System.err.println("Failed to create file: " + filePath);
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + filePath);
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Files.writeString(Path.of(filePath), text.getValue());
            System.out.println("File saved successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save file: " + filePath);
            e.printStackTrace();
        }
    }

    public Model() {
        load();
    }
=======
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
	
>>>>>>> 14bab891c1200f6ddf9532abdf7926dcb80185f0
}
