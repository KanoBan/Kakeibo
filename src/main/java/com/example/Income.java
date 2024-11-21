package com.example;

import java.time.LocalDate;

public class Income {
    private LocalDate date;
    private String source;
    private double amount;
    private String note;

    public Income(LocalDate date, String source, double amount, String note) {
        this.date = date;
        this.source = source;
        this.amount = amount;
        this.note = note;
    }

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
