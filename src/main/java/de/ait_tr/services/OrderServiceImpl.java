package de.ait_tr.services;

import de.ait_tr.models.Order;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.OrderRepository;
import de.ait_tr.repositories.OrderRepositoryImpl;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CheckService checkService;

    public OrderServiceImpl(OrderRepositoryImpl orderRepository, CheckServiceImpl checkService) {
        this.orderRepository = orderRepository;
        this.checkService = checkService;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order create(ProductBasket productBasket) {
        return orderRepository.create(productBasket);
    }

    @Override
    public void printCheck(Order order) {
        checkService.print(order);
    }
}
