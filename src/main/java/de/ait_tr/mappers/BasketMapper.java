package de.ait_tr.mappers;

import de.ait_tr.dtos.BasketRecordDTO;

import java.util.List;

public class BasketMapper {
    private static final int placeForX = 3;
    private static final int POINT_NAME_MAX_LENGTH = 44;
    private BasketMapper() {
    }

    public static String toLines(List<BasketRecordDTO> basketRecordDTOList) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        if (basketRecordDTOList.isEmpty()) {
            return "Корзина пуста";
        } else {
            double totalPrice = 0;
            int spaceCount = toLine(basketRecordDTOList.get(0)).length() + placeForX;

            for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {
                totalPrice += basketRecordDTO.getPrice() * basketRecordDTO.getCount();
                output.append(counter++)
                        .append(". ")
                        .append(toLine(basketRecordDTO))
                        .append(System.lineSeparator());
            }
            output.append(System.lineSeparator())
                    .append("Total price")
                    .append(" ".repeat(spaceCount - ("Total price").length() - String.format("%.2f", totalPrice).length()))
                    .append(String.format("%.2f", totalPrice));
            return output.toString();
        }
    }
    public static String toLine(BasketRecordDTO basketRecordDTO) {
        StringBuilder output = new StringBuilder();
        String title = basketRecordDTO.getTitle();
        String price = String.format("%.2f", basketRecordDTO.getPrice());
        String count = String.valueOf(basketRecordDTO.getCount());
        int spacesCount = POINT_NAME_MAX_LENGTH
                - title.length()
                - price.length()
                - count.length() - 3;
        output.append(title)
                .append(" ".repeat(spacesCount))
                .append(count)
                .append(" X ")
                .append(price);
        return output.toString();
    }
}
