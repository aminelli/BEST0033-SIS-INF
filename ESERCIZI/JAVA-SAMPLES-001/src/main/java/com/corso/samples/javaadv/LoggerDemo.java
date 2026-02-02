package com.corso.samples.javaadv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * Esempio completo e avanzato sull'uso dei Logger in Java
 * 
 * COS'È IL LOGGING?
 * Il logging è il processo di registrazione di eventi, messaggi ed errori
 * durante l'esecuzione di un'applicazione per debugging, monitoring e audit.
 * 
 * FRAMEWORK PRINCIPALI:
 * 1. java.util.logging (JUL) - Built-in Java
 * 2. SLF4J - Simple Logging Facade (API)
 * 3. Logback - Implementazione moderna (default Spring Boot)
 * 4. Log4j2 - Apache logging (versione 2)
 * 
 * VANTAGGI:
 * - Debugging e troubleshooting
 * - Monitoring applicazioni
 * - Audit trail e compliance
 * - Performance analysis
 * 
 * Include:
 * - java.util.logging (JUL) base
 * - SLF4J con Logback
 * - Livelli di log (TRACE, DEBUG, INFO, WARN, ERROR)
 * - Handlers/Appenders (Console, File, Rolling)
 * - Formatters e Layouts
 * - Logger gerarchici
 * - MDC (Mapped Diagnostic Context)
 * - Filtering e threshold
 * - Performance e async logging
 * - Structured logging (JSON)
 * - Best practices
 * 
 * DIPENDENZE MAVEN RICHIESTE:
 * - org.slf4j:slf4j-api:2.0.9
 * - ch.qos.logback:logback-classic:1.4.11
 * - ch.qos.logback:logback-core:1.4.11
 */
public class LoggerDemo {

    private static final String LOG_DIR = System.getProperty("java.io.tmpdir") + "logger-demo/";

    public static void sample() {
        LoggerDemo demo = new LoggerDemo();
        
        System.out.println("=== LOGGING IN JAVA - GUIDA COMPLETA ===\n");
        
        // Setup
        demo.setupDirectory();
        
        // Java Util Logging
        demo.cosEIlLogging();
        demo.javaUtilLoggingBasic();
        demo.julLevels();
        demo.julHandlers();
        demo.julFormatters();
        demo.julConfiguration();
        
        // SLF4J + Logback
        demo.slf4jBasic();
        demo.slf4jLevels();
        demo.slf4jParametrizedLogging();
        demo.logbackConfiguration();
        demo.logbackAppenders();
        demo.logbackRollingFile();
        
        // Advanced
        demo.loggerHierarchy();
        demo.mdcDemo();
        demo.exceptionLogging();
        demo.conditionalLogging();
        demo.asyncLogging();
        demo.structuredLogging();
        
        // Best Practices
        demo.performanceComparison();
        demo.bestPractices();
        
        // Cleanup
        demo.cleanup();
    }

    // ==================== SETUP ====================

