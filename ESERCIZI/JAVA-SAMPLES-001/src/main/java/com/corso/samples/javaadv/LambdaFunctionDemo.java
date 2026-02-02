package com.corso.samples.javaadv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Esempio completo e avanzato sulle Lambda Function in Java
 * Include:
 * - Sintassi lambda base e avanzata
 * - Functional Interfaces predefiniti (Function, Predicate, Consumer, Supplier,
 * etc.)
 * - Method References (static, instance, constructor)
 * - Lambda con Stream API
 * - Composizione di lambda
 * - Lambda con closure
 * - Custom Functional Interfaces
 * - Lambda ricorsive
 * - Curry e Partial Application
 * - Pattern avanzati e best practices
 */
public class LambdaFunctionDemo {

    public static void sample() {
        LambdaFunctionDemo demo = new LambdaFunctionDemo();

        System.out.println("=== LAMBDA FUNCTION IN JAVA - GUIDA COMPLETA ===\n");

        // Basi
        demo.sintassiBase();
        demo.functionalInterfacesPredefiniti();
        demo.methodReferences();

        // Functional Interfaces specifici
        demo.predicateExamples();
        demo.functionExamples();
        demo.consumerExamples();
        demo.supplierExamples();
        demo.operatorExamples();
        demo.biInterfacesExamples();

        // Avanzati
        demo.composizioneLambda();
        demo.lambdaConClosure();
        demo.customFunctionalInterfaces();
        demo.lambdaConStream();
        demo.methodReferencesAvanzati();
        demo.lambdaRicorsive();
        demo.curryingPartialApplication();
        demo.higherOrderFunctions();
        demo.casiUsoReali();
        demo.bestPractices();
    }

    // ==================== SINTASSI BASE ====================

    public void sintassiBase() {
        System.out.println("=== SINTASSI BASE LAMBDA ===");

        // 1. Lambda con zero parametri
        Runnable r1 = () -> System.out.println("  Lambda senza parametri");
        r1.run();

        // 2. Lambda con un parametro (parentesi opzionali)
        Consumer<String> c1 = s -> System.out.println("  Un parametro: " + s);
        c1.accept("Hello");

        Consumer<String> c2 = (s) -> System.out.println("  Con parentesi: " + s);
        c2.accept("World");

        // 3. Lambda con più parametri
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("  Somma (5 + 3): " + add.apply(5, 3));

        // 4. Lambda con tipo esplicito
        BiFunction<Integer, Integer, Integer> multiply = (Integer a, Integer b) -> a * b;
        System.out.println("  Prodotto (5 * 3): " + multiply.apply(5, 3));

        // 5. Lambda con blocco di codice
        Function<Integer, String> describe = (n) -> {
            if (n > 0) {
                return "Positivo";
            } else if (n < 0) {
                return "Negativo";
            } else {
                return "Zero";
            }
        };
        System.out.println("  Descrivi 5: " + describe.apply(5));
        System.out.println("  Descrivi -3: " + describe.apply(-3));

        // 6. Lambda che ritorna lambda (Higher-Order Function)
        Function<Integer, Function<Integer, Integer>> adder = x -> y -> x + y;
        Function<Integer, Integer> add5 = adder.apply(5);
        System.out.println("  Add 5 to 3: " + add5.apply(3));

        System.out.println();
    }

    // ==================== FUNCTIONAL INTERFACES PREDEFINITI ====================

    public void functionalInterfacesPredefiniti() {
        System.out.println("=== FUNCTIONAL INTERFACES PREDEFINITI ===");

        // Function<T, R> - prende T, ritorna R
        Function<String, Integer> length = String::length;
        System.out.println("Function: length('Hello') = " + length.apply("Hello"));

        // Predicate<T> - prende T, ritorna boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Predicate: isEven(4) = " + isEven.test(4));

        // Consumer<T> - prende T, non ritorna nulla
        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        printer.accept("Hello World");

        // Supplier<T> - non prende nulla, ritorna T
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Supplier: random = " + randomSupplier.get());

        // UnaryOperator<T> - Function<T, T>
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println("UnaryOperator: square(5) = " + square.apply(5));

        // BinaryOperator<T> - BiFunction<T, T, T>
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("BinaryOperator: max(5, 3) = " + max.apply(5, 3));

        // BiFunction<T, U, R> - prende T e U, ritorna R
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        System.out.println("BiFunction: repeat('A', 5) = " + repeat.apply("A", 5));

        // BiPredicate<T, U> - prende T e U, ritorna boolean
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println("BiPredicate: 'Hello'.startsWith('He') = " +
                startsWith.test("Hello", "He"));

        // BiConsumer<T, U> - prende T e U, non ritorna nulla
        BiConsumer<String, Integer> printTimes = (s, n) -> {
            for (int i = 0; i < n; i++) {
                System.out.print(s + " ");
            }
            System.out.println();
        };
        System.out.print("BiConsumer: ");
        printTimes.accept("Java", 3);

        System.out.println();
    }

