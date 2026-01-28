package com.corso.samples.datatypes;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Esempio completo e dettagliato sull'uso delle date in Java
 * Utilizza le moderne API java.time (Java 8+)
 */
public class DateDemo {

    public static void sample() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   ESEMPIO COMPLETO SULL'USO DELLE DATE IN JAVA     ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        esempiLocalDate();
        esempiLocalTime();
        esempiLocalDateTime();
        esempiZonedDateTime();
        esempiInstant();
        formattazioneDate();
        parsingDate();
        operazioniSuDate();
        confrontoDate();
        calcoloDifferenze();
        periodoEDurata();
        adjusters();
    }

    /**
     * Esempi con LocalDate - Rappresenta una data senza ora (es: 2026-01-28)
     */
    public static void esempiLocalDate() {
        System.out.println("\n【 1. LOCAL DATE - Data senza ora 】");
        System.out.println("─".repeat(50));

        // Creazione date
        LocalDate oggi = LocalDate.now();
        LocalDate dataSpecifica = LocalDate.of(2026, 1, 28);
        LocalDate dataConMese = LocalDate.of(2026, Month.JANUARY, 28);
        LocalDate dataDaStringa = LocalDate.parse("2026-01-28");

        System.out.println("Data di oggi: " + oggi);
        System.out.println("Data specifica: " + dataSpecifica);
        System.out.println("Data con Month enum: " + dataConMese);
        System.out.println("Data da stringa: " + dataDaStringa);

        // Estrazione componenti
        System.out.println("\nComponenti della data:");
        System.out.println("  Anno: " + oggi.getYear());
        System.out.println("  Mese (numero): " + oggi.getMonthValue());
        System.out.println("  Mese (nome): " + oggi.getMonth());
        System.out.println("  Giorno del mese: " + oggi.getDayOfMonth());
        System.out.println("  Giorno dell'anno: " + oggi.getDayOfYear());
        System.out.println("  Giorno della settimana: " + oggi.getDayOfWeek());
        System.out.println("  È anno bisestile? " + oggi.isLeapYear());
    }

    /**
     * Esempi con LocalTime - Rappresenta un'ora senza data (es: 14:30:00)
     */
    public static void esempiLocalTime() {
        System.out.println("\n【 2. LOCAL TIME - Ora senza data 】");
        System.out.println("─".repeat(50));

        LocalTime adesso = LocalTime.now();
        LocalTime oraSpecifica = LocalTime.of(14, 30, 0);
        LocalTime oraConNano = LocalTime.of(14, 30, 45, 123456789);
        LocalTime oraDaStringa = LocalTime.parse("14:30:00");

        System.out.println("Ora attuale: " + adesso);
        System.out.println("Ora specifica: " + oraSpecifica);
        System.out.println("Ora con nanosecondi: " + oraConNano);
        System.out.println("Ora da stringa: " + oraDaStringa);

        // Estrazione componenti
        System.out.println("\nComponenti dell'ora:");
        System.out.println("  Ore: " + adesso.getHour());
        System.out.println("  Minuti: " + adesso.getMinute());
        System.out.println("  Secondi: " + adesso.getSecond());
        System.out.println("  Nanosecondi: " + adesso.getNano());
    }

    /**
     * Esempi con LocalDateTime - Rappresenta data e ora senza fuso orario
     */
    public static void esempiLocalDateTime() {
        System.out.println("\n【 3. LOCAL DATE TIME - Data e ora senza fuso orario 】");
        System.out.println("─".repeat(50));

        LocalDateTime adesso = LocalDateTime.now();
        LocalDateTime dataOraSpecifica = LocalDateTime.of(2026, 1, 28, 14, 30, 0);
        LocalDateTime combinata = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));

        System.out.println("Data e ora attuali: " + adesso);
        System.out.println("Data e ora specifica: " + dataOraSpecifica);
        System.out.println("Data e ora combinate: " + combinata);

        // Conversione
        LocalDate soloData = adesso.toLocalDate();
        LocalTime soloOra = adesso.toLocalTime();
        System.out.println("\nEstratto solo data: " + soloData);
        System.out.println("Estratto solo ora: " + soloOra);
    }

    /**
     * Esempi con ZonedDateTime - Data e ora con fuso orario
     */
    public static void esempiZonedDateTime() {
        System.out.println("\n【 4. ZONED DATE TIME - Data e ora con fuso orario 】");
        System.out.println("─".repeat(50));

        ZonedDateTime adesso = ZonedDateTime.now();
        ZonedDateTime newYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime roma = ZonedDateTime.now(ZoneId.of("Europe/Rome"));

        System.out.println("Zona attuale: " + adesso);
        System.out.println("New York: " + newYork);
        System.out.println("Tokyo: " + tokyo);
        System.out.println("Roma: " + roma);

        // Conversione tra fusi orari
        ZonedDateTime conversione = adesso.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println("\nConversione a Los Angeles: " + conversione);

        // Zone disponibili (mostriamo solo alcune)
        System.out.println("\nAlcune zone disponibili:");
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.startsWith("Europe/") || z.startsWith("America/"))
                .sorted()
                .limit(10)
                .forEach(z -> System.out.println("  - " + z));
    }

    /**
     * Esempi con Instant - Timestamp in millisecondi dall'epoch (1970-01-01T00:00:00Z)
     */
    public static void esempiInstant() {
        System.out.println("\n【 5. INSTANT - Timestamp assoluto 】");
        System.out.println("─".repeat(50));

        Instant adesso = Instant.now();
        Instant epochStart = Instant.EPOCH;
        Instant daMillisecondi = Instant.ofEpochMilli(System.currentTimeMillis());

        System.out.println("Instant attuale: " + adesso);
        System.out.println("Inizio dell'epoch: " + epochStart);
        System.out.println("Da millisecondi: " + daMillisecondi);
        System.out.println("Millisecondi dall'epoch: " + adesso.toEpochMilli());
        System.out.println("Secondi dall'epoch: " + adesso.getEpochSecond());

        // Conversione a ZonedDateTime
        ZonedDateTime zonedFromInstant = adesso.atZone(ZoneId.systemDefault());
        System.out.println("Convertito a ZonedDateTime: " + zonedFromInstant);
    }

    /**
     * Formattazione delle date in vari formati
     */
    public static void formattazioneDate() {
        System.out.println("\n【 6. FORMATTAZIONE DATE 】");
        System.out.println("─".repeat(50));

        LocalDateTime dataOra = LocalDateTime.now();

        // Formatter predefiniti
        System.out.println("Formati predefiniti:");
        System.out.println("  ISO_LOCAL_DATE: " + dataOra.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("  ISO_LOCAL_TIME: " + dataOra.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("  ISO_LOCAL_DATE_TIME: " + dataOra.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // Formatter con stili
        System.out.println("\nStili predefiniti:");
        System.out.println("  FULL: " + dataOra.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
        System.out.println("  LONG: " + dataOra.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        System.out.println("  MEDIUM: " + dataOra.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println("  SHORT: " + dataOra.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

        // Formatter personalizzati
        System.out.println("\nFormati personalizzati:");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ITALIAN);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd-MMM-yy h:mm a", Locale.ENGLISH);

        System.out.println("  " + dataOra.format(formatter1));
        System.out.println("  " + dataOra.format(formatter2));
        System.out.println("  " + dataOra.format(formatter3));
        System.out.println("  " + dataOra.format(formatter4));

        // Pattern comuni
        System.out.println("\nPattern comuni:");
        System.out.println("  dd/MM/yyyy: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("  MM-dd-yyyy: " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        System.out.println("  yyyy/MM/dd: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        System.out.println("  HH:mm:ss: " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /**
     * Parsing (conversione da stringa a data)
     */
    public static void parsingDate() {
        System.out.println("\n【 7. PARSING - Conversione stringhe in date 】");
        System.out.println("─".repeat(50));

        // Parsing con formatter predefiniti
        LocalDate data1 = LocalDate.parse("2026-01-28");
        LocalTime ora1 = LocalTime.parse("14:30:00");
        LocalDateTime dataOra1 = LocalDateTime.parse("2026-01-28T14:30:00");

        System.out.println("Parsing con formato ISO:");
        System.out.println("  Data: " + data1);
        System.out.println("  Ora: " + ora1);
        System.out.println("  DataOra: " + dataOra1);

        // Parsing con formatter personalizzati
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ITALIAN);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate data2 = LocalDate.parse("28/01/2026", formatter1);
        LocalDate data3 = LocalDate.parse("28-gen-2026", formatter2);
        LocalDateTime dataOra2 = LocalDateTime.parse("2026-01-28 14:30:00", formatter3);

        System.out.println("\nParsing con formati personalizzati:");
        System.out.println("  " + data2);
        System.out.println("  " + data3);
        System.out.println("  " + dataOra2);
    }

    /**
     * Operazioni sulle date (aggiungere/sottrarre)
     */
    public static void operazioniSuDate() {
        System.out.println("\n【 8. OPERAZIONI SULLE DATE 】");
        System.out.println("─".repeat(50));

        LocalDate oggi = LocalDate.now();

        // Aggiungere tempo
        System.out.println("Data di partenza: " + oggi);
        System.out.println("\nAggiungere:");
        System.out.println("  +7 giorni: " + oggi.plusDays(7));
        System.out.println("  +2 settimane: " + oggi.plusWeeks(2));
        System.out.println("  +3 mesi: " + oggi.plusMonths(3));
        System.out.println("  +1 anno: " + oggi.plusYears(1));

        // Sottrarre tempo
        System.out.println("\nSottrarre:");
        System.out.println("  -10 giorni: " + oggi.minusDays(10));
        System.out.println("  -1 mese: " + oggi.minusMonths(1));
        System.out.println("  -1 anno: " + oggi.minusYears(1));

        // Operazioni con LocalDateTime
        LocalDateTime oraAttuale = LocalDateTime.now();
        System.out.println("\nOperazioni con ora:");
        System.out.println("  Ora attuale: " + oraAttuale);
        System.out.println("  +3 ore: " + oraAttuale.plusHours(3));
        System.out.println("  +45 minuti: " + oraAttuale.plusMinutes(45));
        System.out.println("  +30 secondi: " + oraAttuale.plusSeconds(30));

        // Modificare componenti specifici
        System.out.println("\nModificare componenti:");
        System.out.println("  Con anno 2025: " + oggi.withYear(2025));
        System.out.println("  Con mese dicembre: " + oggi.withMonth(12));
        System.out.println("  Con giorno 15: " + oggi.withDayOfMonth(15));
    }

    /**
     * Confronto tra date
     */
    public static void confrontoDate() {
        System.out.println("\n【 9. CONFRONTO TRA DATE 】");
        System.out.println("─".repeat(50));

        LocalDate oggi = LocalDate.now();
        LocalDate domani = oggi.plusDays(1);
        LocalDate ieri = oggi.minusDays(1);
        LocalDate compleanno = LocalDate.of(2026, 6, 15);

        System.out.println("Oggi: " + oggi);
        System.out.println("Domani: " + domani);
        System.out.println("Ieri: " + ieri);
        System.out.println("Compleanno: " + compleanno);

        // Metodi di confronto
        System.out.println("\nConfronto con equals:");
        System.out.println("  oggi == oggi: " + oggi.equals(oggi));
        System.out.println("  oggi == domani: " + oggi.equals(domani));

        System.out.println("\nConfronto con isAfter/isBefore:");
        System.out.println("  domani è dopo oggi? " + domani.isAfter(oggi));
        System.out.println("  ieri è prima di oggi? " + ieri.isBefore(oggi));
        System.out.println("  compleanno è dopo oggi? " + compleanno.isAfter(oggi));

        System.out.println("\nConfronto con compareTo:");
        System.out.println("  oggi.compareTo(domani): " + oggi.compareTo(domani) + " (negativo = prima)");
        System.out.println("  domani.compareTo(oggi): " + domani.compareTo(oggi) + " (positivo = dopo)");
        System.out.println("  oggi.compareTo(oggi): " + oggi.compareTo(oggi) + " (zero = uguale)");

        // Verifica intervalli
        LocalDate inizio = LocalDate.of(2026, 1, 1);
        LocalDate fine = LocalDate.of(2026, 12, 31);
        System.out.println("\nOggi è nel 2026?");
        System.out.println("  " + (!oggi.isBefore(inizio) && !oggi.isAfter(fine)));
    }

    /**
     * Calcolo differenze tra date
     */
    public static void calcoloDifferenze() {
        System.out.println("\n【 10. CALCOLO DIFFERENZE TRA DATE 】");
        System.out.println("─".repeat(50));

        LocalDate dataInizio = LocalDate.of(2026, 1, 1);
        LocalDate dataFine = LocalDate.now();

        System.out.println("Data inizio: " + dataInizio);
        System.out.println("Data fine: " + dataFine);

        // Calcolo con ChronoUnit
        long giorni = ChronoUnit.DAYS.between(dataInizio, dataFine);
        long settimane = ChronoUnit.WEEKS.between(dataInizio, dataFine);
        long mesi = ChronoUnit.MONTHS.between(dataInizio, dataFine);
        long anni = ChronoUnit.YEARS.between(dataInizio, dataFine);

        System.out.println("\nDifferenza:");
        System.out.println("  Giorni: " + giorni);
        System.out.println("  Settimane: " + settimane);
        System.out.println("  Mesi: " + mesi);
        System.out.println("  Anni: " + anni);

        // Differenza tra orari
        LocalDateTime oraInizio = LocalDateTime.of(2026, 1, 28, 9, 0, 0);
        LocalDateTime oraFine = LocalDateTime.of(2026, 1, 28, 17, 30, 0);

        long ore = ChronoUnit.HOURS.between(oraInizio, oraFine);
        long minuti = ChronoUnit.MINUTES.between(oraInizio, oraFine);
        long secondi = ChronoUnit.SECONDS.between(oraInizio, oraFine);

        System.out.println("\nDifferenza oraria (9:00 - 17:30):");
        System.out.println("  Ore: " + ore);
        System.out.println("  Minuti: " + minuti);
        System.out.println("  Secondi: " + secondi);
    }

    /**
     * Period e Duration per rappresentare periodi di tempo
     */
    public static void periodoEDurata() {
        System.out.println("\n【 11. PERIOD E DURATION 】");
        System.out.println("─".repeat(50));

        // Period - per date (anni, mesi, giorni)
        LocalDate nascita = LocalDate.of(1990, 5, 15);
        LocalDate oggi = LocalDate.now();
        Period eta = Period.between(nascita, oggi);

        System.out.println("PERIOD (per date):");
        System.out.println("  Data di nascita: " + nascita);
        System.out.println("  Oggi: " + oggi);
        System.out.println("  Età: " + eta.getYears() + " anni, " +
                eta.getMonths() + " mesi, " +
                eta.getDays() + " giorni");

        // Creazione Period
        Period periodo1 = Period.ofDays(10);
        Period periodo2 = Period.ofWeeks(2);
        Period periodo3 = Period.ofMonths(3);
        Period periodo4 = Period.ofYears(1);
        Period periodo5 = Period.of(1, 6, 15); // 1 anno, 6 mesi, 15 giorni

        System.out.println("\n  10 giorni dopo oggi: " + oggi.plus(periodo1));
        System.out.println("  2 settimane dopo oggi: " + oggi.plus(periodo2));
        System.out.println("  3 mesi dopo oggi: " + oggi.plus(periodo3));
        System.out.println("  1 anno dopo oggi: " + oggi.plus(periodo4));
        System.out.println("  1y 6m 15d dopo oggi: " + oggi.plus(periodo5));

        // Duration - per tempo (ore, minuti, secondi)
        LocalTime inizio = LocalTime.of(9, 0);
        LocalTime fine = LocalTime.of(17, 30);
        Duration durataLavoro = Duration.between(inizio, fine);

        System.out.println("\nDURATION (per tempo):");
        System.out.println("  Inizio lavoro: " + inizio);
        System.out.println("  Fine lavoro: " + fine);
        System.out.println("  Durata: " + durataLavoro.toHours() + " ore e " +
                (durataLavoro.toMinutes() % 60) + " minuti");

        // Creazione Duration
        Duration durata1 = Duration.ofHours(2);
        Duration durata2 = Duration.ofMinutes(30);
        Duration durata3 = Duration.ofSeconds(45);

        LocalDateTime adesso = LocalDateTime.now();
        System.out.println("\n  Ora attuale: " + adesso.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("  +2 ore: " + adesso.plus(durata1).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("  +30 minuti: " + adesso.plus(durata2).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("  +45 secondi: " + adesso.plus(durata3).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /**
     * TemporalAdjusters - modificatori avanzati per le date
     */
    public static void adjusters() {
        System.out.println("\n【 12. TEMPORAL ADJUSTERS - Modificatori avanzati 】");
        System.out.println("─".repeat(50));

        LocalDate oggi = LocalDate.now();

        System.out.println("Data di partenza: " + oggi);
        System.out.println("\nAdjusters predefiniti:");

        // Adjusters comuni
        System.out.println("  Primo giorno del mese: " +
                oggi.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("  Ultimo giorno del mese: " +
                oggi.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("  Primo giorno dell'anno: " +
                oggi.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println("  Ultimo giorno dell'anno: " +
                oggi.with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("  Primo giorno del prossimo mese: " +
                oggi.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println("  Primo giorno del prossimo anno: " +
                oggi.with(TemporalAdjusters.firstDayOfNextYear()));

        // Adjusters per giorni della settimana
        System.out.println("\nGiorni della settimana:");
        System.out.println("  Primo lunedì del mese: " +
                oggi.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("  Ultimo venerdì del mese: " +
                oggi.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
        System.out.println("  Prossimo lunedì: " +
                oggi.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.println("  Prossimo o stesso lunedì: " +
                oggi.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));
        System.out.println("  Precedente domenica: " +
                oggi.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)));

        // Esempi pratici
        System.out.println("\nEsempi pratici:");
        LocalDate inizioMese = oggi.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate fineMese = oggi.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("  Giorni nel mese corrente: " +
                ChronoUnit.DAYS.between(inizioMese, fineMese) + 1);

        LocalDate prossimoCompleanno = LocalDate.of(oggi.getYear(), 6, 15);
        if (prossimoCompleanno.isBefore(oggi)) {
            prossimoCompleanno = prossimoCompleanno.plusYears(1);
        }
        System.out.println("  Giorni al prossimo 15 giugno: " +
                ChronoUnit.DAYS.between(oggi, prossimoCompleanno));
    }
}

