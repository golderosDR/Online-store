package de.ait_tr.repositories;

import de.ait_tr.models.Order;
import de.ait_tr.models.ProductBasket;

public interface OrderRepository {
    void save(Order order);

    Order create(ProductBasket productBasket);

}
