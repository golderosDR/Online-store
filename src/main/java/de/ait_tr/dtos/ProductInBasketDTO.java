package de.ait_tr.dtos;

import java.util.Objects;

public class ProductInBasketDTO {
    private final String id;
    private final int count;

    ProductInBasketDTO(String id, int count) {
        this.id = id;
        this.count = count;
    }



    public String getId() {
        return id;
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
        return Objects.hash(id, count);
    }
}
