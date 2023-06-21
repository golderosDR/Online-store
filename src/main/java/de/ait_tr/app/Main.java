package de.ait_tr.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        while (true) {
            menu.showMainMenu();
            switch (scanner.next()) {
                case "1" -> menu.showAllAndChoose();
                case "2" -> menu.filterByCategoryAndChoose();
/*                case "3" -> ;
                case "4" -> ;*/
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
