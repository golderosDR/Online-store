package de.ait_tr.menutext;

public class MenuText {
    private MenuText(){}
    public final static String CANCEL_OPERATION = "Операция отменена.";
    public final static String WRONG_COMMAND = "Команда не распознана.";
    public final static String WRONG_SEARCH_INFO_LENGTH_MSG = "Введенное слово для поиска слишком короткое!";
    public final static String NO_DATA_FOUND_MSG = "По вашему запросу '%s' ничего не найдено.%n";
    public final static String PRODUCT_ADDED_MSG = "Товар добавлен в корзину.";
    public final static String PRODUCT_REMOVED_MSG = "Товар удален из корзины.";
    public final static String PRODUCT_COUNT_EDITED_MSG = "Количество товара изменено.";
    public final static String SHOPPING_SUCCESS_MSG = "Товары успешно куплены.";
    public final static String PRODUCT_NOT_AVAILABLE_MSG = "Товара нет в наличии.";
    public final static String EMPTY_BASKET_MENU_TEXT = """
            Корзина пуста.
            0. Назад.""";
    public final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести все товары.
            2. Вывести товары по категории.
            3. Поиск товара.
            4. Корзина(%s).
            0. Выход.""";
    public final static String SORTING_ALL_SUBMENU_TEXT = """
            Отсортировать товары по:
            1. Названию.
            2. Категории.
            3. Цене.
            0. Отмена.""";
    public final static String SORTING_SUBMENU_TEXT = """
            Отсортировать товары по:
            1. Названию.
            2. Цене.
            0. Отмена.""";
    public final static String DESCRIPTION_SUBMENU_TEXT = """
            Просмотреть товар с описанием по номеру в списке.
            %s
            0. Назад.""";
    public final static String ADD_TO_BASKET_SUBMENU_TEXT = """
            1. Добавить товар в корзину.
            0. Отмена.""";
    public final static String CHOOSE_CATEGORY_SUBMENU_TEXT = """
            1. Выберите категорию товаров из списка:
            %s
            0. Отмена.""";
    public final static String ENTER_NUMBER_OF_PRODUCT_SUBMENU_TEXT = """
            Введите количество добавляемого товара:
            0. Отмена.""";
    public final static String ENTER_SEARCH_INFO_SUBMENU_TEXT = """
            Введите строку для поиска:
            0. Отмена.""";
    public final static String BASKET_SUBMENU_TEXT = """
            1. Редактировать корзину.
            2. В кассу.
            0. Назад.""";
    public final static String BASKET_EDIT_SUBMENU_TEXT = """
            1. Удалить товар из корзины.
            2. Изменить количество товара.
            0. Назад""";
    public final static String BASKET_CHOOSE_SUBMENU_TEXT = """
            Введите номер позиции товара из списка:
            %s
            """;
    public final static String ENTER_BASKET_PRODUCT_NUMBER_REMOVE_SUBMENU_TEXT = """
            Введите номер позиции товара для удаления из корзины:
            0. Отмена.""";
    public final static String ENTER_BASKET_PRODUCT_NUMBER_EDIT_SUBMENU_TEXT = """
            Введите номер позиции товара:
            0. Отмена.""";
    public final static String ENTER_NEW_NUMBER_OF_PRODUCT_SUBMENU_TEXT = """
            Введите новое количество товара:
            0. Отмена.""";
    public final static String BUY_SUBMENU_TEXT = """
            1. Купить.
            0. Отмена.""";

}
