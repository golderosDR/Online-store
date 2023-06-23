package de.ait_tr.validators;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.models.ProductBasket;

import java.util.List;


public class BasketValidator {
    private BasketValidator() {}
    public static boolean validate(ProductBasket productBasket, List<ProductDTO> productDTOList) {
        List<BasketRecordDTO> basketRecordDTOList = productBasket.getProductsInBasket();

        for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {

            for (ProductDTO productDTO : productDTOList) {
                if (basketRecordDTO.getId().equals(productDTO.getId())) {
                    if (basketRecordDTO.getCount() > productDTO.getAmount()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