    private void setupDirectory() {
        try {
            Files.createDirectories(Paths.get(LOG_DIR));
            System.out.println("Log directory: " + LOG_DIR + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ==================== COS'È IL LOGGING ====================

    public void cosEIlLogging() {
        System.out.println("=== COS'È IL LOGGING? ===\n");

        System.out.println("DEFINIZIONE:");
        System.out.println("Registrazione sistematica di eventi durante l'esecuzione del programma.\n");

        System.out.println("PERCHÉ USARE LOGGER AL POSTO DI System.out.println?");
        System.out.println("✓ Livelli di log (DEBUG, INFO, WARN, ERROR)");
        System.out.println("✓ Configurazione dinamica");
        System.out.println("✓ Output multipli (console, file, database)");
        System.out.println("✓ Filtering e formatting");
        System.out.println("✓ Performance (async, buffering)");
        System.out.println("✓ Thread-safe");
        System.out.println("✗ System.out non è configurabile\n");

        System.out.println("LIVELLI DI LOG (dal meno al più severo):");
        System.out.println("1. TRACE   - Informazioni molto dettagliate");
        System.out.println("2. DEBUG   - Debugging information");
        System.out.println("3. INFO    - Informazioni generali");
        System.out.println("4. WARN    - Warning, potenziali problemi");
        System.out.println("5. ERROR   - Errori che non bloccano l'app");
        System.out.println("6. FATAL   - Errori critici (solo Log4j)\n");

        System.out.println("FRAMEWORK LOGGING:");
        System.out.println("java.util.logging (JUL):");
        System.out.println("  ✓ Built-in Java");
        System.out.println("  ✗ API verbosa");
        System.out.println("  ✗ Configurazione complessa\n");

        System.out.println("SLF4J (Simple Logging Facade):");
        System.out.println("  ✓ API semplice");
        System.out.println("  ✓ Astrazione (cambiare implementazione)");
        System.out.println("  ✓ Parametrized logging\n");

        System.out.println("Logback:");
        System.out.println("  ✓ Implementazione moderna");
        System.out.println("  ✓ Performance eccellenti");
        System.out.println("  ✓ Default Spring Boot\n");

        System.out.println();
    }

    // ==================== JAVA UTIL LOGGING BASIC ====================

    public void javaUtilLoggingBasic2() {
    System.out.println("=== JAVA UTIL LOGGING - BASIC ===");

    // Usare il nome completo della classe
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.example.MyApp");

    logger.info("Applicazione avviata");
    logger.warning("Attenzione: memoria quasi esaurita");
    logger.severe("Errore critico!");

    logger.log(java.util.logging.Level.INFO, "Messaggio con Level");
    // ... resto del codice
}

    public void javaUtilLoggingBasic() {
        System.out.println("=== JAVA UTIL LOGGING - BASIC ===");

        // Ottenere logger
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.example.MyApp");

        // Log messaggi
        logger.info("Applicazione avviata");
        logger.warning("Attenzione: memoria quasi esaurita");
        logger.severe("Errore critico!");

        // Log con metodi convenienti
        logger.info( "Messaggio con Level");
        logger.warning("Warning con Level");

        // Log con parametri
        String user = "Mario";
        int age = 30;
        logger.info("Utente: {0}, Età: {1}" + new Object[]{user, age});

        System.out.println("Messaggi loggati (vedi console)\n");
    }

    // ==================== JUL LEVELS ====================

    public void julLevels() {
        System.out.println("=== JAVA UTIL LOGGING - LEVELS ===");

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.example.Levels");
        
        // Impostare livello
        logger.setLevel(java.util.logging.Level.ALL); // Log tutto
        
        // Disabilita handler parent per vedere solo questo logger
        logger.setUseParentHandlers(false);
        
        // Aggiungi console handler
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(java.util.logging.Level.ALL);
        logger.addHandler(handler);

        // Tutti i livelli
        logger.finest("FINEST - Livello più dettagliato");
        logger.finer("FINER - Dettagli fini");
        logger.fine("FINE - Dettagli");
        logger.config("CONFIG - Messaggi configurazione");
        logger.info("INFO - Informazioni generali");
        logger.warning("WARNING - Avvisi");
        logger.severe("SEVERE - Errori gravi");

        System.out.println("\nLivelli disponibili:");
        System.out.println("  SEVERE  (1000) - Più importante");
        System.out.println("  WARNING ( 900)");
        System.out.println("  INFO    ( 800)");
        System.out.println("  CONFIG  ( 700)");
        System.out.println("  FINE    ( 500)");
        System.out.println("  FINER   ( 400)");
        System.out.println("  FINEST  ( 300) - Meno importante");
        System.out.println("  ALL     (   0) - Log tutto");
        System.out.println("  OFF     (max) - Disabilita logging");

        System.out.println();
    }

    // ==================== JUL HANDLERS ====================

    public void julHandlers() {
        System.out.println("=== JAVA UTIL LOGGING - HANDLERS ===");

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.example.Handlers");
        logger.setLevel(java.util.logging.Level.ALL);
        logger.setUseParentHandlers(false);

        try {
            // Console Handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(java.util.logging.Level.INFO);
            logger.addHandler(consoleHandler);
            System.out.println("ConsoleHandler aggiunto");

            // File Handler
            String logFile = LOG_DIR + "app.log";
            FileHandler fileHandler = new FileHandler(logFile);
            fileHandler.setLevel(java.util.logging.Level.ALL);
            logger.addHandler(fileHandler);
            System.out.println("FileHandler aggiunto: " + logFile);

            // Log messaggi
            logger.fine("Questo va solo su file");
            logger.info("Questo va su console e file");
            logger.warning("Warning su entrambi");

            System.out.println("Verifica file: " + logFile);

            // Cleanup
            fileHandler.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== JUL FORMATTERS ====================

    public void julFormatters() {
        System.out.println("=== JAVA UTIL LOGGING - FORMATTERS ===");

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.example.Formatters");
        logger.setLevel(java.util.logging.Level.ALL);
        logger.setUseParentHandlers(false);

        // Simple Formatter
        ConsoleHandler simpleHandler = new ConsoleHandler();
        simpleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(simpleHandler);

        logger.info("Messaggio con SimpleFormatter");

        // Custom Formatter
        logger.removeHandler(simpleHandler);
        
        ConsoleHandler customHandler = new ConsoleHandler();
        customHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("[%s] %s - %s: %s%n",
                    LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME),
                    record.getLevel(),
                    record.getLoggerName(),
                    record.getMessage());
            }
        });
        logger.addHandler(customHandler);

        logger.info("Messaggio con CustomFormatter");
        logger.warning("Warning con CustomFormatter");

        System.out.println();
    }

    // ==================== JUL CONFIGURATION ====================

    public void julConfiguration() {
        System.out.println("=== JAVA UTIL LOGGING - CONFIGURATION ===");

        System.out.println("CONFIGURAZIONE TRAMITE FILE:");
        System.out.println("File: logging.properties\n");
        System.out.println("handlers=java.util.logging.ConsoleHandler,java.util.logging.FileHandler");
        System.out.println(".level=INFO");
        System.out.println("");
        System.out.println("java.util.logging.ConsoleHandler.level=WARNING");
        System.out.println("java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter");
        System.out.println("");
        System.out.println("java.util.logging.FileHandler.pattern=app-%u.log");
        System.out.println("java.util.logging.FileHandler.level=ALL");
        System.out.println("java.util.logging.FileHandler.formatter=java.util.logging.XMLFormatter");
        System.out.println("");
        System.out.println("com.example.level=FINE");

        System.out.println("\nCARICAMENTO:");
        System.out.println("java -Djava.util.logging.config.file=logging.properties MyApp");

        System.out.println();
    }

    // ==================== SLF4J BASIC ====================

    public void slf4jBasic() {
        System.out.println("=== SLF4J - BASIC ===");

        // Ottenere logger (pattern consigliato)
        org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerDemo.class);

        // Log messaggi
        logger.trace("Messaggio TRACE - dettagli minuziosi");
        logger.debug("Messaggio DEBUG - debugging info");
        logger.info("Messaggio INFO - informazioni generali");
        logger.warn("Messaggio WARN - attenzione");
        logger.error("Messaggio ERROR - errore");

        System.out.println("SLF4J messaggi loggati\n");
    }

