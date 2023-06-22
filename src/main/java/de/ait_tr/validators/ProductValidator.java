package de.ait_tr.validators;

import de.ait_tr.exceptions.IllegalProductArgumentException;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;

import java.util.Arrays;

import static de.ait_tr.exceptions.IllegalProductArgumentException.*;

public class ProductValidator {
    private ProductValidator() {
    }
    public static boolean validate(Product product) {
        if (product.getTitle().length() < 1) {
            throw new IllegalProductArgumentException(WRONG_TITLE_SIZE_MESSAGE);
        }
        if (!product.getId().matches("[A-Z]{3}[0-9]+")) {
            throw new IllegalProductArgumentException(WRONG_ID_FORMAT_MESSAGE);
        }
        if (Arrays.stream(Category.values())
                .map(Category::getDescription)
                .noneMatch(description -> description.equals(product.getCategory().getDescription()))
        ) {
            throw new IllegalProductArgumentException(WRONG_CATEGORY_FORMAT_MESSAGE);
        }
        if (product.getBasicPrice() <= 0) {
            throw new IllegalProductArgumentException(WRONG_PRICE_VALUE_MESSAGE);
        }
        if (product.getMarkup() < 0) {
            throw new IllegalProductArgumentException(WRONG_MARKUP_VALUE_MESSAGE);
        }
        if (product.getAmount() < 0) {
            throw new IllegalProductArgumentException(WRONG_AMOUNT_VALUE_MESSAGE);
        }
        if (product.getDescription().length() < 1) {
            throw new IllegalProductArgumentException(WRONG_DESCRIPTION_SIZE_MESSAGE);
        }

        return true;
    }
}
