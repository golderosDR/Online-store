package de.ait_tr.dtos;
import java.util.Objects;

public record ProductDTO(String id, String title, String category, double price, int amount, String description) {

    public String getTitleIgnoreCase() {
        return title.toLowerCase();
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
