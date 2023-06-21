package de.ait_tr.services;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.repositories.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

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
    public List<ProductDTO> findAllNotebooks() {
        return productRepository.findAllNotebooks();
    }

    @Override
    public List<ProductDTO> findAllTablets() {
        return productRepository.findAllTablets();
    }

    @Override
    public List<ProductDTO> findAllTVs() {
        return productRepository.findAllTVs();
    }

    @Override
    public List<ProductDTO> findAllBags() {
        return productRepository.findAllBags();
    }

    @Override
    public List<ProductDTO> findAllGlasses() {
        return productRepository.findAllGlasses();
    }

    @Override
    public List<ProductDTO> findAllBelts() {
        return productRepository.findAllBelts();
    }

    @Override
    public List<ProductDTO> find(String titlePart) {
        return productRepository.find(titlePart);
    }

    @Override
    public boolean buy(List<ProductInBasketDTO> productInBasketDTOList) {
        return productRepository.buy(productInBasketDTOList);
    }
}
