package de.ait_tr.services;

import de.ait_tr.models.Order;
import de.ait_tr.repositories.OrderRepository;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }
}
