package com.corso.samples.oop;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DIMOSTRAZIONE COMPLETA E AVANZATA SULLE INTERFACCE FUNZIONALI IN JAVA
 * 
 * Le interfacce funzionali sono state introdotte in Java 8 e permettono
 * di utilizzare lambda expressions e method references.
 * 
 * Questo modulo copre tutti gli scenari possibili:
 * 1. Function<T,R> e sue varianti
 * 2. Consumer<T> e BiConsumer<T,U>
 * 3. Supplier<T>
 * 4. Predicate<T> e BiPredicate<T,U>
 * 5. UnaryOperator<T> e BinaryOperator<T>
 * 6. Interfacce funzionali specializzate per tipi primitivi
 * 7. Interfacce funzionali custom
 * 8. Composition di funzioni
 * 9. Method references (4 tipi)
 * 10. Lambda expressions avanzate
 * 11. Currying e partial application
 * 12. Memoization
 * 13. Exception handling
 * 14. Pattern funzionali (Strategy, Command, etc.)
 * 15. Stream API integration
 * 16. Optional integration
 * 17. Closure e variabili effectively final
 * 18. Best practices e pattern avanzati
 */
public class InterfacceFunzionaliDemo {

    // ===========================================
    // SEZIONE 1: FUNCTION<T,R> - TRASFORMAZIONE
    // ===========================================

    /**
     * Function<T,R> - trasforma un input T in un output R
     */
    public static void demonstrateFunction() {
        System.out.println("=== FUNCTION<T,R> ===");

        // Function base: String -> Integer (lunghezza)
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Lunghezza di 'Hello': " + stringLength.apply("Hello"));

        // Function: Integer -> String (formattazione)
        Function<Integer, String> intToHex = i -> Integer.toHexString(i);
        System.out.println("255 in hex: " + intToHex.apply(255));

        // Function con oggetti complessi
        Function<Person, String> personToName = p -> p.getName();
        Person person = new Person("Alice", 30);
        System.out.println("Nome: " + personToName.apply(person));

        // Composition con andThen: f.andThen(g) = g(f(x))
        Function<String, Integer> lengthFunction = String::length;
        Function<Integer, String> doubleIt = n -> String.valueOf(n * 2);
        Function<String, String> composed = lengthFunction.andThen(doubleIt);
        System.out.println("'Hello' length doubled: " + composed.apply("Hello"));

        // Composition con compose: f.compose(g) = f(g(x))
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> addExclamation = s -> s + "!";
        Function<String, String> composedBefore = addExclamation.compose(toUpper);
        System.out.println("Composed: " + composedBefore.apply("hello"));

        // Identity function
        Function<String, String> identity = Function.identity();
        System.out.println("Identity: " + identity.apply("test"));
    }

    // ===========================================
    // SEZIONE 2: BIFUNCTION<T,U,R>
    // ===========================================

    /**
     * BiFunction<T,U,R> - trasforma due input in un output
     */
    public static void demonstrateBiFunction() {
        System.out.println("\n=== BIFUNCTION<T,U,R> ===");

        // BiFunction per sommare due numeri
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("5 + 3 = " + sum.apply(5, 3));

        // BiFunction per concatenare stringhe
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println("Concat: " + concat.apply("Hello", "World"));

        // BiFunction per creare oggetti
        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person newPerson = personCreator.apply("Bob", 25);
        System.out.println("Created: " + newPerson);

        // BiFunction con andThen
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, String> toString = Object::toString;
        BiFunction<Integer, Integer, String> multiplyAndConvert = 
            multiply.andThen(toString);
        System.out.println("4 * 5 = " + multiplyAndConvert.apply(4, 5));

        // BiFunction per operazioni complesse
        BiFunction<List<Integer>, Integer, List<Integer>> addToList = 
            (list, value) -> {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(value);
                return newList;
            };
        List<Integer> nums = Arrays.asList(1, 2, 3);
        System.out.println("Add 4 to list: " + addToList.apply(nums, 4));
    }

    // ===========================================
    // SEZIONE 3: CONSUMER<T> - CONSUMO SENZA RITORNO
    // ===========================================

    /**
     * Consumer<T> - accetta un input e non ritorna nulla
     */
    public static void demonstrateConsumer() {
        System.out.println("\n=== CONSUMER<T> ===");

        // Consumer per stampare
        Consumer<String> printer = s -> System.out.println("Stampo: " + s);
        printer.accept("Hello Consumer");

        // Consumer per modificare oggetti
        Consumer<Person> agePerson = p -> p.setAge(p.getAge() + 1);
        Person person = new Person("Charlie", 20);
        System.out.println("Before: " + person);
        agePerson.accept(person);
        System.out.println("After: " + person);

        // Consumer con side effects
        List<String> log = new ArrayList<>();
        Consumer<String> logger = s -> log.add("Log: " + s);
        logger.accept("Event 1");
        logger.accept("Event 2");
        System.out.println("Log: " + log);

        // Chaining con andThen
        Consumer<String> upperCase = s -> System.out.println(s.toUpperCase());
        Consumer<String> lowerCase = s -> System.out.println(s.toLowerCase());
        Consumer<String> both = upperCase.andThen(lowerCase);
        both.accept("Hello");

        // Consumer per iterare collezioni
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<Integer> printSquare = n -> System.out.print(n * n + " ");
        System.out.print("Quadrati: ");
        numbers.forEach(printSquare);
        System.out.println();
    }

