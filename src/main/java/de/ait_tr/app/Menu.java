package de.ait_tr.app;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mapper.DTOMapper;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.services.ProductService;
import de.ait_tr.services.ProductServiceImpl;
import de.ait_tr.validators.CommandValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final ProductService productService;
    private final ProductBasket productBasket;
    private static final String BASE_PRODUCTS_REPOSITORY_PATH = "Products.csv";
    public final static String CANCEL_OPERATION = "Операция отменена.";
    public final static String WRONG_COMMAND = "Команда не распознана.";

    Menu() {
        this.productService = new ProductServiceImpl(new ProductRepositoryImpl(BASE_PRODUCTS_REPOSITORY_PATH));
        this.productBasket = new ProductBasket();
    }

    public void cancel() {
        System.out.println(CANCEL_OPERATION);
    }

    public void wrongCommand() {
        System.out.println(WRONG_COMMAND);
    }

    public void showMainMenu() {
        System.out.printf(MAIN_MENU_TEXT + System.lineSeparator(), productBasket.getProductsInBasket().size());
    }

    public void showAll() {
        System.out.println(SORTING_ALL_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        List<ProductDTO> tempProductDTOList = new ArrayList<>();
        switch (scanner.next()) {
            case "1" -> printProductDTOList(
                    tempProductDTOList = productService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(ProductDTO::getTitle))
                            .toList()
            );

            case "2" -> printProductDTOList(
                    tempProductDTOList = productService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(ProductDTO::getCategory))
                            .toList()
            );

            case "3" -> printProductDTOList(
                    tempProductDTOList = productService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(ProductDTO::getPrice))
                            .toList()
            );
            case "0" -> cancel();
            default -> wrongCommand();
        }
        System.out.printf(ADD_TO_BASKET_SUBMENU_TEXT + System.lineSeparator(), DTOMapper.toNumeratedProductDTOLines(tempProductDTOList));
        switch (scanner.next()) {
            case "1" -> {
                String command = scanner.next();
                if (CommandValidator.validate(command) && Integer.parseInt(command) <= tempProductDTOList.size()-1) {
                    ProductDTO tempProductDTO = tempProductDTOList.get(Integer.parseInt(command));
                    System.out.println(ENTER_NUMBER_OF_PRODUCT_SUBMENU_TEXT);
                    if ((command = scanner.next()).equals("0")) {
                        cancel();
                    } else {
                        if (CommandValidator.validate(command)) {
                            productBasket.add(tempProductDTO, Integer.parseInt(command));
                        } else {
                            wrongCommand();
                        }
                    }
                } else  {
                    wrongCommand();
                }
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private void printProductDTOList(List<ProductDTO> productDTOList) {
        String DTOsTOPrint = productDTOList
                .stream()
                .map(DTOMapper::toLine)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(DTOsTOPrint);
    }

    private final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести все продукты.
            2. Вывести продукты по категории.
            3. Поиск продукта по названию.
            4. Корзина. (%s)
            0. Выход.""";
    private final static String SORTING_ALL_SUBMENU_TEXT = """
            Отсортировать продукты по:
            1. Названию.
            2. Категории.
            3. Цене.
            0. Отмена.""";
    private final static String SORTING_SUBMENU_TEXT = """
            Отсортировать продукты по:
            1. Названию.
            2. Цене.
            0. Отмена.""";
    private final static String ADD_TO_BASKET_SUBMENU_TEXT = """
            1. Добавить продукт в корзину по номеру в списке.
            %s
            0. Отмена.""";
    private final static String CHOOSE_CATEGORY_SUBMENU_TEXT = """
            1. Выберите категорию товаров из списка:
            %s
            0. Отмена.""";
    private final static String ENTER_NUMBER_OF_PRODUCT_SUBMENU_TEXT = """
            Введите количество добавляемого товара:
            0. Отмена.""";
}