    // ==================== SLF4J LEVELS ====================

    public void slf4jLevels() {
        System.out.println("=== SLF4J - LEVELS ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.SLF4J");

        // Check se livello abilitato (evita costruzione parametri)
        if (logger.isDebugEnabled()) {
            logger.debug("Debug abilitato - operazione costosa: " + expensiveOperation());
        }

        if (logger.isInfoEnabled()) {
            logger.info("Info abilitato");
        }

        if (logger.isWarnEnabled()) {
            logger.warn("Warn abilitato");
        }

        System.out.println("Livelli verificati\n");
    }

    private String expensiveOperation() {
        return "Risultato costoso";
    }

    // ==================== SLF4J PARAMETRIZED LOGGING ====================

    public void slf4jParametrizedLogging() {
        System.out.println("=== SLF4J - PARAMETRIZED LOGGING ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Parametrized");

        String user = "Mario";
        int age = 30;
        String city = "Milano";

        // Parametrized logging (performance migliori)
        logger.info("Utente: {}", user);
        logger.info("Utente: {}, Età: {}", user, age);
        logger.info("Utente: {}, Età: {}, Città: {}", user, age, city);

        // Array per più parametri
        logger.info("Dati: {}, {}, {}, {}", 
            new Object[]{user, age, city, "Italia"});

        // Vs concatenazione (sconsigliato)
        // logger.info("Utente: " + user + ", Età: " + age); // ✗

        System.out.println("Parametrized logging demo completata\n");
    }

