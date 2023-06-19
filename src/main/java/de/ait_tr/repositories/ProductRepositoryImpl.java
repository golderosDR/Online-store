package de.ait_tr.repositories;

public class ProductRepositoryImpl implements ProductRepository {
    private final String fileName;
   public ProductRepositoryImpl(String fileName) {
       this.fileName = fileName;
   }
}
