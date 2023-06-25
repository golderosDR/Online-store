package de.ait_tr.app;

import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.mappers.BasketMapper;
import de.ait_tr.mappers.CategoryMapper;
import de.ait_tr.mappers.ProductDTOMapper;
import de.ait_tr.models.Category;
import de.ait_tr.models.Order;
import de.ait_tr.models.ProductBasket;
import de.ait_tr.repositories.OrderRepositoryImpl;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.repositories.UserRepositoryImpl;
import de.ait_tr.services.*;
import de.ait_tr.validators.BasketValidator;
import de.ait_tr.validators.CommandValidator;

import java.util.*;
import java.util.stream.Collectors;

import static de.ait_tr.menutext.OnlineStoreMenuText.*;

public class OnlineStoreMenu {
    private final ProductService productService;
    private final ProductBasket productBasket;
    private final OrderService orderService;
    private final UserService userService;
    private final String userId;

    private static final String BASE_PRODUCTS_FILE_PATH = "./Databases/productDB/Products.csv";
    private static final String BASE_ORDER_REPOSITORY_PATH = "./orders";
    private static final String BASE_CHECK_SERVICE_FILE_PATH = "./check_printer/check_tape.txt";
    private static final String BASE_USERS_FILE_PATH = "./Databases/UserDB/users.csv";

    OnlineStoreMenu(String userId) {
        this.productService = new ProductServiceImpl(new ProductRepositoryImpl(BASE_PRODUCTS_FILE_PATH));
        this.orderService = new OrderServiceImpl(
                new OrderRepositoryImpl(BASE_ORDER_REPOSITORY_PATH),
                new CheckServiceImpl(BASE_CHECK_SERVICE_FILE_PATH)
        );
        this.userService = new UserServiceImpl(
                new UserRepositoryImpl(BASE_USERS_FILE_PATH),
                new OrderServiceImpl(
                        new OrderRepositoryImpl(BASE_ORDER_REPOSITORY_PATH),
                        new CheckServiceImpl(BASE_CHECK_SERVICE_FILE_PATH))
        );
        this.productBasket = new ProductBasket();
        this.userId = userId;
    }

    public void cancel() {
        System.out.println(CANCEL_OPERATION);
    }

    public void wrongCommand() {
        System.out.println(WRONG_COMMAND);
    }
    public void goBack() {
        System.out.println(GO_BACK);
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
                        Comparator.comparing(ProductDTO::getTitleIgnoreCase));
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(productService.findAll(),
                        Comparator.comparing(ProductDTO::category));
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "3" -> {
                tempProductDTOList = getSortedList(productService.findAll(),
                        Comparator.comparing(ProductDTO::price));
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
                    case "0" -> goBack();
                    default -> wrongCommand();
                }
            }
        } while (!temp.equals("0"));
    }

    public void buyMenu() {
        Scanner scanner = new Scanner(System.in);
        List<String> errors = BasketValidator.validate(productBasket, productService.findAll());
        if (errors.isEmpty()) {
            Order order = orderService.create(productBasket, userId);
            System.out.printf("Заказ сформирован. Номер вашего заказа %d.%n", order.getOrderNumber());
            System.out.println(BUY_SUBMENU_TEXT);
            switch (scanner.next()) {
                case "1" -> {
                    orderService.save(order);
                    productService.buy(productBasket);
                    orderService.printCheck(order);
                    productBasket.clear();
                    System.out.println(SHOPPING_SUCCESS_MSG);
                }
                case "0" -> cancel();
                default -> wrongCommand();
            }

        } else {
            System.out.println(errors.stream()
                    .collect(Collectors.joining(System.lineSeparator()))
            );
            System.out.println();
        }
    }

    /**
     * print 10 random products
     */
    public void greeting() {
        String userName = userService.findById(userId).name();
        System.out.printf("              Horns and hooves GMBH%n              Добро прожаловать, %s!%n%n", userName);
        Random random = new Random();
        Set<Integer> indexList = new HashSet<>();
        List<ProductDTO> productDTOList = productService.findAll()
                .stream()
                .filter(productDTO -> productDTO.amount() != 0)
                .toList();
        List<ProductDTO> randomProductList = new ArrayList<>();

        while (indexList.size() < 10) {
            indexList.add(random.nextInt(productDTOList.size()));
        }
        indexList.forEach(index -> randomProductList.add(productDTOList.get(index)));
        System.out.println(
                randomProductList.stream()
                        .sorted(Comparator.comparing(ProductDTO::getTitleIgnoreCase))
                        .map(ProductDTOMapper::toLine)
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
                        Comparator.comparing(ProductDTO::getTitleIgnoreCase)
                );
                chooseFromListSubmenu(tempProductDTOList);
            }
            case "2" -> {
                tempProductDTOList = getSortedList(tempProductDTOList,
                        Comparator.comparing(ProductDTO::price)
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
                ProductDTOMapper.toNumeratedProductDTOLines(tempProductDTOList));
        String command = scanner.next();
        if (CommandValidator.validate(command)) {
            if (Integer.parseInt(command) == 0) {
                goBack();
            } else if (Integer.parseInt(command) <= tempProductDTOList.size()) {
                ProductDTO tempProductDTO = tempProductDTOList.get(Integer.parseInt(command) - 1);
                System.out.println(ProductDTOMapper.toLineWithDescription(tempProductDTO));
                addToBasketSubmenu(tempProductDTO);
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
                if (tempProductDTO.amount() != 0) {
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
                } else {
                    System.out.println(PRODUCT_NOT_AVAILABLE_MSG);
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
            case "0" -> goBack();
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
