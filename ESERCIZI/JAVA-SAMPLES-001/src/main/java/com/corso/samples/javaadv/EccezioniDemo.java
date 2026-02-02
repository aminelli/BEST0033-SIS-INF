package com.corso.samples.javaadv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Esempio completo e avanzato sulle Eccezioni in Java
 * Include:
 * - Gerarchia delle eccezioni (Checked vs Unchecked)
 * - Try-catch-finally
 * - Try-with-resources
 * - Multi-catch
 * - Clausola throws
 * - Creazione di eccezioni custom
 * - Chained exceptions (causa)
 * - Suppressed exceptions
 * - Best practices e pattern avanzati
 * - Rethrowing e wrapping
 * - Exception handling in lambda e stream
 */
public class EccezioniDemo {

    public static void sample() {
        EccezioniDemo demo = new EccezioniDemo();
        
        System.out.println("=== ECCEZIONI IN JAVA - GUIDA COMPLETA ===\n");
        
        // Basi
        demo.gerarchiaEccezioni();
        demo.tryCatchFinally();
        demo.multiCatch();
        demo.tryWithResources();
        
        // Throws e propagazione
        demo.throwsClausola();
        demo.propagazioneEccezioni();
        demo.rethrowingEccezioni();
        
        // Eccezioni custom
        demo.eccezioniCustom();
        demo.chainedExceptions();
        demo.suppressedExceptions();
        
        // Pattern avanzati
        demo.exceptionHandlingPatterns();
        demo.exceptionInLambda();
        demo.exceptionInStream();
        demo.finallyVsReturn();
        demo.exceptionBestPractices();
        
        // Casi reali
        demo.validazioneInput();
        demo.gestioneRisorse();
        demo.transazioni();
        demo.retryPattern();
    }

    // ==================== GERARCHIA ECCEZIONI ====================

    public void gerarchiaEccezioni() {
        System.out.println("=== GERARCHIA ECCEZIONI ===\n");

        System.out.println("Throwable");
        System.out.println("├── Error (non catturabili, errori JVM)");
        System.out.println("│   ├── OutOfMemoryError");
        System.out.println("│   ├── StackOverflowError");
        System.out.println("│   └── VirtualMachineError");
        System.out.println("└── Exception");
        System.out.println("    ├── RuntimeException (Unchecked)");
        System.out.println("    │   ├── NullPointerException");
        System.out.println("    │   ├── IllegalArgumentException");
        System.out.println("    │   ├── IndexOutOfBoundsException");
        System.out.println("    │   ├── ArithmeticException");
        System.out.println("    │   └── ClassCastException");
        System.out.println("    └── Checked Exceptions");
        System.out.println("        ├── IOException");
        System.out.println("        ├── SQLException");
        System.out.println("        ├── ParseException");
        System.out.println("        └── ClassNotFoundException\n");

        // Unchecked Exception - non richiede try-catch
        try {
            int[] arr = {1, 2, 3};
            System.out.println("Unchecked: " + arr[10]); // IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Catturata: " + e.getClass().getSimpleName());
        }

        // Checked Exception - richiede try-catch o throws
        try {
            Thread.sleep(1); // InterruptedException è checked
            System.out.println("Checked: sleep completato");
        } catch (InterruptedException e) {
            System.out.println("  Catturata: " + e.getClass().getSimpleName());
        }

        // RuntimeException comuni
        try {
            String s = null;
            s.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("  NullPointerException catturata");
        }

        try {
            int result = 10 / 0; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("  ArithmeticException: " + e.getMessage());
        }

        System.out.println();
    }

    // ==================== TRY-CATCH-FINALLY ====================

    public void tryCatchFinally() {
        System.out.println("=== TRY-CATCH-FINALLY ===");

        // Try-catch base
        try {
            int result = divide(10, 2);
            System.out.println("Divisione: 10 / 2 = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Errore: " + e.getMessage());
        }

        // Try-catch con divisione per zero
        try {
            int result = divide(10, 0);
            System.out.println("Questo non verrà stampato");
        } catch (ArithmeticException e) {
            System.out.println("Errore catturato: " + e.getMessage());
        }

        // Try-catch-finally
        System.out.println("\nTry-catch-finally:");
        try {
            System.out.println("  Blocco try");
            throw new Exception("Test exception");
        } catch (Exception e) {
            System.out.println("  Blocco catch: " + e.getMessage());
        } finally {
            System.out.println("  Blocco finally (sempre eseguito)");
        }

        // Multiple catch blocks
        System.out.println("\nMultiple catch:");
        try {
            String s = null;
            if (Math.random() > 0.5) {
                s.length(); // NullPointerException
            } else {
                int[] arr = {};
                System.out.println(arr[0]); // ArrayIndexOutOfBoundsException
            }
        } catch (NullPointerException e) {
            System.out.println("  NPE catturata");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  AIOOBE catturata");
        } catch (Exception e) {
            System.out.println("  Exception generica catturata");
        }

        // Catch con informazioni dettagliate
        System.out.println("\nInformazioni eccezione:");
        try {
            throwException();
        } catch (Exception e) {
            System.out.println("  Messaggio: " + e.getMessage());
            System.out.println("  Classe: " + e.getClass().getName());
            System.out.println("  Stack trace (primo elemento): " + e.getStackTrace()[0]);
        }

        System.out.println();
    }

