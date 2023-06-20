package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.mapper.Mapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


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
                .map(Mapper::toProductDTO)
                .toList();
    }


    private List<Product> getAll() {
        try {
            return Files.readAllLines(new File(fileName).toPath())
                    .stream()
                    .map(Mapper::toProduct)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }
private List<ProductDTO> filterByCategory(Category category){
        return getAll()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .map(Mapper::toProductDTO)
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

    @Override
    public boolean buy(ProductInBasketDTO productInBasketDTO) {
        return false;
    }

    @Override
    public ProductDTO find(String titlePart) {
        return null;
    }
}
