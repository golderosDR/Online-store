package de.ait_tr.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private final String dateTime;
    private final String orderId;
    private final int orderNumber;
    private final String userId;
    private final ProductBasket productBasket;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Order(ProductBasket productBasket, int lastOrderNumber) {
        this.orderId = "ORDER" + ++lastOrderNumber;
        this.dateTime = formatter.format(LocalDateTime.now());
        this.productBasket = productBasket;
        this.orderNumber = ++lastOrderNumber;
        this.userId = "Guest";
    }

    public Order(String dateTime, String orderId, int orderNumber, String userId, ProductBasket productBasket) {
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.productBasket = productBasket;
    }

    public String getUserId() {
        return userId;
    }

    public int getOrderNumber() {
        return orderNumber;
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
    @Override
    public String toString() {
        return "Order{" +
                "dateTime='" + dateTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", productBasket=" + productBasket +
                '}';
    }
}
