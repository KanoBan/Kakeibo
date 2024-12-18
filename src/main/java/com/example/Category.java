package com.example;

public class Category {
    private String name;
    private double totalAmount;

    public Category(String name) {
        this.name = name;
        this.totalAmount = 0;
    }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getTotalAmount() { return totalAmount; }
    public void addAmount(double amount) { this.totalAmount += amount; }
}
