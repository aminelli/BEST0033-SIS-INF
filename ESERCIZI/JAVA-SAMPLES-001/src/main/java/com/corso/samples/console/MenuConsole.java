package com.corso.samples.console;

import java.util.Scanner;

import com.corso.samples.datatypes.AlgoritmiOrdinamentoDemo;
import com.corso.samples.datatypes.ArrayDemo;
import com.corso.samples.datatypes.BigDecimalDemo;
import com.corso.samples.datatypes.CollectionDemo;
import com.corso.samples.datatypes.CollectionsArraysDemo;
import com.corso.samples.datatypes.DateDemo;
import com.corso.samples.datatypes.LiteralDemo;
import com.corso.samples.datatypes.OperatoriDemo;
import com.corso.samples.datatypes.StreamsDemo;
import com.corso.samples.javaadv.EccezioniDemo;
import com.corso.samples.javaadv.InputOutputDemo;
import com.corso.samples.javaadv.LambdaFunctionDemo;
import com.corso.samples.javaadv.ReflectionDemo;
import com.corso.samples.javaadv.SerializationDemo;
import com.corso.samples.javaadv.ThreadDemo;
import com.corso.samples.javabase.CicliDemo;
import com.corso.samples.javabase.CondizioniDemo;
import com.corso.samples.oop.AnnotationsDemo;
import com.corso.samples.oop.ClassiDemo;
import com.corso.samples.oop.CovariantReturnDemo;
import com.corso.samples.oop.GenericsDemo;
import com.corso.samples.oop.InterfacceFunzionaliDemo;
import com.corso.samples.oop.MetodiProprietaDemo;
import com.corso.samples.oop.OverloadOverrideDemo;

public class MenuConsole {

    private final Scanner scanner;

    // Costruttore: serve ad inizializzare l'oggetto che si sta cercando di creare
    public MenuConsole() {
        scanner = new Scanner(System.in);

        /*
         * Scanner sc1 = new Scanner(System.in);
         * Scanner sc2 = new Scanner(System.in);
         * Scanner sc3 = sc2;
         * sc1 = null;
         * System.gc();
         * sc2 = null;
         * Runtime.getRuntime().gc();
         * sc3.close();
         */
    }

    public void showMenu() throws Exception {
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
        System.out.println("1.  BigDecimal Demo");
        System.out.println("2.  Date Demo");
        System.out.println("3.  Operatori Demo");
        System.out.println("4.  Literal Demo");
        System.out.println("5.  Cicli Demo");
        System.out.println("6.  Condizioni Demo");
        System.out.println("-".repeat(60));
        System.out.println("7.  Classi Demo");
        System.out.println("8.  Classi - Proprietà/Metodi Demo");
        System.out.println("9.  Generics Demo");
        System.out.println("10. Interfacce Funzionali Demo");
        System.out.println("11. Classi - Overload/Override Demo");
        System.out.println("12. Covariant Return Demo");
        System.out.println("-".repeat(60));
        System.out.println("13. Array Demo");
        System.out.println("14. Collection Demo");
        System.out.println("15. Collection - Streams Demo");
        System.out.println("16. Collection - Java.util.Collections e java.util.Arrays Demo");
        System.out.println("17. Algoritmi Ordinamento Demo");
        System.out.println("-".repeat(60));
        System.out.println("18. Annotations Demo");
        System.out.println("19. Reflection Demo");
        System.out.println("20. Lambda Function Demo");
        System.out.println("21. Eccezioni e Throws Demo");
        System.out.println("22. Serialization Demo");
        System.out.println("23. Input/Output Demo");
        System.out.println("24. Thread Demo");
        System.out.println("-".repeat(60));
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

    private boolean executeAction(int menuIndex) throws Exception {

        switch (menuIndex) {
            case 1:
                BigDecimalDemo.sample();
                break;
            case 2:
                DateDemo.sample();
                break;
            case 3:
                OperatoriDemo.sample();
                break;
            case 4:
                LiteralDemo.sample();
                break;
            case 5:
                CicliDemo.sample();
                break;
            case 6:
                CondizioniDemo.sample();
                break;
            case 7:
                ClassiDemo.sample();
                break;
            case 8:
                MetodiProprietaDemo.sample();
                break;
            case 9:
                GenericsDemo.sample();
                break;
            case 10:
                InterfacceFunzionaliDemo.sample();
                break;
            case 11:
                OverloadOverrideDemo.sample();
                break;
            case 12:
                CovariantReturnDemo.sample();
                break;
            case 13:
                ArrayDemo.sample();
                break;
            case 14:
                CollectionDemo.sample();
                break;
            case 15:
                StreamsDemo.sample();
                break;
            case 16:
                CollectionsArraysDemo.sample();
                break;
            case 17:
                AlgoritmiOrdinamentoDemo.sample();
                break;
            case 18:
                AnnotationsDemo.sample();
                break;
            case 19:
                ReflectionDemo.sample();
                break;
            case 20:
                LambdaFunctionDemo.sample();
                break;
            case 21:
                EccezioniDemo.sample();
                break;
            case 22:
                SerializationDemo.sample();
                break;
            case 23:
                InputOutputDemo.sample();
                break;
            case 24:
                ThreadDemo.sample();
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
