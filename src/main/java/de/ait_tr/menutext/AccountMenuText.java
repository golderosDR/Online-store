package de.ait_tr.menutext;

public class AccountMenuText {
    private AccountMenuText() {
    }
    public final static String CANCEL_OPERATION = "Операция отменена.";
    public final static String WRONG_COMMAND = "Команда не распознана.";
    public final static String GO_BACK = "<-";
    public final static String MENU_POINT_IS_NOT_AVAILABLE_MSG = "На данный момент функция недоступна.";
    public final static String OLD_PASSWORD_INCORRECT_MSG = "Неверно введен старый пароль.";
    public final static String NEW_PASSWORDS_NOT_SAME_MSG = "Подтверждение нового пароля и новый пароль не совпадают.";
    public final static String PASSWORDS_ARE_SAME_MSG = "Старый и новый пароли совпадают.";
    public final static String CHANGE_USER_PASSWORD_SUCCESS_MSG = "Пароль успешно изменен.";
    public final static String CHANGE_USER_PASSWORD_FAIL_MSG = "Пароль не изменен.";

    public final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. В магазин.
            2. Изменить данные пользователя.
            0. Выход.""";
    public final static String CHANGE_USER_DATA_MENU_TEXT = """
            Изменить данные пользователя:
            1. Изменить пароль.
            2. Изменить имя пользователя.
            3. Изменить e-mail пользователя.
            0. Назад.""";
    public final static String GET_OLD_PASSWORD_SUBMENU_TEXT = """
            Введите старый пароль:
            0. Отмена.""";
    public final static String GET_NEW_PASSWORD_SUBMENU_TEXT = """
            Введите новый пароль:
            0. Отмена.""";
    public final static String REPEAT_PASSWORD_SUBMENU_TEXT = """
            Повторите ввод пароля:
            0. Отмена.""";
}
