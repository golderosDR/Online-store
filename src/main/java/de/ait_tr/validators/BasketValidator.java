package de.ait_tr.validators;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.InBasketDTO;
import de.ait_tr.models.ProductBasket;

import java.util.List;


public class BasketValidator {
    private BasketValidator() {}
    public static boolean validate(ProductBasket productBasket, List<ProductDTO> productDTOList) {
        List<InBasketDTO> inBasketDTOList = productBasket.getProductsInBasket();
        for (InBasketDTO inBasketDTO : inBasketDTOList) {
            for (ProductDTO productDTO : productDTOList) {
                if (inBasketDTO.getId().equals(productDTO.getId())) {
                    if (inBasketDTO.getCount() > productDTO.getAmount()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
