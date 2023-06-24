package de.ait_tr.exceptions;

public class IllegalOrderRecordArgumentException extends RuntimeException{
    public static String WRONG_ORDER_DATE_TIME_FORMAT_MESSAGE = "Недопустимый формат даты заказа.";
    public static String WRONG_ORDER_ID_FORMAT_MESSAGE = "Недопустимый формат Id заказа.";
    public static String WRONG_ORDER_NUMBER_VALUE_MESSAGE = "Недопустимое значение номера заказа.";
    public static String WRONG_PRODUCT_ID_FORMAT_MESSAGE = "Недопустимый формат ID продукта.";
    public static String WRONG_PRODUCT_COUNT_VALUE_MESSAGE = "Недопустимое значение количества товара.";


    public IllegalOrderRecordArgumentException(String message) {
        super(message);
    }
}
