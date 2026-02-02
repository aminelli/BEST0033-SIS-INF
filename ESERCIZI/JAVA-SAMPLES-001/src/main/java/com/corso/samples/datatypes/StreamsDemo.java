package com.corso.samples.datatypes;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.*;

/**
 * Esempio completo e avanzato sull'uso degli Streams con Java Collections
 * Include:
 * - Operazioni base e intermedie
 * - Operazioni terminali
 * - Collectors avanzati
 * - Stream paralleli
 * - Stream infiniti
 * - FlatMap e operazioni complesse
 * - Stream di tipi primitivi
 * - Custom collectors
 * - Pattern avanzati e best practices
 */
public class StreamsDemo {

    public static void sample() {
        StreamsDemo demo = new StreamsDemo();
        
        System.out.println("=== JAVA STREAMS - GUIDA COMPLETA ===\n");
        
        demo.creazioneStreams();
        demo.operazioniIntermedie();
        demo.operazioniTerminali();
        demo.filterMapReduce();
        demo.collectorsBase();
        demo.collectorsAvanzati();
        demo.groupingEPartitioning();
        demo.flatMapOperazioni();
        demo.optionalConStream();
        demo.streamParalleli();
        demo.streamInfiniti();
        demo.streamPrimitivi();
        demo.statistiche();
        demo.customCollectors();
        demo.patternAvanzati();
        demo.casiUsoReali();
    }

    // ==================== CLASSI DI SUPPORTO ====================

    static class Persona {
        private String nome;
        private int eta;
        private String citta;
        private double stipendio;
        private String dipartimento;
        private List<String> hobby;

        public Persona(String nome, int eta, String citta, double stipendio, String dipartimento) {
            this.nome = nome;
            this.eta = eta;
            this.citta = citta;
            this.stipendio = stipendio;
            this.dipartimento = dipartimento;
            this.hobby = new ArrayList<>();
        }

        public Persona(String nome, int eta, String citta, double stipendio, String dipartimento, List<String> hobby) {
            this.nome = nome;
            this.eta = eta;
            this.citta = citta;
            this.stipendio = stipendio;
            this.dipartimento = dipartimento;
            this.hobby = hobby;
        }

        // Getters
        public String getNome() { return nome; }
        public int getEta() { return eta; }
        public String getCitta() { return citta; }
        public double getStipendio() { return stipendio; }
        public String getDipartimento() { return dipartimento; }
        public List<String> getHobby() { return hobby; }

        @Override
        public String toString() {
            return String.format("%s (%d anni, %s, €%.0f, %s)", 
                nome, eta, citta, stipendio, dipartimento);
        }
    }

    static class Ordine {
        private String id;
        private String cliente;
        private double importo;
        private String stato;
        private List<Prodotto> prodotti;

        public Ordine(String id, String cliente, double importo, String stato, List<Prodotto> prodotti) {
            this.id = id;
            this.cliente = cliente;
            this.importo = importo;
            this.stato = stato;
            this.prodotti = prodotti;
        }

        public String getId() { return id; }
        public String getCliente() { return cliente; }
        public double getImporto() { return importo; }
        public String getStato() { return stato; }
        public List<Prodotto> getProdotti() { return prodotti; }

        @Override
        public String toString() {
            return String.format("Ordine[%s, %s, €%.2f, %s]", id, cliente, importo, stato);
        }
    }

    static class Prodotto {
        private String nome;
        private double prezzo;
        private String categoria;
        private int quantita;

        public Prodotto(String nome, double prezzo, String categoria, int quantita) {
            this.nome = nome;
            this.prezzo = prezzo;
            this.categoria = categoria;
            this.quantita = quantita;
        }

        public String getNome() { return nome; }
        public double getPrezzo() { return prezzo; }
        public String getCategoria() { return categoria; }
        public int getQuantita() { return quantita; }

        @Override
        public String toString() {
            return String.format("%s (€%.2f, %s, qty: %d)", nome, prezzo, categoria, quantita);
        }
    }

    // ==================== DATASET DI ESEMPIO ====================

    private List<Persona> creaPersone() {
        return Arrays.asList(
            new Persona("Mario", 35, "Roma", 45000, "IT", Arrays.asList("Calcio", "Lettura")),
            new Persona("Luigi", 28, "Milano", 38000, "Marketing", Arrays.asList("Tennis", "Cinema")),
            new Persona("Peach", 32, "Roma", 52000, "IT", Arrays.asList("Yoga", "Cucina")),
            new Persona("Bowser", 45, "Napoli", 60000, "Management", Arrays.asList("Golf")),
            new Persona("Yoshi", 25, "Milano", 32000, "HR", Arrays.asList("Corsa", "Lettura", "Musica")),
            new Persona("Toad", 30, "Torino", 42000, "IT", Arrays.asList("Gaming", "Fotografia")),
            new Persona("Daisy", 27, "Roma", 39000, "Marketing", Arrays.asList("Danza", "Viaggi")),
            new Persona("Wario", 40, "Milano", 55000, "Sales", Arrays.asList("Cucina")),
            new Persona("Waluigi", 38, "Napoli", 48000, "IT", Arrays.asList("Tennis", "Gaming")),
            new Persona("Rosalina", 33, "Torino", 50000, "HR", Arrays.asList("Lettura", "Astronomia"))
        );
    }

    private List<Ordine> creaOrdini() {
        return Arrays.asList(
            new Ordine("O001", "Mario", 150.00, "Completato", Arrays.asList(
                new Prodotto("Laptop", 1200.00, "Elettronica", 1),
                new Prodotto("Mouse", 25.00, "Elettronica", 2)
            )),
            new Ordine("O002", "Luigi", 85.50, "In corso", Arrays.asList(
                new Prodotto("Libro Java", 45.00, "Libri", 1),
                new Prodotto("Penna", 2.50, "Cancelleria", 5)
            )),
            new Ordine("O003", "Peach", 320.00, "Completato", Arrays.asList(
                new Prodotto("Tablet", 300.00, "Elettronica", 1),
                new Prodotto("Cover", 20.00, "Accessori", 1)
            )),
            new Ordine("O004", "Mario", 45.00, "Annullato", Arrays.asList(
                new Prodotto("Cuffie", 45.00, "Elettronica", 1)
            )),
            new Ordine("O005", "Bowser", 200.00, "Completato", Arrays.asList(
                new Prodotto("Tastiera", 120.00, "Elettronica", 1),
                new Prodotto("Mouse", 25.00, "Elettronica", 1),
                new Prodotto("Mousepad", 15.00, "Accessori", 1)
            ))
        );
    }

