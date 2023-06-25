package de.ait_tr.services;

import de.ait_tr.models.Order;
import de.ait_tr.models.ProductBasket;

public interface OrderService {
    void save(Order order);

    Order create(ProductBasket productBasket, String userId);

    void printCheck(Order order);
}
