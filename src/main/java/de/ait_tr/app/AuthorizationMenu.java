package de.ait_tr.app;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.manualentry.ManualDataEntry;
import de.ait_tr.repositories.OrderRepositoryImpl;
import de.ait_tr.repositories.UserRepositoryImpl;
import de.ait_tr.services.CheckServiceImpl;
import de.ait_tr.services.OrderServiceImpl;
import de.ait_tr.services.UserService;
import de.ait_tr.services.UserServiceImpl;

import static de.ait_tr.menutext.AuthorizationMenuText.*;


public class AuthorizationMenu {
    private final UserService userService;
    private static final String BASE_USERS_FILE_PATH = "./Databases/UserDB/users.csv";
    private static final String BASE_ORDER_REPOSITORY_PATH = "./orders";
    private static final String BASE_CHECK_SERVICE_FILE_PATH = "./check_printer/check_tape.txt";

    public AuthorizationMenu() {
        this.userService = new UserServiceImpl(
                new UserRepositoryImpl(BASE_USERS_FILE_PATH),
                new OrderServiceImpl(
                        new OrderRepositoryImpl(BASE_ORDER_REPOSITORY_PATH),
                        new CheckServiceImpl(BASE_CHECK_SERVICE_FILE_PATH))
        );
    }

    public void cancel() {
        System.out.println(CANCEL_OPERATION_MSG);
    }

    public void wrongCommand() {
        System.out.println(WRONG_COMMAND_MSG);
    }

    public void goBack() {
        System.out.println(GO_BACK_MSG);
    }


    public void showMainMenu() {
        System.out.println("              Horns and hooves GMBH");
        System.out.println("              Добро прожаловать!");
        System.out.println(MAIN_MENU_TEXT);
    }

    public String LogInMenu() {
        ManualDataEntry manualDataEntry = new ManualDataEntry();
        String userId = manualDataEntry.logIn();
        if (userId != null) {
            System.out.println(USER_LOGIN_SUCCESS_MSG);
            return userId;
        }
        return null;

    }

    public void registrationMenu() {
        ManualDataEntry manualDataEntry = new ManualDataEntry();
        NewUserDTO newUserDTO = manualDataEntry.getNewUser();
        if (newUserDTO != null) {
            if (userService.add(newUserDTO)) {
                System.out.println(NEW_USER_CREATION_SUCCESS_MSG);
            }
        } else {
            System.out.println(NEW_USER_CREATION_FAIL_MSG);
        }
    }
}