    // ==================== MULTI-CATCH ====================

    public void multiCatch() {
        System.out.println("=== MULTI-CATCH (Java 7+) ===");

        // Multi-catch - cattura multiple eccezioni in un blocco
        try {
            String input = "abc";
            int number = Integer.parseInt(input); // NumberFormatException
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Errore di formato o null: " + e.getClass().getSimpleName());
        }

        // Multi-catch con IOException e SQLException
        try {
            if (Math.random() > 0.5) {
                throw new IOException("IO Error");
            } else {
                throw new SQLException("SQL Error");
            }
        } catch (IOException | SQLException e) {
            System.out.println("Errore I/O o SQL: " + e.getMessage());
            // Nota: 'e' è final implicitamente nel multi-catch
        }

        // Confronto: prima del multi-catch
        System.out.println("\nPrima di Java 7 (codice duplicato):");
        try {
            riskyOperation();
        } catch (IOException e) {
            handleException(e);
        } catch (SQLException e) {
            handleException(e);
        }

        // Dopo multi-catch (più conciso)
        System.out.println("Con multi-catch (Java 7+):");
        try {
            riskyOperation();
        } catch (IOException | SQLException e) {
            handleException(e);
        }

        System.out.println();
    }

    // ==================== TRY-WITH-RESOURCES ====================

