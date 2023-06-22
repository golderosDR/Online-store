package de.ait_tr.models;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.InBasketDTO;
import de.ait_tr.mappers.DTOMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {

    private final List<InBasketDTO> productsInBasket;
    public ProductBasket() {
        this.productsInBasket = new ArrayList<>();
    }

    public List<InBasketDTO> getProductsInBasket() {
        return productsInBasket;
    }
    public void add(ProductDTO productDTO, int count) {
        for (InBasketDTO inBasketDTO: productsInBasket) {
            if (inBasketDTO.getId().equals(productDTO.getId())) {
               inBasketDTO.setCount(inBasketDTO.getCount() + count);
               return;
            }
        }
        productsInBasket.add(DTOMapper.toProductInBasketDTO(productDTO, count));
    }
}
