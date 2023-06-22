package de.ait_tr.app;

import de.ait_tr.dtos.InBasketDTO;
import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mappers.CategoryMapper;
import de.ait_tr.mappers.DTOMapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.services.ProductService;
import de.ait_tr.services.ProductServiceImpl;
import de.ait_tr.validators.CommandValidator;

import java.util.*;

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
                chooseToAdd(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(productService.findAll(), Comparator.comparing(ProductDTO::getCategory));
                chooseToAdd(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = getSortedList(productService.findAll(), Comparator.comparing(ProductDTO::getPrice));
                chooseToAdd(tempProductDTOList);
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
                tempProductDTOList = productService.findByCategory(Category.ACCESSORIES);
                showFiltered(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = productService.findByCategory(Category.BAGS);
                showFiltered(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = productService.findByCategory(Category.BELTS);
                showFiltered(tempProductDTOList);
            }
            case "4" -> {
                tempProductDTOList = productService.findByCategory(Category.GLASSES);
                showFiltered(tempProductDTOList);
            }
            case "5" -> {
                tempProductDTOList = productService.findByCategory(Category.HEALTH);
                showFiltered(tempProductDTOList);
            }
            case "6" -> {
                tempProductDTOList = productService.findByCategory(Category.NOTEBOOKS);
                showFiltered(tempProductDTOList);
            }
            case "7" -> {
                tempProductDTOList = productService.findByCategory(Category.SMARTPHONES);
                showFiltered(tempProductDTOList);
            }
            case "8" -> {
                tempProductDTOList = productService.findByCategory(Category.TABLETS);
                showFiltered(tempProductDTOList);
            }
            case "9" -> {
                tempProductDTOList = productService.findByCategory(Category.TVS);
                showFiltered(tempProductDTOList);
            }
            case "10" -> {
                tempProductDTOList = productService.findByCategory(Category.WATCHES);
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
                    chooseToAdd(tempProductDTOList);
                }
            }
        }

    }

    public void basketMenu() {
        Scanner scanner = new Scanner(System.in);
        String temp;
        do {
            if (productBasket.getProductsInBasket().isEmpty()) {
                System.out.println("Корзина пуста.");
                System.out.println("0. Отмена.");
                if ((temp = scanner.next()).equals("0")) {
                    cancel();
                } else {
                    wrongCommand();
                }
            } else {
                System.out.println(basketTOLines());
                System.out.println(BASKET_SUBMENU_TEXT);
                switch (scanner.next()) {
                    case "1" -> editBasketMenu();
                    case "2" -> editCountBasketMenu();
                    case "0" -> cancel();
                    default -> wrongCommand();
                }
                System.out.println(basketTOLines());
                System.out.println(BASKET_SUBMENU_TEXT);
                temp = scanner.next();

            }


        } while (!temp.equals("0"));
    }


    private String basketTOLines() {
        System.out.println("Содержимое корзины:");
        List<InBasketDTO> productsInBasket = productBasket.getProductsInBasket();
        StringBuilder output = new StringBuilder();
        int counter = 1;
                if (!productBasket.getProductsInBasket().isEmpty()) {
        ProductDTO temp = productService.findById(productBasket.getProductsInBasket().get(0).getId());
        double totalPrice = 0;
        int spaceCount = DTOMapper.toLine(temp.getTitle(), temp.getPrice(), productBasket.getProductsInBasket().get(0).getCount()).length() + 3;
        for (InBasketDTO inBasketDTO : productsInBasket) {
            temp = productService.findById(inBasketDTO.getId());
            totalPrice += temp.getPrice() * inBasketDTO.getCount();
            output.append(counter++)
                    .append(". ")
                    .append(DTOMapper.toLine(temp.getTitle(), temp.getPrice(), inBasketDTO.getCount()))
                    .append(System.lineSeparator());
        }
        output.append(System.lineSeparator())
                .append("Total price")
                .append(" ".repeat(spaceCount - ("Total price").length() - String.format("%.2f", totalPrice).length()))
                .append(String.format("%.2f", totalPrice));
        return output.toString();
        } else {
            return "Корзина пуста";
        }

    }

    /**
     * sout 10 random products
     */
    public void printHomePageProductList() {
        Random random = new Random();

        Set<ProductDTO> homePageProductList = new HashSet<>();
        while (homePageProductList.size() < 10) {
            int index = random.nextInt(productService.findAll().size());
            ProductDTO productDTO = productService.findAll().get(index);
            homePageProductList.add(productDTO);  //здесь  Set<ProductDTO>
        }
        for (ProductDTO productDTO : homePageProductList) {
            System.out.println(DTOMapper.toLine(productDTO));
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
                chooseToAdd(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::getPrice)
                );
                chooseToAdd(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private void chooseToAdd(List<ProductDTO> tempProductDTOList) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(DESCRIPTION_SUBMENU_TEXT + System.lineSeparator(),
                DTOMapper.toNumeratedProductDTOLines(tempProductDTOList));
        String command = scanner.next();
        if (CommandValidator.validate(command)) {
            if (Integer.parseInt(command) == 0) {
                cancel();
            } else if (Integer.parseInt(command) <= tempProductDTOList.size()) {
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
                if (CommandValidator.validate(command)) {
                    if (Integer.parseInt(command) > 0) {
                        productBasket.add(tempProductDTO, Integer.parseInt(command));
                        System.out.println("Товар добавлен в корзину.");
                    } else {
                        wrongCommand();
                    }
                } else {
                    wrongCommand();
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

    private void editBasketMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(BASKET_EDIT_SUBMENU_TEXT);
        switch (scanner.next()) {
            case "1" -> {
                System.out.printf(BASKET_CHOOSE_SUBMENU_TEXT, basketTOLines());
                System.out.println(ENTER_BASKET_PRODUCT_NUMBER_SUBMENU_TEXT);
                String command = scanner.next();
                if (CommandValidator.validate(command)) {
                    if (Integer.parseInt(command) <= productBasket.getProductsInBasket().size()) {
                        productBasket.getProductsInBasket().remove(Integer.parseInt(command) - 1);
                        System.out.println("Товар удален из корзины.");
                    } else {
                        wrongCommand();
                    }
                } else {
                    wrongCommand();
                }
            }
            case "2" -> {
                System.out.printf(BASKET_CHOOSE_SUBMENU_TEXT, basketTOLines());
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }

    }

    private void editCountBasketMenu() {

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
    private final static String BASKET_SUBMENU_TEXT = """
            1. Редактировать корзину.
            2. Купить.
            0. Отмена.""";
    private final static String BASKET_EDIT_SUBMENU_TEXT = """
            1. Удалить товар из корзины.
            2. Изменить количество товара.
            0. Отмена.""";
    private final static String BASKET_CHOOSE_SUBMENU_TEXT = """
            Выберите товар из списка:
            %s
            0. Отмена.""";
    private final static String ENTER_BASKET_PRODUCT_NUMBER_SUBMENU_TEXT = """
            Введите номер позиции продукта для удаления из корзины:
            0. Отмена.""";
}
