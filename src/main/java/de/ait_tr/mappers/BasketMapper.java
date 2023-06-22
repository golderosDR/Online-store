package de.ait_tr.mappers;

import de.ait_tr.dtos.InBasketDTO;

import java.util.List;

public class BasketMapper {
    private static final int placeForX = 3;
    private BasketMapper() {
    }

    public static String toLines(List<InBasketDTO> inBasketDTOList) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        if (inBasketDTOList.isEmpty()) {
            return "Корзина пуста";
        } else {
            double totalPrice = 0;
            int spaceCount = DTOMapper.toLineInBasket(inBasketDTOList.get(0)).length() + placeForX;

            for (InBasketDTO inBasketDTO: inBasketDTOList) {
                totalPrice += inBasketDTO.getProductDTO().getPrice() * inBasketDTO.getCount();
                output.append(counter++)
                        .append(". ")
                        .append(DTOMapper.toLineInBasket(inBasketDTO))
                        .append(System.lineSeparator());
            }
            output.append(System.lineSeparator())
                    .append("Total price")
                    .append(" ".repeat(spaceCount - ("Total price").length() - String.format("%.2f", totalPrice).length()))
                    .append(String.format("%.2f", totalPrice));
            return output.toString();
        }
    }
}
