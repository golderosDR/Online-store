package de.ait_tr.dtos;

import de.ait_tr.models.Category;

import java.util.Objects;

public class ProductDTO {

    private final String id;
    private final String title;
    private final String category;
    private final double price;
    private int amount;
    private final String description;

    public ProductDTO(String id, String title, String category, double price, int amount, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
