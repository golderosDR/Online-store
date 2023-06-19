package de.ait_tr.app;

import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.services.ProductService;
import de.ait_tr.services.ProductServiceImpl;

public class Menu {
    private final ProductService productService;
    private final ProductBasket productBasket;
    private static final String BASE_PATH = "Products.csv";
    Menu() {
        this.productService = new ProductServiceImpl(new ProductRepositoryImpl(BASE_PATH));
        this.productBasket = new ProductBasket();
    }

}
