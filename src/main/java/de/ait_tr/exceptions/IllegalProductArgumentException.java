package de.ait_tr.exceptions;

public class IllegalProductArgumentException extends RuntimeException {
    public static String WRONG_PRODUCT_TITLE_SIZE_MESSAGE = "Недопустимое значение длинны названия продукта.";
    public static String WRONG_PRODUCT_ID_FORMAT_MESSAGE = "Недопустимый формат ID продукта.";
    public static String WRONG_PRODUCT_CATEGORY_FORMAT_MESSAGE = "Недопустимый формат категории продукта.";
    public static String WRONG_PRODUCT_PRICE_VALUE_MESSAGE = "Недопустимое значение цены продукта.";
    public static String WRONG_PRODUCT_MARKUP_VALUE_MESSAGE = "Недопустимое значение наценки продукта.";
    public static String WRONG_PRODUCT_AMOUNT_VALUE_MESSAGE = "Недопустимое значение количества продукта.";
    public static String WRONG_PRODUCT_DESCRIPTION_SIZE_MESSAGE = "Недопустимое значение длинны описания продукта.";


    public IllegalProductArgumentException(String message) {
        super(message);
    }

}
