package de.ait_tr.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int counter = 1;
    private final String dateTime;
    private String orderId;
    private ProductBasket productBasket;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Order(ProductBasket productBasket) {
        this.orderId = "A00" + counter++;
        this.dateTime = formatter.format(LocalDateTime.now());
        this.productBasket = productBasket;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public ProductBasket getProductBasket() {
        return productBasket;
    }
}
