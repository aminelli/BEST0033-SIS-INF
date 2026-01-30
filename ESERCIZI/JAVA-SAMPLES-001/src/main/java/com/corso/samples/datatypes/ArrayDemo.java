package com.corso.samples.datatypes;

import java.util.Arrays;

/**
 * Dimostrazione completa dell'uso degli array in Java
 */
public class ArrayDemo {

    /**
     * Dichiarazione e inizializzazione di array
     */
    public void dichiarazioneArray() {
        System.out.println("=== DICHIARAZIONE E INIZIALIZZAZIONE ===");
        
        // Modo 1: Dichiarazione con dimensione
        int[] numeri = new int[5];
        numeri[0] = 10;
        numeri[1] = 20;
        numeri[2] = 30;
        numeri[3] = 40;
        numeri[4] = 50;
        System.out.println("Array con dimensione: " + Arrays.toString(numeri));
        
        // Modo 2: Inizializzazione diretta
        int[] numeri2 = {1, 2, 3, 4, 5};
        System.out.println("Inizializzazione diretta: " + Arrays.toString(numeri2));
        
        // Modo 3: Sintassi alternativa (meno comune)
        int[] numeri3 = new int[]{100, 200, 300};
        System.out.println("Sintassi alternativa: " + Arrays.toString(numeri3));
        
        // Nota: int numeri[] è valido ma meno usato
        int altroModo[] = {7, 8, 9};
        System.out.println("Sintassi alternativa (C-style): " + Arrays.toString(altroModo));
    }

    /**
     * Array di tipi primitivi
     */
    public void arrayTipiPrimitivi() {
        System.out.println("\n=== ARRAY DI TIPI PRIMITIVI ===");
        
        // Array di interi
        int[] interi = {1, 2, 3, 4, 5};
        System.out.println("int[]: " + Arrays.toString(interi));
        
        // Array di double
        double[] decimali = {1.5, 2.7, 3.14, 4.2};
        System.out.println("double[]: " + Arrays.toString(decimali));
        
        // Array di char
        char[] caratteri = {'J', 'a', 'v', 'a'};
        System.out.println("char[]: " + Arrays.toString(caratteri));
        
        // Array di boolean
        boolean[] flags = {true, false, true, true};
        System.out.println("boolean[]: " + Arrays.toString(flags));
        
        // Array di byte
        byte[] bytes = {1, 2, 3, 4};
        System.out.println("byte[]: " + Arrays.toString(bytes));
    }

    /**
     * Array di oggetti
     */
    public void arrayOggetti() {
        System.out.println("\n=== ARRAY DI OGGETTI ===");
        
        // Array di String
        String[] nomi = {"Mario", "Luigi", "Peach", "Bowser"};
        System.out.println("String[]: " + Arrays.toString(nomi));
        
        // Array di Integer (wrapper class)
        Integer[] numeri = {10, 20, 30, 40};
        System.out.println("Integer[]: " + Arrays.toString(numeri));
        
        // Array di oggetti custom
        Persona[] persone = new Persona[3];
        persone[0] = new Persona("Mario", 35);
        persone[1] = new Persona("Luigi", 32);
        persone[2] = new Persona("Peach", 28);
        
        System.out.println("Persona[]:");
        for (Persona p : persone) {
            System.out.println("  " + p);
        }
    }

