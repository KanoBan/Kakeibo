package com.example;

import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String category;
    private double amount;
    private String note;

    public Expense(LocalDate date, String category, double amount, String note) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.note = note;
    }

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
