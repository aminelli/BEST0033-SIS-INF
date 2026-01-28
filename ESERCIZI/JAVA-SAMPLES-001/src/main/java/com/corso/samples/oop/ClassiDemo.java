package com.corso.samples.oop;

import java.util.*;
import java.time.LocalDate;

/**
 * Esempio completo e dettagliato su TUTTE LE TIPOLOGIE DI CLASSI in Java
 *
 * TIPOLOGIE:
 * 1. Classi Concrete (Standard)
 * 2. Classi Astratte (Abstract)
 * 3. Classi Final
 * 4. Classi Interne (Inner Classes)
 *    - Member Inner Class
 *    - Static Nested Class
 *    - Local Inner Class
 *    - Anonymous Inner Class
 * 5. Classi Enum
 * 6. Classi Record (Java 14+)
 * 7. Classi Sealed (Java 17+)
 * 8. Interfacce
 * 9. Classi Generiche
 * 10. Pattern Singleton
 * 11. Classi Immutabili
 * 12. Classi Utility (solo metodi statici)
 */
public class ClassiDemo {

    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   TIPOLOGIE DI CLASSI IN JAVA                     ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        esempiClasseConcreta();
        esempiClasseAstratta();
        esempiClasseFinal();
        esempiClassiInterne();
        esempiEnum();
        esempiRecord();
        esempiSealed();
        esempiInterfacce();
        esempiGenerics();
        esempiSingleton();
        esempiImmutabile();
        esempiUtility();
        riepilogoEBestPractices();
    }

    /**
     * 1. CLASSE CONCRETA (Standard)
     *
     * CARATTERISTICHE:
     * - Classe "normale" che può essere istanziata
     * - Può avere campi, metodi, costruttori
     * - Può estendere una classe e implementare interfacce
     * - Può essere ereditata (se non final)
     */
    public static void esempiClasseConcreta() {
        System.out.println("【 1. CLASSE CONCRETA 】");
        System.out.println("─".repeat(50));

        // Esempio base
        Persona persona = new Persona("Mario", "Rossi", 30);
        System.out.println("Persona creata: " + persona.getNomeCompleto());
        System.out.println("  Età: " + persona.getEta());

        persona.setEta(31);
        System.out.println("  Età aggiornata: " + persona.getEta());

        // Esempio con ereditarietà
        Studente studente = new Studente("Luigi", "Verdi", 22, "S12345");
        System.out.println("\nStudente: " + studente.getNomeCompleto());
        System.out.println("  Matricola: " + studente.getMatricola());
        System.out.println("  Info: " + studente.getInfo());

        // Esempio con composizione
        Auto auto = new Auto("Fiat", "Panda", 2020);
        auto.setProprietario(persona);
        System.out.println("\nAuto: " + auto.getDescrizione());
        System.out.println("  Proprietario: " + auto.getProprietario().getNomeCompleto());
    }

    /**
     * 2. CLASSE ASTRATTA
     *
     * CARATTERISTICHE:
     * - NON può essere istanziata direttamente
     * - Può contenere metodi astratti (senza implementazione)
     * - Può contenere metodi concreti (con implementazione)
     * - Può avere campi, costruttori
     * - Le sottoclassi devono implementare i metodi astratti
     */
    public static void esempiClasseAstratta() {
        System.out.println("\n【 2. CLASSE ASTRATTA 】");
        System.out.println("─".repeat(50));

        // Esempio: gerarchia di forme geometriche
        Forma cerchio = new Cerchio(5.0);
        Forma rettangolo = new Rettangolo(4.0, 6.0);
        Forma triangolo = new Triangolo(3.0, 4.0);

        System.out.println("Cerchio (raggio 5):");
        System.out.println("  Area: " + cerchio.calcolaArea());
        System.out.println("  Perimetro: " + cerchio.calcolaPerimetro());
        System.out.println("  Info: " + cerchio.getInfo());

        System.out.println("\nRettangolo (4x6):");
        System.out.println("  Area: " + rettangolo.calcolaArea());
        System.out.println("  Perimetro: " + rettangolo.calcolaPerimetro());

        System.out.println("\nTriangolo (base 3, altezza 4):");
        System.out.println("  Area: " + triangolo.calcolaArea());

        // Polimorfismo con classi astratte
        List<Forma> forme = Arrays.asList(cerchio, rettangolo, triangolo);
        double areaTotale = forme.stream().mapToDouble(Forma::calcolaArea).sum();
        System.out.println("\nArea totale: " + areaTotale);
    }

    /**
     * 3. CLASSE FINAL
     *
     * CARATTERISTICHE:
     * - NON può essere estesa (ereditata)
     * - Tutti i metodi sono implicitamente final
     * - Utile per sicurezza e immutabilità
     * - Esempi: String, Integer, LocalDate
     */
    public static void esempiClasseFinal() {
        System.out.println("\n【 3. CLASSE FINAL 】");
        System.out.println("─".repeat(50));

        // Esempio: classe che non deve essere estesa
        PuntoImmutabile punto = new PuntoImmutabile(10, 20);
        System.out.println("Punto immutabile: (" + punto.getX() + ", " + punto.getY() + ")");
        System.out.println("  Distanza da origine: " + punto.distanzaDaOrigine());

        // Creazione di nuovo punto (immutabile)
        PuntoImmutabile nuovoPunto = punto.trasla(5, 3);
        System.out.println("Punto traslato: (" + nuovoPunto.getX() + ", " + nuovoPunto.getY() + ")");
        System.out.println("Punto originale: (" + punto.getX() + ", " + punto.getY() + ")");

        // Esempio con costanti
        ConfigurazioneApp config = new ConfigurazioneApp();
        System.out.println("\nConfigurazione:");
        System.out.println("  Nome app: " + config.getNomeApp());
        System.out.println("  Versione: " + config.getVersione());
        System.out.println("  Max connessioni: " + config.getMaxConnessioni());
    }

    /**
     * 4. CLASSI INTERNE (Inner Classes)
     *
     * TIPI:
     * - Member Inner Class: classe membro non statica
     * - Static Nested Class: classe annidata statica
     * - Local Inner Class: classe locale in un metodo
     * - Anonymous Inner Class: classe anonima
     */
    public static void esempiClassiInterne() {
        System.out.println("\n【 4. CLASSI INTERNE 】");
        System.out.println("─".repeat(50));

        // Member Inner Class
        System.out.println("A) Member Inner Class:");
        Universita univ = new Universita("Università di Roma");
        Universita.Dipartimento dip = univ.new Dipartimento("Informatica");
        System.out.println("  " + dip.getInfoCompleta());

        // Static Nested Class
        System.out.println("\nB) Static Nested Class:");
        Azienda.Dipendente dip1 = new Azienda.Dipendente("Mario", "IT");
        Azienda.Dipendente dip2 = new Azienda.Dipendente("Luigi", "HR");
        System.out.println("  " + dip1.getInfo());
        System.out.println("  " + dip2.getInfo());
        System.out.println("  Totale dipendenti: " + Azienda.Dipendente.getContatore());

        // Local Inner Class
        System.out.println("\nC) Local Inner Class:");
        String risultato = calcolaConLocalClass(10, 20);
        System.out.println("  " + risultato);

        // Anonymous Inner Class
        System.out.println("\nD) Anonymous Inner Class:");
        eseguiConAnonymousClass();
    }

    // Metodo di supporto per Local Inner Class
    private static String calcolaConLocalClass(int a, int b) {
        // Local Inner Class (definita dentro un metodo)
        class Calcolatrice {
            int somma() { return a + b; }
            int prodotto() { return a * b; }
            String report() {
                return "Numeri: " + a + " e " + b +
                        ", Somma: " + somma() +
                        ", Prodotto: " + prodotto();
            }
        }

        Calcolatrice calc = new Calcolatrice();
        return calc.report();
    }

    // Metodo di supporto per Anonymous Inner Class
    private static void eseguiConAnonymousClass() {
        // Anonymous Inner Class che implementa un'interfaccia
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("  Esecuzione task con classe anonima");
            }
        };
        task.run();

        // Anonymous Inner Class con comparatore
        List<String> nomi = new ArrayList<>(Arrays.asList("Mario", "Anna", "Luigi"));
        Collections.sort(nomi, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1); // Ordine inverso
            }
        });
        System.out.println("  Nomi ordinati (inverso): " + nomi);
    }

    /**
     * 5. CLASSI ENUM
     *
     * CARATTERISTICHE:
     * - Insieme fisso di costanti
     * - Può avere campi, metodi, costruttori
     * - Implicitamente final e static
     * - Type-safe
     */
    public static void esempiEnum() {
        System.out.println("\n【 5. CLASSI ENUM 】");
        System.out.println("─".repeat(50));

        // Enum semplice
        System.out.println("A) Enum semplice:");
        GiornoSettimana giorno = GiornoSettimana.LUNEDI;
        System.out.println("  Giorno: " + giorno);
        System.out.println("  È weekend? " + giorno.isWeekend());

        // Iterazione su tutti i valori
        System.out.println("\n  Tutti i giorni:");
        for (GiornoSettimana g : GiornoSettimana.values()) {
            System.out.println("    " + g + " (weekend: " + g.isWeekend() + ")");
        }

        // Enum con campi e metodi
        System.out.println("\nB) Enum con campi e metodi:");
        Pianeta terra = Pianeta.TERRA;
        System.out.println("  Pianeta: " + terra);
        System.out.println("  Massa: " + terra.getMassa() + " kg");
        System.out.println("  Raggio: " + terra.getRaggio() + " m");
        System.out.println("  Gravità superficiale: " + terra.gravitaSuperficiale() + " m/s²");

        // Enum con comportamenti diversi
        System.out.println("\nC) Enum con comportamenti:");
        for (Operazione op : Operazione.values()) {
            double risultato = op.calcola(10, 5);
            System.out.println("  " + op + ": 10 " + op.getSimbol() + " 5 = " + risultato);
        }

        // Enum per stati
        System.out.println("\nD) Enum per macchina a stati:");
        StatoOrdine stato = StatoOrdine.NUOVO;
        System.out.println("  Stato iniziale: " + stato + " - " + stato.getDescrizione());

        stato = stato.prossimoStato();
        System.out.println("  Stato successivo: " + stato + " - " + stato.getDescrizione());
    }

    /**
     * 6. CLASSI RECORD (Java 14+)
     *
     * CARATTERISTICHE:
     * - Classi immutabili per dati (data carriers)
     * - Genera automaticamente: costruttore, getter, equals, hashCode, toString
     * - Sintassi compatta
     * - Campi implicitamente final
     */
    public static void esempiRecord() {
        System.out.println("\n【 6. CLASSI RECORD (Java 14+) 】");
        System.out.println("─".repeat(50));

        // Record semplice
        Point point = new Point(10, 20);
        System.out.println("Point: " + point);
        System.out.println("  x = " + point.x());
        System.out.println("  y = " + point.y());

        // Record con metodi custom
        Persona2 persona = new Persona2("Mario", "Rossi", 30);
        System.out.println("\nPersona: " + persona);
        System.out.println("  Nome completo: " + persona.nomeCompleto());
        System.out.println("  È maggiorenne? " + persona.isMaggiorenne());

        // Equals e hashCode automatici
        Point point2 = new Point(10, 20);
        System.out.println("\nPoint equals: " + point.equals(point2));
        System.out.println("Point hashCode: " + point.hashCode() + " = " + point2.hashCode());

        // Record con validazione nel costruttore compatto
        try {
            Prodotto prodotto = new Prodotto("Laptop", 999.99, 5);
            System.out.println("\nProdotto valido: " + prodotto);

            // Questo lancerà eccezione
            // Prodotto invalido = new Prodotto("", -100, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore validazione: " + e.getMessage());
        }

        // Record nested
        Indirizzo indirizzo = new Indirizzo("Via Roma", "10", "Roma", "00100");
        Cliente cliente = new Cliente("Mario Rossi", "mario@email.com", indirizzo);
        System.out.println("\nCliente: " + cliente.nome());
        System.out.println("  Email: " + cliente.email());
        System.out.println("  Città: " + cliente.indirizzo().citta());
    }

    /**
     * 7. CLASSI SEALED (Java 17+)
     *
     * CARATTERISTICHE:
     * - Controlla quali classi possono estenderle
     * - Usa 'permits' per specificare sottoclassi
     * - Le sottoclassi devono essere: final, sealed, o non-sealed
     * - Utile per gerarchie chiuse e pattern matching
     */
    public static void esempiSealed() {
        System.out.println("\n【 7. CLASSI SEALED (Java 17+) 】");
        System.out.println("─".repeat(50));

        // Esempi di classi sealed
        Forma2 cerchio = new Cerchio2(5.0);
        Forma2 quadrato = new Quadrato(4.0);
        Forma2 rettangolo2 = new Rettangolo2(3.0, 6.0);

        System.out.println("Cerchio: area = " + cerchio.area());
        System.out.println("Quadrato: area = " + quadrato.area());
        System.out.println("Rettangolo: area = " + rettangolo2.area());

        // Pattern matching con sealed classes
        System.out.println("\nPattern matching con sealed:");
        descriviForma(cerchio);
        descriviForma(quadrato);
        descriviForma(rettangolo2);

        // Esempio con gerarchia di pagamenti
        System.out.println("\nGerarchie di pagamenti:");
        Pagamento carta = new PagamentoCarta("1234-5678-9012-3456", "Mario Rossi");
        Pagamento paypal = new PagamentoPayPal("mario@email.com");
        Pagamento contanti = new PagamentoContanti(100.0);

        processaPagamento(carta, 50.0);
        processaPagamento(paypal, 75.0);
        processaPagamento(contanti, 100.0);
    }

    private static void descriviForma(Forma2 forma) {
        String descrizione = switch (forma) {
            case Cerchio2 c -> "Cerchio con raggio " + c.raggio();
            case Quadrato q -> "Quadrato con lato " + q.lato();
            case Rettangolo2 r -> "Rettangolo " + r.base() + "x" + r.altezza();
        };
        System.out.println("  " + descrizione + " (area: " + forma.area() + ")");
    }

    private static void processaPagamento(Pagamento pagamento, double importo) {
        String info = switch (pagamento) {
            case PagamentoCarta p -> "💳 Carta: " + p.numeroMascherato() + " - €" + importo;
            case PagamentoPayPal p -> "🅿️ PayPal: " + p.email() + " - €" + importo;
            case PagamentoContanti p -> "💵 Contanti: €" + Math.min(importo, p.importoDisponibile());
        };
        System.out.println("  " + info);
    }

    /**
     * 8. INTERFACCE
     *
     * CARATTERISTICHE:
     * - Contratto di comportamento
     * - Metodi astratti (implicitamente public abstract)
     * - Default methods (Java 8+)
     * - Static methods (Java 8+)
     * - Private methods (Java 9+)
     * - Una classe può implementare multiple interfacce
     */
    public static void esempiInterfacce() {
        System.out.println("\n【 8. INTERFACCE 】");
        System.out.println("─".repeat(50));

        // Interfaccia base
        System.out.println("A) Implementazione interfacce:");
        Animale cane = new Cane("Fido");
        Animale gatto = new Gatto("Micio");

        cane.emettiSuono();
        cane.mangia();

        gatto.emettiSuono();
        gatto.mangia();

        // Multiple interfaces
        System.out.println("\nB) Multiple interfaces:");
        Automobile tesla = new Automobile("Tesla Model 3", 2023);
        tesla.accendi();
        tesla.guidabile();
        tesla.ricarica(80);

        // Default methods
        System.out.println("\nC) Default methods:");
        System.out.println("  Info cane: " + cane.getInfo());
        System.out.println("  Info gatto: " + gatto.getInfo());

        // Static methods
        System.out.println("\nD) Static methods:");
        System.out.println("  " + Animale.getCategoriaGenerale());
    }

    /**
     * 9. CLASSI GENERICHE
     *
     * CARATTERISTICHE:
     * - Parametri di tipo (<T>, <K,V>, ecc.)
     * - Type safety a compile-time
     * - Riusabilità del codice
     * - Bounded type parameters
     */
    public static void esempiGenerics() {
        System.out.println("\n【 9. CLASSI GENERICHE 】");
        System.out.println("─".repeat(50));

        // Generic class semplice
        System.out.println("A) Box generico:");
        Box<String> boxString = new Box<>("Hello");
        Box<Integer> boxInt = new Box<>(42);

        System.out.println("  Box String: " + boxString.get());
        System.out.println("  Box Integer: " + boxInt.get());

        // Generic con multiple type parameters
        System.out.println("\nB) Pair generico:");
        Pair<String, Integer> coppia = new Pair<>("Età", 30);
        System.out.println("  " + coppia.getKey() + " = " + coppia.getValue());

        Pair<String, String> coordinate = new Pair<>("Latitudine", "41.9028° N");
        System.out.println("  " + coordinate.getKey() + " = " + coordinate.getValue());

        // Generic collection
        System.out.println("\nC) Stack generico:");
        Stack<String> stackString = new Stack<>();
        stackString.push("Primo");
        stackString.push("Secondo");
        stackString.push("Terzo");

        System.out.println("  Pop: " + stackString.pop());
        System.out.println("  Peek: " + stackString.peek());
        System.out.println("  Size: " + stackString.size());

        // Bounded type parameters
        System.out.println("\nD) Comparatore generico (bounded):");
        ComparatoreNumeri<Integer> compInt = new ComparatoreNumeri<>();
        System.out.println("  Max(10, 20): " + compInt.max(10, 20));
        System.out.println("  Min(10, 20): " + compInt.min(10, 20));

        ComparatoreNumeri<Double> compDouble = new ComparatoreNumeri<>();
        System.out.println("  Max(3.14, 2.71): " + compDouble.max(3.14, 2.71));
    }

    /**
     * 10. PATTERN SINGLETON
     *
     * CARATTERISTICHE:
     * - Una sola istanza della classe
     * - Accesso globale all'istanza
     * - Costruttore privato
     * - Diverse implementazioni (eager, lazy, thread-safe)
     */
    public static void esempiSingleton() {
        System.out.println("\n【 10. PATTERN SINGLETON 】");
        System.out.println("─".repeat(50));

        // Singleton eager
        System.out.println("A) Singleton eager:");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        System.out.println("  db1 == db2: " + (db1 == db2));
        db1.connetti();
        System.out.println("  Stato: " + db2.getStato());

        // Singleton lazy thread-safe
        System.out.println("\nB) Singleton lazy thread-safe:");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("  logger1 == logger2: " + (logger1 == logger2));
        logger1.log("Messaggio di test");
        logger1.log("Secondo messaggio");

        // Singleton enum (best practice)
        System.out.println("\nC) Singleton enum (best practice):");
        ConfigurationManager config1 = ConfigurationManager.INSTANCE;
        ConfigurationManager config2 = ConfigurationManager.INSTANCE;

        System.out.println("  config1 == config2: " + (config1 == config2));
        config1.setProperty("app.name", "MyApp");
        System.out.println("  Proprietà: " + config2.getProperty("app.name"));
    }

    /**
     * 11. CLASSI IMMUTABILI
     *
     * CARATTERISTICHE:
     * - Stato non modificabile dopo creazione
     * - Thread-safe
     * - Campi final
     * - Nessun setter
     * - Defensive copying
     * - Esempi: String, Integer, LocalDate
     */
    public static void esempiImmutabile() {
        System.out.println("\n【 11. CLASSI IMMUTABILI 】");
        System.out.println("─".repeat(50));

        // Classe immutabile
        List<String> interessi = new ArrayList<>(Arrays.asList("Java", "Python"));
        PersonaImmutabile persona = new PersonaImmutabile(
                "Mario", "Rossi", 30, interessi
        );

        System.out.println("Persona: " + persona.getNome() + " " + persona.getCognome());
        System.out.println("  Età: " + persona.getEta());
        System.out.println("  Interessi: " + persona.getInteressi());

        // Tentativo di modifica (non influenza l'oggetto)
        interessi.add("C++");
        System.out.println("  Interessi dopo modifica lista esterna: " + persona.getInteressi());

        List<String> interessiOttenuti = persona.getInteressi();
        try {
            interessiOttenuti.add("Ruby"); // Lancia UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("  ✓ Lista interessi è immutabile");
        }

        // Esempio con Money
        System.out.println("\nMoney immutabile:");
        Money prezzo = new Money(99.99, "EUR");
        System.out.println("  Prezzo: " + prezzo);

        Money scontato = prezzo.sconta(10); // Restituisce nuovo oggetto
        System.out.println("  Prezzo scontato 10%: " + scontato);
        System.out.println("  Prezzo originale: " + prezzo);
    }

    /**
     * 12. CLASSI UTILITY (Solo metodi statici)
     *
     * CARATTERISTICHE:
     * - Solo metodi statici
     * - Costruttore privato (non istanziabile)
     * - Esempi: Math, Collections, Arrays, Objects
     */
    public static void esempiUtility() {
        System.out.println("\n【 12. CLASSI UTILITY 】");
        System.out.println("─".repeat(50));

        // Utility per stringhe
        System.out.println("A) StringUtils:");
        System.out.println("  isEmpty(null): " + StringUtils.isEmpty(null));
        System.out.println("  isEmpty(\"\"): " + StringUtils.isEmpty(""));
        System.out.println("  isEmpty(\"test\"): " + StringUtils.isEmpty("test"));
        System.out.println("  capitalize(\"hello\"): " + StringUtils.capitalize("hello"));
        System.out.println("  reverse(\"Java\"): " + StringUtils.reverse("Java"));

        // Utility per matematica
        System.out.println("\nB) MathUtils:");
        System.out.println("  isPrime(17): " + MathUtils.isPrime(17));
        System.out.println("  isPrime(20): " + MathUtils.isPrime(20));
        System.out.println("  factorial(5): " + MathUtils.factorial(5));
        System.out.println("  gcd(48, 18): " + MathUtils.gcd(48, 18));

        // Utility per validazione
        System.out.println("\nC) ValidationUtils:");
        System.out.println("  isValidEmail(\"test@example.com\"): " +
                ValidationUtils.isValidEmail("test@example.com"));
        System.out.println("  isValidEmail(\"invalid\"): " +
                ValidationUtils.isValidEmail("invalid"));
    }

    /**
     * RIEPILOGO E BEST PRACTICES
     */
    public static void riepilogoEBestPractices() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   RIEPILOGO E BEST PRACTICES                      ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

        System.out.println("\n【 QUANDO USARE OGNI TIPO 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ Classe Concreta: caso standard, oggetti istanziabili");
        System.out.println("✓ Classe Astratta: comportamento comune + specializzazioni");
        System.out.println("✓ Classe Final: prevenire ereditarietà, garantire sicurezza");
        System.out.println("✓ Inner Class: logica strettamente legata alla classe esterna");
        System.out.println("✓ Enum: insieme fisso di costanti con comportamento");
        System.out.println("✓ Record: data carriers immutabili (DTO, value objects)");
        System.out.println("✓ Sealed: gerarchie chiuse, pattern matching esaustivo");
        System.out.println("✓ Interface: contratti, multiple inheritance, polimorfismo");
        System.out.println("✓ Generic: riusabilità con type safety");
        System.out.println("✓ Singleton: una sola istanza (config, connessioni)");
        System.out.println("✓ Immutable: thread-safe, value objects, sicurezza");
        System.out.println("✓ Utility: raggruppare metodi helper statici");

        System.out.println("\n【 BEST PRACTICES 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ Preferisci composizione a ereditarietà");
        System.out.println("✓ Programma verso interfacce, non implementazioni");
        System.out.println("✓ Usa record per DTO e value objects (Java 14+)");
        System.out.println("✓ Usa sealed classes per gerarchie controllate (Java 17+)");
        System.out.println("✓ Enum singleton per pattern singleton thread-safe");
        System.out.println("✓ Rendi classi immutabili quando possibile");
        System.out.println("✓ Usa generics per type safety e riusabilità");
        System.out.println("✓ Default methods per evoluzione interfacce");
        System.out.println("✓ Costruttore privato per utility e singleton");
        System.out.println("✓ Documenta classi astratte e interfacce");

        System.out.println("\n【 PRINCIPI SOLID 】");
        System.out.println("─".repeat(50));
        System.out.println("S - Single Responsibility: una classe, una responsabilità");
        System.out.println("O - Open/Closed: aperta estensione, chiusa modifica");
        System.out.println("L - Liskov Substitution: sottoclassi sostituibili");
        System.out.println("I - Interface Segregation: interfacce specifiche");
        System.out.println("D - Dependency Inversion: dipendi da astrazioni");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("Fine degli esempi sulle tipologie di classi Java");
        System.out.println("═".repeat(50));
    }
}

