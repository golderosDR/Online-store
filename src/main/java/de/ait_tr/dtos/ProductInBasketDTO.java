package de.ait_tr.dtos;

import java.util.Objects;

public class ProductInBasketDTO {
    private final String title;
    private final String id;
    private final double price;
    private final int count;

    ProductInBasketDTO(String title, String id, double price, int count) {
        this.title = title;
        this.id = id;
        this.price = price;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInBasketDTO that = (ProductInBasketDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, price, count);
    }
}
