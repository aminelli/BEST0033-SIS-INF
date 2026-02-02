package com.corso.samples.javaadv;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonSerializationContext;

/**
 * Esempio completo e avanzato sulla Serializzazione/Deserializzazione in Java
 * 
 * COS'È LA SERIALIZZAZIONE?
 * È il processo di conversione di un oggetto in un formato che può essere:
 * - Salvato su disco
 * - Trasmesso in rete
 * - Memorizzato in database
 * - Scambiato tra applicazioni
 * 
 * FORMATI SUPPORTATI:
 * 1. Java Native (ObjectOutputStream) - binario
 * 2. XML (JAXB, XMLEncoder) - testo strutturato
 * 3. JSON (Gson, Jackson) - formato leggero per API
 * 
 * Include:
 * - Serializzazione Java nativa
 * - Custom serialization (writeObject/readObject)
 * - Externalizable interface
 * - Transient e static fields
 * - SerialVersionUID
 * - XML serialization (JAXB, XMLEncoder/Decoder)
 * - JSON serialization (Gson, Jackson)
 * - Handling collections, dates, generics
 * - Versioning e compatibilità
 * - Performance comparison
 * - Best practices
 * 
 * DIPENDENZE MAVEN RICHIESTE:
 * - javax.xml.bind:jaxb-api:2.3.1
 * - org.glassfish.jaxb:jaxb-runtime:2.3.1
 * - com.google.code.gson:gson:2.10.1
 * - com.fasterxml.jackson.core:jackson-databind:2.15.2
 * - com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2
 */
public class SerializationDemo {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "serialization-demo/";

    public static void sample() {
        SerializationDemo demo = new SerializationDemo();
        
        System.out.println("=== SERIALIZZAZIONE/DESERIALIZZAZIONE - GUIDA COMPLETA ===\n");
        
        // Setup
        demo.setupDirectory();
        
        // Java Native Serialization
        demo.cosELaSerializzazione();
        demo.javaNativeSerialization();
        demo.customSerialization();
        demo.externalizableDemo();
        demo.transientFields();
        demo.serialVersionUIDDemo();
        demo.serializationInheritance();
        
        // XML Serialization
        demo.xmlSerializationJAXB();
        demo.xmlEncoderDecoder();
        
        // JSON Serialization
        demo.jsonSerializationGson();
        demo.jsonSerializationJackson();
        
        // Advanced
        demo.collectionsSerialization();
        demo.genericsSerialization();
        demo.dateTimeSerialization();
        demo.polymorphicSerialization();
        demo.circularReferences();
        
        // Comparison & Best Practices
        demo.performanceComparison();
        demo.versioningCompatibility();
        demo.bestPractices();
        
        // Cleanup
        demo.cleanup();
    }

    // ==================== SETUP ====================

