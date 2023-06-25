package de.ait_tr.exceptions;

public class IllegalUserArgumentException extends RuntimeException{
    public static String WRONG_USER_NAME_PARAMETERS_MESSAGE = "Недопустимое значение имени пользователя.";
    public static String WRONG_USER_ID_FORMAT_MESSAGE = "Недопустимый формат ID пользователя.";
    public static String WRONG_USER_EMAIL_FORMAT_MESSAGE = "Недопустимый формат e-mail пользователя.";
    public IllegalUserArgumentException(String message) {
        super(message);
    }
}
