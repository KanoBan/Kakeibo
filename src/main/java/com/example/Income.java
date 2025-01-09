package com.example;

public class Income {
    private double amount;
    private String source;

    public Income(double amount, String source) {
        this.amount = amount;
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Income from " + source + ": $" + amount;
    }
}