    private void setupDirectory() {
        try {
            Files.createDirectories(Paths.get(TEMP_DIR));
            System.out.println("Directory setup: " + TEMP_DIR + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== COS'È LA SERIALIZZAZIONE ====================

    public void cosELaSerializzazione() {
        System.out.println("=== COS'È LA SERIALIZZAZIONE? ===\n");

        System.out.println("DEFINIZIONE:");
        System.out.println("Processo di conversione di oggetti Java in formato persistibile/trasmissibile.\n");

        System.out.println("FORMATI PRINCIPALI:");
        System.out.println("1. JAVA NATIVE (Binary)");
        System.out.println("   ✓ Formato binario compatto");
        System.out.println("   ✓ Veloce e efficiente");
        System.out.println("   ✗ Solo per Java");
        System.out.println("   ✗ Non human-readable");
        System.out.println("   Uso: Caching, sessioni, RMI\n");

        System.out.println("2. XML (eXtensible Markup Language)");
        System.out.println("   ✓ Human-readable");
        System.out.println("   ✓ Cross-platform");
        System.out.println("   ✓ Auto-documentante");
        System.out.println("   ✗ Verbose");
        System.out.println("   ✗ Più lento");
        System.out.println("   Uso: Config files, SOAP, legacy systems\n");

        System.out.println("3. JSON (JavaScript Object Notation)");
        System.out.println("   ✓ Leggero e compatto");
        System.out.println("   ✓ Human-readable");
        System.out.println("   ✓ Cross-platform");
        System.out.println("   ✓ Standard per REST API");
        System.out.println("   ✗ Meno espressivo di XML");
        System.out.println("   Uso: REST API, Web services, NoSQL\n");

        System.out.println("INTERFACCE CHIAVE:");
        System.out.println("- Serializable: marker interface");
        System.out.println("- Externalizable: controllo completo");
        System.out.println("- serialVersionUID: versioning\n");

        System.out.println();
    }

    // ==================== JAVA NATIVE SERIALIZATION ====================

    public void javaNativeSerialization() {
        System.out.println("=== JAVA NATIVE SERIALIZATION ===");

        String filename = TEMP_DIR + "person.ser";

        // Serializzazione
        Person person = new Person("Mario Rossi", 35, "mario@example.com");
        person.setAddress(new Address("Via Roma", "Milano", "20100"));
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(person);
            System.out.println("Oggetto serializzato: " + person);
            System.out.println("File: " + filename);
            System.out.println("Dimensione: " + Files.size(Paths.get(filename)) + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Person loaded = (Person) ois.readObject();
            System.out.println("\nOggetto deserializzato: " + loaded);
            System.out.println("Indirizzo: " + loaded.getAddress());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Serializzazione multipla
        System.out.println("\n--- Lista di oggetti ---");
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "alice@test.com"),
            new Person("Bob", 30, "bob@test.com"),
            new Person("Charlie", 28, "charlie@test.com")
        );

        String listFile = TEMP_DIR + "people-list.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(listFile))) {
            oos.writeObject(people);
            System.out.println("Lista serializzata: " + people.size() + " persone");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(listFile))) {
            @SuppressWarnings("unchecked")
            List<Person> loadedList = (List<Person>) ois.readObject();
            System.out.println("Lista deserializzata:");
            loadedList.forEach(p -> System.out.println("  " + p));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== CUSTOM SERIALIZATION ====================

    public void customSerialization() {
        System.out.println("=== CUSTOM SERIALIZATION ===");

        String filename = TEMP_DIR + "employee.ser";

        // Employee con custom serialization
        Employee emp = new Employee("E001", "John Doe", 50000.0);
        emp.setPassword("secretPassword123"); // Non verrà serializzato
        emp.addSkill("Java");
        emp.addSkill("Python");
        emp.addSkill("SQL");

        System.out.println("Oggetto originale: " + emp);
        System.out.println("Password (transient): " + emp.getPassword());
        System.out.println("Skills: " + emp.getSkills());

        // Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(emp);
            System.out.println("\nSerializzato con custom writeObject()");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Employee loaded = (Employee) ois.readObject();
            System.out.println("\nOggetto deserializzato: " + loaded);
            System.out.println("Password (transient): " + loaded.getPassword());
            System.out.println("Skills (custom): " + loaded.getSkills());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== EXTERNALIZABLE DEMO ====================

    public void externalizableDemo() {
        System.out.println("=== EXTERNALIZABLE INTERFACE ===");

        String filename = TEMP_DIR + "product.ser";

        // Product con Externalizable (controllo completo)
        Product product = new Product("P001", "Laptop", 999.99, 10);
        product.setDescription("High-performance laptop");

        System.out.println("Oggetto originale: " + product);

        // Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(product);
            System.out.println("Serializzato con Externalizable");
            System.out.println("Dimensione: " + Files.size(Paths.get(filename)) + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Product loaded = (Product) ois.readObject();
            System.out.println("\nOggetto deserializzato: " + loaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== TRANSIENT FIELDS ====================

    public void transientFields() {
        System.out.println("=== TRANSIENT FIELDS ===");

        String filename = TEMP_DIR + "user.ser";

        User user = new User("user123", "password456");
        user.setSessionToken("temporary-session-token-xyz");
        user.setLastLogin(LocalDateTime.now());

        System.out.println("Prima della serializzazione:");
        System.out.println("  Username: " + user.getUsername());
        System.out.println("  Password (transient): " + user.getPassword());
        System.out.println("  Session token (transient): " + user.getSessionToken());
        System.out.println("  Last login: " + user.getLastLogin());

        // Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            User loaded = (User) ois.readObject();
            System.out.println("\nDopo deserializzazione:");
            System.out.println("  Username: " + loaded.getUsername());
            System.out.println("  Password (transient): " + loaded.getPassword());
            System.out.println("  Session token (transient): " + loaded.getSessionToken());
            System.out.println("  Last login: " + loaded.getLastLogin());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== SERIAL VERSION UID ====================

    public void serialVersionUIDDemo() {
        System.out.println("=== SERIAL VERSION UID ===");

        System.out.println("SerialVersionUID è usato per:");
        System.out.println("- Verificare compatibilità tra versioni");
        System.out.println("- Evitare InvalidClassException");
        System.out.println("- Gestire evoluzione della classe\n");

        System.out.println("Generazione:");
        System.out.println("1. Automatica (sconsigliata)");
        System.out.println("2. Manuale: private static final long serialVersionUID = 1L;");
        System.out.println("3. Tool: serialver command\n");

        System.out.println("Esempi:");
        System.out.println("Person serialVersionUID: " + getSerialVersionUID(Person.class));
        System.out.println("Employee serialVersionUID: " + getSerialVersionUID(Employee.class));

        System.out.println();
    }

    private long getSerialVersionUID(Class<?> clazz) {
        try {
            java.lang.reflect.Field field = clazz.getDeclaredField("serialVersionUID");
            field.setAccessible(true);
            return field.getLong(null);
        } catch (Exception e) {
            return 0L;
        }
    }

    // ==================== SERIALIZATION INHERITANCE ====================

    public void serializationInheritance() {
        System.out.println("=== SERIALIZATION INHERITANCE ===");

        String filename = TEMP_DIR + "manager.ser";

        Manager manager = new Manager("M001", "Jane Smith", 80000.0, 5);
        manager.setAddress(new Address("Via Verdi", "Roma", "00100"));

        System.out.println("Manager originale: " + manager);
        System.out.println("Team size: " + manager.getTeamSize());

        // Serializzazione
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(manager);
            System.out.println("\nManager serializzato");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Manager loaded = (Manager) ois.readObject();
            System.out.println("\nManager deserializzato: " + loaded);
            System.out.println("Team size: " + loaded.getTeamSize());
            System.out.println("Indirizzo (da Person): " + loaded.getAddress());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== XML SERIALIZATION JAXB ====================

    public void xmlSerializationJAXB() {
        System.out.println("=== XML SERIALIZATION (JAXB) ===");

        String filename = TEMP_DIR + "person.xml";

        // Creare oggetto
        PersonXML person = new PersonXML("Mario Rossi", 35, "mario@example.com");
        person.setAddress(new AddressXML("Via Roma", "Milano", "20100"));
        person.getPhones().add("123-456-7890");
        person.getPhones().add("098-765-4321");

        // Serializzazione XML
        try {
            JAXBContext context = JAXBContext.newInstance(PersonXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // Su file
            marshaller.marshal(person, new File(filename));
            System.out.println("XML serializzato in: " + filename);
            
            // Su console
            System.out.println("\nContenuto XML:");
            marshaller.marshal(person, System.out);
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Deserializzazione XML
        try {
            JAXBContext context = JAXBContext.newInstance(PersonXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            PersonXML loaded = (PersonXML) unmarshaller.unmarshal(new File(filename));
            System.out.println("\n\nOggetto deserializzato da XML:");
            System.out.println("  Nome: " + loaded.getName());
            System.out.println("  Età: " + loaded.getAge());
            System.out.println("  Email: " + loaded.getEmail());
            System.out.println("  Indirizzo: " + loaded.getAddress());
            System.out.println("  Telefoni: " + loaded.getPhones());
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Lista di oggetti
        System.out.println("\n--- Lista XML ---");
        String listFile = TEMP_DIR + "people-list.xml";
        
        PeopleListXML peopleList = new PeopleListXML();
        peopleList.getPeople().add(new PersonXML("Alice", 25, "alice@test.com"));
        peopleList.getPeople().add(new PersonXML("Bob", 30, "bob@test.com"));
        peopleList.getPeople().add(new PersonXML("Charlie", 28, "charlie@test.com"));

        try {
            JAXBContext context = JAXBContext.newInstance(PeopleListXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(peopleList, new File(listFile));
            
            System.out.println("Lista XML serializzata");
            
            // Deserializzazione
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PeopleListXML loadedList = (PeopleListXML) unmarshaller.unmarshal(new File(listFile));
            System.out.println("Lista deserializzata: " + loadedList.getPeople().size() + " persone");
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== XML ENCODER/DECODER ====================

    public void xmlEncoderDecoder() {
        System.out.println("=== XML ENCODER/DECODER (JavaBeans) ===");

        String filename = TEMP_DIR + "person-beans.xml";

        Person person = new Person("Luigi Verdi", 40, "luigi@example.com");
        person.setAddress(new Address("Via Garibaldi", "Torino", "10100"));

        // XMLEncoder - serializzazione
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            encoder.writeObject(person);
            System.out.println("Serializzato con XMLEncoder");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostra contenuto XML
        try {
            String content = Files.readString(Paths.get(filename));
            System.out.println("\nContenuto XML (primi 500 chars):");
            System.out.println(content.substring(0, Math.min(500, content.length())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // XMLDecoder - deserializzazione
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(new FileInputStream(filename)))) {
            Person loaded = (Person) decoder.readObject();
            System.out.println("\nDeserializzato con XMLDecoder: " + loaded);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== JSON SERIALIZATION GSON ====================

    public void jsonSerializationGson() {
        System.out.println("=== JSON SERIALIZATION (GSON) ===");

        String filename = TEMP_DIR + "person-gson.json";

        // Creare Gson
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        // Oggetto da serializzare
        PersonJSON person = new PersonJSON("Mario Rossi", 35, "mario@example.com");
        person.setAddress(new AddressJSON("Via Roma", "Milano", "20100"));
        person.getHobbies().add("Reading");
        person.getHobbies().add("Gaming");
        person.getHobbies().add("Cooking");

        // Serializzazione a stringa
        String json = gson.toJson(person);
        System.out.println("JSON serializzato:");
        System.out.println(json);

        // Serializzazione su file
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(person, writer);
            System.out.println("\nJSON salvato in: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializzazione da stringa
        PersonJSON loaded = gson.fromJson(json, PersonJSON.class);
        System.out.println("\nDeserializzato da stringa: " + loaded);

        // Deserializzazione da file
        try (Reader reader = new FileReader(filename)) {
            PersonJSON fromFile = gson.fromJson(reader, PersonJSON.class);
            System.out.println("Deserializzato da file: " + fromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lista
        System.out.println("\n--- Lista JSON ---");
        List<PersonJSON> people = Arrays.asList(
            new PersonJSON("Alice", 25, "alice@test.com"),
            new PersonJSON("Bob", 30, "bob@test.com"),
            new PersonJSON("Charlie", 28, "charlie@test.com")
        );

        String listJson = gson.toJson(people);
        System.out.println("Lista JSON:");
        System.out.println(listJson);

        // Deserializzazione lista
        java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<List<PersonJSON>>(){}.getType();
        List<PersonJSON> loadedList = gson.fromJson(listJson, listType);
        System.out.println("\nLista deserializzata: " + loadedList.size() + " persone");

        // Map
        System.out.println("\n--- Map JSON ---");
        Map<String, PersonJSON> peopleMap = new HashMap<>();
        peopleMap.put("user1", new PersonJSON("User One", 25, "user1@test.com"));
        peopleMap.put("user2", new PersonJSON("User Two", 30, "user2@test.com"));

        String mapJson = gson.toJson(peopleMap);
        System.out.println("Map JSON:");
        System.out.println(mapJson);

        System.out.println();
    }

    // ==================== JSON SERIALIZATION JACKSON ====================

    public void jsonSerializationJackson() {
        System.out.println("=== JSON SERIALIZATION (JACKSON) ===");

        String filename = TEMP_DIR + "person-jackson.json";

        // Creare ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Oggetto da serializzare
        PersonJackson person = new PersonJackson("Mario Rossi", 35, "mario@example.com");
        person.setAddress(new AddressJackson("Via Roma", "Milano", "20100"));
        person.setCreatedAt(LocalDateTime.now());
        person.getSkills().put("Java", 5);
        person.getSkills().put("Python", 3);
        person.getSkills().put("SQL", 4);

        try {
            // Serializzazione a stringa
            String json = mapper.writeValueAsString(person);
            System.out.println("JSON serializzato:");
            System.out.println(json);

            // Serializzazione su file
            mapper.writeValue(new File(filename), person);
            System.out.println("\nJSON salvato in: " + filename);

            // Deserializzazione da stringa
            PersonJackson loaded = mapper.readValue(json, PersonJackson.class);
            System.out.println("\nDeserializzato da stringa: " + loaded);
            System.out.println("Skills: " + loaded.getSkills());
            System.out.println("Created at: " + loaded.getCreatedAt());

            // Deserializzazione da file
            PersonJackson fromFile = mapper.readValue(new File(filename), PersonJackson.class);
            System.out.println("Deserializzato da file: " + fromFile);

            // Lista
            System.out.println("\n--- Lista JSON ---");
            List<PersonJackson> people = Arrays.asList(
                new PersonJackson("Alice", 25, "alice@test.com"),
                new PersonJackson("Bob", 30, "bob@test.com")
            );

            String listJson = mapper.writeValueAsString(people);
            System.out.println("Lista JSON (primi 200 chars):");
            System.out.println(listJson.substring(0, Math.min(200, listJson.length())) + "...");

            // JsonNode per manipolazione dinamica
            System.out.println("\n--- JsonNode ---");
            com.fasterxml.jackson.databind.JsonNode rootNode = mapper.readTree(json);
            String name = rootNode.get("name").asText();
            int age = rootNode.get("age").asInt();
            System.out.println("Da JsonNode - Name: " + name + ", Age: " + age);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== COLLECTIONS SERIALIZATION ====================

    public void collectionsSerialization() {
        System.out.println("=== COLLECTIONS SERIALIZATION ===");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // List
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "C++");
        String listJson = gson.toJson(list);
        System.out.println("List JSON: " + listJson);

        // Set
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        String setJson = gson.toJson(set);
        System.out.println("Set JSON: " + setJson);

        // Map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", 30);
        map.put("active", true);
        String mapJson = gson.toJson(map);
        System.out.println("Map JSON: " + mapJson);

        // Nested collections
        Map<String, List<String>> nestedMap = new HashMap<>();
        nestedMap.put("skills", Arrays.asList("Java", "SQL"));
        nestedMap.put("hobbies", Arrays.asList("Reading", "Gaming"));
        String nestedJson = gson.toJson(nestedMap);
        System.out.println("\nNested Map JSON:");
        System.out.println(nestedJson);

        System.out.println();
    }

    // ==================== GENERICS SERIALIZATION ====================

    public void genericsSerialization() {
        System.out.println("=== GENERICS SERIALIZATION ===");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Generic class
        Container<String> stringContainer = new Container<>("Hello World");
        String json = gson.toJson(stringContainer);
        System.out.println("Container<String> JSON:");
        System.out.println(json);

        // Deserializzazione con TypeToken
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Container<String>>(){}.getType();
        Container<String> loaded = gson.fromJson(json, type);
        System.out.println("Deserializzato: " + loaded.getValue());

        // Generic con oggetti complessi
        Container<PersonJSON> personContainer = new Container<>(
            new PersonJSON("Test", 25, "test@example.com")
        );
        String personJson = gson.toJson(personContainer);
        System.out.println("\nContainer<Person> JSON:");
        System.out.println(personJson);

        System.out.println();
    }

    // ==================== DATE TIME SERIALIZATION ====================

    public void dateTimeSerialization() {
        System.out.println("=== DATE/TIME SERIALIZATION ===");

        // Gson with custom adapter
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new com.google.gson.JsonSerializer<LocalDateTime>() {
                @Override
                public com.google.gson.JsonElement serialize(LocalDateTime src, 
                        java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                    return new com.google.gson.JsonPrimitive(src.toString());
                }
            })
            .registerTypeAdapter(LocalDateTime.class, new com.google.gson.JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(com.google.gson.JsonElement json, 
                        java.lang.reflect.Type typeOfT, JsonDeserializationContext context) {
                    return LocalDateTime.parse(json.getAsString());
                }
            })
            .create();

        DateTimeData data = new DateTimeData();
        data.setCreated(LocalDateTime.now());
        data.setBirthDate(LocalDate.of(1990, 5, 15));
        data.setStartTime(LocalTime.of(9, 30));

        String json = gson.toJson(data);
        System.out.println("DateTime JSON:");
        System.out.println(json);

        DateTimeData loaded = gson.fromJson(json, DateTimeData.class);
        System.out.println("\nDeserializzato:");
        System.out.println("  Created: " + loaded.getCreated());
        System.out.println("  BirthDate: " + loaded.getBirthDate());
        System.out.println("  StartTime: " + loaded.getStartTime());

        System.out.println();
    }

    // ==================== POLYMORPHIC SERIALIZATION ====================

    public void polymorphicSerialization() {
        System.out.println("=== POLYMORPHIC SERIALIZATION ===");

        // Gson con RuntimeTypeAdapterFactory
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Shape.class, new com.google.gson.JsonSerializer<Shape>() {
                @Override
                public com.google.gson.JsonElement serialize(Shape src, 
                        java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                    com.google.gson.JsonObject obj = new com.google.gson.JsonObject();
                    obj.addProperty("type", src.getClass().getSimpleName());
                    obj.add("data", context.serialize(src));
                    return obj;
                }
            })
            .create();

        List<Shape> shapes = Arrays.asList(
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Circle(3.0)
        );

        String json = gson.toJson(shapes);
        System.out.println("Polymorphic JSON:");
        System.out.println(json);

        System.out.println();
    }

    // ==================== CIRCULAR REFERENCES ====================

    public void circularReferences() {
        System.out.println("=== CIRCULAR REFERENCES ===");

        System.out.println("Problema: Riferimenti circolari causano stack overflow");
        System.out.println("Esempio: Parent -> Child -> Parent\n");

        System.out.println("Soluzioni:");
        System.out.println("1. @JsonIgnore (Jackson) / transient");
        System.out.println("2. @JsonIdentityInfo (Jackson)");
        System.out.println("3. @JsonManagedReference / @JsonBackReference");
        System.out.println("4. Custom serializer\n");

        // Esempio con Jackson
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            Node parent = new Node("Parent");
            Node child1 = new Node("Child1");
            Node child2 = new Node("Child2");
            
            parent.getChildren().add(child1);
            parent.getChildren().add(child2);
            child1.setParent(parent);
            child2.setParent(parent);

            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parent);
            System.out.println("JSON con riferimenti gestiti:");
            System.out.println(json);
            
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        System.out.println();
    }

    // ==================== PERFORMANCE COMPARISON ====================

    public void performanceComparison() {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        PersonJSON person = new PersonJSON("Test User", 30, "test@example.com");
        person.setAddress(new AddressJSON("Test Street", "Test City", "12345"));
        
        int iterations = 10000;

        // Java Native
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                Person p = new Person(person.getName(), person.getAge(), person.getEmail());
                oos.writeObject(p);
                oos.close();
            } catch (IOException e) {}
        }
        long javaNativeTime = System.nanoTime() - start;

        // JSON Gson
        Gson gson = new Gson();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            gson.toJson(person);
        }
        long gsonTime = System.nanoTime() - start;

        // JSON Jackson
        ObjectMapper mapper = new ObjectMapper();
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            try {
                mapper.writeValueAsString(person);
            } catch (Exception e) {}
        }
        long jacksonTime = System.nanoTime() - start;

        System.out.println("Risultati (" + iterations + " iterazioni):");
        System.out.println("  Java Native: " + (javaNativeTime / 1_000_000) + " ms");
        System.out.println("  Gson: " + (gsonTime / 1_000_000) + " ms");
        System.out.println("  Jackson: " + (jacksonTime / 1_000_000) + " ms");

        // Size comparison
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            Person p = new Person(person.getName(), person.getAge(), person.getEmail());
            oos.writeObject(p);
            int nativeSize = baos.size();

            String gsonJson = gson.toJson(person);
            int gsonSize = gsonJson.getBytes().length;

            String jacksonJson = mapper.writeValueAsString(person);
            int jacksonSize = jacksonJson.getBytes().length;

            System.out.println("\nDimensioni:");
            System.out.println("  Java Native: " + nativeSize + " bytes");
            System.out.println("  Gson JSON: " + gsonSize + " bytes");
            System.out.println("  Jackson JSON: " + jacksonSize + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== VERSIONING COMPATIBILITY ====================

    public void versioningCompatibility() {
        System.out.println("=== VERSIONING & COMPATIBILITY ===");

        System.out.println("STRATEGIE DI VERSIONING:\n");

        System.out.println("1. SERIAL VERSION UID:");
        System.out.println("   - Cambiare per incompatibilità intenzionale");
        System.out.println("   - Mantenere per retro-compatibilità");
        System.out.println("   - Default: generato dal compilatore\n");

        System.out.println("2. CAMPI COMPATIBILI:");
        System.out.println("   ✓ Aggiungere campi non-final");
        System.out.println("   ✓ Rendere campi transient");
        System.out.println("   ✗ Rimuovere campi");
        System.out.println("   ✗ Cambiare tipo di campi\n");

        System.out.println("3. JSON VERSIONING:");
        System.out.println("   - Aggiungere campo 'version'");
        System.out.println("   - Ignorare campi sconosciuti");
        System.out.println("   - Default values per campi mancanti\n");

        System.out.println("4. CUSTOM readObject:");
        System.out.println("   - Gestire versioni diverse");
        System.out.println("   - Migrare dati vecchi");
        System.out.println("   - Validazione\n");

        System.out.println("ESEMPIO:");
        VersionedObject v1 = new VersionedObject();
        v1.setVersion(1);
        v1.setName("Test");
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(v1);
        System.out.println(json);

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");

        System.out.println("1. SEMPRE DEFINIRE serialVersionUID:");
        System.out.println("   ✓ private static final long serialVersionUID = 1L;");
        System.out.println("   Evita problemi di compatibilità\n");

        System.out.println("2. USA transient PER DATI SENSIBILI:");
        System.out.println("   ✓ Password, token, session data");
        System.out.println("   ✗ Non serializzare dati confidenziali\n");

        System.out.println("3. SCEGLI IL FORMATO GIUSTO:");
        System.out.println("   - Java Native: caching interno, RMI");
        System.out.println("   - JSON: REST API, web services");
        System.out.println("   - XML: legacy, SOAP, config files\n");

        System.out.println("4. GESTISCI VERSIONING:");
        System.out.println("   ✓ Pianifica evoluzione classi");
        System.out.println("   ✓ Testa compatibilità versioni");
        System.out.println("   ✓ Documenta cambiamenti\n");

        System.out.println("5. VALIDA DATI DESERIALIZZATI:");
        System.out.println("   ✓ Null checks");
        System.out.println("   ✓ Range validation");
        System.out.println("   ✓ Business rules\n");

        System.out.println("6. USA try-with-resources:");
        System.out.println("   ✓ Chiusura automatica stream");
        System.out.println("   ✓ Gestione eccezioni\n");

        System.out.println("7. CONSIDERA PERFORMANCE:");
        System.out.println("   - Jackson > Gson per grandi volumi");
        System.out.println("   - Binary > Text per dimensioni");
        System.out.println("   - Cache serializer/deserializer\n");

        System.out.println("8. SICUREZZA:");
        System.out.println("   ✗ Non deserializzare dati non fidati");
        System.out.println("   ✓ Validazione input");
        System.out.println("   ✓ Whitelist di classi\n");

        System.out.println("9. IMMUTABILITÀ:");
        System.out.println("   ✓ Preferire oggetti immutabili");
        System.out.println("   ✓ Final fields quando possibile\n");

        System.out.println("10. TESTING:");
        System.out.println("    ✓ Test serialization/deserialization");
        System.out.println("    ✓ Test compatibilità versioni");
        System.out.println("    ✓ Test edge cases (null, empty, large)\n");

        System.out.println();
    }

    // ==================== CLEANUP ====================

    private void cleanup() {
        try {
            Files.walk(Paths.get(TEMP_DIR))
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {}
                });
            System.out.println("=== CLEANUP COMPLETATO ===");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== CLASSI DI SUPPORTO ====================

    // Java Native Serialization
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String name;
        private int age;
        private String email;
        private Address address;

        public Person() {}

        public Person(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, email='%s'}", name, age, email);
        }
    }

    static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String street;
        private String city;
        private String zipCode;

        public Address() {}

        public Address(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getZipCode() { return zipCode; }
        public void setZipCode(String zipCode) { this.zipCode = zipCode; }

        @Override
        public String toString() {
            return String.format("%s, %s %s", street, city, zipCode);
        }
    }

    // Custom Serialization
    static class Employee implements Serializable {
        private static final long serialVersionUID = 2L;
        
        private String id;
        private String name;
        private double salary;
        private transient String password; // Non serializzato
        private transient List<String> skills; // Serializzato custom

        public Employee(String id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.skills = new ArrayList<>();
        }

        public void addSkill(String skill) {
            if (skills == null) skills = new ArrayList<>();
            skills.add(skill);
        }

        // Custom serialization
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            // Serializza skills manualmente
            oos.writeInt(skills != null ? skills.size() : 0);
            if (skills != null) {
                for (String skill : skills) {
                    oos.writeUTF(skill);
                }
            }
        }

        private void readObject(ObjectInputStream ois) 
                throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            // Deserializza skills manualmente
            int skillCount = ois.readInt();
            skills = new ArrayList<>();
            for (int i = 0; i < skillCount; i++) {
                skills.add(ois.readUTF());
            }
        }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public List<String> getSkills() { return skills; }

        @Override
        public String toString() {
            return String.format("Employee{id='%s', name='%s', salary=%.2f}", id, name, salary);
        }
    }

    // Externalizable
    static class Product implements Externalizable {
        private String code;
        private String name;
        private double price;
        private int quantity;
        private String description;

        public Product() {} // Richiesto per Externalizable

        public Product(String code, String name, double price, int quantity) {
            this.code = code;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(code);
            out.writeUTF(name);
            out.writeDouble(price);
            out.writeInt(quantity);
            // description non serializzato intenzionalmente
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException {
            code = in.readUTF();
            name = in.readUTF();
            price = in.readDouble();
            quantity = in.readInt();
        }

        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return String.format("Product{code='%s', name='%s', price=%.2f, qty=%d}", 
                code, name, price, quantity);
        }
    }

    // Transient fields
    static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String username;
        private transient String password; // Non serializzato
        private transient String sessionToken; // Non serializzato
        private LocalDateTime lastLogin;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public void setSessionToken(String token) { this.sessionToken = token; }
        public String getSessionToken() { return sessionToken; }
        public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
        public LocalDateTime getLastLogin() { return lastLogin; }
    }

    // Inheritance
    static class Manager extends Employee {
        private static final long serialVersionUID = 1L;
        
        private int teamSize;
        private Address address;

        public Manager(String id, String name, double salary, int teamSize) {
            super(id, name, salary);
            this.teamSize = teamSize;
        }

        public int getTeamSize() { return teamSize; }
        public void setAddress(Address address) { this.address = address; }
        public Address getAddress() { return address; }

        @Override
        public String toString() {
            return super.toString() + String.format(", teamSize=%d", teamSize);
        }
    }

    // XML JAXB
    @XmlRootElement(name = "person")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class PersonXML {
        private String name;
        private int age;
        private String email;
        
        @XmlElement
        private AddressXML address;
        
        @XmlElementWrapper(name = "phones")
        @XmlElement(name = "phone")
        private List<String> phones = new ArrayList<>();

        public PersonXML() {}

        public PersonXML(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public AddressXML getAddress() { return address; }
        public void setAddress(AddressXML address) { this.address = address; }
        public List<String> getPhones() { return phones; }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    static class AddressXML {
        private String street;
        private String city;
        private String zipCode;

        public AddressXML() {}

        public AddressXML(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return String.format("%s, %s %s", street, city, zipCode);
        }
    }

    @XmlRootElement(name = "people")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class PeopleListXML {
        @XmlElement(name = "person")
        private List<PersonXML> people = new ArrayList<>();

        public List<PersonXML> getPeople() { return people; }
    }

    // JSON
    static class PersonJSON {
        private String name;
        private int age;
        private String email;
        private AddressJSON address;
        private List<String> hobbies = new ArrayList<>();

        public PersonJSON(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
        public void setAddress(AddressJSON address) { this.address = address; }
        public AddressJSON getAddress() { return address; }
        public List<String> getHobbies() { return hobbies; }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d}", name, age);
        }
    }

    static class AddressJSON {
        private String street;
        private String city;
        private String zipCode;

        public AddressJSON(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return String.format("%s, %s %s", street, city, zipCode);
        }
    }

    // Jackson
    static class PersonJackson {
        private String name;
        private int age;
        private String email;
        private AddressJackson address;
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        
        private Map<String, Integer> skills = new HashMap<>();

        public PersonJackson() {}

        public PersonJackson(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public AddressJackson getAddress() { return address; }
        public void setAddress(AddressJackson address) { this.address = address; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public Map<String, Integer> getSkills() { return skills; }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d}", name, age);
        }
    }

    static class AddressJackson {
        private String street;
        private String city;
        private String zipCode;

        public AddressJackson() {}

        public AddressJackson(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }

        public String getStreet() { return street; }
        public String getCity() { return city; }
        public String getZipCode() { return zipCode; }
    }

    // Generics
    static class Container<T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        public T getValue() { return value; }
    }

    // DateTime
    static class DateTimeData {
        private LocalDateTime created;
        private LocalDate birthDate;
        private LocalTime startTime;

        public LocalDateTime getCreated() { return created; }
        public void setCreated(LocalDateTime created) { this.created = created; }
        public LocalDate getBirthDate() { return birthDate; }
        public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
        public LocalTime getStartTime() { return startTime; }
        public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    }

    // Polymorphic
    interface Shape {
        double area();
    }

    static class Circle implements Shape {
        private double radius;

        public Circle(double radius) { this.radius = radius; }
        public double getRadius() { return radius; }

        @Override
        public double area() { return Math.PI * radius * radius; }
    }

    static class Rectangle implements Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() { return width; }
        public double getHeight() { return height; }

        @Override
        public double area() { return width * height; }
    }

    // Circular references
    static class Node {
        private String name;
        
        @JsonManagedReference
        private List<Node> children = new ArrayList<>();
        
        @JsonBackReference
        private Node parent;

        public Node(String name) { this.name = name; }

        public String getName() { return name; }
        public List<Node> getChildren() { return children; }
        public void setParent(Node parent) { this.parent = parent; }
    }

    // Versioned object
    static class VersionedObject {
        private int version;
        private String name;

        public int getVersion() { return version; }
        public void setVersion(int version) { this.version = version; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
