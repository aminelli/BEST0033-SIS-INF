package com.corso.samples.datatypes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Dimostrazione completa delle Collection in Java
 * Gerarchia: Collection -> List, Set, Queue
 * Separata: Map (non estende Collection)
 */
public class CollectionDemo {

    /**
     * LIST - collezioni ordinate che ammettono duplicati
     * Interfaccia: List
     * Implementazioni: ArrayList, LinkedList, Vector, Stack
     */
    public void demoList() {
        System.out.println("=== LIST ===");
        
        // ArrayList - basato su array dinamico, accesso veloce per indice
        System.out.println("\n--- ArrayList ---");
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("JavaScript");
        arrayList.add("Python"); // duplicati ammessi
        arrayList.add(1, "C++"); // inserimento per indice
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Elemento all'indice 2: " + arrayList.get(2));
        arrayList.remove("Python"); // rimuove prima occorrenza
        System.out.println("Dopo remove 'Python': " + arrayList);
        
        // LinkedList - basato su lista doppiamente concatenata, inserimenti/rimozioni veloci
        System.out.println("\n--- LinkedList ---");
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        ((LinkedList<Integer>) linkedList).addFirst(5); // metodo specifico LinkedList
        ((LinkedList<Integer>) linkedList).addLast(40);
        System.out.println("LinkedList: " + linkedList);
        System.out.println("Primo: " + ((LinkedList<Integer>) linkedList).getFirst());
        System.out.println("Ultimo: " + ((LinkedList<Integer>) linkedList).getLast());
        
        // Vector - sincronizzato (thread-safe), legacy
        System.out.println("\n--- Vector ---");
        List<String> vector = new Vector<>();
        vector.add("A");
        vector.add("B");
        vector.add("C");
        System.out.println("Vector: " + vector);
        System.out.println("Capacità: " + ((Vector<String>) vector).capacity());
        
        // Stack - LIFO (Last In First Out), estende Vector
        System.out.println("\n--- Stack ---");
        Stack<String> stack = new Stack<>();
        stack.push("Primo");
        stack.push("Secondo");
        stack.push("Terzo");
        System.out.println("Stack: " + stack);
        System.out.println("Peek (top): " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Dopo pop: " + stack);
    }

    /**
     * SET - collezioni non ordinate che non ammettono duplicati
     * Interfaccia: Set
     * Implementazioni: HashSet, LinkedHashSet, TreeSet
     */
    public void demoSet() {
        System.out.println("\n=== SET ===");
        
        // HashSet - non ordinato, veloce, basato su hash table
        System.out.println("\n--- HashSet ---");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Mela");
        hashSet.add("Banana");
        hashSet.add("Arancia");
        hashSet.add("Mela"); // duplicato ignorato
        System.out.println("HashSet: " + hashSet);
        System.out.println("Contiene 'Mela': " + hashSet.contains("Mela"));
        System.out.println("Dimensione: " + hashSet.size());
        
        // LinkedHashSet - mantiene l'ordine di inserimento
        System.out.println("\n--- LinkedHashSet ---");
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(30);
        linkedHashSet.add(10);
        linkedHashSet.add(20);
        linkedHashSet.add(10); // duplicato ignorato
        System.out.println("LinkedHashSet (ordine inserimento): " + linkedHashSet);
        
        // TreeSet - ordinato naturalmente o con Comparator
        System.out.println("\n--- TreeSet ---");
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Delta");
        treeSet.add("Alpha");
        treeSet.add("Charlie");
        treeSet.add("Bravo");
        System.out.println("TreeSet (ordinato): " + treeSet);
        System.out.println("Primo: " + ((TreeSet<String>) treeSet).first());
        System.out.println("Ultimo: " + ((TreeSet<String>) treeSet).last());
        
        // Operazioni su Set
        System.out.println("\n--- Operazioni Set ---");
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        Set<Integer> unione = new HashSet<>(set1);
        unione.addAll(set2);
        System.out.println("Unione: " + unione);
        
        Set<Integer> intersezione = new HashSet<>(set1);
        intersezione.retainAll(set2);
        System.out.println("Intersezione: " + intersezione);
        
        Set<Integer> differenza = new HashSet<>(set1);
        differenza.removeAll(set2);
        System.out.println("Differenza: " + differenza);
    }

    /**
     * QUEUE - collezioni FIFO (First In First Out)
     * Interfaccia: Queue
     * Implementazioni: LinkedList, PriorityQueue, ArrayDeque
     */
    public void demoQueue() {
        System.out.println("\n=== QUEUE ===");
        
        // LinkedList come Queue
        System.out.println("\n--- Queue (LinkedList) ---");
        Queue<String> queue = new LinkedList<>();
        queue.offer("Primo");
        queue.offer("Secondo");
        queue.offer("Terzo");
        System.out.println("Queue: " + queue);
        System.out.println("Peek (testa): " + queue.peek());
        System.out.println("Poll (rimuovi): " + queue.poll());
        System.out.println("Dopo poll: " + queue);
        
        // PriorityQueue - ordinata secondo priorità (naturale o Comparator)
        System.out.println("\n--- PriorityQueue ---");
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(50);
        priorityQueue.offer(20);
        System.out.println("PriorityQueue (heap): " + priorityQueue);
        System.out.println("Poll (min): " + priorityQueue.poll());
        System.out.println("Poll (min): " + priorityQueue.poll());
        System.out.println("Restante: " + priorityQueue);
        
        // PriorityQueue con Comparator custom
        System.out.println("\n--- PriorityQueue Custom ---");
        Queue<String> priorityQueueCustom = new PriorityQueue<>(
            Comparator.comparingInt(String::length)
        );
        priorityQueueCustom.offer("Java");
        priorityQueueCustom.offer("C");
        priorityQueueCustom.offer("Python");
        priorityQueueCustom.offer("Go");
        while (!priorityQueueCustom.isEmpty()) {
            System.out.println("  Poll: " + priorityQueueCustom.poll());
        }
    }

    /**
     * DEQUE - Double Ended Queue (coda a doppia estremità)
     * Interfaccia: Deque
     * Implementazioni: ArrayDeque, LinkedList
     */
    public void demoDeque() {
        System.out.println("\n=== DEQUE ===");
        
        // ArrayDeque - implementazione efficiente con array circolare
        System.out.println("\n--- ArrayDeque ---");
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("Centro");
        deque.addFirst("Sinistra");
        deque.addLast("Destra");
        System.out.println("Deque: " + deque);
        System.out.println("Primo: " + deque.getFirst());
        System.out.println("Ultimo: " + deque.getLast());
        
        // Usato come Stack (LIFO)
        System.out.println("\n--- Deque come Stack ---");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        
        // Usato come Queue (FIFO)
        System.out.println("\n--- Deque come Queue ---");
        Deque<String> queueDeque = new ArrayDeque<>();
        queueDeque.offer("A");
        queueDeque.offer("B");
        queueDeque.offer("C");
        System.out.println("Queue: " + queueDeque);
        System.out.println("Poll: " + queueDeque.poll());
    }

    /**
     * MAP - mappa chiave-valore (non estende Collection)
     * Interfaccia: Map
     * Implementazioni: HashMap, LinkedHashMap, TreeMap, Hashtable
     */
    public void demoMap() {
        System.out.println("\n=== MAP ===");
        
        // HashMap - non ordinato, chiavi uniche, veloce
        System.out.println("\n--- HashMap ---");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);
        hashMap.put("Alice", 26); // sovrascrive valore precedente
        System.out.println("HashMap: " + hashMap);
        System.out.println("Età di Bob: " + hashMap.get("Bob"));
        System.out.println("Contiene 'David': " + hashMap.containsKey("David"));
        System.out.println("Contiene valore 30: " + hashMap.containsValue(30));
        
        // Iterazione su Map
        System.out.println("Iterazione:");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // LinkedHashMap - mantiene l'ordine di inserimento
        System.out.println("\n--- LinkedHashMap ---");
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("uno", "one");
        linkedHashMap.put("due", "two");
        linkedHashMap.put("tre", "three");
        System.out.println("LinkedHashMap (ordine inserimento): " + linkedHashMap);
        
        // TreeMap - ordinato per chiavi (naturalmente o con Comparator)
        System.out.println("\n--- TreeMap ---");
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Tre");
        treeMap.put(1, "Uno");
        treeMap.put(2, "Due");
        treeMap.put(5, "Cinque");
        treeMap.put(4, "Quattro");
        System.out.println("TreeMap (ordinato per chiave): " + treeMap);
        System.out.println("Prima chiave: " + ((TreeMap<Integer, String>) treeMap).firstKey());
        System.out.println("Ultima chiave: " + ((TreeMap<Integer, String>) treeMap).lastKey());
        
        // Hashtable - sincronizzato (thread-safe), legacy, no null
        System.out.println("\n--- Hashtable ---");
        Map<String, Double> hashtable = new Hashtable<>();
        hashtable.put("PI", 3.14159);
        hashtable.put("E", 2.71828);
        System.out.println("Hashtable: " + hashtable);
        
        // Metodi utili di Map
        System.out.println("\n--- Metodi Map ---");
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        
        // getOrDefault
        System.out.println("Get 'C' (default 0): " + map.getOrDefault("C", 0));
        
        // putIfAbsent
        map.putIfAbsent("C", 3);
        map.putIfAbsent("A", 10); // non sovrascrive
        System.out.println("Dopo putIfAbsent: " + map);
        
        // replace
        map.replace("B", 20);
        System.out.println("Dopo replace: " + map);
        
        // remove con valore
        map.remove("A", 1);
        System.out.println("Dopo remove: " + map);
        
        // compute
        map.compute("C", (k, v) -> v * 2);
        System.out.println("Dopo compute: " + map);
    }

