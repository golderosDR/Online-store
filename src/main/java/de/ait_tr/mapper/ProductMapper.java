package de.ait_tr.mapper;

import de.ait_tr.models.Product;

import java.util.List;

public class ProductMapper {
    ProductMapper() {

    }
   public static String toLine(Product product) {
        StringBuilder output = new StringBuilder();
        output.append(product.getId())
                .append(";")
                .append(product.getTitle())
                .append(";")
                .append(product.getCategory())
                .append(";")
                .append(product.getBasicPrice())
                .append(";")
                .append(product.getMarkup())
                .append(";")
                .append(product.getAmount())
                .append(";")
                .append(product.getDescription());
        return output.toString();
   }
}