    // ===========================================
    // SEZIONE 4: BICONSUMER<T,U>
    // ===========================================

    /**
     * BiConsumer<T,U> - accetta due input e non ritorna nulla
     */
    public static void demonstrateBiConsumer() {
        System.out.println("\n=== BICONSUMER<T,U> ===");

        // BiConsumer per stampare coppia
        BiConsumer<String, Integer> printPair = 
            (name, age) -> System.out.println(name + " ha " + age + " anni");
        printPair.accept("David", 35);

        // BiConsumer per popolare mappa
        Map<String, Integer> scores = new HashMap<>();
        BiConsumer<String, Integer> addScore = scores::put;
        addScore.accept("Alice", 95);
        addScore.accept("Bob", 87);
        System.out.println("Scores: " + scores);

        // BiConsumer per aggiornare oggetti
        BiConsumer<Person, String> updateName = Person::setName;
        Person person = new Person("Old Name", 30);
        updateName.accept(person, "New Name");
        System.out.println("Updated: " + person);

        // BiConsumer chain
        BiConsumer<String, Integer> print = (s, i) -> System.out.println(s + ": " + i);
        BiConsumer<String, Integer> logIt = (s, i) -> 
            System.out.println("Logged - " + s + ": " + i);
        BiConsumer<String, Integer> combined = print.andThen(logIt);
        combined.accept("Value", 42);

        // BiConsumer con Map.forEach
        Map<String, String> map = Map.of("key1", "value1", "key2", "value2");
        BiConsumer<String, String> printEntry = 
            (k, v) -> System.out.println(k + " -> " + v);
        map.forEach(printEntry);
    }

    // ===========================================
    // SEZIONE 5: SUPPLIER<T> - PRODUZIONE SENZA INPUT
    // ===========================================

    /**
     * Supplier<T> - fornisce un valore senza input
     */
    public static void demonstrateSupplier() {
        System.out.println("\n=== SUPPLIER<T> ===");

        // Supplier di valori costanti
        Supplier<String> constantSupplier = () -> "Constant Value";
        System.out.println("Constant: " + constantSupplier.get());

        // Supplier di valori random
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(100);
        System.out.println("Random: " + randomSupplier.get());

        // Supplier per lazy initialization
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();
        System.out.println("New list: " + list);

        // Supplier con calcoli complessi (lazy)
        Supplier<Double> expensiveCalculation = () -> {
            System.out.println("Calculating...");
            return Math.PI * Math.E;
        };
        System.out.println("Result: " + expensiveCalculation.get());

        // Supplier per fornire date
        Supplier<LocalDate> todaySupplier = LocalDate::now;
        System.out.println("Today: " + todaySupplier.get());

        // Supplier per factory
        Supplier<Person> personFactory = () -> new Person("Default", 0);
        Person defaultPerson = personFactory.get();
        System.out.println("Default person: " + defaultPerson);

        // Supplier con Optional
        Optional<String> optional = Optional.empty();
        String result = optional.orElseGet(() -> "Default Value");
        System.out.println("Optional result: " + result);
    }

    // ===========================================
    // SEZIONE 6: PREDICATE<T> - TEST BOOLEANO
    // ===========================================

