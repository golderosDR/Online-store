package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.models.Category;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class ProductRepositoryImplTest {
    private ProductRepositoryImpl productRepositoryImpl;


    @BeforeEach
    void setUp() {
        this.productRepositoryImpl = new ProductRepositoryImpl("Products1.csv");
    }

    @Nested
    @DisplayName("Tests for method findByCategory")
    class findByCategory {
        @Test
        void findByCategoryFindsProduct() {
            List<ProductDTO> productDTOArrayList = productRepositoryImpl.findAll();
            Random random = new Random();
            ProductDTO anyProductDTOFromFile = productDTOArrayList.get(random.nextInt(productDTOArrayList.size()));
            String stringSearchInfo = anyProductDTOFromFile.getCategory();//нужно получить категорию в сокращенном виде...
           if (stringSearchInfo.equals("bags")) stringSearchInfo="BAG";
           if (stringSearchInfo.equals("glasses"))stringSearchInfo="GLS";
           if (stringSearchInfo.equals("belts")) stringSearchInfo="BLT";
           if (stringSearchInfo.equals("health")) stringSearchInfo="HLT";
           if (stringSearchInfo.equals("notebooks")) stringSearchInfo="NTB";
           if (stringSearchInfo.equals("smartphones")) stringSearchInfo="SMF";
           if (stringSearchInfo.equals("tablets")) stringSearchInfo="TAB";
           if (stringSearchInfo.equals("watches")) stringSearchInfo="WCS";
           if (stringSearchInfo.equals("accessories")) stringSearchInfo="ACS";

            ProductDTO actual = productRepositoryImpl.findByCategory();
            ProductDTO expected = new ProductDTO(actual.getId(), actual.getTitle(), ЗДЕСЬ ДОЛЖНА БЫТЬ КАТЕГОРИЯ !!!!stringSearchInfo, actual.getPrice(), actual.getAmount(), actual.getDescription());

            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Tests for method findByID")
    class findByIdTest {
        @Test
        void findByIdFindsProduct() {
            List<ProductDTO> productDTOArrayList = productRepositoryImpl.findAll();
            Random random = new Random();
            ProductDTO anyProductDTOFromFile = productDTOArrayList.get(random.nextInt(productDTOArrayList.size()));
            String stringSearchInfo = anyProductDTOFromFile.getId();
            ProductDTO actual = productRepositoryImpl.findById(stringSearchInfo); //ID случайного Product из файла
            ProductDTO expected = new ProductDTO(stringSearchInfo, actual.getTitle(), actual.getCategory(), actual.getPrice(), actual.getAmount(), actual.getDescription());
            assertEquals(expected, actual);
        }

        @Test
        void findByIdDontFindsProduct() {
            String stringSearchInfo = "999999999999";
            ProductDTO actual = productRepositoryImpl.findById(stringSearchInfo);
            ProductDTO expected = null;
            assertEquals(expected, actual);

        }

    }

    @Nested
    @DisplayName("Tests for method find")
    class findTest {

        @Test
        void findFindsProduct() {
            String stringSearchInfo = "iphon";
            List<ProductDTO> actual = productRepositoryImpl.find(stringSearchInfo);
            List<ProductDTO> expected = new ArrayList<>(List.of(
                    new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features"),
                    new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance.")
            ));
            assertEquals(expected, actual);
        }

        @Test
        void findDontFindsProduct() {

            String stringSearchInfo = "555555555555";
            List<ProductDTO> actual = productRepositoryImpl.find(stringSearchInfo);
            List<ProductDTO> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }


    }


}