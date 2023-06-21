package de.ait_tr.app;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mapper.CategoryMapper;
import de.ait_tr.mapper.DTOMapper;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.services.ProductService;
import de.ait_tr.services.ProductServiceImpl;
import de.ait_tr.validators.CommandValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

    public void allProductsMenu() {
        System.out.println(SORTING_ALL_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        List<ProductDTO> tempProductDTOList;
        switch (scanner.next()) {
            case "1" -> {
                tempProductDTOList = getSortedList(productService.findAll(), Comparator.comparing(ProductDTO::getTitle));
                choose(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(productService.findAll(), Comparator.comparing(ProductDTO::getCategory));
                choose(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = getSortedList(productService.findAll(), Comparator.comparing(ProductDTO::getPrice));
                choose(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }

    }

    public void filteredByCategoryMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(CHOOSE_CATEGORY_SUBMENU_TEXT + System.lineSeparator(), CategoryMapper.ToLines());
        List<ProductDTO> tempProductDTOList;
        switch (scanner.next()) {
            case "1" -> {
                tempProductDTOList = productService.findAllAccessories();
                showFiltered(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = productService.findAllBags();
                showFiltered(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = productService.findAllBelts();
                showFiltered(tempProductDTOList);
            }
            case "4" -> {
                tempProductDTOList = productService.findAllGlasses();
                showFiltered(tempProductDTOList);
            }
            case "5" -> {
                tempProductDTOList = productService.findAllHeath();
                showFiltered(tempProductDTOList);
            }
            case "6" -> {
                tempProductDTOList = productService.findAllNotebooks();
                showFiltered(tempProductDTOList);
            }
            case "7" -> {
                tempProductDTOList = productService.findAllSmartphones();
                showFiltered(tempProductDTOList);
            }
            case "8" -> {
                tempProductDTOList = productService.findAllTablets();
                showFiltered(tempProductDTOList);
            }
            case "9" -> {
                tempProductDTOList = productService.findAllTVs();
                showFiltered(tempProductDTOList);
            }
            case "10" -> {
                tempProductDTOList = productService.findAllWatches();
                showFiltered(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    public void findMenu() {
        System.out.println(ENTER_SEARCH_INFO_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        String searchInfo = scanner.nextLine();
        if (searchInfo.equals("0")) {
            cancel();
        } else {
            if (searchInfo.length() < 2) {
                System.out.println("Строка для поиска слишком короткая!");
            } else {
                List<ProductDTO> tempProductDTOList = productService.find(searchInfo);
                if (tempProductDTOList.isEmpty()) {
                    System.out.println("По вашему запросу ничего не найдено.");
                } else {
                    choose(tempProductDTOList);
                }
            }
        }

    }

    private void showFiltered(List<ProductDTO> tempProductDTOList) {
        System.out.println(SORTING_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "1" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::getTitle)
                );
                choose(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::getPrice)
                );
                choose(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private void choose(List<ProductDTO> tempProductDTOList) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(DESCRIPTION_SUBMENU_TEXT + System.lineSeparator(),
                DTOMapper.toNumeratedProductDTOLines(tempProductDTOList));
        String command = scanner.next();
        if (CommandValidator.validate(command)) {
            if (Integer.parseInt(command) == 0) {
                cancel();
            } else if (Integer.parseInt(command) < tempProductDTOList.size()) {
                ProductDTO tempProductDTO = tempProductDTOList.get(Integer.parseInt(command) - 1);
                System.out.println(DTOMapper.toLineWithDescription(tempProductDTO));
                addToBasket(tempProductDTO);
            } else {
                wrongCommand();
            }
        } else {
            wrongCommand();
        }
    }

    private void addToBasket(ProductDTO tempProductDTO) {
        System.out.println(ADD_TO_BASKET_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        switch (command) {
            case "1" -> {
                System.out.println(ENTER_NUMBER_OF_PRODUCT_SUBMENU_TEXT);
                command = scanner.next();
                if (CommandValidator.validate(command) && Integer.parseInt(command) > 0) {
                    productBasket.add(tempProductDTO, Integer.parseInt(command));
                    System.out.println("Товар добавлен в корзину.");
                }
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private List<ProductDTO> getSortedList(List<ProductDTO> productDTOList, Comparator<ProductDTO> comparing) {
        return productDTOList
                .stream()
                .sorted(comparing)
                .toList();
    }


    private final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести все продукты.
            2. Вывести продукты по категории.
            3. Поиск продукта.
            4. Корзина(%s).
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
    private final static String DESCRIPTION_SUBMENU_TEXT = """
            Просмотреть товар с описанием по номеру в списке.
            %s
            0. Отмена.""";
    private final static String ADD_TO_BASKET_SUBMENU_TEXT = """
            1. Добавить продукт в корзину.
            0. Отмена.""";
    private final static String CHOOSE_CATEGORY_SUBMENU_TEXT = """
            1. Выберите категорию товаров из списка:
            %s
            0. Отмена.""";
    private final static String ENTER_NUMBER_OF_PRODUCT_SUBMENU_TEXT = """
            Введите количество добавляемого товара:
            0. Отмена.""";
    private final static String ENTER_SEARCH_INFO_SUBMENU_TEXT = """
            Введите строку для поиска:
            0. Отмена.""";

}
