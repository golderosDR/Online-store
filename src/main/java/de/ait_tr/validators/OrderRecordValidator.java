package de.ait_tr.validators;

import de.ait_tr.exceptions.IllegalOrderRecordArgumentException;
import de.ait_tr.models.OrderRecord;
import static de.ait_tr.exceptions.IllegalOrderRecordArgumentException.*;

public class OrderRecordValidator {
    private OrderRecordValidator(){}
    public static boolean validate(OrderRecord orderRecord) {
        StringBuilder errors = new StringBuilder();

        if (!orderRecord.dateTime().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            errors.append(WRONG_ORDER_DATE_TIME_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (!orderRecord.orderId().matches("ORDER\\d+")) {
            errors.append(WRONG_ORDER_ID_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (orderRecord.orderNumber() < 1) {
            errors.append(WRONG_ORDER_NUMBER_VALUE_MESSAGE)
                    .append(" ");
        }
        if (!orderRecord.productId().matches("[A-Z]{3}[0-9]+")) {
            errors.append(WRONG_PRODUCT_ID_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (orderRecord.count() < 1) {
            errors.append(WRONG_PRODUCT_COUNT_VALUE_MESSAGE)
                    .append(" ");
        }
        if (!errors.isEmpty()) {
            throw new IllegalOrderRecordArgumentException(errors.toString());
        }
        return true;
    }
}