// ═══════════════════════════════════════════════════════════════
// 1. CLASSI CONCRETE
// ═══════════════════════════════════════════════════════════════

/**
 * Classe concreta base
 */
class Persona {
    private String nome;
    private String cognome;
    private int eta;

    public Persona(String nome, String cognome, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    public int getEta() { return eta; }
    public void setEta(int eta) { this.eta = eta; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
}

/**
 * Classe che estende Persona
 */
class Studente extends Persona {
    private String matricola;

    public Studente(String nome, String cognome, int eta, String matricola) {
        super(nome, cognome, eta);
        this.matricola = matricola;
    }

    public String getMatricola() { return matricola; }

    public String getInfo() {
        return "Studente: " + getNomeCompleto() + " (" + matricola + ")";
    }
}

/**
 * Classe con composizione
 */
class Auto {
    private String marca;
    private String modello;
    private int anno;
    private Persona proprietario;

    public Auto(String marca, String modello, int anno) {
        this.marca = marca;
        this.modello = modello;
        this.anno = anno;
    }

    public String getDescrizione() {
        return marca + " " + modello + " (" + anno + ")";
    }

    public void setProprietario(Persona proprietario) {
        this.proprietario = proprietario;
    }

    public Persona getProprietario() { return proprietario; }
}

// ═══════════════════════════════════════════════════════════════
// 2. CLASSI ASTRATTE
// ═══════════════════════════════════════════════════════════════

/**
 * Classe astratta per forme geometriche
 */
abstract class Forma {
    protected String colore;

    public Forma() {
        this.colore = "bianco";
    }

    // Metodi astratti (devono essere implementati)
    public abstract double calcolaArea();
    public abstract double calcolaPerimetro();

    // Metodo concreto (con implementazione)
    public String getInfo() {
        return "Forma di colore " + colore + ", area: " + calcolaArea();
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}

class Cerchio extends Forma {
    private double raggio;

    public Cerchio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double calcolaArea() {
        return Math.PI * raggio * raggio;
    }

    @Override
    public double calcolaPerimetro() {
        return 2 * Math.PI * raggio;
    }
}

class Rettangolo extends Forma {
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double calcolaArea() {
        return base * altezza;
    }

    @Override
    public double calcolaPerimetro() {
        return 2 * (base + altezza);
    }
}

class Triangolo extends Forma {
    private double base;
    private double altezza;

    public Triangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double calcolaArea() {
        return (base * altezza) / 2;
    }

    @Override
    public double calcolaPerimetro() {
        // Semplificazione: triangolo isoscele
        double lato = Math.sqrt((base/2) * (base/2) + altezza * altezza);
        return base + 2 * lato;
    }
}

// ═══════════════════════════════════════════════════════════════
// 3. CLASSI FINAL
// ═══════════════════════════════════════════════════════════════

/**
 * Classe final - non può essere estesa
 */
final class PuntoImmutabile {
    private final int x;
    private final int y;

    public PuntoImmutabile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public double distanzaDaOrigine() {
        return Math.sqrt(x * x + y * y);
    }

    // Restituisce nuovo oggetto invece di modificare
    public PuntoImmutabile trasla(int dx, int dy) {
        return new PuntoImmutabile(x + dx, y + dy);
    }
}

/**
 * Classe final con configurazione
 */
final class ConfigurazioneApp {
    private final String nomeApp = "MyApplication";
    private final String versione = "1.0.0";
    private final int maxConnessioni = 100;

    public String getNomeApp() { return nomeApp; }
    public String getVersione() { return versione; }
    public int getMaxConnessioni() { return maxConnessioni; }
}

// ═══════════════════════════════════════════════════════════════
// 4. CLASSI INTERNE
// ═══════════════════════════════════════════════════════════════

/**
 * Classe con Member Inner Class
 */
class Universita {
    private String nome;

    public Universita(String nome) {
        this.nome = nome;
    }

    // Member Inner Class (non statica)
    class Dipartimento {
        private String nomeDipartimento;

        public Dipartimento(String nomeDipartimento) {
            this.nomeDipartimento = nomeDipartimento;
        }

        public String getInfoCompleta() {
            // Può accedere ai membri della classe esterna
            return nomeDipartimento + " - " + nome;
        }
    }
}

/**
 * Classe con Static Nested Class
 */
class Azienda {
    private static int contatoreGlobale = 0;

    // Static Nested Class
    static class Dipendente {
        private String nome;
        private String dipartimento;
        private static int contatore = 0;

        public Dipendente(String nome, String dipartimento) {
            this.nome = nome;
            this.dipartimento = dipartimento;
            contatore++;
        }

        public String getInfo() {
            return nome + " - " + dipartimento;
        }

        public static int getContatore() {
            return contatore;
        }
    }
}

// ═══════════════════════════════════════════════════════════════
// 5. CLASSI ENUM
// ═══════════════════════════════════════════════════════════════

/**
 * Enum semplice
 */
enum GiornoSettimana {
    LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI, SABATO, DOMENICA;

    public boolean isWeekend() {
        return this == SABATO || this == DOMENICA;
    }
}

/**
 * Enum con campi e costruttore
 */
enum Pianeta {
    MERCURIO(3.303e23, 2.4397e6),
    VENERE(4.869e24, 6.0518e6),
    TERRA(5.976e24, 6.37814e6),
    MARTE(6.421e23, 3.3972e6);

    private final double massa;   // kg
    private final double raggio;  // metri
    private static final double G = 6.67300E-11;

    Pianeta(double massa, double raggio) {
        this.massa = massa;
        this.raggio = raggio;
    }

    public double getMassa() { return massa; }
    public double getRaggio() { return raggio; }

    public double gravitaSuperficiale() {
        return G * massa / (raggio * raggio);
    }
}

/**
 * Enum con metodi astratti
 */
enum Operazione {
    SOMMA("+") {
        @Override
        public double calcola(double a, double b) {
            return a + b;
        }
    },
    SOTTRAZIONE("-") {
        @Override
        public double calcola(double a, double b) {
            return a - b;
        }
    },
    MOLTIPLICAZIONE("*") {
        @Override
        public double calcola(double a, double b) {
            return a * b;
        }
    },
    DIVISIONE("/") {
        @Override
        public double calcola(double a, double b) {
            return b != 0 ? a / b : 0;
        }
    };

    private final String simbolo;

    Operazione(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbol() { return simbolo; }

    public abstract double calcola(double a, double b);
}

/**
 * Enum per macchina a stati
 */
enum StatoOrdine {
    NUOVO("Ordine creato"),
    IN_ELABORAZIONE("In elaborazione"),
    SPEDITO("Spedito"),
    CONSEGNATO("Consegnato"),
    ANNULLATO("Annullato");

    private final String descrizione;

    StatoOrdine(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() { return descrizione; }

    public StatoOrdine prossimoStato() {
        return switch(this) {
            case NUOVO -> IN_ELABORAZIONE;
            case IN_ELABORAZIONE -> SPEDITO;
            case SPEDITO -> CONSEGNATO;
            default -> this;
        };
    }
}

// ═══════════════════════════════════════════════════════════════
// 6. CLASSI RECORD
// ═══════════════════════════════════════════════════════════════

/**
 * Record semplice
 */
record Point(int x, int y) {
    // Automaticamente genera: costruttore, getter, equals, hashCode, toString
}

/**
 * Record con metodi custom
 */
record Persona2(String nome, String cognome, int eta) {
    // Metodi custom
    public String nomeCompleto() {
        return nome + " " + cognome;
    }

    public boolean isMaggiorenne() {
        return eta >= 18;
    }
}

/**
 * Record con validazione (costruttore compatto)
 */
record Prodotto(String nome, double prezzo, int quantita) {
    // Costruttore compatto per validazione
    public Prodotto {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome non può essere vuoto");
        }
        if (prezzo < 0) {
            throw new IllegalArgumentException("Prezzo non può essere negativo");
        }
        if (quantita < 0) {
            throw new IllegalArgumentException("Quantità non può essere negativa");
        }
    }

    public double totale() {
        return prezzo * quantita;
    }
}

/**
 * Record nested
 */
record Indirizzo(String via, String numero, String citta, String cap) {}
record Cliente(String nome, String email, Indirizzo indirizzo) {}

// ═══════════════════════════════════════════════════════════════
// 7. CLASSI SEALED
// ═══════════════════════════════════════════════════════════════

/**
 * Sealed class per forme
 */
sealed interface Forma2 permits Cerchio2, Quadrato, Rettangolo2 {
    double area();
}

final class Cerchio2 implements Forma2 {
    private final double raggio;

    public Cerchio2(double raggio) { this.raggio = raggio; }
    public double raggio() { return raggio; }

    @Override
    public double area() { return Math.PI * raggio * raggio; }
}

final class Quadrato implements Forma2 {
    private final double lato;

    public Quadrato(double lato) { this.lato = lato; }
    public double lato() { return lato; }

    @Override
    public double area() { return lato * lato; }
}

final class Rettangolo2 implements Forma2 {
    private final double base;
    private final double altezza;

    public Rettangolo2(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    public double base() { return base; }
    public double altezza() { return altezza; }

    @Override
    public double area() { return base * altezza; }
}

/**
 * Sealed class per pagamenti
 */
sealed interface Pagamento permits PagamentoCarta, PagamentoPayPal, PagamentoContanti {}

record PagamentoCarta(String numeroCarta, String intestatario) implements Pagamento {
    public String numeroMascherato() {
        return "****-****-****-" + numeroCarta.substring(numeroCarta.length() - 4);
    }
}

record PagamentoPayPal(String email) implements Pagamento {}

record PagamentoContanti(double importoDisponibile) implements Pagamento {}

// ═══════════════════════════════════════════════════════════════
// 8. INTERFACCE
// ═══════════════════════════════════════════════════════════════

/**
 * Interfaccia con default e static methods
 */
interface Animale {
    // Metodo astratto
    void emettiSuono();
    void mangia();

    // Default method (Java 8+)
    default String getInfo() {
        return "Animale generico";
    }

    // Static method (Java 8+)
    static String getCategoriaGenerale() {
        return "Regno Animale";
    }
}

class Cane implements Animale {
    private String nome;

    public Cane(String nome) { this.nome = nome; }

    @Override
    public void emettiSuono() {
        System.out.println("  " + nome + " fa: Bau bau!");
    }

    @Override
    public void mangia() {
        System.out.println("  " + nome + " mangia crocchette");
    }

    @Override
    public String getInfo() {
        return "Cane di nome " + nome;
    }
}

class Gatto implements Animale {
    private String nome;

    public Gatto(String nome) { this.nome = nome; }

    @Override
    public void emettiSuono() {
        System.out.println("  " + nome + " fa: Miao!");
    }

    @Override
    public void mangia() {
        System.out.println("  " + nome + " mangia pesce");
    }
}

/**
 * Multiple interfaces
 */
interface Avviabile {
    void accendi();
    void spegni();
}

interface Guidabile {
    void guidabile();
}

interface Ricaricabile {
    void ricarica(int percentuale);
}

class Automobile implements Avviabile, Guidabile, Ricaricabile {
    private String modello;
    private int anno;
    private boolean accesa;

    public Automobile(String modello, int anno) {
        this.modello = modello;
        this.anno = anno;
    }

    @Override
    public void accendi() {
        accesa = true;
        System.out.println("  " + modello + " accesa");
    }

    @Override
    public void spegni() {
        accesa = false;
        System.out.println("  " + modello + " spenta");
    }

    @Override
    public void guidabile() {
        System.out.println("  " + modello + " pronta per essere guidata");
    }

    @Override
    public void ricarica(int percentuale) {
        System.out.println("  " + modello + " ricaricata al " + percentuale + "%");
    }
}

// ═══════════════════════════════════════════════════════════════
// 9. CLASSI GENERICHE
// ═══════════════════════════════════════════════════════════════

/**
 * Generic class semplice
 */
class Box<T> {
    private T contenuto;

    public Box(T contenuto) {
        this.contenuto = contenuto;
    }

    public T get() { return contenuto; }
    public void set(T contenuto) { this.contenuto = contenuto; }
}

/**
 * Generic con multiple type parameters
 */
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}

/**
 * Generic collection
 */
class Stack<T> {
    private List<T> elementi = new ArrayList<>();

    public void push(T elemento) {
        elementi.add(elemento);
    }

    public T pop() {
        if (elementi.isEmpty()) {
            throw new IllegalStateException("Stack vuoto");
        }
        return elementi.remove(elementi.size() - 1);
    }

    public T peek() {
        if (elementi.isEmpty()) {
            throw new IllegalStateException("Stack vuoto");
        }
        return elementi.get(elementi.size() - 1);
    }

    public int size() {
        return elementi.size();
    }

    public boolean isEmpty() {
        return elementi.isEmpty();
    }
}

/**
 * Bounded type parameters
 */
class ComparatoreNumeri<T extends Number & Comparable<T>> {
    public T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public T min(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }
}

// ═══════════════════════════════════════════════════════════════
// 10. PATTERN SINGLETON
// ═══════════════════════════════════════════════════════════════

/**
 * Singleton eager initialization
 */
class DatabaseConnection {
    private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    private String stato = "disconnesso";

    private DatabaseConnection() {
        // Costruttore privato
    }

    public static DatabaseConnection getInstance() {
        return INSTANCE;
    }

    public void connetti() {
        stato = "connesso";
        System.out.println("  Database connesso");
    }

    public String getStato() {
        return stato;
    }
}

/**
 * Singleton lazy thread-safe (double-checked locking)
 */
class Logger {
    private static volatile Logger instance;
    private List<String> log = new ArrayList<>();

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String messaggio) {
        log.add(messaggio);
        System.out.println("  LOG: " + messaggio);
    }
}

/**
 * Singleton enum (best practice)
 */
enum ConfigurationManager {
    INSTANCE;

    private Map<String, String> properties = new HashMap<>();

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }
}

// ═══════════════════════════════════════════════════════════════
// 11. CLASSI IMMUTABILI
// ═══════════════════════════════════════════════════════════════

/**
 * Classe immutabile
 */
final class PersonaImmutabile {
    private final String nome;
    private final String cognome;
    private final int eta;
    private final List<String> interessi;

    public PersonaImmutabile(String nome, String cognome, int eta, List<String> interessi) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        // Defensive copy
        this.interessi = new ArrayList<>(interessi);
    }

    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public int getEta() { return eta; }

    // Restituisce copia immutabile
    public List<String> getInteressi() {
        return Collections.unmodifiableList(interessi);
    }
}

/**
 * Classe immutabile con operazioni che restituiscono nuove istanze
 */
final class Money {
    private final double importo;
    private final String valuta;

