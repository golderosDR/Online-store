package de.ait_tr.models;

import de.ait_tr.models.Category;

public class Product {

    private Category category;
    private String title;
    private double price;
    private double margin;
    private String subscription;
    private int quantity;

    public Product(String title, double price, double margin, String subscription, int quantity) {
        this.title = title;
        this.price = price;
        this.margin = margin;
        this.subscription = subscription;
        this.quantity = quantity;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getMargin() {
        return margin;
    }

    public String getSubscription() {
        return subscription;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return "product{" +
                "category=" + category +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", margin=" + margin +
                ", subscription='" + subscription + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