    // ==================== LOGBACK CONFIGURATION ====================

    public void logbackConfiguration() {
        System.out.println("=== LOGBACK - CONFIGURATION ===");

        System.out.println("CONFIGURAZIONE PROGRAMMATICA:");

        // Ottenere LoggerContext
        ch.qos.logback.classic.Logger rootLogger = 
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        
        // Reset configurazione
        loggerContext.reset();

        // Console Appender
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setName("console");

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        encoder.start();

        consoleAppender.setEncoder(encoder);
        consoleAppender.start();

        rootLogger.addAppender(consoleAppender);
        rootLogger.setLevel(ch.qos.logback.classic.Level.INFO);

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Logback");
        logger.info("Logback configurato programmaticamente");
        logger.debug("Questo non appare (livello DEBUG < INFO)");

        System.out.println("\nCONFIGURAZIONE XML:");
        System.out.println("File: logback.xml in classpath\n");
        System.out.println("<configuration>");
        System.out.println("  <appender name=\"CONSOLE\" class=\"ch.qos.logback.core.ConsoleAppender\">");
        System.out.println("    <encoder>");
        System.out.println("      <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>");
        System.out.println("    </encoder>");
        System.out.println("  </appender>");
        System.out.println("");
        System.out.println("  <root level=\"info\">");
        System.out.println("    <appender-ref ref=\"CONSOLE\" />");
        System.out.println("  </root>");
        System.out.println("</configuration>");

        System.out.println();
    }

    // ==================== LOGBACK APPENDERS ====================

    public void logbackAppenders() {
        System.out.println("=== LOGBACK - APPENDERS ===");

        ch.qos.logback.classic.Logger rootLogger = 
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        loggerContext.reset();

        // File Appender
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        fileAppender.setName("file");
        fileAppender.setFile(LOG_DIR + "logback-app.log");

        PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
        fileEncoder.setContext(loggerContext);
        fileEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n");
        fileEncoder.start();

        fileAppender.setEncoder(fileEncoder);
        fileAppender.start();

        rootLogger.addAppender(fileAppender);
        rootLogger.setLevel(ch.qos.logback.classic.Level.DEBUG);

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Appenders");
        logger.debug("Debug message to file");
        logger.info("Info message to file");
        logger.warn("Warning message to file");
        logger.error("Error message to file");

        System.out.println("Log scritti su: " + LOG_DIR + "logback-app.log");
        System.out.println();
    }

    // ==================== LOGBACK ROLLING FILE ====================

