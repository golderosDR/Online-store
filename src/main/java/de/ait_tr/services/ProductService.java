package de.ait_tr.services;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;

import java.util.List;

public interface ProductService {
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

    List<ProductDTO> find(String titlePart);

    boolean buy(List<ProductInBasketDTO> productInBasketDTOList);
}
