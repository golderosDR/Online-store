package de.ait_tr.validators;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.models.ProductBasket;

import java.util.ArrayList;
import java.util.List;


public class BasketValidator {
    private BasketValidator() {
    }

    public static List<String> validate(ProductBasket productBasket, List<ProductDTO> productDTOList) {
        List<BasketRecordDTO> basketRecordDTOList = productBasket.getProductsInBasket();
        List<String> errors = new ArrayList<>();
        for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {

            for (ProductDTO productDTO : productDTOList) {
                if (basketRecordDTO.getId().equals(productDTO.id())) {
                    if (basketRecordDTO.getCount() > productDTO.amount()) {
                       errors.add(String.format("Недостаточно товара '%s' в наличии. Запрошено %d, в наличии %d!",
                               productDTO.title(),
                               basketRecordDTO.getCount(),
                               productDTO.amount()
                               )
                       );
                    }
                }
            }
        }
        return errors;
    }
}
