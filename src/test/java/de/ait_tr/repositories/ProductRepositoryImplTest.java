package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ProductRepositoryImplTest {
    private ProductRepositoryImpl productRepositoryImpl;


    @BeforeEach
    void setUp() {
        this.productRepositoryImpl = new ProductRepositoryImpl("Products1.csv");
    }
@Nested
    @DisplayName("Tests for method find")
class  findTest {

    @Test
    void findFindsProduct() {
        //arrange
        String stringSearchInfo = "iphon";
        // action
        List<ProductDTO> actual = productRepositoryImpl.find(stringSearchInfo);
        // assert
        List<ProductDTO> expected = new ArrayList<>(List.of(
                new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features"),
                new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance.")
        ));
        assertEquals(expected, actual);
    }
    @Test
    void findFindsNotProduct() {
        //arrange
        // action
        // assert

    }


    }


}