package de.ait_tr.models;

import de.ait_tr.dtos.BasketRecordDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private final String dateTime;
    private final String orderId;
    private final int orderNumber;
    private final String userId;
    private final List<BasketRecordDTO> productBasket;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Order(List<BasketRecordDTO> productBasket, int lastOrderNumber) {
        this.orderId = "ORDER" + ++lastOrderNumber;
        this.dateTime = formatter.format(LocalDateTime.now());
        this.productBasket = productBasket;
        this.orderNumber = lastOrderNumber;
        this.userId = "Guest";
    }

    public Order(String dateTime, String orderId, int orderNumber, String userId, List<BasketRecordDTO>  productBasket) {
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

    public List<BasketRecordDTO>  getProductBasket() {
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
