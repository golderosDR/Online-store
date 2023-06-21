package de.ait_tr.repositories;

public class OrderRepositoryImpl implements OrderRepository {
    private final String orderFileName;
    private final String checkFileName;

    public OrderRepositoryImpl(String orderFileName, String checkFileName) {
        this.orderFileName = orderFileName;
        this.checkFileName = checkFileName;
    }
}
