package de.ait_tr.dtos;

public record ProductInBasketDTO(String id, String title, double price) {

    @Override
    public String toString() {
        return "ProductInBasketDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
