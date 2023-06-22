package de.ait_tr.mappers;

import de.ait_tr.models.Product;

public class ProductMapper {
    ProductMapper() {

    }
   public static String toLine(Product product) {
       return product.getId() +
               ";" +
               product.getTitle() +
               ";" +
               product.getCategory() +
               ";" +
               product.getBasicPrice() +
               ";" +
               product.getMarkup() +
               ";" +
               product.getAmount() +
               ";" +
               product.getDescription();
   }
}
