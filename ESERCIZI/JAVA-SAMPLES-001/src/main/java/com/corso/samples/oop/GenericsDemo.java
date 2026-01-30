package com.corso.samples.oop;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * DIMOSTRAZIONE COMPLETA E AVANZATA SUI GENERICS IN JAVA
 * 
 * Questo modulo copre tutti gli scenari possibili sull'uso dei generics:
 * 1. Classi generiche di base
 * 2. Metodi generici
 * 3. Bounded type parameters (upper e lower bounds)
 * 4. Wildcards (?, ? extends T, ? super T)
 * 5. Multiple type parameters
 * 6. Generic interfaces
 * 7. Inheritance e generics
 * 8. Covariance e contravariance (PECS)
 * 9. Type erasure e raw types
 * 10. Recursive type bounds
 * 11. Self-bounded types
 * 12. Bridge methods
 * 13. Restrizioni e limitazioni
 * 14. Best practices e pattern avanzati
 */
public class GenericsDemo {

    // ===========================================
    // SEZIONE 1: CLASSI GENERICHE DI BASE
    // ===========================================

    /**
     * Classe generica semplice con un singolo parametro di tipo
     */
    static class Box<T> {
        private T content;

        public Box(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Box[" + content + "]";
        }
    }

    /**
     * Classe generica con vincoli (bounded type parameter)
     */
    static class NumericBox<T extends Number> {
        private T value;

        public NumericBox(T value) {
            this.value = value;
        }

        public double doubleValue() {
            return value.doubleValue(); // Possiamo usare metodi di Number
        }

        public T getValue() {
            return value;
        }
    }

    // ===========================================
    // SEZIONE 2: MULTIPLE TYPE PARAMETERS
    // ===========================================

    /**
     * Classe generica con due parametri di tipo
     */
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    /**
     * Classe generica con tre parametri di tipo
     */
    static class Triple<A, B, C> {
        private A first;
        private B second;
        private C third;

        public Triple(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public A getFirst() { return first; }
        public B getSecond() { return second; }
        public C getThird() { return third; }
    }

    // ===========================================
    // SEZIONE 3: BOUNDED TYPE PARAMETERS
    // ===========================================

    /**
     * Upper bound: T deve essere una sottoclasse di Comparable
     */
    static class ComparableBox<T extends Comparable<T>> {
        private T value;

        public ComparableBox(T value) {
            this.value = value;
        }

        public boolean isGreaterThan(T other) {
            return value.compareTo(other) > 0;
        }

        public T max(T other) {
            return value.compareTo(other) >= 0 ? value : other;
        }
    }

    /**
     * Multiple bounds: T deve implementare sia Comparable che Serializable
     */
    static class MultiBoundBox<T extends Comparable<T> & Serializable> {
        private T value;

        public MultiBoundBox(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        // Possiamo usare metodi di entrambe le interfacce
        public int compareTo(T other) {
            return value.compareTo(other);
        }
    }

    // ===========================================
    // SEZIONE 4: METODI GENERICI
    // ===========================================

    /**
     * Metodo generico statico - il parametro di tipo è dichiarato prima del tipo di ritorno
     */
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Metodo generico con tipo di ritorno generico
     */
    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    /**
     * Metodo generico con bounded type parameter
     */
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array vuoto o null");
        }
        
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Metodo generico con multiple type parameters
     */
    public static <K, V> Pair<V, K> swap(Pair<K, V> pair) {
        return new Pair<>(pair.getValue(), pair.getKey());
    }

    /**
     * Metodo generico che ritorna un tipo diverso dal parametro
     */
    public static <T, R> R transform(T input, Function<T, R> transformer) {
        return transformer.apply(input);
    }

    // ===========================================
    // SEZIONE 5: WILDCARDS
    // ===========================================

