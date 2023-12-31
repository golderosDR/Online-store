package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.mappers.ProductDTOMapper;
import de.ait_tr.mappers.ProductMapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;


public class ProductRepositoryImpl implements ProductRepository {
    private final String filePath;
    private final static String FILE_NOT_FOUND_ERROR_MSG = "Файл не найден или поврежден!";

    public ProductRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<ProductDTO> findAll() {
        return getAll()
                .stream()
                .map(ProductDTOMapper::toProductDTO)
                .toList();
    }

    private List<Product> getAll() {
        try {
            return Files.readAllLines(new File(filePath).toPath())
                    .stream()
                    .map(ProductMapper::toProduct)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    @Override
    public List<ProductDTO> findByCategory(Category category) {
        return getAll()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .map(ProductDTOMapper::toProductDTO)
                .toList();
    }
    /**
     * change amount in products for each product from basket
     *
     * @param basketRecordDTOList
     * @return
     */
    private List<Product> getUpdatedList(List<BasketRecordDTO> basketRecordDTOList) { //!!! не нужно тест
        List<Product> updatedProductList = getAll();
        for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {
            for (Product product : updatedProductList) {
                if (product.getId().equals(basketRecordDTO.getId())) {  //совпадение productId
                    product.setAmount(product.getAmount() - basketRecordDTO.getCount()); //установили количество
                }
            }
        }
        return updatedProductList;
    }
    @Override
    public void update(List<BasketRecordDTO> basketRecordDTOList) {  //!!!не нужно тест
        List<Product> productList = getUpdatedList(basketRecordDTOList);
        String lines = productList
                .stream()
                .map(ProductMapper::toLine)
                .collect(Collectors.joining(System.lineSeparator()));

        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter buffWriter = new BufferedWriter(writer)) {
            buffWriter.write(lines);
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    /**
     * find ProductDTO by String
     *
     * @param searchInfo
     * @return List<ProductDTO>
     */
    @Override
    public List<ProductDTO> find(String searchInfo) {
        return getAll()
                .stream()
                .filter(product -> (
                                product.getTitle().toLowerCase().contains(searchInfo.toLowerCase())
                                        || product.getDescription().contains(searchInfo)
                        )
                )
                .map(ProductDTOMapper::toProductDTO)
                .toList();
    }

    /**
     * find ProductDTO  by productId
     *
     * @param id
     * @return ProductDTO
     */
    @Override
    public ProductDTO findById(String id) {
        return getAll()
                .stream()
                .filter(product -> (
                                product.getId().equals(id)
                        )
                )
                .map(ProductDTOMapper::toProductDTO)
                .findFirst()
                .orElse(null);
    }
}