    // ==================== METHOD REFERENCES ====================

    public void methodReferences() {
        System.out.println("=== METHOD REFERENCES ===");

        // 1. Reference a metodo statico: ClassName::staticMethod
        Function<String, Integer> parseInt1 = Integer::parseInt;
        System.out.println("Static method ref: parseInt('123') = " + parseInt1.apply("123"));

        // 2. Reference a metodo di istanza su oggetto specifico: instance::method
        String prefix = "Prefix: ";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println("Instance method ref: " + addPrefix.apply("Hello"));

        // 3. Reference a metodo di istanza su tipo: ClassName::instanceMethod
        Function<String, String> toUpper = String::toUpperCase;
        System.out.println("Instance method ref (type): " + toUpper.apply("hello"));

        // 4. Reference a costruttore: ClassName::new
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> list = listSupplier.get();
        list.add("Item");
        System.out.println("Constructor ref: " + list);

        // 5. Reference a costruttore con parametri
        Function<Integer, List<String>> listWithSize = ArrayList::new;
        List<String> list2 = listWithSize.apply(10);
        System.out.println("Constructor ref (with param): capacity=" + 10);

        // 6. Reference a metodo di superclasse
        Function<String, String> trim = String::trim;
        System.out.println("Method ref: trim('  hello  ') = '" + trim.apply("  hello  ") + "'");

        System.out.println();
    }

    // ==================== PREDICATE EXAMPLES ====================

    public void predicateExamples() {
        System.out.println("=== PREDICATE EXAMPLES ===");

        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicati base
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> greaterThan5 = n -> n > 5;

        // and - combina con AND logico
        Predicate<Integer> evenAndGreaterThan5 = isEven.and(greaterThan5);
        System.out.println("Pari e > 5: " +
                numeri.stream().filter(evenAndGreaterThan5).collect(Collectors.toList()));

        // or - combina con OR logico
        Predicate<Integer> evenOrGreaterThan5 = isEven.or(greaterThan5);
        System.out.println("Pari o > 5: " +
                numeri.stream().filter(evenOrGreaterThan5).collect(Collectors.toList()));

        // negate - nega il predicato
        Predicate<Integer> isOdd = isEven.negate();
        System.out.println("Dispari: " +
                numeri.stream().filter(isOdd).collect(Collectors.toList()));

        // isEqual - predicato di uguaglianza
        Predicate<Integer> isFive = Predicate.isEqual(5);
        System.out.println("È uguale a 5? " + isFive.test(5));

        // not - metodo statico per negare (Java 11+)
        Predicate<Integer> notEven = Predicate.not(isEven);
        System.out.println("Non pari (Predicate.not): " +
                numeri.stream().filter(notEven).collect(Collectors.toList()));

        // Predicati complessi
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isLong = s -> s.length() > 5;
        Predicate<String> startsWithA = s -> s.startsWith("A");

        Predicate<String> complexPredicate = isEmpty.negate()
                .and(isLong)
                .and(startsWithA);

        List<String> parole = Arrays.asList("Apple", "Apricot", "Banana", "A", "");
        System.out.println("Parole non vuote, lunghe > 5, che iniziano con A:");
        System.out.println("  " + parole.stream()
                .filter(complexPredicate)
                .collect(Collectors.toList()));

        System.out.println();
    }

    // ==================== FUNCTION EXAMPLES ====================

    public void functionExamples() {
        System.out.println("=== FUNCTION EXAMPLES ===");

        // Function base
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("square(5) = " + square.apply(5));

        // andThen - composizione (prima this, poi after)
        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> squareThenAddOne = square.andThen(addOne);
        System.out.println("square(5) then addOne = " + squareThenAddOne.apply(5)); // 26

        // compose - composizione inversa (prima before, poi this)
        Function<Integer, Integer> addOneThenSquare = square.compose(addOne);
        System.out.println("addOne then square(5) = " + addOneThenSquare.apply(5)); // 36

        // Function chain
        Function<String, String> removeSpaces = s -> s.replaceAll("\\s+", "");
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, Integer> length = String::length;

        Function<String, Integer> processString = removeSpaces
                .andThen(toUpper)
                .andThen(length);

        System.out.println("Process 'Hello World': " + processString.apply("Hello World"));

        // identity - funzione identità
        Function<String, String> identity = Function.identity();
        System.out.println("Identity('test'): " + identity.apply("test"));

        // Function con tipi diversi
        Function<Integer, String> intToString = Object::toString;
        Function<String, Integer> stringToLength = String::length;
        Function<Integer, Integer> intToLengthOfString = intToString.andThen(stringToLength);
        System.out.println("123 -> '123' -> 3: " + intToLengthOfString.apply(123));

        System.out.println();
    }

    // ==================== CONSUMER EXAMPLES ====================

