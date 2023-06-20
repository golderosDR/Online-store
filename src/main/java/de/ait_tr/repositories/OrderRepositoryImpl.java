package de.ait_tr.repositories;

public class OrderRepositoryImpl implements OrderRepository {
    private final String fileName;
    OrderRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }
}
