package de.ait_tr.validators;

public class UserEmailValidator {
    private UserEmailValidator() {}
    public static boolean validate(String email) {
        return email.matches("\\w+@[a-z]+.[a-z]+");
    }
}
