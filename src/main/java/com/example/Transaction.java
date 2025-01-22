package com.example;

public class Transaction {
    private String date;
    private String category;
    private double amount;
    private String description;

    // デフォルトコンストラクタ（必要）
    public Transaction() {}

    // パラメータ付きコンストラクタ
    public Transaction(String date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getter and Setter methods
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
