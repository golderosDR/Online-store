package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;

import java.util.List;

public interface ProductRepository {
    List<ProductDTO> findAll();

    List<ProductDTO> findAllElectronics();

    List<ProductDTO> findAllWatches();

    List<ProductDTO> findAllHeath();

    List<ProductDTO> findAllAccessories();

    ProductDTO find(String titlePart);

    boolean buy(ProductInBasketDTO productInBasketDTO);


}
