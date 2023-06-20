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

    public String categoryDiscriptionToString() {
        return  category.getDescription();
    }

    public int getAmount() {
        return amount;
    }


}

