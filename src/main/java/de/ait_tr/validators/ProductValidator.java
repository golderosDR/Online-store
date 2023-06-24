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
        StringBuilder errors = new StringBuilder();
        if (product.getTitle().length() < 1) {
            errors.append(WRONG_PRODUCT_TITLE_SIZE_MESSAGE)
                    .append(" ");
        }
        if (!product.getId().matches("[A-Z]{3}[0-9]+")) {
            errors.append(WRONG_PRODUCT_ID_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (Arrays.stream(Category.values())
                .map(Category::getDescription)
                .noneMatch(description -> description.equals(product.getCategory().getDescription()))
        ) {
            errors.append(WRONG_PRODUCT_CATEGORY_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (product.getBasicPrice() <= 0) {
            errors.append(WRONG_PRODUCT_PRICE_VALUE_MESSAGE)
                    .append(" ");
        }
        if (product.getMarkup() < 0) {
            errors.append(WRONG_PRODUCT_MARKUP_VALUE_MESSAGE)
                    .append(" ");
        }
        if (product.getAmount() < 0) {
            errors.append(WRONG_PRODUCT_AMOUNT_VALUE_MESSAGE)
                    .append(" ");
        }
        if (product.getDescription().length() < 1) {
            errors.append(WRONG_PRODUCT_DESCRIPTION_SIZE_MESSAGE)
                    .append(" ");
        }
        if (!errors.isEmpty()) {
            throw new IllegalProductArgumentException(errors.toString());
        }

        return true;
    }
}
