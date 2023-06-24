package de.ait_tr.dtos;

public record ProductInBasketDTO(String productId,
                                 String productTitle,
                                 double productPrice) {

    @Override
    public String toString() {
        return "ProductInBasketDTO{" +
                "productId='" + productId + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