    public void tryWithResources() {
        System.out.println("=== TRY-WITH-RESOURCES (Java 7+) ===");

        // Try-with-resources - chiude automaticamente le risorse
        System.out.println("Vecchio modo (try-finally):");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader("Line 1\nLine 2\nLine 3"));
            String line = reader.readLine();
            System.out.println("  Prima riga: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Nuovo modo (try-with-resources)
        System.out.println("\nNuovo modo (try-with-resources):");
        try (BufferedReader br = new BufferedReader(new StringReader("Line 1\nLine 2\nLine 3"))) {
            String line = br.readLine();
            System.out.println("  Prima riga: " + line);
            // br viene chiuso automaticamente
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Multiple risorse
        System.out.println("\nMultiple risorse:");
        String content = "Test content";
        try (BufferedReader br = new BufferedReader(new StringReader(content));
             StringWriter sw = new StringWriter();
             BufferedWriter bw = new BufferedWriter(sw)) {
            
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line.toUpperCase());
            }
            bw.flush();
            System.out.println("  Risultato: " + sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try-with-resources con catch e finally
        System.out.println("\nCon catch e finally:");
        try (CustomResource resource = new CustomResource("Resource1")) {
            System.out.println("  Usando la risorsa...");
            if (Math.random() > 1.5) {
                throw new Exception("Test");
            }
        } catch (Exception e) {
            System.out.println("  Catch eseguito");
        } finally {
            System.out.println("  Finally eseguito");
        }

        // Java 9+: variabili final o effectively final
        System.out.println("\nJava 9+ try-with-resources:");
        CustomResource res = new CustomResource("Resource2");
        try (res) {
            System.out.println("  Usando res...");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== THROWS CLAUSOLA ====================

    public void throwsClausola() {
        System.out.println("=== CLAUSOLA THROWS ===");

        // Metodo che dichiara throws
        try {
            String content = readFile("test.txt");
            System.out.println("File letto: " + content);
        } catch (IOException e) {
            System.out.println("Errore lettura file: " + e.getMessage());
        }

        // Multiple throws
        try {
            processData("data.txt");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Parse Error: " + e.getMessage());
        }

        // Throws vs try-catch
        System.out.println("\nThrows vs Try-Catch:");
        System.out.println("  throws: delega la gestione al chiamante");
        System.out.println("  try-catch: gestisce l'eccezione localmente");

        // Metodo che non dichiara throws (gestisce internamente)
        String result = readFileSafe("test.txt");
        System.out.println("Lettura sicura: " + result);

        System.out.println();
    }

    // Metodo con throws
    private String readFile(String filename) throws IOException {
        // Simula lettura file
        if (!filename.endsWith(".txt")) {
            throw new IOException("File non valido: deve essere .txt");
        }
        return "Contenuto del file";
    }

    // Metodo con multiple throws
    private void processData(String filename) throws IOException, ParseException {
        if (filename.isEmpty()) {
            throw new IOException("Filename vuoto");
        }
        if (!filename.contains(".")) {
            throw new ParseException("Formato filename non valido", 0);
        }
        System.out.println("  Dati processati con successo");
    }

    // Metodo che gestisce internamente (no throws)
    private String readFileSafe(String filename) {
        try {
            return readFile(filename);
        } catch (IOException e) {
            return "Errore: " + e.getMessage();
        }
    }

    // ==================== PROPAGAZIONE ECCEZIONI ====================

    public void propagazioneEccezioni() {
        System.out.println("=== PROPAGAZIONE ECCEZIONI ===");

        // Propagazione attraverso call stack
        try {
            metodo1();
        } catch (CustomCheckedException e) {
            System.out.println("Eccezione catturata in main: " + e.getMessage());
            System.out.println("Propagata attraverso: metodo1 -> metodo2 -> metodo3");
        }

        // Propagazione con wrapping
        try {
            metodoConWrapping();
        } catch (RuntimeException e) {
            System.out.println("\nEccezione wrapped: " + e.getMessage());
            System.out.println("Causa originale: " + e.getCause().getMessage());
        }

        System.out.println();
    }

    private void metodo1() throws CustomCheckedException {
        System.out.println("  Metodo1 chiama metodo2");
        metodo2();
    }

    private void metodo2() throws CustomCheckedException {
        System.out.println("  Metodo2 chiama metodo3");
        metodo3();
    }

    private void metodo3() throws CustomCheckedException {
        System.out.println("  Metodo3 lancia eccezione");
        throw new CustomCheckedException("Errore in metodo3");
    }

    private void metodoConWrapping() {
        try {
            throw new IOException("Errore I/O originale");
        } catch (IOException e) {
            // Wrapping: incapsula eccezione checked in unchecked
            throw new RuntimeException("Errore durante operazione", e);
        }
    }

    // ==================== RETHROWING ECCEZIONI ====================

    public void rethrowingEccezioni() {
        System.out.println("=== RETHROWING ECCEZIONI ===");

        // Rethrowing semplice
        try {
            rethrowMethod1();
        } catch (Exception e) {
            System.out.println("Catturata dopo rethrow: " + e.getMessage());
        }

        // Rethrowing con logging
        try {
            rethrowWithLogging();
        } catch (IOException e) {
            System.out.println("Eccezione finale catturata");
        }

        // Rethrowing con wrapping
        try {
            rethrowWithWrapping();
        } catch (Exception e) {
            System.out.println("\nWrapped exception: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getCause().getMessage());
            }
        }

        // Java 7+ precise rethrow
        try {
            preciseRethrow("test");
        } catch (IOException e) {
            System.out.println("\nPrecise rethrow IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Precise rethrow SQLException: " + e.getMessage());
        }

        System.out.println();
    }

    private void rethrowMethod1() throws Exception {
        try {
            throw new Exception("Eccezione originale");
        } catch (Exception e) {
            System.out.println("  Catturata localmente, ora rilancio");
            throw e; // Rethrow
        }
    }

    private void rethrowWithLogging() throws IOException {
        try {
            throw new IOException("Errore I/O");
        } catch (IOException e) {
            System.out.println("  Log: Errore intercettato - " + e.getMessage());
            throw e; // Rethrow dopo logging
        }
    }

    private void rethrowWithWrapping() throws Exception {
        try {
            throw new SQLException("Errore database");
        } catch (SQLException e) {
            System.out.println("  Wrapping SQLException in Exception generica");
            throw new Exception("Errore nell'operazione database", e);
        }
    }

    // Java 7+ precise rethrow
    private void preciseRethrow(String type) throws IOException, SQLException {
        try {
            if ("io".equals(type)) {
                throw new IOException("Errore I/O");
            } else {
                throw new SQLException("Errore SQL");
            }
        } catch (Exception e) {
            System.out.println("  Catturata come Exception, ma tipo preservato nel rethrow");
            throw e; // Il compilatore sa che può essere solo IOException o SQLException
        }
    }

    // ==================== ECCEZIONI CUSTOM ====================

    public void eccezioniCustom() {
        System.out.println("=== ECCEZIONI CUSTOM ===");

        // Unchecked custom exception
        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Eccezione custom unchecked: " + e.getMessage());
            System.out.println("Età fornita: " + e.getProvidedAge());
        }

        // Checked custom exception
        try {
            processBankTransaction(-100);
        } catch (InsufficientFundsException e) {
            System.out.println("\nEccezione custom checked: " + e.getMessage());
            System.out.println("Richiesto: " + e.getRequiredAmount());
            System.out.println("Disponibile: " + e.getAvailableAmount());
            System.out.println("Deficit: " + e.getDeficit());
        }

        // Custom exception con context
        try {
            loginUser("user123", "wrongpass");
        } catch (AuthenticationException e) {
            System.out.println("\nAuthentication failed: " + e.getMessage());
            System.out.println("Username: " + e.getUsername());
            System.out.println("Timestamp: " + e.getTimestamp());
            System.out.println("Attempt: " + e.getAttemptNumber());
        }

        System.out.println();
    }

    private void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Età non valida", age);
        }
    }