    /**
     * ITERAZIONE - modi diversi di iterare sulle collezioni
     */
    public void demoIterazione() {
        System.out.println("\n=== ITERAZIONE ===");
        
        List<String> lista = Arrays.asList("A", "B", "C", "D");
        
        // For-each
        System.out.println("\n--- For-each ---");
        for (String elemento : lista) {
            System.out.print(elemento + " ");
        }
        System.out.println();
        
        // Iterator
        System.out.println("\n--- Iterator ---");
        Iterator<String> iterator = lista.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // ListIterator (solo per List)
        System.out.println("\n--- ListIterator ---");
        ListIterator<String> listIterator = lista.listIterator();
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            String elemento = listIterator.next();
            System.out.print(index + ":" + elemento + " ");
        }
        System.out.println();
        
        // forEach con lambda (Java 8+)
        System.out.println("\n--- forEach Lambda ---");
        lista.forEach(e -> System.out.print(e + " "));
        System.out.println();
        
        // Stream (Java 8+)
        System.out.println("\n--- Stream ---");
        lista.stream()
            .filter(s -> !s.equals("B"))
            .map(String::toLowerCase)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    /**
     * METODI UTILITY - Collections utility class
     */
    public void demoCollectionsUtility() {
        System.out.println("\n=== COLLECTIONS UTILITY ===");
        
        List<Integer> numeri = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("Lista originale: " + numeri);
        
        // sort
        Collections.sort(numeri);
        System.out.println("Dopo sort: " + numeri);
        
        // reverse
        Collections.reverse(numeri);
        System.out.println("Dopo reverse: " + numeri);
        
        // shuffle
        Collections.shuffle(numeri);
        System.out.println("Dopo shuffle: " + numeri);
        
        // max e min
        System.out.println("Max: " + Collections.max(numeri));
        System.out.println("Min: " + Collections.min(numeri));
        
        // frequency
        System.out.println("Frequenza di 1: " + Collections.frequency(numeri, 1));
        
        // binarySearch (lista deve essere ordinata)
        Collections.sort(numeri);
        System.out.println("Lista ordinata: " + numeri);
        System.out.println("BinarySearch di 5: " + Collections.binarySearch(numeri, 5));
        
        // fill
        Collections.fill(numeri, 0);
        System.out.println("Dopo fill(0): " + numeri);
        
        // Collezioni immutabili
        List<String> immutable = Collections.unmodifiableList(Arrays.asList("A", "B", "C"));
        System.out.println("Lista immutabile: " + immutable);
        // immutable.add("D"); // lancerebbe UnsupportedOperationException
        
        // Collezioni sincronizzate
        List<String> sync = Collections.synchronizedList(new ArrayList<>());
        sync.add("Thread-safe");
        System.out.println("Lista sincronizzata: " + sync);
        
        // Collezioni vuote
        List<String> empty = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, String> emptyMap = Collections.emptyMap();
        System.out.println("Collezioni vuote create");
        
        // singleton
        Set<String> singleton = Collections.singleton("Unico");
        System.out.println("Singleton set: " + singleton);
    }

