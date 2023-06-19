package de.ait_tr.services;

import de.ait_tr.repositories.ProductRepository;

public class ProductServiceImpl implements PdoductService{
private final ProductRepository  productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
