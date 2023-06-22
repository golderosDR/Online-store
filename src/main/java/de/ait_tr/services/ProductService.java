package de.ait_tr.services;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.models.Category;
import de.ait_tr.models.ProductBasket;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();

    List<ProductDTO> findByCategory(Category category);
    List<ProductDTO> find(String searchInfo);
    ProductDTO findById(String id);
    boolean buy(ProductBasket productBasket);
}