    /**
     * COMPARAZIONE E ORDINAMENTO
     */
    public void demoComparazione() {
        System.out.println("\n=== COMPARAZIONE E ORDINAMENTO ===");
        
        // Oggetti Comparable
        List<Studente> studenti = Arrays.asList(
            new Studente("Mario", 25),
            new Studente("Luigi", 22),
            new Studente("Peach", 23)
        );
        
        System.out.println("\nPrima dell'ordinamento:");
        studenti.forEach(System.out::println);
        
        Collections.sort(studenti); // usa compareTo di Comparable
        System.out.println("\nDopo ordinamento (per età):");
        studenti.forEach(System.out::println);
        
        // Comparator custom
        studenti.sort(Comparator.comparing(Studente::getNome));
        System.out.println("\nOrdinamento per nome:");
        studenti.forEach(System.out::println);
        
        // Comparator multipli
        List<Studente> studenti2 = Arrays.asList(
            new Studente("Mario", 25),
            new Studente("Luigi", 25),
            new Studente("Peach", 23)
        );
        
        studenti2.sort(
            Comparator.comparing(Studente::getEta)
                      .thenComparing(Studente::getNome)
        );
        System.out.println("\nOrdinamento per età poi nome:");
        studenti2.forEach(System.out::println);
    }

