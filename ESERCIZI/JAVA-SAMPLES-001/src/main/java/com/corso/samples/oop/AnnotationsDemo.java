package com.corso.samples.oop;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationsDemo {

    public static void sample() throws Exception {
        AnnotationsDemo demo = new AnnotationsDemo();
        
        System.out.println("=== ANNOTATIONS IN JAVA ===\n");
        
        demo.mostraAnnotationsBase();
        demo.processoAnnotationsClasse();
        demo.processoAnnotationsMetodi();
        demo.processoAnnotationsCampi();
        demo.validazioneConAnnotations();
        demo.configurazioneConAnnotations();
        demo.simpleFrameworkInjection();
        demo.annotazioniRipetibili();
        demo.annotazioniNested();
    }

    // ==================== ANNOTATIONS CUSTOM ====================

    /**
     * MARKER ANNOTATION - Non ha elementi
     * Usata solo per "marcare" un elemento
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Testable {
    }

    /**
     * SINGLE-VALUE ANNOTATION - Ha un solo elemento chiamato "value"
     * Può essere usata con sintassi abbreviata: @Info("descrizione")
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @interface Info {
        String value();
    }

    /**
     * MULTI-VALUE ANNOTATION - Ha più elementi
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface ApiEndpoint {
        String path();
        String method() default "GET";
        String[] produces() default {"application/json"};
        boolean authenticated() default true;
        int timeout() default 30;
    }

    /**
     * ANNOTATION PER VALIDAZIONE
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface NotNull {
        String message() default "Il campo non può essere null";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface MinLength {
        int value();
        String message() default "Lunghezza minima non rispettata";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface MaxLength {
        int value();
        String message() default "Lunghezza massima superata";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Email {
        String message() default "Email non valida";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Range {
        int min();
        int max();
        String message() default "Valore fuori range";
    }

    /**
     * ANNOTATION PER DEPENDENCY INJECTION
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Inject {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Component {
        String name() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Service {
        String value() default "";
    }

    /**
     * ANNOTATION PER CONFIGURAZIONE
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Configuration {
        String profile() default "default";
        boolean lazy() default false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Property {
        String name();
        String defaultValue() default "";
    }

    /**
     * ANNOTATION PER SICUREZZA
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @interface Secured {
        String[] roles() default {};
        Permission[] permissions() default {};
    }

    enum Permission {
        READ, WRITE, DELETE, ADMIN
    }

    /**
     * ANNOTATION PER CACHING
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Cacheable {
        String key() default "";
        int ttl() default 3600; // secondi
        boolean sync() default false;
    }

    /**
     * ANNOTATION PER LOGGING
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Loggable {
        LogLevel level() default LogLevel.INFO;
        boolean logParams() default true;
        boolean logResult() default true;
        boolean logExecutionTime() default false;
    }

    enum LogLevel {
        TRACE, DEBUG, INFO, WARN, ERROR
    }

    /**
     * ANNOTATION PER TRANSAZIONI
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Transactional {
        Propagation propagation() default Propagation.REQUIRED;
        Isolation isolation() default Isolation.DEFAULT;
        int timeout() default -1;
        boolean readOnly() default false;
    }

    enum Propagation {
        REQUIRED, REQUIRES_NEW, NESTED, SUPPORTS, NOT_SUPPORTED, NEVER, MANDATORY
    }

    enum Isolation {
        DEFAULT, READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
    }

    /**
     * ANNOTATION PER RETRY LOGIC
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Retry {
        int maxAttempts() default 3;
        long delay() default 1000; // millisecondi
        Class<? extends Exception>[] retryOn() default {Exception.class};
    }

    /**
     * ANNOTATION PER SCHEDULING
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Scheduled {
        String cron() default "";
        long fixedDelay() default -1;
        long fixedRate() default -1;
        long initialDelay() default 0;
    }

    /**
     * ANNOTATION PER VALIDAZIONE BEAN
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface ValidateBean {
        boolean strict() default true;
    }

    /**
     * ANNOTATION CON ANNOTATION NESTED
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface RateLimited {
        int requestsPerMinute();
        RateLimitStrategy strategy() default @RateLimitStrategy(type = StrategyType.FIXED_WINDOW);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface RateLimitStrategy {
        StrategyType type();
        int bucketSize() default 100;
    }

    enum StrategyType {
        FIXED_WINDOW, SLIDING_WINDOW, TOKEN_BUCKET
    }

    // ==================== ANNOTATIONS RIPETIBILI ====================

    /**
     * Annotation ripetibile (Java 8+)
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Repeatable(Authors.class)
    @interface Author {
        String name();
        String date();
    }

    /**
     * Container per annotation ripetibile
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Authors {
        Author[] value();
    }

    // ==================== META-ANNOTATIONS CUSTOM ====================

    /**
     * Meta-annotation per creare annotation composite
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Secured(roles = {"ADMIN"}, permissions = {Permission.WRITE})
    @Loggable(level = LogLevel.INFO, logExecutionTime = true)
    @Transactional
    @interface AdminOperation {
        String description() default "";
    }

    // ==================== CLASSI DI ESEMPIO ====================

    /**
     * Classe con varie annotations
     */
    @Info("Servizio per la gestione degli utenti")
    @Service("userService")
    @Configuration(profile = "production", lazy = true)
    static class UserService {

        @Inject("userRepository")
        private Object userRepository;

        @Property(name = "max.users", defaultValue = "1000")
        private String maxUsers;

        @Testable
        @Info("Metodo di test")
        public void testMethod() {
            System.out.println("  Metodo di test eseguito");
        }

        @ApiEndpoint(
            path = "/api/users",
            method = "POST",
            produces = {"application/json", "application/xml"},
            authenticated = true,
            timeout = 60
        )
        @Secured(roles = {"USER", "ADMIN"}, permissions = {Permission.WRITE})
        @Loggable(level = LogLevel.DEBUG, logParams = true, logResult = true, logExecutionTime = true)
        public void createUser(String username) {
            System.out.println("  Creazione utente: " + username);
        }

        @Cacheable(key = "user:#{id}", ttl = 7200, sync = true)
        @Loggable(level = LogLevel.INFO, logResult = true)
        public String getUser(int id) {
            return "User#" + id;
        }

        @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            timeout = 30,
            readOnly = false
        )
        @Retry(maxAttempts = 5, delay = 2000, retryOn = {RuntimeException.class})
        public void updateUser(int id, String data) {
            System.out.println("  Aggiornamento utente " + id);
        }

        @AdminOperation(description = "Elimina tutti gli utenti")
        public void deleteAllUsers() {
            System.out.println("  Eliminazione di tutti gli utenti");
        }

        @Scheduled(cron = "0 0 * * * *", initialDelay = 1000)
        public void hourlyCleanup() {
            System.out.println("  Pulizia oraria");
        }

        @RateLimited(
            requestsPerMinute = 100,
            strategy = @RateLimitStrategy(type = StrategyType.TOKEN_BUCKET, bucketSize = 50)
        )
        public void rateLimitedOperation() {
            System.out.println("  Operazione con rate limiting");
        }

        @Author(name = "Mario Rossi", date = "2026-01-15")
        @Author(name = "Luigi Verdi", date = "2026-02-01")
        public void multiAuthorMethod() {
            System.out.println("  Metodo con autori multipli");
        }
    }

    /**
     * Bean per validazione
     */
    @ValidateBean(strict = true)
    static class UserBean {
        @NotNull(message = "Username obbligatorio")
        @MinLength(value = 3, message = "Username troppo corto")
        @MaxLength(value = 20, message = "Username troppo lungo")
        private String username;

        @NotNull
        @Email(message = "Email non valida")
        private String email;

        @Range(min = 18, max = 120, message = "Età non valida")
        private Integer age;

        @MinLength(value = 8, message = "Password troppo corta")
        private String password;

        public UserBean(String username, String email, Integer age, String password) {
            this.username = username;
            this.email = email;
            this.age = age;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public Integer getAge() { return age; }
        public String getPassword() { return password; }
    }

    // ==================== ESEMPI DI USO ANNOTATIONS STANDARD ====================

    /**
     * Esempi di annotations standard di Java
     */
    static class StandardAnnotationsExample {

        // @Override - indica che il metodo sovrascrive un metodo della superclasse
        @Override
        public String toString() {
            return "StandardAnnotationsExample";
        }

        // @Deprecated - indica che il metodo è deprecato
        @Deprecated(since = "1.5", forRemoval = true)
        public void oldMethod() {
            System.out.println("  Metodo deprecato");
        }

        // @SuppressWarnings - sopprime i warning del compilatore
        @SuppressWarnings({"unchecked", "rawtypes"})
        public void methodWithWarnings() {
            List list = new ArrayList();
            list.add("elemento");
        }

        // @SafeVarargs - indica che il metodo non esegue operazioni unsafe sui varargs
        @SafeVarargs
        public final <T> void safeMethod(T... args) {
            for (T arg : args) {
                System.out.println("  " + arg);
            }
        }

        // @FunctionalInterface - indica che l'interfaccia è funzionale (1 solo metodo astratto)
        @FunctionalInterface
        interface Calculator {
            int calculate(int a, int b);
        }
    }

    // ==================== METODI DI PROCESSING ====================

    public void mostraAnnotationsBase() {
        System.out.println("=== ANNOTATIONS STANDARD ===");
        StandardAnnotationsExample example = new StandardAnnotationsExample();
        System.out.println("toString: " + example.toString());
        
        @SuppressWarnings("deprecation")
        Runnable r = example::oldMethod;
        r.run();
        
        example.safeMethod("A", "B", "C");
        System.out.println();
    }

    public void processoAnnotationsClasse() throws Exception {
        System.out.println("=== PROCESSING ANNOTATIONS DI CLASSE ===");
        
        Class<?> clazz = UserService.class;
        
        // Info annotation
        if (clazz.isAnnotationPresent(Info.class)) {
            Info info = clazz.getAnnotation(Info.class);
            System.out.println("Classe: " + clazz.getSimpleName());
            System.out.println("Info: " + info.value());
        }
        
        // Service annotation
        if (clazz.isAnnotationPresent(Service.class)) {
            Service service = clazz.getAnnotation(Service.class);
            System.out.println("Service name: " + service.value());
        }
        
        // Configuration annotation
        if (clazz.isAnnotationPresent(Configuration.class)) {
            Configuration config = clazz.getAnnotation(Configuration.class);
            System.out.println("Profile: " + config.profile());
            System.out.println("Lazy: " + config.lazy());
        }
        
        // Tutte le annotations
        System.out.println("\nTutte le annotations sulla classe:");
        for (Annotation ann : clazz.getAnnotations()) {
            System.out.println("  - " + ann.annotationType().getSimpleName());
        }
        System.out.println();
    }

    public void processoAnnotationsMetodi() throws Exception {
        System.out.println("=== PROCESSING ANNOTATIONS DI METODI ===");
        
        Class<?> clazz = UserService.class;
        
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotations().length > 0) {
                System.out.println("Metodo: " + method.getName());
                
                // Testable
                if (method.isAnnotationPresent(Testable.class)) {
                    System.out.println("  [Testable] Questo metodo è testabile");
                }
                
                // ApiEndpoint
                if (method.isAnnotationPresent(ApiEndpoint.class)) {
                    ApiEndpoint api = method.getAnnotation(ApiEndpoint.class);
                    System.out.println("  [ApiEndpoint]");
                    System.out.println("    Path: " + api.path());
                    System.out.println("    Method: " + api.method());
                    System.out.println("    Produces: " + Arrays.toString(api.produces()));
                    System.out.println("    Authenticated: " + api.authenticated());
                    System.out.println("    Timeout: " + api.timeout() + "s");
                }
                
                // Secured
                if (method.isAnnotationPresent(Secured.class)) {
                    Secured secured = method.getAnnotation(Secured.class);
                    System.out.println("  [Secured]");
                    System.out.println("    Roles: " + Arrays.toString(secured.roles()));
                    System.out.println("    Permissions: " + Arrays.toString(secured.permissions()));
                }
                
                // Loggable
                if (method.isAnnotationPresent(Loggable.class)) {
                    Loggable log = method.getAnnotation(Loggable.class);
                    System.out.println("  [Loggable]");
                    System.out.println("    Level: " + log.level());
                    System.out.println("    Log params: " + log.logParams());
                    System.out.println("    Log result: " + log.logResult());
                    System.out.println("    Log execution time: " + log.logExecutionTime());
                }
                
                // Cacheable
                if (method.isAnnotationPresent(Cacheable.class)) {
                    Cacheable cache = method.getAnnotation(Cacheable.class);
                    System.out.println("  [Cacheable]");
                    System.out.println("    Key: " + cache.key());
                    System.out.println("    TTL: " + cache.ttl() + "s");
                    System.out.println("    Sync: " + cache.sync());
                }
                
                // Transactional
                if (method.isAnnotationPresent(Transactional.class)) {
                    Transactional tx = method.getAnnotation(Transactional.class);
                    System.out.println("  [Transactional]");
                    System.out.println("    Propagation: " + tx.propagation());
                    System.out.println("    Isolation: " + tx.isolation());
                    System.out.println("    Timeout: " + tx.timeout());
                    System.out.println("    Read-only: " + tx.readOnly());
                }
                
                // Retry
                if (method.isAnnotationPresent(Retry.class)) {
                    Retry retry = method.getAnnotation(Retry.class);
                    System.out.println("  [Retry]");
                    System.out.println("    Max attempts: " + retry.maxAttempts());
                    System.out.println("    Delay: " + retry.delay() + "ms");
                    System.out.println("    Retry on: " + Arrays.toString(retry.retryOn()));
                }
                
                // AdminOperation (meta-annotation)
                if (method.isAnnotationPresent(AdminOperation.class)) {
                    AdminOperation admin = method.getAnnotation(AdminOperation.class);
                    System.out.println("  [AdminOperation]");
                    System.out.println("    Description: " + admin.description());
                    
                    // Verifica le annotations composte
                    Annotation[] metaAnns = method.getAnnotation(AdminOperation.class)
                        .annotationType().getAnnotations();
                    System.out.println("    Meta-annotations:");
                    for (Annotation ma : metaAnns) {
                        if (!ma.annotationType().getName().startsWith("java.lang.annotation")) {
                            System.out.println("      - " + ma.annotationType().getSimpleName());
                        }
                    }
                }
                
                // Scheduled
                if (method.isAnnotationPresent(Scheduled.class)) {
                    Scheduled sched = method.getAnnotation(Scheduled.class);
                    System.out.println("  [Scheduled]");
                    if (!sched.cron().isEmpty()) {
                        System.out.println("    Cron: " + sched.cron());
                    }
                    if (sched.fixedDelay() > 0) {
                        System.out.println("    Fixed delay: " + sched.fixedDelay() + "ms");
                    }
                    if (sched.fixedRate() > 0) {
                        System.out.println("    Fixed rate: " + sched.fixedRate() + "ms");
                    }
                    System.out.println("    Initial delay: " + sched.initialDelay() + "ms");
                }
                
                // RateLimited con annotation nested
                if (method.isAnnotationPresent(RateLimited.class)) {
                    RateLimited rl = method.getAnnotation(RateLimited.class);
                    System.out.println("  [RateLimited]");
                    System.out.println("    Requests per minute: " + rl.requestsPerMinute());
                    System.out.println("    Strategy type: " + rl.strategy().type());
                    System.out.println("    Bucket size: " + rl.strategy().bucketSize());
                }
                
                System.out.println();
            }
        }
    }

    public void processoAnnotationsCampi() throws Exception {
        System.out.println("=== PROCESSING ANNOTATIONS DI CAMPI ===");
        
        Class<?> clazz = UserService.class;
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotations().length > 0) {
                System.out.println("Campo: " + field.getName());
                
                // Inject
                if (field.isAnnotationPresent(Inject.class)) {
                    Inject inject = field.getAnnotation(Inject.class);
                    System.out.println("  [Inject] " + inject.value());
                }
                
                // Property
                if (field.isAnnotationPresent(Property.class)) {
                    Property prop = field.getAnnotation(Property.class);
                    System.out.println("  [Property]");
                    System.out.println("    Name: " + prop.name());
                    System.out.println("    Default: " + prop.defaultValue());
                }
                
                System.out.println();
            }
        }
    }

    public void validazioneConAnnotations() throws Exception {
        System.out.println("=== VALIDAZIONE CON ANNOTATIONS ===");
        
        // Bean valido
        UserBean validBean = new UserBean("mario123", "mario@example.com", 25, "password123");
        System.out.println("Validazione bean valido:");
        List<String> errors = validateBean(validBean);
        if (errors.isEmpty()) {
            System.out.println("  ✓ Bean valido");
        } else {
            errors.forEach(e -> System.out.println("  ✗ " + e));
        }
        
        // Bean non valido
        UserBean invalidBean = new UserBean("ab", "invalid-email", 150, "short");
        System.out.println("\nValidazione bean non valido:");
        errors = validateBean(invalidBean);
        if (errors.isEmpty()) {
            System.out.println("  ✓ Bean valido");
        } else {
            errors.forEach(e -> System.out.println("  ✗ " + e));
        }
        System.out.println();
    }

    private List<String> validateBean(Object bean) throws Exception {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = bean.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(bean);
            
            // @NotNull
            if (field.isAnnotationPresent(NotNull.class)) {
                if (value == null) {
                    NotNull ann = field.getAnnotation(NotNull.class);
                    errors.add(field.getName() + ": " + ann.message());
                }
            }
            
            // @MinLength
            if (field.isAnnotationPresent(MinLength.class) && value != null) {
                MinLength ann = field.getAnnotation(MinLength.class);
                if (value instanceof String && ((String) value).length() < ann.value()) {
                    errors.add(field.getName() + ": " + ann.message() + " (min: " + ann.value() + ")");
                }
            }
            
            // @MaxLength
            if (field.isAnnotationPresent(MaxLength.class) && value != null) {
                MaxLength ann = field.getAnnotation(MaxLength.class);
                if (value instanceof String && ((String) value).length() > ann.value()) {
                    errors.add(field.getName() + ": " + ann.message() + " (max: " + ann.value() + ")");
                }
            }
            
            // @Email
            if (field.isAnnotationPresent(Email.class) && value != null) {
                Email ann = field.getAnnotation(Email.class);
                if (value instanceof String && !((String) value).matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    errors.add(field.getName() + ": " + ann.message());
                }
            }
            
            // @Range
            if (field.isAnnotationPresent(Range.class) && value != null) {
                Range ann = field.getAnnotation(Range.class);
                if (value instanceof Integer) {
                    int intValue = (Integer) value;
                    if (intValue < ann.min() || intValue > ann.max()) {
                        errors.add(field.getName() + ": " + ann.message() + 
                            " (" + ann.min() + "-" + ann.max() + ")");
                    }
                }
            }
        }
        
        return errors;
    }

    public void configurazioneConAnnotations() throws Exception {
        System.out.println("=== CONFIGURAZIONE CON ANNOTATIONS ===");
        
        UserService service = new UserService();
        injectDependencies(service);
        injectProperties(service);
        
        System.out.println("Dipendenze e proprietà iniettate");
        System.out.println();
    }

    private void injectDependencies(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Inject inject = field.getAnnotation(Inject.class);
                
                // Simulazione di dependency injection
                Object dependency = "Instance of " + inject.value();
                field.set(obj, dependency);
                
                System.out.println("  Iniettata dipendenza: " + inject.value() + 
                    " in campo " + field.getName());
            }
        }
    }

    private void injectProperties(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Property.class)) {
                field.setAccessible(true);
                Property prop = field.getAnnotation(Property.class);
                
                // Simulazione di property injection
                String value = prop.defaultValue();
                field.set(obj, value);
                
                System.out.println("  Iniettata proprietà: " + prop.name() + 
                    " = " + value + " in campo " + field.getName());
            }
        }
    }

    public void simpleFrameworkInjection() throws Exception {
        System.out.println("=== SIMPLE FRAMEWORK INJECTION ===");
        
        Map<String, Object> context = new HashMap<>();
        
        // Scansione delle classi @Component e @Service
        Class<?>[] classes = {UserService.class};
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Component comp = clazz.getAnnotation(Component.class);
                String name = comp.name().isEmpty() ? clazz.getSimpleName() : comp.name();
                context.put(name, clazz.getDeclaredConstructor().newInstance());
                System.out.println("  Registrato Component: " + name);
            }
            
            if (clazz.isAnnotationPresent(Service.class)) {
                Service serv = clazz.getAnnotation(Service.class);
                String name = serv.value().isEmpty() ? clazz.getSimpleName() : serv.value();
                context.put(name, clazz.getDeclaredConstructor().newInstance());
                System.out.println("  Registrato Service: " + name);
            }
        }
        
        System.out.println("\nContext contiene " + context.size() + " bean(s)");
        System.out.println();
    }

    public void annotazioniRipetibili() throws Exception {
        System.out.println("=== ANNOTATIONS RIPETIBILI ===");
        
        Method method = UserService.class.getDeclaredMethod("multiAuthorMethod");
        
        // Modo 1: tramite container
        if (method.isAnnotationPresent(Authors.class)) {
            Authors authors = method.getAnnotation(Authors.class);
            System.out.println("Autori del metodo " + method.getName() + ":");
            for (Author author : authors.value()) {
                System.out.println("  - " + author.name() + " (" + author.date() + ")");
            }
        }
        
        // Modo 2: getAnnotationsByType (Java 8+)
        Author[] authors = method.getAnnotationsByType(Author.class);
        System.out.println("\nAutori via getAnnotationsByType:");
        for (Author author : authors) {
            System.out.println("  - " + author.name() + " (" + author.date() + ")");
        }
        
        System.out.println();
    }

    public void annotazioniNested() throws Exception {
        System.out.println("=== ANNOTATIONS NESTED ===");
        
        Method method = UserService.class.getDeclaredMethod("rateLimitedOperation");
        
        if (method.isAnnotationPresent(RateLimited.class)) {
            RateLimited rl = method.getAnnotation(RateLimited.class);
            System.out.println("Rate Limiting su " + method.getName() + ":");
            System.out.println("  Requests per minute: " + rl.requestsPerMinute());
            
            RateLimitStrategy strategy = rl.strategy();
            System.out.println("  Strategy:");
            System.out.println("    Type: " + strategy.type());
            System.out.println("    Bucket size: " + strategy.bucketSize());
        }
        
        System.out.println();
    }
}
