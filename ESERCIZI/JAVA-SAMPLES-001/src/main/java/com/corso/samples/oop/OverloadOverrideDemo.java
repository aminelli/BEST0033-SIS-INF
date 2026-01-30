package com.corso.samples.oop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * DIMOSTRAZIONE COMPLETA E AVANZATA SU OVERLOAD E OVERRIDE IN JAVA
 * 
 * Questo modulo copre tutti gli scenari possibili su overloading e overriding:
 * 
 * OVERLOADING (Sovraccarico) - Compile-time Polymorphism:
 * 1. Overload base con diversi tipi di parametri
 * 2. Overload con numero diverso di parametri
 * 3. Overload con ordine diverso dei parametri
 * 4. Overload con autoboxing/unboxing
 * 5. Overload con varargs
 * 6. Overload con type promotion
 * 7. Overload con generics e type erasure
 * 8. Overload di costruttori
 * 9. Ambiguità e risoluzione
 * 10. Overload con null
 * 
 * OVERRIDE (Sovrascrittura) - Runtime Polymorphism:
 * 11. Override base con @Override
 * 12. Covariant return types
 * 13. Override e modificatori di accesso
 * 14. Override e eccezioni
 * 15. Override di metodi Object
 * 16. Override con final
 * 17. Override con generics
 * 18. Bridge methods
 * 19. Hiding vs Override (metodi statici)
 * 20. Template method pattern
 * 
 * SCENARI AVANZATI:
 * 21. Overload + Override combinati
 * 22. Polimorfismo e dynamic dispatch
 * 23. Method resolution
 * 24. Best practices
 */
public class OverloadOverrideDemo {

    // ===========================================
    // SEZIONE 1: OVERLOAD BASE - DIVERSI TIPI
    // ===========================================

    /**
     * Overload con tipi di parametri diversi
     */
    static class Calculator {
        // Overload con int
        public int add(int a, int b) {
            System.out.println("add(int, int) chiamato");
            return a + b;
        }

        // Overload con double
        public double add(double a, double b) {
            System.out.println("add(double, double) chiamato");
            return a + b;
        }

        // Overload con String
        public String add(String a, String b) {
            System.out.println("add(String, String) chiamato");
            return a + b;
        }

        // Overload con long
        public long add(long a, long b) {
            System.out.println("add(long, long) chiamato");
            return a + b;
        }
    }

    // ===========================================
    // SEZIONE 2: OVERLOAD - NUMERO PARAMETRI
    // ===========================================

    /**
     * Overload con numero diverso di parametri
     */
    static class Printer {
        public void print(String message) {
            System.out.println("Messaggio: " + message);
        }

        public void print(String message, int times) {
            for (int i = 0; i < times; i++) {
                System.out.println(message);
            }
        }

        public void print(String message, int times, String separator) {
            for (int i = 0; i < times; i++) {
                System.out.print(message);
                if (i < times - 1) {
                    System.out.print(separator);
                }
            }
            System.out.println();
        }
    }

    // ===========================================
    // SEZIONE 3: OVERLOAD - ORDINE PARAMETRI
    // ===========================================

    /**
     * Overload con ordine diverso dei parametri
     */
    static class DataFormatter {
        public String format(String name, int age) {
            return "Nome: " + name + ", Età: " + age;
        }

        public String format(int age, String name) {
            return "Età: " + age + ", Nome: " + name;
        }

        public String format(String name, String city) {
            return "Nome: " + name + ", Città: " + city;
        }

        public String format(boolean flag, String message) {
            return flag ? message.toUpperCase() : message.toLowerCase();
        }
    }

    // ===========================================
    // SEZIONE 4: OVERLOAD - AUTOBOXING/UNBOXING
    // ===========================================

    /**
     * Overload con autoboxing e unboxing
     */
    static class BoxingDemo {
        // Primitivo
        public void process(int value) {
            System.out.println("process(int): " + value);
        }

        // Wrapper
        public void process(Integer value) {
            System.out.println("process(Integer): " + value);
        }

        // Il compilatore preferisce il tipo esatto
        public void test() {
            int primitivo = 5;
            Integer wrapper = 10;
            
            process(primitivo);  // Chiama process(int)
            process(wrapper);    // Chiama process(Integer)
            process(15);         // Chiama process(int) - letterale int
        }
    }

