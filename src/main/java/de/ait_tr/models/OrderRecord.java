package de.ait_tr.models;

public record OrderRecord(String dateTime,
                          String orderId,
                          int orderNumber,
                          String userId,
                          String productId,
                          int count) {

    @Override
    public String toString() {
        return "OrderRecord{" +
                "dateTime='" + dateTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNumber=" + orderNumber +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", count=" + count +
                '}';
    }
}