    public void consumerExamples() {
        System.out.println("=== CONSUMER EXAMPLES ===");

        List<String> lista = new ArrayList<>(Arrays.asList("A", "B", "C"));

        // Consumer base
        Consumer<String> printer = s -> System.out.println("  " + s);
        System.out.println("forEach con Consumer:");
        lista.forEach(printer);

        // andThen - esegue in sequenza
        Consumer<String> toUpperPrinter = s -> System.out.println("  Upper: " + s.toUpperCase());
        Consumer<String> lengthPrinter = s -> System.out.println("  Length: " + s.length());

        Consumer<String> combinedConsumer = toUpperPrinter.andThen(lengthPrinter);
        System.out.println("\nConsumer combinato:");
        combinedConsumer.accept("hello");

        // Consumer per side effects
        List<Integer> numeri = new ArrayList<>();
        Consumer<Integer> addToList = numeri::add;
        Consumer<Integer> printNumber = n -> System.out.print(n + " ");

        Consumer<Integer> addAndPrint = addToList.andThen(printNumber);
        System.out.println("\nAggiungi e stampa:");
        Stream.of(1, 2, 3, 4, 5).forEach(addAndPrint);
        System.out.println("\nLista: " + numeri);

        // Consumer con map
        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> putInMap = map::put;
        putInMap.accept("One", 1);
        putInMap.accept("Two", 2);
        System.out.println("\nMap: " + map);

        System.out.println();
    }

    // ==================== SUPPLIER EXAMPLES ====================

    public void supplierExamples() {
        System.out.println("=== SUPPLIER EXAMPLES ===");

        // Supplier base
        Supplier<String> helloSupplier = () -> "Hello World";
        System.out.println("Supplier: " + helloSupplier.get());

        // Supplier per lazy evaluation
        Supplier<Double> expensiveComputation = () -> {
            System.out.println("  Eseguendo computazione costosa...");
            return Math.random() * 1000;
        };

        System.out.println("Supplier lazy (non ancora chiamato)");
        System.out.println("Ora chiamo get(): " + expensiveComputation.get());

        // Supplier per factory
        Supplier<List<String>> listFactory = ArrayList::new;
        List<String> list1 = listFactory.get();
        List<String> list2 = listFactory.get();
        System.out.println("Due liste diverse: " + (list1 != list2));

        // Supplier con Optional
        Supplier<String> defaultValue = () -> "Default";
        Optional<String> empty = Optional.empty();
        System.out.println("Optional orElseGet: " + empty.orElseGet(defaultValue));

        // Supplier per generazione valori
        Supplier<Integer> randomIntSupplier = () -> (int) (Math.random() * 100);
        System.out.println("5 numeri random:");
        Stream.generate(randomIntSupplier)
                .limit(5)
                .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // Supplier per UUID
        Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
        System.out.println("UUID: " + uuidSupplier.get());

        System.out.println();
    }

    // ==================== OPERATOR EXAMPLES ====================

    public void operatorExamples() {
        System.out.println("=== OPERATOR EXAMPLES ===");

        // UnaryOperator<T> extends Function<T, T>
        UnaryOperator<Integer> increment = x -> x + 1;
        UnaryOperator<Integer> square = x -> x * x;

        System.out.println("UnaryOperator increment(5): " + increment.apply(5));
        System.out.println("UnaryOperator square(5): " + square.apply(5));

        // Composizione di UnaryOperator
        // UnaryOperator<Integer> incrementThenSquare = increment.andThen(square);
        UnaryOperator<Integer> incrementThenSquare = x -> square.apply(increment.apply(x));

        System.out.println("increment then square(5): " + incrementThenSquare.apply(5)); // 36

        // BinaryOperator<T> extends BiFunction<T, T, T>
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        BinaryOperator<String> concat = (a, b) -> a + b;

        System.out.println("\nBinaryOperator sum(5, 3): " + sum.apply(5, 3));
        System.out.println("BinaryOperator multiply(5, 3): " + multiply.apply(5, 3));
        System.out.println("BinaryOperator concat('Hello', 'World'): " +
                concat.apply("Hello", "World"));

        // BinaryOperator.maxBy e minBy
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compareTo);

        System.out.println("\nmaxBy(5, 3): " + max.apply(5, 3));
        System.out.println("minBy(5, 3): " + min.apply(5, 3));