    /**
     * Operazioni comuni sugli array
     */
    public void operazioniComuni() {
        System.out.println("\n=== OPERAZIONI COMUNI ===");
        
        int[] numeri = {5, 2, 8, 1, 9, 3};
        
        // Lunghezza
        System.out.println("Lunghezza: " + numeri.length);
        
        // Accesso agli elementi
        System.out.println("Primo elemento: " + numeri[0]);
        System.out.println("Ultimo elemento: " + numeri[numeri.length - 1]);
        
        // Modifica elemento
        numeri[0] = 99;
        System.out.println("Dopo modifica primo elemento: " + Arrays.toString(numeri));
        
        // Iterazione con for classico
        System.out.print("Iterazione for: ");
        for (int i = 0; i < numeri.length; i++) {
            System.out.print(numeri[i] + " ");
        }
        System.out.println();
        
        // Iterazione con for-each
        System.out.print("Iterazione for-each: ");
        for (int num : numeri) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Metodi della classe Arrays
     */
    public void metodiArrays() {
        System.out.println("\n=== METODI CLASSE ARRAYS ===");
        
        int[] numeri = {5, 2, 8, 1, 9, 3};
        
        // toString - stampa array
        System.out.println("toString: " + Arrays.toString(numeri));
        
        // sort - ordina array
        Arrays.sort(numeri);
        System.out.println("Dopo sort: " + Arrays.toString(numeri));
        
        // binarySearch - cerca elemento (array deve essere ordinato)
        int indice = Arrays.binarySearch(numeri, 8);
        System.out.println("Indice di 8: " + indice);
        
        // fill - riempie array con un valore
        int[] riempito = new int[5];
        Arrays.fill(riempito, 7);
        System.out.println("Array riempito con 7: " + Arrays.toString(riempito));
        
        // copyOf - copia array
        int[] copia = Arrays.copyOf(numeri, numeri.length);
        System.out.println("Copia array: " + Arrays.toString(copia));
        
        // copyOfRange - copia una porzione
        int[] porzione = Arrays.copyOfRange(numeri, 1, 4);
        System.out.println("Porzione [1-4): " + Arrays.toString(porzione));
        
        // equals - confronta array
        boolean uguali = Arrays.equals(numeri, copia);
        System.out.println("Array uguali: " + uguali);
    }

    /**
     * Array multidimensionali
     */
    public void arrayMultidimensionali() {
        System.out.println("\n=== ARRAY MULTIDIMENSIONALI ===");
        
        // Array bidimensionale (matrice)
        int[][] matrice = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrice 3x3:");
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
        
        // Creazione con dimensioni
        int[][] matrice2 = new int[3][4];
        matrice2[0][0] = 10;
        matrice2[1][2] = 20;
        System.out.println("\nMatrice 3x4: " + Arrays.deepToString(matrice2));
        
        // Array tridimensionale
        int[][][] cubo = new int[2][3][4];
        cubo[0][1][2] = 100;
        System.out.println("Cubo 2x3x4: " + Arrays.deepToString(cubo));
        
        // Array "jagged" (righe di lunghezza variabile)
        int[][] jagged = new int[3][];
        jagged[0] = new int[2];
        jagged[1] = new int[4];
        jagged[2] = new int[3];
        System.out.println("Array jagged: " + Arrays.deepToString(jagged));
    }

    /**
     * Calcola la somma degli elementi
     */
    public int sommaArray(int[] array) {
        int somma = 0;
        for (int num : array) {
            somma += num;
        }
        return somma;
    }

    /**
     * Trova il valore massimo
     */
    public int trovaMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array vuoto o null");
        }
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Trova il valore minimo
     */
    public int trovaMin(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array vuoto o null");
        }
        
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Inverte l'ordine degli elementi
     */
    public void invertiArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    /**
     * Cerca un elemento nell'array
     */
    public int cercaElemento(int[] array, int valore) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valore) {
                return i;
            }
        }
        return -1; // Non trovato
    }

    /**
     * Esempio di utilizzo
     */
    public static void main(String[] args) {
        ArrayDemo demo = new ArrayDemo();
        
        demo.dichiarazioneArray();
        demo.arrayTipiPrimitivi();
        demo.arrayOggetti();
        demo.operazioniComuni();
        demo.metodiArrays();
        demo.arrayMultidimensionali();
        
        // Esempi con metodi di utilità
        System.out.println("\n=== METODI DI UTILITÀ ===");
        int[] numeri = {5, 2, 8, 1, 9, 3};
        System.out.println("Array: " + Arrays.toString(numeri));
        System.out.println("Somma: " + demo.sommaArray(numeri));
        System.out.println("Massimo: " + demo.trovaMax(numeri));
        System.out.println("Minimo: " + demo.trovaMin(numeri));
        System.out.println("Cerca 8: indice " + demo.cercaElemento(numeri, 8));
        
        demo.invertiArray(numeri);
        System.out.println("Dopo inversione: " + Arrays.toString(numeri));
    }
}

/**
 * Classe di supporto per esempi
 */
class Persona {
    private String nome;
    private int eta;
    
    public Persona(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
    
    @Override
    public String toString() {
        return nome + " (" + eta + " anni)";
    }
}
