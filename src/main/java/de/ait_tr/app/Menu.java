package de.ait_tr.app;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mappers.BasketMapper;
import de.ait_tr.mappers.CategoryMapper;
import de.ait_tr.mappers.DTOMapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.services.ProductService;
import de.ait_tr.services.ProductServiceImpl;
import de.ait_tr.validators.BasketValidator;
import de.ait_tr.validators.CommandValidator;

import java.util.*;
import java.util.stream.Collectors;

import static de.ait_tr.menutext.MenuText.*;

public class Menu {
    private final ProductService productService;
    private final ProductBasket productBasket;
    private static final String BASE_PRODUCTS_REPOSITORY_PATH = "Products.csv";

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
                tempProductDTOList = getSortedList(productService.findAll(),
                        Comparator.comparing(ProductDTO::getTitle));
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(productService.findAll(),
                        Comparator.comparing(ProductDTO::getCategory));
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = getSortedList(productService.findAll(),
                        Comparator.comparing(ProductDTO::getPrice));
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    public void filterByCategoryMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(CHOOSE_CATEGORY_SUBMENU_TEXT + System.lineSeparator(), CategoryMapper.ToLines());
        List<ProductDTO> tempProductDTOList;
        switch (scanner.next()) {
            case "1" -> {
                tempProductDTOList = productService.findByCategory(Category.ACCESSORIES);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = productService.findByCategory(Category.BAGS);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = productService.findByCategory(Category.BELTS);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "4" -> {
                tempProductDTOList = productService.findByCategory(Category.GLASSES);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "5" -> {
                tempProductDTOList = productService.findByCategory(Category.HEALTH);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "6" -> {
                tempProductDTOList = productService.findByCategory(Category.NOTEBOOKS);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "7" -> {
                tempProductDTOList = productService.findByCategory(Category.SMARTPHONES);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "8" -> {
                tempProductDTOList = productService.findByCategory(Category.TABLETS);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "9" -> {
                tempProductDTOList = productService.findByCategory(Category.TVS);
                showFilteredSubmenu(tempProductDTOList);
            }
            case "10" -> {
                tempProductDTOList = productService.findByCategory(Category.WATCHES);
                showFilteredSubmenu(tempProductDTOList);
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
                System.out.println(WRONG_SEARCH_INFO_LENGTH_MSG);
            } else {
                List<ProductDTO> tempProductDTOList = productService.find(searchInfo);
                if (tempProductDTOList.isEmpty()) {
                    System.out.printf(NO_DATA_FOUND_MSG, searchInfo);
                } else {
                    chooseFromListSubmenu(tempProductDTOList);
                }
            }
        }

    }

    public void basketMenu() {
        Scanner scanner = new Scanner(System.in);
        String temp;
        System.out.println("Содержимое корзины:");
        do {
            if (productBasket.getProductsInBasket().isEmpty()) {
                System.out.println(EMPTY_BASKET_MENU_TEXT);
                if ((temp = scanner.next()).equals("0")) {
                    cancel();
                } else {
                    wrongCommand();
                }
            } else {
                System.out.println(BasketMapper.toLines(productBasket.getProductsInBasket()));
                System.out.println(BASKET_SUBMENU_TEXT);
                switch (temp = scanner.next()) {
                    case "1" -> editBasketMenu();
                    case "2" -> buyMenu();
                    case "0" -> cancel();
                    default -> wrongCommand();
                }
            }
        } while (!temp.equals("0"));
    }

    public void buyMenu() {
        if (BasketValidator.validate(productBasket, productService.findAll())) {
            productService.buy(productBasket);
            System.out.println(SHOPPING_SUCCESS_MSG);
        } else {
            System.out.println(SHOPPING_FAIL_MSG);
        }
    }

    /**
     * print 10 random products
     */
    public void greeting() {
        //TODO userService
        String userName = "Guest";
        System.out.printf("     Horns and hooves GMBH%n    Добро прожаловать, %s!%n%n", userName);
        Random random = new Random();
        Set<Integer> indexList = new HashSet<>();
        List<ProductDTO> productDTOList = productService.findAll()
                .stream()
                .filter(productDTO -> productDTO.getAmount() != 0)
                .toList();
        List<ProductDTO> randomProductList = new ArrayList<>();

        while (indexList.size() < 10) {
            indexList.add(random.nextInt(productDTOList.size()));
        }
        indexList.forEach(index -> randomProductList.add(productDTOList.get(index)));
        System.out.println(
                randomProductList.stream()
                        .sorted(Comparator.comparing(ProductDTO::getTitle))
                        .map(DTOMapper::toLine)
                        .collect(Collectors.joining(System.lineSeparator()))
        );
        System.out.println();

    }


    private void showFilteredSubmenu(List<ProductDTO> tempProductDTOList) {
        System.out.println(SORTING_SUBMENU_TEXT);
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "1" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::getTitle)
                        );
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::getPrice)
                );
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private void chooseFromListSubmenu(List<ProductDTO> tempProductDTOList) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(DESCRIPTION_SUBMENU_TEXT + System.lineSeparator(),
                DTOMapper.toNumeratedProductDTOLines(tempProductDTOList));
        String command = scanner.next();
        if (CommandValidator.validate(command)) {
            if (Integer.parseInt(command) == 0) {
                cancel();
            } else if (Integer.parseInt(command) <= tempProductDTOList.size()) {
                ProductDTO tempProductDTO = tempProductDTOList.get(Integer.parseInt(command) - 1);
                if (tempProductDTO.getAmount() != 0) {
                    System.out.println(DTOMapper.toLineWithDescription(tempProductDTO));
                    addToBasketSubmenu(tempProductDTO);
                } else {
                    System.out.println(PRODUCT_NOT_AVAILABLE_MSG);
                }
            } else {
                wrongCommand();
            }
        } else {
            wrongCommand();
        }
    }

    private void addToBasketSubmenu(ProductDTO tempProductDTO) {
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
                        System.out.println(PRODUCT_ADDED_MSG);
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
            case "1" -> removeFromBasketSubmenu();
            case "2" -> editCountBasketSubmenu();
            case "0" -> cancel();
            default -> wrongCommand();
        }
    }

