package de.ait_tr.mappers;

import de.ait_tr.dtos.BasketRecordDTO;

import java.util.List;

public class BasketMapper {
    private static final int placeForX = 3;
    private BasketMapper() {
    }

    public static String toLines(List<BasketRecordDTO> basketRecordDTOList) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        if (basketRecordDTOList.isEmpty()) {
            return "Корзина пуста";
        } else {
            double totalPrice = 0;
            int spaceCount = DTOMapper.toLine(basketRecordDTOList.get(0)).length() + placeForX;

            for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {
                totalPrice += basketRecordDTO.getPrice() * basketRecordDTO.getCount();
                output.append(counter++)
                        .append(". ")
                        .append(DTOMapper.toLine(basketRecordDTO))
                        .append(System.lineSeparator());
            }
            output.append(System.lineSeparator())
                    .append("Total productPrice")
                    .append(" ".repeat(spaceCount - ("Total productPrice").length() - String.format("%.2f", totalPrice).length()))
                    .append(String.format("%.2f", totalPrice));
            return output.toString();
        }
    }
}