    public void logbackRollingFile() {
        System.out.println("=== LOGBACK - ROLLING FILE APPENDER ===");

        ch.qos.logback.classic.Logger rootLogger = 
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        loggerContext.reset();

        // Rolling File Appender
        RollingFileAppender<ILoggingEvent> rollingAppender = new RollingFileAppender<>();
        rollingAppender.setContext(loggerContext);
        rollingAppender.setName("rolling");
        rollingAppender.setFile(LOG_DIR + "rolling.log");

        // Time-based rolling policy
        TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
        rollingPolicy.setContext(loggerContext);
        rollingPolicy.setParent(rollingAppender);
        rollingPolicy.setFileNamePattern(LOG_DIR + "rolling-%d{yyyy-MM-dd}.log");
        rollingPolicy.setMaxHistory(30); // Mantieni 30 giorni
        rollingPolicy.start();

        rollingAppender.setRollingPolicy(rollingPolicy);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %logger{36} - %msg%n");
        encoder.start();

        rollingAppender.setEncoder(encoder);
        rollingAppender.start();

        rootLogger.addAppender(rollingAppender);
        rootLogger.setLevel(ch.qos.logback.classic.Level.INFO);

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Rolling");
        
        for (int i = 1; i <= 100; i++) {
            logger.info("Log entry #{}", i);
        }

        System.out.println("100 log entries scritti su rolling file");
        System.out.println("File: " + LOG_DIR + "rolling.log");

        System.out.println("\nCONFIGURAZIONE XML:");
        System.out.println("<appender name=\"ROLLING\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">");
        System.out.println("  <file>app.log</file>");
        System.out.println("  <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">");
        System.out.println("    <fileNamePattern>app-%d{yyyy-MM-dd}.log</fileNamePattern>");
        System.out.println("    <maxHistory>30</maxHistory>");
        System.out.println("  </rollingPolicy>");
        System.out.println("  <encoder>");
        System.out.println("    <pattern>%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n</pattern>");
        System.out.println("  </encoder>");
        System.out.println("</appender>");

        System.out.println();
    }

    // ==================== LOGGER HIERARCHY ====================

    public void loggerHierarchy() {
        System.out.println("=== LOGGER HIERARCHY ===");

        // Logger gerarchici
        org.slf4j.Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        org.slf4j.Logger comLogger = LoggerFactory.getLogger("com");
        org.slf4j.Logger exampleLogger = LoggerFactory.getLogger("com.example");
        org.slf4j.Logger serviceLogger = LoggerFactory.getLogger("com.example.service");

        System.out.println("GERARCHIA:");
        System.out.println("ROOT");
        System.out.println("  └─ com");
        System.out.println("      └─ com.example");
        System.out.println("          └─ com.example.service");

        System.out.println("\nCONFIGURAZIONE GERARCHICA:");
        System.out.println("<logger name=\"com.example\" level=\"DEBUG\" />");
        System.out.println("<logger name=\"com.example.service\" level=\"INFO\" />");
        System.out.println("<root level=\"WARN\">");
        System.out.println("  <appender-ref ref=\"CONSOLE\" />");
        System.out.println("</root>");

        System.out.println("\nLOG MESSAGES:");
        serviceLogger.debug("Debug da service (non appare, level=INFO)");
        serviceLogger.info("Info da service (appare)");
        exampleLogger.debug("Debug da example (appare, level=DEBUG)");

        System.out.println();
    }

    // ==================== MDC DEMO ====================

    public void mdcDemo() {
        System.out.println("=== MDC - MAPPED DIAGNOSTIC CONTEXT ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.MDC");

        System.out.println("MDC permette di aggiungere context ai log (es. user ID, request ID)\n");

        // Simulare richiesta utente
        String userId = "user123";
        String requestId = UUID.randomUUID().toString().substring(0, 8);

        // Aggiungere al MDC
        MDC.put("userId", userId);
        MDC.put("requestId", requestId);

        logger.info("Inizio elaborazione richiesta");
        logger.info("Validazione dati");
        processRequest();
        logger.info("Richiesta completata");

        // Pulire MDC
        MDC.clear();

        System.out.println("\nPATTERN CON MDC:");
        System.out.println("%d{HH:mm:ss} [%X{requestId}] [%X{userId}] %-5level %logger - %msg%n");
        System.out.println("\nOUTPUT:");
        System.out.println("10:15:30 [a1b2c3d4] [user123] INFO  com.example.MDC - Inizio elaborazione");

        System.out.println();
    }

    private void processRequest() {
        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Service");
        logger.info("Elaborazione in corso (MDC ereditato)");
    }

    // ==================== EXCEPTION LOGGING ====================

    public void exceptionLogging() {
        System.out.println("=== EXCEPTION LOGGING ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Exceptions");

        try {
            int result = 10 / 0;
        } catch (Exception e) {
            // Log exception con stack trace
            logger.error("Errore durante il calcolo", e);
        }

        try {
            throw new IllegalArgumentException("Parametro non valido");
        } catch (Exception e) {
            // Log con messaggio e exception
            logger.error("Validazione fallita: {}", e.getMessage(), e);
        }

        // Nested exceptions
        try {
            try {
                throw new IOException("Errore I/O");
            } catch (IOException e) {
                throw new RuntimeException("Wrapper exception", e);
            }
        } catch (Exception e) {
            logger.error("Errore con causa", e);
        }

        System.out.println("Exceptions loggati con stack trace\n");
    }

    // ==================== CONDITIONAL LOGGING ====================

    public void conditionalLogging() {
        System.out.println("=== CONDITIONAL LOGGING ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Conditional");

        // Performance: evita costruzione stringa se debug disabilitato
        
        // ✗ SBAGLIATO - stringa costruita sempre
        // logger.debug("Dati: " + expensiveToString());

        // ✓ CORRETTO - check livello
        if (logger.isDebugEnabled()) {
            logger.debug("Dati: {}", expensiveToString());
        }

        // ✓ ANCORA MEGLIO - parametrized logging (SLF4J check automatico)
        logger.debug("Utente: {}, Status: {}", "Mario", "Active");

        System.out.println("Best practice:");
        System.out.println("1. Usare parametrized logging: logger.debug(\"Value: {}\", value)");
        System.out.println("2. Check livello solo per operazioni costose");
        System.out.println("3. Mai concatenare stringhe nel log");

        System.out.println();
    }

    private String expensiveToString() {
        // Simula operazione costosa
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i).append(",");
        }
        return sb.toString();
    }

