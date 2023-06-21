package de.ait_tr.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        while (true) {
            menu.showMainMenu();
            switch (scanner.next()) {
                case "1" -> menu.allProductsMenu();
                case "2" -> menu.filteredByCategoryMenu();
                case "3" -> menu.findMenu();
                /*case "4" -> ;*/
                case "0" -> {
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                }
                default -> menu.wrongCommand();
            }
        }
    }
}