    // ===========================================
    // SEZIONE 5: OVERLOAD - VARARGS
    // ===========================================

    /**
     * Overload con varargs
     */
    static class VarargsDemo {
        // Metodo senza parametri
        public int sum() {
            System.out.println("sum() - nessun parametro");
            return 0;
        }

        // Metodo con un parametro
        public int sum(int a) {
            System.out.println("sum(int) chiamato");
            return a;
        }

        // Metodo con due parametri
        public int sum(int a, int b) {
            System.out.println("sum(int, int) chiamato");
            return a + b;
        }

        // Metodo con varargs - ha la precedenza più bassa
        public int sum(int... numbers) {
            System.out.println("sum(int...) chiamato con " + numbers.length + " parametri");
            int total = 0;
            for (int num : numbers) {
                total += num;
            }
            return total;
        }

        // Varargs con parametro fisso
        public String concat(String separator, String... strings) {
            return String.join(separator, strings);
        }
    }

    // ===========================================
    // SEZIONE 6: OVERLOAD - TYPE PROMOTION
    // ===========================================

    /**
     * Type promotion nell'overload
     */
    static class PromotionDemo {
        public void process(int value) {
            System.out.println("process(int): " + value);
        }

        public void process(long value) {
            System.out.println("process(long): " + value);
        }

        // byte -> short -> int -> long -> float -> double
        public void testPromotion() {
            byte b = 5;
            short s = 10;
            
            // byte viene promosso a int
            process(b);  // Chiama process(int)
            
            // short viene promosso a int
            process(s);  // Chiama process(int)
            
            // Se non c'è int, viene promosso a long
            // process(char c) non esiste, promuove a int
        }
    }

    // ===========================================
    // SEZIONE 7: OVERLOAD - GENERICS
    // ===========================================

    /**
     * Overload con generics e type erasure
     */
    static class GenericOverload {
        // NOTA: Questi due metodi NON possono coesistere per type erasure
        // public void process(List<String> list) { }
        // public void process(List<Integer> list) { }
        
        // Soluzione: usare nomi diversi o parametri aggiuntivi
        public void processStrings(List<String> list) {
            System.out.println("Processing strings: " + list);
        }

        public void processIntegers(List<Integer> list) {
            System.out.println("Processing integers: " + list);
        }

        // Overload con tipo raw e generico
        @SuppressWarnings("rawtypes")
        public void process(List list) {
            System.out.println("process(List) - raw type");
        }

        public <T> void process(List<T> list, Class<T> type) {
            System.out.println("process(List<T>, Class<T>) - tipo: " + type.getSimpleName());
        }
    }

    // ===========================================
    // SEZIONE 8: OVERLOAD - COSTRUTTORI
    // ===========================================

    /**
     * Overload di costruttori
     */
    static class Person {
        private String name;
        private int age;
        private String city;

        // Costruttore senza parametri
        public Person() {
            this("Unknown", 0, "Unknown");
            System.out.println("Costruttore: Person()");
        }

        // Costruttore con nome
        public Person(String name) {
            this(name, 0, "Unknown");
            System.out.println("Costruttore: Person(String)");
        }

        // Costruttore con nome ed età
        public Person(String name, int age) {
            this(name, age, "Unknown");
            System.out.println("Costruttore: Person(String, int)");
        }

        // Costruttore completo
        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
            System.out.println("Costruttore: Person(String, int, String)");
        }