    // ==================== ASYNC LOGGING ====================

    public void asyncLogging() {
        System.out.println("=== ASYNC LOGGING ===");

        System.out.println("ASYNC LOGGING migliora performance separando I/O dal thread applicativo\n");

        System.out.println("CONFIGURAZIONE LOGBACK (logback.xml):");
        System.out.println("<configuration>");
        System.out.println("  <appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">");
        System.out.println("    <file>app.log</file>");
        System.out.println("    <encoder>");
        System.out.println("      <pattern>%d %-5level %logger - %msg%n</pattern>");
        System.out.println("    </encoder>");
        System.out.println("  </appender>");
        System.out.println("");
        System.out.println("  <appender name=\"ASYNC\" class=\"ch.qos.logback.classic.AsyncAppender\">");
        System.out.println("    <queueSize>512</queueSize>");
        System.out.println("    <discardingThreshold>0</discardingThreshold>");
        System.out.println("    <appender-ref ref=\"FILE\" />");
        System.out.println("  </appender>");
        System.out.println("");
        System.out.println("  <root level=\"info\">");
        System.out.println("    <appender-ref ref=\"ASYNC\" />");
        System.out.println("  </root>");
        System.out.println("</configuration>");

        System.out.println("\nVANTAGGI:");
        System.out.println("✓ Logging non blocca thread applicativo");
        System.out.println("✓ Performance migliori (throughput)");
        System.out.println("✓ Queue bufferizza burst di log");

        System.out.println("\nSVANTAGGI:");
        System.out.println("✗ Log potrebbero perdersi in caso di crash");
        System.out.println("✗ Memoria aggiuntiva per queue");

        System.out.println();
    }

    // ==================== STRUCTURED LOGGING ====================

    public void structuredLogging() {
        System.out.println("=== STRUCTURED LOGGING (JSON) ===");

        System.out.println("STRUCTURED LOGGING usa JSON per log machine-readable\n");

        System.out.println("DIPENDENZA:");
        System.out.println("<dependency>");
        System.out.println("  <groupId>net.logstash.logback</groupId>");
        System.out.println("  <artifactId>logstash-logback-encoder</artifactId>");
        System.out.println("  <version>7.4</version>");
        System.out.println("</dependency>");

        System.out.println("\nCONFIGURAZIONE:");
        System.out.println("<appender name=\"JSON\" class=\"ch.qos.logback.core.FileAppender\">");
        System.out.println("  <file>app.json</file>");
        System.out.println("  <encoder class=\"net.logstash.logback.encoder.LogstashEncoder\" />");
        System.out.println("</appender>");

        System.out.println("\nEXAMPLE OUTPUT:");
        System.out.println("{");
        System.out.println("  \"@timestamp\": \"2024-01-15T10:30:45.123+00:00\",");
        System.out.println("  \"level\": \"INFO\",");
        System.out.println("  \"logger_name\": \"com.example.Service\",");
        System.out.println("  \"thread_name\": \"main\",");
        System.out.println("  \"message\": \"User login\",");
        System.out.println("  \"userId\": \"user123\",");
        System.out.println("  \"requestId\": \"a1b2c3d4\"");
        System.out.println("}");

        System.out.println("\nVANTAGGI:");
        System.out.println("✓ Parsing facile (tools come ELK, Splunk)");
        System.out.println("✓ Structured queries");
        System.out.println("✓ Context ricco (MDC, markers)");

        System.out.println();
    }

    // ==================== PERFORMANCE COMPARISON ====================

    public void performanceComparison() {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        org.slf4j.Logger logger = LoggerFactory.getLogger("com.example.Performance");
        int iterations = 100_000;

        // 1. Concatenazione diretta (worst)
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (logger.isInfoEnabled()) {
                // Disabilitato per non riempire log
                // logger.info("Value: " + i + ", Square: " + (i * i));
            }
        }
        long concatTime = System.nanoTime() - start;

        // 2. Parametrized logging (best)
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (logger.isInfoEnabled()) {
                // logger.info("Value: {}, Square: {}", i, i * i);
            }
        }
        long paramTime = System.nanoTime() - start;

