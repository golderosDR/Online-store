package de.ait_tr.models;

import de.ait_tr.dtos.ProductInBasketDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {

    private List<ProductInBasketDTO> productsInBasket;
    public ProductBasket() {
        this.productsInBasket = new ArrayList<>();
    }
}