    /**
     * Main per eseguire tutti gli esempi
     */
    public static void sample() {
        CollectionDemo demo = new CollectionDemo();
        
        demo.demoList();
        demo.demoSet();
        demo.demoQueue();
        demo.demoDeque();
        demo.demoMap();
        demo.demoIterazione();
        demo.demoCollectionsUtility();
        demo.demoComparazione();
        
        System.out.println("\n=== RIEPILOGO ===");
        System.out.println("LIST: ArrayList, LinkedList, Vector, Stack");
        System.out.println("SET: HashSet, LinkedHashSet, TreeSet");
        System.out.println("QUEUE: LinkedList, PriorityQueue");
        System.out.println("DEQUE: ArrayDeque, LinkedList");
        System.out.println("MAP: HashMap, LinkedHashMap, TreeMap, Hashtable");
    }
}

/**
 * Classe di supporto che implementa Comparable
 */
class Studente implements Comparable<Studente> {
    private String nome;
    private int eta;
    
    public Studente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getEta() {
        return eta;
    }
    
    @Override
    public int compareTo(Studente altro) {
        return Integer.compare(this.eta, altro.eta);
    }
    
    @Override
    public String toString() {
        return nome + " (" + eta + " anni)";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studente studente = (Studente) o;
        return eta == studente.eta && Objects.equals(nome, studente.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome, eta);
    }
}