        // 3. Con check livello
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (logger.isDebugEnabled()) {
                logger.debug("Debug: {}", i);
            }
        }
        long checkTime = System.nanoTime() - start;

        System.out.println("Iterazioni: " + iterations);
        System.out.println("\nRisultati (solo overhead, log disabilitati):");
        System.out.println("  Concatenazione:     " + (concatTime / 1_000_000) + " ms");
        System.out.println("  Parametrized:       " + (paramTime / 1_000_000) + " ms");
        System.out.println("  Check + disabled:   " + (checkTime / 1_000_000) + " ms");

        System.out.println("\nCONSIDERAZIONI:");
        System.out.println("- Parametrized logging evita costruzione stringa se log disabilitato");
        System.out.println("- Check livello utile solo per operazioni molto costose");
        System.out.println("- Async logging migliora throughput per I/O");

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== BEST PRACTICES ===\n");

        System.out.println("1. USA SLF4J COME FACADE:");
        System.out.println("   ✓ import org.slf4j.Logger;");
        System.out.println("   ✓ import org.slf4j.LoggerFactory;");
        System.out.println("   Motivo: Permette cambio implementazione\n");

        System.out.println("2. LOGGER STATICO PER CLASSE:");
        System.out.println("   ✓ private static final Logger log = LoggerFactory.getLogger(MyClass.class);");
        System.out.println("   ✗ Logger come instance variable");
        System.out.println("   Motivo: Performance, no overhead per istanza\n");

        System.out.println("3. USA LIVELLI APPROPRIATI:");
        System.out.println("   - ERROR: Errori che richiedono attenzione");
        System.out.println("   - WARN:  Situazioni anomale recuperabili");
        System.out.println("   - INFO:  Eventi business importanti");
        System.out.println("   - DEBUG: Informazioni debugging");
        System.out.println("   - TRACE: Dettagli molto granulari\n");

        System.out.println("4. PARAMETRIZED LOGGING:");
        System.out.println("   ✓ log.info(\"User {} logged in from {}\", user, ip);");
        System.out.println("   ✗ log.info(\"User \" + user + \" logged in from \" + ip);");
        System.out.println("   Motivo: Performance, no concatenazione se disabled\n");

        System.out.println("5. NON LOGGARE DATI SENSIBILI:");
        System.out.println("   ✗ log.info(\"Password: {}\", password);");
        System.out.println("   ✗ log.info(\"Credit card: {}\", cardNumber);");
        System.out.println("   Motivo: Security, compliance\n");

        System.out.println("6. LOG EXCEPTIONS CORRETTAMENTE:");
        System.out.println("   ✓ log.error(\"Failed to process\", exception);");
        System.out.println("   ✗ log.error(exception.getMessage()); // Perde stack trace");
        System.out.println("   Motivo: Stack trace essenziale per debug\n");

        System.out.println("7. USA MDC PER CONTEXT:");
        System.out.println("   ✓ MDC.put(\"requestId\", requestId);");
        System.out.println("   ✓ MDC.put(\"userId\", userId);");
        System.out.println("   ✓ MDC.clear(); // Cleanup importante!");
        System.out.println("   Motivo: Correlazione log multi-thread\n");

        System.out.println("8. CONFIGURAZIONE ESTERNA:");
        System.out.println("   ✓ logback.xml, log4j2.xml");
        System.out.println("   ✗ Configurazione hardcoded");
        System.out.println("   Motivo: Modifiche senza ricompilazione\n");

        System.out.println("9. ROLLING FILE APPENDER:");
        System.out.println("   ✓ Time-based o size-based rolling");
        System.out.println("   ✓ Retention policy (maxHistory)");
        System.out.println("   Motivo: Gestione spazio disco\n");

        System.out.println("10. ASYNC LOGGING PER ALTA PERFORMANCE:");
        System.out.println("    ✓ AsyncAppender in produzione");
        System.out.println("    Motivo: Non blocca thread applicativi\n");

        System.out.println("11. PATTERN CONSISTENTI:");
        System.out.println("    ✓ %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        System.out.println("    Motivo: Parsing automatico, readability\n");

        System.out.println("12. MONITORING E ALERTING:");
        System.out.println("    ✓ Centralizzare log (ELK, Splunk, CloudWatch)");
        System.out.println("    ✓ Alert su ERROR/FATAL");
        System.out.println("    Motivo: Proactive problem detection\n");

        System.out.println("13. NON LOGGARE IN LOOP STRETTI:");
        System.out.println("    ✗ for(int i=0; i<1000000; i++) { log.debug(...) }");
        System.out.println("    ✓ Aggregare e loggare summary");
        System.out.println("    Motivo: Performance, dimensione log\n");

        System.out.println("14. TESTING:");
        System.out.println("    ✓ Verificare log nei test");
        System.out.println("    ✓ Usare test appender (es. ListAppender)");
        System.out.println("    Motivo: Log fanno parte del comportamento\n");

        System.out.println("15. DOCUMENTAZIONE:");
        System.out.println("    ✓ Documentare significato log importanti");
        System.out.println("    ✓ Runbook per errori comuni");
        System.out.println("    Motivo: Onboarding, troubleshooting\n");
    }

    // ==================== CLEANUP ====================

    private void cleanup() {
        try {
            System.out.println("=== CLEANUP ===");
            System.out.println("Log files in: " + LOG_DIR);
            System.out.println("(Files mantenuti per ispezione)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
