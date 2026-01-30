package com.corso.samples.oop;

/**
 * Esempio completo sui livelli di visibilità di METODI e PROPRIETÀ in Java
 * 
 * MODIFICATORI DI ACCESSO (dal più permissivo al più restrittivo):
 * 
 * 1. PUBLIC - Accessibile ovunque
 * 2. PROTECTED - Accessibile nello stesso package e nelle sottoclassi (anche in altri package)
 * 3. DEFAULT (package-private) - Accessibile solo nello stesso package
 * 4. PRIVATE - Accessibile solo all'interno della stessa classe
 */
public class MetodiProprietaDemo {
    
    // ═══════════════════════════════════════════════════════════════
    // PROPRIETÀ (CAMPI/FIELDS) CON DIVERSI LIVELLI DI VISIBILITÀ
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * PUBLIC - Visibile ovunque
     * 
     * USO: Costanti globali, raramente per campi mutabili
     * BEST PRACTICE: Usare solo per costanti (static final)
     * RISCHIO: Viola il principio di incapsulamento
     */
    public static final String COSTANTE_PUBBLICA = "Visibile ovunque";
    public String campoPubblico = "Campo pubblico (NON consigliato per dati mutabili)";
    
    /**
     * PROTECTED - Visibile nello stesso package e nelle sottoclassi
     * 
     * USO: Campi che le sottoclassi devono poter modificare
     * BEST PRACTICE: Usare quando si progetta per ereditarietà
     * NOTA: Più permissivo di default per le sottoclassi in altri package
     */
    protected String campoProtetto = "Accessibile in package e sottoclassi";
    protected int valoreProtetto = 100;
    
    /**
     * DEFAULT (nessun modificatore) - Visibile solo nello stesso package
     * 
     * USO: Campi condivisi tra classi dello stesso package
     * BEST PRACTICE: Utile per package ben progettati e coesi
     * NOTA: Non accessibile da sottoclassi in altri package
     */
    String campoDefault = "Visibile solo nel package com.example";
    int valoreDefault = 50;
    
    /**
     * PRIVATE - Visibile solo all'interno di questa classe
     * 
     * USO: Implementazione interna, dati sensibili
     * BEST PRACTICE: SEMPRE usare private per i campi (incapsulamento)
     * ACCESSO: Tramite getter/setter pubblici
     */
    private String campoPrivato = "Accessibile solo in questa classe";
    private int valorePrivato = 10;
    private double saldo = 1000.0;
    
    // Campi statici con diverse visibilità
    public static int contatorePublico = 0;
    protected static int contatoreProtetto = 0;
    static int contatoreDefault = 0;
    private static int contatorePrivato = 0;
    
    // ═══════════════════════════════════════════════════════════════
    // COSTRUTTORI CON DIVERSE VISIBILITÀ
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Costruttore PUBLIC - Chiunque può creare istanze
     */
    public MetodiProprietaDemo() {
        System.out.println("Costruttore pubblico chiamato");
    }
    
    /**
     * Costruttore PROTECTED - Solo package e sottoclassi
     */
    protected MetodiProprietaDemo(String valore) {
        this.campoProtetto = valore;
        System.out.println("Costruttore protetto chiamato");
    }
    
    /**
     * Costruttore DEFAULT - Solo stesso package
     */
    MetodiProprietaDemo(int valore) {
        this.valoreDefault = valore;
        System.out.println("Costruttore package-private chiamato");
    }
    
