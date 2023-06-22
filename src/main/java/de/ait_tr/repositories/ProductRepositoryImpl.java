package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.InBasketDTO;
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
                    .map(DTOMapper::toProduct)
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
     * @param inBasketDTOList
     */
    @Override
    public void buy(List<InBasketDTO> inBasketDTOList) {
        List<Product> chanchedProductList = getAll();
        for (InBasketDTO inBasketDTO : inBasketDTOList) {
            for (Product product : chanchedProductList) {
                if (product.getId().equals(inBasketDTO.getId())) {  //совпадение id
                    product.setAmount(product.getAmount() - inBasketDTO.getCount()); //установили количество
                }
            }
        }
        save(chanchedProductList);
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

    @Override
    public void save(List<Product> productList) {
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
}
