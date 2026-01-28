package com.corso.samples.javabase;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * Esempio completo e avanzato su ISTRUZIONI CONDIZIONALI in Java
 *
 * TIPI DI ISTRUZIONI CONDIZIONALI:
 * 1. if - Esegue codice se condizione è vera
 * 2. if-else - Scelta binaria tra due alternative
 * 3. if-else if-else - Scelta multipla sequenziale
 * 4. switch classico - Selezione multipla su valore
 * 5. switch expression (Java 14+) - Switch che restituisce valore
 * 6. switch con pattern matching (Java 17+) - Switch con type patterns
 * 7. Operatore ternario (? :) - Condizione inline
 * 8. Null checking - Gestione valori null
 * 9. Condizioni annidate - Logica complessa
 * 10. Short-circuit evaluation - Ottimizzazione valutazione
 */
public class CondizioniDemo {

    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ISTRUZIONI CONDIZIONALI IN JAVA                 ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        istruzioneSempliceIf();
        istruzioneIfElse();
        istruzioneIfElseIf();
        switchClassico();
        switchExpression();
        switchPatternMatching();
        operatoreTernario();
        nullChecking();
        condizioniAnnidate();
        shortCircuitEvaluation();
        guardConditions();
        scenariAvanzati();
        bestPractices();
    }

    /**
     * ISTRUZIONE IF SEMPLICE
     *
     * Sintassi: if (condizione) { ... }
     *
     * CARATTERISTICHE:
     * - Esegue il blocco SOLO se la condizione è true
     * - Se false, il blocco viene saltato
     * - Può esistere senza else
     */
    public static void istruzioneSempliceIf() {
        System.out.println("【 1. ISTRUZIONE IF SEMPLICE 】");
        System.out.println("─".repeat(50));

        // Esempio base
        int eta = 18;
        System.out.println("Età: " + eta);
        if (eta >= 18) {
            System.out.println("  ✓ Maggiorenne");
        }

        // Senza blocco (una sola istruzione)
        int numero = 10;
        if (numero > 0)
            System.out.println("  " + numero + " è positivo");

        // Condizioni multiple con AND
        int voto = 28;
        boolean presente = true;
        if (voto >= 18 && presente) {
            System.out.println("  ✓ Esame superato e presenza confermata");
        }

        // Condizioni multiple con OR
        String giorno = "Sabato";
        if (giorno.equals("Sabato") || giorno.equals("Domenica")) {
            System.out.println("  🎉 Weekend!");
        }

        // Condizione con NOT
        boolean piove = false;
        if (!piove) {
            System.out.println("  ☀ Bel tempo!");
        }

        // Condizione su oggetti
        String testo = "Java";
        if (testo != null && !testo.isEmpty()) {
            System.out.println("  Testo valido: " + testo);
        }

        // Condizioni su collezioni
        List<String> lista = Arrays.asList("A", "B", "C");
        if (lista.contains("B")) {
            System.out.println("  Lista contiene 'B'");
        }

        // Condizione complessa
        int x = 5;
        if (x > 0 && x < 10 && x % 2 != 0) {
            System.out.println("  " + x + " è un numero dispari tra 0 e 10");
        }
    }

    /**
     * ISTRUZIONE IF-ELSE
     *
     * Sintassi: if (condizione) { ... } else { ... }
     *
     * CARATTERISTICHE:
     * - Scelta BINARIA: esegue un blocco O l'altro
     * - Il blocco else viene eseguito se condizione è false
     * - Garantisce che uno dei due blocchi venga eseguito
     */
    public static void istruzioneIfElse() {
        System.out.println("\n【 2. ISTRUZIONE IF-ELSE 】");
        System.out.println("─".repeat(50));

        // Esempio base
        int numero = 7;
        if (numero % 2 == 0) {
            System.out.println(numero + " è pari");
        } else {
            System.out.println(numero + " è dispari");
        }

        // Controllo età
        int eta = 15;
        if (eta >= 18) {
            System.out.println("Accesso consentito (età: " + eta + ")");
        } else {
            System.out.println("Accesso negato - minorenne (età: " + eta + ")");
        }

        // Confronto valori
        int a = 10, b = 20;
        if (a > b) {
            System.out.println(a + " è maggiore di " + b);
        } else {
            System.out.println(a + " è minore o uguale a " + b);
        }

        // Validazione input
        String username = "admin";
        if (username != null && username.length() >= 3) {
            System.out.println("✓ Username valido: " + username);
        } else {
            System.out.println("✗ Username non valido");
        }

        // Controllo range
        int temperatura = 25;
        if (temperatura >= 20 && temperatura <= 30) {
            System.out.println("Temperatura ideale: " + temperatura + "°C");
        } else {
            System.out.println("Temperatura fuori range: " + temperatura + "°C");
        }

        // Operazioni diverse nei due rami
        double saldo = 1000.0;
        double importo = 50.0;
        if (saldo >= importo) {
            saldo -= importo;
            System.out.println("✓ Prelievo effettuato. Saldo: €" + saldo);
        } else {
            System.out.println("✗ Saldo insufficiente. Disponibile: €" + saldo);
        }
    }

    /**
     * ISTRUZIONE IF-ELSE IF-ELSE
     *
     * Sintassi: if (cond1) {...} else if (cond2) {...} else {...}
     *
     * CARATTERISTICHE:
     * - Scelta MULTIPLA sequenziale
     * - Valuta le condizioni in ORDINE
     * - Si ferma alla PRIMA condizione vera
     * - else finale è opzionale (caso di default)
     */
    public static void istruzioneIfElseIf() {
        System.out.println("\n【 3. ISTRUZIONE IF-ELSE IF-ELSE 】");
        System.out.println("─".repeat(50));

        // Classificazione voti
        int voto = 28;
        System.out.println("Voto: " + voto);
        if (voto >= 30) {
            System.out.println("  Eccellente! 🌟");
        } else if (voto >= 24) {
            System.out.println("  Molto buono 👍");
        } else if (voto >= 18) {
            System.out.println("  Sufficiente ✓");
        } else {
            System.out.println("  Insufficiente ✗");
        }

        // Classificazione temperatura
        int temperatura = 15;
        System.out.println("\nTemperatura: " + temperatura + "°C");
        if (temperatura > 30) {
            System.out.println("  Molto caldo 🔥");
        } else if (temperatura > 20) {
            System.out.println("  Caldo ☀");
        } else if (temperatura > 10) {
            System.out.println("  Mite 🌤");
        } else if (temperatura > 0) {
            System.out.println("  Freddo ❄");
        } else {
            System.out.println("  Gelido 🥶");
        }

        // Fasce di età
        int eta = 35;
        System.out.println("\nEtà: " + eta);
        if (eta < 0) {
            System.out.println("  ✗ Età non valida");
        } else if (eta < 13) {
            System.out.println("  Bambino");
        } else if (eta < 18) {
            System.out.println("  Adolescente");
        } else if (eta < 65) {
            System.out.println("  Adulto");
        } else {
            System.out.println("  Senior");
        }

        // Calcolo sconto
        double totale = 250.0;
        double sconto = 0;
        System.out.println("\nTotale acquisto: €" + totale);
        if (totale >= 500) {
            sconto = 0.20; // 20%
        } else if (totale >= 200) {
            sconto = 0.10; // 10%
        } else if (totale >= 100) {
            sconto = 0.05; // 5%
        } else {
            sconto = 0.0;
        }
        double importoSconto = totale * sconto;
        double prezzoFinale = totale - importoSconto;
        System.out.println("  Sconto applicato: " + (sconto * 100) + "%");
        System.out.println("  Prezzo finale: €" + prezzoFinale);

        // Valutazione performance
        int vendite = 75;
        System.out.println("\nVendite: " + vendite);
        if (vendite >= 100) {
            System.out.println("  Performance: Eccezionale - Bonus 500€");
        } else if (vendite >= 80) {
            System.out.println("  Performance: Ottima - Bonus 300€");
        } else if (vendite >= 60) {
            System.out.println("  Performance: Buona - Bonus 150€");
        } else if (vendite >= 40) {
            System.out.println("  Performance: Sufficiente - Nessun bonus");
        } else {
            System.out.println("  Performance: Insufficiente - Piano di miglioramento");
        }
    }

    /**
     * SWITCH CLASSICO (Statement)
     *
     * Sintassi: switch (variabile) { case valore: ... break; }
     *
     * CARATTERISTICHE:
     * - Seleziona in base al VALORE di una variabile
     * - Supporta: byte, short, char, int, String, enum
     * - break: esce dallo switch (opzionale)
     * - default: caso quando nessun case corrisponde
     * - Fall-through: senza break, esegue anche i case successivi
     */
    public static void switchClassico() {
        System.out.println("\n【 4. SWITCH CLASSICO 】");
        System.out.println("─".repeat(50));

        // Switch con int
        int giorno = 3;
        System.out.println("Giorno (numero): " + giorno);
        switch (giorno) {
            case 1:
                System.out.println("  Lunedì");
                break;
            case 2:
                System.out.println("  Martedì");
                break;
            case 3:
                System.out.println("  Mercoledì");
                break;
            case 4:
                System.out.println("  Giovedì");
                break;
            case 5:
                System.out.println("  Venerdì");
                break;
            case 6:
                System.out.println("  Sabato");
                break;
            case 7:
                System.out.println("  Domenica");
                break;
            default:
                System.out.println("  Giorno non valido");
        }

        // Switch con String
        String mese = "Gennaio";
        System.out.println("\nMese: " + mese);
        switch (mese) {
            case "Gennaio":
            case "Febbraio":
            case "Dicembre":
                System.out.println("  Inverno ❄");
                break;
            case "Marzo":
            case "Aprile":
            case "Maggio":
                System.out.println("  Primavera 🌸");
                break;
            case "Giugno":
            case "Luglio":
            case "Agosto":
                System.out.println("  Estate ☀");
                break;
            case "Settembre":
            case "Ottobre":
            case "Novembre":
                System.out.println("  Autunno 🍂");
                break;
            default:
                System.out.println("  Mese non valido");
        }

        // Switch con enum
        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        System.out.println("\nGiorno della settimana: " + dayOfWeek);
        switch (dayOfWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("  Giorno lavorativo 💼");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("  Weekend! 🎉");
                break;
        }

        // Switch con char
        char voto = 'B';
        System.out.println("\nVoto (lettera): " + voto);
        switch (voto) {
            case 'A':
                System.out.println("  Eccellente (90-100)");
                break;
            case 'B':
                System.out.println("  Buono (80-89)");
                break;
            case 'C':
                System.out.println("  Sufficiente (70-79)");
                break;
            case 'D':
                System.out.println("  Insufficiente (60-69)");
                break;
            case 'F':
                System.out.println("  Bocciato (<60)");
                break;
            default:
                System.out.println("  Voto non valido");
        }

        // Fall-through intenzionale
        int mese_numero = 2;
        int anno = 2024;
        int giorni;
        System.out.println("\nGiorni nel mese " + mese_numero + " dell'anno " + anno + ":");
        switch (mese_numero) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                giorni = 31;
                break;
            case 4: case 6: case 9: case 11:
                giorni = 30;
                break;
            case 2:
                // Anno bisestile
                giorni = (anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0)) ? 29 : 28;
                break;
            default:
                giorni = -1;
        }
        System.out.println("  Giorni: " + giorni);
    }

    /**
     * SWITCH EXPRESSION (Java 14+)
     *
     * Sintassi: var risultato = switch (variabile) { case valore -> espressione; };
     *
     * CARATTERISTICHE:
     * - Restituisce un VALORE
     * - Sintassi arrow (->) più compatta
     * - Non serve break
     * - Deve essere ESAUSTIVO (coprire tutti i casi)
     * - yield: restituisce valore da blocco multi-linea
     */
    public static void switchExpression() {
        System.out.println("\n【 5. SWITCH EXPRESSION (Java 14+) 】");
        System.out.println("─".repeat(50));

        // Switch expression base
        int giorno = 4;
        String nomeGiorno = switch (giorno) {
            case 1 -> "Lunedì";
            case 2 -> "Martedì";
            case 3 -> "Mercoledì";
            case 4 -> "Giovedì";
            case 5 -> "Venerdì";
            case 6 -> "Sabato";
            case 7 -> "Domenica";
            default -> "Non valido";
        };
        System.out.println("Giorno " + giorno + ": " + nomeGiorno);

        // Switch con multipli valori
        String tipoGiorno = switch (giorno) {
            case 1, 2, 3, 4, 5 -> "Giorno lavorativo";
            case 6, 7 -> "Weekend";
            default -> "Non valido";
        };
        System.out.println("Tipo: " + tipoGiorno);

        // Switch con yield (blocco multi-linea)
        int mese = 7;
        String stagione = switch (mese) {
            case 12, 1, 2 -> {
                System.out.println("  Mesi invernali");
                yield "Inverno";
            }
            case 3, 4, 5 -> {
                System.out.println("  Mesi primaverili");
                yield "Primavera";
            }
            case 6, 7, 8 -> {
                System.out.println("  Mesi estivi");
                yield "Estate";
            }
            case 9, 10, 11 -> {
                System.out.println("  Mesi autunnali");
                yield "Autunno";
            }
            default -> "Non valido";
        };
        System.out.println("Stagione per mese " + mese + ": " + stagione);

        // Calcolo con switch expression
        String operazione = "+";
        int a = 10, b = 5;
        int risultato = switch (operazione) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0;
            default -> {
                System.out.println("  ✗ Operazione non valida");
                yield 0;
            }
        };
        System.out.println(a + " " + operazione + " " + b + " = " + risultato);

        // Switch con enum
        DayOfWeek day = DayOfWeek.SATURDAY;
        boolean isWeekend = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
            case SATURDAY, SUNDAY -> true;
        };
        System.out.println(day + " è weekend? " + isWeekend);

        // Calcolo costo spedizione
        String destinazione = "Europa";
        double peso = 2.5; // kg
        double costoSpedizione = switch (destinazione) {
            case "Italia" -> peso * 5.0;
            case "Europa" -> peso * 10.0;
            case "USA", "Canada" -> peso * 20.0;
            case "Asia", "Australia" -> peso * 30.0;
            default -> {
                System.out.println("  Destinazione non supportata");
                yield 0.0;
            }
        };
        System.out.println("Spedizione " + destinazione + " (" + peso + "kg): €" + costoSpedizione);
    }

    /**
     * SWITCH con PATTERN MATCHING (Java 17+)
     *
     * CARATTERISTICHE:
     * - Type patterns: verifica tipo e cast automatico
     * - Guarded patterns: condizioni aggiuntive
     * - Null handling: gestione esplicita di null
     * - Record patterns (Java 19+)
     */
    public static void switchPatternMatching() {
        System.out.println("\n【 6. SWITCH PATTERN MATCHING (Java 17+) 】");
        System.out.println("─".repeat(50));

        // Pattern matching con type patterns
        Object obj = "Hello";
        String descrizione = switch (obj) {
            case null -> "Valore null";
            case String s -> "Stringa di lunghezza " + s.length() + ": " + s;
            case Integer i -> "Intero: " + i + " (doppio: " + (i * 2) + ")";
            case Long l -> "Long: " + l;
            case Double d -> "Double: " + d;
            case int[] arr -> "Array di int con " + arr.length + " elementi";
            case List<?> list -> "List con " + list.size() + " elementi";
            default -> "Tipo: " + obj.getClass().getSimpleName();
        };
        System.out.println("Oggetto: " + descrizione);

        // Pattern con guardie (condizioni aggiuntive)
        Object numero = 42;
        String classificazione = switch (numero) {
            case null -> "Null";
            case Integer i when i < 0 -> "Negativo: " + i;
            case Integer i when i == 0 -> "Zero";
            case Integer i when i > 0 && i <= 10 -> "Piccolo positivo: " + i;
            case Integer i when i > 10 && i <= 100 -> "Medio: " + i;
            case Integer i when i > 100 -> "Grande: " + i;
            case String s -> "Stringa: " + s;
            default -> "Altro tipo";
        };
        System.out.println("Classificazione: " + classificazione);

        // Esempi con diversi tipi
        elaboraOggetto(null);
        elaboraOggetto(42);
        elaboraOggetto("Java");
        elaboraOggetto(Arrays.asList("A", "B", "C"));
        elaboraOggetto(new int[]{1, 2, 3, 4, 5});

        // Pattern matching per validazione
        Object input = -5;
        boolean valido = switch (input) {
            case null -> false;
            case Integer i when i >= 0 && i <= 100 -> true;
            case String s when !s.isEmpty() -> true;
            default -> false;
        };
        System.out.println("Input " + input + " valido? " + valido);
    }

    // Metodo di supporto per pattern matching
    private static void elaboraOggetto(Object obj) {
        String risultato = switch (obj) {
            case null -> "  → Null ricevuto";
            case String s when s.isEmpty() -> "  → Stringa vuota";
            case String s when s.length() < 5 -> "  → Stringa corta: '" + s + "'";
            case String s -> "  → Stringa: '" + s + "' (" + s.length() + " caratteri)";
            case Integer i when i < 0 -> "  → Intero negativo: " + i;
            case Integer i when i == 0 -> "  → Zero";
            case Integer i -> "  → Intero positivo: " + i;
            case List<?> list when list.isEmpty() -> "  → Lista vuota";
            case List<?> list -> "  → Lista con " + list.size() + " elementi: " + list;
            case int[] arr -> "  → Array: " + Arrays.toString(arr);
            default -> "  → Tipo: " + obj.getClass().getSimpleName();
        };
        System.out.println(risultato);
    }

    /**
     * OPERATORE TERNARIO (? :)
     *
     * Sintassi: condizione ? valoreSeVero : valoreSeFalso
     *
     * CARATTERISTICHE:
     * - Condizione INLINE compatta
     * - Restituisce sempre un valore
     * - Utile per assegnazioni condizionali
     * - Può essere annidato (ma riduce leggibilità)
     */
    public static void operatoreTernario() {
        System.out.println("\n【 7. OPERATORE TERNARIO (? :) 】");
        System.out.println("─".repeat(50));

        // Esempio base
        int eta = 20;
        String categoria = (eta >= 18) ? "Maggiorenne" : "Minorenne";
        System.out.println("Età " + eta + ": " + categoria);

        // Assegnazione condizionale
        int numero = -5;
        int assoluto = (numero >= 0) ? numero : -numero;
        System.out.println("Valore assoluto di " + numero + ": " + assoluto);

        // Trovare max/min
        int a = 10, b = 20;
        int max = (a > b) ? a : b;
        int min = (a < b) ? a : b;
        System.out.println("Max(" + a + ", " + b + ") = " + max);
        System.out.println("Min(" + a + ", " + b + ") = " + min);

        // Validazione
        String username = "admin";
        String messaggio = (username != null && username.length() >= 3)
                ? "✓ Username valido"
                : "✗ Username troppo corto";
        System.out.println(messaggio);

        // Formattazione output
        int items = 1;
        String testo = items + " element" + (items == 1 ? "" : "i");
        System.out.println(testo);

        items = 5;
        testo = items + " element" + (items == 1 ? "o" : "i");
        System.out.println(testo);

        // Ternario annidato (uso cauto!)
        int voto = 28;
        String valutazione = (voto >= 30) ? "Eccellente" :
                (voto >= 24) ? "Molto buono" :
                        (voto >= 18) ? "Sufficiente" : "Insufficiente";
        System.out.println("Voto " + voto + ": " + valutazione);

        // Calcolo sconto
        double totale = 150.0;
        double sconto = (totale >= 100) ? totale * 0.10 : 0.0;
        System.out.println("Totale: €" + totale + ", Sconto: €" + sconto);

        // Null coalescing simulation
        String nome = null;
        String nomeVisualizzato = (nome != null) ? nome : "Anonimo";
        System.out.println("Nome: " + nomeVisualizzato);

        // Operazioni diverse
        boolean isPositivo = numero > 0;
        System.out.println(numero + " è " + (isPositivo ? "positivo" : "negativo o zero"));

        // Con chiamate a metodi
        int[] array = {1, 2, 3};
        String lunghezza = (array != null) ? "Array con " + array.length + " elementi" : "Array null";
        System.out.println(lunghezza);
    }

    /**
     * NULL CHECKING
     *
     * TECNICHE:
     * - if (obj != null) - Check esplicito
     * - Objects.isNull() / Objects.nonNull() - Metodi utility
     * - Objects.requireNonNull() - Validazione con eccezione
     * - Optional - Gestione funzionale di valori potenzialmente null
     */
    public static void nullChecking() {
        System.out.println("\n【 8. NULL CHECKING 】");
        System.out.println("─".repeat(50));

        // Check esplicito
        String testo = null;
        if (testo != null) {
            System.out.println("Lunghezza: " + testo.length());
        } else {
            System.out.println("Testo è null");
        }

        // Check con operazioni
        testo = "Java";
        if (testo != null && !testo.isEmpty()) {
            System.out.println("✓ Testo valido: " + testo);
        }

        // Objects.isNull() e Objects.nonNull()
        String nome = null;
        if (Objects.isNull(nome)) {
            System.out.println("Nome è null");
        }

        nome = "Mario";
        if (Objects.nonNull(nome)) {
            System.out.println("Nome presente: " + nome);
        }

        // Objects.requireNonNull() - Lancia NullPointerException se null
        try {
            String required = Objects.requireNonNull(nome, "Nome non può essere null");
            System.out.println("Nome richiesto: " + required);
        } catch (NullPointerException e) {
            System.out.println("✗ " + e.getMessage());
        }

        // Null coalescing con metodo
        String valore = null;
        String risultato = Objects.requireNonNullElse(valore, "Valore di default");
        System.out.println("Risultato: " + risultato);

        // Null coalescing con supplier
        risultato = Objects.requireNonNullElseGet(valore, () -> "Calcolato dinamicamente");
        System.out.println("Risultato con supplier: " + risultato);

        // Optional - Approccio funzionale
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println("Optional presente? " + optional.isPresent());

        optional = Optional.of("Valore");
        optional.ifPresent(v -> System.out.println("Valore presente: " + v));

        // Optional con default
        String defaultValue = optional.orElse("Default");
        System.out.println("Con orElse: " + defaultValue);

        optional = Optional.empty();
        defaultValue = optional.orElse("Default per empty");
        System.out.println("Empty con orElse: " + defaultValue);

        // Optional con supplier
        defaultValue = optional.orElseGet(() -> "Calcolato per empty");
        System.out.println("Empty con orElseGet: " + defaultValue);

        // Optional con trasformazioni
        Optional<String> optionalNome = Optional.of("mario");
        String nomeMaiuscolo = optionalNome
                .map(String::toUpperCase)
                .orElse("NESSUN NOME");
        System.out.println("Nome trasformato: " + nomeMaiuscolo);

        // Chain di operazioni null-safe
        String email = null;
        String dominio = Optional.ofNullable(email)
                .filter(e -> e.contains("@"))
                .map(e -> e.substring(e.indexOf("@") + 1))
                .orElse("dominio sconosciuto");
        System.out.println("Dominio email: " + dominio);
    }

    /**
     * CONDIZIONI ANNIDATE
     *
     * CARATTERISTICHE:
     * - Condizioni dentro altre condizioni
     * - Utile per logica complessa
     * - Attenzione all'indentazione e leggibilità
     */
    public static void condizioniAnnidate() {
        System.out.println("\n【 9. CONDIZIONI ANNIDATE 】");
        System.out.println("─".repeat(50));

        // Esempio: validazione utente
        String username = "admin";
        String password = "password123";
        boolean isActive = true;

        System.out.println("Login utente: " + username);
        if (username != null && !username.isEmpty()) {
            if (password != null && password.length() >= 8) {
                if (isActive) {
                    System.out.println("  ✓ Login riuscito!");
                } else {
                    System.out.println("  ✗ Account disabilitato");
                }
            } else {
                System.out.println("  ✗ Password non valida");
            }
        } else {
            System.out.println("  ✗ Username non valido");
        }

        // Classificazione studente
        int eta = 20;
        double media = 27.5;
        int crediti = 120;

        System.out.println("\nValutazione studente (età: " + eta + ", media: " + media + ", crediti: " + crediti + "):");
        if (eta >= 18) {
            if (crediti >= 180) {
                if (media >= 27) {
                    System.out.println("  🎓 Laurea con lode");
                } else if (media >= 24) {
                    System.out.println("  🎓 Laurea con merito");
                } else {
                    System.out.println("  🎓 Laurea");
                }
            } else if (crediti >= 120) {
                System.out.println("  📚 Studente avanzato");
            } else {
                System.out.println("  📖 Studente");
            }
        } else {
            System.out.println("  ✗ Età non valida per laurea");
        }

        // Accesso a risorsa
        boolean isAuthenticated = true;
        String ruolo = "admin";
        boolean hasPermission = true;

        System.out.println("\nAccesso risorsa (auth: " + isAuthenticated + ", ruolo: " + ruolo + "):");
        if (isAuthenticated) {
            if (ruolo.equals("admin")) {
                System.out.println("  ✓ Accesso completo (admin)");
            } else if (ruolo.equals("user")) {
                if (hasPermission) {
                    System.out.println("  ✓ Accesso limitato (user con permessi)");
                } else {
                    System.out.println("  ⚠ Accesso parziale (user senza permessi)");
                }
            } else {
                System.out.println("  ✗ Ruolo non riconosciuto");
            }
        } else {
            System.out.println("  ✗ Non autenticato");
        }

        // Calcolo tariffa
        int distanza = 150; // km
        boolean isWeekend = false;
        boolean isPremium = true;

        double tariffa;
        System.out.println("\nCalcolo tariffa (distanza: " + distanza + "km, weekend: " + isWeekend + ", premium: " + isPremium + "):");
        if (distanza <= 50) {
            tariffa = 10.0;
        } else if (distanza <= 100) {
            if (isPremium) {
                tariffa = 18.0; // sconto premium
            } else {
                tariffa = 20.0;
            }
        } else {
            if (isWeekend) {
                if (isPremium) {
                    tariffa = 27.0; // weekend + premium
                } else {
                    tariffa = 35.0; // solo weekend
                }
            } else {
                if (isPremium) {
                    tariffa = 25.0; // solo premium
                } else {
                    tariffa = 30.0; // tariffa base
                }
            }
        }
        System.out.println("  Tariffa: €" + tariffa);
    }

    /**
     * SHORT-CIRCUIT EVALUATION
     *
     * CARATTERISTICHE:
     * - && (AND): Se prima condizione è false, non valuta la seconda
     * - || (OR): Se prima condizione è true, non valuta la seconda
     * - Utile per performance e prevenire errori
     */
    public static void shortCircuitEvaluation() {
        System.out.println("\n【 10. SHORT-CIRCUIT EVALUATION 】");
        System.out.println("─".repeat(50));

        // AND short-circuit (&&)
        System.out.println("AND short-circuit:");
        String testo = null;
        if (testo != null && testo.length() > 0) {
            System.out.println("  Testo valido: " + testo);
        } else {
            System.out.println("  ✓ Evitato NullPointerException con short-circuit");
        }

        // Senza short-circuit causerebbe NullPointerException:
        // if (testo.length() > 0 && testo != null) // ✗ ERRORE!

        // OR short-circuit (||)
        System.out.println("\nOR short-circuit:");
        boolean isAdmin = true;
        boolean hasSuperPermission = checkPermission(); // Non verrà chiamato

        if (isAdmin || hasSuperPermission) {
            System.out.println("  ✓ Accesso consentito (checkPermission non eseguito)");
        }

        // Esempio con divisione per zero
        int a = 10, b = 0;
        if (b != 0 && a / b > 5) {
            System.out.println("  Divisione valida");
        } else {
            System.out.println("  ✓ Evitata divisione per zero con short-circuit");
        }

        // Array bounds checking
        int[] array = {1, 2, 3};
        int index = 5;
        if (index >= 0 && index < array.length && array[index] > 0) {
            System.out.println("  Valore: " + array[index]);
        } else {
            System.out.println("  ✓ Evitato ArrayIndexOutOfBoundsException");
        }

        // Lista null-safe
        List<String> lista = null;
        if (lista != null && !lista.isEmpty() && lista.get(0).equals("Test")) {
            System.out.println("  Primo elemento è Test");
        } else {
            System.out.println("  ✓ Gestione sicura di lista null");
        }

        // Performance con OR
        System.out.println("\nPerformance optimization con OR:");
        boolean quickCheck = true;
        boolean expensiveCheck = expensiveOperation(); // Non verrà eseguito

        if (quickCheck || expensiveCheck) {
            System.out.println("  ✓ Condizione vera (operazione costosa evitata)");
        }

        // Confronto AND vs OR
        System.out.println("\nConfronto operatori:");
        boolean cond1 = false;
        boolean cond2 = true;

        System.out.println("  cond1 && cond2 = " + (cond1 && cond2) + " (cond2 non valutata)");
        System.out.println("  cond1 || cond2 = " + (cond1 || cond2));

        cond1 = true;
        System.out.println("  cond1 && cond2 = " + (cond1 && cond2));
        System.out.println("  cond1 || cond2 = " + (cond1 || cond2) + " (cond2 non valutata)");
    }

    private static boolean checkPermission() {
        System.out.println("    [checkPermission chiamato]");
        return false;
    }

    private static boolean expensiveOperation() {
        System.out.println("    [expensiveOperation chiamato - COSTOSO!]");
        return true;
    }

    /**
     * GUARD CONDITIONS (Early Return)
     *
     * CARATTERISTICHE:
     * - Validazioni all'inizio del metodo
     * - Return anticipato per casi invalidi
     * - Riduce annidamento e migliora leggibilità
     */
    public static void guardConditions() {
        System.out.println("\n【 11. GUARD CONDITIONS 】");
        System.out.println("─".repeat(50));

        System.out.println("Esempi di guard conditions:");

        // Test metodi con guard
        System.out.println("\n1. Calcolo sconto:");
        System.out.println("  Importo 150: €" + calcolaSconto(150.0));
        System.out.println("  Importo -50: €" + calcolaSconto(-50.0));

        System.out.println("\n2. Elaborazione utente:");
        elaboraUtente(null);
        elaboraUtente(new Utente("Mario", 25, true));
        elaboraUtente(new Utente("Luigi", 15, true));
        elaboraUtente(new Utente("Peach", 30, false));

        System.out.println("\n3. Validazione email:");
        validaEmail(null);
        validaEmail("");
        validaEmail("invalid");
        validaEmail("test@example.com");
    }

    // Esempio con guard conditions
    private static double calcolaSconto(double importo) {
        // Guard: validazione input
        if (importo <= 0) {
            System.out.println("  ✗ Importo non valido");
            return 0.0;
        }

        // Guard: nessuno sconto per importi bassi
        if (importo < 100) {
            System.out.println("  ℹ Nessuno sconto");
            return 0.0;
        }

        // Logica principale (non più annidata)
        double percentualeSconto;
        if (importo >= 500) {
            percentualeSconto = 0.20;
        } else if (importo >= 200) {
            percentualeSconto = 0.10;
        } else {
            percentualeSconto = 0.05;
        }

        return importo * percentualeSconto;
    }

    private static void elaboraUtente(Utente utente) {
        // Guard: null check
        if (utente == null) {
            System.out.println("  ✗ Utente null");
            return;
        }

        // Guard: validazione età
        if (utente.eta < 18) {
            System.out.println("  ✗ Utente minorenne: " + utente.nome);
            return;
        }

        // Guard: account attivo
        if (!utente.attivo) {
            System.out.println("  ✗ Account disabilitato: " + utente.nome);
            return;
        }

        // Logica principale
        System.out.println("  ✓ Elaborato utente: " + utente.nome + " (età: " + utente.eta + ")");
    }

    private static boolean validaEmail(String email) {
        // Guard: null
        if (email == null) {
            System.out.println("  ✗ Email null");
            return false;
        }

        // Guard: vuota
        if (email.isEmpty()) {
            System.out.println("  ✗ Email vuota");
            return false;
        }

        // Guard: formato base
        if (!email.contains("@")) {
            System.out.println("  ✗ Email senza @: " + email);
            return false;
        }

        // Validazione principale
        System.out.println("  ✓ Email valida: " + email);
        return true;
    }

    // Classe di supporto
    static class Utente {
        String nome;
        int eta;
        boolean attivo;

        Utente(String nome, int eta, boolean attivo) {
            this.nome = nome;
            this.eta = eta;
            this.attivo = attivo;
        }
    }

    /**
     * SCENARI AVANZATI
     */
    public static void scenariAvanzati() {
        System.out.println("\n【 12. SCENARI AVANZATI 】");
        System.out.println("─".repeat(50));

        // Scenario 1: Validazione form complessa
        System.out.println("1. Validazione form registrazione:");
        String email = "test@example.com";
        String password = "SecurePass123";
        String confermaPassword = "SecurePass123";
        boolean accettaTermini = true;

        boolean formValido = validaFormRegistrazione(email, password, confermaPassword, accettaTermini);
        System.out.println("  Form valido: " + formValido);

        // Scenario 2: Sistema di pricing dinamico
        System.out.println("\n2. Sistema pricing dinamico:");
        double prezzoBase = 100.0;
        String categoriaCliente = "gold";
        int quantita = 15;
        boolean isPrimoAcquisto = false;
        LocalDate dataAcquisto = LocalDate.now();

        double prezzoFinale = calcolaPrezzoFinale(prezzoBase, categoriaCliente, quantita,
                isPrimoAcquisto, dataAcquisto);
        System.out.println("  Prezzo finale: €" + String.format("%.2f", prezzoFinale));

        // Scenario 3: Gestione stati
        System.out.println("\n3. Gestione stati ordine:");
        gestioneStatoOrdine("PENDING", true, 5);
        gestioneStatoOrdine("PROCESSING", true, 0);
        gestioneStatoOrdine("SHIPPED", false, 0);
        gestioneStatoOrdine("DELIVERED", false, 0);

        // Scenario 4: Controllo accesso basato su ruoli
        System.out.println("\n4. Controllo accesso (RBAC):");
        verificaAccesso("admin", "DELETE_USER");
        verificaAccesso("user", "READ_DATA");
        verificaAccesso("user", "DELETE_USER");
        verificaAccesso("guest", "READ_DATA");
    }

    private static boolean validaFormRegistrazione(String email, String password,
                                                   String conferma, boolean termini) {
        List<String> errori = new ArrayList<>();

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errori.add("Email non valida");
        }

        if (password == null || password.length() < 8) {
            errori.add("Password troppo corta (min 8 caratteri)");
        } else if (!password.matches(".*[A-Z].*")) {
            errori.add("Password deve contenere almeno una maiuscola");
        } else if (!password.matches(".*[0-9].*")) {
            errori.add("Password deve contenere almeno un numero");
        }

        if (!password.equals(conferma)) {
            errori.add("Le password non corrispondono");
        }

        if (!termini) {
            errori.add("Devi accettare i termini e condizioni");
        }

        if (!errori.isEmpty()) {
            errori.forEach(e -> System.out.println("    ✗ " + e));
            return false;
        }

        System.out.println("    ✓ Validazione completata");
        return true;
    }

    private static double calcolaPrezzoFinale(double prezzoBase, String categoria, int quantita,
                                              boolean primoAcquisto, LocalDate data) {
        double prezzo = prezzoBase;

        // Sconto quantità
        if (quantita >= 100) {
            prezzo *= 0.80; // 20% sconto
            System.out.println("    Sconto quantità 100+: 20%");
        } else if (quantita >= 50) {
            prezzo *= 0.90; // 10% sconto
            System.out.println("    Sconto quantità 50+: 10%");
        } else if (quantita >= 10) {
            prezzo *= 0.95; // 5% sconto
            System.out.println("    Sconto quantità 10+: 5%");
        }

        // Sconto categoria cliente
        double scontoCategoria = switch (categoria.toLowerCase()) {
            case "platinum" -> 0.25;
            case "gold" -> 0.15;
            case "silver" -> 0.10;
            default -> 0.0;
        };
        if (scontoCategoria > 0) {
            prezzo *= (1 - scontoCategoria);
            System.out.println("    Sconto " + categoria + ": " + (scontoCategoria * 100) + "%");
        }

        // Bonus primo acquisto
        if (primoAcquisto) {
            prezzo *= 0.95;
            System.out.println("    Bonus primo acquisto: 5%");
        }

        // Promozione weekend
        if (data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY) {
            prezzo *= 0.90;
            System.out.println("    Promozione weekend: 10%");
        }

        return prezzo * quantita;
    }

    private static void gestioneStatoOrdine(String stato, boolean pagato, int giorni) {
        String azione = switch (stato) {
            case "PENDING" -> {
                if (!pagato) {
                    yield "⏳ In attesa di pagamento";
                } else if (giorni > 7) {
                    yield "⚠ Ordine scaduto, annullamento automatico";
                } else {
                    yield "✓ Pronto per elaborazione";
                }
            }
            case "PROCESSING" -> pagato ? "🔄 Elaborazione in corso" : "✗ Errore: non pagato";
            case "SHIPPED" -> "📦 Spedito";
            case "DELIVERED" -> "✅ Consegnato";
            case "CANCELLED" -> "❌ Annullato";
            default -> "❓ Stato sconosciuto";
        };

        System.out.println("  Stato " + stato + ": " + azione);
    }

    private static void verificaAccesso(String ruolo, String azione) {
        boolean accesso = switch (ruolo) {
            case "admin" -> true; // Admin può tutto
            case "user" -> switch (azione) {
                case "READ_DATA", "WRITE_DATA", "UPDATE_DATA" -> true;
                default -> false;
            };
            case "guest" -> azione.equals("READ_DATA");
            default -> false;
        };

        String simbolo = accesso ? "✓" : "✗";
        System.out.println("  " + simbolo + " " + ruolo + " → " + azione + ": " +
                (accesso ? "Consentito" : "Negato"));
    }

    /**
     * BEST PRACTICES
     */
    public static void bestPractices() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   BEST PRACTICES                                  ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

        System.out.println("\n【 SCELTA DELL'ISTRUZIONE 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ if-else: per scelte binarie semplici");
        System.out.println("✓ if-else if: per scelte multiple con condizioni diverse");
        System.out.println("✓ switch: per scelte multiple su stesso valore");
        System.out.println("✓ switch expression: quando serve restituire valore");
        System.out.println("✓ Ternario: per assegnazioni condizionali semplici");
        System.out.println("✓ Pattern matching: per type checking con logica");

        System.out.println("\n【 REGOLE GENERALI 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ Usa guard conditions per ridurre annidamento");
        System.out.println("✓ Preferisci condizioni positive (evita troppi !)");
        System.out.println("✓ Estrai condizioni complesse in variabili/metodi");
        System.out.println("✓ Usa short-circuit per performance e sicurezza");
        System.out.println("✓ Limita annidamento (max 3-4 livelli)");
        System.out.println("✓ Valuta casi edge (null, vuoto, zero, negativi)");
        System.out.println("✓ Usa Optional per valori opzionali");
        System.out.println("✓ Documenta logica complessa");

        System.out.println("\n【 ERRORI COMUNI DA EVITARE 】");
        System.out.println("─".repeat(50));
        System.out.println("✗ Confronti con == per String (usa equals())");
        System.out.println("✗ NullPointerException (controlla sempre null)");
        System.out.println("✗ Dimenticare break in switch classico");
        System.out.println("✗ Ternario annidato eccessivo (illeggibile)");
        System.out.println("✗ Condizioni troppo lunghe (estrai in variabili)");
        System.out.println("✗ Annidamento eccessivo (usa early return)");
        System.out.println("✗ Logica duplicata in più rami");

        System.out.println("\n【 ESEMPI REFACTORING 】");
        System.out.println("─".repeat(50));

        // Prima: condizione complessa inline
        System.out.println("\n❌ EVITA:");
        System.out.println("if (user != null && user.age >= 18 && user.isActive() && user.hasPermission(\"READ\")) {");
        System.out.println("    // ...");
        System.out.println("}");

        System.out.println("\n✅ PREFERISCI:");
        System.out.println("boolean canAccess = user != null && user.age >= 18 && ");
        System.out.println("                    user.isActive() && user.hasPermission(\"READ\");");
        System.out.println("if (canAccess) {");
        System.out.println("    // ...");
        System.out.println("}");

        // Prima: annidamento eccessivo
        System.out.println("\n❌ EVITA (annidamento):");
        System.out.println("if (a) {");
        System.out.println("    if (b) {");
        System.out.println("        if (c) {");
        System.out.println("            // logica");
        System.out.println("        }");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n✅ PREFERISCI (guard):");
        System.out.println("if (!a) return;");
        System.out.println("if (!b) return;");
        System.out.println("if (!c) return;");
        System.out.println("// logica");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("Fine degli esempi sulle condizioni Java");
        System.out.println("═".repeat(50));
    }
}
