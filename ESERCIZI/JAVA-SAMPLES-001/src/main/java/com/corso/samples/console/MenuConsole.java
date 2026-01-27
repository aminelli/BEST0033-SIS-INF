package com.corso.samples.console;

import com.corso.samples.datatypes.BigDecimalDemo;

import java.util.Scanner;

public class MenuConsole  {

    private final Scanner scanner;

    // Costruttore: serve ad inizializzare l'oggetto che si sta cercando di creare
    public MenuConsole() {
        scanner = new Scanner(System.in);


        /*
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = sc2;
        sc1 = null;
        System.gc();
        sc2 = null;
        Runtime.getRuntime().gc();
        sc3.close();
        */
    }


    public void showMenu() {
        boolean displayMenu = true;

        while (displayMenu) {
            drawMenu();
            int menuIndex = loadMenu();
            displayMenu = executeAction(menuIndex);
        }

        System.out.println("Ciao !!!");
        scanner.close();
    }


    private void drawMenu() {
        System.out.println("\n".repeat(10) + "=".repeat(60));
        System.out.println("MENU PRINCIPALE");
        System.out.println("=".repeat(60));
        System.out.println("1. BigDecimal Demo");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
        System.out.println("");
        System.out.println("0. Esci");
        System.out.println("=".repeat(60));
        System.out.println("");
        System.out.println("Scegli una voce di menu...");
    }

    private int loadMenu() {
        try {
            int menuIndex = scanner.nextInt();
            scanner.nextLine();
            return menuIndex;
        } catch (Exception ex) {
            scanner.nextLine();
            return -1;
        }

    }

    private boolean executeAction(int menuIndex) {

        switch (menuIndex) {
            case 1:
                BigDecimalDemo.sample();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                return false;
            default:
                System.out.println("Scelta non valida !");
        }

        System.out.println("Premere invio per continuare");
        scanner.nextLine();
        return true;
    }

}
