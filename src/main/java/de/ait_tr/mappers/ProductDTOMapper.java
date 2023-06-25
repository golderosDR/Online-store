package de.ait_tr.mappers;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.models.Product;

import java.util.List;


public class ProductDTOMapper {

    private ProductDTOMapper() {
    }

    public static ProductDTO toProductDTO(Product product) {
        double price = product.getBasicPrice() * (100 + product.getMarkup()) / 100.0;
        String category = product.getCategory().getDescription();
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                category,
                price,
                product.getAmount(),
                product.getDescription()
        );
    }

    public static String toLine(ProductDTO productDTO) {
        return productDTO.title() +
                ", категория " +
                productDTO.category() +
                ", цена - " +
                String.format("%.2f", productDTO.price()) +
                (productDTO.amount() == 0 ?
                        ".  <- Нет в наличии!" :
                        ".");
    }

    public static String toLineWithDescription(ProductDTO productDTO) {
        return productDTO.title() + "," +
                System.lineSeparator() +
                "категория " +
                productDTO.category() +
                "," +
                System.lineSeparator() +
                "цена -  " +
                String.format("%.2f", productDTO.price()) +
                "," +
                System.lineSeparator() +
                (productDTO.amount() == 0 ?
                        "Нет в наличии!" + System.lineSeparator() :
                        String.format("В наличии %d.%n", productDTO.amount())) +
                productDTO.description();
    }

    public static String toNumeratedProductDTOLines(List<ProductDTO> productDTOList) {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (ProductDTO productDTO : productDTOList) {
            output.append(counter++)
                    .append(counter < 11 ? ".  " : ". ")
                    .append(toLine(productDTO))
                    .append(System.lineSeparator());
        }
        return output.toString();
    }

    public static BasketRecordDTO toBasketRecordDTO(ProductDTO productDTO, int count) {
        return new BasketRecordDTO(toBasketRecordDTO(productDTO), count);
    }

    public static ProductInBasketDTO toBasketRecordDTO(ProductDTO productDTO) {
        return new ProductInBasketDTO(productDTO.id(), productDTO.title(), productDTO.price());
    }


}