    private void processBankTransaction(double amount) throws InsufficientFundsException {
        double balance = 50.0;
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Fondi insufficienti per la transazione",
                amount,
                balance
            );
        }
    }

    private void loginUser(String username, String password) throws AuthenticationException {
        if (!"correctpass".equals(password)) {
            throw new AuthenticationException(
                "Credenziali non valide",
                username,
                3
            );
        }
    }

    // ==================== CHAINED EXCEPTIONS ====================

    public void chainedExceptions() {
        System.out.println("=== CHAINED EXCEPTIONS ===");

        // Catena di eccezioni con causa
        try {
            highLevelOperation();
        } catch (Exception e) {
            System.out.println("Eccezione finale: " + e.getMessage());
            System.out.println("\nCatena delle cause:");
            
            Throwable cause = e.getCause();
            int level = 1;
            while (cause != null) {
                System.out.println("  Causa livello " + level + ": " + 
                    cause.getClass().getSimpleName() + " - " + cause.getMessage());
                cause = cause.getCause();
                level++;
            }
        }

        // initCause vs costruttore
        System.out.println("\nDue modi per impostare la causa:");
        
        // Modo 1: costruttore
        try {
            throw new Exception("Eccezione principale", 
                new IOException("Causa I/O"));
        } catch (Exception e) {
            System.out.println("Via costruttore: " + e.getMessage());
            System.out.println("  Causa: " + e.getCause().getMessage());
        }

        // Modo 2: initCause
        try {
            Exception e = new Exception("Eccezione principale");
            e.initCause(new SQLException("Causa SQL"));
            throw e;
        } catch (Exception e) {
            System.out.println("Via initCause: " + e.getMessage());
            System.out.println("  Causa: " + e.getCause().getMessage());
        }

        System.out.println();
    }

    private void highLevelOperation() throws Exception {
        try {
            middleLevelOperation();
        } catch (Exception e) {
            throw new Exception("Errore in high-level operation", e);
        }
    }

    private void middleLevelOperation() throws Exception {
        try {
            lowLevelOperation();
        } catch (IOException e) {
            throw new Exception("Errore in middle-level operation", e);
        }
    }

    private void lowLevelOperation() throws IOException {
        throw new IOException("Errore I/O nel low-level");
    }

    // ==================== SUPPRESSED EXCEPTIONS ====================

    public void suppressedExceptions() {
        System.out.println("=== SUPPRESSED EXCEPTIONS ===");

        // Senza try-with-resources (vecchio modo)
        System.out.println("Senza try-with-resources:");
        try {
            throwExceptionInTryAndFinally();
        } catch (Exception e) {
            System.out.println("  Eccezione catturata: " + e.getMessage());
            System.out.println("  L'eccezione del finally ha sovrascritto quella del try!");
        }

        // Con try-with-resources (gestisce suppressed)
        System.out.println("\nCon try-with-resources:");
        try (FailingResource resource = new FailingResource()) {
            System.out.println("  Usando risorsa...");
            throw new Exception("Eccezione nel try");
        } catch (Exception e) {
            System.out.println("  Eccezione principale: " + e.getMessage());
            
            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("  Eccezioni soppresse:");
                for (Throwable t : suppressed) {
                    System.out.println("    - " + t.getMessage());
                }
            }
        }

        // Aggiungere manualmente suppressed exceptions
        System.out.println("\nSuppressed exceptions manuali:");
        try {
            Exception main = new Exception("Eccezione principale");
            main.addSuppressed(new IOException("Suppressed 1"));
            main.addSuppressed(new SQLException("Suppressed 2"));
            throw main;
        } catch (Exception e) {
            System.out.println("  Principale: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("  Soppressa: " + t.getMessage());
            }
        }

        System.out.println();
    }

    private void throwExceptionInTryAndFinally() throws Exception {
        try {
            throw new Exception("Eccezione nel try");
        } finally {
            throw new Exception("Eccezione nel finally");
        }
    }

    // ==================== EXCEPTION HANDLING PATTERNS ====================

    public void exceptionHandlingPatterns() {
        System.out.println("=== EXCEPTION HANDLING PATTERNS ===");

        // 1. Fail Fast
        System.out.println("1. FAIL FAST:");
        try {
            processUserDataFailFast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("  Validazione fallita subito: " + e.getMessage());
        }

        // 2. Fail Safe
        System.out.println("\n2. FAIL SAFE:");
        String result = processUserDataFailSafe(null);
        System.out.println("  Risultato: " + result);

        // 3. Exception Translation
        System.out.println("\n3. EXCEPTION TRANSLATION:");
        try {
            businessOperation();
        } catch (BusinessException e) {
            System.out.println("  Business exception: " + e.getMessage());
            System.out.println("  Tipo originale nascosto: " + e.getCause().getClass().getSimpleName());
        }

        // 4. Swallowing Exceptions (ANTI-PATTERN)
        System.out.println("\n4. SWALLOWING (ANTI-PATTERN - da evitare):");
        swallowExceptionBad(); // Non fa nulla, errore silenzioso!
        System.out.println("  Eccezione ingoiata silenziosamente (male!)");

        // 5. Logging prima di propagare
        System.out.println("\n5. LOG AND RETHROW:");
        try {
            logAndRethrow();
        } catch (Exception e) {
            System.out.println("  Catturata dopo log");
        }

        System.out.println();
    }

    private void processUserDataFailFast(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data non può essere null");
        }
        // processa data...
    }

    private String processUserDataFailSafe(String data) {
        if (data == null) {
            return "default value";
        }
        return data;
    }

    private void businessOperation() throws BusinessException {
        try {
            // Operazione di basso livello
            throw new SQLException("Errore database");
        } catch (SQLException e) {
            // Traduzione in business exception
            throw new BusinessException("Operazione business fallita", e);
        }
    }

    private void swallowExceptionBad() {
        try {
            throw new Exception("Errore importante!");
        } catch (Exception e) {
            // MALE! Non fare nulla con l'eccezione
        }
    }

    private void logAndRethrow() throws Exception {
        try {
            throw new Exception("Errore critico");
        } catch (Exception e) {
            System.out.println("  [LOG] Errore intercettato: " + e.getMessage());
            throw e; // Rilancia dopo aver loggato
        }
    }

    // ==================== EXCEPTION IN LAMBDA ====================

    public void exceptionInLambda() {
        System.out.println("=== EXCEPTION IN LAMBDA ===");

        List<String> numeri = Arrays.asList("1", "2", "abc", "4", "5");

        // Problema: lambda non può lanciare checked exception
        System.out.println("Numeri validi:");
        numeri.stream()
            .map(s -> {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return null; // O valore di default
                }
            })
            .filter(Objects::nonNull)
            .forEach(n -> System.out.print("  " + n));
        System.out.println();

        // Wrapper per checked exceptions
        System.out.println("\nCon wrapper per checked exception:");
        List<String> files = Arrays.asList("file1.txt", "file2.txt");
        
        files.stream()
            .map(this::readFileWrapped)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(content -> System.out.println("  " + content));

        // Functional interface custom che permette exception
        System.out.println("\nCon functional interface custom:");
        List<String> inputs = Arrays.asList("1", "2", "abc");
        
        inputs.forEach(sneakyThrow(s -> {
            int num = Integer.parseInt(s); // Può lanciare exception
            System.out.println("  Parsed: " + num);
        }));

        System.out.println();
    }

    private Optional<String> readFileWrapped(String filename) {
        try {
            return Optional.of(readFile(filename));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    // Functional interface che permette exception
    @FunctionalInterface
    interface ThrowingConsumer<T> {
        void accept(T t) throws Exception;
    }

    private <T> java.util.function.Consumer<T> sneakyThrow(ThrowingConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                // Ignora per demo - in produzione gestire appropriatamente
            }
        };
    }

    // ==================== EXCEPTION IN STREAM ====================

    public void exceptionInStream() {
        System.out.println("=== EXCEPTION IN STREAM ===");

        List<Integer> numeri = Arrays.asList(10, 5, 0, 8, 2);

        // Stream con gestione exception inline
        System.out.println("Divisione 100 per ogni numero:");
        numeri.stream()
            .map(n -> {
                try {
                    return 100 / n;
                } catch (ArithmeticException e) {
                    return -1; // Valore sentinel per errore
                }
            })
            .forEach(result -> {
                if (result == -1) {
                    System.out.println("  Errore divisione!");
                } else {
                    System.out.println("  " + result);
                }
            });

        // Stream con Optional per gestire errori
        System.out.println("\nCon Optional:");
        numeri.stream()
            .map(n -> {
                try {
                    return Optional.of(100 / n);
                } catch (ArithmeticException e) {
                    return Optional.<Integer>empty();
                }
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(r -> System.out.println("  " + r));

        // Stream che colleziona sia risultati che errori
        System.out.println("\nRisultati ed errori separati:");
        Map<Boolean, List<String>> risultati = numeri.stream()
            .map(n -> {
                try {
                    int result = 100 / n;
                    return "OK: " + result;
                } catch (ArithmeticException e) {
                    return "ERROR: divisione per " + n;
                }
            })
            .collect(java.util.stream.Collectors.partitioningBy(s -> s.startsWith("OK")));

        System.out.println("  Successi: " + risultati.get(true));
        System.out.println("  Errori: " + risultati.get(false));

        System.out.println();
    }

    // ==================== FINALLY VS RETURN ====================

    public void finallyVsReturn() {
        System.out.println("=== FINALLY VS RETURN ===");

        // Finally viene eseguito anche con return nel try
        System.out.println("Return nel try: " + methodWithReturnInTry());

        // Finally può sovrascrivere return del try
        System.out.println("Return sovrascritto: " + finallyOverridesReturn());

        // Finally con exception
        try {
            methodWithExceptionAndFinally();
        } catch (Exception e) {
            System.out.println("Catturata: " + e.getMessage());
        }

        // Finally non viene eseguito solo con System.exit() o crash JVM
        System.out.println("\nFinally NON viene eseguito solo con:");
        System.out.println("  - System.exit()");
        System.out.println("  - JVM crash");
        System.out.println("  - Thread.interrupt() in certi casi");

        System.out.println();
    }

    private int methodWithReturnInTry() {
        try {
            System.out.println("  Try: prima del return");
            return 1;
        } finally {
            System.out.println("  Finally: eseguito anche con return nel try!");
        }
    }

    private int finallyOverridesReturn() {
        try {
            System.out.println("  Try: return 1");
            return 1;
        } finally {
            System.out.println("  Finally: return 2 (sovrascrive!)");
            return 2; // ANTI-PATTERN: sovrascrive il return del try
        }
    }

    private void methodWithExceptionAndFinally() throws Exception {
        try {
            System.out.println("  Try: lancio eccezione");
            throw new Exception("Eccezione dal try");
        } finally {
            System.out.println("  Finally: eseguito anche con exception");
        }
    }

    // ==================== BEST PRACTICES ====================

    public void exceptionBestPractices() {
        System.out.println("=== EXCEPTION BEST PRACTICES ===\n");

        System.out.println("1. USA ECCEZIONI SPECIFICHE:");
        System.out.println("   ✓ throw new IllegalArgumentException(\"Età non valida\")");
        System.out.println("   ✗ throw new Exception(\"Errore\")");

        System.out.println("\n2. NON USARE ECCEZIONI PER FLOW CONTROL:");
        System.out.println("   ✗ try { return array[i]; } catch { return null; }");
        System.out.println("   ✓ if (i < array.length) return array[i];");

        System.out.println("\n3. CHIUDI SEMPRE LE RISORSE:");
        System.out.println("   ✓ try-with-resources");
        System.out.println("   ✗ Dimenticare close() nel finally");

        System.out.println("\n4. NON INGOIARE ECCEZIONI:");
        System.out.println("   ✗ catch (Exception e) { }");
        System.out.println("   ✓ catch (Exception e) { log.error(...); }");

        System.out.println("\n5. CATTURA ECCEZIONI SPECIFICHE PRIMA:");
        System.out.println("   ✓ catch IOException, poi Exception");
        System.out.println("   ✗ catch Exception, poi IOException (errore compilazione)");

        System.out.println("\n6. FORNISCI MESSAGGI DESCRITTIVI:");
        System.out.println("   ✓ \"User 'john' not found in database\"");
        System.out.println("   ✗ \"Error\"");

        System.out.println("\n7. DOCUMENTA THROWS CON @throws:");
        System.out.println("   ✓ /** @throws IOException if file not found */");

        System.out.println("\n8. USA CHECKED PER ERRORI RECUPERABILI:");
        System.out.println("   Checked: IOException, SQLException");
        System.out.println("   Unchecked: IllegalArgumentException, NullPointerException");

        System.out.println("\n9. PRESERVA LO STACK TRACE:");
        System.out.println("   ✓ throw new CustomException(\"msg\", e);");
        System.out.println("   ✗ throw new CustomException(\"msg\");");

        System.out.println("\n10. VALIDA INPUT EARLY (FAIL FAST):");
        System.out.println("    ✓ Valida all'inizio del metodo");
        System.out.println("    ✗ Validare dopo operazioni costose");

        System.out.println();
    }

    // ==================== VALIDAZIONE INPUT ====================

    public void validazioneInput() {
        System.out.println("=== VALIDAZIONE INPUT ===");

        // Validazione con IllegalArgumentException
        try {
            validateUser(null, 15, "invalid-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Validazione fallita: " + e.getMessage());
        }

        // Validazione con custom exception
        try {
            validateUserStrict("John", 25, "john@example.com");
            System.out.println("Utente valido");
        } catch (ValidationException e) {
            System.out.println("Errori di validazione:");
            e.getErrors().forEach(err -> System.out.println("  - " + err));
        }

        // Validazione con Objects.requireNonNull
        try {
            String name = Objects.requireNonNull(null, "Nome non può essere null");
        } catch (NullPointerException e) {
            System.out.println("requireNonNull: " + e.getMessage());
        }

        System.out.println();
    }

    private void validateUser(String name, int age, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome richiesto");
        }
        if (age < 18) {
            throw new IllegalArgumentException("Età minima 18 anni");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email non valida");
        }
    }

    private void validateUserStrict(String name, int age, String email) throws ValidationException {
        List<String> errors = new ArrayList<>();
        
        if (name == null || name.length() < 2) {
            errors.add("Nome deve essere almeno 2 caratteri");
        }
        if (age < 18 || age > 120) {
            errors.add("Età deve essere tra 18 e 120");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Email non valida");
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException("Validazione fallita", errors);
        }
    }

    // ==================== GESTIONE RISORSE ====================

    public void gestioneRisorse() {
        System.out.println("=== GESTIONE RISORSE ===");

        // Gestione multipla risorse con try-with-resources
        System.out.println("Gestione automatica risorse:");
        try (CustomResource r1 = new CustomResource("DB Connection");
             CustomResource r2 = new CustomResource("File Handle");
             CustomResource r3 = new CustomResource("Network Socket")) {
            
            System.out.println("  Operazioni con risorse...");
            // Le risorse vengono chiuse in ordine inverso: r3, r2, r1
        } catch (Exception e) {
            System.out.println("  Errore: " + e.getMessage());
        }

        System.out.println();
    }

    // ==================== TRANSAZIONI ====================

    public void transazioni() {
        System.out.println("=== PATTERN TRANSAZIONALE ===");

        // Simulazione transazione con rollback
        try {
            executeTransaction();
            System.out.println("Transazione completata con successo");
        } catch (TransactionException e) {
            System.out.println("Transazione fallita: " + e.getMessage());
            System.out.println("Rollback eseguito");
        }

        System.out.println();
    }

    private void executeTransaction() throws TransactionException {
        List<String> operations = new ArrayList<>();
        
        try {
            // Step 1
            System.out.println("  Step 1: Update account A");
            operations.add("Account A updated");
            
            // Step 2
            System.out.println("  Step 2: Update account B");
            operations.add("Account B updated");
            
            // Step 3 - fallisce
            System.out.println("  Step 3: Validate");
            if (Math.random() > 0.5) {
                throw new Exception("Validation failed");
            }
            
            // Commit
            System.out.println("  Commit");
            
        } catch (Exception e) {
            // Rollback
            System.out.println("  Errore durante transazione, eseguo rollback...");
            for (int i = operations.size() - 1; i >= 0; i--) {
                System.out.println("    Rollback: " + operations.get(i));
            }
            throw new TransactionException("Transaction failed: " + e.getMessage(), e);
        }
    }

    // ==================== RETRY PATTERN ====================

    public void retryPattern() {
        System.out.println("=== RETRY PATTERN ===");

        // Retry semplice
        try {
            String result = retryOperation(3);
            System.out.println("Operazione riuscita: " + result);
        } catch (Exception e) {
            System.out.println("Operazione fallita dopo tutti i retry: " + e.getMessage());
        }

        // Retry con exponential backoff
        System.out.println("\nRetry con exponential backoff:");
        try {
            retryWithBackoff(3);
        } catch (Exception e) {
            System.out.println("Fallito dopo backoff: " + e.getMessage());
        }

        System.out.println();
    }

    private String retryOperation(int maxRetries) throws Exception {
        int attempt = 0;
        Exception lastException = null;
        
        while (attempt < maxRetries) {
            try {
                attempt++;
                System.out.println("  Tentativo " + attempt + " di " + maxRetries);
                
                // Operazione che può fallire
                if (Math.random() > 0.7) {
                    return "Success";
                } else {
                    throw new Exception("Operation failed");
                }
                
            } catch (Exception e) {
                lastException = e;
                System.out.println("    Fallito: " + e.getMessage());
                
                if (attempt < maxRetries) {
                    System.out.println("    Riprovo...");
                }
            }
        }
        
        throw new Exception("Max retries exceeded", lastException);
    }

    private void retryWithBackoff(int maxRetries) throws Exception {
        int attempt = 0;
        long delay = 100; // ms
        
        while (attempt < maxRetries) {
            try {
                attempt++;
                System.out.println("  Tentativo " + attempt);
                
                if (Math.random() > 0.9) {
                    System.out.println("  Successo!");
                    return;
                }
                throw new Exception("Failed");
                
            } catch (Exception e) {
                if (attempt < maxRetries) {
                    System.out.println("  Attendo " + delay + "ms prima di riprovare...");
                    Thread.sleep(delay);
                    delay *= 2; // Exponential backoff
                } else {
                    throw e;
                }
            }
        }
    }

    // ==================== METODI HELPER ====================

    private int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisione per zero");
        }
        return a / b;
    }

    private void throwException() throws Exception {
        throw new Exception("Eccezione di test");
    }

    private void riskyOperation() throws IOException, SQLException {
        if (Math.random() > 0.5) {
            throw new IOException("Errore I/O casuale");
        } else {
            throw new SQLException("Errore SQL casuale");
        }
    }

    private void handleException(Exception e) {
        System.out.println("  Gestione: " + e.getClass().getSimpleName());
    }

    // ==================== CLASSI DI SUPPORTO ====================

    // Custom Resource per try-with-resources
    static class CustomResource implements AutoCloseable {
        private String name;

        public CustomResource(String name) {
            this.name = name;
            System.out.println("  [" + name + "] Risorsa aperta");
        }

        @Override
        public void close() throws Exception {
            System.out.println("  [" + name + "] Risorsa chiusa");
        }
    }

    // Resource che fallisce nella close
    static class FailingResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new Exception("Errore nella chiusura della risorsa");
        }
    }

    // ==================== ECCEZIONI CUSTOM ====================

    // Unchecked exception custom
    static class InvalidAgeException extends RuntimeException {
        private int providedAge;

        public InvalidAgeException(String message, int age) {
            super(message);
            this.providedAge = age;
        }

        public int getProvidedAge() {
            return providedAge;
        }
    }

    // Checked exception custom con dati aggiuntivi
    static class InsufficientFundsException extends Exception {
        private double requiredAmount;
        private double availableAmount;

        public InsufficientFundsException(String message, double required, double available) {
            super(message);
            this.requiredAmount = required;
            this.availableAmount = available;
        }

        public double getRequiredAmount() {
            return requiredAmount;
        }

        public double getAvailableAmount() {
            return availableAmount;
        }

        public double getDeficit() {
            return requiredAmount - availableAmount;
        }
    }

    // Exception con context completo
    static class AuthenticationException extends Exception {
        private String username;
        private long timestamp;
        private int attemptNumber;

        public AuthenticationException(String message, String username, int attemptNumber) {
            super(message);
            this.username = username;
            this.timestamp = System.currentTimeMillis();
            this.attemptNumber = attemptNumber;
        }

        public String getUsername() {
            return username;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public int getAttemptNumber() {
            return attemptNumber;
        }
    }

    // Business exception
    static class BusinessException extends Exception {
        public BusinessException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Validation exception
    static class ValidationException extends Exception {
        private List<String> errors;

        public ValidationException(String message, List<String> errors) {
            super(message);
            this.errors = errors;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

    // Transaction exception
    static class TransactionException extends Exception {
        public TransactionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Checked custom exception
    static class CustomCheckedException extends Exception {
        public CustomCheckedException(String message) {
            super(message);
        }
    }
}
