package de.ait_tr.repositories;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mappers.ProductMapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.Product;
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
    private ProductMapper productMapper;


    @BeforeEach
    void setUp() {
        this.productRepositoryImpl = new ProductRepositoryImpl("Products1.csv");
    }


    @Test
    void findAllGetsAllProductDTOs() {
        List<ProductDTO> actual = productRepositoryImpl.findAll();
        List<ProductDTO> expected = new ArrayList<>(List.of(
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
                new ProductDTO("TAB0005", "Lenovo Tab M10 Plus", "TAB", 249.00, 12, "Family-friendly tablet with dual speakers and a kid's mode."),
                new ProductDTO("HLT0001", "Fitbit Charge 4", "HLT", 129.00, 20, "Advanced fitness tracker with built-in GPS and heart rate monitoring."),
                new ProductDTO("HLT0002", "NordicTrack T Series Treadmill", "HLT", 999.00, 20, "High-quality treadmill for home workouts with adjustable incline."),
                new ProductDTO("HLT0003", "Bowflex Adjustable Dumbbells", "HLT", 399.00, 20, "Space-saving dumbbells with adjustable weight for versatile strength training."),
                new ProductDTO("HLT0004", "Yoga Mat", "HLT", 29.00, 20, "Comfortable and durable mat for yoga and exercise routines."),
                new ProductDTO("HLT0005", "Resistance Bands Set", "HLT", 19.00, 20, "Versatile set of resistance bands for strength and mobility workouts."),
                new ProductDTO("ACS0001", "Wireless Earbuds", "ACS", 99.00, 25, "True wireless earbuds with noise cancellation and long battery life."),
                new ProductDTO("ACS0002", "Phone Stand", "ACS", 25.00, 25, "Foldable and portable stand for holding smartphones and tablets."),
                new ProductDTO("ACS0003", "Portable Charger", "ACS", 39.00, 25, "Compact and high-capacity power bank for charging devices on the go."),
                new ProductDTO("ACS0004", "Bluetooth Speaker", "ACS", 59.00, 25, "Portable speaker with premium sound quality and wireless connectivity."),
                new ProductDTO("ACS0005", "Car Phone Mount", "ACS", 19.00, 25, "Sturdy and adjustable mount for securely holding smartphones in the car."),
                new ProductDTO("WCS0001", "Apple Watch Series 6", "WCS", 399.00, 30, "Advanced smartwatch with fitness tracking, ECG, and always-on display."),
                new ProductDTO("WCS0002", "Samsung Galaxy Watch Active2", "WCS", 249.00, 30, "Stylish smartwatch with health monitoring and built-in GPS."),
                new ProductDTO("WCS0003", "Fitbit Versa 3", "WCS", 229.00, 30, "Versatile smartwatch with built-in GPS and voice assistant."),
                new ProductDTO("WCS0004", "Garmin Fenix 6", "WCS", 599.00, 30, "Premium multisport GPS watch with rugged construction and advanced features."),
                new ProductDTO("WCS0005", "Fossil Gen 5 Carlyle", "WCS", 279.00, 30, "Classic and elegant smartwatch with Wear OS by Google."),
                new ProductDTO("BAG0001", "Leather Messenger Bag", "BAG", 149.00, 35, "Stylish and spacious messenger bag made from genuine leather."),
                new ProductDTO("BAG0002", "Backpack", "BAG", 79.00, 35, "Durable and versatile backpack with multiple compartments for organization."),
                new ProductDTO("BAG0003", "Tote Bag", "BAG", 39.00, 35, "Fashionable and functional tote bag for everyday use or shopping trips."),
                new ProductDTO("BAG0004", "Travel Duffel Bag", "BAG", 99.00, 35, "Roomy duffel bag with multiple pockets and a sturdy construction for travel."),
                new ProductDTO("BAG0005", "Laptop Sleeve", "BAG", 29.00, 35, "Protective sleeve for laptops or tablets with a slim and lightweight design."),
                new ProductDTO("GLS0001", "Polarized Sunglasses", "GLS", 79.00, 40, "Classic and stylish sunglasses with UV protection and glare reduction."),
                new ProductDTO("GLS0002", "Blue Light Blocking Glasses", "GLS", 49.00, 40, "Eyewear designed to filter out harmful blue light from digital screens."),
                new ProductDTO("GLS0003", "Sport Sunglasses", "GLS", 99.00, 40, "Durable and lightweight sunglasses for sports and outdoor activities."),
                new ProductDTO("GLS0004", "Reading Glasses", "GLS", 19.00, 40, "Optical-quality reading glasses available in various diopter strengths."),
                new ProductDTO("GLS0005", "Fashionable Eyeglasses", "GLS", 69.00, 40, "Trendy eyeglass frames suitable for prescription lenses or fashion wear."),
                new ProductDTO("BLT0001", "Leather Belt", "BLT", 49.00, 45, "Genuine leather belt with a classic design and adjustable fit."),
                new ProductDTO("BLT0002", "Canvas Belt", "BLT", 29.00, 45, "Casual and versatile belt made from durable canvas material."),
                new ProductDTO("BLT0003", "Woven Belt", "BLT", 39.00, 45, "Stylish belt with a woven pattern, ideal for adding a touch of flair to outfits."),
                new ProductDTO("BLT0004", "Reversible Belt", "BLT", 59.00, 45, "Two-in-one belt with a reversible buckle, offering versatility and convenience."),
                new ProductDTO("BLT0005", "Elastic Belt", "BLT", 19.00, 45, "Comfortable and stretchable belt for a secure and flexible fit.")
        ));
        assertEquals(expected, actual);
    }


    @DisplayName("Tests for method findByCategory")
    @Test
    void findByCategoryFindsProduct() {

        List<ProductDTO> actual = productRepositoryImpl.findByCategory(Category.SMARTPHONES);
        List<ProductDTO> expected =
                new ArrayList<>(List.of(
                        new ProductDTO("SMF0001", "iPhone 12", "SMF", 799.00, 8, "Powerful and stylish smartphone with advanced features."),
                        new ProductDTO("SMF0002", "Samsung Galaxy S21", "SMF", 899.00, 8, "High-performance smartphone with a stunning display."),
                        new ProductDTO("SMF0003", "Google Pixel 5", "SMF", 699.00, 8, "Flagship smartphone with a top-notch camera system."),
                        new ProductDTO("SMF0004", "iPhone SE", "SMF", 399.00, 8, "Compact and affordable iPhone with impressive performance."),
                        new ProductDTO("SMF0005", "OnePlus 9 Pro", "SMF", 999.00, 8, "Premium Android smartphone with a smooth user experience.")
                ));
        assertEquals(expected, actual);
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