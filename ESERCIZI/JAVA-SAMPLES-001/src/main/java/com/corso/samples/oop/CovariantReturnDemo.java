package com.corso.samples.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * DIMOSTRAZIONE COMPLETA E AVANZATA SUI COVARIANT RETURN TYPES IN JAVA
 * 
 * I Covariant Return Types sono stati introdotti in Java 5 e permettono
 * a un metodo sovrascritto di restituire un tipo che è una sottoclasse
 * del tipo restituito dal metodo nella classe base.
 * 
 * Questo modulo copre tutti gli scenari possibili:
 * 1. Concetti base di covariant return
 * 2. Override con classi concrete
 * 3. Covariant return con interfacce
 * 4. Factory methods pattern
 * 5. Clone e covariant return
 * 6. Builder pattern con covariant return
 * 7. Fluent interfaces
 * 8. Hierarchy complesse
 * 9. Generic types e covariant return
 * 10. Abstract factory pattern
 * 11. Prototype pattern
 * 12. Template method pattern
 * 13. Scenari con collections
 * 14. Covariant return con enum
 * 15. Limitazioni e restrizioni
 * 16. Best practices e pattern avanzati
 */
public class CovariantReturnDemo {

    // ===========================================
    // SEZIONE 1: CONCETTI BASE DI COVARIANT RETURN
    // ===========================================

    /**
     * Classe base con metodo che ritorna il tipo base
     */
    static class Animal {
        private String name;

        public Animal(String name) {
            this.name = name;
        }

        /**
         * Metodo che ritorna Animal
         * Le sottoclassi possono sovrascrivere restituendo un tipo più specifico
         */
        public Animal reproduce() {
            return new Animal("Offspring of " + name);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + name + "]";
        }
    }

