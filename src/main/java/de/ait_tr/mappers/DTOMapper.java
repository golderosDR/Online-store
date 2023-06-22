package de.ait_tr.mappers;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.InBasketDTO;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;
import de.ait_tr.validators.ProductValidator;

import java.util.Arrays;
import java.util.List;


public class DTOMapper {
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    private static final String DELIMITER = ";";
    private static final int POINT_NAME_MAX_LENGTH = 50;
    private DTOMapper(){
    }
    public static  Product toProduct(String line) {
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
                    .toList()
                    .get(0);

            Product product = new Product(id, title, category, basicPrice, markup, amount, description);

            if (ProductValidator.validate(product)) {
                return product;
            } else return null;

        } catch (RuntimeException e) {
            throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE) ;
        }
    }
    public static ProductDTO toProductDTO(Product product) {
      double price = product.getBasicPrice()*(100+product.getMarkup())/100.0;
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
                ", price " +
                String.format("%.2f", productDTO.getPrice()) +
                ".";
    }
    public static String toLineWithDescription(ProductDTO productDTO) {
        return productDTO.getTitle() + "," +
                System.lineSeparator() +
                "category " +
                productDTO.getCategory() +
                "," +
                System.lineSeparator() +
                "price " +
                String.format("%.2f", productDTO.getPrice()) +
                "," +
                System.lineSeparator() +
                productDTO.getDescription();
    }
    public static String toNumeratedProductDTOLines(List<ProductDTO> productDTOList) {
        int counter = 1;
        StringBuilder output = new StringBuilder();
        for (ProductDTO productDTO: productDTOList) {
            output.append(counter++)
                    .append(". ")
                    .append(toLine(productDTO))
                    .append(System.lineSeparator());
        }
        return output.toString();
    }
    public static InBasketDTO toProductInBasketDTO(ProductDTO productDTO, int count) {
        return  new InBasketDTO(productDTO.getId(), count);
    }
    public static String toLine(String title,double price, int count) {
        StringBuilder output = new StringBuilder();
        int spacesCount = POINT_NAME_MAX_LENGTH - title.length() - String.format("%.2f",price).length()- String.valueOf(count).length()-1;
        output.append(title)
                .append(" ".repeat(spacesCount))
                .append(count)
                .append(" X ")
                .append(String.format("%.2f",price));
        return output.toString();
    }
}
