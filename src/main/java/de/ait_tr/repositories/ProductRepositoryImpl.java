package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.mapper.Mapper;
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

    @Override
    public List<ProductDTO> findAllElectronics() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllWatches() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllAccessories() {
        return null;
    }

    @Override
    public List<ProductDTO> findAllHeath() {
        return null;
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
