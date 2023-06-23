package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.mappers.DTOMapper;
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
    private final String fileName;
    private final static String FILE_NOT_FOUND_ERROR_MSG = "Файл не найден или поврежден!";

    public ProductRepositoryImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<ProductDTO> findAll() {
        return getAll()
                .stream()
                .map(DTOMapper::toProductDTO)
                .toList();
    }

    private List<Product> getAll() {
        try {
            return Files.readAllLines(new File(fileName).toPath())
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
                .map(DTOMapper::toProductDTO)
                .toList();
    }
    /**
     * change amount in products for each product from basket
     *
     * @param basketRecordDTOList
     * @return
     */
    private List<Product> getUpdatedList(List<BasketRecordDTO> basketRecordDTOList) {
        List<Product> updatedProductList = getAll();
        for (BasketRecordDTO basketRecordDTO : basketRecordDTOList) {
            for (Product product : updatedProductList) {
                if (product.getId().equals(basketRecordDTO.getId())) {  //совпадение id
                    product.setAmount(product.getAmount() - basketRecordDTO.getCount()); //установили количество
                }
            }
        }
        return updatedProductList;
    }
    @Override
    public void update(List<BasketRecordDTO> basketRecordDTOList) {
        List<Product> productList = getUpdatedList(basketRecordDTOList);
        String lines = productList
                .stream()
                .map(ProductMapper::toLine)
                .collect(Collectors.joining(System.lineSeparator()));

        try (FileWriter writer = new FileWriter(fileName);
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
                                product.getTitle().contains(searchInfo)
                                        || product.getDescription().contains(searchInfo)
                        )
                )
                .map(DTOMapper::toProductDTO)
                .toList();
    }

    /**
     * find ProductDTO  by id
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
                .map(DTOMapper::toProductDTO)
                .findFirst()
                .orElse(null);
    }
}
