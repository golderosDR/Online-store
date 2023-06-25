package de.ait_tr.app;

import de.ait_tr.manualentry.ManualDataEntry;
import de.ait_tr.repositories.OrderRepositoryImpl;
import de.ait_tr.repositories.ProductRepositoryImpl;
import de.ait_tr.repositories.UserRepositoryImpl;
import de.ait_tr.services.*;

import java.util.Scanner;

import static de.ait_tr.menutext.AccountMenuText.*;

public class AccountMenu {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final String userId;

    private static final String BASE_PRODUCTS_FILE_PATH = "./Databases/productDB/Products.csv";
    private static final String BASE_ORDER_REPOSITORY_PATH = "./orders";
    private static final String BASE_CHECK_SERVICE_FILE_PATH = "./check_printer/check_tape.txt";
    private static final String BASE_USERS_FILE_PATH = "./Databases/UserDB/users.csv";

    AccountMenu(String userId) {
        this.productService = new ProductServiceImpl(new ProductRepositoryImpl(BASE_PRODUCTS_FILE_PATH));

        this.orderService = new OrderServiceImpl(
                new OrderRepositoryImpl(BASE_ORDER_REPOSITORY_PATH),
                new CheckServiceImpl(BASE_CHECK_SERVICE_FILE_PATH)
        );
        this.userService = new UserServiceImpl(
                new UserRepositoryImpl(BASE_USERS_FILE_PATH),
                this.orderService
        );
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
        String userName = userService.findById(userId).name();
        System.out.printf("              Horns and hooves GMBH%n              Добро прожаловать, %s!%n%n", userName);
        System.out.println(MAIN_MENU_TEXT);
    }

    public void changeUserDataMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CHANGE_USER_DATA_MENU_TEXT);
        switch (scanner.next()) {
            case "1" -> changePasswordSubmenu();
            case "2", "3" -> System.out.println(MENU_POINT_IS_NOT_AVAILABLE_MSG);
            case "0" -> goBack();
            default -> wrongCommand();
        }
    }

    private void changePasswordSubmenu() {
        ManualDataEntry manualDataEntry = new ManualDataEntry();
        String newPassword = manualDataEntry.getNewPassword(userId);
        if (newPassword != null) {
            if (userService.changePassword(userId, newPassword)) {
                System.out.println(CHANGE_USER_PASSWORD_SUCCESS_MSG);
            } else {
                System.out.println(PASSWORDS_ARE_SAME_MSG);
            }
        } else {
            System.out.println(CHANGE_USER_PASSWORD_FAIL_MSG);
        }
    }

    public void myOrdersMenu() {
        System.out.println(MENU_POINT_IS_NOT_AVAILABLE_MSG);
    }
}
