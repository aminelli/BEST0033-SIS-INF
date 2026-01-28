package com.corso.samples.datatypes;

public class LiteralDemo {

    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ESEMPIO COMPLETO DEI LITERAL IN JAVA            ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        literalInteri();
        literalFloatingPoint();
        literalBooleani();
        literalCaratteri();
        literalStringhe();
        literalNull();
        underscoreNeiLiteral();
        suffissiLiteral();
        escapeSequences();
        textBlocks();
        literalSpeciali();
        conversioni();
    }

    /**
     * Literal Interi
     * Possono essere espressi in: decimale, esadecimale, ottale, binario
     */
    public static void literalInteri() {
        System.out.println("【 1. LITERAL INTERI 】");
        System.out.println("─".repeat(50));

        // Decimale (base 10) - modo standard
        int decimale = 42;
        System.out.println("Decimale: " + decimale + " (base 10)");

        // Esadecimale (base 16) - prefisso 0x o 0X
        int esadecimale = 0x2A; // 2*16 + 10 = 42
        int esadecimale2 = 0xFF; // 255
        int esadecimale3 = 0xCAFEBABE;
        System.out.println("\nEsadecimale:");
        System.out.println("  0x2A = " + esadecimale + " (42 in decimale)");
        System.out.println("  0xFF = " + esadecimale2 + " (255 in decimale)");
        System.out.println("  0xCAFEBABE = " + esadecimale3);

        // Ottale (base 8) - prefisso 0
        int ottale = 052; // 5*8 + 2 = 42
        int ottale2 = 0377; // 255
        System.out.println("\nOttale:");
        System.out.println("  052 = " + ottale + " (42 in decimale)");
        System.out.println("  0377 = " + ottale2 + " (255 in decimale)");
        System.out.println("  ⚠ Attenzione: 0 iniziale indica ottale!");

        // Binario (base 2) - prefisso 0b o 0B (Java 7+)
        int binario = 0b101010; // 42
        int binario2 = 0b11111111; // 255
        System.out.println("\nBinario (Java 7+):");
        System.out.println("  0b101010 = " + binario + " (42 in decimale)");
        System.out.println("  0b11111111 = " + binario2 + " (255 in decimale)");

        // Literal long - suffisso L o l
        long numeroGrande = 123456789012345L;
        System.out.println("\nLiteral long (suffisso L):");
        System.out.println("  123456789012345L = " + numeroGrande);

        // Valori min/max
        System.out.println("\nValori min/max:");
        System.out.println("  byte: " + Byte.MIN_VALUE + " ... " + Byte.MAX_VALUE);
        System.out.println("  short: " + Short.MIN_VALUE + " ... " + Short.MAX_VALUE);
        System.out.println("  int: " + Integer.MIN_VALUE + " ... " + Integer.MAX_VALUE);
        System.out.println("  long: " + Long.MIN_VALUE + " ... " + Long.MAX_VALUE);
    }

    /**
     * Literal Floating-Point (numeri con virgola)
     * float e double
     */
    public static void literalFloatingPoint() {
        System.out.println("\n【 2. LITERAL FLOATING-POINT 】");
        System.out.println("─".repeat(50));

        // Double - tipo predefinito per numeri decimali
        double d1 = 3.14;
        double d2 = 3.14159265359;
        double d3 = .5; // 0.5
        double d4 = 5.; // 5.0
        System.out.println("Double (predefinito):");
        System.out.println("  3.14 = " + d1);
        System.out.println("  3.14159265359 = " + d2);
        System.out.println("  .5 = " + d3);
        System.out.println("  5. = " + d4);

        // Notazione scientifica
        double scientifico1 = 1.23e2; // 1.23 * 10^2 = 123.0
        double scientifico2 = 1.23E2; // stesso di sopra
        double scientifico3 = 1.23e-2; // 1.23 * 10^-2 = 0.0123
        System.out.println("\nNotazione scientifica:");
        System.out.println("  1.23e2 = " + scientifico1 + " (1.23 × 10²)");
        System.out.println("  1.23E2 = " + scientifico2);
        System.out.println("  1.23e-2 = " + scientifico3 + " (1.23 × 10⁻²)");

        // Float - suffisso F o f
        float f1 = 3.14f;
        float f2 = 3.14F;
        float f3 = 1.23e2f;
        System.out.println("\nFloat (suffisso F o f):");
        System.out.println("  3.14f = " + f1);
        System.out.println("  3.14F = " + f2);
        System.out.println("  1.23e2f = " + f3);

        // Double esplicito - suffisso D o d (opzionale)
        double d5 = 3.14d;
        double d6 = 3.14D;
        System.out.println("\nDouble esplicito (suffisso D opzionale):");
        System.out.println("  3.14d = " + d5);
        System.out.println("  3.14D = " + d6);

        // Valori speciali
        System.out.println("\nValori speciali:");
        System.out.println("  Double.POSITIVE_INFINITY = " + Double.POSITIVE_INFINITY);
        System.out.println("  Double.NEGATIVE_INFINITY = " + Double.NEGATIVE_INFINITY);
        System.out.println("  Double.NaN = " + Double.NaN);
        System.out.println("  Double.MIN_VALUE = " + Double.MIN_VALUE + " (più piccolo positivo)");
        System.out.println("  Double.MAX_VALUE = " + Double.MAX_VALUE);

        // Esadecimale floating-point (Java 5+)
        double hex = 0x1.0p0; // 1.0 * 2^0 = 1.0
        double hex2 = 0x1.8p1; // 1.5 * 2^1 = 3.0
        System.out.println("\nEsadecimale floating-point (raro):");
        System.out.println("  0x1.0p0 = " + hex);
        System.out.println("  0x1.8p1 = " + hex2);
    }

    /**
     * Literal Booleani
     */
    public static void literalBooleani() {
        System.out.println("\n【 3. LITERAL BOOLEANI 】");
        System.out.println("─".repeat(50));

        boolean vero = true;
        boolean falso = false;

        System.out.println("Solo due valori possibili:");
        System.out.println("  true = " + vero);
        System.out.println("  false = " + falso);

        System.out.println("\n⚠ In Java, i booleani NON sono numeri:");
        System.out.println("  Non puoi usare 0 o 1 al posto di false/true");
        System.out.println("  Non puoi fare cast tra int e boolean");

        // Esempi d'uso
        System.out.println("\nEsempi d'uso:");
        if (vero) {
            System.out.println("  if (true) → eseguito");
        }

        boolean maggiorenne = true;
        String messaggio = maggiorenne ? "Adulto" : "Minore";
        System.out.println("  " + messaggio);
    }

    /**
     * Literal Caratteri (char)
     * Rappresentano un singolo carattere Unicode a 16 bit
     */
    public static void literalCaratteri() {
        System.out.println("\n【 4. LITERAL CARATTERI (char) 】");
        System.out.println("─".repeat(50));

        // Caratteri semplici - tra apici singoli
        char lettera = 'A';
        char cifra = '5';
        char simbolo = '@';
        char spazio = ' ';
        System.out.println("Caratteri tra apici singoli (' '):");
        System.out.println("  'A' = " + lettera);
        System.out.println("  '5' = " + cifra);
        System.out.println("  '@' = " + simbolo);
        System.out.println("  ' ' = '" + spazio + "' (spazio)");

        // Escape sequences
        char nuovaLinea = '\n';
        char tab = '\t';
        char backslash = '\\';
        char apice = '\'';
        char virgolette = '\"';
        System.out.println("\nEscape sequences:");
        System.out.println("  '\\n' = nuova linea");
        System.out.println("  '\\t' = tabulazione");
        System.out.println("  '\\\\' = " + backslash);
        System.out.println("  '\\'' = " + apice);
        System.out.println("  '\\\"' = " + virgolette);

        // Unicode
        char unicode1 = '\u0041'; // 'A'
        char unicode2 = '\u20AC'; // €
        char unicode3 = '\u2764'; // ❤
        System.out.println("\nUnicode (\\uXXXX):");
        System.out.println("  '\\u0041' = " + unicode1 + " (A)");
        System.out.println("  '\\u20AC' = " + unicode2 + " (Euro)");
        System.out.println("  '\\u2764' = " + unicode3 + " (Cuore)");

        // Valore numerico
        char c = 65; // 'A' (codice ASCII)
        System.out.println("\nValore numerico:");
        System.out.println("  char c = 65 → '" + c + "' (A)");
        System.out.println("  Codice di 'A': " + (int)'A');
        System.out.println("  Codice di 'a': " + (int)'a');
        System.out.println("  Codice di '0': " + (int)'0');

        // Operazioni aritmetiche
        char a = 'A';
        char b = (char)(a + 1); // cast necessario
        System.out.println("\nOperazioni aritmetiche:");
        System.out.println("  'A' + 1 = '" + b + "' (B)");
        System.out.println("  'Z' - 'A' = " + ('Z' - 'A') + " (differenza)");
    }

    /**
     * Literal Stringhe
     * Sequenze di caratteri tra doppi apici
     */
    public static void literalStringhe() {
        System.out.println("\n【 5. LITERAL STRINGHE 】");
        System.out.println("─".repeat(50));

        // Stringhe semplici
        String saluto = "Ciao";
        String frase = "Java è un linguaggio potente!";
        String vuota = "";
        System.out.println("Stringhe tra doppi apici (\" \"):");
        System.out.println("  \"Ciao\" = " + saluto);
        System.out.println("  \"Java è un linguaggio potente!\" = " + frase);
        System.out.println("  \"\" = '" + vuota + "' (stringa vuota)");

        // Stringhe con escape sequences
        String conNewline = "Prima riga\nSeconda riga";
        String conTab = "Colonna1\tColonna2\tColonna3";
        String conApici = "Disse: \"Ciao!\"";
        String conBackslash = "C:\\Users\\Nome\\Desktop";
        System.out.println("\nCon escape sequences:");
        System.out.println("  \"Prima riga\\nSeconda riga\":");
        System.out.println(conNewline);
        System.out.println("  \"Colonna1\\tColonna2\\tColonna3\":");
        System.out.println(conTab);
        System.out.println("  \"Disse: \\\"Ciao!\\\"\" = " + conApici);
        System.out.println("  \"C:\\\\Users\\\\Nome\\\\Desktop\" = " + conBackslash);

        // Unicode nelle stringhe
        String unicode = "Simboli: \u2665 \u2764 \u2605"; // ♥ ❤ ★
        String emoji = "Emoji: \uD83D\uDE00"; // 😀 (richiede coppia surrogata)
        System.out.println("\nUnicode:");
        System.out.println("  " + unicode);
        System.out.println("  " + emoji);

        // Concatenazione
        String parte1 = "Java";
        String parte2 = " Programming";
        String completa = parte1 + parte2;
        System.out.println("\nConcatenazione:");
        System.out.println("  \"Java\" + \" Programming\" = " + completa);

        // String literal pool
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        System.out.println("\nString literal pool:");
        System.out.println("  String s1 = \"Java\"");
        System.out.println("  String s2 = \"Java\"");
        System.out.println("  s1 == s2: " + (s1 == s2) + " (stesso oggetto nel pool)");
        System.out.println("  String s3 = new String(\"Java\")");
        System.out.println("  s1 == s3: " + (s1 == s3) + " (oggetto diverso)");
        System.out.println("  s1.equals(s3): " + s1.equals(s3) + " (stesso contenuto)");
    }

    /**
     * Literal null
     * Rappresenta l'assenza di un oggetto
     */
    public static void literalNull() {
        System.out.println("\n【 6. LITERAL NULL 】");
        System.out.println("─".repeat(50));

        String stringaNulla = null;
        Integer numeroNullo = null;
        Object oggettoNullo = null;

        System.out.println("null rappresenta l'assenza di un oggetto:");
        System.out.println("  String stringaNulla = null");
        System.out.println("  Integer numeroNullo = null");

        System.out.println("\nVerifica null:");
        System.out.println("  stringaNulla == null: " + (stringaNulla == null));
        System.out.println("  numeroNullo == null: " + (numeroNullo == null));

        System.out.println("\n⚠ Attenzione:");
        System.out.println("  null può essere assegnato solo a tipi riferimento");
        System.out.println("  Non a tipi primitivi (int, boolean, char, ecc.)");
        // int x = null; // ERRORE di compilazione!

        System.out.println("\nOperazioni con null:");
        if (stringaNulla == null) {
            System.out.println("  La stringa è null (nessun NullPointerException)");
        }

        // Chiamare metodi su null causa NullPointerException
        try {
            int lunghezza = stringaNulla.length();
        } catch (NullPointerException e) {
            System.out.println("  stringaNulla.length() → NullPointerException!");
        }
    }

    /**
     * Underscore nei literal numerici (Java 7+)
     * Migliora la leggibilità di numeri grandi
     */
    public static void underscoreNeiLiteral() {
        System.out.println("\n【 7. UNDERSCORE NEI LITERAL (Java 7+) 】");
        System.out.println("─".repeat(50));

        // Interi con underscore
        int milione = 1_000_000;
        long carte = 1234_5678_9012_3456L; // come numero carta di credito
        int binario = 0b1010_1010_1010_1010;
        int esadecimale = 0xCAFE_BABE;
        System.out.println("Migliorano la leggibilità:");
        System.out.println("  1_000_000 = " + milione);
        System.out.println("  1234_5678_9012_3456L = " + carte);
        System.out.println("  0b1010_1010_1010_1010 = " + binario);
        System.out.println("  0xCAFE_BABE = " + esadecimale);

        // Float/Double con underscore
        double pi = 3.141_592_653_589_793;
        float grande = 123_456.789_012f;
        System.out.println("\nCon floating-point:");
        System.out.println("  3.141_592_653_589_793 = " + pi);
        System.out.println("  123_456.789_012f = " + grande);

        // Regole
        System.out.println("\nRegole:");
        System.out.println("  ✓ Tra cifre: 1_000_000");
        System.out.println("  ✗ All'inizio: _1000 (errore)");
        System.out.println("  ✗ Alla fine: 1000_ (errore)");
        System.out.println("  ✗ Prima/dopo punto: 3._14 o 3_.14 (errore)");
        System.out.println("  ✗ Prima del suffisso: 1000_L (errore)");
    }

    /**
     * Suffissi nei literal
     */
    public static void suffissiLiteral() {
        System.out.println("\n【 8. SUFFISSI NEI LITERAL 】");
        System.out.println("─".repeat(50));

        System.out.println("Literal interi:");
        System.out.println("  Nessun suffisso → int");
        System.out.println("  L o l → long");
        int i = 42;
        long l1 = 42L;
        long l2 = 42l; // sconsigliato (confuso con 1)
        System.out.println("    42 = " + i + " (int)");
        System.out.println("    42L = " + l1 + " (long)");

        System.out.println("\nLiteral floating-point:");
        System.out.println("  Nessun suffisso → double");
        System.out.println("  F o f → float");
        System.out.println("  D o d → double (opzionale)");
        double d1 = 3.14;
        double d2 = 3.14D;
        float f1 = 3.14f;
        float f2 = 3.14F;
        System.out.println("    3.14 = " + d1 + " (double)");
        System.out.println("    3.14D = " + d2 + " (double)");
        System.out.println("    3.14f = " + f1 + " (float)");
        System.out.println("    3.14F = " + f2 + " (float)");

        System.out.println("\n⚠ Errori comuni:");
        // float f = 3.14; // ERRORE: double non può essere assegnato a float
        System.out.println("  float f = 3.14; // ERRORE!");
        System.out.println("  float f = 3.14f; // OK");
        // long l = 10000000000; // ERRORE: troppo grande per int
        System.out.println("  long l = 10000000000; // ERRORE!");
        System.out.println("  long l = 10000000000L; // OK");
    }

    /**
     * Escape Sequences complete
     */
    public static void escapeSequences() {
        System.out.println("\n【 9. ESCAPE SEQUENCES 】");
        System.out.println("─".repeat(50));

        System.out.println("Sequenze di escape comuni:");
        System.out.println("  \\n  - Nuova linea (newline)");
        System.out.println("  \\t  - Tabulazione (tab)");
        System.out.println("  \\r  - Ritorno a capo (carriage return)");
        System.out.println("  \\b  - Backspace");
        System.out.println("  \\f  - Form feed");
        System.out.println("  \\'  - Apice singolo");
        System.out.println("  \\\"  - Apice doppio (virgolette)");
        System.out.println("  \\\\  - Backslash");

        System.out.println("\nEsempi:");
        System.out.println("Prima\triga\tcon\ttab");
        System.out.println("Riga 1\nRiga 2\nRiga 3");
        System.out.println("Percorso Windows: C:\\Program Files\\Java");
        System.out.println("Citazione: \"Il sapere è potere\"");

        // Unicode escape
        System.out.println("\nUnicode escape (\\uXXXX):");
        System.out.println("  \\u0041 = \u0041 (A)");
        System.out.println("  \\u0061 = \u0061 (a)");
        System.out.println("  \\u0030 = \u0030 (0)");
        System.out.println("  \\u00A9 = \u00A9 (©)");
        System.out.println("  \\u2764 = \u2764 (❤)");

        // Ottale (deprecato ma possibile)
        char ottale = '\101'; // 'A' (65 in ottale)
        System.out.println("\nOttale (\\NNN, deprecato):");
        System.out.println("  '\\101' = '" + ottale + "' (A)");
    }

    /**
     * Text Blocks (Java 13+)
     * Stringhe multilinea
     */
    public static void textBlocks() {
        System.out.println("\n【 10. TEXT BLOCKS (Java 13+) 】");
        System.out.println("─".repeat(50));

        // Text block - delimitato da """
        String textBlock = """
                Questo è un text block.
                Può contenere più righe
                senza bisogno di \\n espliciti.
                Mantiene l'indentazione.
                """;

        System.out.println("Text block (delimitato da \"\"\"):");
        System.out.println(textBlock);

        // JSON esempio
        String json = """
                {
                    "nome": "Mario",
                    "cognome": "Rossi",
                    "età": 30,
                    "attivo": true
                }
                """;

        System.out.println("Perfetto per JSON:");
        System.out.println(json);

        // HTML esempio
        String html = """
                <html>
                    <head>
                        <title>Pagina di test</title>
                    </head>
                    <body>
                        <h1>Benvenuto!</h1>
                        <p>Questo è un esempio di HTML in un text block.</p>
                    </body>
                </html>
                """;

        System.out.println("Perfetto per HTML:");
        System.out.println(html);

        // SQL esempio
        String sql = """
                SELECT nome, cognome, età
                FROM utenti
                WHERE età >= 18
                ORDER BY cognome ASC;
                """;

        System.out.println("Perfetto per SQL:");
        System.out.println(sql);

        System.out.println("Vantaggi:");
        System.out.println("  • Nessun escape per \\n");
        System.out.println("  • Nessun escape per \\\" (virgolette)");
        System.out.println("  • Indentazione automatica");
        System.out.println("  • Più leggibile per testi multilinea");
    }

    /**
     * Literal speciali e casi particolari
     */
    public static void literalSpeciali() {
        System.out.println("\n【 11. LITERAL SPECIALI 】");
        System.out.println("─".repeat(50));

        // Byte e short
        System.out.println("Literal byte e short:");
        byte b = 127; // literal int convertito automaticamente
        short s = 32767;
        System.out.println("  byte b = 127 (literal int, conversione automatica)");
        System.out.println("  short s = 32767");
        // byte b2 = 128; // ERRORE: fuori range
        // byte b3 = (byte)128; // OK con cast, ma overflow

        // Char come intero
        System.out.println("\nChar come intero:");
        char c1 = 65; // 'A'
        char c2 = 0x41; // 'A' in esadecimale
        System.out.println("  char c1 = 65 → '" + c1 + "'");
        System.out.println("  char c2 = 0x41 → '" + c2 + "'");

        // Costanti nelle classi wrapper
        System.out.println("\nCostanti predefinite:");
        System.out.println("  Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("  Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("  Float.MAX_VALUE = " + Float.MAX_VALUE);
        System.out.println("  Double.POSITIVE_INFINITY = " + Double.POSITIVE_INFINITY);
        System.out.println("  Double.NaN = " + Double.NaN);
        System.out.println("  Math.PI = " + Math.PI);
        System.out.println("  Math.E = " + Math.E);

        // Literal in espressioni
        System.out.println("\nLiteral in espressioni:");
        int risultato = 10 + 20 * 3; // tutti literal
        boolean confronto = 5 > 3; // literal in confronto
        String msg = "Risultato: " + 42; // literal in concatenazione
        System.out.println("  10 + 20 * 3 = " + risultato);
        System.out.println("  5 > 3 = " + confronto);
        System.out.println("  \"Risultato: \" + 42 = " + msg);
    }

    /**
     * Conversioni tra literal e tipi
     */
    public static void conversioni() {
        System.out.println("\n【 12. CONVERSIONI 】");
        System.out.println("─".repeat(50));

        // Conversioni implicite (widening)
        System.out.println("Conversioni implicite (widening):");
        int i = 42;
        long l = i; // int → long
        float f = l; // long → float
        double d = f; // float → double
        System.out.println("  int → long → float → double");
        System.out.println("  42 → " + l + " → " + f + " → " + d);

        // Conversioni esplicite (narrowing)
        System.out.println("\nConversioni esplicite (narrowing):");
        double d2 = 3.14159;
        float f2 = (float)d2;
        long l2 = (long)f2;
        int i2 = (int)l2;
        System.out.println("  double → float → long → int");
        System.out.println("  " + d2 + " → " + f2 + " → " + l2 + " → " + i2);

        // Parsing da String
        System.out.println("\nParsing da String:");
        String str = "123";
        int numero = Integer.parseInt(str);
        double decimale = Double.parseDouble("3.14");
        boolean bool = Boolean.parseBoolean("true");
        System.out.println("  Integer.parseInt(\"123\") = " + numero);
        System.out.println("  Double.parseDouble(\"3.14\") = " + decimale);
        System.out.println("  Boolean.parseBoolean(\"true\") = " + bool);

        // Conversione a String
        System.out.println("\nConversione a String:");
        String s1 = String.valueOf(42);
        String s2 = String.valueOf(3.14);
        String s3 = String.valueOf(true);
        String s4 = Integer.toString(42);
        String s5 = "" + 42; // concatenazione
        System.out.println("  String.valueOf(42) = \"" + s1 + "\"");
        System.out.println("  String.valueOf(3.14) = \"" + s2 + "\"");
        System.out.println("  String.valueOf(true) = \"" + s3 + "\"");
        System.out.println("  Integer.toString(42) = \"" + s4 + "\"");
        System.out.println("  \"\" + 42 = \"" + s5 + "\"");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("Fine degli esempi sui literal Java");
        System.out.println("═".repeat(50));
    }
}