    /**
     * Predicate<T> - test che ritorna boolean
     */
    public static void demonstratePredicate() {
        System.out.println("\n=== PREDICATE<T> ===");

        // Predicate base
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("4 è pari? " + isEven.test(4));
        System.out.println("5 è pari? " + isEven.test(5));

        // Predicate per stringhe
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println("'' è vuota? " + isEmpty.test(""));
        System.out.println("'Hi' non è vuota? " + isNotEmpty.test("Hi"));

        // Composition con and, or, negate
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isLessThan100 = n -> n < 100;
        Predicate<Integer> isValid = isPositive.and(isLessThan100);
        System.out.println("50 è valido? " + isValid.test(50));
        System.out.println("150 è valido? " + isValid.test(150));

        // Predicate chain con or
        Predicate<String> startsWithA = s -> s.startsWith("A");
        Predicate<String> startsWithB = s -> s.startsWith("B");
        Predicate<String> startsWithAorB = startsWithA.or(startsWithB);
        System.out.println("'Alice' starts with A or B? " + startsWithAorB.test("Alice"));
        System.out.println("'Charlie' starts with A or B? " + startsWithAorB.test("Charlie"));

        // Filtering con Predicate
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
            .filter(isEven)
            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        // Predicate per oggetti complessi
        Predicate<Person> isAdult = p -> p.getAge() >= 18;
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 15),
            new Person("Charlie", 30)
        );
        List<Person> adults = people.stream()
            .filter(isAdult)
            .collect(Collectors.toList());
        System.out.println("Adults: " + adults);

        // Predicate.isEqual
        Predicate<String> isHello = Predicate.isEqual("Hello");
        System.out.println("'Hello' equals 'Hello'? " + isHello.test("Hello"));
    }

    // ===========================================
    // SEZIONE 7: BIPREDICATE<T,U>
    // ===========================================

    /**
     * BiPredicate<T,U> - test su due input
     */
    public static void demonstrateBiPredicate() {
        System.out.println("\n=== BIPREDICATE<T,U> ===");

        // BiPredicate per confronto
        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        System.out.println("5 > 3? " + isGreater.test(5, 3));
        System.out.println("2 > 8? " + isGreater.test(2, 8));

        // BiPredicate per stringhe
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("'Hello' starts with 'He'? " + startsWith.test("Hello", "He"));

        // BiPredicate con and/or/negate
        BiPredicate<Integer, Integer> bothPositive = 
            (a, b) -> a > 0 && b > 0;
        BiPredicate<Integer, Integer> sumIsEven = 
            (a, b) -> (a + b) % 2 == 0;
        BiPredicate<Integer, Integer> combined = bothPositive.and(sumIsEven);
        System.out.println("3 and 5 both positive and sum even? " + combined.test(3, 5));
        System.out.println("4 and 6 both positive and sum even? " + combined.test(4, 6));

        // BiPredicate per validazione
        BiPredicate<String, Integer> isValidPassword = 
            (pwd, minLength) -> pwd.length() >= minLength && pwd.matches(".*[0-9].*");
        System.out.println("'pass123' valid (min 6)? " + 
            isValidPassword.test("pass123", 6));
        System.out.println("'pass' valid (min 6)? " + 
            isValidPassword.test("pass", 6));
    }

    // ===========================================
    // SEZIONE 8: UNARYOPERATOR<T> E BINARYOPERATOR<T>
    // ===========================================

    /**
     * UnaryOperator<T> - Function<T,T> specializzata
     * BinaryOperator<T> - BiFunction<T,T,T> specializzata
     */
    public static void demonstrateOperators() {
        System.out.println("\n=== UNARYOPERATOR E BINARYOPERATOR ===");

        // UnaryOperator - trasformazione dello stesso tipo
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 5: " + square.apply(5));

        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println("Uppercase: " + toUpperCase.apply("hello"));

        // UnaryOperator.identity()
        UnaryOperator<String> identity = UnaryOperator.identity();
        System.out.println("Identity: " + identity.apply("test"));

        // BinaryOperator - operazione su due valori dello stesso tipo
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("3 + 4 = " + add.apply(3, 4));

        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;
        System.out.println("Concat: " + concat.apply("Hello", "World"));

        // BinaryOperator.maxBy e minBy
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compareTo);
        System.out.println("Max(5, 3): " + max.apply(5, 3));
        System.out.println("Min(5, 3): " + min.apply(5, 3));

        // BinaryOperator con Comparator custom
        BinaryOperator<Person> youngerPerson = BinaryOperator.minBy(
            Comparator.comparing(Person::getAge)
        );
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Bob", 25);
        System.out.println("Younger: " + youngerPerson.apply(p1, p2));

        // Uso in reduce
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = numbers.stream().reduce(0, add);
        System.out.println("Sum: " + sum);
    }

    // ===========================================
    // SEZIONE 9: INTERFACCE FUNZIONALI PRIMITIVE
    // ===========================================

    /**
     * IntFunction, IntPredicate, IntConsumer, IntSupplier, etc.
     * Evitano boxing/unboxing per migliori performance
     */
    public static void demonstratePrimitiveFunctionalInterfaces() {
        System.out.println("\n=== INTERFACCE FUNZIONALI PRIMITIVE ===");

        // IntPredicate - evita autoboxing
        IntPredicate isEvenInt = n -> n % 2 == 0;
        System.out.println("4 is even? " + isEvenInt.test(4));

        // IntFunction<R> - int -> R
        IntFunction<String> intToString = i -> "Number: " + i;
        System.out.println(intToString.apply(42));

        // ToIntFunction<T> - T -> int
        ToIntFunction<String> stringToLength = String::length;
        System.out.println("Length: " + stringToLength.applyAsInt("Hello"));

        // IntConsumer
        IntConsumer printInt = i -> System.out.println("Int: " + i);
        printInt.accept(100);

        // IntSupplier
        IntSupplier randomInt = () -> new Random().nextInt(100);
        System.out.println("Random int: " + randomInt.getAsInt());

        // IntUnaryOperator
        IntUnaryOperator doubleIt = n -> n * 2;
        System.out.println("Double 5: " + doubleIt.applyAsInt(5));

        // IntBinaryOperator
        IntBinaryOperator multiply = (a, b) -> a * b;
        System.out.println("3 * 4 = " + multiply.applyAsInt(3, 4));

        // LongPredicate, DoublePredicate, etc.
        LongPredicate isPositiveLong = l -> l > 0;
        DoublePredicate isFinite = Double::isFinite;
        System.out.println("100L is positive? " + isPositiveLong.test(100L));
        System.out.println("3.14 is finite? " + isFinite.test(3.14));

        // Performance comparison con stream
        int sum = java.util.stream.IntStream.range(0, 1000)
            .filter(isEvenInt)
            .map(doubleIt)
            .sum();
        System.out.println("Sum of doubled evens: " + sum);
    }

    // ===========================================
    // SEZIONE 10: INTERFACCE FUNZIONALI CUSTOM
    // ===========================================

    /**
     * Interfacce funzionali personalizzate
     */
    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
        
        // Metodi default sono permessi
        default <W> TriFunction<T, U, V, W> andThen(Function<R, W> after) {
            Objects.requireNonNull(after);
            return (t, u, v) -> after.apply(apply(t, u, v));
        }
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T value);
        
        default Validator<T> and(Validator<T> other) {
            return value -> this.validate(value) && other.validate(value);
        }
        
        default Validator<T> or(Validator<T> other) {
            return value -> this.validate(value) || other.validate(value);
        }
    }

    @FunctionalInterface
    interface Transformer<T> {
        T transform(T input);
        
        default Transformer<T> compose(Transformer<T> before) {
            return input -> transform(before.transform(input));
        }
    }

    public static void demonstrateCustomFunctionalInterfaces() {
        System.out.println("\n=== INTERFACCE FUNZIONALI CUSTOM ===");

        // TriFunction
        TriFunction<Integer, Integer, Integer, Integer> sumThree = 
            (a, b, c) -> a + b + c;
        System.out.println("Sum of 1, 2, 3: " + sumThree.apply(1, 2, 3));

        // TriFunction con andThen
        TriFunction<String, String, String, String> concat3 = 
            (s1, s2, s3) -> s1 + s2 + s3;
        Function<String, String> toUpper = String::toUpperCase;
        TriFunction<String, String, String, String> concatAndUpper = 
            concat3.andThen(toUpper);
        System.out.println("Concat + Upper: " + 
            concatAndUpper.apply("hello", " ", "world"));

        // Validator custom
        Validator<String> notEmpty = s -> !s.isEmpty();
        Validator<String> hasNumber = s -> s.matches(".*[0-9].*");
        Validator<String> passwordValidator = notEmpty.and(hasNumber);
        System.out.println("'pass123' valid? " + passwordValidator.validate("pass123"));
        System.out.println("'pass' valid? " + passwordValidator.validate("pass"));

        // Transformer custom
        Transformer<String> addPrefix = s -> "PREFIX_" + s;
        Transformer<String> toUpperTransformer = String::toUpperCase;
        Transformer<String> combined = addPrefix.compose(toUpperTransformer);
        System.out.println("Transform: " + combined.transform("test"));
    }

    // ===========================================
    // SEZIONE 11: METHOD REFERENCES
    // ===========================================

    /**
     * 4 tipi di method reference:
     * 1. Static method reference: ClassName::staticMethod
     * 2. Instance method reference: instance::instanceMethod
     * 3. Instance method reference (arbitrary object): ClassName::instanceMethod
     * 4. Constructor reference: ClassName::new
     */
    public static void demonstrateMethodReferences() {
        System.out.println("\n=== METHOD REFERENCES ===");

        // 1. Static method reference
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("Parse '123': " + parseInt.apply("123"));

        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Random: " + randomSupplier.get());

        // 2. Instance method reference su oggetto specifico
        String prefix = "Prefix: ";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println(addPrefix.apply("Test"));

        List<String> list = new ArrayList<>();
        Consumer<String> addToList = list::add;
        addToList.accept("Item1");
        addToList.accept("Item2");
        System.out.println("List: " + list);

        // 3. Instance method reference su tipo (arbitrary object)
        Function<String, String> toUpperRef = String::toUpperCase;
        System.out.println("Upper: " + toUpperRef.apply("hello"));

        BiPredicate<String, String> startsWithRef = String::startsWith;
        System.out.println("'Hello' starts with 'He'? " + 
            startsWithRef.test("Hello", "He"));

        Comparator<String> comparator = String::compareToIgnoreCase;
        System.out.println("Compare: " + comparator.compare("abc", "ABC"));

        // 4. Constructor reference
        Supplier<List<String>> listConstructor = ArrayList::new;
        List<String> newList = listConstructor.get();
        System.out.println("New list: " + newList);

        Function<String, Person> personConstructor = name -> new Person(name, 0);
        BiFunction<String, Integer, Person> personBiConstructor = Person::new;
        Person person = personBiConstructor.apply("Alice", 25);
        System.out.println("Created: " + person);

        // Array constructor reference
        IntFunction<String[]> arrayConstructor = String[]::new;
        String[] array = arrayConstructor.apply(5);
        System.out.println("Array length: " + array.length);
    }

    // ===========================================
    // SEZIONE 12: COMPOSITION DI FUNZIONI
    // ===========================================

    /**
     * Composizione avanzata di funzioni
     */
    public static void demonstrateFunctionComposition() {
        System.out.println("\n=== COMPOSITION DI FUNZIONI ===");

        // Pipeline di trasformazioni
        Function<String, String> trim = String::trim;
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<String, String> removeSpaces = s -> s.replaceAll("\\s+", "");
        
        Function<String, String> normalize = trim
            .andThen(toLowerCase)
            .andThen(removeSpaces);
        
        String input = "  Hello World  ";
        System.out.println("Normalized: '" + normalize.apply(input) + "'");

        // Composition bidirezionale
        Function<Integer, Integer> multiplyBy2 = n -> n * 2;
        Function<Integer, Integer> add3 = n -> n + 3;
        
        // andThen: prima moltiplica, poi aggiungi
        Function<Integer, Integer> pipeline1 = multiplyBy2.andThen(add3);
        System.out.println("(5 * 2) + 3 = " + pipeline1.apply(5)); // 13
        
        // compose: prima aggiungi, poi moltiplica
        Function<Integer, Integer> pipeline2 = multiplyBy2.compose(add3);
        System.out.println("(5 + 3) * 2 = " + pipeline2.apply(5)); // 16

        // Composition di Predicate
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isGreaterThan10 = n -> n > 10;
        
        Predicate<Integer> complexPredicate = isEven
            .and(isPositive)
            .and(isGreaterThan10);
        
        System.out.println("12 passes complex predicate? " + 
            complexPredicate.test(12));
        System.out.println("8 passes complex predicate? " + 
            complexPredicate.test(8));

        // Composition di Consumer
        Consumer<String> print = System.out::println;
        Consumer<String> log = s -> System.out.println("LOG: " + s);
        Consumer<String> both = print.andThen(log);
        both.accept("Message");
    }

    // ===========================================
    // SEZIONE 13: CURRYING E PARTIAL APPLICATION
    // ===========================================

    /**
     * Currying: trasforma una funzione multi-parametro in una sequenza
     * di funzioni single-parametro
     */
    public static void demonstrateCurrying() {
        System.out.println("\n=== CURRYING E PARTIAL APPLICATION ===");

        // Currying manuale
        Function<Integer, Function<Integer, Integer>> curriedAdd = 
            a -> b -> a + b;
        
        Function<Integer, Integer> add5 = curriedAdd.apply(5);
        System.out.println("5 + 3 = " + add5.apply(3));
        System.out.println("5 + 10 = " + add5.apply(10));

        // Currying a tre livelli
        Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedSum3 =
            a -> b -> c -> a + b + c;
        
        Function<Integer, Function<Integer, Integer>> addTo10 = 
            curriedSum3.apply(10);
        Function<Integer, Integer> addTo10And5 = addTo10.apply(5);
        System.out.println("10 + 5 + 3 = " + addTo10And5.apply(3));

        // Partial application con BiFunction
        BiFunction<String, Integer, String> repeat = 
            (str, times) -> str.repeat(times);
        
        // Crea una funzione parzialmente applicata
        Function<Integer, String> repeatHello = times -> repeat.apply("Hello ", times);
        System.out.println(repeatHello.apply(3));

        // Currying per configurazione
        Function<String, Function<Integer, Person>> createPersonWithName = 
            name -> age -> new Person(name, age);
        
        Function<Integer, Person> createAlice = createPersonWithName.apply("Alice");
        Person alice25 = createAlice.apply(25);
        Person alice30 = createAlice.apply(30);
        System.out.println("Alice at 25: " + alice25);
        System.out.println("Alice at 30: " + alice30);
    }

    // ===========================================
    // SEZIONE 14: MEMOIZATION
    // ===========================================

    /**
     * Memoization: cache dei risultati di funzioni pure
     */
    static class Memoizer {
        public static <T, R> Function<T, R> memoize(Function<T, R> function) {
            Map<T, R> cache = new HashMap<>();
            return input -> cache.computeIfAbsent(input, function);
        }
    }

    public static void demonstrateMemoization() {
        System.out.println("\n=== MEMOIZATION ===");

        // Funzione costosa
        Function<Integer, Integer> expensive = n -> {
            System.out.println("Calculating factorial of " + n);
            int result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        // Memoize la funzione
        Function<Integer, Integer> memoizedFactorial = Memoizer.memoize(expensive);

        System.out.println("First call:");
        System.out.println("5! = " + memoizedFactorial.apply(5));
        
        System.out.println("\nSecond call (cached):");
        System.out.println("5! = " + memoizedFactorial.apply(5));
        
        System.out.println("\nDifferent input:");
        System.out.println("6! = " + memoizedFactorial.apply(6));

        // Memoization per Fibonacci
        Function<Integer, Long> fibonacci = Memoizer.memoize(new Function<Integer, Long>() {
            public Long apply(Integer n) {
                System.out.println("Computing fib(" + n + ")");
                if (n <= 1) return (long) n;
                return this.apply(n - 1) + this.apply(n - 2);
            }
        });

        System.out.println("\nFibonacci memoized:");
        System.out.println("fib(10) = " + fibonacci.apply(10));
    }

    // ===========================================
    // SEZIONE 15: EXCEPTION HANDLING
    // ===========================================

    /**
     * Gestione eccezioni con interfacce funzionali
     */
    @FunctionalInterface
    interface CheckedFunction<T, R> {
        R apply(T t) throws Exception;
        
        static <T, R> Function<T, R> wrap(CheckedFunction<T, R> function) {
            return t -> {
                try {
                    return function.apply(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
        }
    }

    @FunctionalInterface
    interface CheckedConsumer<T> {
        void accept(T t) throws Exception;
        
        static <T> Consumer<T> wrap(CheckedConsumer<T> consumer) {
            return t -> {
                try {
                    consumer.accept(t);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
        }
    }

    public static void demonstrateExceptionHandling() {
        System.out.println("\n=== EXCEPTION HANDLING ===");

        // Wrapping checked exceptions
        Function<String, Integer> parseIntSafe = CheckedFunction.wrap(s -> {
            if (s.equals("error")) throw new IOException("Simulated error");
            return Integer.parseInt(s);
        });

        try {
            System.out.println("Parse '123': " + parseIntSafe.apply("123"));
            System.out.println("Parse 'error': " + parseIntSafe.apply("error"));
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getCause().getMessage());
        }

        // Function con gestione errori
        Function<String, Optional<Integer>> safeParseInt = s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        };

        System.out.println("Parse '456': " + safeParseInt.apply("456"));
        System.out.println("Parse 'abc': " + safeParseInt.apply("abc"));

        // Consumer con try-catch
        List<String> items = Arrays.asList("1", "2", "abc", "4");
        Consumer<String> printParsed = s -> {
            try {
                System.out.println("Parsed: " + Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println("Error parsing: " + s);
            }
        };
        items.forEach(printParsed);
    }

    // ===========================================
    // SEZIONE 16: PATTERN FUNZIONALI - STRATEGY
    // ===========================================

    /**
     * Strategy Pattern con interfacce funzionali
     */
    interface DiscountStrategy extends Function<Double, Double> {
        // Function<Double, Double> già fornisce apply(Double) -> Double
    }

    static class PriceCalculator {
        private DiscountStrategy discountStrategy;

        public PriceCalculator(DiscountStrategy strategy) {
            this.discountStrategy = strategy;
        }

        public void setDiscountStrategy(DiscountStrategy strategy) {
            this.discountStrategy = strategy;
        }

        public double calculatePrice(double basePrice) {
            return discountStrategy.apply(basePrice);
        }
    }

    public static void demonstrateStrategyPattern() {
        System.out.println("\n=== STRATEGY PATTERN ===");

        // Diverse strategie di sconto
        DiscountStrategy noDiscount = price -> price;
        DiscountStrategy tenPercent = price -> price * 0.9;
        DiscountStrategy twentyPercent = price -> price * 0.8;
        DiscountStrategy fixed10Euro = price -> price - 10;

        PriceCalculator calculator = new PriceCalculator(noDiscount);
        double basePrice = 100.0;

        System.out.println("Base price: " + basePrice);
        System.out.println("No discount: " + calculator.calculatePrice(basePrice));

        calculator.setDiscountStrategy(tenPercent);
        System.out.println("10% discount: " + calculator.calculatePrice(basePrice));

        calculator.setDiscountStrategy(twentyPercent);
        System.out.println("20% discount: " + calculator.calculatePrice(basePrice));

        calculator.setDiscountStrategy(fixed10Euro);
        System.out.println("Fixed 10€ discount: " + calculator.calculatePrice(basePrice));

        // Strategy on-the-fly
        calculator.setDiscountStrategy(price -> {
            if (price > 100) return price * 0.85; // 15% for >100
            if (price > 50) return price * 0.9;   // 10% for >50
            return price;                         // no discount
        });
        System.out.println("Tiered discount (100): " + calculator.calculatePrice(100));
        System.out.println("Tiered discount (150): " + calculator.calculatePrice(150));
    }

    // ===========================================
    // SEZIONE 17: PATTERN FUNZIONALI - COMMAND
    // ===========================================

    /**
     * Command Pattern con interfacce funzionali
     */
    interface Command extends Runnable {
        // Runnable già fornisce run()
    }

    static class TextEditor {
        private StringBuilder text = new StringBuilder();
        private Deque<Command> undoStack = new ArrayDeque<>();

        public void write(String s) {
            text.append(s);
            undoStack.push(() -> {
                int length = text.length();
                text.delete(length - s.length(), length);
            });
        }

        public void delete(int count) {
            String deleted = text.substring(text.length() - count);
            text.delete(text.length() - count, text.length());
            undoStack.push(() -> text.append(deleted));
        }

        public void undo() {
            if (!undoStack.isEmpty()) {
                undoStack.pop().run();
            }
        }

        public String getText() {
            return text.toString();
        }
    }

    public static void demonstrateCommandPattern() {
        System.out.println("\n=== COMMAND PATTERN ===");

        TextEditor editor = new TextEditor();
        
        System.out.println("Initial: '" + editor.getText() + "'");
        
        editor.write("Hello");
        System.out.println("After write 'Hello': '" + editor.getText() + "'");
        
        editor.write(" World");
        System.out.println("After write ' World': '" + editor.getText() + "'");
        
        editor.undo();
        System.out.println("After undo: '" + editor.getText() + "'");
        
        editor.undo();
        System.out.println("After undo: '" + editor.getText() + "'");

        // Command pattern generico
        List<Runnable> commands = new ArrayList<>();
        commands.add(() -> System.out.println("Command 1 executed"));
        commands.add(() -> System.out.println("Command 2 executed"));
        commands.add(() -> System.out.println("Command 3 executed"));

        System.out.println("\nExecuting commands:");
        commands.forEach(Runnable::run);
    }

    // ===========================================
    // SEZIONE 18: STREAM API INTEGRATION
    // ===========================================

    /**
     * Uso intensivo di interfacce funzionali con Stream API
     */
    public static void demonstrateStreamIntegration() {
        System.out.println("\n=== STREAM API INTEGRATION ===");

        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35),
            new Person("David", 28),
            new Person("Eve", 22)
        );

        // Filter (Predicate)
        List<Person> youngPeople = people.stream()
            .filter(p -> p.getAge() < 30)
            .collect(Collectors.toList());
        System.out.println("Young people: " + youngPeople);

        // Map (Function)
        List<String> names = people.stream()
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.println("Names: " + names);

        // FlatMap (Function)
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        List<Integer> flattened = listOfLists.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Flattened: " + flattened);

        // Reduce (BinaryOperator)
        Optional<Person> oldest = people.stream()
            .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2);
        System.out.println("Oldest: " + oldest.orElse(null));

        // Collect con groupingBy
        Map<Boolean, List<Person>> partitioned = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
        System.out.println("30 or older: " + partitioned.get(true));
        System.out.println("Under 30: " + partitioned.get(false));

        // Peek (Consumer)
        long count = people.stream()
            .peek(p -> System.out.println("Processing: " + p.getName()))
            .filter(p -> p.getAge() > 25)
            .peek(p -> System.out.println("Filtered: " + p.getName()))
            .count();
        System.out.println("Count: " + count);
    }

    // ===========================================
    // SEZIONE 19: OPTIONAL INTEGRATION
    // ===========================================

    /**
     * Optional con interfacce funzionali
     */
    public static void demonstrateOptionalIntegration() {
        System.out.println("\n=== OPTIONAL INTEGRATION ===");

        Optional<String> optional = Optional.of("Hello");
        Optional<String> empty = Optional.empty();

        // map (Function)
        Optional<Integer> length = optional.map(String::length);
        System.out.println("Length: " + length.orElse(0));

        // flatMap (Function che ritorna Optional)
        Function<String, Optional<String>> toUpperIfLong = s -> 
            s.length() > 3 ? Optional.of(s.toUpperCase()) : Optional.empty();
        
        Optional<String> result = optional.flatMap(toUpperIfLong);
        System.out.println("Result: " + result.orElse("N/A"));

        // filter (Predicate)
        Optional<String> filtered = optional.filter(s -> s.startsWith("H"));
        System.out.println("Filtered: " + filtered.orElse("N/A"));

        // ifPresent (Consumer)
        optional.ifPresent(s -> System.out.println("Present: " + s));
        empty.ifPresent(s -> System.out.println("This won't print"));

        // ifPresentOrElse (Consumer, Runnable)
        optional.ifPresentOrElse(
            s -> System.out.println("Value: " + s),
            () -> System.out.println("Empty")
        );

        // orElseGet (Supplier)
        String value = empty.orElseGet(() -> "Default value");
        System.out.println("OrElseGet: " + value);

        // or (Supplier<Optional>)
        Optional<String> alternative = empty.or(() -> Optional.of("Alternative"));
        System.out.println("Alternative: " + alternative.get());
    }

    // ===========================================
    // SEZIONE 20: CLOSURE E VARIABILI
    // ===========================================

    /**
     * Closure e effectively final variables
     */
    public static void demonstrateClosure() {
        System.out.println("\n=== CLOSURE E VARIABILI ===");

        // Variable capture (effectively final)
        String prefix = "Mr. ";
        Function<String, String> addPrefix = name -> prefix + name;
        System.out.println(addPrefix.apply("Smith"));

        // prefix = "Mrs. "; // Errore: prefix deve essere effectively final

        // Workaround con array/wrapper per variabili mutabili
        final int[] counter = {0};
        Runnable increment = () -> counter[0]++;
        
        increment.run();
        increment.run();
        increment.run();
        System.out.println("Counter: " + counter[0]);

        // Closure con stato
        Supplier<Integer> idGenerator = new Supplier<Integer>() {
            private int id = 0;
            
            @Override
            public Integer get() {
                return ++id;
            }
        };
        
        System.out.println("ID: " + idGenerator.get());
        System.out.println("ID: " + idGenerator.get());
        System.out.println("ID: " + idGenerator.get());

        // Accesso a variabili della classe
        InterfacceFunzionaliDemo demo = new InterfacceFunzionaliDemo();
        demo.demonstrateClosureWithInstanceVariables();
    }

    private int instanceCounter = 0;

    private void demonstrateClosureWithInstanceVariables() {
        Consumer<String> printer = s -> {
            System.out.println(s + " - Count: " + (++instanceCounter));
        };
        
        printer.accept("First");
        printer.accept("Second");
        printer.accept("Third");
    }

    // ===========================================
    // SEZIONE 21: BEST PRACTICES E PERFORMANCE
    // ===========================================

    /**
     * BEST PRACTICES PER INTERFACCE FUNZIONALI:
     * 
     * 1. PREFERIRE METHOD REFERENCE A LAMBDA QUANDO POSSIBILE
     *    - list.forEach(System.out::println) invece di list.forEach(x -> System.out.println(x))
     * 
     * 2. USARE INTERFACCE FUNZIONALI PRIMITIVE PER PERFORMANCE
     *    - IntPredicate invece di Predicate<Integer> (evita boxing)
     * 
     * 3. MANTENERE LE LAMBDA BREVI E LEGGIBILI
     *    - Se troppo complessa, estrarre in un metodo
     * 
     * 4. EVITARE SIDE EFFECTS NELLE FUNZIONI PURE
     *    - Le Function dovrebbero essere pure, i Consumer per side effects
     * 
     * 5. USARE COMPOSITION PER COSTRUIRE LOGICA COMPLESSA
     *    - f.andThen(g).andThen(h) invece di lambda complicate
     * 
     * 6. GESTIRE LE ECCEZIONI APPROPRIATAMENTE
     *    - Wrappare checked exceptions quando necessario
     * 
     * 7. ATTENZIONE ALLE VARIABILI CAPTURED (EFFECTIVELY FINAL)
     *    - Le variabili usate nelle lambda devono essere effectively final
     * 
     * 8. CONSIDERARE MEMOIZATION PER FUNZIONI COSTOSE
     *    - Cachare i risultati quando appropriato
     * 
     * 9. USARE OPTIONAL AL POSTO DI NULL
     *    - Supplier<Optional<T>> invece di Supplier<T> che può ritornare null
     * 
     * 10. DOCUMENTARE IL COMPORTAMENTO ATTESO
     *     - Chiarire se la funzione è pura, thread-safe, etc.
     * 
     * QUANDO USARE:
     * - Function: trasformazioni pure
     * - Consumer: operazioni con side effects
     * - Supplier: lazy initialization, factory
     * - Predicate: filtering, validation
     * - Operator: operazioni sullo stesso tipo
     * 
     * PERFORMANCE:
     * - Le lambda sono performanti (non creano oggetti se non necessario)
     * - Method reference spesso migliori delle lambda
     * - Interfacce primitive evitano boxing overhead
     * - Stream paralleli per grandi dataset
     */

    // ===========================================
    // CLASSE HELPER PER ESEMPI
    // ===========================================

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    // ===========================================
    // METODO MAIN - DIMOSTRAZIONE COMPLETA
    // ===========================================

    public static void sample() {
        System.out.println("=== DIMOSTRAZIONE COMPLETA SULLE INTERFACCE FUNZIONALI ===\n");

        demonstrateFunction();
        demonstrateBiFunction();
        demonstrateConsumer();
        demonstrateBiConsumer();
        demonstrateSupplier();
        demonstratePredicate();
        demonstrateBiPredicate();
        demonstrateOperators();
        demonstratePrimitiveFunctionalInterfaces();
        demonstrateCustomFunctionalInterfaces();
        demonstrateMethodReferences();
        demonstrateFunctionComposition();
        demonstrateCurrying();
        demonstrateMemoization();
        demonstrateExceptionHandling();
        demonstrateStrategyPattern();
        demonstrateCommandPattern();
        demonstrateStreamIntegration();
        demonstrateOptionalIntegration();
        demonstrateClosure();

        System.out.println("\n=== FINE DIMOSTRAZIONE ===");
    }
}
