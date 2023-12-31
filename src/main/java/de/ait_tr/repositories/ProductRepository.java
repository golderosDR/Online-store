package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAll();
    List<ProductDTO> find(String searchInfo);
    List<ProductDTO> findByCategory(Category category);
    ProductDTO findById(String id);
    void update(List<BasketRecordDTO> basketRecordDTOList);





}
