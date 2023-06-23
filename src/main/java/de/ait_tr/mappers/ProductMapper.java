package de.ait_tr.mappers;

import de.ait_tr.models.Category;
import de.ait_tr.models.Product;
import de.ait_tr.validators.ProductValidator;

import java.util.Arrays;
public class ProductMapper {
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    private static final String DELIMITER = ";";
    ProductMapper() {

    }
    public static Product toProduct(String line) {
        try {
            String[] parsed = line.split(DELIMITER);
            String id = parsed[0];
            String title = parsed[1];
            String categoryAbbreviation = parsed[2];
            double basicPrice = Double.parseDouble(parsed[3]);
            int markup = Integer.parseInt(parsed[4]);
            int amount = Integer.parseInt(parsed[5]);
            String description = parsed[6];
            Category category =
                    Arrays.stream(Category.values())
                            .filter(c -> c.getAbbreviation().equals(categoryAbbreviation))
                            .findFirst()
                            .orElse(null);

            Product product = new Product(id, title, category, basicPrice, markup, amount, description);

            if (ProductValidator.validate(product)) {
                return product;
            } else return null;

        } catch (RuntimeException e) {
            throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
        }
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