    // ==================== CREAZIONE STREAMS ====================

    public void creazioneStreams() {
        System.out.println("=== CREAZIONE STREAMS ===");

        // 1. Da Collection
        List<String> lista = Arrays.asList("A", "B", "C");
        Stream<String> stream1 = lista.stream();
        System.out.println("Da lista: " + stream1.collect(Collectors.toList()));

        // 2. Da Array
        String[] array = {"X", "Y", "Z"};
        Stream<String> stream2 = Arrays.stream(array);
        System.out.println("Da array: " + stream2.collect(Collectors.toList()));

        // 3. Con Stream.of()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Con Stream.of: " + stream3.collect(Collectors.toList()));

        // 4. Stream vuoto
        Stream<String> stream4 = Stream.empty();
        System.out.println("Stream vuoto: " + stream4.count());

        // 5. Stream infinito con iterate
        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("Con iterate: " + stream5.collect(Collectors.toList()));

        // 6. Stream infinito con generate
        Stream<Double> stream6 = Stream.generate(Math::random).limit(3);
        System.out.println("Con generate: " + stream6.collect(Collectors.toList()));

        // 7. Stream da stringa
        IntStream stream7 = "Hello".chars();
        System.out.println("Da stringa (chars): " + stream7.count());

        // 8. Stream builder
        Stream<String> stream8 = Stream.<String>builder()
            .add("Uno")
            .add("Due")
            .add("Tre")
            .build();
        System.out.println("Con builder: " + stream8.collect(Collectors.toList()));

        // 9. Stream da range (primitivi)
        IntStream stream9 = IntStream.range(1, 6); // 1,2,3,4,5
        System.out.println("IntStream range: " + stream9.boxed().collect(Collectors.toList()));

        // 10. Stream da rangeClosed (primitivi)
        IntStream stream10 = IntStream.rangeClosed(1, 5); // 1,2,3,4,5
        System.out.println("IntStream rangeClosed: " + stream10.boxed().collect(Collectors.toList()));

        System.out.println();
    }

    // ==================== OPERAZIONI INTERMEDIE ====================

    public void operazioniIntermedie() {
        System.out.println("=== OPERAZIONI INTERMEDIE ===");

        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4);