    /**
     * Unbounded wildcard - può accettare qualsiasi tipo
     */
    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Upper bounded wildcard - accetta Number e sue sottoclassi
     * Usato per PRODUTTORI (Producer Extends)
     */
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    /**
     * Lower bounded wildcard - accetta Integer e sue superclassi
     * Usato per CONSUMATORI (Consumer Super)
     */
    public static void addIntegers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i); // Possiamo aggiungere Integer
        }
    }

    /**
     * Esempio di PECS (Producer Extends, Consumer Super)
     */
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T element : source) {
            destination.add(element);
        }
    }

    // ===========================================
    // SEZIONE 6: GENERIC INTERFACES
    // ===========================================

    /**
     * Interfaccia generica semplice
     */
    interface Container<T> {
        void add(T item);
        T get(int index);
        int size();
    }

    /**
     * Implementazione di interfaccia generica con tipo concreto
     */
    static class StringContainer implements Container<String> {
        private List<String> items = new ArrayList<>();

        @Override
        public void add(String item) {
            items.add(item);
        }

        @Override
        public String get(int index) {
            return items.get(index);
        }

        @Override
        public int size() {
            return items.size();
        }
    }

    /**
     * Implementazione di interfaccia generica mantenendo il tipo generico
     */
    static class GenericContainer<E> implements Container<E> {
        private List<E> items = new ArrayList<>();

        @Override
        public void add(E item) {
            items.add(item);
        }

        @Override
        public E get(int index) {
            return items.get(index);
        }

        @Override
        public int size() {
            return items.size();
        }
    }

    /**
     * Interfaccia generica con multiple type parameters
     */
    interface Transformer<T, R> {
        R transform(T input);
    }

    /**
     * Interfaccia generica con bounded type parameter
     */
    interface Calculator<T extends Number> {
        T add(T a, T b);
        T multiply(T a, T b);
    }

    // ===========================================
    // SEZIONE 7: INHERITANCE CON GENERICS
    // ===========================================

    /**
     * Classe base generica
     */
    static class Animal<T> {
        private T id;
        private String name;

        public Animal(T id, String name) {
            this.id = id;
            this.name = name;
        }

        public T getId() { return id; }
        public String getName() { return name; }
    }

    /**
     * Sottoclasse che mantiene il tipo generico
     */
    static class Dog<T> extends Animal<T> {
        private String breed;

        public Dog(T id, String name, String breed) {
            super(id, name);
            this.breed = breed;
        }

        public String getBreed() { return breed; }
    }

    /**
     * Sottoclasse che specifica un tipo concreto
     */
    static class Cat extends Animal<Integer> {
        private int livesRemaining;

        public Cat(Integer id, String name, int livesRemaining) {
            super(id, name);
            this.livesRemaining = livesRemaining;
        }

        public int getLivesRemaining() { return livesRemaining; }
    }

    /**
     * Sottoclasse che aggiunge un nuovo parametro di tipo
     */
    static class Bird<T, V> extends Animal<T> {
        private V wingSpan;

        public Bird(T id, String name, V wingSpan) {
            super(id, name);
            this.wingSpan = wingSpan;
        }

        public V getWingSpan() { return wingSpan; }
    }

    // ===========================================
    // SEZIONE 8: COVARIANCE E CONTRAVARIANCE
    // ===========================================

    /**
     * Esempio di covariance con arrays (unsafe)
     */
    public static void demonstrateArrayCovariance() {
        // Gli array sono covarianti (possibile ma unsafe)
        Number[] numbers = new Integer[10]; // Compila
        // numbers[0] = 3.14; // ArrayStoreException a runtime!
    }

    /**
     * Esempio di invariance con generics (safe)
     */
    public static void demonstrateGenericInvariance() {
        // List<Number> numbers = new ArrayList<Integer>(); // NON compila
        // I generics sono invarianti per sicurezza dei tipi
    }

    /**
     * Uso di wildcards per simulare covariance (safe)
     */
    public static void demonstrateWildcardCovariance() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<? extends Number> numbers = integers; // OK
        // Number num = numbers.get(0); // OK - possiamo leggere
        // numbers.add(42); // NON compila - non possiamo scrivere
    }

    /**
     * Uso di wildcards per simulare contravariance (safe)
     */
    public static void demonstrateWildcardContravariance() {
        List<Number> numbers = new ArrayList<>();
        List<? super Integer> integers = numbers; // OK
        integers.add(42); // OK - possiamo scrivere Integer
        // Integer num = integers.get(0); // NON compila - non sappiamo il tipo esatto
        Object obj = integers.get(0); // OK - possiamo leggere come Object
    }

    // ===========================================
    // SEZIONE 9: RECURSIVE TYPE BOUNDS
    // ===========================================

    /**
     * Recursive type bound - T deve essere comparabile a se stesso
     */
    static class SelfComparable<T extends Comparable<T>> {
        private T value;

        public SelfComparable(T value) {
            this.value = value;
        }

        public boolean isLessThan(SelfComparable<T> other) {
            return value.compareTo(other.value) < 0;
        }
    }

    /**
     * Enum comparison pattern - il pattern F-bounded polymorphism
     */
    interface Comparable2<T extends Comparable2<T>> {
        int compareTo(T other);
    }

    /**
     * Implementazione del pattern Comparable2
     */
    static class Person implements Comparable2<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age);
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    // ===========================================
    // SEZIONE 10: SELF-BOUNDED TYPES (Builder Pattern)
    // ===========================================

    /**
     * Self-bounded type per il Builder pattern
     * Permette method chaining type-safe nelle sottoclassi
     */
    static class Builder<T extends Builder<T>> {
        private String name;
        private int value;

        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setValue(int value) {
            this.value = value;
            return self();
        }

        public String getName() { return name; }
        public int getValue() { return value; }
    }

    /**
     * Sottoclasse che estende il builder mantenendo il type-safe chaining
     */
    static class ExtendedBuilder extends Builder<ExtendedBuilder> {
        private String extra;

        public ExtendedBuilder setExtra(String extra) {
            this.extra = extra;
            return this;
        }

        public String getExtra() { return extra; }
    }

    // ===========================================
    // SEZIONE 11: TYPE ERASURE E RAW TYPES
    // ===========================================

    /**
     * Dimostra il concetto di type erasure
     */
    public static void demonstrateTypeErasure() {
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(42);

        // A runtime, entrambi sono dello stesso tipo Box (type erasure)
        System.out.println("Stesso tipo? " + 
            (stringBox.getClass() == intBox.getClass())); // true

        // Raw type (sconsigliato - perde type safety)
        @SuppressWarnings("rawtypes")
        Box rawBox = new Box("Raw");
        
        @SuppressWarnings("unchecked")
        String content = (String) rawBox.getContent(); // Cast necessario
    }

    // ===========================================
    // SEZIONE 12: GENERIC CONSTRUCTORS
    // ===========================================

    /**
     * Classe con costruttore generico
     */
    static class GenericConstructorDemo {
        private Object value;

        // Costruttore generico - può accettare qualsiasi tipo
        public <T> GenericConstructorDemo(T value) {
            this.value = value;
        }

        // Costruttore generico con bounded type
        public <T extends Number> GenericConstructorDemo(T value, boolean isNumber) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }

    // ===========================================
    // SEZIONE 13: GENERIC METHODS CON COLLECTIONS
    // ===========================================

    /**
     * Filtra una lista usando un predicato
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Mappa una lista usando una funzione
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T element : list) {
            result.add(mapper.apply(element));
        }
        return result;
    }

    /**
     * Riduce una lista a un singolo valore
     */
    public static <T> T reduce(List<T> list, T identity, 
                               java.util.function.BinaryOperator<T> accumulator) {
        T result = identity;
        for (T element : list) {
            result = accumulator.apply(result, element);
        }
        return result;
    }

    /**
     * Trova il primo elemento che soddisfa un predicato
     */
    public static <T> Optional<T> findFirst(List<T> list, Predicate<T> predicate) {
        for (T element : list) {
            if (predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    // ===========================================
    // SEZIONE 14: GENERIC STACK IMPLEMENTATION
    // ===========================================

    /**
     * Implementazione generica di uno stack
     */
    static class Stack<E> {
        private List<E> elements = new ArrayList<>();

        public void push(E element) {
            elements.add(element);
        }

        public E pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return elements.remove(elements.size() - 1);
        }

        public E peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        public int size() {
            return elements.size();
        }

        /**
         * Metodo che usa wildcard per accettare stack di sottotipi
         */
        public void pushAll(Iterable<? extends E> src) {
            for (E e : src) {
                push(e);
            }
        }

        /**
         * Metodo che usa wildcard per popolare collezioni di supertipi
         */
        public void popAll(Collection<? super E> dst) {
            while (!isEmpty()) {
                dst.add(pop());
            }
        }
    }

    // ===========================================
    // SEZIONE 15: GENERIC TREE STRUCTURE
    // ===========================================

    /**
     * Nodo generico per struttura ad albero
     */
    static class TreeNode<T> {
        private T data;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }

        public T getData() { return data; }
        public TreeNode<T> getLeft() { return left; }
        public TreeNode<T> getRight() { return right; }

        public void setLeft(TreeNode<T> left) { this.left = left; }
        public void setRight(TreeNode<T> right) { this.right = right; }

        /**
         * Visita in-order dell'albero
         */
        public void inOrderTraversal(List<T> result) {
            if (left != null) {
                left.inOrderTraversal(result);
            }
            result.add(data);
            if (right != null) {
                right.inOrderTraversal(result);
            }
        }
    }

    /**
     * Albero binario di ricerca generico
     */
    static class BinarySearchTree<T extends Comparable<T>> {
        private TreeNode<T> root;

        public void insert(T value) {
            root = insertRec(root, value);
        }

        private TreeNode<T> insertRec(TreeNode<T> node, T value) {
            if (node == null) {
                return new TreeNode<>(value);
            }

            int cmp = value.compareTo(node.getData());
            if (cmp < 0) {
                node.setLeft(insertRec(node.getLeft(), value));
            } else if (cmp > 0) {
                node.setRight(insertRec(node.getRight(), value));
            }

            return node;
        }

        public boolean contains(T value) {
            return containsRec(root, value);
        }

        private boolean containsRec(TreeNode<T> node, T value) {
            if (node == null) {
                return false;
            }

            int cmp = value.compareTo(node.getData());
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                return containsRec(node.getLeft(), value);
            } else {
                return containsRec(node.getRight(), value);
            }
        }

        public List<T> inOrder() {
            List<T> result = new ArrayList<>();
            if (root != null) {
                root.inOrderTraversal(result);
            }
            return result;
        }
    }

    // ===========================================
    // SEZIONE 16: GENERIC CACHE IMPLEMENTATION
    // ===========================================

    /**
     * Cache generica con tipo chiave e valore
     */
    static class Cache<K, V> {
        private Map<K, V> cache = new HashMap<>();
        private int maxSize;

        public Cache(int maxSize) {
            this.maxSize = maxSize;
        }

        public void put(K key, V value) {
            if (cache.size() >= maxSize && !cache.containsKey(key)) {
                // Rimuovi il primo elemento (strategia semplice)
                K firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
            cache.put(key, value);
        }

        public Optional<V> get(K key) {
            return Optional.ofNullable(cache.get(key));
        }

        public void clear() {
            cache.clear();
        }

        public int size() {
            return cache.size();
        }
    }

    // ===========================================
    // SEZIONE 17: TYPE TOKENS E REIFICATION
    // ===========================================

    /**
     * Type token pattern per preservare informazioni sul tipo a runtime
     */
    static class TypeToken<T> {
        private final Class<T> type;

        public TypeToken(Class<T> type) {
            this.type = type;
        }

        public Class<T> getType() {
            return type;
        }

        @SuppressWarnings("unchecked")
        public T cast(Object obj) {
            return (T) type.cast(obj);
        }

        public T newInstance() throws Exception {
            return type.getDeclaredConstructor().newInstance();
        }
    }

    /**
     * Generic factory usando type tokens
     */
    static class GenericFactory<T> {
        private final Class<T> type;

        public GenericFactory(Class<T> type) {
            this.type = type;
        }

        public T create() throws Exception {
            return type.getDeclaredConstructor().newInstance();
        }

        public boolean isInstance(Object obj) {
            return type.isInstance(obj);
        }
    }

    // ===========================================
    // SEZIONE 18: RESTRIZIONI E LIMITAZIONI
    // ===========================================

    /**
     * Dimostra le limitazioni dei generics in Java
     */
    public static void demonstrateRestrictions() {
        // 1. Non si possono creare istanze di tipi generici
        // T obj = new T(); // ERRORE DI COMPILAZIONE

        // 2. Non si possono creare array di tipi parametrizzati
        // List<String>[] arrayOfLists = new List<String>[10]; // ERRORE

        // 3. Non si possono usare tipi primitivi come parametri di tipo
        // Box<int> intBox = new Box<>(5); // ERRORE - usare Box<Integer>

        // 4. Non si può usare instanceof con tipi parametrizzati
        // if (obj instanceof List<String>) { } // ERRORE
        // if (obj instanceof List<?>) { } // OK

        // 5. Non si possono avere metodi statici con parametri di tipo della classe
        // static class BadGeneric<T> {
        //     static T value; // ERRORE
        //     static T getValue() { return value; } // ERRORE
        // }

        // 6. Non si possono fare cast a tipi parametrizzati (unchecked warning)
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) new ArrayList<>(); // Warning

        // 7. Non si possono creare eccezioni generiche
        // class GenericException<T> extends Exception { } // ERRORE
    }

    // ===========================================
    // SEZIONE 19: VARIANCE ANNOTATION PATTERN
    // ===========================================

    /**
     * Producer - usa extends per output
     */
    static class Producer<T> {
        private List<T> items = new ArrayList<>();

        public void produce(T item) {
            items.add(item);
        }

        // Ritorna una lista che può essere usata come lista di supertipo
        public List<? extends T> getItems() {
            return items;
        }
    }

    /**
     * Consumer - usa super per input
     */
    static class Consumer<T> {
        private List<T> items = new ArrayList<>();

        // Accetta una lista che può contenere T o supertipo
        public void consume(List<? extends T> source) {
            items.addAll(source);
        }

        public List<T> getItems() {
            return new ArrayList<>(items);
        }
    }

    // ===========================================
    // SEZIONE 20: ADVANCED PATTERNS
    // ===========================================

    /**
     * Generic visitor pattern
     */
    interface Visitor<T> {
        void visit(T element);
    }

    static class ElementProcessor<T> {
        public void process(List<T> elements, Visitor<T> visitor) {
            for (T element : elements) {
                visitor.visit(element);
            }
        }
    }

    /**
     * Generic strategy pattern
     */
    interface Strategy<T, R> {
        R execute(T input);
    }

    static class Context<T, R> {
        private Strategy<T, R> strategy;

        public Context(Strategy<T, R> strategy) {
            this.strategy = strategy;
        }

        public void setStrategy(Strategy<T, R> strategy) {
            this.strategy = strategy;
        }

        public R executeStrategy(T input) {
            return strategy.execute(input);
        }
    }

    /**
     * Generic chain of responsibility
     */
    static abstract class Handler<T> {
        protected Handler<T> next;

        public Handler<T> setNext(Handler<T> next) {
            this.next = next;
            return next;
        }

        public abstract boolean handle(T request);

        protected boolean handleNext(T request) {
            if (next != null) {
                return next.handle(request);
            }
            return false;
        }
    }

    /**
     * Generic option/result type (monad pattern)
     */
    static class Result<T, E> {
        private final T value;
        private final E error;
        private final boolean success;

        private Result(T value, E error, boolean success) {
            this.value = value;
            this.error = error;
            this.success = success;
        }

        public static <T, E> Result<T, E> success(T value) {
            return new Result<>(value, null, true);
        }

        public static <T, E> Result<T, E> failure(E error) {
            return new Result<>(null, error, false);
        }

        public boolean isSuccess() {
            return success;
        }

        public T getValue() {
            if (!success) {
                throw new IllegalStateException("Nessun valore in un risultato fallito");
            }
            return value;
        }

        public E getError() {
            if (success) {
                throw new IllegalStateException("Nessun errore in un risultato success");
            }
            return error;
        }

        public <R> Result<R, E> map(Function<T, R> mapper) {
            if (success) {
                return Result.success(mapper.apply(value));
            }
            return Result.failure(error);
        }

        public <R> Result<R, E> flatMap(Function<T, Result<R, E>> mapper) {
            if (success) {
                return mapper.apply(value);
            }
            return Result.failure(error);
        }
    }

    // ===========================================
    // SEZIONE 21: ESEMPI PRATICI COMPLETI
    // ===========================================

    /**
     * Repository pattern generico
     */
    interface Repository<T, ID> {
        void save(T entity);
        Optional<T> findById(ID id);
        List<T> findAll();
        void delete(ID id);
        void update(T entity);
    }

    /**
     * Implementazione in-memory del repository
     */
    static class InMemoryRepository<T, ID> implements Repository<T, ID> {
        private Map<ID, T> storage = new HashMap<>();
        private Function<T, ID> idExtractor;

        public InMemoryRepository(Function<T, ID> idExtractor) {
            this.idExtractor = idExtractor;
        }

        @Override
        public void save(T entity) {
            ID id = idExtractor.apply(entity);
            storage.put(id, entity);
        }

        @Override
        public Optional<T> findById(ID id) {
            return Optional.ofNullable(storage.get(id));
        }

        @Override
        public List<T> findAll() {
            return new ArrayList<>(storage.values());
        }

        @Override
        public void delete(ID id) {
            storage.remove(id);
        }

        @Override
        public void update(T entity) {
            ID id = idExtractor.apply(entity);
            if (storage.containsKey(id)) {
                storage.put(id, entity);
            } else {
                throw new IllegalArgumentException("Entità non trovata");
            }
        }
    }

    /**
     * Specification pattern generico
     */
    interface Specification<T> {
        boolean isSatisfiedBy(T item);
        
        default Specification<T> and(Specification<T> other) {
            return item -> this.isSatisfiedBy(item) && other.isSatisfiedBy(item);
        }
        
        default Specification<T> or(Specification<T> other) {
            return item -> this.isSatisfiedBy(item) || other.isSatisfiedBy(item);
        }
        
        default Specification<T> not() {
            return item -> !this.isSatisfiedBy(item);
        }
    }

    // ===========================================
    // SEZIONE 22: RIEPILOGO E BEST PRACTICES
    // ===========================================

    /**
     * BEST PRACTICES PER L'USO DEI GENERICS:
     * 
     * 1. PREFERIRE I GENERICS AI RAW TYPES
     *    - List<String> invece di List (type safety)
     * 
     * 2. ELIMINARE GLI UNCHECKED WARNINGS
     *    - Ogni warning unchecked indica un potenziale ClassCastException
     * 
     * 3. USARE BOUNDED TYPE PARAMETERS QUANDO NECESSARIO
     *    - <T extends Comparable<T>> per garantire che T sia comparabile
     * 
     * 4. APPLICARE IL PRINCIPIO PECS (Producer Extends, Consumer Super)
     *    - Usa <? extends T> quando LEGGI dalla struttura (producer)
     *    - Usa <? super T> quando SCRIVI nella struttura (consumer)
     * 
     * 5. NON USARE WILDCARDS NEI TIPI DI RITORNO
     *    - List<?> come ritorno rende difficile l'uso del risultato
     * 
     * 6. COMBINARE GENERICS CON VARARGS CON CAUTELA
     *    - @SafeVarargs per indicare che il metodo è type-safe
     * 
     * 7. USARE TYPE TOKENS PER PRESERVARE INFO DI TIPO A RUNTIME
     *    - Class<T> per superare il type erasure
     * 
     * 8. PREFERIRE I METODI GENERICI ALLE CLASSI GENERICHE
     *    - Quando solo un metodo ha bisogno di essere generico
     * 
     * 9. CONSIDERARE SELF-BOUNDED TYPES PER BUILDER/FLUENT APIs
     *    - <T extends Builder<T>> per method chaining type-safe
     * 
     * 10. DOCUMENTARE CHIARAMENTE I BOUNDED TYPE PARAMETERS
     *     - Specificare le assunzioni sui tipi accettati
     * 
     * QUANDO USARE GENERICS:
     * - Collezioni type-safe
     * - Algoritmi che funzionano su tipi diversi
     * - Classi container/wrapper
     * - API che richiedono type safety a compile-time
     * 
     * QUANDO EVITARE GENERICS:
     * - Quando il tipo è sempre lo stesso
     * - Quando la complessità supera i benefici
     * - Quando serve accesso diretto alle informazioni di tipo a runtime
     */

    // ===========================================
    // METODO MAIN - DIMOSTRAZIONE COMPLETA
    // ===========================================

    public static void sample() {
        System.out.println("=== DIMOSTRAZIONE COMPLETA SUI GENERICS IN JAVA ===\n");

        // 1. Classi generiche di base
        System.out.println("1. CLASSI GENERICHE DI BASE:");
        Box<String> stringBox = new Box<>("Hello Generics");
        Box<Integer> intBox = new Box<>(42);
        System.out.println("String Box: " + stringBox);
        System.out.println("Integer Box: " + intBox);

        NumericBox<Double> doubleBox = new NumericBox<>(3.14);
        System.out.println("Numeric Box double value: " + doubleBox.doubleValue());

        // 2. Multiple type parameters
        System.out.println("\n2. MULTIPLE TYPE PARAMETERS:");
        Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println("Pair: " + pair);
        Pair<Integer, String> swapped = swap(pair);
        System.out.println("Swapped: " + swapped);

        Triple<String, Integer, Boolean> triple = new Triple<>("Test", 100, true);
        System.out.println("Triple: (" + triple.getFirst() + ", " + 
                           triple.getSecond() + ", " + triple.getThird() + ")");

        // 3. Bounded type parameters
        System.out.println("\n3. BOUNDED TYPE PARAMETERS:");
        ComparableBox<Integer> compBox = new ComparableBox<>(10);
        System.out.println("10 > 5? " + compBox.isGreaterThan(5));
        System.out.println("Max(10, 20): " + compBox.max(20));

        // 4. Metodi generici
        System.out.println("\n4. METODI GENERICI:");
        Integer[] intArray = {3, 1, 4, 1, 5, 9};
        String[] strArray = {"Java", "Generics", "Demo"};
        
        System.out.print("Array di interi: ");
        printArray(intArray);
        
        System.out.print("Array di stringhe: ");
        printArray(strArray);
        
        System.out.println("Max nell'array: " + findMax(intArray));
        
        String transformed = transform(42, num -> "Number: " + num);
        System.out.println("Transformed: " + transformed);

        // 5. Wildcards
        System.out.println("\n5. WILDCARDS:");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.print("Lista interi: ");
        printList(integers);
        
        System.out.println("Somma interi: " + sumNumbers(integers));
        System.out.println("Somma doubles: " + sumNumbers(doubles));
        
        List<Number> numbers = new ArrayList<>();
        addIntegers(numbers);
        System.out.println("Dopo addIntegers: " + numbers);

        // 6. PECS (Producer Extends, Consumer Super)
        System.out.println("\n6. PECS PRINCIPLE:");
        List<Integer> source = Arrays.asList(10, 20, 30);
        List<Number> destination = new ArrayList<>();
        copy(source, destination);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);

        // 7. Generic interfaces
        System.out.println("\n7. GENERIC INTERFACES:");
        Container<String> stringContainer = new GenericContainer<>();
        stringContainer.add("First");
        stringContainer.add("Second");
        stringContainer.add("Third");
        System.out.println("Container size: " + stringContainer.size());
        System.out.println("Element at 1: " + stringContainer.get(1));

        // 8. Inheritance con generics
        System.out.println("\n8. INHERITANCE CON GENERICS:");
        Dog<String> dog = new Dog<>("DOG001", "Buddy", "Golden Retriever");
        System.out.println("Dog ID: " + dog.getId() + ", Name: " + dog.getName() + 
                           ", Breed: " + dog.getBreed());
        
        Cat cat = new Cat(123, "Whiskers", 7);
        System.out.println("Cat ID: " + cat.getId() + ", Lives: " + cat.getLivesRemaining());

        // 9. Recursive type bounds
        System.out.println("\n9. RECURSIVE TYPE BOUNDS:");
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        System.out.println("Alice > Bob? " + (person1.compareTo(person2) > 0));

        // 10. Self-bounded types (Builder pattern)
        System.out.println("\n10. SELF-BOUNDED TYPES (Builder Pattern):");
        ExtendedBuilder builder = new ExtendedBuilder()
            .setName("Test")
            .setValue(100)
            .setExtra("Extra info");
        System.out.println("Builder: name=" + builder.getName() + 
                           ", value=" + builder.getValue() + 
                           ", extra=" + builder.getExtra());

        // 11. Type erasure
        System.out.println("\n11. TYPE ERASURE:");
        demonstrateTypeErasure();

        // 12. Generic methods con collections
        System.out.println("\n12. GENERIC METHODS CON COLLECTIONS:");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> evens = filter(nums, n -> n % 2 == 0);
        System.out.println("Numeri pari: " + evens);
        
        List<String> strings = map(nums, n -> "Num_" + n);
        System.out.println("Mapped: " + strings);
        
        Integer sum = reduce(nums, 0, (a, b) -> a + b);
        System.out.println("Somma: " + sum);
        
        Optional<Integer> firstGreaterThan5 = findFirst(nums, n -> n > 5);
        System.out.println("Primo > 5: " + firstGreaterThan5.orElse(-1));

        // 13. Generic Stack
        System.out.println("\n13. GENERIC STACK:");
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("Stack size: " + stack.size());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack size dopo pop: " + stack.size());
        
        Stack<Number> numStack = new Stack<>();
        numStack.pushAll(Arrays.asList(1, 2, 3));
        numStack.pushAll(Arrays.asList(1.1, 2.2, 3.3));
        System.out.println("Number stack size: " + numStack.size());

        // 14. Binary Search Tree
        System.out.println("\n14. BINARY SEARCH TREE:");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] values = {5, 3, 7, 1, 4, 6, 9};
        for (int val : values) {
            bst.insert(val);
        }
        System.out.println("BST in-order: " + bst.inOrder());
        System.out.println("Contiene 4? " + bst.contains(4));
        System.out.println("Contiene 8? " + bst.contains(8));

        // 15. Generic Cache
        System.out.println("\n15. GENERIC CACHE:");
        Cache<String, Integer> cache = new Cache<>(3);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        System.out.println("Cache size: " + cache.size());
        System.out.println("Get 'two': " + cache.get("two").orElse(-1));
        cache.put("four", 4); // Rimuove il primo elemento
        System.out.println("Cache size dopo aggiunta: " + cache.size());

        // 16. Type Tokens
        System.out.println("\n16. TYPE TOKENS:");
        try {
            TypeToken<String> stringToken = new TypeToken<>(String.class);
            System.out.println("Type: " + stringToken.getType().getSimpleName());
            
            GenericFactory<ArrayList> listFactory = new GenericFactory<>(ArrayList.class);
            ArrayList newList = listFactory.create();
            System.out.println("Created instance: " + newList.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 17. Variance (covariance e contravariance)
        System.out.println("\n17. VARIANCE (Covariance e Contravariance):");
        demonstrateWildcardCovariance();
        demonstrateWildcardContravariance();

        // 18. Advanced Patterns - Result Monad
        System.out.println("\n18. RESULT MONAD PATTERN:");
        Result<Integer, String> successResult = Result.success(42);
        Result<Integer, String> failureResult = Result.failure("Errore!");
        
        Result<String, String> mapped = successResult.map(n -> "Value: " + n);
        System.out.println("Success mapped: " + mapped.getValue());
        
        Result<String, String> mappedFailure = failureResult.map(n -> "Value: " + n);
        System.out.println("Failure is success? " + mappedFailure.isSuccess());
        if (!mappedFailure.isSuccess()) {
            System.out.println("Error: " + mappedFailure.getError());
        }

        // 19. Repository Pattern
        System.out.println("\n19. REPOSITORY PATTERN:");
        InMemoryRepository<Person, String> personRepo = 
            new InMemoryRepository<>(Person::getName);
        
        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 25);
        
        personRepo.save(alice);
        personRepo.save(bob);
        
        System.out.println("Tutte le persone: " + personRepo.findAll().size());
        Optional<Person> foundAlice = personRepo.findById("Alice");
        System.out.println("Trovata Alice? " + foundAlice.isPresent());

        // 20. Specification Pattern
        System.out.println("\n20. SPECIFICATION PATTERN:");
        Specification<Integer> isEven = n -> n % 2 == 0;
        Specification<Integer> isGreaterThan5 = n -> n > 5;
        Specification<Integer> combined = isEven.and(isGreaterThan5);
        
        System.out.println("6 è pari E > 5? " + combined.isSatisfiedBy(6));
        System.out.println("4 è pari E > 5? " + combined.isSatisfiedBy(4));
        System.out.println("7 è pari E > 5? " + combined.isSatisfiedBy(7));

        System.out.println("\n=== FINE DIMOSTRAZIONE ===");
    }
}
