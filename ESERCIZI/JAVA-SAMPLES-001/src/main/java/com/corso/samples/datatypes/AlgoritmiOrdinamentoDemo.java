package com.corso.samples.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Esempio completo e avanzato sugli Algoritmi di Ordinamento in Java
 * Include:
 * - Bubble Sort (base e ottimizzato)
 * - Selection Sort
 * - Insertion Sort
 * - Merge Sort
 * - Quick Sort (diverse varianti)
 * - Heap Sort
 * - Counting Sort
 * - Radix Sort
 * - Bucket Sort
 * - Shell Sort
 * - Tim Sort (ibrido)
 * - Analisi complessità temporale e spaziale
 * - Comparazioni di performance
 * - Visualizzazione passi
 * - Ordinamento di oggetti custom
 */
public class AlgoritmiOrdinamentoDemo {

    private static boolean visualizzaPasosi = false;
    private static int contatorePassi = 0;

    public static void sample() {
        AlgoritmiOrdinamentoDemo demo = new AlgoritmiOrdinamentoDemo();
        
        System.out.println("=== ALGORITMI DI ORDINAMENTO - GUIDA COMPLETA ===\n");
        
        // Algoritmi base
        demo.bubbleSortDemo();
        demo.selectionSortDemo();
        demo.insertionSortDemo();
        
        // Algoritmi avanzati
        demo.mergeSortDemo();
        demo.quickSortDemo();
        demo.heapSortDemo();
        
        // Algoritmi speciali
        demo.countingSortDemo();
        demo.radixSortDemo();
        demo.bucketSortDemo();
        demo.shellSortDemo();
        
        // Analisi e comparazioni
        demo.analisiComplessita();
        demo.comparazionePerformance();
        demo.ordinamentoOggetti();
        demo.casiSpeciali();
        demo.bestPractices();
    }

    // ==================== BUBBLE SORT ====================

    /**
     * Bubble Sort - Algoritmo base
     * Complessità: O(n²) nel caso peggiore
     * Spazio: O(1)
     * Stabile: Sì
     */
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Scambia elementi
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Bubble Sort Ottimizzato
     * Aggiunge flag per terminare anticipatamente se l'array è già ordinato
     */
    public void bubbleSortOttimizzato(int[] arr) {
        int n = arr.length;
        boolean scambiato;
        
        for (int i = 0; i < n - 1; i++) {
            scambiato = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    scambiato = true;
                }
            }
            
