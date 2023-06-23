package de.ait_tr.repositories;

public class OrderRepositoryImpl implements OrderRepository {
    private final String orderFileName;


    public OrderRepositoryImpl(String orderFileName) {
        this.orderFileName = orderFileName;
    }
}
