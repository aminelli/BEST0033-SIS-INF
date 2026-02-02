package com.corso.samples.datatypes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Esempio completo e avanzato sull'uso di java.util.Collections e java.util.Arrays
 * Include:
 * - Tutti i metodi principali di Collections
 * - Tutti i metodi principali di Arrays
 * - Operazioni di ordinamento, ricerca, manipolazione
 * - Collection immutabili e sincronizzate
 * - Operazioni bulk
 * - Pattern avanzati e best practices
 */
public class CollectionsArraysDemo {

    public static void sample() {
        CollectionsArraysDemo demo = new CollectionsArraysDemo();
        
        System.out.println("=== JAVA.UTIL.COLLECTIONS & JAVA.UTIL.ARRAYS ===\n");
        
        // Collections
        demo.collectionsOrdinamento();
        demo.collectionsRicerca();
        demo.collectionsManipolazione();
        demo.collectionsRotazione();
        demo.collectionsRiempimento();
        demo.collectionsCopia();
        demo.collectionsFrequency();
        demo.collectionsMinMax();
        demo.collectionsReverse();
        demo.collectionsShuffle();
        demo.collectionsDisjoint();
        demo.collectionsImmutabili();
        demo.collectionsSincronizzate();
        demo.collectionsChecked();
        demo.collectionsFactory();
        
        // Arrays
        demo.arraysOrdinamento();
        demo.arraysRicerca();
        demo.arraysRiempimento();
        demo.arraysCopia();
        demo.arraysConfronti();
        demo.arraysConversione();
        demo.arraysStream();
        demo.arraysParallel();
        demo.arraysSetAll();
        demo.arraysDeepOperations();
        demo.arraysMismatch();
        
        // Casi d'uso avanzati
        demo.casiUsoAvanzati();
        demo.performanceComparison();
        demo.bestPractices();
    }

    // ==================== COLLECTIONS - ORDINAMENTO ====================

    public void collectionsOrdinamento() {
        System.out.println("=== COLLECTIONS - ORDINAMENTO ===");

        // sort() - ordinamento naturale
        List<Integer> numeri = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        Collections.sort(numeri);
        System.out.println("sort (naturale): " + numeri);

        // sort() con Comparator - ordinamento custom
        List<String> nomi = new ArrayList<>(Arrays.asList("Mario", "Luigi", "Peach", "Bowser"));
        Collections.sort(nomi, Comparator.reverseOrder());
        System.out.println("sort (reverseOrder): " + nomi);

        // sort() con comparator custom
        List<Persona> persone = Arrays.asList(
            new Persona("Mario", 35, 45000),
            new Persona("Luigi", 28, 38000),
            new Persona("Peach", 32, 52000)
        );
        Collections.sort(persone, Comparator.comparing(Persona::getEta));
        System.out.println("sort (per età): " + persone);

        // sort() multi-criterio
        Collections.sort(persone, 
            Comparator.comparing(Persona::getEta)
                .thenComparing(Persona::getStipendio));
        System.out.println("sort (età + stipendio): " + persone);

        System.out.println();
    }

    // ==================== COLLECTIONS - RICERCA ====================