    private void removeFromBasketSubmenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(BASKET_CHOOSE_SUBMENU_TEXT,
                BasketMapper.toLines(productBasket.getProductsInBasket()));
        System.out.println(ENTER_BASKET_PRODUCT_NUMBER_REMOVE_SUBMENU_TEXT);
        String command = scanner.next();
        if (command.equals("0")) {
            cancel();
        } else {
            if (CommandValidator.validate(command)) {
                if (Integer.parseInt(command) <= productBasket.getProductsInBasket().size()) {
                    productBasket.remove(Integer.parseInt(command) - 1);
                    System.out.println(PRODUCT_REMOVED_MSG);
                } else {
                    wrongCommand();
                }
            } else {
                wrongCommand();
            }

        }
    }

    private void editCountBasketSubmenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(BASKET_CHOOSE_SUBMENU_TEXT,
                BasketMapper.toLines(productBasket.getProductsInBasket()));
        System.out.println(ENTER_BASKET_PRODUCT_NUMBER_EDIT_SUBMENU_TEXT);
        String command = scanner.next();
        if (command.equals("0")) {
            cancel();
        } else {
            if (CommandValidator.validate(command)) {
                if (Integer.parseInt(command) <= productBasket.getProductsInBasket().size()) {
                    System.out.println(ENTER_NEW_NUMBER_OF_PRODUCT_SUBMENU_TEXT);
                    String count = scanner.next();
                    if (count.equals("0")) {
                        cancel();
                    } else {
                        if (CommandValidator.validate(command)) {

                            productBasket.edit(Integer.parseInt(command) - 1, Integer.parseInt(count));
                            System.out.println(PRODUCT_COUNT_EDITED_MSG);
                        } else {
                            wrongCommand();
                        }
                    }
                } else {
                    wrongCommand();
                }
            } else {
                wrongCommand();
            }

        }
    }

}
