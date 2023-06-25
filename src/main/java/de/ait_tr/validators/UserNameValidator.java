package de.ait_tr.validators;

public class UserNameValidator {
    private UserNameValidator() {}
    public static boolean validate(String userName) {
        return userName.matches("\\w{4,16}");
    }
}