    /**
     * Costruttore PRIVATE - Solo uso interno (pattern Singleton, Factory)
     */
    private MetodiProprietaDemo(String campo, int valore) {
        this.campoPrivato = campo;
        this.valorePrivato = valore;
        System.out.println("Costruttore privato chiamato");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // METODI CON DIVERSE VISIBILITÀ
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * METODO PUBLIC - Accessibile ovunque
     * 
     * USO: API pubblica della classe, interfaccia esterna
     * ESEMPIO: Metodi di servizio, operazioni principali
     */
    public void metodoPubblico() {
        System.out.println("  ✓ Metodo pubblico: accessibile ovunque");
        // Può chiamare tutti i metodi privati
        metodoPrivato();
    }
    
    public String ottieniInformazioni() {
        return "Info pubbliche: " + campoPubblico;
    }
    
    public int calcolaQuadrato(int numero) {
        return numero * numero;
    }
    
    /**
     * METODO PROTECTED - Accessibile in package e sottoclassi
     * 
     * USO: Metodi che le sottoclassi possono override o estendere
     * ESEMPIO: Template method pattern, hook methods
     */
    protected void metodoProtetto() {
        System.out.println("  ✓ Metodo protected: package e sottoclassi");
    }
    
    protected int calcolaBonus(int base) {
        return base * valoreProtetto / 100;
    }
    
    protected void elaboraDati() {
        System.out.println("  Elaborazione base dei dati");
        // Le sottoclassi possono fare override
    }
    
    /**
     * METODO DEFAULT (package-private) - Solo stesso package
     * 
     * USO: Utility interne al package, supporto tra classi correlate
     * ESEMPIO: Helper methods condivisi nel package
     */
    void metodoDefault() {
        System.out.println("  ✓ Metodo default: solo package");
    }
    
    int calcolaInterno(int a, int b) {
        return a * b + valoreDefault;
    }
    
    void resetDatiInterni() {
        valoreDefault = 0;
        campoDefault = "";
    }
    
    /**
     * METODO PRIVATE - Solo all'interno di questa classe
     * 
     * USO: Implementazione interna, helper methods
     * ESEMPIO: Validazione, calcoli interni, utility
     */
    private void metodoPrivato() {
        System.out.println("    → Metodo privato: solo interno");
    }
    
    private boolean valida(String input) {
        return input != null && !input.isEmpty();
    }
    
    private int calcolaCommissione(double importo) {
        return (int)(importo * 0.05);
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTER E SETTER - PATTERN DI INCAPSULAMENTO
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * GETTER PUBBLICI - Forniscono accesso in LETTURA ai campi privati
     * 
     * VANTAGGI:
     * - Controllo su come i dati vengono esposti
     * - Possibilità di calcolare valori on-the-fly
     * - Logging, validazione, trasformazione
     */
    public String getCampoPrivato() {
        // Può includere logica aggiuntiva
        System.out.println("  [LOG] Accesso a campoPrivato");
        return campoPrivato;
    }
    
    public int getValorePrivato() {
        return valorePrivato;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    /**
     * SETTER PUBBLICI - Forniscono accesso in SCRITTURA ai campi privati
     * 
     * VANTAGGI:
     * - Validazione dei dati in ingresso
     * - Notifiche di cambiamento
     * - Conversione/normalizzazione
     * - Protezione dell'integrità dei dati
     */
    public void setCampoPrivato(String valore) {
        // Validazione
        if (valida(valore)) {
            this.campoPrivato = valore;
            System.out.println("  [LOG] campoPrivato aggiornato");
        } else {
            System.out.println("  [ERRORE] Valore non valido!");
        }
    }
    
    public void setValorePrivato(int valore) {
        // Validazione con range
        if (valore >= 0 && valore <= 1000) {
            this.valorePrivato = valore;
        } else {
            throw new IllegalArgumentException("Valore deve essere tra 0 e 1000");
        }
    }
    
    public void setSaldo(double nuovoSaldo) {
        // Business logic
        if (nuovoSaldo < 0) {
            throw new IllegalArgumentException("Saldo non può essere negativo");
        }
        this.saldo = nuovoSaldo;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // METODI STATICI CON DIVERSE VISIBILITÀ
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Metodi statici seguono le stesse regole di visibilità
     */
    public static void metodoStaticoPubblico() {
        System.out.println("Metodo statico pubblico");
        contatorePublico++;
    }
    
    protected static void metodoStaticoProtetto() {
        System.out.println("Metodo statico protetto");
        contatoreProtetto++;
    }
    
    static void metodoStaticoDefault() {
        System.out.println("Metodo statico default");
        contatoreDefault++;
    }
    
    private static void metodoStaticoPrivato() {
        System.out.println("Metodo statico privato");
        contatorePrivato++;
    }
    
    // Factory method che usa costruttore privato
    public static MetodiProprietaDemo creaIstanzaSpeciale() {
        return new MetodiProprietaDemo("Speciale", 999);
    }
    
    // ═══════════════════════════════════════════════════════════════
    // METODI FINALI E ABSTRACT (non possono essere modificati)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * FINAL - Non può essere sovrascritto nelle sottoclassi
     */
    public final void metodoFinale() {
        System.out.println("Questo metodo non può essere sovrascritto");
    }
    
    protected final int calcolaValore() {
        return valorePrivato * 10;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // PASSAGGIO PARAMETRI AI METODI - ESEMPI COMPLETI
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 1: PASSAGGIO DI TIPI PRIMITIVI (PASS BY VALUE)  ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * I TIPI PRIMITIVI vengono SEMPRE passati PER VALORE:
     * - Una COPIA del valore viene passata al metodo
     * - Modifiche al parametro NON influenzano la variabile originale
     * - I tipi primitivi: byte, short, int, long, float, double, char, boolean
     */
    public void modificaPrimitivo(int numero) {
        System.out.println("  Valore ricevuto: " + numero);
        numero = numero * 2; // Modifica solo la copia locale
        System.out.println("  Valore modificato nel metodo: " + numero);
    }
    
    public void testPassaggioPrimitivi() {
        System.out.println("\n【 PASSAGGIO TIPI PRIMITIVI 】");
        System.out.println("─".repeat(50));
        
        int valorOriginale = 10;
        System.out.println("Valore PRIMA della chiamata: " + valorOriginale);
        modificaPrimitivo(valorOriginale);
        System.out.println("Valore DOPO la chiamata: " + valorOriginale);
        System.out.println("➜ Il valore originale NON è cambiato!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 2: PASSAGGIO DI OGGETTI (PASS BY VALUE OF REF)  ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * Gli OGGETTI vengono passati PER VALORE DEL RIFERIMENTO:
     * - Il riferimento (indirizzo) viene copiato
     * - Modifiche all'OGGETTO puntato SONO visibili
     * - Riassegnare il parametro NON influenza l'originale
     */
    public void modificaOggetto(StringBuilder sb) {
        System.out.println("  Riferimento ricevuto: " + sb);
        sb.append(" - modificato"); // Modifica l'oggetto puntato
        System.out.println("  Oggetto modificato: " + sb);
    }
    
    public void riassegnaOggetto(StringBuilder sb) {
        System.out.println("  Riferimento ricevuto: " + sb);
        sb = new StringBuilder("Nuovo oggetto"); // Riassegna solo la copia locale
        System.out.println("  Riferimento riassegnato: " + sb);
    }
    
    public void testPassaggioOggetti() {
        System.out.println("\n【 PASSAGGIO OGGETTI 】");
        System.out.println("─".repeat(50));
        
        StringBuilder originale = new StringBuilder("Originale");
        System.out.println("Oggetto PRIMA: " + originale);
        
        modificaOggetto(originale);
        System.out.println("Oggetto DOPO modificaOggetto: " + originale);
        System.out.println("➜ L'oggetto È stato modificato!");
        
        riassegnaOggetto(originale);
        System.out.println("Oggetto DOPO riassegnaOggetto: " + originale);
        System.out.println("➜ L'oggetto originale NON è cambiato!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 3: PASSAGGIO DI STRINGHE (OGGETTI IMMUTABILI)   ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * Le STRINGHE sono IMMUTABILI:
     * - Ogni modifica crea una NUOVA stringa
     * - Il riferimento originale NON viene influenzato
     * - Comportamento simile ai primitivi
     */
    public void modificaStringa(String s) {
        System.out.println("  Stringa ricevuta: '" + s + "'");
        s = s + " - modificata"; // Crea una NUOVA stringa
        System.out.println("  Stringa nel metodo: '" + s + "'");
    }
    
    public void testPassaggioStringhe() {
        System.out.println("\n【 PASSAGGIO STRINGHE (IMMUTABILI) 】");
        System.out.println("─".repeat(50));
        
        String originale = "Originale";
        System.out.println("Stringa PRIMA: '" + originale + "'");
        modificaStringa(originale);
        System.out.println("Stringa DOPO: '" + originale + "'");
        System.out.println("➜ La stringa originale NON è cambiata!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 4: PASSAGGIO DI ARRAY                            ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * Gli ARRAY sono OGGETTI:
     * - Modifiche agli elementi SONO visibili
     * - Riassegnare l'array NON influenza l'originale
     */
    public void modificaArray(int[] arr) {
        System.out.println("  Array ricevuto, lunghezza: " + arr.length);
        if (arr.length > 0) {
            arr[0] = 999; // Modifica l'elemento
        }
    }
    
    public void riassegnaArray(int[] arr) {
        System.out.println("  Array ricevuto, lunghezza: " + arr.length);
        arr = new int[]{100, 200}; // Riassegna solo il riferimento locale
        System.out.println("  Nuovo array nel metodo, lunghezza: " + arr.length);
    }
    
    public void testPassaggioArray() {
        System.out.println("\n【 PASSAGGIO ARRAY 】");
        System.out.println("─".repeat(50));
        
        int[] numeri = {1, 2, 3, 4, 5};
        System.out.println("Array PRIMA: " + java.util.Arrays.toString(numeri));
        
        modificaArray(numeri);
        System.out.println("Array DOPO modificaArray: " + java.util.Arrays.toString(numeri));
        System.out.println("➜ Gli elementi SONO stati modificati!");
        
        riassegnaArray(numeri);
        System.out.println("Array DOPO riassegnaArray: " + java.util.Arrays.toString(numeri));
        System.out.println("➜ L'array originale NON è cambiato!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 5: PASSAGGIO DI WRAPPER (Integer, Double, ...)  ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * I WRAPPER sono IMMUTABILI:
     * - Comportamento simile alle stringhe
     * - Modifiche creano nuovi oggetti
     */
    public void modificaInteger(Integer num) {
        System.out.println("  Integer ricevuto: " + num);
        num = num * 2; // Crea un NUOVO Integer (autoboxing)
        System.out.println("  Integer nel metodo: " + num);
    }
    
    public void testPassaggioWrapper() {
        System.out.println("\n【 PASSAGGIO WRAPPER (IMMUTABILI) 】");
        System.out.println("─".repeat(50));
        
        Integer numero = 10;
        System.out.println("Integer PRIMA: " + numero);
        modificaInteger(numero);
        System.out.println("Integer DOPO: " + numero);
        System.out.println("➜ L'Integer originale NON è cambiato!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 6: PASSAGGIO DI OGGETTI MUTABILI CUSTOM         ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    static class Persona {
        String nome;
        int eta;
        
        Persona(String nome, int eta) {
            this.nome = nome;
            this.eta = eta;
        }
        
        @Override
        public String toString() {
            return nome + " (" + eta + " anni)";
        }
    }
    
    public void modificaPersona(Persona p) {
        System.out.println("  Persona ricevuta: " + p);
        p.eta = p.eta + 1; // Modifica l'oggetto puntato
        System.out.println("  Persona modificata: " + p);
    }
    
    public void riassegnaPersona(Persona p) {
        System.out.println("  Persona ricevuta: " + p);
        p = new Persona("Nuovo", 0); // Riassegna solo il riferimento locale
        System.out.println("  Persona riassegnata: " + p);
    }
    
    public void testPassaggioOggettiCustom() {
        System.out.println("\n【 PASSAGGIO OGGETTI MUTABILI CUSTOM 】");
        System.out.println("─".repeat(50));
        
        Persona persona = new Persona("Mario", 30);
        System.out.println("Persona PRIMA: " + persona);
        
        modificaPersona(persona);
        System.out.println("Persona DOPO modificaPersona: " + persona);
        System.out.println("➜ L'oggetto È stato modificato!");
        
        riassegnaPersona(persona);
        System.out.println("Persona DOPO riassegnaPersona: " + persona);
        System.out.println("➜ L'oggetto originale NON è cambiato!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 7: PASSAGGIO DI NULL                             ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    public void gestisciNull(String s) {
        if (s == null) {
            System.out.println("  ⚠ Parametro è null");
        } else {
            System.out.println("  Parametro: '" + s + "'");
        }
    }
    
    public void testPassaggioNull() {
        System.out.println("\n【 PASSAGGIO DI NULL 】");
        System.out.println("─".repeat(50));
        
        String valoreNull = null;
        gestisciNull(valoreNull);
        gestisciNull("Valore valido");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 8: VARARGS (PARAMETRI VARIABILI)                ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * VARARGS permette di passare un numero variabile di parametri:
     * - Sintassi: tipo... nomeParametro
     * - Trattato internamente come array
     * - DEVE essere l'ultimo parametro
     */
    public int somma(int... numeri) {
        System.out.println("  Ricevuti " + numeri.length + " numeri");
        int totale = 0;
        for (int n : numeri) {
            totale += n;
        }
        return totale;
    }
    
    public void stampaMessaggi(String prefisso, String... messaggi) {
        for (String msg : messaggi) {
            System.out.println("  " + prefisso + ": " + msg);
        }
    }
    
    public void testVarargs() {
        System.out.println("\n【 VARARGS (PARAMETRI VARIABILI) 】");
        System.out.println("─".repeat(50));
        
        System.out.println("somma() = " + somma()); // Zero parametri
        System.out.println("somma(5) = " + somma(5)); // Un parametro
        System.out.println("somma(1, 2, 3) = " + somma(1, 2, 3)); // Più parametri
        System.out.println("somma(1, 2, 3, 4, 5) = " + somma(1, 2, 3, 4, 5));
        
        System.out.println("\nstampaMessaggi:");
        stampaMessaggi("INFO", "Messaggio 1", "Messaggio 2", "Messaggio 3");
        
        // Può anche passare un array
        int[] numeri = {10, 20, 30};
        System.out.println("somma(array) = " + somma(numeri));
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 9: AUTOBOXING E UNBOXING                        ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * AUTOBOXING: conversione automatica da primitivo a wrapper
     * UNBOXING: conversione automatica da wrapper a primitivo
     */
    public void accettaPrimitivo(int n) {
        System.out.println("  Metodo con primitivo: " + n);
    }
    
    public void accettaWrapper(Integer n) {
        System.out.println("  Metodo con wrapper: " + n);
    }
    
    public void testAutoboxingUnboxing() {
        System.out.println("\n【 AUTOBOXING E UNBOXING 】");
        System.out.println("─".repeat(50));
        
        int primitivo = 42;
        Integer wrapper = 100;
        
        // Autoboxing: primitivo → wrapper
        accettaWrapper(primitivo);
        
        // Unboxing: wrapper → primitivo
        accettaPrimitivo(wrapper);
        
        // Attenzione a NullPointerException con unboxing
        Integer nullWrapper = null;
        try {
            accettaPrimitivo(nullWrapper); // NPE!
        } catch (NullPointerException e) {
            System.out.println("  ⚠ NullPointerException durante unboxing!");
        }
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 10: OGGETTI CON RIFERIMENTI INTERNI             ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    static class ContoCorrente {
        String numero;
        double saldo;
        java.util.List<String> transazioni;
        
        ContoCorrente(String numero, double saldo) {
            this.numero = numero;
            this.saldo = saldo;
            this.transazioni = new java.util.ArrayList<>();
        }
        
        @Override
        public String toString() {
            return "Conto " + numero + ", Saldo: €" + saldo + 
                   ", Transazioni: " + transazioni.size();
        }
    }
    
    public void aggiungiTransazione(ContoCorrente conto, String transazione) {
        conto.transazioni.add(transazione); // Modifica la lista interna
        System.out.println("  Transazione aggiunta: " + transazione);
    }
    
    public void testOggettiConRiferimentiInterni() {
        System.out.println("\n【 OGGETTI CON RIFERIMENTI INTERNI 】");
        System.out.println("─".repeat(50));
        
        ContoCorrente conto = new ContoCorrente("IT12345", 1000.0);
        System.out.println("Conto PRIMA: " + conto);
        
        aggiungiTransazione(conto, "Deposito €100");
        aggiungiTransazione(conto, "Prelievo €50");
        
        System.out.println("Conto DOPO: " + conto);
        System.out.println("➜ Le modifiche agli oggetti interni SONO visibili!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 11: PASSAGGIO DI PARAMETRI MULTIPLI             ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    public void metodoMultipli(int primitivo, String immutabile, 
                               StringBuilder mutabile, int[] array) {
        System.out.println("  Ricevuti parametri multipli:");
        primitivo = 100;
        immutabile = "Modificato";
        mutabile.append(" - modificato");
        array[0] = 999;
    }
    
    public void testParametriMultipli() {
        System.out.println("\n【 PARAMETRI MULTIPLI DI DIVERSI TIPI 】");
        System.out.println("─".repeat(50));
        
        int num = 10;
        String str = "Originale";
        StringBuilder sb = new StringBuilder("StringBuilder");
        int[] arr = {1, 2, 3};
        
        System.out.println("PRIMA:");
        System.out.println("  int: " + num);
        System.out.println("  String: " + str);
        System.out.println("  StringBuilder: " + sb);
        System.out.println("  array: " + java.util.Arrays.toString(arr));
        
        metodoMultipli(num, str, sb, arr);
        
        System.out.println("DOPO:");
        System.out.println("  int: " + num + " (NON modificato)");
        System.out.println("  String: " + str + " (NON modificato)");
        System.out.println("  StringBuilder: " + sb + " (MODIFICATO)");
        System.out.println("  array: " + java.util.Arrays.toString(arr) + " (MODIFICATO)");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 12: PASSAGGIO DI COLLEZIONI                     ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    public void modificaLista(java.util.List<String> lista) {
        lista.add("Elemento aggiunto nel metodo");
        System.out.println("  Lista nel metodo: " + lista);
    }
    
    public void riassegnaLista(java.util.List<String> lista) {
        lista = new java.util.ArrayList<>();
        lista.add("Nuova lista");
        System.out.println("  Lista riassegnata nel metodo: " + lista);
    }
    
    public void testPassaggioCollezioni() {
        System.out.println("\n【 PASSAGGIO DI COLLEZIONI 】");
        System.out.println("─".repeat(50));
        
        java.util.List<String> lista = new java.util.ArrayList<>();
        lista.add("Elemento originale");
        
        System.out.println("Lista PRIMA: " + lista);
        
        modificaLista(lista);
        System.out.println("Lista DOPO modificaLista: " + lista);
        System.out.println("➜ L'elemento È stato aggiunto!");
        
        riassegnaLista(lista);
        System.out.println("Lista DOPO riassegnaLista: " + lista);
        System.out.println("➜ La lista originale NON è cambiata!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 13: METODI CHE RESTITUISCONO VALORI             ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * Per "modificare" valori immutabili, bisogna RESTITUIRE il nuovo valore
     */
    public int raddoppia(int n) {
        return n * 2;
    }
    
    public String concatena(String s, String suffisso) {
        return s + suffisso;
    }
    
    public Integer incrementa(Integer n) {
        return n + 1;
    }
    
    public void testMetodiConReturn() {
        System.out.println("\n【 METODI CHE RESTITUISCONO VALORI 】");
        System.out.println("─".repeat(50));
        
        int num = 10;
        System.out.println("num PRIMA: " + num);
        num = raddoppia(num); // Assegna il valore restituito
        System.out.println("num DOPO: " + num);
        
        String str = "Ciao";
        System.out.println("str PRIMA: " + str);
        str = concatena(str, " Mondo"); // Assegna il valore restituito
        System.out.println("str DOPO: " + str);
        
        Integer wrapper = 5;
        System.out.println("wrapper PRIMA: " + wrapper);
        wrapper = incrementa(wrapper); // Assegna il valore restituito
        System.out.println("wrapper DOPO: " + wrapper);
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  SCENARIO 14: DEFENSIVE COPYING                           ║
     * ╚════════════════════════════════════════════════════════════╝
     * 
     * Per proteggere dati interni da modifiche esterne
     */
    public void modificaArrayConCopia(int[] arr) {
        // Crea una copia per non modificare l'originale
        int[] copia = java.util.Arrays.copyOf(arr, arr.length);
        copia[0] = 999;
        System.out.println("  Copia modificata: " + java.util.Arrays.toString(copia));
    }
    
    public void testDefensiveCopying() {
        System.out.println("\n【 DEFENSIVE COPYING 】");
        System.out.println("─".repeat(50));
        
        int[] numeri = {1, 2, 3};
        System.out.println("Array PRIMA: " + java.util.Arrays.toString(numeri));
        modificaArrayConCopia(numeri);
        System.out.println("Array DOPO: " + java.util.Arrays.toString(numeri));
        System.out.println("➜ L'array originale è protetto!");
    }
    
    /**
     * ╔════════════════════════════════════════════════════════════╗
     * ║  METODO PRINCIPALE PER TEST PASSAGGIO PARAMETRI           ║
     * ╚════════════════════════════════════════════════════════════╝
     */
    public void eseguiTuttiTestPassaggioParametri() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ESEMPI COMPLETI: PASSAGGIO PARAMETRI AI METODI ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        testPassaggioPrimitivi();
        testPassaggioOggetti();
        testPassaggioStringhe();
        testPassaggioArray();
        testPassaggioWrapper();
        testPassaggioOggettiCustom();
        testPassaggioNull();
        testVarargs();
        testAutoboxingUnboxing();
        testOggettiConRiferimentiInterni();
        testParametriMultipli();
        testPassaggioCollezioni();
        testMetodiConReturn();
        testDefensiveCopying();
        
        stampaRiepilogoPassaggioParametri();
    }
    
    private void stampaRiepilogoPassaggioParametri() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   RIEPILOGO: PASSAGGIO PARAMETRI IN JAVA         ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        System.out.println("\n【 REGOLA FONDAMENTALE 】");
        System.out.println("─".repeat(50));
        System.out.println("Java usa SEMPRE il PASSAGGIO PER VALORE:");
        System.out.println("  • Per primitivi: si passa una COPIA del valore");
        System.out.println("  • Per oggetti: si passa una COPIA del riferimento");
        
        System.out.println("\n【 COSA SUCCEDE AI PARAMETRI 】");
        System.out.println("─".repeat(50));
        System.out.println("┌─────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Tipo Parametro      │ Modifica Obj │ Riassegna    │");
        System.out.println("├─────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Primitivi           │     N/A      │   NO effetto │");
        System.out.println("│ String (immutabile) │     N/A      │   NO effetto │");
        System.out.println("│ Wrapper (immutabili)│     N/A      │   NO effetto │");
        System.out.println("│ Oggetti mutabili    │   ✓ Visibile │   NO effetto │");
        System.out.println("│ Array               │   ✓ Visibile │   NO effetto │");
        System.out.println("│ Collezioni          │   ✓ Visibile │   NO effetto │");
        System.out.println("└─────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n【 BEST PRACTICES 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ Per modificare primitivi/immutabili: RESTITUIRE il nuovo valore");
        System.out.println("✓ Per proteggere dati: usare DEFENSIVE COPYING");
        System.out.println("✓ Controllare sempre parametri NULL");
        System.out.println("✓ Varargs: DEVE essere l'ultimo parametro");
        System.out.println("✓ Attenzione a NullPointerException con unboxing");
        System.out.println("✓ Documentare se il metodo modifica gli oggetti passati");
        
        System.out.println("\n【 PATTERN COMUNI 】");
        System.out.println("─".repeat(50));
        System.out.println("1. BUILDER PATTERN: return this; per chiamate concatenate");
        System.out.println("2. IMMUTABLE OBJECTS: restituire nuove istanze");
        System.out.println("3. FACTORY METHODS: creare e restituire oggetti");
        System.out.println("4. CALLBACK METHODS: passare oggetti per comunicare risultati");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // ESEMPIO DI USO NELLA STESSA CLASSE
    // ═══════════════════════════════════════════════════════════════
    
    public void dimostraAccessoCompleto() {
        System.out.println("\n【 ACCESSO DALL'INTERNO DELLA CLASSE 】");
        System.out.println("─".repeat(50));
        
        System.out.println("Una classe può accedere a TUTTI i suoi membri:");
        
        // Accesso ai campi
        System.out.println("  Campo pubblico: " + campoPubblico);
        System.out.println("  Campo protetto: " + campoProtetto);
        System.out.println("  Campo default: " + campoDefault);
        System.out.println("  Campo privato: " + campoPrivato);
        
        // Chiamata metodi
        metodoPubblico();
        metodoProtetto();
        metodoDefault();
        metodoPrivato();
        
        // Accesso diretto vs getter
        System.out.println("\nAccesso diretto: " + valorePrivato);
        System.out.println("Tramite getter: " + getValorePrivato());
    }
    
    // ═══════════════════════════════════════════════════════════════
    // ESEMPI DI UTILIZZO
    // ═══════════════════════════════════════════════════════════════
    
    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   VISIBILITÀ DI METODI E PROPRIETÀ IN JAVA        ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        MetodiProprietaDemo demo = new MetodiProprietaDemo();
        demo.dimostraAccessoCompleto();
        
        // Accesso dall'esterno (stessa classe nel main)
        accessoDalMain(demo);
        
        // Test da altra classe nello stesso package
        testStessoPackage();
        
        // Riepilogo
        stampaRiepilogo();
        
        // ═══════════════════════════════════════════════════════════════
        // SEZIONE: TEST PASSAGGIO PARAMETRI
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n\n");
        demo.eseguiTuttiTestPassaggioParametri();
    }
    
    private static void accessoDalMain(MetodiProprietaDemo demo) {
        System.out.println("\n【 ACCESSO DA MAIN (STESSA CLASSE) 】");
        System.out.println("─".repeat(50));
        
        // Può accedere a tutto perché è nella stessa classe
        System.out.println("Campo pubblico: " + demo.campoPubblico);
        System.out.println("Campo protetto: " + demo.campoProtetto);
        System.out.println("Campo default: " + demo.campoDefault);
        System.out.println("Campo privato: " + demo.campoPrivato);
        
        demo.metodoPubblico();
        demo.metodoProtetto();
        demo.metodoDefault();
        demo.metodoPrivato();
    }
    
    private static void testStessoPackage() {
        System.out.println("\n【 TEST DA CLASSE NELLO STESSO PACKAGE 】");
        System.out.println("─".repeat(50));
        
        ClasseStessoPackage test = new ClasseStessoPackage();
        test.testAccesso();
    }
    
    private static void stampaRiepilogo() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   RIEPILOGO MODIFICATORI DI ACCESSO               ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        System.out.println("\n┌──────────────┬────────┬─────────┬────────────┬──────┐");
        System.out.println("│ Modificatore │ Classe │ Package │ Sottoclasse│ Mondo│");
        System.out.println("├──────────────┼────────┼─────────┼────────────┼──────┤");
        System.out.println("│ public       │   ✓    │    ✓    │     ✓      │  ✓   │");
        System.out.println("│ protected    │   ✓    │    ✓    │     ✓      │  ✗   │");
        System.out.println("│ default      │   ✓    │    ✓    │     ✗      │  ✗   │");
        System.out.println("│ private      │   ✓    │    ✗    │     ✗      │  ✗   │");
        System.out.println("└──────────────┴────────┴─────────┴────────────┴──────┘");
        
        System.out.println("\n【 BEST PRACTICES 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ CAMPI: Sempre private (incapsulamento)");
        System.out.println("✓ GETTER/SETTER: Public per accesso controllato");
        System.out.println("✓ METODI PUBBLICI: Interfaccia chiara e stabile");
        System.out.println("✓ METODI PRIVATI: Implementazione modificabile");
        System.out.println("✓ PROTECTED: Solo per ereditarietà pianificata");
        System.out.println("✓ DEFAULT: Per package coeso e ben progettato");
        
        System.out.println("\n【 PRINCIPI DI PROGETTAZIONE 】");
        System.out.println("─".repeat(50));
        System.out.println("1. INCAPSULAMENTO: Nascondi i dettagli implementativi");
        System.out.println("2. MINIMO PRIVILEGIO: Usa la visibilità più restrittiva");
        System.out.println("3. STABILITÀ API: Public methods = contratto pubblico");
        System.out.println("4. FLESSIBILITÀ: Private = libertà di cambiare");
        System.out.println("5. PROTEZIONE DATI: Validazione nei setter");
    }
}

/**
 * CLASSE NELLO STESSO PACKAGE
 * Dimostra l'accesso da una classe diversa nello stesso package
 */
class ClasseStessoPackage {
    
    public void testAccesso() {
        System.out.println("Accesso da ClasseStessoPackage (stesso package):");
        
        MetodiProprietaDemo demo = new MetodiProprietaDemo();
        
        // ✓ PUBLIC - Accessibile
        System.out.println("  ✓ Campo pubblico: " + demo.campoPubblico);
        demo.metodoPubblico();
        
        // ✓ PROTECTED - Accessibile (stesso package)
        System.out.println("  ✓ Campo protetto: " + demo.campoProtetto);
        demo.metodoProtetto();
        
        // ✓ DEFAULT - Accessibile (stesso package)
        System.out.println("  ✓ Campo default: " + demo.campoDefault);
        demo.metodoDefault();
        
        // ✗ PRIVATE - NON accessibile
        // System.out.println(demo.campoPrivato); // ERRORE!
        // demo.metodoPrivato(); // ERRORE!
        
        // Ma può usare getter/setter pubblici
        System.out.println("  ✓ Via getter: " + demo.getCampoPrivato());
        demo.setCampoPrivato("Nuovo valore");
    }
}

/**
 * SOTTOCLASSE NELLO STESSO PACKAGE
 * Dimostra l'accesso tramite ereditarietà
 */
class SottoclasseStessoPackage extends MetodiProprietaDemo {
    
    public void testAccessoEreditato() {
        System.out.println("\n【 SOTTOCLASSE NELLO STESSO PACKAGE 】");
        System.out.println("─".repeat(50));
        
        // ✓ PUBLIC - Accessibile ed ereditato
        System.out.println("  ✓ Campo pubblico: " + campoPubblico);
        metodoPubblico();
        
        // ✓ PROTECTED - Accessibile ed ereditato
        System.out.println("  ✓ Campo protetto: " + campoProtetto);
        metodoProtetto();
        
        // ✓ DEFAULT - Accessibile (stesso package)
        System.out.println("  ✓ Campo default: " + campoDefault);
        metodoDefault();
        
        // ✗ PRIVATE - NON accessibile (nemmeno nelle sottoclassi!)
        // System.out.println(campoPrivato); // ERRORE!
        // metodoPrivato(); // ERRORE!
        
        // Può usare getter/setter ereditati
        System.out.println("  ✓ Via getter ereditato: " + getCampoPrivato());
    }
    
    /**
     * Override di metodo protected
     */
    @Override
    protected void elaboraDati() {
        System.out.println("  Elaborazione ESTESA nella sottoclasse");
        super.elaboraDati(); // Chiama implementazione base
        System.out.println("  Elaborazione aggiuntiva completata");
    }
    
    /**
     * Può accedere a campi protected per modificarli
     */
    public void modificaCampoProtetto() {
        campoProtetto = "Modificato dalla sottoclasse";
        valoreProtetto = 200;
    }
}

/**
 * CLASSE DI ESEMPIO: Conto Bancario
 * Dimostra l'uso pratico dell'incapsulamento
 */
class ContoBancario {
    
    // ✓ CAMPI PRIVATI - Nascosti dal mondo esterno
    private String numeroConto;
    private String intestatario;
    private double saldo;
    private String pin;
    
    // ✓ COSTRUTTORE PUBBLICO
    public ContoBancario(String numeroConto, String intestatario, String pin) {
        this.numeroConto = numeroConto;
        this.intestatario = intestatario;
        this.saldo = 0.0;
        this.pin = pin;
    }
    
    // ✓ GETTER PUBBLICI - Solo lettura per campi sensibili
    public String getNumeroConto() {
        return numeroConto;
    }
    
    public String getIntestatario() {
        return intestatario;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    // ✗ NESSUN GETTER per PIN - Massima sicurezza
    
    // ✓ METODI PUBBLICI - API del conto
    public boolean deposita(double importo) {
        if (validaImporto(importo)) {
            saldo += importo;
            registraOperazione("DEPOSITO", importo);
            return true;
        }
        return false;
    }
    
    public boolean preleva(double importo, String pinInserito) {
        if (validaPin(pinInserito) && validaImporto(importo) && saldo >= importo) {
            saldo -= importo;
            registraOperazione("PRELIEVO", importo);
            return true;
        }
        return false;
    }
    
    // ✓ METODI PRIVATI - Implementazione nascosta
    private boolean validaImporto(double importo) {
        return importo > 0 && importo <= 10000;
    }
    
    private boolean validaPin(String pinInserito) {
        return this.pin.equals(pinInserito);
    }
    
    private void registraOperazione(String tipo, double importo) {
        System.out.println("  [LOG] " + tipo + ": €" + importo);
    }
}
