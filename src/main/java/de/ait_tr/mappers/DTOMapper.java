package de.ait_tr.mappers;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.models.Product;

import java.util.List;


public class DTOMapper {

    private DTOMapper() {
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
        return productDTO.getTitle() +
                ", category " +
                productDTO.getCategory() +
                ", productPrice " +
                String.format("%.2f", productDTO.getPrice()) +
                (productDTO.getAmount() == 0 ? ".  <- Нет в наличии!": ".");
    }

    public static String toLineWithDescription(ProductDTO productDTO) {
        return productDTO.getTitle() + "," +
                System.lineSeparator() +
                (productDTO.getAmount() == 0 ? "Нет в наличии!" + System.lineSeparator(): "") +
                "category " +
                productDTO.getCategory() +
                "," +
                System.lineSeparator() +
                "productPrice " +
                String.format("%.2f", productDTO.getPrice()) +
                "," +
                System.lineSeparator() +
                productDTO.getDescription();
    }

    public static String toNumeratedProductDTOLines(List<ProductDTO> productDTOList) {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (ProductDTO productDTO : productDTOList) {
            output.append(counter++)
                    .append(counter < 11 ? ".  ":". ")
                    .append(toLine(productDTO))
                    .append(System.lineSeparator());
        }
        return output.toString();
    }

    public static BasketRecordDTO toBasketRecordDTO(ProductDTO productDTO, int count) {
        return new BasketRecordDTO(toBasketRecordDTO(productDTO), count);
    }
    public static ProductInBasketDTO toBasketRecordDTO(ProductDTO productDTO) {
        return new ProductInBasketDTO(productDTO.getId(), productDTO.getTitle(), productDTO.getPrice());
    }


}
