package de.ait_tr.models;

public class Product {
    private final String id;
    private final String title;
    private final Category category;
    private final double basicPrice;
    private final int markup;
    private int amount;
    private final String description;

    public Product(String id, String title, Category category, double basicPrice, int markup, int amount, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.basicPrice = basicPrice;
        this.markup = markup;
        this.amount = amount;
        this.description = description;
    }

    public String getId() { return id; }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public double getMarkup() {
        return markup;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + id + '\'' +
                ", productTitle='" + title + '\'' +
                ", category=" + category +
                ", basicPrice=" + basicPrice +
                ", markup=" + markup +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}

