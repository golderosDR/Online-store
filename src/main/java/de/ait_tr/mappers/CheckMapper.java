package de.ait_tr.mappers;

import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.models.Order;

public class CheckMapper {
    private static final String CHECK_PATTERN = """
                      “Horns and hooves” GMBH           |
                                                        |
                          Check number: %s|
            --------------------------------------------|
            %s
            --------------------------------------------|
            Total:%s%.2f|
                                                        |
                    Thank you for your purchase!        |



            """;

    private CheckMapper() {
    }

    public static String toLines(Order order) {
        StringBuilder output = new StringBuilder();
        double totalPrice = 0;
        for (BasketRecordDTO basketRecordDTO : order.getProductBasket()) {
            totalPrice += basketRecordDTO.getPrice() * basketRecordDTO.getCount();

            output.append(BasketMapper.toLine(basketRecordDTO))
                    .append("|")
                    .append(System.lineSeparator());
        }
        output.deleteCharAt(output.length()-1);
        return String.format(CHECK_PATTERN,
                order.getOrderNumber() + " ".repeat(16 - String.valueOf(order.getOrderNumber()).length()),
                output,
                " ".repeat(38 - String.format("%.2f",totalPrice).length()),
                totalPrice
                );
    }
}
