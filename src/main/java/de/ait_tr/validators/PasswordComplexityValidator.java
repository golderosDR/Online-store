package de.ait_tr.validators;

import java.util.regex.Pattern;

public class PasswordComplexityValidator {
    // 8-16 символов пароль, минимум одна цифра, минимум одна
    // строчная буква, хотя бы одна прописная буква, хотя бы один
    // специальный символ без пробелов
    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);

    private PasswordComplexityValidator() {}

    public static boolean validate(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

}