    /**
     * Sottoclasse che sovrascrive con tipo di ritorno covariante
     */
    static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }

        /**
         * COVARIANT RETURN: ritorna Dog invece di Animal
         * Questo è legale perché Dog è una sottoclasse di Animal
         */
        @Override
        public Dog reproduce() {
            return new Dog("Puppy of " + getName(), breed);
        }

        public String getBreed() {
            return breed;
        }
    }

    /**
     * Altra sottoclasse con covariant return
     */
    static class Cat extends Animal {
        private int livesRemaining;

        public Cat(String name, int livesRemaining) {
            super(name);
            this.livesRemaining = livesRemaining;
        }

        /**
         * COVARIANT RETURN: ritorna Cat invece di Animal
         */
        @Override
        public Cat reproduce() {
            return new Cat("Kitten of " + getName(), 9);
        }

        public int getLivesRemaining() {
            return livesRemaining;
        }
    }

    // ===========================================
    // SEZIONE 2: FACTORY METHODS CON COVARIANT RETURN
    // ===========================================

    /**
     * Classe base per un factory pattern
     */
    static abstract class Vehicle {
        protected String model;

        public Vehicle(String model) {
            this.model = model;
        }

        /**
         * Factory method - ritorna Vehicle
         * Le sottoclassi possono restituire tipi più specifici
         */
        public abstract Vehicle createNew(String model);

        public String getModel() {
            return model;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[model=" + model + "]";
        }
    }

    /**
     * Sottoclasse con factory method covariante
     */
    static class Car extends Vehicle {
        private int doors;

        public Car(String model, int doors) {
            super(model);
            this.doors = doors;
        }

        /**
         * COVARIANT RETURN: ritorna Car invece di Vehicle
         */
        @Override
        public Car createNew(String model) {
            return new Car(model, this.doors);
        }

        public int getDoors() {
            return doors;
        }
    }

    /**
     * Altra sottoclasse con factory method covariante
     */
    static class Motorcycle extends Vehicle {
        private boolean hasSidecar;

        public Motorcycle(String model, boolean hasSidecar) {
            super(model);
            this.hasSidecar = hasSidecar;
        }

        /**
         * COVARIANT RETURN: ritorna Motorcycle invece di Vehicle
         */
        @Override
        public Motorcycle createNew(String model) {
            return new Motorcycle(model, this.hasSidecar);
        }

        public boolean hasSidecar() {
            return hasSidecar;
        }
    }

    // ===========================================
    // SEZIONE 3: CLONE E COVARIANT RETURN
    // ===========================================

    /**
     * Classe base che implementa Cloneable
     */
    static class Shape implements Cloneable {
        protected String color;

        public Shape(String color) {
            this.color = color;
        }

        /**
         * Clone che ritorna Shape
         * Le sottoclassi possono restituire tipi più specifici
         */
        @Override
        public Shape clone() {
            try {
                return (Shape) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("Clone non supportato", e);
            }
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[color=" + color + "]";
        }
    }

    /**
     * Sottoclasse con clone covariante
     */
    static class Circle extends Shape {
        private double radius;

        public Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        /**
         * COVARIANT RETURN: ritorna Circle invece di Shape
         * Elimina la necessità di cast per chi usa questa classe
         */
        @Override
        public Circle clone() {
            return (Circle) super.clone();
        }

        public double getRadius() {
            return radius;
        }

        public double getArea() {
            return Math.PI * radius * radius;
        }
    }

    /**
     * Altra sottoclasse con clone covariante
     */
    static class Rectangle extends Shape {
        private double width;
        private double height;

        public Rectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        /**
         * COVARIANT RETURN: ritorna Rectangle invece di Shape
         */
        @Override
        public Rectangle clone() {
            return (Rectangle) super.clone();
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        public double getArea() {
            return width * height;
        }
    }

    // ===========================================
    // SEZIONE 4: BUILDER PATTERN CON COVARIANT RETURN
    // ===========================================

    /**
     * Builder base con metodi fluent
     */
    static class PersonBuilder {
        protected String name;
        protected int age;

        /**
         * Metodo fluent che ritorna this
         * Le sottoclassi possono restituire il proprio tipo
         */
        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(name, age);
        }
    }

    /**
     * Builder esteso con covariant return per method chaining
     */
    static class EmployeeBuilder extends PersonBuilder {
        private String employeeId;
        private String department;

        /**
         * COVARIANT RETURN: ritorna EmployeeBuilder invece di PersonBuilder
         * Permette method chaining type-safe con metodi specifici
         */
        @Override
        public EmployeeBuilder setName(String name) {
            super.setName(name);
            return this;
        }

        @Override
        public EmployeeBuilder setAge(int age) {
            super.setAge(age);
            return this;
        }

        /**
         * Metodo specifico di EmployeeBuilder
         */
        public EmployeeBuilder setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(name, age, employeeId, department);
        }
    }

    static class Person {
        protected String name;
        protected int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return "Person[name=" + name + ", age=" + age + "]";
        }
    }

    static class Employee extends Person {
        private String employeeId;
        private String department;

        public Employee(String name, int age, String employeeId, String department) {
            super(name, age);
            this.employeeId = employeeId;
            this.department = department;
        }

        public String getEmployeeId() { return employeeId; }
        public String getDepartment() { return department; }

        @Override
        public String toString() {
            return "Employee[name=" + name + ", age=" + age + 
                   ", id=" + employeeId + ", dept=" + department + "]";
        }
    }

    // ===========================================
    // SEZIONE 5: INTERFACCE E COVARIANT RETURN
    // ===========================================

    /**
     * Interfaccia base con metodo che ritorna un tipo
     */
    interface Copyable {
        Copyable copy();
    }

    /**
     * Interfaccia estesa con covariant return
     */
    interface AdvancedCopyable extends Copyable {
        /**
         * COVARIANT RETURN: ritorna AdvancedCopyable invece di Copyable
         */
        @Override
        AdvancedCopyable copy();
    }

    /**
     * Classe che implementa l'interfaccia con covariant return
     */
    static class Document implements Copyable {
        private String title;
        private String content;

        public Document(String title, String content) {
            this.title = title;
            this.content = content;
        }

        /**
         * COVARIANT RETURN: ritorna Document invece di Copyable
         */
        @Override
        public Document copy() {
            return new Document(this.title + " (copy)", this.content);
        }

        public String getTitle() { return title; }
        public String getContent() { return content; }

        @Override
        public String toString() {
            return "Document[title=" + title + "]";
        }
    }

    /**
     * Sottoclasse con ulteriore specializzazione del covariant return
     */
    static class SecureDocument extends Document implements AdvancedCopyable {
        private String encryptionKey;

        public SecureDocument(String title, String content, String encryptionKey) {
            super(title, content);
            this.encryptionKey = encryptionKey;
        }

        /**
         * COVARIANT RETURN: ritorna SecureDocument
         */
        @Override
        public SecureDocument copy() {
            return new SecureDocument(
                getTitle() + " (secure copy)", 
                getContent(), 
                this.encryptionKey
            );
        }

        public String getEncryptionKey() { return encryptionKey; }
    }

    // ===========================================
    // SEZIONE 6: HIERARCHY COMPLESSE
    // ===========================================

    /**
     * Gerarchia a tre livelli con covariant return
     */
    static class Creature {
        protected String species;

        public Creature(String species) {
            this.species = species;
        }

        public Creature evolve() {
            return new Creature(species + " evolved");
        }

        public String getSpecies() { return species; }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + species + "]";
        }
    }

    static class Mammal extends Creature {
        protected boolean hasHair;

        public Mammal(String species, boolean hasHair) {
            super(species);
            this.hasHair = hasHair;
        }

        /**
         * COVARIANT RETURN livello 1: Mammal invece di Creature
         */
        @Override
        public Mammal evolve() {
            return new Mammal(species + " evolved", hasHair);
        }

        public boolean hasHair() { return hasHair; }
    }

    static class Primate extends Mammal {
        private boolean canUseTools;

        public Primate(String species, boolean hasHair, boolean canUseTools) {
            super(species, hasHair);
            this.canUseTools = canUseTools;
        }

        /**
         * COVARIANT RETURN livello 2: Primate invece di Mammal
         */
        @Override
        public Primate evolve() {
            return new Primate(species + " evolved", hasHair, true);
        }

        public boolean canUseTools() { return canUseTools; }
    }

    // ===========================================
    // SEZIONE 7: ABSTRACT FACTORY PATTERN
    // ===========================================

    /**
     * Abstract factory con covariant return
     */
    static abstract class UIFactory {
        public abstract Button createButton();
        public abstract TextField createTextField();
    }

    static abstract class Button {
        protected String label;

        public Button(String label) {
            this.label = label;
        }

        public abstract Button clone();
        public String getLabel() { return label; }
    }

    static abstract class TextField {
        protected String placeholder;

        public TextField(String placeholder) {
            this.placeholder = placeholder;
        }

        public abstract TextField clone();
        public String getPlaceholder() { return placeholder; }
    }

    /**
     * Windows factory con covariant return
     */
    static class WindowsUIFactory extends UIFactory {
        /**
         * COVARIANT RETURN: ritorna WindowsButton invece di Button
         */
        @Override
        public WindowsButton createButton() {
            return new WindowsButton("Windows Button");
        }

        @Override
        public WindowsTextField createTextField() {
            return new WindowsTextField("Enter text...");
        }
    }

    static class WindowsButton extends Button {
        public WindowsButton(String label) {
            super(label);
        }

        @Override
        public WindowsButton clone() {
            return new WindowsButton(this.label);
        }

        @Override
        public String toString() {
            return "WindowsButton[" + label + "]";
        }
    }

    static class WindowsTextField extends TextField {
        public WindowsTextField(String placeholder) {
            super(placeholder);
        }

        @Override
        public WindowsTextField clone() {
            return new WindowsTextField(this.placeholder);
        }

        @Override
        public String toString() {
            return "WindowsTextField[" + placeholder + "]";
        }
    }

    /**
     * Mac factory con covariant return
     */
    static class MacUIFactory extends UIFactory {
        @Override
        public MacButton createButton() {
            return new MacButton("Mac Button");
        }

        @Override
        public MacTextField createTextField() {
            return new MacTextField("Type here...");
        }
    }

    static class MacButton extends Button {
        public MacButton(String label) {
            super(label);
        }

        @Override
        public MacButton clone() {
            return new MacButton(this.label);
        }

        @Override
        public String toString() {
            return "MacButton[" + label + "]";
        }
    }

    static class MacTextField extends TextField {
        public MacTextField(String placeholder) {
            super(placeholder);
        }

        @Override
        public MacTextField clone() {
            return new MacTextField(this.placeholder);
        }

        @Override
        public String toString() {
            return "MacTextField[" + placeholder + "]";
        }
    }

    // ===========================================
    // SEZIONE 8: PROTOTYPE PATTERN CON COVARIANT RETURN
    // ===========================================

    /**
     * Prototype base
     */
    static abstract class Prototype implements Cloneable {
        private String id;

        public Prototype(String id) {
            this.id = id;
        }

        /**
         * Metodo astratto che deve essere implementato con covariant return
         */
        public abstract Prototype clonePrototype();

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }

    /**
     * Concrete prototype con covariant return
     */
    static class ConcretePrototypeA extends Prototype {
        private String propertyA;

        public ConcretePrototypeA(String id, String propertyA) {
            super(id);
            this.propertyA = propertyA;
        }

        /**
         * COVARIANT RETURN: ritorna ConcretePrototypeA
         */
        @Override
        public ConcretePrototypeA clonePrototype() {
            return new ConcretePrototypeA(getId() + "_clone", this.propertyA);
        }

        public String getPropertyA() { return propertyA; }

        @Override
        public String toString() {
            return "ConcretePrototypeA[id=" + getId() + ", propertyA=" + propertyA + "]";
        }
    }

    static class ConcretePrototypeB extends Prototype {
        private int propertyB;

        public ConcretePrototypeB(String id, int propertyB) {
            super(id);
            this.propertyB = propertyB;
        }

        /**
         * COVARIANT RETURN: ritorna ConcretePrototypeB
         */
        @Override
        public ConcretePrototypeB clonePrototype() {
            return new ConcretePrototypeB(getId() + "_clone", this.propertyB);
        }

        public int getPropertyB() { return propertyB; }

        @Override
        public String toString() {
            return "ConcretePrototypeB[id=" + getId() + ", propertyB=" + propertyB + "]";
        }
    }

    // ===========================================
    // SEZIONE 9: FLUENT INTERFACES AVANZATE
    // ===========================================

    /**
     * Base fluent interface
     */
    static class QueryBuilder {
        protected StringBuilder query = new StringBuilder();

        public QueryBuilder select(String... columns) {
            query.append("SELECT ");
            query.append(String.join(", ", columns));
            return this;
        }

        public QueryBuilder from(String table) {
            query.append(" FROM ").append(table);
            return this;
        }

        public String build() {
            return query.toString();
        }
    }

    /**
     * Extended fluent interface con covariant return
     */
    static class AdvancedQueryBuilder extends QueryBuilder {
        /**
         * COVARIANT RETURN per method chaining type-safe
         */
        @Override
        public AdvancedQueryBuilder select(String... columns) {
            super.select(columns);
            return this;
        }

        @Override
        public AdvancedQueryBuilder from(String table) {
            super.from(table);
            return this;
        }

        /**
         * Metodi specifici disponibili solo in AdvancedQueryBuilder
         */
        public AdvancedQueryBuilder where(String condition) {
            query.append(" WHERE ").append(condition);
            return this;
        }

        public AdvancedQueryBuilder orderBy(String column) {
            query.append(" ORDER BY ").append(column);
            return this;
        }

        public AdvancedQueryBuilder limit(int limit) {
            query.append(" LIMIT ").append(limit);
            return this;
        }
    }

    // ===========================================
    // SEZIONE 10: GENERIC TYPES E COVARIANT RETURN
    // ===========================================

    /**
     * Classe generica base con self-type
     */
    static class Container<T, SELF extends Container<T, SELF>> {
        protected T item;

        public Container(T item) {
            this.item = item;
        }

        /**
         * Metodo che ritorna SELF (self-type pattern)
         */
        @SuppressWarnings("unchecked")
        protected SELF self() {
            return (SELF) this;
        }

        public SELF setItem(T item) {
            this.item = item;
            return self();
        }

        public T getItem() {
            return item;
        }
    }

    /**
     * Estensione con covariant return usando self-type
     */
    static class LabeledContainer<T> extends Container<T, LabeledContainer<T>> {
        private String label;

        public LabeledContainer(T item, String label) {
            super(item);
            this.label = label;
        }

        /**
         * Metodo specifico con return type corretto
         */
        public LabeledContainer<T> setLabel(String label) {
            this.label = label;
            return this;
        }

        public String getLabel() {
            return label;
        }
    }

    // ===========================================
    // SEZIONE 11: COLLECTIONS E COVARIANT RETURN
    // ===========================================

    /**
     * Collection wrapper base
     */
    static class ImmutableList<E> {
        protected List<E> elements;

        public ImmutableList(List<E> elements) {
            this.elements = new ArrayList<>(elements);
        }

        /**
         * Metodo che ritorna una nuova istanza
         */
        public ImmutableList<E> add(E element) {
            List<E> newList = new ArrayList<>(elements);
            newList.add(element);
            return new ImmutableList<>(newList);
        }

        public E get(int index) {
            return elements.get(index);
        }

        public int size() {
            return elements.size();
        }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

    /**
     * Extended collection con covariant return
     */
    static class SortedImmutableList<E extends Comparable<E>> extends ImmutableList<E> {
        public SortedImmutableList(List<E> elements) {
            super(elements);
            Collections.sort(this.elements);
        }

        /**
         * COVARIANT RETURN: ritorna SortedImmutableList invece di ImmutableList
         */
        @Override
        public SortedImmutableList<E> add(E element) {
            List<E> newList = new ArrayList<>(elements);
            newList.add(element);
            return new SortedImmutableList<>(newList);
        }

        public E getMin() {
            return elements.isEmpty() ? null : elements.get(0);
        }

        public E getMax() {
            return elements.isEmpty() ? null : elements.get(elements.size() - 1);
        }
    }

    // ===========================================
    // SEZIONE 12: TEMPLATE METHOD PATTERN
    // ===========================================

    /**
     * Template method base con hook che usa covariant return
     */
    static abstract class AbstractProcessor {
        /**
         * Template method
         */
        public final void process() {
            initialize();
            AbstractProcessor result = execute();
            finalize(result);
        }

        protected abstract void initialize();
        
        /**
         * Hook method che può essere sovrascritto con covariant return
         */
        protected abstract AbstractProcessor execute();
        
        protected abstract void finalize(AbstractProcessor result);

        public abstract String getType();
    }

    /**
     * Concrete processor con covariant return
     */
    static class DataProcessor extends AbstractProcessor {
        private String data;

        public DataProcessor(String data) {
            this.data = data;
        }

        @Override
        protected void initialize() {
            System.out.println("Inizializzazione DataProcessor");
        }

        /**
         * COVARIANT RETURN: ritorna DataProcessor invece di AbstractProcessor
         */
        @Override
        protected DataProcessor execute() {
            String processed = data.toUpperCase();
            return new DataProcessor(processed);
        }

        @Override
        protected void finalize(AbstractProcessor result) {
            DataProcessor dp = (DataProcessor) result;
            System.out.println("Risultato: " + dp.data);
        }

        @Override
        public String getType() {
            return "DataProcessor";
        }

        public String getData() {
            return data;
        }
    }

    // ===========================================
    // SEZIONE 13: COMPOSITE PATTERN CON COVARIANT RETURN
    // ===========================================

    /**
     * Component base
     */
    static abstract class Component {
        protected String name;

        public Component(String name) {
            this.name = name;
        }

        public abstract Component copy();
        public String getName() { return name; }
    }

    /**
     * Leaf component con covariant return
     */
    static class Leaf extends Component {
        private String value;

        public Leaf(String name, String value) {
            super(name);
            this.value = value;
        }

        /**
         * COVARIANT RETURN: ritorna Leaf
         */
        @Override
        public Leaf copy() {
            return new Leaf(this.name + "_copy", this.value);
        }

        public String getValue() { return value; }

        @Override
        public String toString() {
            return "Leaf[" + name + "=" + value + "]";
        }
    }

    /**
     * Composite component con covariant return
     */
    static class Composite extends Component {
        private List<Component> children = new ArrayList<>();

        public Composite(String name) {
            super(name);
        }

        public void add(Component component) {
            children.add(component);
        }

        /**
         * COVARIANT RETURN: ritorna Composite
         */
        @Override
        public Composite copy() {
            Composite copy = new Composite(this.name + "_copy");
            for (Component child : children) {
                copy.add(child.copy());
            }
            return copy;
        }

        public List<Component> getChildren() {
            return new ArrayList<>(children);
        }

        @Override
        public String toString() {
            return "Composite[" + name + ", children=" + children.size() + "]";
        }
    }

    // ===========================================
    // SEZIONE 14: ENUM CON COVARIANT RETURN
    // ===========================================

    /**
     * Base class per operations
     */
    static abstract class Operation {
        public abstract Operation inverse();
        public abstract double apply(double x, double y);
    }

    /**
     * Enum con metodi che usano covariant return
     */
    enum MathOperation {
        ADDITION {
            @Override
            public MathOperationImpl getOperation() {
                return new MathOperationImpl("Addition", "+") {
                    @Override
                    public double apply(double x, double y) {
                        return x + y;
                    }

                    @Override
                    public MathOperationImpl inverse() {
                        return SUBTRACTION.getOperation();
                    }
                };
            }
        },
        SUBTRACTION {
            @Override
            public MathOperationImpl getOperation() {
                return new MathOperationImpl("Subtraction", "-") {
                    @Override
                    public double apply(double x, double y) {
                        return x - y;
                    }

                    @Override
                    public MathOperationImpl inverse() {
                        return ADDITION.getOperation();
                    }
                };
            }
        },
        MULTIPLICATION {
            @Override
            public MathOperationImpl getOperation() {
                return new MathOperationImpl("Multiplication", "*") {
                    @Override
                    public double apply(double x, double y) {
                        return x * y;
                    }

                    @Override
                    public MathOperationImpl inverse() {
                        return DIVISION.getOperation();
                    }
                };
            }
        },
        DIVISION {
            @Override
            public MathOperationImpl getOperation() {
                return new MathOperationImpl("Division", "/") {
                    @Override
                    public double apply(double x, double y) {
                        return x / y;
                    }

                    @Override
                    public MathOperationImpl inverse() {
                        return MULTIPLICATION.getOperation();
                    }
                };
            }
        };

        public abstract MathOperationImpl getOperation();
    }

    static abstract class MathOperationImpl extends Operation {
        private String name;
        private String symbol;

        public MathOperationImpl(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() { return name; }
        public String getSymbol() { return symbol; }

        @Override
        public abstract MathOperationImpl inverse();
    }

    // ===========================================
    // SEZIONE 15: CHAIN OF RESPONSIBILITY CON COVARIANT RETURN
    // ===========================================

    /**
     * Handler base
     */
    static abstract class Handler {
        protected Handler next;

        public Handler setNext(Handler next) {
            this.next = next;
            return next;
        }

        public abstract Handler handle(String request);
    }

    /**
     * Concrete handler con covariant return
     */
    static class AuthenticationHandler extends Handler {
        /**
         * COVARIANT RETURN: ritorna AuthenticationHandler
         */
        @Override
        public AuthenticationHandler handle(String request) {
            System.out.println("AuthenticationHandler: processing " + request);
            if (request.contains("auth")) {
                System.out.println("Autenticazione completata");
            }
            if (next != null) {
                next.handle(request);
            }
            return this;
        }
    }

    static class AuthorizationHandler extends Handler {
        @Override
        public AuthorizationHandler handle(String request) {
            System.out.println("AuthorizationHandler: processing " + request);
            if (request.contains("authz")) {
                System.out.println("Autorizzazione completata");
            }
            if (next != null) {
                next.handle(request);
            }
            return this;
        }
    }

    static class LoggingHandler extends Handler {
        @Override
        public LoggingHandler handle(String request) {
            System.out.println("LoggingHandler: logging " + request);
            if (next != null) {
                next.handle(request);
            }
            return this;
        }
    }

    // ===========================================
    // SEZIONE 16: IMMUTABLE OBJECTS CON COVARIANT RETURN
    // ===========================================

    /**
     * Immutable base class
     */
    static class ImmutablePoint {
        protected final int x;
        protected final int y;

        public ImmutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Ritorna una nuova istanza con x modificato
         */
        public ImmutablePoint withX(int newX) {
            return new ImmutablePoint(newX, this.y);
        }

        public ImmutablePoint withY(int newY) {
            return new ImmutablePoint(this.x, newY);
        }

        public int getX() { return x; }
        public int getY() { return y; }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    /**
     * Immutable 3D point con covariant return
     */
    static class ImmutablePoint3D extends ImmutablePoint {
        private final int z;

        public ImmutablePoint3D(int x, int y, int z) {
            super(x, y);
            this.z = z;
        }

        /**
         * COVARIANT RETURN: ritorna ImmutablePoint3D invece di ImmutablePoint
         */
        @Override
        public ImmutablePoint3D withX(int newX) {
            return new ImmutablePoint3D(newX, this.y, this.z);
        }

        @Override
        public ImmutablePoint3D withY(int newY) {
            return new ImmutablePoint3D(this.x, newY, this.z);
        }

        public ImmutablePoint3D withZ(int newZ) {
            return new ImmutablePoint3D(this.x, this.y, newZ);
        }

        public int getZ() { return z; }

        @Override
        public String toString() {
            return "Point3D(" + x + ", " + y + ", " + z + ")";
        }
    }

    // ===========================================
    // SEZIONE 17: LIMITAZIONI E RESTRIZIONI
    // ===========================================

    /**
     * Dimostra le limitazioni dei covariant return types
     */
    public static void demonstrateLimitations() {
        System.out.println("\n=== LIMITAZIONI DEI COVARIANT RETURN TYPES ===");

        // 1. Il tipo di ritorno deve essere una sottoclasse (non può essere un tipo diverso)
        // class Base {
        //     String method() { return "base"; }
        // }
        // class Derived extends Base {
        //     Integer method() { return 42; } // ERRORE: Integer non è sottoclasse di String
        // }

        // 2. Non funziona con tipi primitivi
        // class Base2 {
        //     Number method() { return 1; }
        // }
        // class Derived2 extends Base2 {
        //     int method() { return 1; } // ERRORE: int non è sottoclasse di Number
        // }

        // 3. Overloading vs Override - il covariant return richiede override
        // class Base3 {
        //     Object method(String s) { return s; }
        // }
        // class Derived3 extends Base3 {
        //     String method(String s) { return s; } // OK - covariant return
        //     String method(Integer i) { return i.toString(); } // OK - overload, non override
        // }

        // 4. Con generics, la covarianza è limitata dal type erasure
        // class GenericBase<T> {
        //     T get() { return null; }
        // }
        // class GenericDerived extends GenericBase<String> {
        //     String get() { return ""; } // OK dopo type erasure
        // }

        System.out.println("Le limitazioni sono documentate nei commenti del codice");
    }

    // ===========================================
    // SEZIONE 18: BEST PRACTICES E VANTAGGI
    // ===========================================

    /**
     * BEST PRACTICES PER COVARIANT RETURN TYPES:
     * 
     * 1. USARE CON CLONE()
     *    - Elimina la necessità di cast quando si clona un oggetto
     *    - Circle c = circle.clone(); invece di Circle c = (Circle) circle.clone();
     * 
     * 2. BUILDER PATTERN
     *    - Permette method chaining type-safe nelle sottoclassi
     *    - EmployeeBuilder può chiamare setName() e poi setEmployeeId()
     * 
     * 3. FACTORY METHODS
     *    - Le factory possono restituire tipi più specifici
     *    - CarFactory.create() ritorna Car, non Vehicle
     * 
     * 4. FLUENT INTERFACES
     *    - Mantiene il tipo corretto attraverso la catena di chiamate
     *    - Permette di accedere a metodi specifici della sottoclasse
     * 
     * 5. IMMUTABLE OBJECTS
     *    - I metodi "with*" possono restituire il tipo corretto
     *    - point3D.withX(5) ritorna ImmutablePoint3D, non ImmutablePoint
     * 
     * VANTAGGI:
     * - Type safety migliorata (meno cast)
     * - Codice più leggibile e mantenibile
     * - Meno errori a runtime
     * - Migliore supporto IDE (autocomplete)
     * - API più intuitive
     * 
     * QUANDO USARE:
     * - Override di clone()
     * - Builder pattern con ereditarietà
     * - Factory methods
     * - Fluent interfaces
     * - Prototype pattern
     * - Template method pattern
     * - Metodi che ritornano "this" o una nuova istanza
     * 
     * QUANDO NON USARE:
     * - Quando non c'è una vera gerarchia di tipi
     * - Quando il tipo di ritorno non è correlato all'ereditarietà
     * - Per forzare relazioni che non esistono naturalmente
     */

    // ===========================================
    // METODO MAIN - DIMOSTRAZIONE COMPLETA
    // ===========================================

    public static void sample() {
        System.out.println("=== DIMOSTRAZIONE COMPLETA SUI COVARIANT RETURN TYPES ===\n");

        // 1. Esempio base con Animal
        System.out.println("1. ESEMPIO BASE:");
        Dog dog = new Dog("Rex", "Labrador");
        Dog puppy = dog.reproduce(); // Ritorna Dog direttamente, no cast!
        System.out.println("Dog: " + dog);
        System.out.println("Puppy: " + puppy + ", Breed: " + puppy.getBreed());

        Cat cat = new Cat("Whiskers", 9);
        Cat kitten = cat.reproduce(); // Ritorna Cat direttamente
        System.out.println("Cat: " + cat);
        System.out.println("Kitten: " + kitten + ", Lives: " + kitten.getLivesRemaining());

        // 2. Factory methods
        System.out.println("\n2. FACTORY METHODS:");
        Car car = new Car("Tesla Model 3", 4);
        Car newCar = car.createNew("Tesla Model Y"); // Ritorna Car
        System.out.println("Original: " + car);
        System.out.println("New: " + newCar + ", Doors: " + newCar.getDoors());

        Motorcycle bike = new Motorcycle("Harley", true);
        Motorcycle newBike = bike.createNew("Honda"); // Ritorna Motorcycle
        System.out.println("Original bike: " + bike);
        System.out.println("New bike: " + newBike + ", Sidecar: " + newBike.hasSidecar());

        // 3. Clone con covariant return
        System.out.println("\n3. CLONE CON COVARIANT RETURN:");
        Circle circle = new Circle("Red", 5.0);
        Circle clonedCircle = circle.clone(); // No cast necessario!
        System.out.println("Original: " + circle + ", Area: " + circle.getArea());
        System.out.println("Cloned: " + clonedCircle + ", Area: " + clonedCircle.getArea());

        Rectangle rect = new Rectangle("Blue", 4.0, 6.0);
        Rectangle clonedRect = rect.clone();
        System.out.println("Original: " + rect + ", Area: " + rect.getArea());
        System.out.println("Cloned: " + clonedRect + ", Area: " + clonedRect.getArea());

        // 4. Builder pattern
        System.out.println("\n4. BUILDER PATTERN:");
        Employee employee = new EmployeeBuilder()
            .setName("Alice")
            .setAge(30)
            .setEmployeeId("EMP001")
            .setDepartment("Engineering")
            .build();
        System.out.println("Employee: " + employee);

        // 5. Interfacce
        System.out.println("\n5. INTERFACCE:");
        Document doc = new Document("Report", "Content here");
        Document docCopy = doc.copy(); // Ritorna Document
        System.out.println("Original: " + doc);
        System.out.println("Copy: " + docCopy);

        SecureDocument secDoc = new SecureDocument("Secret", "Classified", "KEY123");
        SecureDocument secCopy = secDoc.copy(); // Ritorna SecureDocument
        System.out.println("Original: " + secDoc);
        System.out.println("Copy: " + secCopy + ", Key: " + secCopy.getEncryptionKey());

        // 6. Hierarchy complesse
        System.out.println("\n6. HIERARCHY COMPLESSE:");
        Primate human = new Primate("Homo sapiens", true, true);
        Primate evolved = human.evolve(); // Ritorna Primate
        System.out.println("Original: " + human + ", Tools: " + human.canUseTools());
        System.out.println("Evolved: " + evolved + ", Tools: " + evolved.canUseTools());

        // 7. Abstract Factory
        System.out.println("\n7. ABSTRACT FACTORY PATTERN:");
        WindowsUIFactory winFactory = new WindowsUIFactory();
        WindowsButton winBtn = winFactory.createButton(); // Ritorna WindowsButton
        WindowsTextField winField = winFactory.createTextField();
        System.out.println("Windows UI: " + winBtn + ", " + winField);

        MacUIFactory macFactory = new MacUIFactory();
        MacButton macBtn = macFactory.createButton();
        MacTextField macField = macFactory.createTextField();
        System.out.println("Mac UI: " + macBtn + ", " + macField);

        // 8. Prototype pattern
        System.out.println("\n8. PROTOTYPE PATTERN:");
        ConcretePrototypeA protoA = new ConcretePrototypeA("A1", "Property A");
        ConcretePrototypeA clonedA = protoA.clonePrototype();
        System.out.println("Original: " + protoA);
        System.out.println("Cloned: " + clonedA);

        ConcretePrototypeB protoB = new ConcretePrototypeB("B1", 42);
        ConcretePrototypeB clonedB = protoB.clonePrototype();
        System.out.println("Original: " + protoB);
        System.out.println("Cloned: " + clonedB);

        // 9. Fluent interfaces
        System.out.println("\n9. FLUENT INTERFACES:");
        String basicQuery = new QueryBuilder()
            .select("id", "name")
            .from("users")
            .build();
        System.out.println("Basic Query: " + basicQuery);

        String advancedQuery = new AdvancedQueryBuilder()
            .select("id", "name", "email")
            .from("users")
            .where("age > 18")
            .orderBy("name")
            .limit(10)
            .build();
        System.out.println("Advanced Query: " + advancedQuery);

        // 10. Generic self-type
        System.out.println("\n10. GENERIC SELF-TYPE:");
        LabeledContainer<String> labeledCont = new LabeledContainer<>("Hello", "Greeting");
        labeledCont.setItem("Hi").setLabel("Salutation");
        System.out.println("Item: " + labeledCont.getItem() + ", Label: " + labeledCont.getLabel());

        // 11. Collections
        System.out.println("\n11. COLLECTIONS:");
        SortedImmutableList<Integer> sortedList = 
            new SortedImmutableList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Original: " + sortedList);
        System.out.println("Min: " + sortedList.getMin() + ", Max: " + sortedList.getMax());
        
        SortedImmutableList<Integer> newList = sortedList.add(3);
        System.out.println("After add: " + newList);
        System.out.println("New Min: " + newList.getMin() + ", New Max: " + newList.getMax());

        // 12. Composite pattern
        System.out.println("\n12. COMPOSITE PATTERN:");
        Composite root = new Composite("root");
        root.add(new Leaf("leaf1", "value1"));
        root.add(new Leaf("leaf2", "value2"));
        
        Composite child = new Composite("child");
        child.add(new Leaf("leaf3", "value3"));
        root.add(child);
        
        Composite rootCopy = root.copy();
        System.out.println("Original: " + root);
        System.out.println("Copy: " + rootCopy);

        // 13. Enum operations
        System.out.println("\n13. ENUM OPERATIONS:");
        MathOperationImpl add = MathOperation.ADDITION.getOperation();
        System.out.println("5 + 3 = " + add.apply(5, 3));
        MathOperationImpl inverse = add.inverse();
        System.out.println("Inverse: " + inverse.getName() + " -> 5 - 3 = " + inverse.apply(5, 3));

        // 14. Chain of Responsibility
        System.out.println("\n14. CHAIN OF RESPONSIBILITY:");
        AuthenticationHandler auth = new AuthenticationHandler();
        AuthorizationHandler authz = new AuthorizationHandler();
        LoggingHandler log = new LoggingHandler();
        
        auth.setNext(authz).setNext(log);
        auth.handle("auth+authz request");

        // 15. Immutable objects
        System.out.println("\n15. IMMUTABLE OBJECTS:");
        ImmutablePoint3D point = new ImmutablePoint3D(1, 2, 3);
        System.out.println("Original: " + point);
        
        ImmutablePoint3D movedX = point.withX(10); // Ritorna ImmutablePoint3D
        ImmutablePoint3D movedZ = movedX.withZ(30); // Può chiamare withZ()
        System.out.println("After withX(10): " + movedX);
        System.out.println("After withZ(30): " + movedZ);

        // 16. Limitazioni
        demonstrateLimitations();

        System.out.println("\n=== FINE DIMOSTRAZIONE ===");
    }
}
