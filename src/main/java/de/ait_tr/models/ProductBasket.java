package de.ait_tr.models;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.mapper.DTOMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {

    private List<ProductInBasketDTO> productsInBasket;
    public ProductBasket() {
        this.productsInBasket = new ArrayList<>();
    }

    public List<ProductInBasketDTO> getProductsInBasket() {
        return productsInBasket;
    }
    public void add(ProductDTO productDTO, int count) {
        productsInBasket.add(DTOMapper.toProductInBasketDTO(productDTO, count));
    }
}