    public Money(double importo, String valuta) {
        this.importo = importo;
        this.valuta = valuta;
    }

    public double getImporto() { return importo; }
    public String getValuta() { return valuta; }

    // Restituisce nuovo oggetto
    public Money somma(Money altro) {
        if (!this.valuta.equals(altro.valuta)) {
            throw new IllegalArgumentException("Valute diverse");
        }
        return new Money(this.importo + altro.importo, this.valuta);
    }

    public Money sconta(double percentuale) {
        return new Money(importo * (1 - percentuale / 100), valuta);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", importo, valuta);
    }
}

// ═══════════════════════════════════════════════════════════════
// 12. CLASSI UTILITY
// ═══════════════════════════════════════════════════════════════

/**
 * Utility class per stringhe
 */
final class StringUtils {
    // Costruttore privato - non istanziabile
    private StringUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String capitalize(String str) {
        if (isEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String reverse(String str) {
        if (str == null) return null;
        return new StringBuilder(str).reverse().toString();
    }
}

/**
 * Utility class per matematica
 */
final class MathUtils {
    private MathUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n deve essere >= 0");
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

/**
 * Utility class per validazione
 */
final class ValidationUtils {
    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidUrl(String url) {
        if (url == null) return false;
        return url.matches("^https?://.*");
    }
}