        // filter - filtra elementi
        System.out.println("Numeri pari: " + 
            numeri.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList()));

        // map - trasforma elementi
        System.out.println("Numeri al quadrato: " + 
            numeri.stream()
                .map(n -> n * n)
                .limit(5)
                .collect(Collectors.toList()));

        // distinct - rimuove duplicati
        System.out.println("Numeri unici: " + 
            numeri.stream()
                .distinct()
                .collect(Collectors.toList()));

        // sorted - ordina elementi
        System.out.println("Numeri ordinati desc: " + 
            numeri.stream()
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .collect(Collectors.toList()));

        // limit - limita numero elementi
        System.out.println("Primi 5: " + 
            numeri.stream()
                .limit(5)
                .collect(Collectors.toList()));

        // skip - salta elementi
        System.out.println("Salta primi 5: " + 
            numeri.stream()
                .skip(5)
                .limit(3)
                .collect(Collectors.toList()));

        // peek - esegue azione senza modificare stream (per debug)
        System.out.print("Peek (debug): ");
        List<Integer> result = numeri.stream()
            .filter(n -> n > 5)
            .peek(n -> System.out.print(n + " "))
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("\nDopo map: " + result);

        // takeWhile (Java 9+) - prende elementi finché condizione vera
        System.out.println("TakeWhile < 5: " + 
            numeri.stream()
                .takeWhile(n -> n < 5)
                .collect(Collectors.toList()));

        // dropWhile (Java 9+) - scarta elementi finché condizione vera
        System.out.println("DropWhile < 5: " + 
            numeri.stream()
                .dropWhile(n -> n < 5)
                .limit(5)
                .collect(Collectors.toList()));

        System.out.println();
    }

    // ==================== OPERAZIONI TERMINALI ====================

    public void operazioniTerminali() {
        System.out.println("=== OPERAZIONI TERMINALI ===");

        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // forEach - itera su elementi
        System.out.print("forEach: ");
        numeri.stream().limit(5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // count - conta elementi
        long count = numeri.stream().filter(n -> n > 5).count();
        System.out.println("Count (> 5): " + count);

        // min - trova minimo
        Optional<Integer> min = numeri.stream().min(Integer::compareTo);
        System.out.println("Minimo: " + min.orElse(0));

        // max - trova massimo
        Optional<Integer> max = numeri.stream().max(Integer::compareTo);
        System.out.println("Massimo: " + max.orElse(0));

        // findFirst - trova primo elemento
        Optional<Integer> first = numeri.stream().filter(n -> n > 5).findFirst();
        System.out.println("Primo > 5: " + first.orElse(0));

        // findAny - trova qualsiasi elemento (utile con parallel)
        Optional<Integer> any = numeri.stream().filter(n -> n > 5).findAny();
        System.out.println("Qualsiasi > 5: " + any.orElse(0));

        // allMatch - verifica se tutti gli elementi soddisfano condizione
        boolean allPositive = numeri.stream().allMatch(n -> n > 0);
        System.out.println("Tutti positivi: " + allPositive);

        // anyMatch - verifica se almeno un elemento soddisfa condizione
        boolean anyGreaterThan5 = numeri.stream().anyMatch(n -> n > 5);
        System.out.println("Almeno uno > 5: " + anyGreaterThan5);

        // noneMatch - verifica se nessun elemento soddisfa condizione
        boolean noneNegative = numeri.stream().noneMatch(n -> n < 0);
        System.out.println("Nessuno negativo: " + noneNegative);

        // reduce - riduce stream a singolo valore
        Optional<Integer> sum = numeri.stream().reduce((a, b) -> a + b);
        System.out.println("Somma (reduce): " + sum.orElse(0));

        // reduce con valore iniziale
        int sumWithIdentity = numeri.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Somma (reduce con identity): " + sumWithIdentity);

        // reduce per trovare max
        int maxReduce = numeri.stream().reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("Max (reduce): " + maxReduce);

        // toArray - converte in array
        Integer[] array = numeri.stream().toArray(Integer[]::new);
        System.out.println("ToArray: " + Arrays.toString(array));

        System.out.println();
    }

    // ==================== FILTER, MAP, REDUCE ====================

    public void filterMapReduce() {
        System.out.println("=== FILTER, MAP, REDUCE ===");

        List<Persona> persone = creaPersone();

        // Filter: solo persone di Roma
        System.out.println("Persone di Roma:");
        persone.stream()
            .filter(p -> p.getCitta().equals("Roma"))
            .forEach(p -> System.out.println("  " + p));

        // Map: estrai solo i nomi
        System.out.println("\nNomi:");
        List<String> nomi = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.toList());
        System.out.println("  " + nomi);

        // Filter + Map: nomi delle persone con età > 30
        System.out.println("\nNomi persone con età > 30:");
        persone.stream()
            .filter(p -> p.getEta() > 30)
            .map(Persona::getNome)
            .forEach(nome -> System.out.println("  " + nome));

        // Reduce: somma degli stipendi
        double totaleStipendi = persone.stream()
            .map(Persona::getStipendio)
            .reduce(0.0, Double::sum);
        System.out.println("\nTotale stipendi: €" + String.format("%.2f", totaleStipendi));

        // Reduce: trova persona con stipendio massimo
        Optional<Persona> maxStipendio = persone.stream()
            .reduce((p1, p2) -> p1.getStipendio() > p2.getStipendio() ? p1 : p2);
        System.out.println("Stipendio massimo: " + maxStipendio.orElse(null));

        // Chain complessa: filtra, mappa, ordina, limita
        System.out.println("\nTop 3 stipendi IT:");
        persone.stream()
            .filter(p -> p.getDipartimento().equals("IT"))
            .sorted(Comparator.comparing(Persona::getStipendio).reversed())
            .limit(3)
            .forEach(p -> System.out.println("  " + p));

        System.out.println();
    }

    // ==================== COLLECTORS BASE ====================

    public void collectorsBase() {
        System.out.println("=== COLLECTORS BASE ===");

        List<Persona> persone = creaPersone();

        // toList
        List<String> nomi = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.toList());
        System.out.println("toList: " + nomi);

        // toSet (rimuove duplicati)
        Set<String> citta = persone.stream()
            .map(Persona::getCitta)
            .collect(Collectors.toSet());
        System.out.println("toSet (città): " + citta);

        // toCollection (specifica tipo di collection)
        TreeSet<String> cittaOrdinateSet = persone.stream()
            .map(Persona::getCitta)
            .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("toCollection (TreeSet): " + cittaOrdinateSet);

        // toMap
        Map<String, Integer> nomeEtaMap = persone.stream()
            .collect(Collectors.toMap(
                Persona::getNome,
                Persona::getEta
            ));
        System.out.println("toMap (nome -> età): " + nomeEtaMap);

        // toMap con merge function (per duplicati)
        Map<String, Double> cittaStipendioMap = persone.stream()
            .collect(Collectors.toMap(
                Persona::getCitta,
                Persona::getStipendio,
                (s1, s2) -> s1 + s2  // somma stipendi per città
            ));
        System.out.println("toMap con merge (città -> totale stipendi): " + cittaStipendioMap);

        // joining
        String nomiConcatenati = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.joining());
        System.out.println("joining: " + nomiConcatenati);

        // joining con delimitatore
        String nomiConVirgola = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.joining(", "));
        System.out.println("joining con delimiter: " + nomiConVirgola);

        // joining con delimitatore, prefisso e suffisso
        String nomiFormattati = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joining con prefix/suffix: " + nomiFormattati);

        // counting
        long numeroPersone = persone.stream().collect(Collectors.counting());
        System.out.println("counting: " + numeroPersone);

        System.out.println();
    }

    // ==================== COLLECTORS AVANZATI ====================

    public void collectorsAvanzati() {
        System.out.println("=== COLLECTORS AVANZATI ===");

        List<Persona> persone = creaPersone();

        // summingDouble - somma
        double totaleStipendi = persone.stream()
            .collect(Collectors.summingDouble(Persona::getStipendio));
        System.out.println("summingDouble (stipendi): €" + String.format("%.2f", totaleStipendi));

        // averagingDouble - media
        double mediaStipendi = persone.stream()
            .collect(Collectors.averagingDouble(Persona::getStipendio));
        System.out.println("averagingDouble (stipendi): €" + String.format("%.2f", mediaStipendi));

        // summarizingDouble - statistiche complete
        DoubleSummaryStatistics statsStipendi = persone.stream()
            .collect(Collectors.summarizingDouble(Persona::getStipendio));
        System.out.println("summarizingDouble:");
        System.out.println("  Count: " + statsStipendi.getCount());
        System.out.println("  Sum: €" + String.format("%.2f", statsStipendi.getSum()));
        System.out.println("  Min: €" + String.format("%.2f", statsStipendi.getMin()));
        System.out.println("  Max: €" + String.format("%.2f", statsStipendi.getMax()));
        System.out.println("  Average: €" + String.format("%.2f", statsStipendi.getAverage()));

        // maxBy - trova elemento massimo
        Optional<Persona> personaPiuAnziana = persone.stream()
            .collect(Collectors.maxBy(Comparator.comparing(Persona::getEta)));
        System.out.println("maxBy (età): " + personaPiuAnziana.orElse(null));

        // minBy - trova elemento minimo
        Optional<Persona> personaPiuGiovane = persone.stream()
            .collect(Collectors.minBy(Comparator.comparing(Persona::getEta)));
        System.out.println("minBy (età): " + personaPiuGiovane.orElse(null));

        // mapping - mappa e poi colleziona
        List<String> nomiMaiuscolo = persone.stream()
            .collect(Collectors.mapping(
                p -> p.getNome().toUpperCase(),
                Collectors.toList()
            ));
        System.out.println("mapping: " + nomiMaiuscolo);

        // filtering - filtra e poi colleziona (Java 9+)
        List<Persona> personeMilano = persone.stream()
            .collect(Collectors.filtering(
                p -> p.getCitta().equals("Milano"),
                Collectors.toList()
            ));
        System.out.println("filtering (Milano): " + personeMilano.size() + " persone");

        // flatMapping - flatten e poi colleziona (Java 9+)
        List<String> tuttiHobby = persone.stream()
            .collect(Collectors.flatMapping(
                p -> p.getHobby().stream(),
                Collectors.toList()
            ));
        System.out.println("flatMapping (hobby): " + tuttiHobby);

        // teeing - combina due collectors (Java 12+)
        String statistiche = persone.stream()
            .collect(Collectors.teeing(
                Collectors.counting(),
                Collectors.averagingDouble(Persona::getEta),
                (count, avgAge) -> String.format("Count: %d, Avg Age: %.1f", count, avgAge)
            ));
        System.out.println("teeing: " + statistiche);

        System.out.println();
    }

    // ==================== GROUPING E PARTITIONING ====================

    public void groupingEPartitioning() {
        System.out.println("=== GROUPING E PARTITIONING ===");

        List<Persona> persone = creaPersone();

        // groupingBy - raggruppa per città
        Map<String, List<Persona>> perCitta = persone.stream()
            .collect(Collectors.groupingBy(Persona::getCitta));
        System.out.println("groupingBy (città):");
        perCitta.forEach((citta, lista) -> 
            System.out.println("  " + citta + ": " + lista.size() + " persone"));

        // groupingBy con counting - conta elementi per gruppo
        Map<String, Long> countPerDipartimento = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getDipartimento,
                Collectors.counting()
            ));
        System.out.println("\ngroupingBy + counting (dipartimento):");
        countPerDipartimento.forEach((dept, count) -> 
            System.out.println("  " + dept + ": " + count));

        // groupingBy con summingDouble - somma per gruppo
        Map<String, Double> stipendioPerCitta = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.summingDouble(Persona::getStipendio)
            ));
        System.out.println("\ngroupingBy + summingDouble (città):");
        stipendioPerCitta.forEach((citta, totale) -> 
            System.out.println("  " + citta + ": €" + String.format("%.2f", totale)));

        // groupingBy con averagingDouble - media per gruppo
        Map<String, Double> mediaEtaPerCitta = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.averagingDouble(Persona::getEta)
            ));
        System.out.println("\ngroupingBy + averagingDouble (città):");
        mediaEtaPerCitta.forEach((citta, media) -> 
            System.out.println("  " + citta + ": " + String.format("%.1f", media) + " anni"));

        // groupingBy con mapping - estrae solo i nomi
        Map<String, List<String>> nomiPerCitta = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.mapping(Persona::getNome, Collectors.toList())
            ));
        System.out.println("\ngroupingBy + mapping (città -> nomi):");
        nomiPerCitta.forEach((citta, nomi) -> 
            System.out.println("  " + citta + ": " + nomi));

        // groupingBy multilivello - raggruppa per città e poi per dipartimento
        Map<String, Map<String, List<Persona>>> perCittaEDipartimento = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.groupingBy(Persona::getDipartimento)
            ));
        System.out.println("\ngroupingBy multilivello (città -> dipartimento):");
        perCittaEDipartimento.forEach((citta, deptMap) -> {
            System.out.println("  " + citta + ":");
            deptMap.forEach((dept, lista) -> 
                System.out.println("    " + dept + ": " + lista.size()));
        });

        // partitioningBy - divide in due gruppi (true/false)
        Map<Boolean, List<Persona>> partitionPerEta = persone.stream()
            .collect(Collectors.partitioningBy(p -> p.getEta() >= 35));
        System.out.println("\npartitioningBy (età >= 35):");
        System.out.println("  >= 35: " + partitionPerEta.get(true).size());
        System.out.println("  < 35: " + partitionPerEta.get(false).size());

        // partitioningBy con downstream collector
        Map<Boolean, Long> countPartition = persone.stream()
            .collect(Collectors.partitioningBy(
                p -> p.getStipendio() >= 45000,
                Collectors.counting()
            ));
        System.out.println("\npartitioningBy + counting (stipendio >= 45k):");
        System.out.println("  >= 45k: " + countPartition.get(true));
        System.out.println("  < 45k: " + countPartition.get(false));

        // collectingAndThen - applica trasformazione finale
        List<String> nomiImmodificabile = persone.stream()
            .map(Persona::getNome)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList
            ));
        System.out.println("\ncollectingAndThen: " + nomiImmodificabile.getClass().getSimpleName());

        System.out.println();
    }

    // ==================== FLATMAP ====================

    public void flatMapOperazioni() {
        System.out.println("=== FLATMAP ===");

        List<Persona> persone = creaPersone();
        List<Ordine> ordini = creaOrdini();

        // flatMap su liste di hobby
        System.out.println("Tutti gli hobby (con duplicati):");
        List<String> tuttiHobby = persone.stream()
            .flatMap(p -> p.getHobby().stream())
            .collect(Collectors.toList());
        System.out.println("  " + tuttiHobby);

        // flatMap + distinct - hobby unici
        System.out.println("\nHobby unici:");
        List<String> hobbyUnici = persone.stream()
            .flatMap(p -> p.getHobby().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("  " + hobbyUnici);

        // flatMap con conteggio
        Map<String, Long> conteggioHobby = persone.stream()
            .flatMap(p -> p.getHobby().stream())
            .collect(Collectors.groupingBy(
                hobby -> hobby,
                Collectors.counting()
            ));
        System.out.println("\nConteggio hobby:");
        conteggioHobby.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));

        // flatMap su ordini -> prodotti
        System.out.println("\nTutti i prodotti da tutti gli ordini:");
        List<Prodotto> tuttiProdotti = ordini.stream()
            .flatMap(o -> o.getProdotti().stream())
            .collect(Collectors.toList());
        tuttiProdotti.forEach(p -> System.out.println("  " + p));

        // flatMap con filtering
        System.out.println("\nProdotti categoria Elettronica:");
        ordini.stream()
            .flatMap(o -> o.getProdotti().stream())
            .filter(p -> p.getCategoria().equals("Elettronica"))
            .forEach(p -> System.out.println("  " + p));

        // flatMap con grouping
        Map<String, Long> prodottiPerCategoria = ordini.stream()
            .flatMap(o -> o.getProdotti().stream())
            .collect(Collectors.groupingBy(
                Prodotto::getCategoria,
                Collectors.counting()
            ));
        System.out.println("\nProdotti per categoria:");
        prodottiPerCategoria.forEach((cat, count) -> 
            System.out.println("  " + cat + ": " + count));

        // flatMap su array di array
        List<List<Integer>> numeriNestati = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        List<Integer> numeriFlatten = numeriNestati.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("\nFlatten array di array: " + numeriFlatten);

        // flatMapToInt - flatten a IntStream
        int sommaQuantita = ordini.stream()
            .flatMapToInt(o -> o.getProdotti().stream().mapToInt(Prodotto::getQuantita))
            .sum();
        System.out.println("Somma quantità totale: " + sommaQuantita);

        System.out.println();
    }

    // ==================== OPTIONAL CON STREAM ====================

    public void optionalConStream() {
        System.out.println("=== OPTIONAL CON STREAM ===");

        List<Persona> persone = creaPersone();

        // findFirst ritorna Optional
        Optional<Persona> primaPersonaRoma = persone.stream()
            .filter(p -> p.getCitta().equals("Roma"))
            .findFirst();
        
        System.out.println("Prima persona di Roma:");
        primaPersonaRoma.ifPresent(p -> System.out.println("  " + p));

        // orElse - valore di default
        Persona persona = persone.stream()
            .filter(p -> p.getCitta().equals("Palermo"))
            .findFirst()
            .orElse(new Persona("Unknown", 0, "Unknown", 0, "Unknown"));
        System.out.println("\nPersona di Palermo (orElse): " + persona);

        // orElseGet - supplier di default
        Persona persona2 = persone.stream()
            .filter(p -> p.getEta() > 100)
            .findFirst()
            .orElseGet(() -> new Persona("Default", 0, "Default", 0, "Default"));
        System.out.println("Persona > 100 anni (orElseGet): " + persona2.getNome());

        // orElseThrow - lancia eccezione
        try {
            Persona persona3 = persone.stream()
                .filter(p -> p.getCitta().equals("Palermo"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nessuna persona trovata"));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow: " + e.getMessage());
        }

        // map su Optional
        Optional<String> nomePersonaRoma = persone.stream()
            .filter(p -> p.getCitta().equals("Roma"))
            .findFirst()
            .map(Persona::getNome);
        System.out.println("\nNome prima persona Roma: " + nomePersonaRoma.orElse("N/A"));

        // flatMap su Optional
        Optional<String> primoHobby = persone.stream()
            .filter(p -> p.getCitta().equals("Roma"))
            .findFirst()
            .flatMap(p -> p.getHobby().stream().findFirst());
        System.out.println("Primo hobby prima persona Roma: " + primoHobby.orElse("N/A"));

        // filter su Optional
        Optional<Persona> personaFiltrata = persone.stream()
            .findFirst()
            .filter(p -> p.getEta() > 30);
        System.out.println("Prima persona filtrata (età > 30): " + 
            personaFiltrata.map(Persona::getNome).orElse("N/A"));

        // Stream da Optional (Java 9+)
        long count = persone.stream()
            .findFirst()
            .stream()
            .count();
        System.out.println("\nStream da Optional count: " + count);

        // Uso pratico: evitare null
        String risultato = persone.stream()
            .filter(p -> p.getCitta().equals("Milano"))
            .findFirst()
            .map(Persona::getNome)
            .map(String::toUpperCase)
            .orElse("NON TROVATO");
        System.out.println("Risultato chain con Optional: " + risultato);

        System.out.println();
    }

    // ==================== STREAM PARALLELI ====================

    public void streamParalleli() {
        System.out.println("=== STREAM PARALLELI ===");

        List<Integer> numeri = IntStream.rangeClosed(1, 1000)
            .boxed()
            .collect(Collectors.toList());

        // Stream sequenziale
        long startSeq = System.currentTimeMillis();
        long sumSeq = numeri.stream()
            .mapToLong(n -> n * n)
            .sum();
        long endSeq = System.currentTimeMillis();
        System.out.println("Somma sequenziale: " + sumSeq + " (" + (endSeq - startSeq) + "ms)");

        // Stream parallelo
        long startPar = System.currentTimeMillis();
        long sumPar = numeri.parallelStream()
            .mapToLong(n -> n * n)
            .sum();
        long endPar = System.currentTimeMillis();
        System.out.println("Somma parallelo: " + sumPar + " (" + (endPar - startPar) + "ms)");

        // Conversione a parallelo
        long sumPar2 = numeri.stream()
            .parallel()
            .mapToLong(n -> n * n)
            .sum();
        System.out.println("Somma parallelo (con .parallel()): " + sumPar2);

        // Verifica se è parallelo
        boolean isParallel = numeri.parallelStream().isParallel();
        System.out.println("È parallelo: " + isParallel);

        // Conversione a sequenziale
        long sumSeq2 = numeri.parallelStream()
            .sequential()
            .mapToLong(n -> n * n)
            .sum();
        System.out.println("Somma sequenziale (da parallel): " + sumSeq2);

        // Esempio con collezione concorrente
        List<Persona> persone = creaPersone();
        Map<String, List<Persona>> perCitta = persone.parallelStream()
            .collect(Collectors.groupingByConcurrent(Persona::getCitta));
        System.out.println("\nGrouping concorrente: " + perCitta.keySet());

        // Attenzione: ordine non garantito con parallel
        System.out.println("\nOrdine sequenziale:");
        List<Integer> primi5Seq = IntStream.rangeClosed(1, 10)
            .boxed()
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("  " + primi5Seq);

        System.out.println("Ordine parallelo (può variare):");
        List<Integer> primi5Par = IntStream.rangeClosed(1, 10)
            .boxed()
            .parallel()
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("  " + primi5Par);

        // ForEachOrdered mantiene l'ordine
        System.out.print("\nforEachOrdered con parallel: ");
        IntStream.rangeClosed(1, 10)
            .parallel()
            .forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println();
    }

    // ==================== STREAM INFINITI ====================

    public void streamInfiniti() {
        System.out.println("=== STREAM INFINITI ===");

        // iterate - genera sequenza
        System.out.println("Primi 10 numeri pari (iterate):");
        List<Integer> pari = Stream.iterate(0, n -> n + 2)
            .limit(10)
            .collect(Collectors.toList());
        System.out.println("  " + pari);

        // iterate con condizione (Java 9+)
        System.out.println("\nIterate con condizione (< 20):");
        List<Integer> numeri = Stream.iterate(0, n -> n < 20, n -> n + 3)
            .collect(Collectors.toList());
        System.out.println("  " + numeri);

        // generate - genera valori random
        System.out.println("\n5 numeri random:");
        List<Double> random = Stream.generate(Math::random)
            .limit(5)
            .collect(Collectors.toList());
        random.forEach(n -> System.out.println("  " + String.format("%.4f", n)));

        // generate con supplier
        System.out.println("\n5 UUID:");
        Stream.generate(() -> UUID.randomUUID().toString())
            .limit(5)
            .forEach(uuid -> System.out.println("  " + uuid));

        // Sequenza di Fibonacci
        System.out.println("\nPrimi 10 Fibonacci:");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
            .limit(10)
            .map(f -> f[0])
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Numeri primi con stream infinito
        System.out.println("\nPrimi 10 numeri primi:");
        Stream.iterate(2, n -> n + 1)
            .filter(this::isPrime)
            .limit(10)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Potenze di 2
        System.out.println("\nPrime 10 potenze di 2:");
        Stream.iterate(1, n -> n * 2)
            .limit(10)
            .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // ==================== STREAM PRIMITIVI ====================

    public void streamPrimitivi() {
        System.out.println("=== STREAM PRIMITIVI ===");

        // IntStream
        System.out.println("IntStream range(1, 6):");
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println("IntStream rangeClosed(1, 5):");
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Operazioni su IntStream
        int sum = IntStream.rangeClosed(1, 10).sum();
        System.out.println("Somma 1-10: " + sum);

        OptionalDouble average = IntStream.rangeClosed(1, 10).average();
        System.out.println("Media 1-10: " + average.orElse(0));

        OptionalInt max = IntStream.rangeClosed(1, 10).max();
        System.out.println("Max 1-10: " + max.orElse(0));

        OptionalInt min = IntStream.rangeClosed(1, 10).min();
        System.out.println("Min 1-10: " + min.orElse(0));

        // LongStream
        System.out.println("\nLongStream:");
        long sumLong = LongStream.rangeClosed(1, 1000000).sum();
        System.out.println("Somma 1-1000000: " + sumLong);

        // DoubleStream
        System.out.println("\nDoubleStream:");
        double sumDouble = DoubleStream.of(1.5, 2.7, 3.2, 4.8).sum();
        System.out.println("Somma double: " + String.format("%.2f", sumDouble));

        // Conversione da Stream a primitivo
        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5);
        int sumFromList = numeri.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("\nSomma da List (mapToInt): " + sumFromList);

        // Conversione da primitivo a Stream
        List<Integer> boxed = IntStream.rangeClosed(1, 5)
            .boxed()
            .collect(Collectors.toList());
        System.out.println("Boxed IntStream: " + boxed);

        // mapToInt, mapToLong, mapToDouble
        List<Persona> persone = creaPersone();
        
        int sommaEta = persone.stream()
            .mapToInt(Persona::getEta)
            .sum();
        System.out.println("\nSomma età (mapToInt): " + sommaEta);

        double sommaStipendi = persone.stream()
            .mapToDouble(Persona::getStipendio)
            .sum();
        System.out.println("Somma stipendi (mapToDouble): €" + String.format("%.2f", sommaStipendi));

        // summaryStatistics
        IntSummaryStatistics statsEta = persone.stream()
            .mapToInt(Persona::getEta)
            .summaryStatistics();
        System.out.println("\nStatistiche età:");
        System.out.println("  Count: " + statsEta.getCount());
        System.out.println("  Sum: " + statsEta.getSum());
        System.out.println("  Min: " + statsEta.getMin());
        System.out.println("  Max: " + statsEta.getMax());
        System.out.println("  Average: " + String.format("%.2f", statsEta.getAverage()));

        System.out.println();
    }

    // ==================== STATISTICHE ====================

    public void statistiche() {
        System.out.println("=== STATISTICHE ===");

        List<Persona> persone = creaPersone();

        // Statistiche manuali con reduce
        int somma = persone.stream()
            .map(Persona::getEta)
            .reduce(0, Integer::sum);
        System.out.println("Somma età (reduce): " + somma);

        int min = persone.stream()
            .map(Persona::getEta)
            .reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("Età minima (reduce): " + min);

        int max = persone.stream()
            .map(Persona::getEta)
            .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("Età massima (reduce): " + max);

        // Media manuale
        double media = persone.stream()
            .mapToDouble(Persona::getEta)
            .average()
            .orElse(0.0);
        System.out.println("Età media: " + String.format("%.2f", media));

        // Statistiche con Collectors
        DoubleSummaryStatistics stats = persone.stream()
            .collect(Collectors.summarizingDouble(Persona::getStipendio));
        
        System.out.println("\nStatistiche stipendi:");
        System.out.println("  Numero persone: " + stats.getCount());
        System.out.println("  Totale: €" + String.format("%.2f", stats.getSum()));
        System.out.println("  Minimo: €" + String.format("%.2f", stats.getMin()));
        System.out.println("  Massimo: €" + String.format("%.2f", stats.getMax()));
        System.out.println("  Media: €" + String.format("%.2f", stats.getAverage()));

        // Mediana (valore centrale)
        List<Integer> etaOrdinata = persone.stream()
            .map(Persona::getEta)
            .sorted()
            .collect(Collectors.toList());
        double mediana = etaOrdinata.size() % 2 == 0
            ? (etaOrdinata.get(etaOrdinata.size()/2 - 1) + etaOrdinata.get(etaOrdinata.size()/2)) / 2.0
            : etaOrdinata.get(etaOrdinata.size()/2);
        System.out.println("\nMediana età: " + String.format("%.1f", mediana));

        // Deviazione standard (manuale)
        double mediaEta = persone.stream()
            .mapToDouble(Persona::getEta)
            .average()
            .orElse(0.0);
        
        double varianza = persone.stream()
            .mapToDouble(p -> Math.pow(p.getEta() - mediaEta, 2))
            .average()
            .orElse(0.0);
        
        double devStandard = Math.sqrt(varianza);
        System.out.println("Deviazione standard età: " + String.format("%.2f", devStandard));

        System.out.println();
    }

    // ==================== CUSTOM COLLECTORS ====================

    public void customCollectors() {
        System.out.println("=== CUSTOM COLLECTORS ===");

        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Custom collector: somma dei quadrati
        int sommaQuadrati = numeri.stream()
            .collect(
                () -> new int[1],  // supplier
                (arr, n) -> arr[0] += n * n,  // accumulator
                (arr1, arr2) -> arr1[0] += arr2[0]  // combiner
            )[0];
        System.out.println("Somma quadrati (custom): " + sommaQuadrati);

        // Custom collector con Collector.of
        Collector<Integer, List<Integer>, List<Integer>> toImmutableList = Collector.of(
            ArrayList::new,  // supplier
            List::add,  // accumulator
            (list1, list2) -> {  // combiner
                list1.addAll(list2);
                return list1;
            },
            Collections::unmodifiableList  // finisher
        );
        
        List<Integer> immutableList = numeri.stream()
            .filter(n -> n % 2 == 0)
            .collect(toImmutableList);
        System.out.println("Lista immutabile: " + immutableList);

        // Custom collector: string joiner personalizzato
        Collector<String, StringJoiner, String> customJoiner = Collector.of(
            () -> new StringJoiner(" | ", "[ ", " ]"),  // supplier
            StringJoiner::add,  // accumulator
            StringJoiner::merge,  // combiner
            StringJoiner::toString  // finisher
        );
        
        String joined = Stream.of("A", "B", "C", "D")
            .collect(customJoiner);
        System.out.println("Custom joiner: " + joined);

        // Custom collector: ConcurrentHashMap
        List<Persona> persone = creaPersone();
        
        ConcurrentHashMap<String, Persona> mapPersone = persone.stream()
            .collect(
                ConcurrentHashMap::new,
                (map, p) -> map.put(p.getNome(), p),
                ConcurrentHashMap::putAll
            );
        System.out.println("ConcurrentHashMap size: " + mapPersone.size());

        // Custom collector: statistiche personalizzate
        class CustomStats {
            int count = 0;
            int sum = 0;
            
            void accept(int value) {
                count++;
                sum += value;
            }
            
            CustomStats combine(CustomStats other) {
                count += other.count;
                sum += other.sum;
                return this;
            }
            
            double average() {
                return count > 0 ? (double) sum / count : 0;
            }
        }
        
        CustomStats stats = numeri.stream()
            .collect(
                CustomStats::new,
                CustomStats::accept,
                CustomStats::combine
            );
        System.out.println("Custom stats - Count: " + stats.count + ", Media: " + stats.average());

        System.out.println();
    }

    // ==================== PATTERN AVANZATI ====================

    public void patternAvanzati() {
        System.out.println("=== PATTERN AVANZATI ===");

        List<Persona> persone = creaPersone();

        // 1. Chaining complesso
        System.out.println("Top 3 stipendi IT ordinati per età:");
        persone.stream()
            .filter(p -> p.getDipartimento().equals("IT"))
            .sorted(Comparator.comparing(Persona::getStipendio).reversed())
            .limit(3)
            .sorted(Comparator.comparing(Persona::getEta))
            .forEach(p -> System.out.println("  " + p));

        // 2. Multiple criteri di sorting
        System.out.println("\nOrdina per città (asc) e poi per stipendio (desc):");
        persone.stream()
            .sorted(Comparator.comparing(Persona::getCitta)
                .thenComparing(Comparator.comparing(Persona::getStipendio).reversed()))
            .limit(5)
            .forEach(p -> System.out.println("  " + p));

        // 3. Peek per debugging
        System.out.println("\nPeek per debug:");
        long count = persone.stream()
            .peek(p -> System.out.println("  Original: " + p.getNome()))
            .filter(p -> p.getEta() > 30)
            .peek(p -> System.out.println("  Filtered: " + p.getNome()))
            .map(Persona::getNome)
            .peek(nome -> System.out.println("  Mapped: " + nome))
            .count();
        System.out.println("  Final count: " + count);

        // 4. Distinct con custom key
        System.out.println("\nPersone con città distinte (prima per città):");
        persone.stream()
            .collect(Collectors.toMap(
                Persona::getCitta,
                p -> p,
                (p1, p2) -> p1  // in caso di duplicato, prendi il primo
            ))
            .values()
            .forEach(p -> System.out.println("  " + p));

        // 5. Conditional collection
        System.out.println("\nConditional grouping:");
        Map<String, List<Persona>> conditional = persone.stream()
            .collect(Collectors.groupingBy(p -> 
                p.getEta() < 30 ? "Giovani" :
                p.getEta() < 40 ? "Adulti" : "Senior"
            ));
        conditional.forEach((key, lista) -> 
            System.out.println("  " + key + ": " + lista.size()));

        // 6. Nested collectors
        System.out.println("\nStipendio medio per città e dipartimento:");
        Map<String, Map<String, Double>> nested = persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.groupingBy(
                    Persona::getDipartimento,
                    Collectors.averagingDouble(Persona::getStipendio)
                )
            ));
        nested.forEach((citta, deptMap) -> {
            System.out.println("  " + citta + ":");
            deptMap.forEach((dept, media) -> 
                System.out.println("    " + dept + ": €" + String.format("%.2f", media)));
        });

        // 7. Combinare più streams
        Stream<String> stream1 = Stream.of("A", "B", "C");
        Stream<String> stream2 = Stream.of("D", "E", "F");
        List<String> combined = Stream.concat(stream1, stream2)
            .collect(Collectors.toList());
        System.out.println("\nStream combinati: " + combined);

        // 8. takeWhile e dropWhile (Java 9+)
        List<Integer> numeri = Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1);
        System.out.println("\nTakeWhile (< 4): " + 
            numeri.stream().takeWhile(n -> n < 4).collect(Collectors.toList()));
        System.out.println("DropWhile (< 4): " + 
            numeri.stream().dropWhile(n -> n < 4).collect(Collectors.toList()));

        System.out.println();
    }

    // ==================== CASI D'USO REALI ====================

    public void casiUsoReali() {
        System.out.println("=== CASI D'USO REALI ===");

        List<Persona> persone = creaPersone();
        List<Ordine> ordini = creaOrdini();

        // 1. Report stipendi per dipartimento
        System.out.println("REPORT STIPENDI PER DIPARTIMENTO:");
        persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getDipartimento,
                Collectors.summarizingDouble(Persona::getStipendio)
            ))
            .forEach((dept, stats) -> {
                System.out.println("  " + dept + ":");
                System.out.println("    Persone: " + stats.getCount());
                System.out.println("    Totale: €" + String.format("%.2f", stats.getSum()));
                System.out.println("    Media: €" + String.format("%.2f", stats.getAverage()));
                System.out.println("    Min: €" + String.format("%.2f", stats.getMin()));
                System.out.println("    Max: €" + String.format("%.2f", stats.getMax()));
            });

        // 2. Top 3 persone per stipendio
        System.out.println("\nTOP 3 STIPENDI:");
        persone.stream()
            .sorted(Comparator.comparing(Persona::getStipendio).reversed())
            .limit(3)
            .forEach(p -> System.out.println("  " + p.getNome() + 
                ": €" + String.format("%.2f", p.getStipendio())));

        // 3. Trova persone con hobby in comune
        System.out.println("\nHOBBY PIÙ POPOLARI:");
        persone.stream()
            .flatMap(p -> p.getHobby().stream())
            .collect(Collectors.groupingBy(h -> h, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue() + " persone"));

        // 4. Analisi ordini
        System.out.println("\nANALISI ORDINI:");
        double totaleOrdini = ordini.stream()
            .mapToDouble(Ordine::getImporto)
            .sum();
        System.out.println("  Totale vendite: €" + String.format("%.2f", totaleOrdini));

        Map<String, Long> ordiniPerStato = ordini.stream()
            .collect(Collectors.groupingBy(Ordine::getStato, Collectors.counting()));
        System.out.println("  Ordini per stato: " + ordiniPerStato);

        Map<String, Double> venditaPerCliente = ordini.stream()
            .collect(Collectors.groupingBy(
                Ordine::getCliente,
                Collectors.summingDouble(Ordine::getImporto)
            ));
        System.out.println("  Vendita per cliente:");
        venditaPerCliente.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.println("    " + e.getKey() + 
                ": €" + String.format("%.2f", e.getValue())));

        // 5. Prodotti più venduti
        System.out.println("\nPRODOTTI PIÙ VENDUTI:");
        ordini.stream()
            .flatMap(o -> o.getProdotti().stream())
            .collect(Collectors.groupingBy(
                Prodotto::getNome,
                Collectors.summingInt(Prodotto::getQuantita)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue() + " unità"));

        // 6. Fatturato per categoria
        System.out.println("\nFATTURATO PER CATEGORIA:");
        ordini.stream()
            .flatMap(o -> o.getProdotti().stream())
            .collect(Collectors.groupingBy(
                Prodotto::getCategoria,
                Collectors.summingDouble(p -> p.getPrezzo() * p.getQuantita())
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.println("  " + e.getKey() + 
                ": €" + String.format("%.2f", e.getValue())));

        // 7. Trova clienti con ordini completati
        System.out.println("\nCLIENTI CON ORDINI COMPLETATI:");
        Set<String> clientiCompletati = ordini.stream()
            .filter(o -> o.getStato().equals("Completato"))
            .map(Ordine::getCliente)
            .collect(Collectors.toSet());
        System.out.println("  " + clientiConCompletati);

        // 8. Media età per città con almeno 2 persone
        System.out.println("\nMEDIA ETÀ PER CITTÀ (min 2 persone):");
        persone.stream()
            .collect(Collectors.groupingBy(
                Persona::getCitta,
                Collectors.toList()
            ))
            .entrySet().stream()
            .filter(e -> e.getValue().size() >= 2)
            .forEach(e -> {
                double mediaEta = e.getValue().stream()
                    .mapToInt(Persona::getEta)
                    .average()
                    .orElse(0);
                System.out.println("  " + e.getKey() + " (" + e.getValue().size() + 
                    " persone): " + String.format("%.1f", mediaEta) + " anni");
            });

        System.out.println();
    }
}
