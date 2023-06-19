package de.ait_tr.services;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
private final ProductRepository  productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllElectronics() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllWatches() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllHeath() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllAccessories() {
        return null;
    }

    @Override
    public ProductDTO find(String titlePart) {
        return null;
    }

    @Override
    public boolean buy(ProductInBasketDTO productInBasketDTO) {
        return false;
    }
}
