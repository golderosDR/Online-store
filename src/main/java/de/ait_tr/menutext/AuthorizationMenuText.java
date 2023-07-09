package de.ait_tr.menutext;

public class AuthorizationMenuText {
    private AuthorizationMenuText() {}
    public final static String CANCEL_OPERATION_MSG = "Операция отменена.";
    public final static String WRONG_COMMAND_MSG = "Команда не распознана.";
    public final static String GO_BACK_MSG = "<-";
    public final static String INCORRECT_LOGIN = "Неверное имя пользователя.";
    public final static String INCORRECT_PASSWORD = "Неверный пароль.";
    public final static String INCORRECT_LOGIN_OR_PASSWORD = "Неверное имя пользователя или пароль.";
    public final static String USER_LOGIN_SUCCESS_MSG = "Авторизация прошла успешно.";
    public final static String NEW_USER_CREATION_SUCCESS_MSG = "Новый пользователь успешно создан.";
    public final static String NEW_USER_CREATION_FAIL_MSG = "Ошибка создания нового пользователя.";
    public final static String USER_NAME_EXISTS_MSG = "Пользователь с таким именем уже существует.";
    public final static String USER_EMAIL_EXISTS_MSG = "Пользователь с таким e-mail уже существует.";
    public final static String WRONG_USER_EMAIL_FORMAT_MSG = "Подобный формат e-mail не поддерживается.";
    public final static String WRONG_USER_NAME_FORMAT_MSG = """
            Неверный формат имени пользователя.
            Может содержать от 4 до 16 только буквенно-циферных символов и символы '_'.
            """;
    public final static String PASSWORD_COMPLEXITY_DESCRIPTION_TEXT = """
            Пароль слишком простой.
            Пароль  должен быть длинной 8-16 символов , содержать минимум одну цифру,
            минимум одну строчную букву, хотя бы одну прописную букву, хотя бы один
            специальный символ без пробелов.""";
    public final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Aвторизация.
            2. Регистрация нового пользователя.
            0. Выход.""";
    public final static String AUTHORIZATION_GET_USER_NAME_MENU_TEXT = """
            Введите имя пользователя или e-mail:
            0. Отмена.""";
    public final static String AUTHORIZATION_GET_USER_PASSWORD_MENU_TEXT = """
            Введите пароль:
            0. Отмена.""";
    public final static String REGISTRATION_GET_USER_PASSWORD_MENU_TEXT = """
            Введите пароль:
            0. Отмена.""";
    public final static String REGISTRATION_GET_USER_NAME_MENU_TEXT = """
            Введите имя пользователя:
            0. Отмена.""";
    public final static String REGISTRATION_GET_USER_EMAIL_MENU_TEXT = """
            Введите e-mail:
            0. Отмена.""";

}
