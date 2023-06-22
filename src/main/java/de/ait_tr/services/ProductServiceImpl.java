package de.ait_tr.services;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.models.Category;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepository;
import de.ait_tr.validators.BasketValidator;

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
    public List<ProductDTO> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<ProductDTO> find(String searchInfo) {
        return productRepository.find(searchInfo);
    }

    @Override
    public ProductDTO findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean buy(ProductBasket productBasket) {
        if (BasketValidator.validate(productBasket, productRepository.findAll())) {
            productRepository.update(
                    productRepository.getUpdatedList(
                            productBasket.getProductsInBasket()
                    )
            );
            return true;
        }
        return false;
    }
}
