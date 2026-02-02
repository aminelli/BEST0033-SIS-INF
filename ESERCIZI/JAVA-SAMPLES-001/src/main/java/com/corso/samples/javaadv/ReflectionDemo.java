package com.corso.samples.javaadv;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Esempio completo e avanzato sulla Reflection in Java
 * 
 * COS'È LA REFLECTION?
 * La Reflection è un meccanismo che permette di ispezionare e manipolare
 * classi, metodi, campi e costruttori a runtime. Fornisce la capacità di:
 * - Esaminare la struttura interna di una classe
 * - Creare istanze dinamicamente
 * - Invocare metodi dinamicamente
 * - Accedere e modificare campi (anche privati)
 * - Lavorare con annotazioni
 * 
 * COME FUNZIONA?
 * Ogni classe caricata dalla JVM ha un oggetto Class associato che contiene
 * tutte le informazioni metadata sulla classe. Attraverso questo oggetto
 * possiamo ottenere informazioni complete sulla struttura della classe.
 * 
 * Include:
 * - Ottenere oggetti Class
 * - Ispezione di classi (metodi, campi, costruttori)
 * - Creazione dinamica di istanze
 * - Invocazione dinamica di metodi
 * - Accesso a membri privati
 * - Lavorare con annotazioni
 * - Generics e Type
 * - Array reflection
 * - Proxy dinamici
 * - Module reflection (Java 9+)
 * - Performance e best practices
 */
public class ReflectionDemo {

    public static void sample() {
        ReflectionDemo demo = new ReflectionDemo();
        
        System.out.println("=== REFLECTION IN JAVA - GUIDA COMPLETA ===\n");
        
        // Basi
        demo.cosELaReflection();
        demo.ottenereClassObject();
        demo.ispezioneClasse();
        
        // Membri della classe
        demo.ispezioneCampi();
        demo.ispezioneMetodi();
        demo.ispezioneCostruttori();
        demo.ispezioneAnnotazioni();
        
        // Manipolazione dinamica
        demo.creazioneIstanzeDinamiche();
        demo.invocazioneMetodiDinamici();
        demo.accessoCampiPrivati();
        demo.modificaCampiPrivati();
        
        // Avanzato
        demo.lavorareConGenerics();
        demo.arrayReflection();
        demo.proxyDinamici();
        demo.enumReflection();
        demo.moduleReflection();
        
        // Pattern e casi d'uso
        demo.dependencyInjection();
        demo.objectMapper();
        demo.testingFramework();
        demo.performanceConsiderations();
        demo.bestPractices();
    }

    // ==================== COS'È LA REFLECTION ====================

    public void cosELaReflection() {
        System.out.println("=== COS'È LA REFLECTION? ===\n");

        System.out.println("DEFINIZIONE:");
        System.out.println("La Reflection è la capacità di un programma di esaminare");
        System.out.println("e modificare la propria struttura e comportamento a runtime.\n");

        System.out.println("VANTAGGI:");
        System.out.println("✓ Ispezione runtime di classi sconosciute");
        System.out.println("✓ Invocazione dinamica di metodi");
        System.out.println("✓ Creazione dinamica di oggetti");
        System.out.println("✓ Accesso a membri privati (testing)");
        System.out.println("✓ Framework e librerie (Spring, Hibernate, JUnit)");
        System.out.println("✓ Serializzazione/Deserializzazione");
        System.out.println("✓ Dependency Injection");
        System.out.println("✓ Proxy dinamici\n");

        System.out.println("SVANTAGGI:");
        System.out.println("✗ Performance overhead");
        System.out.println("✗ Violazione dell'incapsulamento");
        System.out.println("✗ Problemi di sicurezza");
        System.out.println("✗ Codice più complesso");
        System.out.println("✗ Errori solo a runtime (non compile-time)\n");

        System.out.println("PACKAGE PRINCIPALE:");
        System.out.println("java.lang.reflect.*");
        System.out.println("  - Class<T>");
        System.out.println("  - Field");
        System.out.println("  - Method");
        System.out.println("  - Constructor");
        System.out.println("  - Modifier");
        System.out.println("  - Array");
        System.out.println("  - Proxy");
        System.out.println();
    }

    // ==================== OTTENERE CLASS OBJECT ====================

