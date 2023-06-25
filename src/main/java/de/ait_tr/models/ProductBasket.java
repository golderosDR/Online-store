package de.ait_tr.models;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.mappers.ProductDTOMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {

    private final List<BasketRecordDTO> productsInBasket;
    public ProductBasket() {
        this.productsInBasket = new ArrayList<>();
    }
    public ProductBasket( List<BasketRecordDTO> productsInBasket) {
        this.productsInBasket = productsInBasket;
    }
    public List<BasketRecordDTO> getProductsInBasket() {
        return productsInBasket;
    }
    public void add(ProductDTO productDTO, int count) {
        for (BasketRecordDTO basketRecordDTO : productsInBasket) {
            if (basketRecordDTO.getId().equals(productDTO.id())) {
               basketRecordDTO.setCount(basketRecordDTO.getCount() + count);
               return;
            }
        }
        productsInBasket.add(ProductDTOMapper.toBasketRecordDTO(productDTO, count));
    }
    public void remove(int index) {
        productsInBasket.remove(index);
    }
    public void edit(int index, int newCount) {
        productsInBasket.get(index).setCount(newCount);
    }
    public void clear() {
        productsInBasket.clear();
    }

    @Override
    public String toString() {
        return "ProductBasket{" +
                "productsInBasket=" + productsInBasket +
                '}';
    }
}