        // Copy constructor
        public Person(Person other) {
            this(other.name, other.age, other.city);
            System.out.println("Copy constructor");
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
        }
    }

    // ===========================================
    // SEZIONE 9: OVERLOAD - AMBIGUITÀ
    // ===========================================

    /**
     * Ambiguità nell'overload
     */
    static class AmbiguityDemo {
        // Caso 1: Ambiguità con null
        public void process(String str) {
            System.out.println("process(String): " + str);
        }

        public void process(Integer num) {
            System.out.println("process(Integer): " + num);
        }

        // process(null) è ambiguo! Errore di compilazione

        // Caso 2: Ambiguità con varargs
        public void print(int... numbers) {
            System.out.println("print(int...)");
        }

        public void print(long... numbers) {
            System.out.println("print(long...)");
        }

        // print() senza argomenti è ambiguo!

        // Caso 3: Ambiguità con autoboxing e widening
        public void execute(long value) {
            System.out.println("execute(long)");
        }

        public void execute(Integer value) {
            System.out.println("execute(Integer)");
        }

        // execute(5) preferisce widening a long, non autoboxing a Integer
    }

    // ===========================================
    // SEZIONE 10: OVERLOAD CON NULL
    // ===========================================

    /**
     * Gestione di null nell'overload
     */
    static class NullOverloadDemo {
        public void handle(Object obj) {
            System.out.println("handle(Object)");
        }

        public void handle(String str) {
            System.out.println("handle(String)");
        }

        public void handle(Integer num) {
            System.out.println("handle(Integer)");
        }

        public void test() {
            // Il compilatore sceglie il tipo più specifico
            handle((String) null);   // handle(String)
            handle((Integer) null);  // handle(Integer)
            handle((Object) null);   // handle(Object)
            
            // handle(null); // ERRORE: ambiguo tra String e Integer
        }
    }

    // ===========================================
    // SEZIONE 11: OVERRIDE BASE
    // ===========================================

    /**
     * Override base con @Override annotation
     */
    static class Animal {
        public void makeSound() {
            System.out.println("Animal makes a sound");
        }

        public void eat() {
            System.out.println("Animal eats");
        }

        public void sleep() {
            System.out.println("Animal sleeps");
        }
    }

    static class Dog extends Animal {
        // @Override garantisce che stiamo effettivamente sovrascrivendo
        @Override
        public void makeSound() {
            System.out.println("Dog barks: Woof!");
        }

        @Override
        public void eat() {
            System.out.println("Dog eats dog food");
        }

        // Non override - metodo nuovo
        public void fetch() {
            System.out.println("Dog fetches the ball");
        }
    }

    static class Cat extends Animal {
        @Override
        public void makeSound() {
            System.out.println("Cat meows: Meow!");
        }

        @Override
        public void sleep() {
            System.out.println("Cat sleeps 16 hours a day");
        }
    }

    // ===========================================
    // SEZIONE 12: COVARIANT RETURN TYPES
    // ===========================================

    /**
     * Override con covariant return types
     */
    static class Vehicle {
        public Vehicle getCopy() {
            System.out.println("Vehicle.getCopy()");
            return new Vehicle();
        }
    }

    static class Car extends Vehicle {
        private String model;

        public Car(String model) {
            this.model = model;
        }

        // Covariant return: Car invece di Vehicle
        @Override
        public Car getCopy() {
            System.out.println("Car.getCopy()");
            return new Car(this.model);
        }

        public String getModel() { return model; }
    }

    static class ElectricCar extends Car {
        private int batteryCapacity;

        public ElectricCar(String model, int batteryCapacity) {
            super(model);
            this.batteryCapacity = batteryCapacity;
        }

        // Ancora più specifico
        @Override
        public ElectricCar getCopy() {
            System.out.println("ElectricCar.getCopy()");
            return new ElectricCar(getModel(), this.batteryCapacity);
        }
    }

    // ===========================================
    // SEZIONE 13: OVERRIDE E MODIFICATORI ACCESSO
    // ===========================================

    /**
     * Override e modificatori di accesso
     */
    static class Base {
        // protected - può essere sovrascritto con protected o public
        protected void protectedMethod() {
            System.out.println("Base.protectedMethod()");
        }

        // public - può essere sovrascritto solo con public
        public void publicMethod() {
            System.out.println("Base.publicMethod()");
        }

        // default (package-private) - può essere protected o public
        void defaultMethod() {
            System.out.println("Base.defaultMethod()");
        }

        // private - NON può essere sovrascritto, solo nascosto
        private void privateMethod() {
            System.out.println("Base.privateMethod()");
        }
    }

    static class Derived extends Base {
        // Aumenta la visibilità da protected a public (OK)
        @Override
        public void protectedMethod() {
            System.out.println("Derived.protectedMethod()");
        }

        // Mantiene public (OK)
        @Override
        public void publicMethod() {
            System.out.println("Derived.publicMethod()");
        }

        // Aumenta da package-private a public (OK)
        @Override
        public void defaultMethod() {
            System.out.println("Derived.defaultMethod()");
        }

        // NON è override, è un nuovo metodo (hiding)
        @SuppressWarnings("unused")
        private void privateMethod() {
            System.out.println("Derived.privateMethod()");
        }

        // ERRORE: non si può ridurre la visibilità
        // @Override
        // protected void publicMethod() { } // ERRORE!
    }

    // ===========================================
    // SEZIONE 14: OVERRIDE E ECCEZIONI
    // ===========================================

    /**
     * Override e gestione eccezioni
     */
    static class ExceptionBase {
        // Dichiara IOException
        public void readFile() throws IOException {
            System.out.println("ExceptionBase.readFile()");
        }

        // Nessuna eccezione
        public void processData() {
            System.out.println("ExceptionBase.processData()");
        }

        // Exception generica
        public void execute() throws Exception {
            System.out.println("ExceptionBase.execute()");
        }
    }

    static class ExceptionDerived extends ExceptionBase {
        // OK: stessa eccezione
        @Override
        public void readFile() throws IOException {
            System.out.println("ExceptionDerived.readFile()");
        }

        // OK: aggiunge eccezione unchecked
        @Override
        public void processData() throws RuntimeException {
            System.out.println("ExceptionDerived.processData()");
        }

        // OK: sottoclasse di Exception
        @Override
        public void execute() throws IOException {
            System.out.println("ExceptionDerived.execute()");
        }

        // ERRORE: non può dichiarare eccezione più ampia
        // @Override
        // public void readFile() throws Exception { } // ERRORE!
    }

    // ===========================================
    // SEZIONE 15: OVERRIDE METODI OBJECT
    // ===========================================

    /**
     * Override di metodi da Object
     */
    static class Student {
        private String name;
        private int id;

        public Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        // Override equals
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return id == student.id && Objects.equals(name, student.name);
        }

        // Override hashCode (sempre insieme a equals!)
        @Override
        public int hashCode() {
            return Objects.hash(name, id);
        }

        // Override toString
        @Override
        public String toString() {
            return "Student{name='" + name + "', id=" + id + "}";
        }

        // Override clone (se necessario)
        @Override
        protected Student clone() {
            return new Student(this.name, this.id);
        }
    }

    // ===========================================
    // SEZIONE 16: OVERRIDE CON FINAL
    // ===========================================

    /**
     * Override e modificatore final
     */
    static class FinalBase {
        // Metodo normale - può essere sovrascritto
        public void normalMethod() {
            System.out.println("FinalBase.normalMethod()");
        }

        // Metodo final - NON può essere sovrascritto
        public final void finalMethod() {
            System.out.println("FinalBase.finalMethod()");
        }

        // Metodo privato e final (ridondante)
        private final void privateFinalMethod() {
            System.out.println("FinalBase.privateFinalMethod()");
        }
    }

    static class FinalDerived extends FinalBase {
        // OK: override di metodo normale
        @Override
        public void normalMethod() {
            System.out.println("FinalDerived.normalMethod()");
        }

        // ERRORE: non può sovrascrivere metodo final
        // @Override
        // public void finalMethod() { } // ERRORE!

        // OK: non è override, è nuovo metodo
        @SuppressWarnings("unused")
        private void privateFinalMethod() {
            System.out.println("FinalDerived.privateFinalMethod()");
        }
    }

    // Classe final - non può essere estesa
    static final class FinalClass {
        public void method() {
            System.out.println("FinalClass.method()");
        }
    }

    // ERRORE: non può estendere classe final
    // static class CannotExtend extends FinalClass { }

    // ===========================================
    // SEZIONE 17: OVERRIDE CON GENERICS
    // ===========================================

    /**
     * Override con generics
     */
    static class GenericBase<T> {
        private T value;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void process(T item) {
            System.out.println("GenericBase.process: " + item);
        }
    }

    static class GenericDerived<T> extends GenericBase<T> {
        // Override con stesso tipo generico
        @Override
        public void process(T item) {
            System.out.println("GenericDerived.process: " + item);
            super.process(item);
        }
    }

    static class StringDerived extends GenericBase<String> {
        // Override con tipo concreto
        @Override
        public void process(String item) {
            System.out.println("StringDerived.process: " + item.toUpperCase());
        }

        // Aggiunge nuovo metodo (overload, non override)
        public void process(String item, boolean uppercase) {
            System.out.println(uppercase ? item.toUpperCase() : item.toLowerCase());
        }
    }

    // ===========================================
    // SEZIONE 18: BRIDGE METHODS
    // ===========================================

    /**
     * Bridge methods generati dal compilatore
     */
    static class BridgeMethodDemo {
        interface Comparable<T> {
            int compareTo(T other);
        }

        static class MyInteger implements Comparable<MyInteger> {
            private int value;

            public MyInteger(int value) {
                this.value = value;
            }

            // Questo è il metodo reale
            @Override
            public int compareTo(MyInteger other) {
                System.out.println("compareTo(MyInteger) chiamato");
                return Integer.compare(this.value, other.value);
            }

            // Il compilatore genera un bridge method:
            // public int compareTo(Object other) {
            //     return compareTo((MyInteger) other);
            // }
        }
    }

    // ===========================================
    // SEZIONE 19: HIDING VS OVERRIDE (STATICI)
    // ===========================================

    /**
     * Hiding di metodi statici vs Override
     */
    static class StaticBase {
        // Metodo statico
        public static void staticMethod() {
            System.out.println("StaticBase.staticMethod()");
        }

        // Metodo di istanza
        public void instanceMethod() {
            System.out.println("StaticBase.instanceMethod()");
        }
    }

    static class StaticDerived extends StaticBase {
        // Hiding (non override!) di metodo statico
        public static void staticMethod() {
            System.out.println("StaticDerived.staticMethod()");
        }

        // Override di metodo di istanza
        @Override
        public void instanceMethod() {
            System.out.println("StaticDerived.instanceMethod()");
        }
    }

    // ===========================================
    // SEZIONE 20: TEMPLATE METHOD PATTERN
    // ===========================================

    /**
     * Template Method Pattern con override
     */
    static abstract class AbstractGame {
        // Template method - final per evitare override
        public final void play() {
            initialize();
            startPlay();
            endPlay();
            cleanup();
        }

        // Hook methods da sovrascrivere
        protected abstract void initialize();
        protected abstract void startPlay();
        protected abstract void endPlay();

        // Hook opzionale con implementazione default
        protected void cleanup() {
            System.out.println("Default cleanup");
        }
    }

    static class Chess extends AbstractGame {
        @Override
        protected void initialize() {
            System.out.println("Chess: Initializing chess board");
        }

        @Override
        protected void startPlay() {
            System.out.println("Chess: Game started. White moves first");
        }

        @Override
        protected void endPlay() {
            System.out.println("Chess: Game ended. Checkmate!");
        }

        @Override
        protected void cleanup() {
            System.out.println("Chess: Cleaning up chess pieces");
        }
    }

    static class Soccer extends AbstractGame {
        @Override
        protected void initialize() {
            System.out.println("Soccer: Setting up field and ball");
        }

        @Override
        protected void startPlay() {
            System.out.println("Soccer: Game started. Kick off!");
        }

        @Override
        protected void endPlay() {
            System.out.println("Soccer: Game ended. Final score displayed");
        }

        // Non override cleanup - usa quello di default
    }

    // ===========================================
    // SEZIONE 21: OVERLOAD + OVERRIDE COMBINATI
    // ===========================================

    /**
     * Overload e Override combinati
     */
    static class Shape {
        // Metodo base
        public void draw() {
            System.out.println("Shape.draw()");
        }

        // Overload
        public void draw(String color) {
            System.out.println("Shape.draw(" + color + ")");
        }

        // Overload
        public void draw(int x, int y) {
            System.out.println("Shape.draw(" + x + ", " + y + ")");
        }
    }

    static class Circle extends Shape {
        // Override del metodo base
        @Override
        public void draw() {
            System.out.println("Circle.draw()");
        }

        // Override dell'overload
        @Override
        public void draw(String color) {
            System.out.println("Circle.draw(" + color + ")");
        }

        // Nuovo overload (non override)
        public void draw(double radius) {
            System.out.println("Circle.draw(radius=" + radius + ")");
        }

        // Overload del metodo ereditato
        public void draw(int x, int y, double radius) {
            System.out.println("Circle.draw(" + x + ", " + y + ", " + radius + ")");
        }
    }

    // ===========================================
    // SEZIONE 22: POLIMORFISMO E DYNAMIC DISPATCH
    // ===========================================

    /**
     * Polimorfismo runtime e dynamic method dispatch
     */
    static class PolymorphismDemo {
        static class Employee {
            protected String name;
            protected double salary;

            public Employee(String name, double salary) {
                this.name = name;
                this.salary = salary;
            }

            public double calculateBonus() {
                return salary * 0.10; // 10% default
            }

            public void displayInfo() {
                System.out.println("Employee: " + name);
                System.out.println("Salary: " + salary);
                System.out.println("Bonus: " + calculateBonus());
            }
        }

        static class Manager extends Employee {
            private int teamSize;

            public Manager(String name, double salary, int teamSize) {
                super(name, salary);
                this.teamSize = teamSize;
            }

            @Override
            public double calculateBonus() {
                // Manager ottiene 20% + bonus team
                return salary * 0.20 + (teamSize * 1000);
            }

            @Override
            public void displayInfo() {
                System.out.println("Manager: " + name);
                System.out.println("Salary: " + salary);
                System.out.println("Team Size: " + teamSize);
                System.out.println("Bonus: " + calculateBonus());
            }
        }

        static class Developer extends Employee {
            private String programmingLanguage;

            public Developer(String name, double salary, String language) {
                super(name, salary);
                this.programmingLanguage = language;
            }

            @Override
            public double calculateBonus() {
                // Developer ottiene 15%
                return salary * 0.15;
            }

            @Override
            public void displayInfo() {
                System.out.println("Developer: " + name);
                System.out.println("Salary: " + salary);
                System.out.println("Language: " + programmingLanguage);
                System.out.println("Bonus: " + calculateBonus());
            }
        }

        public static void processBonuses(List<Employee> employees) {
            for (Employee emp : employees) {
                // Dynamic dispatch: il metodo chiamato dipende dal tipo runtime
                emp.displayInfo();
                System.out.println();
            }
        }
    }

    // ===========================================
    // SEZIONE 23: METHOD RESOLUTION
    // ===========================================

    /**
     * Risoluzione dei metodi - compile-time vs runtime
     */
    static class MethodResolutionDemo {
        static class A {
            public void method1() {
                System.out.println("A.method1()");
            }

            public void method2() {
                System.out.println("A.method2()");
            }
        }

        static class B extends A {
            @Override
            public void method1() {
                System.out.println("B.method1()");
            }

            public void method3() {
                System.out.println("B.method3()");
            }
        }

        static class C extends B {
            @Override
            public void method1() {
                System.out.println("C.method1()");
            }

            @Override
            public void method2() {
                System.out.println("C.method2()");
            }
        }

        public static void demonstrate() {
            // Tipo dichiarato: A, tipo effettivo: C
            A obj = new C();
            
            obj.method1(); // C.method1() - runtime dispatch
            obj.method2(); // C.method2() - runtime dispatch
            // obj.method3(); // ERRORE: method3() non esiste in A
            
            // Cast necessario per chiamare method3()
            if (obj instanceof B) {
                ((B) obj).method3(); // B.method3()
            }

            // Tipo dichiarato e effettivo: B
            B obj2 = new B();
            obj2.method1(); // B.method1()
            obj2.method2(); // A.method2() (ereditato)
            obj2.method3(); // B.method3()
        }
    }

    // ===========================================
    // SEZIONE 24: OVERLOAD CON LAMBDA E METHOD REFERENCE
    // ===========================================

    /**
     * Overload con lambda expressions e method references
     */
    static class LambdaOverloadDemo {
        // Overload con functional interfaces diverse
        public void execute(Runnable task) {
            System.out.println("execute(Runnable)");
            task.run();
        }

        public void execute(java.util.function.Supplier<String> supplier) {
            System.out.println("execute(Supplier): " + supplier.get());
        }

        public void execute(java.util.function.Consumer<String> consumer) {
            System.out.println("execute(Consumer)");
            consumer.accept("Test");
        }

        public void test() {
            // Lambda con tipo esplicito
            execute((Runnable) () -> System.out.println("Running"));
            
            // Method reference
            execute((Runnable) System::gc);
            
            // Lambda con inferenza
            execute(() -> "Supplied value");
            
            execute((String s) -> System.out.println("Consumed: " + s));
        }
    }

    // ===========================================
    // SEZIONE 25: SUPER E THIS
    // ===========================================

    /**
     * Uso di super e this con override e overload
     */
    static class SuperThisDemo {
        static class Parent {
            protected String name = "Parent";

            public void display() {
                System.out.println("Parent.display()");
            }

            public void display(String message) {
                System.out.println("Parent.display(String): " + message);
            }

            public void callDisplay() {
                System.out.println("Parent.callDisplay():");
                display(); // Chiama display() di questa classe
            }
        }

        static class Child extends Parent {
            protected String name = "Child";

            @Override
            public void display() {
                System.out.println("Child.display()");
                System.out.println("this.name = " + this.name);
                System.out.println("super.name = " + super.name);
                super.display(); // Chiama il metodo del parent
            }

            @Override
            public void display(String message) {
                System.out.println("Child.display(String): " + message);
                super.display(message); // Chiama l'overload del parent
            }

            @Override
            public void callDisplay() {
                System.out.println("Child.callDisplay():");
                display(); // Chiama display() di Child (this)
                super.callDisplay(); // Chiama callDisplay() di Parent
            }
        }
    }

    // ===========================================
    // SEZIONE 26: BEST PRACTICES E PATTERN
    // ===========================================

    /**
     * BEST PRACTICES PER OVERLOAD E OVERRIDE:
     * 
     * OVERLOADING:
     * 1. Mantenere comportamento coerente tra metodi overloaded
     * 2. Evitare ambiguità (preferire nomi diversi se necessario)
     * 3. Non abusare dell'overload - può confondere
     * 4. Usare varargs con cautela (ha la precedenza più bassa)
     * 5. Considerare builder pattern invece di molti costruttori
     * 6. Documentare le differenze tra gli overload
     * 
     * OVERRIDE:
     * 1. SEMPRE usare @Override annotation
     * 2. Mantenere o aumentare la visibilità (mai ridurre)
     * 3. Non dichiarare eccezioni più ampie
     * 4. Override equals() → override hashCode()
     * 5. Override toString() per debugging
     * 6. Usare super.method() quando appropriato
     * 7. Evitare override di metodi final
     * 8. Considerare template method pattern
     * 
     * POLIMORFISMO:
     * 1. Programmare verso interfacce, non implementazioni
     * 2. Favorire composizione su ereditarietà
     * 3. Usare abstract classes per comportamento comune
     * 4. Liskov Substitution Principle: sottoclassi sostituibili
     * 5. Open/Closed Principle: aperto a estensioni, chiuso a modifiche
     * 
     * ERRORI COMUNI:
     * 1. Confondere overload (compile-time) con override (runtime)
     * 2. Dimenticare @Override e fare hiding invece di override
     * 3. Violare equals/hashCode contract
     * 4. Overload con tipi troppo simili (ambiguità)
     * 5. Override con eccezioni incompatibili
     * 6. Nascondere metodi statici pensando sia override
     * 7. Non chiamare super() nei costruttori
     */

    // ===========================================
    // METODO MAIN - DIMOSTRAZIONE COMPLETA
    // ===========================================

    public static void sample() {
        System.out.println("=== DIMOSTRAZIONE COMPLETA SU OVERLOAD E OVERRIDE ===\n");

        // 1. Overload base
        System.out.println("1. OVERLOAD BASE - DIVERSI TIPI:");
        Calculator calc = new Calculator();
        System.out.println("Risultato int: " + calc.add(5, 3));
        System.out.println("Risultato double: " + calc.add(5.5, 3.2));
        System.out.println("Risultato String: " + calc.add("Hello", "World"));

        // 2. Overload con numero diverso di parametri
        System.out.println("\n2. OVERLOAD - NUMERO PARAMETRI:");
        Printer printer = new Printer();
        printer.print("Test");
        printer.print("Test", 3);
        printer.print("Test", 3, " | ");

        // 3. Overload con ordine diverso
        System.out.println("\n3. OVERLOAD - ORDINE PARAMETRI:");
        DataFormatter formatter = new DataFormatter();
        System.out.println(formatter.format("Alice", 25));
        System.out.println(formatter.format(30, "Bob"));

        // 4. Autoboxing
        System.out.println("\n4. AUTOBOXING/UNBOXING:");
        BoxingDemo boxing = new BoxingDemo();
        boxing.test();

        // 5. Varargs
        System.out.println("\n5. VARARGS:");
        VarargsDemo varargs = new VarargsDemo();
        System.out.println("Sum: " + varargs.sum());
        System.out.println("Sum: " + varargs.sum(5));
        System.out.println("Sum: " + varargs.sum(5, 10));
        System.out.println("Sum: " + varargs.sum(1, 2, 3, 4, 5));

        // 6. Type promotion
        System.out.println("\n6. TYPE PROMOTION:");
        PromotionDemo promotion = new PromotionDemo();
        promotion.testPromotion();

        // 7. Costruttori overloaded
        System.out.println("\n7. OVERLOAD COSTRUTTORI:");
        Person p1 = new Person();
        System.out.println(p1);
        Person p2 = new Person("Alice", 25);
        System.out.println(p2);

        // 8. Override base
        System.out.println("\n8. OVERRIDE BASE:");
        Animal animal = new Animal();
        animal.makeSound();
        
        Dog dog = new Dog();
        dog.makeSound();
        
        Animal polymorphicDog = new Dog(); // Polimorfismo
        polymorphicDog.makeSound(); // Chiama Dog.makeSound()

        // 9. Covariant return types
        System.out.println("\n9. COVARIANT RETURN TYPES:");
        Vehicle vehicle = new Vehicle();
        Vehicle v1 = vehicle.getCopy();
        
        Car car = new Car("Tesla");
        Car c1 = car.getCopy(); // Ritorna Car, non Vehicle
        System.out.println("Copy model: " + c1.getModel());

        // 10. Override e visibilità
        System.out.println("\n10. OVERRIDE E VISIBILITÀ:");
        Derived derived = new Derived();
        derived.protectedMethod();
        derived.publicMethod();

        // 11. Override metodi Object
        System.out.println("\n11. OVERRIDE METODI OBJECT:");
        Student s1 = new Student("Alice", 123);
        Student s2 = new Student("Alice", 123);
        Student s3 = new Student("Bob", 456);
        
        System.out.println("s1: " + s1);
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s2.hashCode(): " + s2.hashCode());

        // 12. Hiding vs Override
        System.out.println("\n12. HIDING VS OVERRIDE (STATICI):");
        StaticBase.staticMethod();
        StaticDerived.staticMethod();
        
        StaticBase baseRef = new StaticDerived();
        baseRef.staticMethod();      // Chiama StaticBase (hiding, non override)
        baseRef.instanceMethod();    // Chiama StaticDerived (override)

        // 13. Template Method Pattern
        System.out.println("\n13. TEMPLATE METHOD PATTERN:");
        AbstractGame chess = new Chess();
        chess.play();
        
        System.out.println();
        
        AbstractGame soccer = new Soccer();
        soccer.play();

        // 14. Overload + Override
        System.out.println("\n14. OVERLOAD + OVERRIDE COMBINATI:");
        Shape shape = new Shape();
        shape.draw();
        shape.draw("red");
        
        Circle circle = new Circle();
        circle.draw();
        circle.draw("blue");
        circle.draw(5.0);

        // 15. Polimorfismo
        System.out.println("\n15. POLIMORFISMO E DYNAMIC DISPATCH:");
        List<PolymorphismDemo.Employee> employees = Arrays.asList(
            new PolymorphismDemo.Employee("John", 50000),
            new PolymorphismDemo.Manager("Alice", 80000, 5),
            new PolymorphismDemo.Developer("Bob", 70000, "Java")
        );
        PolymorphismDemo.processBonuses(employees);

        // 16. Method Resolution
        System.out.println("\n16. METHOD RESOLUTION:");
        MethodResolutionDemo.demonstrate();

        // 17. Super e This
        System.out.println("\n17. SUPER E THIS:");
        SuperThisDemo.Child child = new SuperThisDemo.Child();
        child.display();
        System.out.println();
        child.display("Hello");
        System.out.println();
        child.callDisplay();

        System.out.println("\n=== FINE DIMOSTRAZIONE ===");
    }
}