    public void ottenereClassObject() {
        System.out.println("=== OTTENERE OGGETTO CLASS ===");

        // Modo 1: .class literal
        Class<String> class1 = String.class;
        System.out.println("1. Class literal: " + class1.getName());

        // Modo 2: Object.getClass()
        String str = "Hello";
        Class<?> class2 = str.getClass();
        System.out.println("2. getClass(): " + class2.getName());

        // Modo 3: Class.forName() - caricamento dinamico
        try {
            Class<?> class3 = Class.forName("java.util.ArrayList");
            System.out.println("3. forName(): " + class3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Modo 4: primitive types
        Class<Integer> class4 = int.class;
        Class<Integer> class5 = Integer.TYPE;
        System.out.println("4. Primitive: " + class4.getName());
        System.out.println("   TYPE: " + class5.getName());

        // Modo 5: array types
        Class<?> class6 = int[].class;
        Class<?> class7 = String[][].class;
        System.out.println("5. Array int[]: " + class6.getName());
        System.out.println("   Array String[][]: " + class7.getName());

        // Confronto Class objects
        System.out.println("\nConfronto:");
        System.out.println("String.class == str.getClass(): " + 
            (String.class == str.getClass()));

        // Class per interfacce e classi astratte
        Class<?> listClass = List.class;
        Class<?> abstractClass = AbstractList.class;
        System.out.println("\nInterfaccia List: " + listClass.getName());
        System.out.println("Classe astratta: " + abstractClass.getName());

        System.out.println();
    }

    // ==================== ISPEZIONE CLASSE ====================

    public void ispezioneClasse() {
        System.out.println("=== ISPEZIONE CLASSE ===");

        Class<?> clazz = ArrayList.class;

        // Informazioni base
        System.out.println("Informazioni base:");
        System.out.println("  Nome semplice: " + clazz.getSimpleName());
        System.out.println("  Nome completo: " + clazz.getName());
        System.out.println("  Nome canonico: " + clazz.getCanonicalName());
        System.out.println("  Package: " + clazz.getPackage().getName());
        System.out.println("  Modifiers: " + Modifier.toString(clazz.getModifiers()));

        // Tipi
        System.out.println("\nTipi:");
        System.out.println("  È interfaccia: " + clazz.isInterface());
        System.out.println("  È classe astratta: " + Modifier.isAbstract(clazz.getModifiers()));
        System.out.println("  È enum: " + clazz.isEnum());
        System.out.println("  È annotation: " + clazz.isAnnotation());
        System.out.println("  È array: " + clazz.isArray());
        System.out.println("  È primitive: " + clazz.isPrimitive());

        // Gerarchia
        System.out.println("\nGerarchia:");
        System.out.println("  Superclasse: " + clazz.getSuperclass().getSimpleName());
        
        System.out.println("  Interfacce implementate:");
        for (Class<?> iface : clazz.getInterfaces()) {
            System.out.println("    - " + iface.getSimpleName());
        }

        // Classi interne
        Class<?>[] innerClasses = clazz.getDeclaredClasses();
        if (innerClasses.length > 0) {
            System.out.println("\n  Classi interne: " + innerClasses.length);
        }

        // Ispezione custom class
        System.out.println("\n=== Ispezione SampleClass ===");
        Class<?> sampleClass = SampleClass.class;
        
        System.out.println("Campi: " + sampleClass.getDeclaredFields().length);
        System.out.println("Metodi: " + sampleClass.getDeclaredMethods().length);
        System.out.println("Costruttori: " + sampleClass.getDeclaredConstructors().length);
        System.out.println("Annotazioni: " + sampleClass.getDeclaredAnnotations().length);

        System.out.println();
    }

    // ==================== ISPEZIONE CAMPI ====================

    public void ispezioneCampi() {
        System.out.println("=== ISPEZIONE CAMPI ===");

        Class<?> clazz = SampleClass.class;

        // getDeclaredFields() - tutti i campi (public, private, protected)
        System.out.println("Tutti i campi (getDeclaredFields):");
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println("  " + Modifier.toString(field.getModifiers()) + 
                " " + field.getType().getSimpleName() + 
                " " + field.getName());
        }

        // getFields() - solo campi public (inclusi ereditati)
        System.out.println("\nCampi public (getFields):");
        Field[] publicFields = clazz.getFields();
        for (Field field : publicFields) {
            System.out.println("  " + field.getName());
        }

        // Ottenere campo specifico
        try {
            Field privateField = clazz.getDeclaredField("privateValue");
            System.out.println("\nCampo specifico:");
            System.out.println("  Nome: " + privateField.getName());
            System.out.println("  Tipo: " + privateField.getType());
            System.out.println("  Modifiers: " + Modifier.toString(privateField.getModifiers()));
            System.out.println("  È statico: " + Modifier.isStatic(privateField.getModifiers()));
            System.out.println("  È final: " + Modifier.isFinal(privateField.getModifiers()));
            System.out.println("  È transient: " + Modifier.isTransient(privateField.getModifiers()));
            System.out.println("  È volatile: " + Modifier.isVolatile(privateField.getModifiers()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Annotazioni sui campi
        System.out.println("\nCampi con annotazioni:");
        for (Field field : allFields) {
            if (field.isAnnotationPresent(Important.class)) {
                Important ann = field.getAnnotation(Important.class);
                System.out.println("  " + field.getName() + " - " + ann.value());
            }
        }

        System.out.println();
    }

    // ==================== ISPEZIONE METODI ====================

    public void ispezioneMetodi() {
        System.out.println("=== ISPEZIONE METODI ===");

        Class<?> clazz = SampleClass.class;

        // getDeclaredMethods() - tutti i metodi
        System.out.println("Tutti i metodi (getDeclaredMethods):");
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.print("  " + Modifier.toString(method.getModifiers()) + 
                " " + method.getReturnType().getSimpleName() + 
                " " + method.getName() + "(");
            
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        // getMethods() - solo metodi public (inclusi ereditati)
        System.out.println("\nMetodi public (getMethods) - primi 5:");
        Method[] publicMethods = clazz.getMethods();
        for (int i = 0; i < Math.min(5, publicMethods.length); i++) {
            System.out.println("  " + publicMethods[i].getName());
        }

        // Ottenere metodo specifico
        try {
            Method method = clazz.getDeclaredMethod("privateMethod", String.class);
            System.out.println("\nMetodo specifico:");
            System.out.println("  Nome: " + method.getName());
            System.out.println("  Return type: " + method.getReturnType());
            System.out.println("  Parameter count: " + method.getParameterCount());
            
            // Parametri
            Parameter[] parameters = method.getParameters();
            System.out.println("  Parametri:");
            for (Parameter param : parameters) {
                System.out.println("    - " + param.getType().getSimpleName() + 
                    " " + param.getName());
            }

            // Eccezioni
            Class<?>[] exceptions = method.getExceptionTypes();
            if (exceptions.length > 0) {
                System.out.println("  Exceptions:");
                for (Class<?> exc : exceptions) {
                    System.out.println("    - " + exc.getSimpleName());
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // Metodi con annotazioni
        System.out.println("\nMetodi con @Important:");
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Important.class)) {
                System.out.println("  " + method.getName());
            }
        }

        System.out.println();
    }

    // ==================== ISPEZIONE COSTRUTTORI ====================

    public void ispezioneCostruttori() {
        System.out.println("=== ISPEZIONE COSTRUTTORI ===");

        Class<?> clazz = SampleClass.class;

        // Tutti i costruttori
        System.out.println("Costruttori:");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.print("  " + Modifier.toString(constructor.getModifiers()) + 
                " " + constructor.getName() + "(");
            
            Class<?>[] params = constructor.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        // Costruttore specifico
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
            System.out.println("\nCostruttore specifico:");
            System.out.println("  Parametri: " + constructor.getParameterCount());
            
            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter param = parameters[i];
                System.out.println("  Param " + i + ": " + 
                    param.getType().getSimpleName() + " " + param.getName());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== ISPEZIONE ANNOTAZIONI ====================

    public void ispezioneAnnotazioni() {
        System.out.println("=== ISPEZIONE ANNOTAZIONI ===");

        Class<?> clazz = SampleClass.class;

        // Annotazioni sulla classe
        System.out.println("Annotazioni sulla classe:");
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation ann : annotations) {
            System.out.println("  @" + ann.annotationType().getSimpleName());
            if (ann instanceof MyAnnotation) {
                MyAnnotation myAnn = (MyAnnotation) ann;
                System.out.println("    value: " + myAnn.value());
                System.out.println("    priority: " + myAnn.priority());
            }
        }

        // Verificare se ha annotazione specifica
        if (clazz.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ann = clazz.getAnnotation(MyAnnotation.class);
            System.out.println("\nMyAnnotation presente:");
            System.out.println("  value: " + ann.value());
            System.out.println("  priority: " + ann.priority());
        }

        // Annotazioni su metodi
        System.out.println("\nMetodi annotati con @Important:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Important.class)) {
                Important ann = method.getAnnotation(Important.class);
                System.out.println("  " + method.getName() + ": " + ann.value());
            }
        }

        // Annotazioni su campi
        System.out.println("\nCampi annotati:");
        for (Field field : clazz.getDeclaredFields()) {
            Annotation[] fieldAnns = field.getDeclaredAnnotations();
            if (fieldAnns.length > 0) {
                System.out.print("  " + field.getName() + ": ");
                for (Annotation ann : fieldAnns) {
                    System.out.print("@" + ann.annotationType().getSimpleName() + " ");
                }
                System.out.println();
            }
        }

        // Annotazioni su parametri
        try {
            Method method = clazz.getDeclaredMethod("methodWithAnnotatedParams", 
                String.class, int.class);
            System.out.println("\nAnnotazioni su parametri:");
            
            Parameter[] params = method.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.print("  Param " + i + ": ");
                Annotation[] paramAnns = params[i].getDeclaredAnnotations();
                for (Annotation ann : paramAnns) {
                    System.out.print("@" + ann.annotationType().getSimpleName() + " ");
                }
                System.out.println();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== CREAZIONE ISTANZE DINAMICHE ====================

    public void creazioneIstanzeDinamiche() {
        System.out.println("=== CREAZIONE ISTANZE DINAMICHE ===");

        try {
            // Modo 1: newInstance() deprecato in Java 9+
            // Class<?> clazz = SampleClass.class;
            // Object obj = clazz.newInstance(); // DEPRECATO

            // Modo 2: Constructor.newInstance() (preferito)
            Class<?> clazz = SampleClass.class;
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object obj1 = constructor.newInstance();
            System.out.println("1. Costruttore no-arg: " + obj1);

            // Con parametri
            Constructor<?> constructor2 = clazz.getDeclaredConstructor(String.class, int.class);
            Object obj2 = constructor2.newInstance("Dynamic", 100);
            System.out.println("2. Costruttore con parametri: " + obj2);

            // Costruttore privato
            Constructor<?> privateConstructor = clazz.getDeclaredConstructor(String.class);
            privateConstructor.setAccessible(true); // Permette accesso a costruttore privato
            Object obj3 = privateConstructor.newInstance("Private");
            System.out.println("3. Costruttore privato: " + obj3);

            // Creazione dinamica con Class.forName()
            String className = "java.util.ArrayList";
            Class<?> dynamicClass = Class.forName(className);
            Constructor<?> listConstructor = dynamicClass.getDeclaredConstructor();
            Object listObj = listConstructor.newInstance();
            System.out.println("4. Classe caricata dinamicamente: " + 
                listObj.getClass().getSimpleName());

            // Con generics
            @SuppressWarnings("unchecked")
            List<String> list = (List<String>) listObj;
            list.add("Item 1");
            System.out.println("   Aggiunti elementi: " + list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== INVOCAZIONE METODI DINAMICI ====================

    public void invocazioneMetodiDinamici() {
        System.out.println("=== INVOCAZIONE METODI DINAMICI ===");

        try {
            SampleClass obj = new SampleClass("Test", 42);

            // Invocare metodo public
            Method publicMethod = obj.getClass().getDeclaredMethod("publicMethod", String.class);
            String result1 = (String) publicMethod.invoke(obj, "Hello");
            System.out.println("1. Metodo public: " + result1);

            // Invocare metodo privato
            Method privateMethod = obj.getClass().getDeclaredMethod("privateMethod", String.class);
            privateMethod.setAccessible(true); // Permette accesso
            String result2 = (String) privateMethod.invoke(obj, "World");
            System.out.println("2. Metodo privato: " + result2);

            // Invocare metodo statico
            Method staticMethod = obj.getClass().getDeclaredMethod("staticMethod", int.class);
            int result3 = (int) staticMethod.invoke(null, 10); // null per metodi statici
            System.out.println("3. Metodo statico: " + result3);

            // Invocare metodo senza parametri
            Method noArgsMethod = obj.getClass().getDeclaredMethod("getName");
            String result4 = (String) noArgsMethod.invoke(obj);
            System.out.println("4. Metodo senza parametri: " + result4);

            // Invocare metodo con più parametri
            Method multiParamMethod = obj.getClass().getDeclaredMethod(
                "methodWithMultipleParams", String.class, int.class, boolean.class);
            String result5 = (String) multiParamMethod.invoke(obj, "Test", 5, true);
            System.out.println("5. Metodo multi-param: " + result5);

            // Gestione eccezioni
            try {
                Method throwingMethod = obj.getClass().getDeclaredMethod("throwingMethod");
                throwingMethod.invoke(obj);
            } catch (InvocationTargetException e) {
                System.out.println("6. Eccezione catturata: " + 
                    e.getCause().getClass().getSimpleName());
            }

            // Invocare metodo su oggetto sconosciuto
            Object unknownObj = new SampleClass("Unknown", 99);
            Method method = unknownObj.getClass().getMethod("toString");
            String result7 = (String) method.invoke(unknownObj);
            System.out.println("7. Oggetto sconosciuto: " + result7);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== ACCESSO CAMPI PRIVATI ====================

    public void accessoCampiPrivati() {
        System.out.println("=== ACCESSO CAMPI PRIVATI ===");

        try {
            SampleClass obj = new SampleClass("Original", 100);
            System.out.println("Oggetto originale: " + obj);

            // Leggere campo privato
            Field privateField = obj.getClass().getDeclaredField("privateValue");
            privateField.setAccessible(true); // Permette accesso
            int value = (int) privateField.get(obj);
            System.out.println("1. Valore privato letto: " + value);

            // Leggere campo public
            Field publicField = obj.getClass().getDeclaredField("publicValue");
            int publicValue = (int) publicField.get(obj);
            System.out.println("2. Valore public letto: " + publicValue);

            // Leggere campo statico
            Field staticField = obj.getClass().getDeclaredField("staticCounter");
            staticField.setAccessible(true);
            int staticValue = (int) staticField.get(null); // null per campi statici
            System.out.println("3. Valore statico letto: " + staticValue);

            // Leggere campo final (solo lettura, modifica problematica)
            Field finalField = obj.getClass().getDeclaredField("CONSTANT");
            finalField.setAccessible(true);
            String constantValue = (String) finalField.get(obj);
            System.out.println("4. Valore final letto: " + constantValue);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== MODIFICA CAMPI PRIVATI ====================

    public void modificaCampiPrivati() {
        System.out.println("=== MODIFICA CAMPI PRIVATI ===");

        try {
            SampleClass obj = new SampleClass("Original", 100);
            System.out.println("Prima: " + obj);

            // Modificare campo privato
            Field privateField = obj.getClass().getDeclaredField("privateValue");
            privateField.setAccessible(true);
            privateField.set(obj, 999);
            System.out.println("Dopo modifica privateValue: " + obj);

            // Modificare campo public
            Field publicField = obj.getClass().getDeclaredField("publicValue");
            publicField.set(obj, 777);
            System.out.println("Dopo modifica publicValue: " + obj);

            // Modificare campo statico
            Field staticField = obj.getClass().getDeclaredField("staticCounter");
            staticField.setAccessible(true);
            staticField.set(null, 5000);
            System.out.println("Dopo modifica staticCounter: " + 
                staticField.get(null));

            // Tentativo di modificare campo final (problematico)
            System.out.println("\nTentativo modifica campo final:");
            try {
                Field finalField = obj.getClass().getDeclaredField("CONSTANT");
                finalField.setAccessible(true);
                
                // Rimuovere il modificatore final
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(finalField, 
                    finalField.getModifiers() & ~Modifier.FINAL);
                
                finalField.set(obj, "Modified");
                System.out.println("  Valore modificato: " + finalField.get(obj));
                System.out.println("  (Nota: comportamento non garantito per final)");
            } catch (Exception e) {
                System.out.println("  Errore: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== LAVORARE CON GENERICS ====================

    public void lavorareConGenerics() {
        System.out.println("=== LAVORARE CON GENERICS ===");

        try {
            // Type erasure - i generics sono cancellati a runtime
            List<String> stringList = new ArrayList<>();
            System.out.println("Type erasure:");
            System.out.println("  Runtime class: " + stringList.getClass());
            System.out.println("  (Type parameter <String> è perso)");

            // ParameterizedType per ottenere informazioni sui generics
            Field field = GenericClass.class.getDeclaredField("list");
            Type genericType = field.getGenericType();
            
            if (genericType instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) genericType;
                System.out.println("\nParameterizedType:");
                System.out.println("  Raw type: " + paramType.getRawType());
                System.out.println("  Actual type arguments:");
                for (Type typeArg : paramType.getActualTypeArguments()) {
                    System.out.println("    - " + typeArg);
                }
            }

            // Generic method return type
            Method method = GenericClass.class.getDeclaredMethod("getList");
            Type returnType = method.getGenericReturnType();
            System.out.println("\nGeneric return type: " + returnType);

            // Generic method parameters
            Method method2 = GenericClass.class.getDeclaredMethod("setList", List.class);
            Type[] paramTypes = method2.getGenericParameterTypes();
            System.out.println("Generic parameter type: " + paramTypes[0]);

            // Type variables
            TypeVariable<?>[] typeVars = GenericClass.class.getTypeParameters();
            System.out.println("\nType variables:");
            for (TypeVariable<?> typeVar : typeVars) {
                System.out.println("  " + typeVar.getName());
                Type[] bounds = typeVar.getBounds();
                System.out.println("  Bounds: " + Arrays.toString(bounds));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== ARRAY REFLECTION ====================

    public void arrayReflection() {
        System.out.println("=== ARRAY REFLECTION ===");

        // Creare array dinamicamente
        Object intArray = Array.newInstance(int.class, 5);
        System.out.println("Array creato: " + intArray.getClass().getComponentType() + 
            "[" + Array.getLength(intArray) + "]");

        // Popolare array
        for (int i = 0; i < 5; i++) {
            Array.set(intArray, i, i * 10);
        }

        // Leggere da array
        System.out.print("Contenuto: ");
        for (int i = 0; i < Array.getLength(intArray); i++) {
            System.out.print(Array.get(intArray, i) + " ");
        }
        System.out.println();

        // Array multidimensionale
        Object multiArray = Array.newInstance(String.class, 2, 3);
        System.out.println("\nArray 2D creato: " + 
            multiArray.getClass().getComponentType().getComponentType() + "[2][3]");

        // Popolare array 2D
        for (int i = 0; i < 2; i++) {
            Object row = Array.get(multiArray, i);
            for (int j = 0; j < 3; j++) {
                Array.set(row, j, "(" + i + "," + j + ")");
            }
        }

        // Stampare array 2D
        System.out.println("Contenuto:");
        for (int i = 0; i < 2; i++) {
            Object row = Array.get(multiArray, i);
            System.out.print("  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(Array.get(row, j) + " ");
            }
            System.out.println();
        }

        // Verificare se è array
        Class<?> clazz = intArray.getClass();
        System.out.println("\nInformazioni array:");
        System.out.println("  È array: " + clazz.isArray());
        System.out.println("  Component type: " + clazz.getComponentType());

        System.out.println();
    }

    // ==================== PROXY DINAMICI ====================

    public void proxyDinamici() {
        System.out.println("=== PROXY DINAMICI ===");

        // Creare proxy per interfaccia
        Calculator calc = (Calculator) Proxy.newProxyInstance(
            Calculator.class.getClassLoader(),
            new Class<?>[] { Calculator.class },
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) 
                        throws Throwable {
                    System.out.println("  [PROXY] Chiamato: " + method.getName() + 
                        Arrays.toString(args));
                    
                    // Implementazione logica
                    if (method.getName().equals("add")) {
                        return (int)args[0] + (int)args[1];
                    } else if (method.getName().equals("multiply")) {
                        return (int)args[0] * (int)args[1];
                    }
                    
                    return null;
                }
            }
        );

        System.out.println("Proxy creato per Calculator");
        int sum = calc.add(5, 3);
        System.out.println("  Risultato add: " + sum);
        
        int product = calc.multiply(5, 3);
        System.out.println("  Risultato multiply: " + product);

        // Verificare se è proxy
        System.out.println("\nInformazioni proxy:");
        System.out.println("  È proxy: " + Proxy.isProxyClass(calc.getClass()));
        System.out.println("  Handler: " + Proxy.getInvocationHandler(calc).getClass().getName());

        // Proxy con logging
        System.out.println("\nProxy con logging:");
        Calculator loggingCalc = createLoggingProxy(new CalculatorImpl());
        loggingCalc.add(10, 20);
        loggingCalc.multiply(4, 7);

        System.out.println();
    }

    private Calculator createLoggingProxy(Calculator target) {
        return (Calculator) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            (proxy, method, args) -> {
                System.out.println("  [LOG] Before: " + method.getName());
                Object result = method.invoke(target, args);
                System.out.println("  [LOG] After: " + method.getName() + " = " + result);
                return result;
            }
        );
    }

    // ==================== ENUM REFLECTION ====================

    public void enumReflection() {
        System.out.println("=== ENUM REFLECTION ===");

        Class<?> enumClass = Priority.class;

        // Verificare se è enum
        System.out.println("È enum: " + enumClass.isEnum());

        // Ottenere tutte le costanti
        Object[] constants = enumClass.getEnumConstants();
        System.out.println("\nCostanti enum:");
        for (Object constant : constants) {
            Priority priority = (Priority) constant;
            System.out.println("  " + priority.name() + " (ordinal: " + 
                priority.ordinal() + ", level: " + priority.getLevel() + ")");
        }

        // Ottenere enum da stringa
        try {
            Method valueOf = enumClass.getMethod("valueOf", String.class);
            Priority high = (Priority) valueOf.invoke(null, "HIGH");
            System.out.println("\nEnum da stringa: " + high);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Campi dell'enum
        System.out.println("\nCampi dell'enum:");
        for (Field field : enumClass.getDeclaredFields()) {
            if (!field.isEnumConstant()) {
                System.out.println("  " + field.getName() + ": " + field.getType());
            }
        }

        // Metodi dell'enum
        System.out.println("\nMetodi dell'enum (primi 5):");
        Method[] methods = enumClass.getDeclaredMethods();
        for (int i = 0; i < Math.min(5, methods.length); i++) {
            System.out.println("  " + methods[i].getName());
        }

        System.out.println();
    }

    // ==================== MODULE REFLECTION (Java 9+) ====================

    public void moduleReflection() {
        System.out.println("=== MODULE REFLECTION (Java 9+) ===");

        try {
            Class<?> clazz = String.class;
            
            // Ottenere il modulo
            Module module = clazz.getModule();
            System.out.println("Modulo di String:");
            System.out.println("  Nome: " + module.getName());
            System.out.println("  È named: " + module.isNamed());
            System.out.println("  Descriptor: " + 
                (module.getDescriptor() != null ? module.getDescriptor().name() : "null"));

            // Modulo della classe corrente
            Module thisModule = this.getClass().getModule();
            System.out.println("\nModulo corrente:");
            System.out.println("  Nome: " + thisModule.getName());
            System.out.println("  È named: " + thisModule.isNamed());

            // Package nel modulo
            if (module.isNamed() && module.getDescriptor() != null) {
                System.out.println("\nPackage esportati (primi 5):");
                module.getDescriptor().exports().stream()
                    .limit(5)
                    .forEach(exp -> System.out.println("  " + exp.source()));
            }

        } catch (Exception e) {
            System.out.println("Module reflection richiede Java 9+");
        }

        System.out.println();
    }

    // ==================== DEPENDENCY INJECTION ====================

    public void dependencyInjection() {
        System.out.println("=== DEPENDENCY INJECTION (Pattern) ===");

        // Simple DI container
        DIContainer container = new DIContainer();
        
        // Registra dipendenze
        container.register(Database.class, () -> new Database());
        container.register(UserService.class, () -> {
            UserService service = new UserService();
            container.inject(service);
            return service;
        });

        // Ottieni oggetto con dipendenze iniettate
        UserService userService = container.get(UserService.class);
        userService.save("John Doe");

        System.out.println();
    }

    // ==================== OBJECT MAPPER ====================

    public void objectMapper() {
        System.out.println("=== OBJECT MAPPER (Pattern) ===");

        // Simple object mapper usando reflection
        SimpleMapper mapper = new SimpleMapper();
        
        // Object to Map
        SampleClass obj = new SampleClass("Mapper", 42);
        Map<String, Object> map = mapper.toMap(obj);
        System.out.println("Object to Map: " + map);

        // Map to Object
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("name", "FromMap");
        sourceMap.put("value", 100);
        
        SampleClass newObj = mapper.fromMap(sourceMap, SampleClass.class);
        System.out.println("Map to Object: " + newObj);

        System.out.println();
    }

    // ==================== TESTING FRAMEWORK ====================

    public void testingFramework() {
        System.out.println("=== TESTING FRAMEWORK (Pattern) ===");

        // Simple test runner usando reflection
        SimpleTestRunner runner = new SimpleTestRunner();
        runner.runTests(SimpleTest.class);

        System.out.println();
    }

    // ==================== PERFORMANCE CONSIDERATIONS ====================

    public void performanceConsiderations() {
        System.out.println("=== PERFORMANCE CONSIDERATIONS ===");

        SampleClass obj = new SampleClass("Perf", 42);
        int iterations = 1_000_000;

        // Direct method call
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            obj.getName();
        }
        long directTime = System.nanoTime() - start;

        // Reflection method call
        try {
            Method method = obj.getClass().getDeclaredMethod("getName");
            start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                method.invoke(obj);
            }
            long reflectionTime = System.nanoTime() - start;

            System.out.println("Performance test (" + iterations + " iterazioni):");
            System.out.println("  Direct call: " + (directTime / 1_000_000) + " ms");
            System.out.println("  Reflection call: " + (reflectionTime / 1_000_000) + " ms");
            System.out.println("  Slowdown: " + (reflectionTime / directTime) + "x");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Caching reflection objects
        System.out.println("\nCon caching:");
        try {
            Method method = obj.getClass().getDeclaredMethod("getName");
            method.setAccessible(true); // Cache accessibility
            
            start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                method.invoke(obj);
            }
            long cachedTime = System.nanoTime() - start;
            
            System.out.println("  Cached reflection: " + (cachedTime / 1_000_000) + " ms");
            System.out.println("  (setAccessible cacheato migliora performance)");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");

        System.out.println("1. USA REFLECTION SOLO QUANDO NECESSARIO:");
        System.out.println("   ✓ Framework, testing, serializzazione");
        System.out.println("   ✗ Codice business normale");

        System.out.println("\n2. CACHE GLI OGGETTI REFLECTION:");
        System.out.println("   ✓ Method, Field, Constructor oggetti");
        System.out.println("   ✗ Chiamare getDeclaredMethod() ogni volta");

        System.out.println("\n3. GESTISCI LE ECCEZIONI:");
        System.out.println("   ✓ try-catch per NoSuchMethodException, etc.");
        System.out.println("   ✓ InvocationTargetException.getCause()");

        System.out.println("\n4. USA setAccessible() CON CAUTELA:");
        System.out.println("   ✓ Solo quando necessario (testing)");
        System.out.println("   ✗ Violare incapsulamento in produzione");

        System.out.println("\n5. CONSIDERA MethodHandles (Java 7+):");
        System.out.println("   ✓ Più performanti di reflection");
        System.out.println("   ✓ Type-safe");

        System.out.println("\n6. DOCUMENTA L'USO DI REFLECTION:");
        System.out.println("   ✓ Spiega perché è necessario");
        System.out.println("   ✓ Avvisa di dipendenze da struttura interna");

        System.out.println("\n7. TESTA EDGE CASES:");
        System.out.println("   ✓ Campi/metodi privati, final, static");
        System.out.println("   ✓ Classi interne, anonime");

        System.out.println("\n8. SECURITY MANAGER:");
        System.out.println("   ✓ Reflection può essere bloccata");
        System.out.println("   ✓ Verifica permessi appropriati");

        System.out.println("\n9. PREFERISCI ALTERNATIVE:");
        System.out.println("   ✓ Interfaces e polimorfismo");
        System.out.println("   ✓ Annotation processing (compile-time)");
        System.out.println("   ✓ Code generation");

        System.out.println("\n10. CASI D'USO APPROPRIATI:");
        System.out.println("    ✓ Dependency Injection frameworks");
        System.out.println("    ✓ ORM (Hibernate, JPA)");
        System.out.println("    ✓ Testing frameworks (JUnit, Mockito)");
        System.out.println("    ✓ Serialization/Deserialization");
        System.out.println("    ✓ Dynamic proxies");

        System.out.println();
    }

    // ==================== CLASSI DI SUPPORTO ====================

    @MyAnnotation(value = "Sample class for reflection demo", priority = 1)
    static class SampleClass {
        @Important("Constant value")
        private static final String CONSTANT = "CONST";
        
        private static int staticCounter = 0;
        
        @Important("Private field")
        private int privateValue;
        
        public int publicValue;
        
        private String name;
        private int value;

        // Costruttore pubblico
        public SampleClass() {
            this("Default", 0);
        }

        // Costruttore con parametri
        public SampleClass(String name, int value) {
            this.name = name;
            this.value = value;
            this.privateValue = value * 2;
            this.publicValue = value * 3;
            staticCounter++;
        }

        // Costruttore privato
        private SampleClass(String name) {
            this(name, 0);
        }

        // Metodo pubblico
        public String publicMethod(String input) {
            return "Public: " + input;
        }

        // Metodo privato
        @Important("Important private method")
        private String privateMethod(String input) {
            return "Private: " + input;
        }

        // Metodo statico
        public static int staticMethod(int x) {
            return x * 2;
        }

        // Metodo con multiple parametri
        public String methodWithMultipleParams(String s, int i, boolean b) {
            return String.format("%s-%d-%b", s, i, b);
        }

        // Metodo con parametri annotati
        public void methodWithAnnotatedParams(
                @Important("First param") String param1,
                @Important("Second param") int param2) {
        }

        // Metodo che lancia eccezione
        public void throwingMethod() throws Exception {
            throw new Exception("Test exception");
        }

        // Getters
        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("SampleClass{name='%s', value=%d, privateValue=%d, publicValue=%d}",
                name, value, privateValue, publicValue);
        }
    }

    // Generic class
    static class GenericClass<T> {
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    // Enum
    enum Priority {
        LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);

        private final int level;

        Priority(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    // Interfacce e implementazioni
    interface Calculator {
        int add(int a, int b);
        int multiply(int a, int b);
    }

    static class CalculatorImpl implements Calculator {
        @Override
        public int add(int a, int b) {
            return a + b;
        }

        @Override
        public int multiply(int a, int b) {
            return a * b;
        }
    }

    // Dependency Injection
    static class Database {
        public void save(String data) {
            System.out.println("  [DB] Salvato: " + data);
        }
    }

    static class UserService {
        @Inject
        private Database database;

        public void save(String user) {
            System.out.println("  [Service] Salvando utente: " + user);
            database.save(user);
        }
    }

    // Simple DI Container
    static class DIContainer {
        private Map<Class<?>, Object> instances = new HashMap<>();
        private Map<Class<?>, java.util.function.Supplier<?>> factories = new HashMap<>();

        public <T> void register(Class<T> type, java.util.function.Supplier<T> factory) {
            factories.put(type, factory);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> type) {
            if (!instances.containsKey(type)) {
                java.util.function.Supplier<?> factory = factories.get(type);
                if (factory != null) {
                    instances.put(type, factory.get());
                }
            }
            return (T) instances.get(type);
        }

        public void inject(Object target) {
            for (Field field : target.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    try {
                        Object dependency = get(field.getType());
                        field.set(target, dependency);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Simple Object Mapper
    static class SimpleMapper {
        public Map<String, Object> toMap(Object obj) {
            Map<String, Object> map = new HashMap<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (!Modifier.isStatic(field.getModifiers()) && 
                        !Modifier.isFinal(field.getModifiers())) {
                        map.put(field.getName(), field.get(obj));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return map;
        }

        public <T> T fromMap(Map<String, Object> map, Class<T> type) {
            try {
                T obj = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (map.containsKey(field.getName())) {
                        field.set(obj, map.get(field.getName()));
                    }
                }
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    // Simple Test Framework
    static class SimpleTestRunner {
        public void runTests(Class<?> testClass) {
            System.out.println("Running tests for: " + testClass.getSimpleName());
            
            int passed = 0;
            int failed = 0;

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {
                        Object instance = testClass.getDeclaredConstructor().newInstance();
                        method.invoke(instance);
                        System.out.println("  ✓ " + method.getName() + " PASSED");
                        passed++;
                    } catch (Exception e) {
                        System.out.println("  ✗ " + method.getName() + " FAILED: " + 
                            e.getCause().getMessage());
                        failed++;
                    }
                }
            }

            System.out.println("\nResults: " + passed + " passed, " + failed + " failed");
        }
    }

    static class SimpleTest {
        @Test
        public void testAddition() {
            int result = 2 + 2;
            if (result != 4) {
                throw new AssertionError("Expected 4, got " + result);
            }
        }

        @Test
        public void testSubtraction() {
            int result = 5 - 3;
            if (result != 2) {
                throw new AssertionError("Expected 2, got " + result);
            }
        }

        @Test
        public void testFailure() {
            throw new AssertionError("This test is designed to fail");
        }
    }

    // ==================== ANNOTAZIONI ====================

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface MyAnnotation {
        String value();
        int priority() default 0;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    @interface Important {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @interface Inject {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Test {
    }
}
