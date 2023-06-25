package de.ait_tr.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String userId = runAuthorizationMenu();
        runAccountMenu(userId);
    }


    public static void runOnlineStoreMenu(String userId) {
        Scanner scanner = new Scanner(System.in);
        OnlineStoreMenu onlineStoreMenu = new OnlineStoreMenu(userId);
        onlineStoreMenu.greeting();
        String command;
        do {
            onlineStoreMenu.showMainMenu();
            switch (command = scanner.next()) {
                case "1" -> onlineStoreMenu.allProductsMenu();
                case "2" -> onlineStoreMenu.filterByCategoryMenu();
                case "3" -> onlineStoreMenu.findMenu();
                case "4" -> onlineStoreMenu.basketMenu();
                case "0" -> onlineStoreMenu.goBack();
                default -> onlineStoreMenu.wrongCommand();
            }
        }
        while (!command.equals("0"));
    }

    public static void runAccountMenu(String userId) {
        Scanner scanner = new Scanner(System.in);
        AccountMenu accountMenu = new AccountMenu(userId);
        while (true) {
            accountMenu.showMainMenu();
            switch (scanner.next()) {
                case "1" -> runOnlineStoreMenu(userId);
                case "2" -> accountMenu.changeUserDataMenu();
                case "3" -> accountMenu.myOrdersMenu();

                case "0" -> {
                    System.out.println("<-");
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                }
                default -> accountMenu.wrongCommand();
            }
        }
    }

    public static String runAuthorizationMenu() {
        String userId;
        Scanner scanner = new Scanner(System.in);
        AuthorizationMenu authorizationMenu = new AuthorizationMenu();
        while (true) {
            authorizationMenu.showMainMenu();
            switch (scanner.next()) {
                case "1" -> {
                    userId = authorizationMenu.LogInMenu();
                    if (userId != null) {
                        return userId;
                    }
                }
                case "2" -> authorizationMenu.registrationMenu();

                case "0" -> {
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                }
                default -> authorizationMenu.wrongCommand();
            }
        }
    }
}
