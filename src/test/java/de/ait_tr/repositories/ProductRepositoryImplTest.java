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
    @DisplayName("Tests for method findAll")
    class findAllTests {
        @Test
            //find_IN_Iphon_OUT_List_ProductDTO_success
        void findAll_IN_VOID_OUT_List_ProductDTO_success() {

            List<ProductDTO> actual = productRepositoryImpl.findAll();
            assertEquals(findAllExpected, actual);
        }
    }

    @Nested
    @DisplayName("Tests for method findByCategory")
    class findByCategoryTests {
        @Test
        void findByCategory_SMARTPHONES_OUT_List_ProductDTO_success() {

            List<ProductDTO> actual = productRepositoryImpl.findByCategory(Category.SMARTPHONES);
            assertEquals(findByCategoryExpected, actual);
        }
    }

    @Nested
    @DisplayName("Tests for method findByID")
    class findByIdTests {
        @Test
        void findById_SMF0005_OUT_List_ProductDTO_success() {
            String stringSearchInfo = "SMF0005";
            ProductDTO actual = productRepositoryImpl.findById(stringSearchInfo);
            assertEquals(findByIdExpected, actual);
        }

        @Test
        void findById_999999_OUT_Empty_List_success() {
            String stringSearchInfo = "999999";
            ProductDTO actual = productRepositoryImpl.findById(stringSearchInfo);
            ProductDTO expected = null;
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Tests for method find")
    class findTests {

        @Test
        void find_iphon_OUT_List_ProductDTO_success() {
            String stringSearchInfo = "iphon";
            List<ProductDTO> actual = productRepositoryImpl.find(stringSearchInfo);
            assertEquals(findExpected, actual);
        }

        @Test
        void find_555555_OUT_Empty_List_success() {
            String stringSearchInfo = "555555";
            List<ProductDTO> actual = productRepositoryImpl.find(stringSearchInfo);
            List<ProductDTO> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }
    }

    private List<ProductDTO> findAllExpected = new ArrayList<>(List.of(
            new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features."),
            new ProductDTO("SMF0002", "Samsung Galaxy S21", "SMF", 899.00, 8, "High-performance smartphone with a stunning display."),
            new ProductDTO("SMF0003", "Google Pixel 5", "SMF", 699.00, 8, "Flagship smartphone with a top-notch camera system."),
            new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance."),
            new ProductDTO("SMF0005", "OnePlus 9 Pro", "SMF", 999.00, 8, "Premium Android smartphone with a smooth user experience."),
            new ProductDTO("TVS0001", "LG OLED CX Series", "TVS", 1499.00, 10, "High-quality OLED TVS with vivid colors and deep blacks."),
            new ProductDTO("TVS0002", "Sony X900H", "TVS", 1299.00, 10, "4K HDR TVS with impressive picture quality and smart features."),
            new ProductDTO("TVS0003", "Samsung Q90T", "TVS", 1599.00, 10, "QLED TVS with exceptional brightness and immersive viewing experience."),
            new ProductDTO("TVS0004", "TCL 6-Series", "TVS", 799.00, 10, "Affordable 4K TVS with excellent picture performance and Roku platform."),
            new ProductDTO("TVS0005", "Vizio M-Series", "TVS", 699.00, 10, "Smart TVS with a modern design and vibrant visuals."),
            new ProductDTO("NTB0001", "Dell XPS 15", "NTB", 1699.00, 15, "Powerful and sleek laptop for productivity and multimedia tasks."),
            new ProductDTO("NTB0002", "Apple MacBook Pro", "NTB", 1999.00, 15, "High-performance MacBook with a stunning Retina display."),
            new ProductDTO("NTB0003", "Lenovo ThinkPad X1 Carbon", "NTB", 1399.00, 15, "Business laptop with durability and security features."),
            new ProductDTO("NTB0004", "HP Spectre x360", "NTB", 1299.00, 15, "Convertible laptop with a touch display and long battery life."),
            new ProductDTO("NTB0005", "Asus ZenBook 14", "NTB", 999.00, 15, "Ultra-slim laptop with a powerful processor and premium design."),
            new ProductDTO("TAB0001", "iPad Air", "TAB", 599.00, 12, "Versatile tablet with a large display and Apple Pencil support."),
            new ProductDTO("TAB0002", "Samsung Galaxy Tab S7", "TAB", 699.00, 12, "Android tablet with a stunning AMOLED display and S Pen."),
            new ProductDTO("TAB0003", "Microsoft Surface Pro 7", "TAB", 899.00, 12, "2-in-1 tablet with a detachable keyboard and Windows 10."),
            new ProductDTO("TAB0004", "Amazon Fire HD 10", "TAB", 149.00, 12, "Affordable tablet with a vibrant display and ACSess to Amazon services."),
            new ProductDTO("TAB0005", "Lenovo Tab M10 Plus", "TAB", 249.00, 12, "Family-friendly tablet with dual speakers and a kid's mode.")
    ));
    private List<ProductDTO> findByCategoryExpected =
            new ArrayList<>(List.of(
                    new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features."),
                    new ProductDTO("SMF0002", "Samsung Galaxy S21", "SMF", 899.00, 8, "High-performance smartphone with a stunning display."),
                    new ProductDTO("SMF0003", "Google Pixel 5", "SMF", 699.00, 8, "Flagship smartphone with a top-notch camera system."),
                    new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance."),
                    new ProductDTO("SMF0005", "OnePlus 9 Pro", "SMF", 999.00, 8, "Premium Android smartphone with a smooth user experience.")
            ));

    private ProductDTO findByIdExpected = new ProductDTO(
            "SMF0005", "OnePlus 9 Pro", "SMF", 999.00, 8, "Premium Android smartphone with a smooth user experience.");

    private List<ProductDTO> findExpected = new ArrayList<>(List.of(
            new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features"),
            new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance.")
    ));
}