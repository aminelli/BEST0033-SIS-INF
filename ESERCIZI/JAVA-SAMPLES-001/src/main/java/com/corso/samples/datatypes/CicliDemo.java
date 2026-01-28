package com.corso.samples.datatypes;

import java.util.*;

public class CicliDemo {

    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ESEMPIO COMPLETO DEI CICLI IN JAVA              ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        cicloWhile();
        cicloDoWhile();
        cicloFor();
        cicloForEach();
        breakStatement();
        continueStatement();
        cicliAnnidati();
        labeledBreakContinue();
        scenariPratici();
        confrontoCicli();
    }

    /**
     * CICLO WHILE
     *
     * Sintassi: while (condizione) { ... }
     *
     * CARATTERISTICHE:
     * - Controlla la condizione PRIMA di eseguire il blocco
     * - Può NON eseguire mai se condizione inizialmente falsa
     * - Utile quando non si sa quante iterazioni servono
     */
    public static void cicloWhile() {
        System.out.println("【 1. CICLO WHILE 】");
        System.out.println("─".repeat(50));

        // Esempio base
        System.out.println("Conteggio da 1 a 5:");
        int i = 1;
        while (i <= 5) {
            System.out.println("  Iterazione " + i);
            i++;
        }

        // Esempio con condizione complessa
        System.out.println("\nSomma fino a superare 100:");
        int somma = 0;
        int numero = 1;
        while (somma < 100) {
            somma += numero;
            System.out.println("  Numero " + numero + ", somma parziale: " + somma);
            numero++;
        }

        // Esempio: lettura input (simulata)
        System.out.println("\nElaborazione elementi fino a trovare zero:");
        int[] valori = {5, 10, 15, 0, 20, 25}; // 0 è il terminatore
        int indice = 0;
        while (indice < valori.length && valori[indice] != 0) {
            System.out.println("  Valore: " + valori[indice]);
            indice++;
        }

        // Ciclo infinito (da evitare senza break!)
        System.out.println("\nCiclo infinito controllato (con break):");
        int count = 0;
        while (true) { // Condizione sempre vera
            System.out.println("  Iterazione " + count);
            count++;
            if (count >= 3) {
                break; // Esce dal ciclo
            }
        }

        // Ciclo che potrebbe non eseguire mai
        System.out.println("\nCiclo con condizione inizialmente falsa:");
        int x = 10;
        while (x < 5) {
            System.out.println("  Questo non verrà mai eseguito");
            x++;
        }
        System.out.println("  Ciclo mai eseguito (x = " + x + " >= 5)");
    }

    /**
     * CICLO DO-WHILE
     *
     * Sintassi: do { ... } while (condizione);
     *
     * CARATTERISTICHE:
     * - Esegue ALMENO UNA VOLTA prima di controllare la condizione
     * - Controllo condizione DOPO l'esecuzione
     * - Utile per menu, validazione input, conferme
     */
    public static void cicloDoWhile() {
        System.out.println("\n【 2. CICLO DO-WHILE 】");
        System.out.println("─".repeat(50));

        // Esempio base
        System.out.println("Conteggio da 1 a 5:");
        int i = 1;
        do {
            System.out.println("  Iterazione " + i);
            i++;
        } while (i <= 5);

        // Differenza con while: esegue ALMENO una volta
        System.out.println("\nDo-while esegue almeno una volta:");
        int x = 10;
        do {
            System.out.println("  Eseguito! (x = " + x + ")");
            x++;
        } while (x < 5); // Condizione falsa, ma il blocco è già stato eseguito

        // Esempio pratico: menu
        System.out.println("\nSimulazione menu:");
        String[] scelte = {"1", "2", "3", "0"}; // Simuliamo scelte utente
        int sceltaIndex = 0;
        String scelta;
        do {
            System.out.println("\n  --- MENU ---");
            System.out.println("  1. Opzione 1");
            System.out.println("  2. Opzione 2");
            System.out.println("  3. Opzione 3");
            System.out.println("  0. Esci");

            scelta = scelte[sceltaIndex++];
            System.out.println("  Scelta: " + scelta);

            switch (scelta) {
                case "1" -> System.out.println("  → Opzione 1 selezionata");
                case "2" -> System.out.println("  → Opzione 2 selezionata");
                case "3" -> System.out.println("  → Opzione 3 selezionata");
                case "0" -> System.out.println("  → Uscita...");
                default -> System.out.println("  → Scelta non valida!");
            }
        } while (!scelta.equals("0"));

        // Esempio: validazione input
        System.out.println("\nValidazione input (simulata):");
        int[] inputs = {-5, 0, 150, 42}; // Simuliamo input utente
        int inputIndex = 0;
        int valore;
        do {
            valore = inputs[inputIndex++];
            System.out.println("  Input ricevuto: " + valore);
            if (valore < 1 || valore > 100) {
                System.out.println("  ✗ Valore non valido! Deve essere tra 1 e 100");
            }
        } while (valore < 1 || valore > 100);
        System.out.println("  ✓ Valore valido: " + valore);
    }

    /**
     * CICLO FOR
     *
     * Sintassi: for (inizializzazione; condizione; aggiornamento) { ... }
     *
     * CARATTERISTICHE:
     * - Struttura compatta per cicli con contatore
     * - Tutte le parti (init, condizione, update) in una riga
     * - Ideale quando si conosce il numero di iterazioni
     */
    public static void cicloFor() {
        System.out.println("\n【 3. CICLO FOR 】");
        System.out.println("─".repeat(50));

        // Esempio base: conteggio crescente
        System.out.println("Conteggio da 1 a 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("  Iterazione " + i);
        }

        // Conteggio decrescente
        System.out.println("\nConteggio decrescente da 5 a 1:");
        for (int i = 5; i >= 1; i--) {
            System.out.println("  " + i);
        }

        // Incremento personalizzato
        System.out.println("\nNumeri pari da 0 a 10:");
        for (int i = 0; i <= 10; i += 2) {
            System.out.print("  " + i);
        }
        System.out.println();

        // Multipla inizializzazione e aggiornamento
        System.out.println("\nDue variabili simultanee:");
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.println("  i = " + i + ", j = " + j);
        }

        // Iterazione su array
        System.out.println("\nIterazione su array (indice):");
        String[] frutti = {"Mela", "Pera", "Banana", "Arancia"};
        for (int i = 0; i < frutti.length; i++) {
            System.out.println("  [" + i + "] " + frutti[i]);
        }

        // For con parti opzionali
        System.out.println("\nFor con inizializzazione esterna:");
        int k = 0;
        for (; k < 3; k++) {
            System.out.println("  k = " + k);
        }

        // For infinito (tutte le parti opzionali)
        System.out.println("\nFor infinito (con break):");
        int counter = 0;
        for (;;) { // Equivalente a while(true)
            System.out.println("  Counter: " + counter);
            counter++;
            if (counter >= 3) break;
        }

        // Iterazione inversa su array
        System.out.println("\nIterazione inversa:");
        for (int i = frutti.length - 1; i >= 0; i--) {
            System.out.println("  " + frutti[i]);
        }
    }

    /**
     * CICLO FOR-EACH (Enhanced For Loop)
     *
     * Sintassi: for (Tipo elemento : collezione) { ... }
     *
     * CARATTERISTICHE:
     * - Introdotto in Java 5
     * - Più semplice e leggibile
     * - NON fornisce l'indice
     * - NON può modificare la collezione durante iterazione
     * - Funziona con array e Iterable
     */
    public static void cicloForEach() {
        System.out.println("\n【 4. CICLO FOR-EACH 】");
        System.out.println("─".repeat(50));

        // Esempio base con array
        System.out.println("Iterazione su array:");
        String[] colori = {"Rosso", "Verde", "Blu", "Giallo"};
        for (String colore : colori) {
            System.out.println("  Colore: " + colore);
        }

        // Con array di primitivi
        System.out.println("\nIterazione su array di numeri:");
        int[] numeri = {10, 20, 30, 40, 50};
        int somma = 0;
        for (int numero : numeri) {
            somma += numero;
            System.out.println("  Numero: " + numero + ", somma: " + somma);
        }

        // Con List
        System.out.println("\nIterazione su List:");
        List<String> nomi = Arrays.asList("Mario", "Luigi", "Peach", "Toad");
        for (String nome : nomi) {
            System.out.println("  Nome: " + nome);
        }

        // Con Set
        System.out.println("\nIterazione su Set:");
        Set<Integer> numeriSet = new HashSet<>(Arrays.asList(5, 10, 15, 20));
        for (Integer num : numeriSet) {
            System.out.println("  Numero: " + num);
        }

        // Con Map (iterazione su entry)
        System.out.println("\nIterazione su Map:");
        Map<String, Integer> eta = new HashMap<>();
        eta.put("Mario", 30);
        eta.put("Luigi", 28);
        eta.put("Peach", 25);

        for (Map.Entry<String, Integer> entry : eta.entrySet()) {
            System.out.println("  " + entry.getKey() + " ha " + entry.getValue() + " anni");
        }

        // Iterazione su chiavi
        System.out.println("\nIterazione su chiavi Map:");
        for (String nome : eta.keySet()) {
            System.out.println("  Nome: " + nome);
        }

        // Iterazione su valori
        System.out.println("\nIterazione su valori Map:");
        for (Integer anni : eta.values()) {
            System.out.println("  Età: " + anni);
        }

        // Limitazioni del for-each
        System.out.println("\nLimitazioni del for-each:");
        System.out.println("  ✗ Non fornisce l'indice");
        System.out.println("  ✗ Non può modificare gli elementi (per tipi primitivi)");
        System.out.println("  ✗ Non può rimuovere elementi durante iterazione");
        System.out.println("  ✓ Ma è più semplice e leggibile!");
    }

    /**
     * BREAK STATEMENT
     *
     * FUNZIONE:
     * - Esce IMMEDIATAMENTE dal ciclo più interno
     * - Interrompe l'esecuzione e passa al codice dopo il ciclo
     * - Utile per uscite anticipate, ricerche, condizioni speciali
     */
    public static void breakStatement() {
        System.out.println("\n【 5. BREAK STATEMENT 】");
        System.out.println("─".repeat(50));

        // Esempio: ricerca in array
        System.out.println("Ricerca elemento in array:");
        int[] numeri = {5, 10, 15, 20, 25, 30};
        int target = 20;
        int index = -1;

        for (int i = 0; i < numeri.length; i++) {
            System.out.println("  Controllo indice " + i + ": " + numeri[i]);
            if (numeri[i] == target) {
                index = i;
                System.out.println("  ✓ Elemento trovato!");
                break; // Esce dal ciclo, non serve continuare
            }
        }
        System.out.println("Elemento " + target + " trovato all'indice: " + index);

        // Esempio: validazione con limite
        System.out.println("\nValidazione con limite tentativi:");
        String[] passwords = {"wrong1", "wrong2", "wrong3", "correct", "wrong4"};
        int maxTentativi = 3;
        boolean autenticato = false;

        for (int i = 0; i < maxTentativi; i++) {
            String pwd = passwords[i];
            System.out.println("  Tentativo " + (i + 1) + ": " + pwd);

            if (pwd.equals("correct")) {
                autenticato = true;
                System.out.println("  ✓ Autenticazione riuscita!");
                break; // Esce immediatamente
            }
        }

        if (!autenticato) {
            System.out.println("  ✗ Troppi tentativi falliti");
        }

        // Esempio: uscita da ciclo infinito
        System.out.println("\nContatore con limite:");
        int count = 0;
        while (true) {
            count++;
            System.out.println("  Count: " + count);

            if (count >= 5) {
                System.out.println("  → Limite raggiunto, uscita!");
                break;
            }
        }

        // Break in switch (diverso uso)
        System.out.println("\nBreak in switch:");
        int giorno = 3;
        switch (giorno) {
            case 1:
                System.out.println("  Lunedì");
                break; // Esce dallo switch
            case 2:
                System.out.println("  Martedì");
                break;
            case 3:
                System.out.println("  Mercoledì");
                break; // Senza break, eseguirebbe anche i case successivi!
            default:
                System.out.println("  Altro giorno");
        }
    }

    /**
     * CONTINUE STATEMENT
     *
     * FUNZIONE:
     * - Salta il resto dell'iterazione CORRENTE
     * - Passa alla PROSSIMA iterazione
     * - NON esce dal ciclo (a differenza di break)
     * - Utile per saltare casi speciali, filtrare elementi
     */
    public static void continueStatement() {
        System.out.println("\n【 6. CONTINUE STATEMENT 】");
        System.out.println("─".repeat(50));

        // Esempio: salta numeri pari
        System.out.println("Stampa solo numeri dispari (1-10):");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue; // Salta i numeri pari
            }
            System.out.println("  " + i);
        }

        // Esempio: salta valori negativi
        System.out.println("\nElabora solo valori positivi:");
        int[] valori = {5, -3, 10, -1, 7, 0, -5, 15};
        int sommaPositivi = 0;

        for (int valore : valori) {
            if (valore <= 0) {
                System.out.println("  Saltato: " + valore);
                continue; // Salta valori negativi e zero
            }
            sommaPositivi += valore;
            System.out.println("  Aggiunto: " + valore + " (somma: " + sommaPositivi + ")");
        }

        // Esempio: filtra stringhe vuote
        System.out.println("\nElabora solo stringhe non vuote:");
        String[] parole = {"Java", "", "Programming", "   ", "Language", null, "!"};

        for (String parola : parole) {
            if (parola == null || parola.trim().isEmpty()) {
                System.out.println("  Saltata stringa vuota/null");
                continue;
            }
            System.out.println("  Elaborata: " + parola);
        }

        // Esempio con while
        System.out.println("\nContinue con while:");
        int i = 0;
        while (i < 8) {
            i++;
            if (i % 3 == 0) {
                System.out.println("  Saltato multiplo di 3: " + i);
                continue;
            }
            System.out.println("  Valore: " + i);
        }

        // Differenza break vs continue
        System.out.println("\nConfronto break vs continue:");
        System.out.println("Con continue (salta 5, continua dopo):");
        for (int j = 1; j <= 8; j++) {
            if (j == 5) continue;
            System.out.print("  " + j);
        }
        System.out.println();

        System.out.println("Con break (si ferma a 5):");
        for (int j = 1; j <= 8; j++) {
            if (j == 5) break;
            System.out.print("  " + j);
        }
        System.out.println();
    }

    /**
     * CICLI ANNIDATI
     *
     * CARATTERISTICHE:
     * - Un ciclo dentro un altro
     * - Utile per matrici, tabelle, combinazioni
     * - Attenzione alla complessità (n² o peggio)
     */
    public static void cicliAnnidati() {
        System.out.println("\n【 7. CICLI ANNIDATI 】");
        System.out.println("─".repeat(50));

        // Esempio: tabella di moltiplicazione
        System.out.println("Tabella di moltiplicazione (5x5):");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }

        // Esempio: matrice
        System.out.println("\nIterazione su matrice:");
        int[][] matrice = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print("  [" + i + "][" + j + "]=" + matrice[i][j]);
            }
            System.out.println();
        }

        // Con for-each
        System.out.println("\nMatrice con for-each:");
        for (int[] riga : matrice) {
            for (int elemento : riga) {
                System.out.printf("%4d", elemento);
            }
            System.out.println();
        }

        // Esempio: pattern di stelle
        System.out.println("\nPattern triangolare:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Esempio: coppie di elementi
        System.out.println("\nTutte le coppie:");
        String[] lettere = {"A", "B", "C"};
        for (String l1 : lettere) {
            for (String l2 : lettere) {
                if (!l1.equals(l2)) { // Evita coppie con stesso elemento
                    System.out.println("  " + l1 + "-" + l2);
                }
            }
        }

        // Break in ciclo annidato (esce solo dal più interno)
        System.out.println("\nBreak in ciclo annidato:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Ciclo esterno: " + i);
            for (int j = 1; j <= 5; j++) {
                if (j == 3) {
                    System.out.println("  → Break al j=3");
                    break; // Esce solo dal ciclo interno
                }
                System.out.println("  Ciclo interno: " + j);
            }
        }
    }

    /**
     * LABELED BREAK e CONTINUE
     *
     * CARATTERISTICHE:
     * - Etichette (label) per identificare cicli specifici
     * - break label: esce dal ciclo etichettato
     * - continue label: salta alla prossima iterazione del ciclo etichettato
     * - Utile per cicli annidati complessi
     */
    public static void labeledBreakContinue() {
        System.out.println("\n【 8. LABELED BREAK E CONTINUE 】");
        System.out.println("─".repeat(50));

        // Labeled break: esce da ciclo esterno
        System.out.println("Labeled break (esce da ciclo esterno):");
        outer: // Etichetta per ciclo esterno
        for (int i = 1; i <= 3; i++) {
            System.out.println("Esterno: " + i);
            for (int j = 1; j <= 5; j++) {
                System.out.println("  Interno: " + j);
                if (i == 2 && j == 3) {
                    System.out.println("  → Break outer!");
                    break outer; // Esce dal ciclo esterno
                }
            }
        }
        System.out.println("Dopo i cicli\n");

        // Labeled continue: salta iterazione ciclo esterno
        System.out.println("Labeled continue (salta iterazione esterna):");
        outer2:
        for (int i = 1; i <= 3; i++) {
            System.out.println("Esterno: " + i);
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("  → Continue outer2!");
                    continue outer2; // Salta alla prossima iterazione di outer2
                }
                System.out.println("  Interno: " + j);
            }
            System.out.println("  Fine ciclo interno per i=" + i);
        }

        // Esempio pratico: ricerca in matrice
        System.out.println("\nRicerca in matrice con labeled break:");
        int[][] matrice = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int cercato = 7;
        boolean trovato = false;
        int rigaTrovata = -1, colonnaTrovata = -1;

        ricerca:
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.println("  Controllo [" + i + "][" + j + "] = " + matrice[i][j]);
                if (matrice[i][j] == cercato) {
                    rigaTrovata = i;
                    colonnaTrovata = j;
                    trovato = true;
                    break ricerca; // Esce da entrambi i cicli
                }
            }
        }

        if (trovato) {
            System.out.println("✓ Trovato " + cercato + " in [" + rigaTrovata + "][" + colonnaTrovata + "]");
        }

        // Esempio: validazione dati annidati
        System.out.println("\nValidazione con labeled continue:");
        String[][] dati = {
                {"Mario", "30", "Roma"},
                {"Luigi", "invalid", "Milano"}, // Età non valida
                {"Peach", "25", "Napoli"}
        };

        elaborazione:
        for (int i = 0; i < dati.length; i++) {
            System.out.println("\nRecord " + (i + 1) + ":");
            for (int j = 0; j < dati[i].length; j++) {
                if (dati[i][1].equals("invalid")) {
                    System.out.println("  ✗ Record non valido, salto");
                    continue elaborazione; // Salta al prossimo record
                }
            }
            // Elabora record valido
            System.out.println("  ✓ " + dati[i][0] + ", " + dati[i][1] + " anni, " + dati[i][2]);
        }
    }

    /**
     * SCENARI PRATICI DI UTILIZZO
     */
    public static void scenariPratici() {
        System.out.println("\n【 9. SCENARI PRATICI 】");
        System.out.println("─".repeat(50));

        // Scenario 1: Calcolo fattoriale
        System.out.println("1. Calcolo fattoriale di 5:");
        int n = 5;
        int fattoriale = 1;
        for (int i = 1; i <= n; i++) {
            fattoriale *= i;
        }
        System.out.println("   " + n + "! = " + fattoriale);

        // Scenario 2: Fibonacci
        System.out.println("\n2. Sequenza Fibonacci (primi 10 numeri):");
        int a = 0, b = 1;
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println();

        // Scenario 3: Numeri primi
        System.out.println("\n3. Numeri primi fino a 30:");
        System.out.print("   ");
        for (int num = 2; num <= 30; num++) {
            boolean isPrimo = true;
            for (int div = 2; div <= Math.sqrt(num); div++) {
                if (num % div == 0) {
                    isPrimo = false;
                    break;
                }
            }
            if (isPrimo) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // Scenario 4: Inversione stringa
        System.out.println("\n4. Inversione stringa:");
        String testo = "Java";
        String invertita = "";
        for (int i = testo.length() - 1; i >= 0; i--) {
            invertita += testo.charAt(i);
        }
        System.out.println("   \"" + testo + "\" → \"" + invertita + "\"");

        // Scenario 5: Somma elementi array
        System.out.println("\n5. Somma e media array:");
        int[] numeri = {10, 20, 30, 40, 50};
        int somma = 0;
        for (int numero : numeri) {
            somma += numero;
        }
        double media = (double) somma / numeri.length;
        System.out.println("   Somma: " + somma + ", Media: " + media);

        // Scenario 6: Trova max e min
        System.out.println("\n6. Trova massimo e minimo:");
        int[] valori = {45, 12, 78, 23, 89, 5, 67};
        int max = valori[0];
        int min = valori[0];
        for (int valore : valori) {
            if (valore > max) max = valore;
            if (valore < min) min = valore;
        }
        System.out.println("   Max: " + max + ", Min: " + min);

        // Scenario 7: Conta occorrenze
        System.out.println("\n7. Conta occorrenze carattere:");
        String frase = "programming";
        char carattere = 'm';
        int conta = 0;
        for (char c : frase.toCharArray()) {
            if (c == carattere) conta++;
        }
        System.out.println("   '" + carattere + "' appare " + conta + " volte in \"" + frase + "\"");

        // Scenario 8: Filtra e trasforma
        System.out.println("\n8. Filtra numeri pari e raddoppia:");
        int[] originali = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> risultato = new ArrayList<>();
        for (int num : originali) {
            if (num % 2 == 0) {
                risultato.add(num * 2);
            }
        }
        System.out.println("   Risultato: " + risultato);
    }

    /**
     * CONFRONTO TRA TIPI DI CICLI
     */
    public static void confrontoCicli() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║   CONFRONTO E GUIDA ALLA SCELTA                   ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────┬──────────────────────────────────────┐");
        System.out.println("│ TIPO CICLO  │ QUANDO USARLO                        │");
        System.out.println("├─────────────┼──────────────────────────────────────┤");
        System.out.println("│ for         │ • Numero iterazioni NOTO             │");
        System.out.println("│             │ • Iterazione con contatore           │");
        System.out.println("│             │ • Accesso per indice                 │");
        System.out.println("├─────────────┼──────────────────────────────────────┤");
        System.out.println("│ for-each    │ • Iterazione su collezioni           │");
        System.out.println("│             │ • Non serve l'indice                 │");
        System.out.println("│             │ • Codice più leggibile               │");
        System.out.println("├─────────────┼──────────────────────────────────────┤");
        System.out.println("│ while       │ • Numero iterazioni SCONOSCIUTO      │");
        System.out.println("│             │ • Condizione controllata prima       │");
        System.out.println("│             │ • Può non eseguire mai               │");
        System.out.println("├─────────────┼──────────────────────────────────────┤");
        System.out.println("│ do-while    │ • Esecuzione ALMENO UNA VOLTA        │");
        System.out.println("│             │ • Menu, validazione input            │");
        System.out.println("│             │ • Condizione controllata dopo        │");
        System.out.println("└─────────────┴──────────────────────────────────────┘");

        System.out.println("\n【 BREAK vs CONTINUE 】");
        System.out.println("─".repeat(50));
        System.out.println("BREAK:");
        System.out.println("  • Esce COMPLETAMENTE dal ciclo");
        System.out.println("  • Usa per: ricerche, limiti, uscite anticipate");
        System.out.println("  • break label: esce da ciclo specifico");
        System.out.println("\nCONTINUE:");
        System.out.println("  • Salta RESTO iterazione corrente");
        System.out.println("  • Passa alla PROSSIMA iterazione");
        System.out.println("  • Usa per: filtrare, saltare casi speciali");
        System.out.println("  • continue label: salta a ciclo specifico");

        System.out.println("\n【 BEST PRACTICES 】");
        System.out.println("─".repeat(50));
        System.out.println("✓ Usa for-each quando possibile (più leggibile)");
        System.out.println("✓ Preferisci for per iterazioni con contatore");
        System.out.println("✓ Usa while quando iterazioni dipendono da condizione");
        System.out.println("✓ Usa do-while per esecuzione garantita");
        System.out.println("✓ Evita modifiche alla variabile di controllo nel corpo");
        System.out.println("✓ Evita cicli infiniti accidentali");
        System.out.println("✓ Usa break per uscite pulite");
        System.out.println("✓ Usa continue per semplificare logica");
        System.out.println("✓ Documenta cicli complessi o annidati");
        System.out.println("✓ Attenzione alla complessità di cicli annidati");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("Fine degli esempi sui cicli Java");
        System.out.println("═".repeat(50));
    }
}
