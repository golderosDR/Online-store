package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.mapper.DTOMapper;
import de.ait_tr.mapper.ProductMapper;
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

    private List<ProductDTO> filterByCategory(Category category) {
        return getAll()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .map(DTOMapper::toProductDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> findAllSmartphones() {
        return filterByCategory(Category.SMARTPHONES);
    }

    @Override
    public List<ProductDTO> findAllWatches() {
        return filterByCategory(Category.WATCHES);
    }

    @Override
    public List<ProductDTO> findAllAccessories() {
        return filterByCategory(Category.ACCESSORIES);
    }

    @Override
    public List<ProductDTO> findAllNotebooks() {
        return filterByCategory(Category.NOTEBOOKS);
    }

    @Override
    public List<ProductDTO> findAllTablets() {
        return filterByCategory(Category.TABLETS);
    }

    @Override
    public List<ProductDTO> findAllTVs() {
        return filterByCategory(Category.TVS);
    }

    @Override
    public List<ProductDTO> findAllBags() {
        return filterByCategory(Category.BAGS);
    }

    @Override
    public List<ProductDTO> findAllGlasses() {
        return filterByCategory(Category.GLASSES);
    }

    @Override
    public List<ProductDTO> findAllBelts() {
        return filterByCategory(Category.BELTS);
    }

    @Override
    public List<ProductDTO> findAllHeath() {
        return filterByCategory(Category.HEALTH);
    }

    /**
     * change amount in products for each product from basket
     * @param productInBasketDTOList
     */
    @Override
    public void buy(List<ProductInBasketDTO> productInBasketDTOList) {
        List<Product> chanchedProductList = getAll();
        for (ProductInBasketDTO productInBasketDTO : productInBasketDTOList
        ) {
            for (Product product : chanchedProductList
            ) {
                if (product.getId().equals(productInBasketDTO.id())) {  //совпадение id
                    product.setAmount(product.getAmount() - productInBasketDTO.count()); //установили количество
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
     * @return List<ProductDTO>
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
                .toList().get(0);
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
