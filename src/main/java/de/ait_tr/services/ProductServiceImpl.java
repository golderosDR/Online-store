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
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> findAllSmartphones() {
        return productRepository.findAllSmartphones();
    }

    @Override
    public List<ProductDTO> findAllWatches() {
        return productRepository.findAllWatches();
    }

    @Override
    public List<ProductDTO> findAllHeath() {
        return productRepository.findAllHeath();
    }

    @Override
    public List<ProductDTO> findAllAccessories() {
        return productRepository.findAllAccessories();
    }

    @Override
    public ProductDTO find(String titlePart) {
        return productRepository.find(titlePart);
    }

    @Override
    public boolean buy(ProductInBasketDTO productInBasketDTO) {
        return productRepository.buy(productInBasketDTO);
    }
}
