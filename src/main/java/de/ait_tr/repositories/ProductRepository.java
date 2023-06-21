package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.models.Product;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAll();
    List<ProductDTO> findAllSmartphones();
    List<ProductDTO> findAllWatches();
    List<ProductDTO> findAllHeath();
    List<ProductDTO> findAllAccessories();
    List<ProductDTO> findAllNotebooks();
    List<ProductDTO> findAllTablets();
    List<ProductDTO> findAllTVs();
    List<ProductDTO> findAllBags();
    List<ProductDTO> findAllGlasses();
    List<ProductDTO> findAllBelts();
    List<ProductDTO> find(String searchInfo);
    ProductDTO findById(String id);
    void save(List<Product> productList);
    boolean buy(List<ProductInBasketDTO> productInBasketDTOList);





}