    public void collectionsRicerca() {
        System.out.println("=== COLLECTIONS - RICERCA ===");

        List<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 9));
        Collections.sort(numeri); // deve essere ordinato per binarySearch

        // binarySearch() - ricerca binaria
        int index = Collections.binarySearch(numeri, 5);
        System.out.println("binarySearch(5): " + index);

        // binarySearch() con elemento non presente
        int index2 = Collections.binarySearch(numeri, 4);
        System.out.println("binarySearch(4) [non presente]: " + index2);
        System.out.println("  Insertion point: " + (-index2 - 1));

        // binarySearch() con Comparator
        List<String> nomi = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
        Collections.sort(nomi, String.CASE_INSENSITIVE_ORDER);
        int index3 = Collections.binarySearch(nomi, "charlie", String.CASE_INSENSITIVE_ORDER);
        System.out.println("binarySearch('charlie', case insensitive): " + index3);

        // indexOfSubList() - trova sottolista
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 3, 4, 6);
        List<Integer> subList = Arrays.asList(3, 4);
        int subIndex = Collections.indexOfSubList(lista, subList);
        System.out.println("indexOfSubList([3,4]): " + subIndex);

        // lastIndexOfSubList() - ultima occorrenza
        int lastSubIndex = Collections.lastIndexOfSubList(lista, subList);
        System.out.println("lastIndexOfSubList([3,4]): " + lastSubIndex);

        System.out.println();
    }

    // ==================== COLLECTIONS - MANIPOLAZIONE ====================

    public void collectionsManipolazione() {
        System.out.println("=== COLLECTIONS - MANIPOLAZIONE ===");

        // swap() - scambia elementi
        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.swap(lista, 0, 3);
        System.out.println("swap(0, 3): " + lista);

        // replaceAll() - rimpiazza tutti gli elementi
        List<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.replaceAll(numeri, 3, 99);
        System.out.println("replaceAll(3, 99): " + numeri);

        // addAll() - aggiunge multipli elementi
        List<String> nomi = new ArrayList<>();
        Collections.addAll(nomi, "Mario", "Luigi", "Peach");
        System.out.println("addAll: " + nomi);

        // reverse() verrà mostrato in sezione dedicata

        System.out.println();
    }

    // ==================== COLLECTIONS - ROTAZIONE ====================

    public void collectionsRotazione() {
        System.out.println("=== COLLECTIONS - ROTAZIONE ===");

        // rotate() - ruota elementi
        List<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Originale: " + numeri);
        
        Collections.rotate(numeri, 2);
        System.out.println("rotate(2): " + numeri);

        Collections.rotate(numeri, -2);
        System.out.println("rotate(-2): " + numeri);

        // Esempio pratico: rotazione circolare
        List<String> queue = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        System.out.println("\nCoda circolare:");
        for (int i = 0; i < 6; i++) {
            System.out.println("  Step " + i + ": " + queue);
            Collections.rotate(queue, 1);
        }

        System.out.println();
    }

    // ==================== COLLECTIONS - RIEMPIMENTO ====================

    public void collectionsRiempimento() {
        System.out.println("=== COLLECTIONS - RIEMPIMENTO ===");

        // fill() - riempie con un valore
        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        Collections.fill(lista, "X");
        System.out.println("fill('X'): " + lista);

        // nCopies() - crea lista con n copie
        List<String> copie = Collections.nCopies(5, "Hello");
        System.out.println("nCopies(5, 'Hello'): " + copie);

        // Uso pratico: inizializzazione con valori di default
        List<Integer> defaults = new ArrayList<>(Collections.nCopies(10, 0));
        System.out.println("Lista 10 zeri: " + defaults);

        System.out.println();
    }

    // ==================== COLLECTIONS - COPIA ====================

    public void collectionsCopia() {
        System.out.println("=== COLLECTIONS - COPIA ===");

        // copy() - copia elementi
        List<String> source = Arrays.asList("A", "B", "C");
        List<String> dest = new ArrayList<>(Arrays.asList("X", "Y", "Z", "W", "K"));
        
        System.out.println("Source: " + source);
        System.out.println("Dest before: " + dest);
        
        Collections.copy(dest, source);
        System.out.println("Dest after copy: " + dest);

        // Nota: dest deve avere size >= source.size()
        try {
            List<String> destSmall = new ArrayList<>(Arrays.asList("X", "Y"));
            Collections.copy(destSmall, source);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Errore: dest troppo piccola - " + e.getMessage());
        }

        System.out.println();
    }

    // ==================== COLLECTIONS - FREQUENCY ====================

    public void collectionsFrequency() {
        System.out.println("=== COLLECTIONS - FREQUENCY ===");

        List<String> lista = Arrays.asList("A", "B", "C", "A", "D", "A", "B");

        // frequency() - conta occorrenze
        int freqA = Collections.frequency(lista, "A");
        System.out.println("frequency('A'): " + freqA);

        int freqB = Collections.frequency(lista, "B");
        System.out.println("frequency('B'): " + freqB);

        // Trova elemento più frequente
        Set<String> unique = new HashSet<>(lista);
        String mostFrequent = null;
        int maxFreq = 0;
        
        for (String elem : unique) {
            int freq = Collections.frequency(lista, elem);
            if (freq > maxFreq) {
                maxFreq = freq;
                mostFrequent = elem;
            }
        }
        System.out.println("Elemento più frequente: " + mostFrequent + " (" + maxFreq + " volte)");

        // Crea mappa di frequenze
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String elem : unique) {
            frequencyMap.put(elem, Collections.frequency(lista, elem));
        }
        System.out.println("Mappa frequenze: " + frequencyMap);

        System.out.println();
    }

    // ==================== COLLECTIONS - MIN/MAX ====================

    public void collectionsMinMax() {
        System.out.println("=== COLLECTIONS - MIN/MAX ===");

        List<Integer> numeri = Arrays.asList(5, 2, 8, 1, 9, 3);

        // min() - trova minimo
        int min = Collections.min(numeri);
        System.out.println("min: " + min);

        // max() - trova massimo
        int max = Collections.max(numeri);
        System.out.println("max: " + max);

        // min/max con Comparator
        List<String> nomi = Arrays.asList("Alice", "Bob", "Charlie", "David");
        String minLen = Collections.min(nomi, Comparator.comparing(String::length));
        System.out.println("min (lunghezza): " + minLen);

        String maxLen = Collections.max(nomi, Comparator.comparing(String::length));
        System.out.println("max (lunghezza): " + maxLen);

        // min/max su oggetti custom
        List<Persona> persone = Arrays.asList(
            new Persona("Mario", 35, 45000),
            new Persona("Luigi", 28, 38000),
            new Persona("Peach", 32, 52000)
        );

        Persona personaPiuGiovane = Collections.min(persone, Comparator.comparing(Persona::getEta));
        System.out.println("Persona più giovane: " + personaPiuGiovane);

        Persona stipendioMax = Collections.max(persone, Comparator.comparing(Persona::getStipendio));
        System.out.println("Stipendio massimo: " + stipendioMax);

        System.out.println();
    }

    // ==================== COLLECTIONS - REVERSE ====================

    public void collectionsReverse() {
        System.out.println("=== COLLECTIONS - REVERSE ===");

        // reverse() - inverte ordine
        List<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Originale: " + numeri);
        
        Collections.reverse(numeri);
        System.out.println("Dopo reverse: " + numeri);

        // reverseOrder() - Comparator per ordine inverso
        List<String> nomi = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        Collections.sort(nomi, Collections.reverseOrder());
        System.out.println("sort con reverseOrder: " + nomi);

        // reverseOrder() con Comparator custom
        List<Persona> persone = new ArrayList<>(Arrays.asList(
            new Persona("Mario", 35, 45000),
            new Persona("Luigi", 28, 38000),
            new Persona("Peach", 32, 52000)
        ));
        Collections.sort(persone, Collections.reverseOrder(Comparator.comparing(Persona::getEta)));
        System.out.println("Persone ordinate per età (desc): " + persone);

        System.out.println();
    }

    // ==================== COLLECTIONS - SHUFFLE ====================

    public void collectionsShuffle() {
        System.out.println("=== COLLECTIONS - SHUFFLE ===");

        // shuffle() - mescola casualmente
        List<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Originale: " + numeri);
        
        Collections.shuffle(numeri);
        System.out.println("Dopo shuffle: " + numeri);

        // shuffle() con Random seed (riproducibile)
        List<Integer> numeri2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Random random = new Random(12345); // seed fisso
        Collections.shuffle(numeri2, random);
        System.out.println("Shuffle con seed 12345: " + numeri2);

        // Uso pratico: mescolare carte
        List<String> mazzo = new ArrayList<>();
        String[] semi = {"♠", "♥", "♦", "♣"};
        String[] valori = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for (String seme : semi) {
            for (String valore : valori) {
                mazzo.add(valore + seme);
            }
        }
        
        System.out.println("\nMazzo ordinato (prime 10): " + mazzo.subList(0, 10));
        Collections.shuffle(mazzo);
        System.out.println("Mazzo mescolato (prime 10): " + mazzo.subList(0, 10));
        
        // Distribuisci 5 carte
        System.out.println("Mano di 5 carte: " + mazzo.subList(0, 5));

        System.out.println();
    }

    // ==================== COLLECTIONS - DISJOINT ====================

    public void collectionsDisjoint() {
        System.out.println("=== COLLECTIONS - DISJOINT ===");

        // disjoint() - verifica se due collezioni sono disgiunte (nessun elemento in comune)
        List<Integer> lista1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> lista2 = Arrays.asList(6, 7, 8, 9, 10);
        List<Integer> lista3 = Arrays.asList(4, 5, 6, 7);

        boolean disjoint1 = Collections.disjoint(lista1, lista2);
        System.out.println("lista1 e lista2 disgiunte: " + disjoint1);

        boolean disjoint2 = Collections.disjoint(lista1, lista3);
        System.out.println("lista1 e lista3 disgiunte: " + disjoint2);

        // Uso pratico: verifica conflitti
        Set<String> giorniLavoro = new HashSet<>(Arrays.asList("Lunedì", "Martedì", "Mercoledì"));
        Set<String> giorniVacanza = new HashSet<>(Arrays.asList("Sabato", "Domenica"));
        Set<String> giorniConflitto = new HashSet<>(Arrays.asList("Mercoledì", "Giovedì"));

        System.out.println("\nLavoro e vacanza disgiunti: " + 
            Collections.disjoint(giorniLavoro, giorniVacanza));
        System.out.println("Lavoro e conflitto disgiunti: " + 
            Collections.disjoint(giorniLavoro, giorniConflitto));

        System.out.println();
    }

    // ==================== COLLECTIONS - IMMUTABILI ====================

    public void collectionsImmutabili() {
        System.out.println("=== COLLECTIONS - IMMUTABILI ===");

        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // unmodifiableList() - lista non modificabile
        List<String> listaImmutable = Collections.unmodifiableList(lista);
        System.out.println("Lista immutabile: " + listaImmutable);
        
        try {
            listaImmutable.add("D");
        } catch (UnsupportedOperationException e) {
            System.out.println("Errore add su immutabile: " + e.getClass().getSimpleName());
        }

        // Nota: la lista originale può ancora essere modificata
        lista.add("D");
        System.out.println("Lista originale modificata: " + lista);
        System.out.println("Lista immutabile riflette cambio: " + listaImmutable);

        // unmodifiableSet()
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> setImmutable = Collections.unmodifiableSet(set);
        System.out.println("\nSet immutabile: " + setImmutable);

        // unmodifiableMap()
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        Map<String, Integer> mapImmutable = Collections.unmodifiableMap(map);
        System.out.println("Map immutabile: " + mapImmutable);

        // unmodifiableCollection()
        Collection<String> collection = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        Collection<String> collectionImmutable = Collections.unmodifiableCollection(collection);
        System.out.println("Collection immutabile: " + collectionImmutable);

        // unmodifiableSortedSet()
        SortedSet<String> sortedSet = new TreeSet<>(Arrays.asList("C", "A", "B"));
        SortedSet<String> sortedSetImmutable = Collections.unmodifiableSortedSet(sortedSet);
        System.out.println("SortedSet immutabile: " + sortedSetImmutable);

        // unmodifiableSortedMap()
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("Z", 3);
        sortedMap.put("A", 1);
        sortedMap.put("M", 2);
        SortedMap<String, Integer> sortedMapImmutable = Collections.unmodifiableSortedMap(sortedMap);
        System.out.println("SortedMap immutabile: " + sortedMapImmutable);

        System.out.println();
    }

    // ==================== COLLECTIONS - SINCRONIZZATE ====================

    public void collectionsSincronizzate() {
        System.out.println("=== COLLECTIONS - SINCRONIZZATE ===");

        // synchronizedList() - lista thread-safe
        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> listaSynchr = Collections.synchronizedList(lista);
        System.out.println("Lista sincronizzata: " + listaSynchr);

        // synchronizedSet()
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> setSynchr = Collections.synchronizedSet(set);
        System.out.println("Set sincronizzato: " + setSynchr);

        // synchronizedMap()
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        Map<String, Integer> mapSynchr = Collections.synchronizedMap(map);
        System.out.println("Map sincronizzata: " + mapSynchr);

        // synchronizedCollection()
        Collection<String> collection = new ArrayList<>(Arrays.asList("X", "Y"));
        Collection<String> collectionSynchr = Collections.synchronizedCollection(collection);
        System.out.println("Collection sincronizzata: " + collectionSynchr);

        // synchronizedSortedSet()
        SortedSet<String> sortedSet = new TreeSet<>(Arrays.asList("C", "A", "B"));
        SortedSet<String> sortedSetSynchr = Collections.synchronizedSortedSet(sortedSet);
        System.out.println("SortedSet sincronizzato: " + sortedSetSynchr);

        // synchronizedSortedMap()
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        sortedMap.put("B", 2);
        SortedMap<String, Integer> sortedMapSynchr = Collections.synchronizedSortedMap(sortedMap);
        System.out.println("SortedMap sincronizzata: " + sortedMapSynchr);

        // Nota importante: iterazione deve essere sincronizzata manualmente
        System.out.println("\nIterazione sicura:");
        synchronized (listaSynchr) {
            for (String s : listaSynchr) {
                System.out.println("  " + s);
            }
        }

        System.out.println();
    }

    // ==================== COLLECTIONS - CHECKED ====================

    public void collectionsChecked() {
        System.out.println("=== COLLECTIONS - CHECKED ===");

        // checkedList() - verifica tipo a runtime
        List<String> lista = new ArrayList<>();
        List<String> listaChecked = Collections.checkedList(lista, String.class);
        
        listaChecked.add("Hello");
        listaChecked.add("World");
        System.out.println("Lista checked: " + listaChecked);

        // Previene inserimento di tipo errato
        @SuppressWarnings("rawtypes")
        List listaRaw = listaChecked;
        try {
            @SuppressWarnings("unchecked")
            boolean added = listaRaw.add(123); // tentativo di aggiungere Integer
        } catch (ClassCastException e) {
            System.out.println("Errore checked list: " + e.getMessage());
        }

        // checkedSet()
        Set<Integer> set = new HashSet<>();
        Set<Integer> setChecked = Collections.checkedSet(set, Integer.class);
        setChecked.add(42);
        System.out.println("Set checked: " + setChecked);

        // checkedMap()
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> mapChecked = Collections.checkedMap(map, String.class, Integer.class);
        mapChecked.put("answer", 42);
        System.out.println("Map checked: " + mapChecked);

        // checkedSortedSet()
        SortedSet<String> sortedSet = new TreeSet<>();
        SortedSet<String> sortedSetChecked = Collections.checkedSortedSet(sortedSet, String.class);
        sortedSetChecked.add("A");
        System.out.println("SortedSet checked: " + sortedSetChecked);

        // checkedSortedMap()
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        SortedMap<String, Integer> sortedMapChecked = 
            Collections.checkedSortedMap(sortedMap, String.class, Integer.class);
        sortedMapChecked.put("key", 100);
        System.out.println("SortedMap checked: " + sortedMapChecked);

        System.out.println();
    }

    // ==================== COLLECTIONS - FACTORY METHODS ====================

    public void collectionsFactory() {
        System.out.println("=== COLLECTIONS - FACTORY METHODS ===");

        // emptyList(), emptySet(), emptyMap()
        List<String> emptyList = Collections.emptyList();
        Set<Integer> emptySet = Collections.emptySet();
        Map<String, Object> emptyMap = Collections.emptyMap();
        System.out.println("Empty list: " + emptyList);
        System.out.println("Empty set: " + emptySet);
        System.out.println("Empty map: " + emptyMap);

        // singleton(), singletonList(), singletonMap()
        Set<String> singleton = Collections.singleton("Solo");
        List<Integer> singletonList = Collections.singletonList(42);
        Map<String, String> singletonMap = Collections.singletonMap("key", "value");
        System.out.println("\nSingleton set: " + singleton);
        System.out.println("Singleton list: " + singletonList);
        System.out.println("Singleton map: " + singletonMap);

        // Sono immutabili
        try {
            singletonList.add(99);
        } catch (UnsupportedOperationException e) {
            System.out.println("Singleton list immutabile: " + e.getClass().getSimpleName());
        }

        // enumeration() - converte a Enumeration (legacy)
        List<String> lista = Arrays.asList("A", "B", "C");
        Enumeration<String> enumeration = Collections.enumeration(lista);
        System.out.println("\nEnumeration:");
        while (enumeration.hasMoreElements()) {
            System.out.println("  " + enumeration.nextElement());
        }

        // list() - converte da Enumeration a ArrayList
        Enumeration<String> enum2 = Collections.enumeration(Arrays.asList("X", "Y", "Z"));
        ArrayList<String> listFromEnum = Collections.list(enum2);
        System.out.println("List da enumeration: " + listFromEnum);

        // asLifoQueue() - vista LIFO di Deque
        Deque<String> deque = new ArrayDeque<>(Arrays.asList("1", "2", "3"));
        Queue<String> lifoQueue = Collections.asLifoQueue(deque);
        System.out.println("\nLIFO Queue:");
        while (!lifoQueue.isEmpty()) {
            System.out.println("  " + lifoQueue.poll());
        }

        System.out.println();
    }

    // ==================== ARRAYS - ORDINAMENTO ====================

    public void arraysOrdinamento() {
        System.out.println("=== ARRAYS - ORDINAMENTO ===");

        // sort() - ordinamento naturale
        int[] numeri = {5, 2, 8, 1, 9, 3};
        Arrays.sort(numeri);
        System.out.println("sort (int[]): " + Arrays.toString(numeri));

        // sort() con range
        int[] numeri2 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(numeri2, 1, 4); // ordina da indice 1 a 3
        System.out.println("sort con range [1,4): " + Arrays.toString(numeri2));

        // sort() per oggetti
        String[] nomi = {"Mario", "Luigi", "Peach", "Bowser"};
        Arrays.sort(nomi);
        System.out.println("sort (String[]): " + Arrays.toString(nomi));

        // sort() con Comparator
        String[] nomi2 = {"Mario", "Luigi", "Peach", "Bowser"};
        Arrays.sort(nomi2, Comparator.reverseOrder());
        System.out.println("sort (reverseOrder): " + Arrays.toString(nomi2));

        // sort() oggetti custom
        Persona[] persone = {
            new Persona("Mario", 35, 45000),
            new Persona("Luigi", 28, 38000),
            new Persona("Peach", 32, 52000)
        };
        Arrays.sort(persone, Comparator.comparing(Persona::getEta));
        System.out.println("sort persone (età): " + Arrays.toString(persone));

        // parallelSort() - ordinamento parallelo (più veloce per array grandi)
        int[] numeriGrandi = new int[10000];
        for (int i = 0; i < numeriGrandi.length; i++) {
            numeriGrandi[i] = (int)(Math.random() * 1000);
        }
        
        long start = System.currentTimeMillis();
        Arrays.sort(numeriGrandi.clone());
        long timeSort = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        Arrays.parallelSort(numeriGrandi.clone());
        long timeParallelSort = System.currentTimeMillis() - start;
        
        System.out.println("\nSort vs ParallelSort (10000 elementi):");
        System.out.println("  sort: " + timeSort + "ms");
        System.out.println("  parallelSort: " + timeParallelSort + "ms");

        System.out.println();
    }

    // ==================== ARRAYS - RICERCA ====================

    public void arraysRicerca() {
        System.out.println("=== ARRAYS - RICERCA ===");

        // binarySearch() - ricerca binaria (array deve essere ordinato)
        int[] numeri = {1, 2, 3, 5, 8, 9};
        int index = Arrays.binarySearch(numeri, 5);
        System.out.println("binarySearch(5): " + index);

        // binarySearch() con elemento non presente
        int index2 = Arrays.binarySearch(numeri, 4);
        System.out.println("binarySearch(4) [non presente]: " + index2);
        System.out.println("  Insertion point: " + (-index2 - 1));

        // binarySearch() con range
        int[] numeri2 = {1, 2, 3, 5, 8, 9, 10, 15};
        int index3 = Arrays.binarySearch(numeri2, 2, 6, 8);
        System.out.println("binarySearch con range [2,6): " + index3);

        // binarySearch() per oggetti
        String[] nomi = {"Alice", "Bob", "Charlie", "David"};
        Arrays.sort(nomi); // deve essere ordinato
        int index4 = Arrays.binarySearch(nomi, "Charlie");
        System.out.println("binarySearch('Charlie'): " + index4);

        // binarySearch() con Comparator
        String[] nomi2 = {"alice", "bob", "charlie", "david"};
        Arrays.sort(nomi2, String.CASE_INSENSITIVE_ORDER);
        int index5 = Arrays.binarySearch(nomi2, "CHARLIE", String.CASE_INSENSITIVE_ORDER);
        System.out.println("binarySearch case insensitive: " + index5);

        System.out.println();
    }

    // ==================== ARRAYS - RIEMPIMENTO ====================

    public void arraysRiempimento() {
        System.out.println("=== ARRAYS - RIEMPIMENTO ===");

        // fill() - riempie array con valore
        int[] numeri = new int[5];
        Arrays.fill(numeri, 7);
        System.out.println("fill(7): " + Arrays.toString(numeri));

        // fill() con range
        int[] numeri2 = new int[10];
        Arrays.fill(numeri2, 0, 5, 1);
        Arrays.fill(numeri2, 5, 10, 9);
        System.out.println("fill con range: " + Arrays.toString(numeri2));

        // fill() per oggetti
        String[] nomi = new String[5];
        Arrays.fill(nomi, "Default");
        System.out.println("fill oggetti: " + Arrays.toString(nomi));

        // setAll() - riempie con funzione (Java 8+)
        int[] numeri3 = new int[10];
        Arrays.setAll(numeri3, i -> i * 2);
        System.out.println("setAll (i*2): " + Arrays.toString(numeri3));

        // setAll() con lambda complessa
        String[] stringhe = new String[5];
        Arrays.setAll(stringhe, i -> "Item-" + i);
        System.out.println("setAll (stringhe): " + Arrays.toString(stringhe));

        // parallelSetAll() - versione parallela
        int[] numeri4 = new int[10];
        Arrays.parallelSetAll(numeri4, i -> i * i);
        System.out.println("parallelSetAll (i²): " + Arrays.toString(numeri4));

        System.out.println();
    }

    // ==================== ARRAYS - COPIA ====================

    public void arraysCopia() {
        System.out.println("=== ARRAYS - COPIA ===");

        int[] originale = {1, 2, 3, 4, 5};

        // copyOf() - copia array
        int[] copia = Arrays.copyOf(originale, originale.length);
        System.out.println("copyOf: " + Arrays.toString(copia));

        // copyOf() con lunghezza maggiore (padding con 0)
        int[] copiaLunga = Arrays.copyOf(originale, 10);
        System.out.println("copyOf (lunghezza maggiore): " + Arrays.toString(copiaLunga));

        // copyOf() con lunghezza minore (troncamento)
        int[] copiaCorta = Arrays.copyOf(originale, 3);
        System.out.println("copyOf (lunghezza minore): " + Arrays.toString(copiaCorta));

        // copyOfRange() - copia range
        int[] range = Arrays.copyOfRange(originale, 1, 4);
        System.out.println("copyOfRange [1,4): " + Arrays.toString(range));

        // copyOfRange() con lunghezza maggiore
        int[] rangeLungo = Arrays.copyOfRange(originale, 2, 8);
        System.out.println("copyOfRange esteso: " + Arrays.toString(rangeLungo));

        // copyOf() per oggetti
        String[] nomi = {"Alice", "Bob", "Charlie"};
        String[] copiaNomi = Arrays.copyOf(nomi, nomi.length);
        System.out.println("copyOf (String[]): " + Arrays.toString(copiaNomi));

        // Nota: è shallow copy
        Persona[] persone = {new Persona("Mario", 35, 45000)};
        Persona[] copiaPersone = Arrays.copyOf(persone, persone.length);
        persone[0].nome = "Luigi"; // modifica originale
        System.out.println("Shallow copy - originale modificato: " + copiaPersone[0].nome);

        System.out.println();
    }

    // ==================== ARRAYS - CONFRONTI ====================

    public void arraysConfronti() {
        System.out.println("=== ARRAYS - CONFRONTI ===");

        // equals() - confronta array
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 4, 6};
        
        System.out.println("equals(arr1, arr2): " + Arrays.equals(arr1, arr2));
        System.out.println("equals(arr1, arr3): " + Arrays.equals(arr1, arr3));

        // equals() con range
        System.out.println("equals con range [0,3): " + 
            Arrays.equals(arr1, 0, 3, arr3, 0, 3));

        // equals() per oggetti
        String[] nomi1 = {"Alice", "Bob"};
        String[] nomi2 = {"Alice", "Bob"};
        System.out.println("equals (String[]): " + Arrays.equals(nomi1, nomi2));

        // deepEquals() - confronto profondo per array multidimensionali
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        int[][] matrix3 = {{1, 2}, {3, 5}};
        
        System.out.println("\ndeepEquals(matrix1, matrix2): " + Arrays.deepEquals(matrix1, matrix2));
        System.out.println("deepEquals(matrix1, matrix3): " + Arrays.deepEquals(matrix1, matrix3));

        // compare() - confronto lessicografico (Java 9+)
        int[] a1 = {1, 2, 3};
        int[] a2 = {1, 2, 4};
        int result = Arrays.compare(a1, a2);
        System.out.println("\ncompare([1,2,3], [1,2,4]): " + result + 
            (result < 0 ? " (primo minore)" : result > 0 ? " (primo maggiore)" : " (uguali)"));

        // compareUnsigned() - confronto senza segno
        int[] unsigned1 = {-1, -2};
        int[] unsigned2 = {1, 2};
        int resultUnsigned = Arrays.compareUnsigned(unsigned1, unsigned2);
        System.out.println("compareUnsigned([-1,-2], [1,2]): " + resultUnsigned);

        // mismatch() - trova primo indice diverso (Java 9+)
        int[] m1 = {1, 2, 3, 4, 5};
        int[] m2 = {1, 2, 9, 4, 5};
        int mismatchIndex = Arrays.mismatch(m1, m2);
        System.out.println("\nmismatch([1,2,3,4,5], [1,2,9,4,5]): indice " + mismatchIndex);

        int[] m3 = {1, 2, 3};
        int mismatchIndex2 = Arrays.mismatch(m1, m3);
        System.out.println("mismatch (lunghezze diverse): " + mismatchIndex2);

        System.out.println();
    }

    // ==================== ARRAYS - CONVERSIONE ====================

    public void arraysConversione() {
        System.out.println("=== ARRAYS - CONVERSIONE ===");

        // asList() - converte array a List
        String[] array = {"A", "B", "C", "D"};
        List<String> lista = Arrays.asList(array);
        System.out.println("asList: " + lista);

        // Nota: la lista è backed dall'array
        array[0] = "Z";
        System.out.println("Lista dopo modifica array: " + lista);

        // Nota: non si può modificare dimensione
        try {
            lista.add("E");
        } catch (UnsupportedOperationException e) {
            System.out.println("asList non supporta add: " + e.getClass().getSimpleName());
        }

        // Per lista modificabile, usare costruttore
        List<String> listaMod = new ArrayList<>(Arrays.asList("A", "B", "C"));
        listaMod.add("D");
        System.out.println("Lista modificabile: " + listaMod);

        // toString() - rappresentazione stringa
        int[] numeri = {1, 2, 3, 4, 5};
        System.out.println("\ntoString: " + Arrays.toString(numeri));

        // toString() per oggetti
        Persona[] persone = {
            new Persona("Mario", 35, 45000),
            new Persona("Luigi", 28, 38000)
        };
        System.out.println("toString (oggetti): " + Arrays.toString(persone));

        // deepToString() - per array multidimensionali
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("deepToString: " + Arrays.deepToString(matrix));

        // hashCode() e deepHashCode()
        int[] arr = {1, 2, 3};
        System.out.println("\nhashCode: " + Arrays.hashCode(arr));
        
        int[][] matrix2 = {{1, 2}, {3, 4}};
        System.out.println("deepHashCode: " + Arrays.deepHashCode(matrix2));

        System.out.println();
    }

    // ==================== ARRAYS - STREAM ====================

    public void arraysStream() {
        System.out.println("=== ARRAYS - STREAM ===");

        // stream() - crea stream da array
        int[] numeri = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        long count = Arrays.stream(numeri).count();
        System.out.println("stream count: " + count);

        // stream() con operazioni
        int sum = Arrays.stream(numeri).sum();
        System.out.println("stream sum: " + sum);

        double average = Arrays.stream(numeri).average().orElse(0);
        System.out.println("stream average: " + average);

        // stream() con filter e map
        int[] pariDoppi = Arrays.stream(numeri)
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .toArray();
        System.out.println("stream filter+map: " + Arrays.toString(pariDoppi));

        // stream() con range
        int sumRange = Arrays.stream(numeri, 2, 7).sum();
        System.out.println("stream con range [2,7): " + sumRange);

        // stream() per oggetti
        String[] nomi = {"Alice", "Bob", "Charlie", "David"};
        List<String> maiuscoli = Arrays.stream(nomi)
            .map(String::toUpperCase)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("stream oggetti: " + maiuscoli);

        // parallelStream() equivalent
        String[] nomiLunghi = Arrays.stream(nomi)
            .parallel()
            .filter(s -> s.length() > 3)
            .toArray(String[]::new);
        System.out.println("parallel stream: " + Arrays.toString(nomiLunghi));

        System.out.println();
    }

    // ==================== ARRAYS - PARALLEL ====================

    public void arraysParallel() {
        System.out.println("=== ARRAYS - PARALLEL ===");

        // parallelSort() - già visto in ordinamento

        // parallelSetAll() - già visto in riempimento

        // parallelPrefix() - applica operazione cumulativa
        int[] numeri = {1, 2, 3, 4, 5};
        Arrays.parallelPrefix(numeri, (a, b) -> a + b);
        System.out.println("parallelPrefix (sum): " + Arrays.toString(numeri));
        // Risultato: [1, 3, 6, 10, 15] (somma cumulativa)

        // parallelPrefix() con moltiplicazione
        int[] numeri2 = {1, 2, 3, 4, 5};
        Arrays.parallelPrefix(numeri2, (a, b) -> a * b);
        System.out.println("parallelPrefix (product): " + Arrays.toString(numeri2));
        // Risultato: [1, 2, 6, 24, 120] (prodotto cumulativo)

        // parallelPrefix() con range
        int[] numeri3 = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.parallelPrefix(numeri3, 2, 6, (a, b) -> a + b);
        System.out.println("parallelPrefix con range [2,6): " + Arrays.toString(numeri3));

        // parallelPrefix() per oggetti
        String[] parole = {"A", "B", "C", "D"};
        Arrays.parallelPrefix(parole, (a, b) -> a + b);
        System.out.println("parallelPrefix (String): " + Arrays.toString(parole));

        System.out.println();
    }

    // ==================== ARRAYS - SETALL ====================

    public void arraysSetAll() {
        System.out.println("=== ARRAYS - SETALL ===");

        // setAll() - già visto in riempimento, qui esempi avanzati

        // Genera sequenza Fibonacci
        int[] fibonacci = new int[10];
        int[] fib = {0, 1};
        Arrays.setAll(fibonacci, i -> {
            if (i < 2) return fib[i];
            int next = fib[0] + fib[1];
            fib[0] = fib[1];
            fib[1] = next;
            return next;
        });
        System.out.println("Fibonacci con setAll: " + Arrays.toString(fibonacci));

        // Genera numeri casuali
        double[] random = new double[5];
        Arrays.setAll(random, i -> Math.random());
        System.out.println("Random con setAll: " + Arrays.toString(random));

        // Genera pattern
        int[] pattern = new int[10];
        Arrays.setAll(pattern, i -> i % 3);
        System.out.println("Pattern con setAll: " + Arrays.toString(pattern));

        // Genera con indice
        String[] indexed = new String[5];
        Arrays.setAll(indexed, i -> "Index[" + i + "]=" + (i * 10));
        System.out.println("Indexed con setAll: " + Arrays.toString(indexed));

        System.out.println();
    }

    // ==================== ARRAYS - DEEP OPERATIONS ====================

    public void arraysDeepOperations() {
        System.out.println("=== ARRAYS - DEEP OPERATIONS ===");

        // Array multidimensionali
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        // deepEquals() - confronto profondo
        System.out.println("deepEquals(matrix1, matrix2): " + Arrays.deepEquals(matrix1, matrix2));
        System.out.println("deepEquals(matrix1, matrix3): " + Arrays.deepEquals(matrix1, matrix3));

        // deepToString() - rappresentazione stringa profonda
        System.out.println("\ndeepToString(matrix1):");
        System.out.println(Arrays.deepToString(matrix1));

        // deepHashCode() - hashcode profondo
        int hash1 = Arrays.deepHashCode(matrix1);
        int hash2 = Arrays.deepHashCode(matrix2);
        System.out.println("\ndeepHashCode(matrix1): " + hash1);
        System.out.println("deepHashCode(matrix2): " + hash2);
        System.out.println("Hash uguali: " + (hash1 == hash2));

        // Array 3D
        int[][][] cube = {
            {{1, 2}, {3, 4}},
            {{5, 6}, {7, 8}}
        };
        System.out.println("\nCubo 3D:");
        System.out.println(Arrays.deepToString(cube));

        // Array di oggetti nested
        Object[] nested = {
            new int[]{1, 2, 3},
            new String[]{"A", "B", "C"},
            new Object[]{
                new int[]{4, 5},
                "nested"
            }
        };
        System.out.println("\nArray nested:");
        System.out.println(Arrays.deepToString(nested));

        System.out.println();
    }

    // ==================== ARRAYS - MISMATCH ====================

    public void arraysMismatch() {
        System.out.println("=== ARRAYS - MISMATCH (Java 9+) ===");

        // mismatch() - trova primo indice diverso
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 9, 5};
        int index = Arrays.mismatch(arr1, arr2);
        System.out.println("mismatch: indice " + index + " (valore " + arr1[index] + " vs " + arr2[index] + ")");

        // Array uguali
        int[] arr3 = {1, 2, 3, 4, 5};
        int index2 = Arrays.mismatch(arr1, arr3);
        System.out.println("mismatch (uguali): " + index2);

        // Array di lunghezza diversa
        int[] arr4 = {1, 2, 3};
        int index3 = Arrays.mismatch(arr1, arr4);
        System.out.println("mismatch (lunghezze diverse): " + index3);

        // mismatch() con range
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr6 = {1, 2, 9, 4, 5, 6, 7};
        int index4 = Arrays.mismatch(arr5, 0, 5, arr6, 0, 5);
        System.out.println("mismatch con range [0,5): " + index4);

        // Per oggetti
        String[] nomi1 = {"Alice", "Bob", "Charlie"};
        String[] nomi2 = {"Alice", "Bob", "David"};
        int index5 = Arrays.mismatch(nomi1, nomi2);
        System.out.println("mismatch (String): indice " + index5);

        System.out.println();
    }

    // ==================== CASI D'USO AVANZATI ====================

    public void casiUsoAvanzati() {
        System.out.println("=== CASI D'USO AVANZATI ===");

        // 1. Implementare cache con Collections.synchronizedMap
        Map<String, String> cache = Collections.synchronizedMap(new HashMap<>());
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println("Cache thread-safe: " + cache);

        // 2. Unmodifiable view per API pubbliche
        List<String> privateList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> publicList = Collections.unmodifiableList(privateList);
        System.out.println("Public API (unmodifiable): " + publicList);

        // 3. Binary search con inserimento
        List<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        int newValue = 6;
        int insertIndex = Collections.binarySearch(sortedList, newValue);
        if (insertIndex < 0) {
            insertIndex = -insertIndex - 1;
            sortedList.add(insertIndex, newValue);
        }
        System.out.println("Lista dopo inserimento ordinato: " + sortedList);

        // 4. Rotazione di buffer circolare
        List<String> buffer = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
        Collections.rotate(buffer, 2); // ruota a destra
        System.out.println("Buffer circolare ruotato: " + buffer);

        // 5. Frequenza per analisi testo
        String testo = "il gatto è sul tetto il cane è nel giardino il gatto";
        List<String> parole = Arrays.asList(testo.split(" "));
        Map<String, Integer> frequenze = new HashMap<>();
        for (String parola : new HashSet<>(parole)) {
            frequenze.put(parola, Collections.frequency(parole, parola));
        }
        System.out.println("Frequenze parole: " + frequenze);

        // 6. Min/Max con comparator custom per trovare outliers
        List<Integer> dati = Arrays.asList(10, 12, 15, 11, 13, 100, 14, 12);
        int max = Collections.max(dati);
        double avg = dati.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Possibile outlier: " + max + " (media: " + avg + ")");

        // 7. Clonazione profonda con serialization helper
        int[][] original = {{1, 2}, {3, 4}};
        int[][] clone = Arrays.stream(original)
            .map(int[]::clone)
            .toArray(int[][]::new);
        original[0][0] = 99;
        System.out.println("Clone profondo - originale[0][0]: " + original[0][0] + 
                         ", clone[0][0]: " + clone[0][0]);

        // 8. Merge di array ordinati
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] merged = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
        Arrays.sort(merged);
        System.out.println("Array merged e ordinati: " + Arrays.toString(merged));

        System.out.println();
    }

    // ==================== PERFORMANCE COMPARISON ====================

    public void performanceComparison() {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        int size = 100000;
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int)(Math.random() * 10000);
        }

        // Sort vs ParallelSort
        int[] data1 = data.clone();
        int[] data2 = data.clone();

        long start = System.nanoTime();
        Arrays.sort(data1);
        long sortTime = System.nanoTime() - start;

        start = System.nanoTime();
        Arrays.parallelSort(data2);
        long parallelSortTime = System.nanoTime() - start;

        System.out.println("Arrays.sort: " + (sortTime / 1_000_000) + "ms");
        System.out.println("Arrays.parallelSort: " + (parallelSortTime / 1_000_000) + "ms");
        System.out.println("Speedup: " + String.format("%.2fx", (double)sortTime / parallelSortTime));

        // Binary search vs linear search
        Arrays.sort(data);
        int searchValue = data[size / 2];

        start = System.nanoTime();
        int index1 = Arrays.binarySearch(data, searchValue);
        long binarySearchTime = System.nanoTime() - start;

        start = System.nanoTime();
        int index2 = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == searchValue) {
                index2 = i;
                break;
            }
        }
        long linearSearchTime = System.nanoTime() - start;

        System.out.println("\nBinary search: " + binarySearchTime + "ns");
        System.out.println("Linear search: " + linearSearchTime + "ns");
        System.out.println("Speedup: " + String.format("%.0fx", (double)linearSearchTime / binarySearchTime));

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===");

        System.out.println("1. Usa Arrays.asList() per liste piccole immutabili");
        List<String> immutable = Arrays.asList("A", "B", "C");
        System.out.println("   " + immutable);

        System.out.println("\n2. Usa new ArrayList<>(Arrays.asList()) per liste modificabili");
        List<String> modifiable = new ArrayList<>(Arrays.asList("A", "B", "C"));
        modifiable.add("D");
        System.out.println("   " + modifiable);

        System.out.println("\n3. Ordina prima di usare binarySearch");
        List<Integer> numeri = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        Collections.sort(numeri);
        int index = Collections.binarySearch(numeri, 8);
        System.out.println("   Found at: " + index);

        System.out.println("\n4. Usa unmodifiable per API pubbliche");
        List<String> internal = new ArrayList<>(Arrays.asList("A", "B"));
        List<String> external = Collections.unmodifiableList(internal);
        System.out.println("   Public API: " + external.getClass().getSimpleName());

        System.out.println("\n5. Usa synchronized per thread-safety");
        List<String> threadSafe = Collections.synchronizedList(new ArrayList<>());
        System.out.println("   Thread-safe: " + threadSafe.getClass().getName());

        System.out.println("\n6. Preferisci Arrays.stream() per operazioni funzionali");
        int sum = Arrays.stream(new int[]{1, 2, 3, 4, 5}).sum();
        System.out.println("   Sum: " + sum);

        System.out.println("\n7. Usa parallelSort per array grandi (>10000 elementi)");
        System.out.println("   Arrays.parallelSort() per performance");

        System.out.println("\n8. deepEquals/deepToString per array multidimensionali");
        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println("   " + Arrays.deepToString(matrix));

        System.out.println("\n9. Usa Collections.frequency per statistiche");
        List<Integer> data = Arrays.asList(1, 2, 2, 3, 3, 3);
        System.out.println("   Freq di 3: " + Collections.frequency(data, 3));

        System.out.println("\n10. Collections.emptyList() invece di new ArrayList<>() per liste vuote");
        List<String> empty = Collections.emptyList();
        System.out.println("   Empty: " + empty.getClass().getSimpleName());

        System.out.println();
    }

    // ==================== CLASSE DI SUPPORTO ====================

    static class Persona {
        String nome;
        int eta;
        double stipendio;

        Persona(String nome, int eta, double stipendio) {
            this.nome = nome;
            this.eta = eta;
            this.stipendio = stipendio;
        }

        String getNome() { return nome; }
        int getEta() { return eta; }
        double getStipendio() { return stipendio; }

        @Override
        public String toString() {
            return String.format("%s(%d,€%.0f)", nome, eta, stipendio);
        }
    }
}