            // Se nessun scambio, l'array è ordinato
            if (!scambiato) {
                break;
            }
        }
    }

    /**
     * Bubble Sort con visualizzazione passi
     */
    public void bubbleSortVisualizzato(int[] arr) {
        int n = arr.length;
        System.out.println("Array iniziale: " + Arrays.toString(arr));
        
        for (int i = 0; i < n - 1; i++) {
            boolean scambiato = false;
            System.out.println("\n--- Pass " + (i + 1) + " ---");
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    System.out.println("Scambio " + arr[j] + " con " + arr[j + 1]);
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    scambiato = true;
                }
            }
            
            System.out.println("Dopo pass " + (i + 1) + ": " + Arrays.toString(arr));
            
            if (!scambiato) {
                System.out.println("Array già ordinato, termino anticipatamente");
                break;
            }
        }
    }

    public void bubbleSortDemo() {
        System.out.println("=== BUBBLE SORT ===");
        System.out.println("Complessità: O(n²) tempo, O(1) spazio");
        System.out.println("Stabile: Sì\n");
        
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array originale: " + Arrays.toString(arr1));
        bubbleSort(arr1.clone());
        
        int[] arr2 = {5, 2, 8, 1, 9};
        System.out.println("\nBubble Sort con visualizzazione:");
        bubbleSortVisualizzato(arr2);
        
        // Test su array quasi ordinato
        int[] arr3 = {1, 2, 3, 5, 4};
        int[] test1 = arr3.clone();
        int[] test2 = arr3.clone();
        
        long start = System.nanoTime();
        bubbleSort(test1);
        long timeBasic = System.nanoTime() - start;
        
        start = System.nanoTime();
        bubbleSortOttimizzato(test2);
        long timeOptimized = System.nanoTime() - start;
        
        System.out.println("\nArray quasi ordinato " + Arrays.toString(arr3) + ":");
        System.out.println("Bubble Sort base: " + timeBasic + "ns");
        System.out.println("Bubble Sort ottimizzato: " + timeOptimized + "ns");
        System.out.println("Speedup: " + String.format("%.2fx", (double)timeBasic / timeOptimized));
        
        System.out.println();
    }

    // ==================== SELECTION SORT ====================

    /**
     * Selection Sort
     * Complessità: O(n²) sempre
     * Spazio: O(1)
     * Stabile: No (ma può essere reso stabile)
     */
    public void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Trova l'indice del minimo elemento
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Scambia il minimo trovato con il primo elemento
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Selection Sort con visualizzazione
     */
    public void selectionSortVisualizzato(int[] arr) {
        int n = arr.length;
        System.out.println("Array iniziale: " + Arrays.toString(arr));
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            System.out.println("\n--- Pass " + (i + 1) + " ---");
            System.out.println("Cerco minimo da indice " + i);
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            System.out.println("Minimo trovato: " + arr[minIndex] + " all'indice " + minIndex);
            System.out.println("Scambio con elemento all'indice " + i + " (valore " + arr[i] + ")");
            
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            
            System.out.println("Dopo pass " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }

    /**
     * Selection Sort Stabile
     * Versione modificata per mantenere la stabilità
     */
    public void selectionSortStabile(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Invece di scambiare, shifta gli elementi
            int minValue = arr[minIndex];
            while (minIndex > i) {
                arr[minIndex] = arr[minIndex - 1];
                minIndex--;
            }
            arr[i] = minValue;
        }
    }

    public void selectionSortDemo() {
        System.out.println("=== SELECTION SORT ===");
        System.out.println("Complessità: O(n²) tempo, O(1) spazio");
        System.out.println("Stabile: No (versione base)\n");
        
        int[] arr = {29, 10, 14, 37, 13};
        System.out.println("Selection Sort con visualizzazione:");
        selectionSortVisualizzato(arr.clone());
        
        System.out.println();
    }

    // ==================== INSERTION SORT ====================

    /**
     * Insertion Sort
     * Complessità: O(n²) caso peggiore, O(n) caso migliore
     * Spazio: O(1)
     * Stabile: Sì
     */
    public void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Sposta gli elementi maggiori di key una posizione avanti
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Insertion Sort con visualizzazione
     */
    public void insertionSortVisualizzato(int[] arr) {
        int n = arr.length;
        System.out.println("Array iniziale: " + Arrays.toString(arr));
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            System.out.println("\n--- Pass " + i + " ---");
            System.out.println("Inserisco elemento " + key + " nella parte ordinata");
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            
            System.out.println("Dopo pass " + i + ": " + Arrays.toString(arr));
        }
    }

    /**
     * Insertion Sort con Binary Search
     * Usa binary search per trovare la posizione di inserimento
     */
    public void insertionSortBinary(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            
            // Trova posizione con binary search
            int pos = Arrays.binarySearch(arr, 0, i, key);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            
            // Shifta elementi
            System.arraycopy(arr, pos, arr, pos + 1, i - pos);
            arr[pos] = key;
        }
    }

    public void insertionSortDemo() {
        System.out.println("=== INSERTION SORT ===");
        System.out.println("Complessità: O(n²) caso peggiore, O(n) caso migliore");
        System.out.println("Spazio: O(1)");
        System.out.println("Stabile: Sì\n");
        
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("Insertion Sort con visualizzazione:");
        insertionSortVisualizzato(arr.clone());
        
        // Caso migliore: array già ordinato
        int[] arrOrdinato = {1, 2, 3, 4, 5};
        long start = System.nanoTime();
        insertionSort(arrOrdinato.clone());
        long timeBest = System.nanoTime() - start;
        
        // Caso peggiore: array ordinato al contrario
        int[] arrInverso = {5, 4, 3, 2, 1};
        start = System.nanoTime();
        insertionSort(arrInverso.clone());
        long timeWorst = System.nanoTime() - start;
        
        System.out.println("\nPerformance Insertion Sort:");
        System.out.println("Caso migliore (già ordinato): " + timeBest + "ns");
        System.out.println("Caso peggiore (inverso): " + timeWorst + "ns");
        System.out.println("Differenza: " + String.format("%.2fx", (double)timeWorst / timeBest));
        
        System.out.println();
    }

    // ==================== MERGE SORT ====================

    /**
     * Merge Sort
     * Complessità: O(n log n) sempre
     * Spazio: O(n)
     * Stabile: Sì
     */
    public void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Ordina ricorsivamente le due metà
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            
            // Merge delle due metà ordinate
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // Dimensioni dei sub-array
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Array temporanei
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copia dati negli array temporanei
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        // Merge degli array temporanei
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copia elementi rimanenti
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    /**
     * Merge Sort con visualizzazione
     */
    public void mergeSortVisualizzato(int[] arr) {
        System.out.println("Array iniziale: " + Arrays.toString(arr));
        contatorePassi = 0;
        mergeSortVisualizzatoHelper(arr, 0, arr.length - 1, 0);
    }

    private void mergeSortVisualizzatoHelper(int[] arr, int left, int right, int depth) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            String indent = "  ".repeat(depth);
            System.out.println(indent + "Dividi [" + left + ".." + right + "] in " +
                             "[" + left + ".." + mid + "] e [" + (mid+1) + ".." + right + "]");
            
            mergeSortVisualizzatoHelper(arr, left, mid, depth + 1);
            mergeSortVisualizzatoHelper(arr, mid + 1, right, depth + 1);
            
            merge(arr, left, mid, right);
            
            System.out.println(indent + "Merge [" + left + ".." + right + "]: " +
                             Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        }
    }

    public void mergeSortDemo() {
        System.out.println("=== MERGE SORT ===");
        System.out.println("Complessità: O(n log n) tempo, O(n) spazio");
        System.out.println("Stabile: Sì\n");
        
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Merge Sort con visualizzazione:");
        mergeSortVisualizzato(arr.clone());
        
        System.out.println();
    }

    // ==================== QUICK SORT ====================

    /**
     * Quick Sort - Versione standard (pivot = ultimo elemento)
     * Complessità: O(n log n) medio, O(n²) peggiore
     * Spazio: O(log n)
     * Stabile: No
     */
    public void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Quick Sort con pivot mediano (mediana di tre)
     */
    public void quickSortMedianOfThree(int[] arr) {
        quickSortMedianHelper(arr, 0, arr.length - 1);
    }

    private void quickSortMedianHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionMedian(arr, low, high);
            
            quickSortMedianHelper(arr, low, pivotIndex - 1);
            quickSortMedianHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partitionMedian(int[] arr, int low, int high) {
        // Median of three: first, middle, last
        int mid = low + (high - low) / 2;
        
        if (arr[mid] < arr[low]) swap(arr, low, mid);
        if (arr[high] < arr[low]) swap(arr, low, high);
        if (arr[mid] < arr[high]) swap(arr, mid, high);
        
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Quick Sort con randomized pivot
     */
    public void quickSortRandomized(int[] arr) {
        quickSortRandomHelper(arr, 0, arr.length - 1);
    }

    private void quickSortRandomHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionRandom(arr, low, high);
            
            quickSortRandomHelper(arr, low, pivotIndex - 1);
            quickSortRandomHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partitionRandom(int[] arr, int low, int high) {
        // Scegli pivot casuale
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1);
        swap(arr, randomIndex, high);
        
        return partition(arr, low, high);
    }

    /**
     * Quick Sort 3-way (gestisce duplicati efficientemente)
     */
    public void quickSort3Way(int[] arr) {
        quickSort3WayHelper(arr, 0, arr.length - 1);
    }

    private void quickSort3WayHelper(int[] arr, int low, int high) {
        if (low >= high) return;
        
        int lt = low;
        int gt = high;
        int pivot = arr[low];
        int i = low + 1;
        
        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        
        quickSort3WayHelper(arr, low, lt - 1);
        quickSort3WayHelper(arr, gt + 1, high);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSortDemo() {
        System.out.println("=== QUICK SORT ===");
        System.out.println("Complessità: O(n log n) medio, O(n²) peggiore");
        System.out.println("Spazio: O(log n)");
        System.out.println("Stabile: No\n");
        
        int[] arr = {10, 7, 8, 9, 1, 5};
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();
        
        System.out.println("Array originale: " + Arrays.toString(arr));
        
        quickSort(arr1);
        System.out.println("Quick Sort standard: " + Arrays.toString(arr1));
        
        quickSortMedianOfThree(arr2);
        System.out.println("Quick Sort (median of three): " + Arrays.toString(arr2));
        
        quickSortRandomized(arr3);
        System.out.println("Quick Sort (randomized): " + Arrays.toString(arr3));
        
        // Test con molti duplicati
        int[] arrDuplicati = {5, 2, 5, 2, 5, 2, 5, 2, 5};
        int[] test1 = arrDuplicati.clone();
        int[] test2 = arrDuplicati.clone();
        
        System.out.println("\nArray con duplicati: " + Arrays.toString(arrDuplicati));
        
        long start = System.nanoTime();
        quickSort(test1);
        long timeStandard = System.nanoTime() - start;
        
        start = System.nanoTime();
        quickSort3Way(test2);
        long time3Way = System.nanoTime() - start;
        
        System.out.println("Quick Sort standard: " + timeStandard + "ns");
        System.out.println("Quick Sort 3-way: " + time3Way + "ns");
        System.out.println("Speedup con 3-way: " + String.format("%.2fx", (double)timeStandard / time3Way));
        
        System.out.println();
    }

    // ==================== HEAP SORT ====================

    /**
     * Heap Sort
     * Complessità: O(n log n) sempre
     * Spazio: O(1)
     * Stabile: No
     */
    public void heapSort(int[] arr) {
        int n = arr.length;
        
        // Costruisci max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Estrai elementi uno ad uno dall'heap
        for (int i = n - 1; i > 0; i--) {
            // Sposta root corrente alla fine
            swap(arr, 0, i);
            
            // Heapify sulla heap ridotta
            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    /**
     * Heap Sort con visualizzazione
     */
    public void heapSortVisualizzato(int[] arr) {
        int n = arr.length;
        System.out.println("Array iniziale: " + Arrays.toString(arr));
        
        // Costruisci max heap
        System.out.println("\nCostruzione Max Heap:");
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            System.out.println("Dopo heapify indice " + i + ": " + Arrays.toString(arr));
        }
        
        System.out.println("\nEstrazione elementi:");
        // Estrai elementi
        for (int i = n - 1; i > 0; i--) {
            System.out.println("Scambio root (" + arr[0] + ") con ultimo (" + arr[i] + ")");
            swap(arr, 0, i);
            heapify(arr, i, 0);
            System.out.println("Array: " + Arrays.toString(arr));
        }
    }

    public void heapSortDemo() {
        System.out.println("=== HEAP SORT ===");
        System.out.println("Complessità: O(n log n) tempo, O(1) spazio");
        System.out.println("Stabile: No\n");
        
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Heap Sort con visualizzazione:");
        heapSortVisualizzato(arr.clone());
        
        System.out.println();
    }

    // ==================== COUNTING SORT ====================

    /**
     * Counting Sort
     * Complessità: O(n + k) dove k è il range dei valori
     * Spazio: O(k)
     * Stabile: Sì
     * Funziona solo con interi non negativi
     */
    public void countingSort(int[] arr) {
        if (arr.length == 0) return;
        
        // Trova max
        int max = Arrays.stream(arr).max().getAsInt();
        
        // Array di conteggio
        int[] count = new int[max + 1];
        
        // Conta occorrenze
        for (int num : arr) {
            count[num]++;
        }
        
        // Modifica count per contenere posizioni
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        
        // Array output
        int[] output = new int[arr.length];
        
        // Costruisci output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        // Copia output in arr
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public void countingSortDemo() {
        System.out.println("=== COUNTING SORT ===");
        System.out.println("Complessità: O(n + k) tempo, O(k) spazio");
        System.out.println("Stabile: Sì");
        System.out.println("Funziona solo con interi non negativi\n");
        
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Array originale: " + Arrays.toString(arr));
        countingSort(arr);
        System.out.println("Dopo Counting Sort: " + Arrays.toString(arr));
        
        System.out.println();
    }

    // ==================== RADIX SORT ====================

    /**
     * Radix Sort
     * Complessità: O(d * (n + k)) dove d è numero di cifre
     * Spazio: O(n + k)
     * Stabile: Sì
     */
    public void radixSort(int[] arr) {
        if (arr.length == 0) return;
        
        // Trova max per determinare numero di cifre
        int max = Arrays.stream(arr).max().getAsInt();
        
        // Esegui counting sort per ogni cifra
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // cifre 0-9
        
        // Conta occorrenze
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        
        // Modifica count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Costruisci output
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        
        // Copia in arr
        System.arraycopy(output, 0, arr, 0, n);
    }

    public void radixSortDemo() {
        System.out.println("=== RADIX SORT ===");
        System.out.println("Complessità: O(d * (n + k)) tempo");
        System.out.println("Stabile: Sì\n");
        
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Array originale: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("Dopo Radix Sort: " + Arrays.toString(arr));
        
        System.out.println();
    }

    // ==================== BUCKET SORT ====================

    /**
     * Bucket Sort
     * Complessità: O(n + k) medio, O(n²) peggiore
     * Spazio: O(n + k)
     * Stabile: Sì (dipende dall'algoritmo per bucket)
     */
    public void bucketSort(int[] arr) {
        if (arr.length == 0) return;
        
        // Trova min e max
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
        
        // Numero di bucket
        int bucketCount = (int) Math.sqrt(arr.length);
        int range = (max - min) / bucketCount + 1;
        
        // Crea bucket
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Distribuisci elementi nei bucket
        for (int num : arr) {
            int bucketIndex = (num - min) / range;
            buckets[bucketIndex].add(num);
        }
        
        // Ordina ogni bucket e concatena
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public void bucketSortDemo() {
        System.out.println("=== BUCKET SORT ===");
        System.out.println("Complessità: O(n + k) medio, O(n²) peggiore");
        System.out.println("Stabile: Sì\n");
        
        int[] arr = {42, 32, 33, 52, 37, 47, 51};
        System.out.println("Array originale: " + Arrays.toString(arr));
        bucketSort(arr);
        System.out.println("Dopo Bucket Sort: " + Arrays.toString(arr));
        
        System.out.println();
    }

    // ==================== SHELL SORT ====================

    /**
     * Shell Sort (miglioramento di Insertion Sort)
     * Complessità: O(n log n) - O(n²) dipende dalla sequenza gap
     * Spazio: O(1)
     * Stabile: No
     */
    public void shellSort(int[] arr) {
        int n = arr.length;
        
        // Inizia con un gap grande, poi riducilo
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Esegui insertion sort con il gap corrente
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                
                arr[j] = temp;
            }
        }
    }

    /**
     * Shell Sort con sequenza di Knuth
     */
    public void shellSortKnuth(int[] arr) {
        int n = arr.length;
        
        // Calcola gap iniziale con sequenza di Knuth: 1, 4, 13, 40, 121, ...
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }
        
        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                
                arr[j] = temp;
            }
            gap /= 3;
        }
    }

    public void shellSortDemo() {
        System.out.println("=== SHELL SORT ===");
        System.out.println("Complessità: dipende dalla sequenza gap");
        System.out.println("Spazio: O(1)");
        System.out.println("Stabile: No\n");
        
        int[] arr = {12, 34, 54, 2, 3};
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        
        System.out.println("Array originale: " + Arrays.toString(arr));
        
        shellSort(arr1);
        System.out.println("Shell Sort (gap /= 2): " + Arrays.toString(arr1));
        
        shellSortKnuth(arr2);
        System.out.println("Shell Sort (Knuth): " + Arrays.toString(arr2));
        
        System.out.println();
    }

    // ==================== ANALISI COMPLESSITÀ ====================

    public void analisiComplessita() {
        System.out.println("=== ANALISI COMPLESSITÀ ALGORITMI ===\n");
        
        System.out.println("┌─────────────────┬──────────────┬──────────────┬──────────────┬────────┬──────────┐");
        System.out.println("│ Algoritmo       │ Caso Migliore│ Caso Medio   │ Caso Peggiore│ Spazio │ Stabile  │");
        System.out.println("├─────────────────┼──────────────┼──────────────┼──────────────┼────────┼──────────┤");
        System.out.println("│ Bubble Sort     │ O(n)         │ O(n²)        │ O(n²)        │ O(1)   │ Sì       │");
        System.out.println("│ Selection Sort  │ O(n²)        │ O(n²)        │ O(n²)        │ O(1)   │ No       │");
        System.out.println("│ Insertion Sort  │ O(n)         │ O(n²)        │ O(n²)        │ O(1)   │ Sì       │");
        System.out.println("│ Merge Sort      │ O(n log n)   │ O(n log n)   │ O(n log n)   │ O(n)   │ Sì       │");
        System.out.println("│ Quick Sort      │ O(n log n)   │ O(n log n)   │ O(n²)        │ O(log n)│ No      │");
        System.out.println("│ Heap Sort       │ O(n log n)   │ O(n log n)   │ O(n log n)   │ O(1)   │ No       │");
        System.out.println("│ Counting Sort   │ O(n + k)     │ O(n + k)     │ O(n + k)     │ O(k)   │ Sì       │");
        System.out.println("│ Radix Sort      │ O(d(n + k))  │ O(d(n + k))  │ O(d(n + k))  │ O(n+k) │ Sì       │");
        System.out.println("│ Bucket Sort     │ O(n + k)     │ O(n + k)     │ O(n²)        │ O(n+k) │ Sì       │");
        System.out.println("│ Shell Sort      │ O(n log n)   │ O(n^1.3)     │ O(n²)        │ O(1)   │ No       │");
        System.out.println("└─────────────────┴──────────────┴──────────────┴──────────────┴────────┴──────────┘");
        
        System.out.println("\nNote:");
        System.out.println("- k = range dei valori (Counting/Bucket Sort)");
        System.out.println("- d = numero di cifre (Radix Sort)");
        System.out.println("- Quick Sort: O(n²) evitabile con pivot randomizzato");
        System.out.println("- Bubble Sort: O(n) con ottimizzazione early termination");
        
        System.out.println();
    }

    // ==================== COMPARAZIONE PERFORMANCE ====================

    public void comparazionePerformance() {
        System.out.println("=== COMPARAZIONE PERFORMANCE ===\n");
        
        int[] sizes = {100, 1000, 5000};
        
        for (int size : sizes) {
            System.out.println("Array size: " + size);
            
            // Genera array casuale
            int[] original = new int[size];
            Random rand = new Random(12345); // seed fisso per consistenza
            for (int i = 0; i < size; i++) {
                original[i] = rand.nextInt(1000);
            }
            
            // Test algoritmi
            Map<String, Long> risultati = new LinkedHashMap<>();
            
            risultati.put("Bubble Sort", testAlgoritmo(original.clone(), this::bubbleSort));
            risultati.put("Selection Sort", testAlgoritmo(original.clone(), this::selectionSort));
            risultati.put("Insertion Sort", testAlgoritmo(original.clone(), this::insertionSort));
            risultati.put("Merge Sort", testAlgoritmo(original.clone(), this::mergeSort));
            risultati.put("Quick Sort", testAlgoritmo(original.clone(), this::quickSort));
            risultati.put("Heap Sort", testAlgoritmo(original.clone(), this::heapSort));
            risultati.put("Shell Sort", testAlgoritmo(original.clone(), this::shellSort));
            
            // Stampa risultati ordinati
            risultati.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(String.format("  %-20s: %6d ms", 
                    e.getKey(), e.getValue() / 1_000_000)));
            
            System.out.println();
        }
    }

    private long testAlgoritmo(int[] arr, Consumer<int[]> sortFunction) {
        long start = System.nanoTime();
        sortFunction.accept(arr);
        return System.nanoTime() - start;
    }

    // ==================== ORDINAMENTO OGGETTI ====================

    public void ordinamentoOggetti() {
        System.out.println("=== ORDINAMENTO OGGETTI CUSTOM ===\n");
        
        Studente[] studenti = {
            new Studente("Mario", 85, 22),
            new Studente("Luigi", 92, 21),
            new Studente("Peach", 88, 22),
            new Studente("Bowser", 75, 23),
            new Studente("Yoshi", 90, 21)
        };
        
        System.out.println("Array originale:");
        for (Studente s : studenti) {
            System.out.println("  " + s);
        }
        
        // Ordina per voto (decrescente)
        Studente[] perVoto = studenti.clone();
        Arrays.sort(perVoto, Comparator.comparing(Studente::getVoto).reversed());
        
        System.out.println("\nOrdinato per voto (decrescente):");
        for (Studente s : perVoto) {
            System.out.println("  " + s);
        }
        
        // Ordina per età poi per nome
        Studente[] perEtaNome = studenti.clone();
        Arrays.sort(perEtaNome, 
            Comparator.comparing(Studente::getEta)
                     .thenComparing(Studente::getNome));
        
        System.out.println("\nOrdinato per età, poi nome:");
        for (Studente s : perEtaNome) {
            System.out.println("  " + s);
        }
        
        // Ordina con algoritmo custom (Merge Sort)
        Studente[] customSort = studenti.clone();
        mergeSortOggetti(customSort, Comparator.comparing(Studente::getVoto));
        
        System.out.println("\nOrdinato con Merge Sort custom (per voto):");
        for (Studente s : customSort) {
            System.out.println("  " + s);
        }
        
        System.out.println();
    }

    private <T> void mergeSortOggetti(T[] arr, Comparator<T> comp) {
        if (arr.length < 2) return;
        mergeSortOggettiHelper(arr, 0, arr.length - 1, comp);
    }

    @SuppressWarnings("unchecked")
    private <T> void mergeSortOggettiHelper(T[] arr, int left, int right, Comparator<T> comp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortOggettiHelper(arr, left, mid, comp);
            mergeSortOggettiHelper(arr, mid + 1, right, comp);
            
            mergeOggetti(arr, left, mid, right, comp);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void mergeOggetti(T[] arr, int left, int mid, int right, Comparator<T> comp) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Object[] leftArr = new Object[n1];
        Object[] rightArr = new Object[n2];
        
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (comp.compare((T)leftArr[i], (T)rightArr[j]) <= 0) {
                arr[k] = (T)leftArr[i];
                i++;
            } else {
                arr[k] = (T)rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = (T)leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = (T)rightArr[j];
            j++;
            k++;
        }
    }

    // ==================== CASI SPECIALI ====================

    public void casiSpeciali() {
        System.out.println("=== CASI SPECIALI ===\n");
        
        // Array vuoto
        int[] empty = {};
        bubbleSort(empty);
        System.out.println("Array vuoto: " + Arrays.toString(empty));
        
        // Array con un elemento
        int[] single = {42};
        mergeSort(single);
        System.out.println("Array singolo: " + Arrays.toString(single));
        
        // Array già ordinato
        int[] sorted = {1, 2, 3, 4, 5};
        long start = System.nanoTime();
        insertionSort(sorted.clone());
        long timeInsertion = System.nanoTime() - start;
        
        start = System.nanoTime();
        quickSort(sorted.clone());
        long timeQuick = System.nanoTime() - start;
        
        System.out.println("\nArray già ordinato [1,2,3,4,5]:");
        System.out.println("  Insertion Sort: " + timeInsertion + "ns (ottimale)");
        System.out.println("  Quick Sort: " + timeQuick + "ns");
        
        // Array ordinato al contrario
        int[] reversed = {5, 4, 3, 2, 1};
        start = System.nanoTime();
        insertionSort(reversed.clone());
        long timeInsertionWorst = System.nanoTime() - start;
        
        start = System.nanoTime();
        mergeSort(reversed.clone());
        long timeMerge = System.nanoTime() - start;
        
        System.out.println("\nArray ordinato al contrario [5,4,3,2,1]:");
        System.out.println("  Insertion Sort: " + timeInsertionWorst + "ns (peggiore)");
        System.out.println("  Merge Sort: " + timeMerge + "ns (sempre O(n log n))");
        
        // Array con molti duplicati
        int[] duplicates = {3, 3, 3, 1, 1, 2, 2, 2, 2};
        System.out.println("\nArray con duplicati: " + Arrays.toString(duplicates));
        quickSort3Way(duplicates.clone());
        System.out.println("Quick Sort 3-way (ottimizzato per duplicati): " + 
            Arrays.toString(duplicates));
        
        // Array con valori tutti uguali
        int[] allSame = {7, 7, 7, 7, 7};
        countingSort(allSame);
        System.out.println("\nArray tutti uguali: " + Arrays.toString(allSame));
        
        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");
        
        System.out.println("1. SCEGLIERE L'ALGORITMO GIUSTO:");
        System.out.println("   - Array piccoli (<50): Insertion Sort");
        System.out.println("   - Array quasi ordinati: Insertion Sort o Bubble Sort ottimizzato");
        System.out.println("   - Array grandi: Quick Sort, Merge Sort, Heap Sort");
        System.out.println("   - Stabilità richiesta: Merge Sort");
        System.out.println("   - Spazio limitato: Heap Sort, Quick Sort");
        System.out.println("   - Interi in range limitato: Counting Sort, Radix Sort");
        
        System.out.println("\n2. OTTIMIZZAZIONI:");
        System.out.println("   - Quick Sort: usa median-of-three o randomized pivot");
        System.out.println("   - Quick Sort: switch a Insertion Sort per piccoli sub-array");
        System.out.println("   - Merge Sort: usa Insertion Sort per piccoli sub-array");
        System.out.println("   - Bubble Sort: early termination se già ordinato");
        
        System.out.println("\n3. STABILITÀ:");
        System.out.println("   - Stabile: Merge Sort, Insertion Sort, Bubble Sort");
        System.out.println("   - Non stabile: Quick Sort, Heap Sort, Selection Sort");
        System.out.println("   - Importa quando si ordinano oggetti con chiavi multiple");
        
        System.out.println("\n4. IN PRODUZIONE:");
        System.out.println("   - Java usa Dual-Pivot Quick Sort (Arrays.sort per primitivi)");
        System.out.println("   - Java usa Tim Sort (Collections.sort per oggetti)");
        System.out.println("   - Tim Sort è ibrido: Merge Sort + Insertion Sort");
        
        System.out.println("\n5. TESTING:");
        System.out.println("   - Testa con array vuoto, singolo elemento, duplicati");
        System.out.println("   - Testa casi migliore, medio, peggiore");
        System.out.println("   - Verifica stabilità se necessaria");
        
        System.out.println();
    }

    // ==================== CLASSE DI SUPPORTO ====================

    static class Studente {
        private String nome;
        private int voto;
        private int eta;

        public Studente(String nome, int voto, int eta) {
            this.nome = nome;
            this.voto = voto;
            this.eta = eta;
        }

        public String getNome() { return nome; }
        public int getVoto() { return voto; }
        public int getEta() { return eta; }

        @Override
        public String toString() {
            return String.format("%-10s (voto: %2d, età: %2d)", nome, voto, eta);
        }
    }
}