        // Uso con reduce
        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5);
        int somma = numeri.stream().reduce(0, sum);
        int prodotto = numeri.stream().reduce(1, multiply);

        System.out.println("\nReduce con sum: " + somma);
        System.out.println("Reduce con multiply: " + prodotto);

        // IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator
        IntUnaryOperator doubleIt = x -> x * 2;
        System.out.println("\nIntUnaryOperator doubleIt(5): " + doubleIt.applyAsInt(5));

        // IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator
        IntBinaryOperator intSum = (a, b) -> a + b;
        System.out.println("IntBinaryOperator sum(5, 3): " + intSum.applyAsInt(5, 3));

        System.out.println();
    }

    // ==================== BI-INTERFACES EXAMPLES ====================

    public void biInterfacesExamples() {
        System.out.println("=== BI-INTERFACES EXAMPLES ===");

        // BiFunction<T, U, R>
        BiFunction<String, Integer, String> substring = String::substring;
        System.out.println("BiFunction substring('Hello', 1): " +
                substring.apply("Hello", 1));

        BiFunction<Integer, Integer, Integer> power = (base, exp) -> (int) Math.pow(base, exp);
        System.out.println("BiFunction power(2, 3): " + power.apply(2, 3));

        // BiPredicate<T, U>
        BiPredicate<String, Integer> longerThan = (s, len) -> s.length() > len;
        System.out.println("\nBiPredicate 'Hello' longer than 3: " +
                longerThan.test("Hello", 3));

        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        System.out.println("BiPredicate 5 > 3: " + isGreater.test(5, 3));

        // BiConsumer<T, U>
        BiConsumer<String, Integer> printNTimes = (s, n) -> {
            for (int i = 0; i < n; i++) {
                System.out.print(s + " ");
            }
            System.out.println();
        };
        System.out.print("\nBiConsumer: ");
        printNTimes.accept("Java", 3);

        // Uso con forEach su Map
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        System.out.println("Map forEach con BiConsumer:");
        map.forEach((k, v) -> System.out.println("  " + k + " = " + v));

        // BiConsumer andThen
        BiConsumer<String, Integer> logger = (k, v) -> System.out.println("  Logging: " + k + " -> " + v);
        BiConsumer<String, Integer> validator = (k, v) -> {
            if (v < 0)
                System.out.println("  Warning: negative value for " + k);
        };

        BiConsumer<String, Integer> combined = logger.andThen(validator);
        combined.accept("Test", -5);

        System.out.println();
    }

    // ==================== COMPOSIZIONE LAMBDA ====================

    public void composizioneLambda() {
        System.out.println("=== COMPOSIZIONE LAMBDA ===");

        // Function composition
        Function<String, String> trim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        Function<String, String> addPrefix = s -> "*** " + s + " ***";

        Function<String, String> process = trim
                .andThen(toUpper)
                .andThen(addPrefix);

        System.out.println("Composizione Function:");
        System.out.println(process.apply("  hello world  "));

        // Predicate composition
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> lessThan100 = n -> n < 100;

        Predicate<Integer> complexPredicate = isPositive
                .and(isEven)
                .and(lessThan100);

        System.out.println("\nComposizione Predicate:");
        System.out.println("Test 50: " + complexPredicate.test(50));
        System.out.println("Test -2: " + complexPredicate.test(-2));
        System.out.println("Test 101: " + complexPredicate.test(101));

        // Consumer composition
        Consumer<String> print = System.out::println;
        Consumer<String> logLength = s -> System.out.println("  Length: " + s.length());
        Consumer<String> logUpper = s -> System.out.println("  Upper: " + s.toUpperCase());

        Consumer<String> fullProcessor = print
                .andThen(logLength)
                .andThen(logUpper);

        System.out.println("\nComposizione Consumer:");
        fullProcessor.accept("Hello");

        // Composizione multipla
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x * 2;
        Function<Integer, Integer> f3 = x -> x - 5;

        Function<Integer, Integer> composed = f1.andThen(f2).andThen(f3);
        System.out.println("\n(((x + 1) * 2) - 5) con x=10: " + composed.apply(10));

        System.out.println();
    }

    // ==================== LAMBDA CON CLOSURE ====================

    public void lambdaConClosure() {
        System.out.println("=== LAMBDA CON CLOSURE ===");

        // Variabili effectively final
        String prefix = "Hello";
        Function<String, String> addPrefix = s -> prefix + " " + s;
        System.out.println("Closure: " + addPrefix.apply("World"));

        // Nota: prefix deve essere effectively final
        // prefix = "Hi"; // Questo causerebbe errore di compilazione

        // Closure con variabili locali
        int multiplier = 10;
        Function<Integer, Integer> multiply = x -> x * multiplier;
        System.out.println("Closure con int: " + multiply.apply(5));

        // Closure per creare factory
        Function<Integer, Function<Integer, Integer>> multiplierFactory = factor -> number -> number * factor;

        Function<Integer, Integer> times5 = multiplierFactory.apply(5);
        Function<Integer, Integer> times10 = multiplierFactory.apply(10);

        System.out.println("\nFactory closure:");
        System.out.println("times5(3): " + times5.apply(3));
        System.out.println("times10(3): " + times10.apply(3));

        // Closure con liste mutabili
        List<String> log = new ArrayList<>();
        Consumer<String> logger = s -> {
            log.add(s);
            System.out.println("  Logged: " + s);
        };

        System.out.println("\nClosure con lista mutabile:");
        logger.accept("Message 1");
        logger.accept("Message 2");
        System.out.println("Log contents: " + log);

        // Closure per contatori
        int[] counter = { 0 }; // array per mutabilità
        Runnable incrementer = () -> counter[0]++;

        System.out.println("\nClosure con contatore:");
        System.out.println("Before: " + counter[0]);
        incrementer.run();
        incrementer.run();
        incrementer.run();
        System.out.println("After 3 increments: " + counter[0]);

        System.out.println();
    }

    // ==================== CUSTOM FUNCTIONAL INTERFACES ====================

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T value);

        // Metodi default sono permessi
        default Validator<T> and(Validator<T> other) {
            return value -> this.validate(value) && other.validate(value);
        }

        default Validator<T> or(Validator<T> other) {
            return value -> this.validate(value) || other.validate(value);
        }
    }

    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    @FunctionalInterface
    interface Factory<T> {
        T create();

        static <T> Factory<T> of(Supplier<T> supplier) {
            return supplier::get;
        }
    }

    public void customFunctionalInterfaces() {
        System.out.println("=== CUSTOM FUNCTIONAL INTERFACES ===");

        // Calculator
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("Calculator add(5, 3): " + add.calculate(5, 3));
        System.out.println("Calculator multiply(5, 3): " + multiply.calculate(5, 3));

        // Validator
        Validator<String> notEmpty = s -> s != null && !s.isEmpty();
        Validator<String> minLength = s -> s.length() >= 3;
        Validator<String> startsWithA = s -> s.startsWith("A");

        Validator<String> complexValidator = notEmpty.and(minLength).and(startsWithA);

        System.out.println("\nValidator 'Apple': " + complexValidator.validate("Apple"));
        System.out.println("Validator 'A': " + complexValidator.validate("A"));
        System.out.println("Validator 'Banana': " + complexValidator.validate("Banana"));

        // TriFunction
        TriFunction<Integer, Integer, Integer, Integer> sumThree = (a, b, c) -> a + b + c;

        System.out.println("\nTriFunction sum(1, 2, 3): " + sumThree.apply(1, 2, 3));

        // Factory
        Factory<List<String>> listFactory = ArrayList::new;
        List<String> list = listFactory.create();
        list.add("Item");
        System.out.println("Factory: " + list);

        Factory<StringBuilder> sbFactory = Factory.of(StringBuilder::new);
        StringBuilder sb = sbFactory.create();
        sb.append("Test");
        System.out.println("Factory StringBuilder: " + sb);

        System.out.println();
    }

    // ==================== LAMBDA CON STREAM ====================

    public void lambdaConStream() {
        System.out.println("=== LAMBDA CON STREAM ===");

        List<Persona> persone = Arrays.asList(
                new Persona("Mario", 35, 45000),
                new Persona("Luigi", 28, 38000),
                new Persona("Peach", 32, 52000),
                new Persona("Bowser", 45, 60000),
                new Persona("Yoshi", 25, 32000));

        // filter con lambda
        System.out.println("Persone con età > 30:");
        persone.stream()
                .filter(p -> p.getEta() > 30)
                .forEach(p -> System.out.println("  " + p));

        // map con lambda
        System.out.println("\nNomi in maiuscolo:");
        persone.stream()
                .map(p -> p.getNome().toUpperCase())
                .forEach(nome -> System.out.println("  " + nome));

        // sorted con lambda
        System.out.println("\nOrdinati per stipendio (desc):");
        persone.stream()
                .sorted((p1, p2) -> Double.compare(p2.getStipendio(), p1.getStipendio()))
                .forEach(p -> System.out.println("  " + p));

        // reduce con lambda
        double totaleStipendi = persone.stream()
                .map(Persona::getStipendio)
                .reduce(0.0, (a, b) -> a + b);
        System.out.println("\nTotale stipendi: €" + totaleStipendi);

        // collect con lambda complessa
        Map<String, Double> stipendiPerFasciaEta = persone.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getEta() < 30 ? "Giovani" : p.getEta() < 40 ? "Adulti" : "Senior",
                        Collectors.averagingDouble(Persona::getStipendio)));
        System.out.println("\nMedia stipendi per fascia età: " + stipendiPerFasciaEta);

        // anyMatch, allMatch, noneMatch
        boolean haGiovani = persone.stream().anyMatch(p -> p.getEta() < 30);
        boolean tuttiMaggiorenni = persone.stream().allMatch(p -> p.getEta() >= 18);
        boolean nessunoCentenne = persone.stream().noneMatch(p -> p.getEta() >= 100);

        System.out.println("\nHa giovani (<30): " + haGiovani);
        System.out.println("Tutti maggiorenni: " + tuttiMaggiorenni);
        System.out.println("Nessun centenne: " + nessunoCentenne);

        System.out.println();
    }

    // ==================== METHOD REFERENCES AVANZATI ====================

    public void methodReferencesAvanzati() {
        System.out.println("=== METHOD REFERENCES AVANZATI ===");

        List<String> parole = Arrays.asList("Apple", "Banana", "Cherry");

        // Static method reference
        parole.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Instance method reference su tipo
        System.out.println("\nLunghezze:");
        parole.stream()
                .map(String::length)
                .forEach(System.out::println);

        // Constructor reference
        List<Integer> lunghezze = parole.stream()
                .map(String::length)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Collection con constructor ref: " + lunghezze);

        // Array constructor reference
        String[] array = parole.stream().toArray(String[]::new);
        System.out.println("Array: " + Arrays.toString(array));

        // Method reference su oggetto specifico
        StringBuilder sb = new StringBuilder();
        parole.forEach(sb::append);
        System.out.println("\nStringBuilder result: " + sb);

        // Method reference con multiple parametri
        BiFunction<String, String, Boolean> startsWith = String::startsWith;
        System.out.println("\n'Hello' starts with 'He': " + startsWith.apply("Hello", "He"));

        // Bounded method reference
        String prefix = "Prefix-";
        Function<String, String> addPrefix = prefix::concat;
        System.out.println("Bounded ref: " + addPrefix.apply("Test"));

        // Constructor reference per oggetti
        Supplier<Persona> personaSupplier = () -> new Persona("Default", 0, 0);
        Function<String, Persona> personaFactory = nome -> new Persona(nome, 0, 0);

        Persona p1 = personaSupplier.get();
        Persona p2 = personaFactory.apply("Mario");
        System.out.println("\nConstructor ref: " + p1 + ", " + p2);

        System.out.println();
    }

    // ==================== LAMBDA RICORSIVE ====================

    public void lambdaRicorsive() {
        System.out.println("=== LAMBDA RICORSIVE ===");

        // Fattoriale con lambda ricorsiva
        UnaryOperator<Integer>[] factorial = new UnaryOperator[1];
        factorial[0] = n -> n <= 1 ? 1 : n * factorial[0].apply(n - 1);

        System.out.println("Factorial(5): " + factorial[0].apply(5));
        System.out.println("Factorial(7): " + factorial[0].apply(7));

        // Fibonacci con lambda ricorsiva
        UnaryOperator<Integer>[] fibonacci = new UnaryOperator[1];
        fibonacci[0] = n -> n <= 1 ? n : fibonacci[0].apply(n - 1) + fibonacci[0].apply(n - 2);

        System.out.println("\nFibonacci(10): " + fibonacci[0].apply(10));

        // Somma dei numeri da 1 a n
        UnaryOperator<Integer>[] sumToN = new UnaryOperator[1];
        sumToN[0] = n -> n <= 0 ? 0 : n + sumToN[0].apply(n - 1);

        System.out.println("Sum to 100: " + sumToN[0].apply(100));

        // Lambda ricorsiva con BiFunction
        BiFunction<Integer, Integer, Integer>[] gcd = new BiFunction[1];
        gcd[0] = (a, b) -> b == 0 ? a : gcd[0].apply(b, a % b);

        System.out.println("\nGCD(48, 18): " + gcd[0].apply(48, 18));

        System.out.println();
    }

    // ==================== CURRYING E PARTIAL APPLICATION ====================

    public void curryingPartialApplication() {
        System.out.println("=== CURRYING E PARTIAL APPLICATION ===");

        // Currying: trasforma f(a, b, c) in f(a)(b)(c)
        Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedSum = a -> b -> c -> a + b + c;

        // Applicazione parziale
        Function<Integer, Function<Integer, Integer>> addTo5 = curriedSum.apply(5);
        Function<Integer, Integer> addTo5And3 = addTo5.apply(3);
        int result = addTo5And3.apply(2);

        System.out.println("Curried sum(5)(3)(2): " + result);

        // Partial application diretta
        Function<Integer, Integer> add5 = curriedSum.apply(5).apply(0);
        System.out.println("add5(10): " + add5.apply(10));

        // Currying per configurazione
        Function<String, Function<String, Function<String, String>>> formatter = prefix -> suffix -> content -> prefix
                + content + suffix;

        Function<String, Function<String, String>> htmlFormatter = formatter.apply("<html>");
        Function<String, String> htmlBodyFormatter = htmlFormatter.apply("</html>");

        System.out.println("\nHTML formatter:");
        System.out.println(htmlBodyFormatter.apply("<body>Content</body>"));

        // Currying per validazione
        Function<Integer, Function<Integer, Predicate<Integer>>> rangeValidator = min -> max -> value -> value >= min
                && value <= max;

        Predicate<Integer> ageValidator = rangeValidator.apply(18).apply(65);
        System.out.println("\nAge validator (18-65):");
        System.out.println("  25: " + ageValidator.test(25));
        System.out.println("  70: " + ageValidator.test(70));

        // Partial application per operazioni matematiche
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        Function<Double, Double> double_ = x -> multiply.apply(2.0, x);
        Function<Double, Double> triple = x -> multiply.apply(3.0, x);

        System.out.println("\nPartial multiplication:");
        System.out.println("double(5): " + double_.apply(5.0));
        System.out.println("triple(5): " + triple.apply(5.0));

        System.out.println();
    }

    // ==================== HIGHER-ORDER FUNCTIONS ====================

    public void higherOrderFunctions() {
        System.out.println("=== HIGHER-ORDER FUNCTIONS ===");

        // Funzione che ritorna funzione
        Function<String, Function<String, String>> createGreeter = greeting -> name -> greeting + ", " + name + "!";

        Function<String, String> helloGreeter = createGreeter.apply("Hello");
        Function<String, String> hiGreeter = createGreeter.apply("Hi");

        System.out.println("Higher-order function:");
        System.out.println(helloGreeter.apply("Mario"));
        System.out.println(hiGreeter.apply("Luigi"));

        // Funzione che prende funzione come parametro
        Function<Function<Integer, Integer>, Function<Integer, Integer>> applyTwice = f -> x -> f.apply(f.apply(x));

        Function<Integer, Integer> increment = x -> x + 1;
        Function<Integer, Integer> double_ = x -> x * 2;

        System.out.println("\napplyTwice increment(5): " + applyTwice.apply(increment).apply(5));
        System.out.println("applyTwice double(5): " + applyTwice.apply(double_).apply(5));

        // Compose generico - definito come variabile Function
        Function<Function<Integer, Integer>[], Function<Integer, Integer>> composeAll = functions -> x -> {
            Integer result = x;
            for (Function<Integer, Integer> f : functions) {
                result = f.apply(result);
            }
            return result;
        };

        @SuppressWarnings("unchecked")
        Function<Integer, Integer> pipeline = composeAll.apply(new Function[] {
                (Function<Integer, Integer>) (x -> x + 1),
                (Function<Integer, Integer>) (x -> x * 2),
                (Function<Integer, Integer>) (x -> x - 3)
        });

        System.out.println("\nComposed pipeline (5): " + pipeline.apply(5));

        // Filter generico - definito come variabile BiFunction
        BiFunction<List<Integer>, Predicate<Integer>, List<Integer>> filterList = (list, predicate) -> list.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> pari = filterList.apply(numeri, n -> n % 2 == 0);
        List<Integer> maggioriDi5 = filterList.apply(numeri, n -> n > 5);

        System.out.println("\nFilter generico:");
        System.out.println("Pari: " + pari);
        System.out.println("Maggiori di 5: " + maggioriDi5);

        // Map generico - definito come variabile BiFunction
        BiFunction<List<Integer>, Function<Integer, String>, List<String>> mapListToString = (list, mapper) -> list
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        BiFunction<List<Integer>, Function<Integer, Integer>, List<Integer>> mapListToInt = (list, mapper) -> list
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        List<String> stringhe = mapListToString.apply(numeri, Object::toString);
        List<Integer> quadrati = mapListToInt.apply(numeri, x -> x * x);

        System.out.println("\nMap generico:");
        System.out.println("To String: " + stringhe);
        System.out.println("Quadrati: " + quadrati);

        System.out.println();
    }

    // ==================== CASI D'USO REALI ====================

    public void casiUsoReali() {
        System.out.println("=== CASI D'USO REALI ===");

        // 1. Validazione input
        Validator<String> emailValidator = email -> email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");

        Validator<String> passwordValidator = pwd -> pwd != null && pwd.length() >= 8 && pwd.matches(".*[0-9].*");

        System.out.println("Validazione:");
        System.out.println("Email 'test@example.com': " +
                emailValidator.validate("test@example.com"));
        System.out.println("Password 'password123': " +
                passwordValidator.validate("password123"));

        // 2. Strategy pattern con lambda
        Map<String, Function<Double, Double>> taxStrategies = new HashMap<>();
        taxStrategies.put("IT", amount -> amount * 0.22);
        taxStrategies.put("DE", amount -> amount * 0.19);
        taxStrategies.put("FR", amount -> amount * 0.20);

        Function<Double, Double> italianTax = taxStrategies.get("IT");
        System.out.println("\nTax strategy IT per €100: €" + italianTax.apply(100.0));

        // 3. Builder pattern con lambda
        Consumer<PersonBuilder> builderConfig = builder -> builder
                .setNome("Mario")
                .setEta(35)
                .setStipendio(45000);

        PersonBuilder builder = new PersonBuilder();
        builderConfig.accept(builder);
        Persona persona = builder.build();
        System.out.println("\nBuilder pattern: " + persona);

        // 4. Event handling
        List<Consumer<String>> eventHandlers = new ArrayList<>();
        eventHandlers.add(event -> System.out.println("  Handler 1: " + event));
        eventHandlers.add(event -> System.out.println("  Handler 2: " + event.toUpperCase()));
        eventHandlers.add(event -> System.out.println("  Handler 3: length=" + event.length()));

        System.out.println("\nEvent handling:");
        eventHandlers.forEach(handler -> handler.accept("Click"));

        // 5. Lazy initialization
        Supplier<ExpensiveObject> lazyObject = () -> new ExpensiveObject();
        System.out.println("\nLazy object creato (non ancora inizializzato)");
        System.out.println("Ora inizializzo...");
        ExpensiveObject obj = lazyObject.get();

        // 6. Memoization

        Map<Integer, Integer> cache = new HashMap<>();

        Function<Integer, Integer>[] fibonacci = new Function[1];
        fibonacci[0] = n -> {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }
            int result = n <= 1 ? n : fibonacci[0].apply(n - 1) + fibonacci[0].apply(n - 2);
            cache.put(n, result);
            return result;
        };

        /*
         * private int fibonacci(int n) {
         * if (cache.containsKey(n)) {
         * return cache.get(n);
         * }
         * int result = n <= 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
         * cache.put(n, result);
         * return result;
         * }
         */

        System.out.println("\nMemoization fibonacci(35): " + fibonacci[0].apply(35));

        // 7. Pipeline processing
        Function<String, String> trimAndLower = s -> s.trim().toLowerCase();
        Function<String, String> removeSpaces = s -> s.replaceAll("\\s+", "");
        Function<String, Integer> countVowels = s -> (int) s.chars().filter(c -> "aeiou".indexOf(c) >= 0).count();

        Function<String, Integer> textProcessor = trimAndLower
                .andThen(removeSpaces)
                .andThen(countVowels);

        System.out.println("\nPipeline: vocali in '  Hello World  ': " +
                textProcessor.apply("  Hello World  "));

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");

        System.out.println("1. PREFERISCI METHOD REFERENCES:");
        System.out.println("   ✓ list.forEach(System.out::println)");
        System.out.println("   ✗ list.forEach(s -> System.out.println(s))");

        System.out.println("\n2. USA FUNCTIONAL INTERFACES PREDEFINITI:");
        System.out.println("   ✓ Function<String, Integer> length = String::length");
        System.out.println("   ✗ Creare interfaccia custom quando non necessario");

        System.out.println("\n3. EVITA LAMBDA TROPPO COMPLESSE:");
        System.out.println("   ✓ Estrai logica complessa in metodi separati");
        System.out.println("   ✗ Lambda con molte righe di codice");

        System.out.println("\n4. VARIABILI EFFECTIVELY FINAL:");
        System.out.println("   Le variabili esterne usate in lambda devono essere final o effectively final");

        System.out.println("\n5. USA COMPOSIZIONE:");
        System.out.println("   ✓ function1.andThen(function2).andThen(function3)");
        System.out.println("   Crea pipeline riusabili e testabili");

        System.out.println("\n6. EVITA SIDE EFFECTS:");
        System.out.println("   Le lambda dovrebbero essere pure (senza side effects)");
        System.out.println("   Specialmente in operazioni parallele");

        System.out.println("\n7. USA LAMBDA PER BREVITÀ:");
        System.out.println("   Le lambda brillano per codice conciso");
        System.out.println("   Per logica complessa, considera classi tradizionali");

        System.out.println("\n8. TYPE INFERENCE:");
        System.out.println("   ✓ (a, b) -> a + b");
        System.out.println("   ✗ (Integer a, Integer b) -> a + b  (quando non necessario)");

        System.out.println("\n9. PARENTESI OPZIONALI:");
        System.out.println("   ✓ s -> s.length()  (un parametro)");
        System.out.println("   ✓ (a, b) -> a + b  (multipli parametri)");

        System.out.println("\n10. DOCUMENTA LAMBDA COMPLESSE:");
        System.out.println("    Usa commenti per lambda non ovvie");
        System.out.println("    Considera variabili con nomi descrittivi");

        System.out.println();
    }

    // ==================== CLASSI DI SUPPORTO ====================

    static class Persona {
        private String nome;
        private int eta;
        private double stipendio;

        public Persona(String nome, int eta, double stipendio) {
            this.nome = nome;
            this.eta = eta;
            this.stipendio = stipendio;
        }

        public String getNome() {
            return nome;
        }

        public int getEta() {
            return eta;
        }

        public double getStipendio() {
            return stipendio;
        }

        @Override
        public String toString() {
            return String.format("%s(%d, €%.0f)", nome, eta, stipendio);
        }
    }

    static class PersonBuilder {
        private String nome;
        private int eta;
        private double stipendio;

        public PersonBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public PersonBuilder setEta(int eta) {
            this.eta = eta;
            return this;
        }

        public PersonBuilder setStipendio(double stipendio) {
            this.stipendio = stipendio;
            return this;
        }

        public Persona build() {
            return new Persona(nome, eta, stipendio);
        }
    }

    static class ExpensiveObject {
        public ExpensiveObject() {
            System.out.println("  ExpensiveObject creato (operazione costosa simulata)");
        }
    }

    // Helper method per higher-order functions
    private <T> Function<T, T> applyTwice(Function<T, T> f) {
        return x -> f.apply(f.apply(x));
    }

    @SafeVarargs
    private final <T> Function<T, T> composeAll(Function<T, T>... functions) {
        return x -> {
            T result = x;
            for (Function<T, T> f : functions) {
                result = f.apply(result);
            }
            return result;
        };
    }

    private <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
