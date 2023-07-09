package de.ait_tr.manualentry;

import de.ait_tr.dtos.EntryDataDTO;
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
                userInfo,
                userPassword;
        System.out.println(AUTHORIZATION_GET_USER_NAME_MENU_TEXT);
        userInfo = scanner.next();
        if (userInfo.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        } else {
            if (userInfo.contains("@")) {
                userId = userService.findByEmail(userInfo) != null ?
                        userService.findByEmail(userInfo).id() :
                        null;
            } else {
                userId = userService.findByName(userInfo) != null ?
                        userService.findByName(userInfo).id() :
                        null;
            }
        }
        System.out.println(AUTHORIZATION_GET_USER_PASSWORD_MENU_TEXT);
        userPassword = scanner.next();
        if (userPassword.equals("0")) {
            System.out.println(CANCEL_OPERATION);
            return null;
        }
        if (userId != null && userService.isPasswordCorrect(userId, userPassword)) {
            return userId;
        } else  {
            return null;
        }
    }

    public NewUserDTO getNewUser() {
        System.out.println(REGISTRATION_GET_USER_NAME_MENU_TEXT);
        EntryDataDTO newNameCheck = checkName();
        if (!newNameCheck.isValid()) {
            if (newNameCheck.entryData().equals("0")) {
                System.out.println(CANCEL_OPERATION);
                return null;
            }
            System.out.println(USER_NAME_EXISTS_MSG);
            return null;
        } else {
            if (newNameCheck.entryData() == null) {
                System.out.println(WRONG_USER_NAME_FORMAT_MSG);
                return null;
            }
        }
        System.out.println(REGISTRATION_GET_USER_EMAIL_MENU_TEXT);
        EntryDataDTO newEmailCheck = checkEmail();
        if (!newEmailCheck.isValid()) {
            if (newEmailCheck.entryData().equals("0")) {
                System.out.println(CANCEL_OPERATION);
                return null;
            }
            System.out.println(USER_EMAIL_EXISTS_MSG);
            return null;
        } else {
            if (newEmailCheck.entryData() == null) {
                System.out.println(WRONG_USER_EMAIL_FORMAT_MSG);
                return null;
            }
        }
        System.out.println(REGISTRATION_GET_USER_PASSWORD_MENU_TEXT);
        EntryDataDTO newPasswordCheck = checkAndConfirmNewPassword();
        if (!newPasswordCheck.isValid()) {
            if (newPasswordCheck.entryData().equals("0")) {
                System.out.println(CANCEL_OPERATION);
                return null;
            }
            System.out.println(PASSWORD_COMPLEXITY_DESCRIPTION_TEXT);
            return null;
        } else {
            if (newPasswordCheck.entryData() == null) {
                System.out.println(NEW_PASSWORDS_NOT_SAME_MSG);
                return null;
            }
        }
        return UserDTOMapper.toNewUserDTO(
                newNameCheck.entryData(),
                newEmailCheck.entryData(),
                newPasswordCheck.entryData()
        );
    }

    private EntryDataDTO checkEmail() {
        Scanner scanner = new Scanner(System.in);
        String newEmail = scanner.next();
        if (newEmail.equals("0")) {
            return new EntryDataDTO(false, "0");
        }
        if (userService.findByEmail(newEmail) != null) {
            return new EntryDataDTO(false, null);
        } else {
            if (UserEmailValidator.validate(newEmail)) {
                return new EntryDataDTO(true, newEmail);
            }
            return new EntryDataDTO(true, null);
        }
    }

    private EntryDataDTO checkName() {
        Scanner scanner = new Scanner(System.in);
        String newName = scanner.next();
        if (newName.equals("0")) {
            return new EntryDataDTO(false, "0");
        }
        if (userService.findByName(newName) != null) {
            return new EntryDataDTO(false, null);
        } else {
            if (UserNameValidator.validate(newName)) {
                return new EntryDataDTO(true, newName);
            }
            return new EntryDataDTO(true, null);
        }
    }

    public String getNewPassword(String userId) {
        System.out.println(GET_OLD_PASSWORD_SUBMENU_TEXT);
        EntryDataDTO oldPasswordCheck = checkPassword(userId);

        if (!oldPasswordCheck.isValid()) {
            if (oldPasswordCheck.entryData().equals("0")) {
                System.out.println(CANCEL_OPERATION);
                return null;
            }
            System.out.println(OLD_PASSWORD_INCORRECT_MSG);
            return null;
        }

        System.out.println(GET_NEW_PASSWORD_SUBMENU_TEXT);
        EntryDataDTO newPasswordCheck = checkAndConfirmNewPassword();

        if (!newPasswordCheck.isValid()) {
            if (newPasswordCheck.entryData().equals("0")) {
                System.out.println(CANCEL_OPERATION);
                return null;
            }
            System.out.println(PASSWORD_COMPLEXITY_DESCRIPTION_TEXT);
            return null;
        } else {
            if (newPasswordCheck.entryData() == null) {
                System.out.println(NEW_PASSWORDS_NOT_SAME_MSG);
                return null;
            }
            return newPasswordCheck.entryData();
        }
    }

    private EntryDataDTO checkPassword(String userId) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();
        boolean isCorrect;
        if (password.equals("0")) {
            return new EntryDataDTO(false, "0");
        } else {
            isCorrect = userService.isPasswordCorrect(userId, password);
            if (isCorrect) {
                return new EntryDataDTO(true, password);
            }
            return new EntryDataDTO(false, null);
        }
    }

    private EntryDataDTO checkAndConfirmNewPassword() {
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.next();
        if (newPassword.equals("0'")) {
            return new EntryDataDTO(false, "0");
        }
        System.out.println(REPEAT_PASSWORD_SUBMENU_TEXT);
        String newPasswordConfirmation = scanner.next();
        if (newPasswordConfirmation.equals("0'")) {
            return new EntryDataDTO(false, "0");
        }
        boolean isComplex = PasswordComplexityValidator.validate(newPassword);
        boolean isSame = newPassword.equals(newPasswordConfirmation);
        if (!isComplex) {
            return new EntryDataDTO(false, null);
        } else {
            return isSame ?
                    new EntryDataDTO(true, newPassword) :
                    new EntryDataDTO(true, null);
        }


    }
}
