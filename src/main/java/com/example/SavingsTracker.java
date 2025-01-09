package com.example;

public class SavingsTracker {
    private double totalSavings;

    public SavingsTracker(double initialSavings) {
        this.totalSavings = initialSavings;
    }

    public void addSavings(double amount) {
        if (amount > 0) {
            totalSavings += amount;
        }
    }

    public void deductSavings(double amount) {
        if (amount > 0 && totalSavings >= amount) {
            totalSavings -= amount;
        }
    }

    public double getTotalSavings() {
        return totalSavings;
    }
}
