package de.ait_tr.manualentry;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.mappers.UserDTOMapper;
import de.ait_tr.repositories.OrderRepositoryImpl;
import de.ait_tr.repositories.UserRepositoryImpl;
import de.ait_tr.services.CheckServiceImpl;
import de.ait_tr.services.OrderServiceImpl;
import de.ait_tr.services.UserService;
import de.ait_tr.services.UserServiceImpl;
import de.ait_tr.validators.PasswordComplexityValidator;
import de.ait_tr.validators.UserEmailValidator;
import de.ait_tr.validators.UserNameValidator;

import java.util.Scanner;

import static de.ait_tr.menutext.AccountMenuText.*;
import static de.ait_tr.menutext.AccountMenuText.NEW_PASSWORDS_NOT_SAME_MSG;
import static de.ait_tr.menutext.AuthorizationMenuText.*;
import static de.ait_tr.menutext.AuthorizationMenuText.PASSWORD_COMPLEXITY_DESCRIPTION_TEXT;

public class ManualDataEntry {
    private final UserService userService;
    private static final String BASE_ORDER_REPOSITORY_PATH = "./orders";
    private static final String BASE_CHECK_SERVICE_FILE_PATH = "./check_printer/check_tape.txt";
    private static final String BASE_USERS_FILE_PATH = "./Databases/UserDB/users.csv";

    public ManualDataEntry() {
        this.userService = new UserServiceImpl(
                new UserRepositoryImpl(BASE_USERS_FILE_PATH),
                new OrderServiceImpl(
                        new OrderRepositoryImpl(BASE_ORDER_REPOSITORY_PATH),
                        new CheckServiceImpl(BASE_CHECK_SERVICE_FILE_PATH)
                )
        );
    }

    public String logIn() {
        Scanner scanner = new Scanner(System.in);
        String userId,
                userIdByName,
                userIdByEmail,
                userInfo,
                userPassword;
        boolean isPasswordCorrect;
        System.out.println(AUTHORIZATION_GET_USER_NAME_MENU_TEXT);
        userInfo = scanner.next();
        if (userInfo.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            userIdByName = (userService.findByName(userInfo) != null ?
                    userService.findByName(userInfo).id() :
                    null
            );
            userIdByEmail = (userService.findByEmail(userInfo) != null ?
                    userService.findByEmail(userInfo).id() :
                    null
            );
            if (userIdByName != null) {
                userId = userIdByName;
            } else if (userIdByEmail != null) {
                userId = userIdByEmail;
            } else {
                System.out.println(INCORRECT_LOGIN);
                return null;
            }
        }
        System.out.println(AUTHORIZATION_GET_USER_PASSWORD_MENU_TEXT);
        userPassword = scanner.next();
        if (userPassword.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            isPasswordCorrect = userService.isPasswordCorrect(userId, userPassword);
        }

        if (isPasswordCorrect) {
            return userId;
        } else {
            System.out.println(INCORRECT_PASSWORD);
            return null;
        }


    }

    public NewUserDTO getNewUser() {
        Scanner scanner = new Scanner(System.in);
        String newUserName,
                newUserEmail,
                newUserPassword;
        boolean isUserNameExists,
                isUserNameFormatCorrect,
                isUserEmailExists,
                isUserEmailFormatCorrect,
                isPasswordCorrect;

        System.out.println(REGISTRATION_GET_USER_NAME_MENU_TEXT);
        newUserName = scanner.next();
        isUserNameExists = userService.findByName(newUserName) != null;
        if (isUserNameExists) {
            System.out.println(USER_NAME_EXISTS_MSG);
            return null;
        } else  {
            isUserNameFormatCorrect = UserNameValidator.validate(newUserName);
            if (!isUserNameFormatCorrect) {
                System.out.println(WRONG_USER_NAME_FORMAT_MSG);
                return null;
            }
        }

        System.out.println(REGISTRATION_GET_USER_EMAIL_MENU_TEXT);
        newUserEmail = scanner.next();
        isUserEmailExists = userService.findByEmail(newUserEmail) != null;
        if (isUserEmailExists) {
            System.out.println(USER_EMAIL_EXISTS_MSG);
            return null;
        } else {
            isUserEmailFormatCorrect = UserEmailValidator.validate(newUserEmail);
            if (!isUserEmailFormatCorrect) {
                System.out.println(WRONG_USER_EMAIL_FORMAT_MSG);
                return null;
            }
        }

        System.out.println(REGISTRATION_GET_USER_PASSWORD_MENU_TEXT);
        newUserPassword = scanner.next();
        isPasswordCorrect = PasswordComplexityValidator.validate(newUserPassword);
        if (!isPasswordCorrect) {
            System.out.println(PASSWORD_COMPLEXITY_DESCRIPTION_TEXT);
            return null;
        }

        return UserDTOMapper.toNewUserDTO(newUserName, newUserEmail, newUserPassword);
    }

    public String getNewPassword(String userId) {
        boolean isOldCorrect,
                isNewComplex,
                isNewPasswordsSame;
        String newPassword;
        String oldPassword,
                newPasswordRepeat;
        Scanner scanner = new Scanner(System.in);

        System.out.println(GET_OLD_PASSWORD_SUBMENU_TEXT);
        oldPassword = scanner.next();
        if (oldPassword.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            isOldCorrect = userService.isPasswordCorrect(userId, oldPassword);
            if (!isOldCorrect) {
                System.out.println(OLD_PASSWORD_INCORRECT_MSG);
                return null;
            }
        }
        System.out.println(GET_NEW_PASSWORD_SUBMENU_TEXT);
        newPassword = scanner.next();
        if (newPassword.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            isNewComplex = PasswordComplexityValidator.validate(newPassword);
            if (!isNewComplex) {
                System.out.println(PASSWORD_COMPLEXITY_DESCRIPTION_TEXT);
                return null;
            }
        }
        System.out.println(REPEAT_NEW_PASSWORD_SUBMENU_TEXT);
        newPasswordRepeat = scanner.next();
        if (newPasswordRepeat.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            isNewPasswordsSame = newPassword.equals(newPasswordRepeat);
            if (!isNewPasswordsSame) {
                System.out.println(NEW_PASSWORDS_NOT_SAME_MSG);
                return null;
            }
        }
        return newPassword;
    }
}
