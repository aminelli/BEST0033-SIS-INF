package com.corso.samples.datatypes;

public class OperatoriDemo {

    public static void sample() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ESEMPIO COMPLETO DEGLI OPERATORI IN JAVA        ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        operatoriAritmetici();
        operatoriAssegnazione();
        operatoriIncrementoDecremento();
        operatoriRelazionali();
        operatoriLogici();
        operatoriBitwise();
        operatoriShift();
        operatoreTernario();
        operatoreInstanceof();
        concatenazioneStringhe();
        precedenzaOperatori();
        cortocircuito();
        operazioniSpeciali();
    }

    /**
     * Operatori Aritmetici: +, -, *, /, %
     */
    public static void operatoriAritmetici() {
        System.out.println("【 1. OPERATORI ARITMETICI 】");
        System.out.println("─".repeat(50));

        int a = 20;
        int b = 7;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println();

        // Addizione
        int somma = a + b;
        System.out.println("Addizione (a + b): " + somma);

        // Sottrazione
        int differenza = a - b;
        System.out.println("Sottrazione (a - b): " + differenza);

        // Moltiplicazione
        int prodotto = a * b;
        System.out.println("Moltiplicazione (a * b): " + prodotto);

        // Divisione intera
        int quoziente = a / b;
        System.out.println("Divisione (a / b): " + quoziente + " (divisione intera)");

        // Modulo (resto della divisione)
        int resto = a % b;
        System.out.println("Modulo (a % b): " + resto + " (resto)");

        // Operazioni con decimali
        System.out.println("\nCon numeri decimali:");
        double x = 20.5;
        double y = 7.3;
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x + y = " + (x + y));
        System.out.println("x - y = " + (x - y));
        System.out.println("x * y = " + (x * y));
        System.out.println("x / y = " + (x / y));
        System.out.println("x % y = " + (x % y));

        // Casi particolari
        System.out.println("\nCasi particolari:");
        System.out.println("15 / 2 = " + (15 / 2) + " (int)");
        System.out.println("15.0 / 2 = " + (15.0 / 2) + " (double)");
        System.out.println("-10 % 3 = " + (-10 % 3));
        System.out.println("10 % -3 = " + (10 % -3));
    }

    /**
     * Operatori di Assegnazione: =, +=, -=, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=
     */
    public static void operatoriAssegnazione() {
        System.out.println("\n【 2. OPERATORI DI ASSEGNAZIONE 】");
        System.out.println("─".repeat(50));

        int num = 10;
        System.out.println("Valore iniziale: num = " + num);

        // Assegnazione semplice
        num = 20;
        System.out.println("num = 20 → " + num);

        // Assegnazione con addizione
        num += 5; // equivalente a: num = num + 5
        System.out.println("num += 5 → " + num);

        // Assegnazione con sottrazione
        num -= 3; // equivalente a: num = num - 3
        System.out.println("num -= 3 → " + num);

        // Assegnazione con moltiplicazione
        num *= 2; // equivalente a: num = num * 2
        System.out.println("num *= 2 → " + num);

        // Assegnazione con divisione
        num /= 4; // equivalente a: num = num / 4
        System.out.println("num /= 4 → " + num);

        // Assegnazione con modulo
        num %= 7; // equivalente a: num = num % 7
        System.out.println("num %= 7 → " + num);

        // Operatori di assegnazione bitwise
        System.out.println("\nOperatori di assegnazione bitwise:");
        int bits = 12; // 1100 in binario
        System.out.println("bits = " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");

        bits &= 10; // AND bitwise (1100 & 1010 = 1000)
        System.out.println("bits &= 10 → " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");

        bits |= 5; // OR bitwise (1000 | 0101 = 1101)
        System.out.println("bits |= 5 → " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");

        bits ^= 3; // XOR bitwise (1101 ^ 0011 = 1110)
        System.out.println("bits ^= 3 → " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");

        bits <<= 1; // Shift sinistro
        System.out.println("bits <<= 1 → " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");

        bits >>= 2; // Shift destro
        System.out.println("bits >>= 2 → " + bits + " (binario: " + Integer.toBinaryString(bits) + ")");
    }

    /**
     * Operatori Unari di Incremento e Decremento: ++, --
     */
    public static void operatoriIncrementoDecremento() {
        System.out.println("\n【 3. OPERATORI DI INCREMENTO E DECREMENTO 】");
        System.out.println("─".repeat(50));

        int x = 5;
        System.out.println("Valore iniziale: x = " + x);

        // Post-incremento (usa il valore poi incrementa)
        System.out.println("\nPost-incremento (x++):");
        System.out.println("  Valore di x++: " + (x++));
        System.out.println("  Valore dopo: " + x);

        x = 5; // reset

        // Pre-incremento (incrementa poi usa il valore)
        System.out.println("\nPre-incremento (++x):");
        System.out.println("  Valore di ++x: " + (++x));
        System.out.println("  Valore dopo: " + x);

        x = 5; // reset

        // Post-decremento
        System.out.println("\nPost-decremento (x--):");
        System.out.println("  Valore di x--: " + (x--));
        System.out.println("  Valore dopo: " + x);

        x = 5; // reset

        // Pre-decremento
        System.out.println("\nPre-decremento (--x):");
        System.out.println("  Valore di --x: " + (--x));
        System.out.println("  Valore dopo: " + x);

        // Esempio pratico
        System.out.println("\nEsempio pratico:");
        int a = 10, b = 10;
        int risultato1 = a++ + 5; // usa 10, poi incrementa
        int risultato2 = ++b + 5; // incrementa a 11, poi usa 11
        System.out.println("a++ + 5 = " + risultato1 + " (a ora vale " + a + ")");
        System.out.println("++b + 5 = " + risultato2 + " (b ora vale " + b + ")");
    }

    /**
     * Operatori Relazionali (di confronto): ==, !=, >, <, >=, <=
     */
    public static void operatoriRelazionali() {
        System.out.println("\n【 4. OPERATORI RELAZIONALI 】");
        System.out.println("─".repeat(50));

        int a = 10;
        int b = 20;
        int c = 10;

        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
        System.out.println();

        // Uguale a
        System.out.println("a == b: " + (a == b) + " (uguale a)");
        System.out.println("a == c: " + (a == c));

        // Diverso da
        System.out.println("a != b: " + (a != b) + " (diverso da)");
        System.out.println("a != c: " + (a != c));

        // Maggiore di
        System.out.println("a > b: " + (a > b) + " (maggiore di)");
        System.out.println("b > a: " + (b > a));

        // Minore di
        System.out.println("a < b: " + (a < b) + " (minore di)");
        System.out.println("b < a: " + (b < a));

        // Maggiore o uguale a
        System.out.println("a >= c: " + (a >= c) + " (maggiore o uguale a)");
        System.out.println("a >= b: " + (a >= b));

        // Minore o uguale a
        System.out.println("a <= c: " + (a <= c) + " (minore o uguale a)");
        System.out.println("b <= a: " + (b <= a));

        // Confronto con stringhe (ATTENZIONE!)
        System.out.println("\nConfronto stringhe:");
        String str1 = "Java";
        String str2 = "Java";
        String str3 = new String("Java");

        System.out.println("str1 == str2: " + (str1 == str2) + " (confronta riferimenti)");
        System.out.println("str1 == str3: " + (str1 == str3) + " (diversi oggetti!)");
        System.out.println("str1.equals(str3): " + (str1.equals(str3)) + " (confronta contenuto) ✓");
    }

    /**
     * Operatori Logici: &&, ||, !, &, |
     */
    public static void operatoriLogici() {
        System.out.println("\n【 5. OPERATORI LOGICI 】");
        System.out.println("─".repeat(50));

        boolean a = true;
        boolean b = false;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println();

        // AND logico (&&) - entrambi devono essere true
        System.out.println("AND logico (&&):");
        System.out.println("  a && a = " + (a && a));
        System.out.println("  a && b = " + (a && b));
        System.out.println("  b && b = " + (b && b));

        // OR logico (||) - almeno uno deve essere true
        System.out.println("\nOR logico (||):");
        System.out.println("  a || a = " + (a || a));
        System.out.println("  a || b = " + (a || b));
        System.out.println("  b || b = " + (b || b));

        // NOT logico (!) - inverte il valore
        System.out.println("\nNOT logico (!):");
        System.out.println("  !a = " + (!a));
        System.out.println("  !b = " + (!b));
        System.out.println("  !!a = " + (!!a) + " (doppia negazione)");

        // Operatori logici bitwise (& e |) - valutano sempre entrambi gli operandi
        System.out.println("\nOperatori bitwise per booleani (& e |):");
        System.out.println("  a & b = " + (a & b) + " (AND bitwise)");
        System.out.println("  a | b = " + (a | b) + " (OR bitwise)");

        // Espressioni complesse
        System.out.println("\nEspressioni complesse:");
        System.out.println("  a && b || !b = " + (a && b || !b));
        System.out.println("  (a || b) && !b = " + ((a || b) && !b));

        // Tabella di verità
        System.out.println("\nTabella di verità:");
        System.out.println("  true  && true  = " + (true && true));
        System.out.println("  true  && false = " + (true && false));
        System.out.println("  false && true  = " + (false && true));
        System.out.println("  false && false = " + (false && false));
        System.out.println();
        System.out.println("  true  || true  = " + (true || true));
        System.out.println("  true  || false = " + (true || false));
        System.out.println("  false || true  = " + (false || true));
        System.out.println("  false || false = " + (false || false));
    }

    /**
     * Operatori Bitwise: &, |, ^, ~
     */
    public static void operatoriBitwise() {
        System.out.println("\n【 6. OPERATORI BITWISE 】");
        System.out.println("─".repeat(50));

        int a = 12; // 1100 in binario
        int b = 10; // 1010 in binario

        System.out.println("a = " + a + " (binario: " + String.format("%4s", Integer.toBinaryString(a)).replace(' ', '0') + ")");
        System.out.println("b = " + b + " (binario: " + String.format("%4s", Integer.toBinaryString(b)).replace(' ', '0') + ")");
        System.out.println();

        // AND bitwise (&) - 1 solo se entrambi i bit sono 1
        int andResult = a & b; // 1100 & 1010 = 1000 (8)
        System.out.println("AND bitwise (a & b):");
        System.out.println("  Risultato: " + andResult + " (binario: " + String.format("%4s", Integer.toBinaryString(andResult)).replace(' ', '0') + ")");

        // OR bitwise (|) - 1 se almeno un bit è 1
        int orResult = a | b; // 1100 | 1010 = 1110 (14)
        System.out.println("\nOR bitwise (a | b):");
        System.out.println("  Risultato: " + orResult + " (binario: " + String.format("%4s", Integer.toBinaryString(orResult)).replace(' ', '0') + ")");

        // XOR bitwise (^) - 1 se i bit sono diversi
        int xorResult = a ^ b; // 1100 ^ 1010 = 0110 (6)
        System.out.println("\nXOR bitwise (a ^ b):");
        System.out.println("  Risultato: " + xorResult + " (binario: " + String.format("%4s", Integer.toBinaryString(xorResult)).replace(' ', '0') + ")");

        // NOT bitwise (~) - inverte tutti i bit
        int notResult = ~a; // ~1100 = ...11110011 (complemento a 2)
        System.out.println("\nNOT bitwise (~a):");
        System.out.println("  Risultato: " + notResult + " (binario: " + Integer.toBinaryString(notResult) + ")");

        // Esempi pratici
        System.out.println("\nEsempi pratici:");

        // Verifica se un numero è pari (ultimo bit = 0)
        int numero = 42;
        boolean isPari = (numero & 1) == 0;
        System.out.println("  " + numero + " è pari? " + isPari + " (usando & 1)");

        // Scambiare due numeri senza variabile temporanea
        int x = 5, y = 10;
        System.out.println("  Prima: x = " + x + ", y = " + y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("  Dopo swap XOR: x = " + x + ", y = " + y);
    }

    /**
     * Operatori di Shift: <<, >>, >>>
     */
    public static void operatoriShift() {
        System.out.println("\n【 7. OPERATORI DI SHIFT 】");
        System.out.println("─".repeat(50));

        int num = 8; // 1000 in binario
        System.out.println("Valore iniziale: " + num + " (binario: " + Integer.toBinaryString(num) + ")");
        System.out.println();

        // Left shift (<<) - sposta i bit a sinistra, riempie con 0 a destra
        // Equivale a moltiplicare per 2^n
        int leftShift1 = num << 1; // 1000 << 1 = 10000 (16)
        int leftShift2 = num << 2; // 1000 << 2 = 100000 (32)
        System.out.println("Left shift (<<):");
        System.out.println("  " + num + " << 1 = " + leftShift1 + " (binario: " + Integer.toBinaryString(leftShift1) + ") = " + num + " * 2");
        System.out.println("  " + num + " << 2 = " + leftShift2 + " (binario: " + Integer.toBinaryString(leftShift2) + ") = " + num + " * 4");

        // Right shift (>>) - sposta i bit a destra, preserva il segno
        // Equivale a dividere per 2^n (arrotonda verso -∞)
        int rightShift1 = num >> 1; // 1000 >> 1 = 100 (4)
        int rightShift2 = num >> 2; // 1000 >> 2 = 10 (2)
        System.out.println("\nRight shift (>>) - con segno:");
        System.out.println("  " + num + " >> 1 = " + rightShift1 + " (binario: " + Integer.toBinaryString(rightShift1) + ") = " + num + " / 2");
        System.out.println("  " + num + " >> 2 = " + rightShift2 + " (binario: " + Integer.toBinaryString(rightShift2) + ") = " + num + " / 4");

        // Test con numero negativo
        int negativo = -8;
        System.out.println("\nCon numero negativo (" + negativo + "):");
        System.out.println("  Binario: " + Integer.toBinaryString(negativo));
        System.out.println("  " + negativo + " >> 1 = " + (negativo >> 1) + " (preserva il segno)");

        // Unsigned right shift (>>>) - sposta i bit a destra, riempie con 0 a sinistra
        int unsignedShift = negativo >>> 1;
        System.out.println("\nUnsigned right shift (>>>) - senza segno:");
        System.out.println("  " + negativo + " >>> 1 = " + unsignedShift + " (tratta come unsigned)");
        System.out.println("  Binario: " + Integer.toBinaryString(unsignedShift));

        // Esempi pratici
        System.out.println("\nEsempi pratici:");
        System.out.println("  Moltiplicare per 8: " + num + " << 3 = " + (num << 3));
        System.out.println("  Dividere per 4: " + num + " >> 2 = " + (num >> 2));
    }

    /**
     * Operatore Ternario: ? :
     */
    public static void operatoreTernario() {
        System.out.println("\n【 8. OPERATORE TERNARIO (? :) 】");
        System.out.println("─".repeat(50));

        System.out.println("Sintassi: condizione ? valoreSeTrue : valoreSeFalse\n");

        int a = 10;
        int b = 20;

        // Esempio base
        int max = (a > b) ? a : b;
        System.out.println("Massimo tra " + a + " e " + b + ": " + max);

        int min = (a < b) ? a : b;
        System.out.println("Minimo tra " + a + " e " + b + ": " + min);

        // Con stringhe
        int voto = 75;
        String risultato = (voto >= 60) ? "Promosso" : "Bocciato";
        System.out.println("\nVoto " + voto + ": " + risultato);

        // Ternario annidato (sconsigliato per la leggibilità)
        int numero = 0;
        String segno = (numero > 0) ? "positivo" : (numero < 0) ? "negativo" : "zero";
        System.out.println("\nIl numero " + numero + " è " + segno);

        // Valutazione pari/dispari
        int n = 15;
        String parita = (n % 2 == 0) ? "pari" : "dispari";
        System.out.println("Il numero " + n + " è " + parita);

        // Assegnazione condizionale
        boolean isAdulto = true;
        int etaMinima = isAdulto ? 18 : 13;
        System.out.println("\nEtà minima: " + etaMinima);

        // Comparato con if-else
        System.out.println("\nConfronto ternario vs if-else:");

        // Con ternario
        int x = 5;
        String messaggioT = (x > 0) ? "Positivo" : "Non positivo";
        System.out.println("  Ternario: " + messaggioT);

        // Con if-else (equivalente)
        String messaggioIf;
        if (x > 0) {
            messaggioIf = "Positivo";
        } else {
            messaggioIf = "Non positivo";
        }
        System.out.println("  If-else: " + messaggioIf);
    }

    /**
     * Operatore instanceof
     */
    public static void operatoreInstanceof() {
        System.out.println("\n【 9. OPERATORE INSTANCEOF 】");
        System.out.println("─".repeat(50));

        System.out.println("Verifica se un oggetto è istanza di una classe\n");

        Object obj1 = "Stringa";
        Object obj2 = 42;
        Object obj3 = 3.14;
        Object obj4 = new java.util.ArrayList<>();
        String str = "Java";

        // Test instanceof
        System.out.println("obj1 instanceof String: " + (obj1 instanceof String));
        System.out.println("obj1 instanceof Object: " + (obj1 instanceof Object));
        System.out.println("obj2 instanceof Integer: " + (obj2 instanceof Integer));
        System.out.println("obj2 instanceof String: " + (obj2 instanceof String));
        System.out.println("obj3 instanceof Double: " + (obj3 instanceof Double));
        System.out.println("obj4 instanceof java.util.List: " + (obj4 instanceof java.util.List));

        // Uso pratico con cast
        System.out.println("\nUso con cast sicuro:");
        if (obj1 instanceof String) {
            String s = (String) obj1;
            System.out.println("  Lunghezza stringa: " + s.length());
        }

        // Pattern matching (Java 16+)
        System.out.println("\nPattern matching con instanceof (Java 16+):");
        if (obj1 instanceof String s) {
            System.out.println("  Stringa: '" + s + "' (lunghezza: " + s.length() + ")");
        }

        // Verifica null
        Object nullObj = null;
        System.out.println("\nnull instanceof String: " + (nullObj instanceof String) + " (null non è istanza di nulla)");
    }

    /**
     * Concatenazione di Stringhe: +
     */
    public static void concatenazioneStringhe() {
        System.out.println("\n【 10. CONCATENAZIONE STRINGHE (+) 】");
        System.out.println("─".repeat(50));

        String nome = "Mario";
        String cognome = "Rossi";
        int eta = 30;

        // Concatenazione base
        String nomeCompleto = nome + " " + cognome;
        System.out.println("Nome completo: " + nomeCompleto);

        // Concatenazione con numeri
        String messaggio = nome + " ha " + eta + " anni";
        System.out.println(messaggio);

        // Attenzione all'ordine delle operazioni!
        System.out.println("\nOrdine delle operazioni:");
        System.out.println("\"Risultato: \" + 5 + 3 = " + ("Risultato: " + 5 + 3)); // "Risultato: 53"
        System.out.println("\"Risultato: \" + (5 + 3) = " + ("Risultato: " + (5 + 3))); // "Risultato: 8"
        System.out.println("5 + 3 + \" è il risultato\" = " + (5 + 3 + " è il risultato")); // "8 è il risultato"

        // Concatenazione multipla
        String indirizzo = "Via " + "Roma" + ", " + 10 + " - " + "Milano";
        System.out.println("\n" + indirizzo);

        // Operatore += con stringhe
        String testo = "Java";
        testo += " è";
        testo += " fantastico";
        System.out.println("\n" + testo);

        // Confronto prestazioni
        System.out.println("\nNota sulle prestazioni:");
        System.out.println("  Per concatenazioni multiple, usa StringBuilder");
        StringBuilder sb = new StringBuilder();
        sb.append("Questa").append(" è").append(" più").append(" efficiente");
        System.out.println("  " + sb.toString());
    }

    /**
     * Precedenza degli Operatori
     */
    public static void precedenzaOperatori() {
        System.out.println("\n【 11. PRECEDENZA DEGLI OPERATORI 】");
        System.out.println("─".repeat(50));

        System.out.println("Ordine di precedenza (dal più alto al più basso):\n");
        System.out.println("1. Postfix: expr++, expr--");
        System.out.println("2. Unary: ++expr, --expr, +expr, -expr, !, ~");
        System.out.println("3. Moltiplicazione: *, /, %");
        System.out.println("4. Addizione: +, -");
        System.out.println("5. Shift: <<, >>, >>>");
        System.out.println("6. Relazionali: <, >, <=, >=, instanceof");
        System.out.println("7. Uguaglianza: ==, !=");
        System.out.println("8. AND bitwise: &");
        System.out.println("9. XOR bitwise: ^");
        System.out.println("10. OR bitwise: |");
        System.out.println("11. AND logico: &&");
        System.out.println("12. OR logico: ||");
        System.out.println("13. Ternario: ? :");
        System.out.println("14. Assegnazione: =, +=, -=, ecc.");

        System.out.println("\nEsempi:");

        int risultato1 = 5 + 3 * 2; // moltiplicazione prima
        System.out.println("5 + 3 * 2 = " + risultato1 + " (non 16)");

        int risultato2 = (5 + 3) * 2; // parentesi cambiano la precedenza
        System.out.println("(5 + 3) * 2 = " + risultato2);

        boolean bool1 = 5 > 3 && 10 < 20; // confronto prima di &&
        System.out.println("5 > 3 && 10 < 20 = " + bool1);

        int risultato3 = 10 + 5 * 2 - 3;
        System.out.println("10 + 5 * 2 - 3 = " + risultato3 + " (prima *, poi + e -)");

        // Espressione complessa
        int a = 2, b = 3, c = 5;
        int risultato4 = a + b * c - (a * b) + c / b;
        System.out.println("\na=" + a + ", b=" + b + ", c=" + c);
        System.out.println("a + b * c - (a * b) + c / b = " + risultato4);
        System.out.println("  Step by step: 2 + (3*5) - (2*3) + (5/3)");
        System.out.println("              = 2 + 15 - 6 + 1 = " + risultato4);
    }

    /**
     * Cortocircuito degli operatori logici
     */
    public static void cortocircuito() {
        System.out.println("\n【 12. CORTOCIRCUITO (SHORT-CIRCUIT) 】");
        System.out.println("─".repeat(50));

        System.out.println("Gli operatori && e || usano la valutazione cortocircuito:\n");

        // AND logico (&&)
        System.out.println("AND logico (&&):");
        System.out.println("  Se il primo operando è false, non valuta il secondo");

        int x = 5;
        boolean result1 = (x > 10) && (++x > 5); // ++x non viene eseguito
        System.out.println("  (x > 10) && (++x > 5) = " + result1);
        System.out.println("  Valore di x: " + x + " (non incrementato perché cortocircuito)");

        x = 5;
        boolean result2 = (x < 10) && (++x > 5); // ++x viene eseguito
        System.out.println("  (x < 10) && (++x > 5) = " + result2);
        System.out.println("  Valore di x: " + x + " (incrementato)");

        // OR logico (||)
        System.out.println("\nOR logico (||):");
        System.out.println("  Se il primo operando è true, non valuta il secondo");

        x = 5;
        boolean result3 = (x < 10) || (++x > 5); // ++x non viene eseguito
        System.out.println("  (x < 10) || (++x > 5) = " + result3);
        System.out.println("  Valore di x: " + x + " (non incrementato perché cortocircuito)");

        x = 5;
        boolean result4 = (x > 10) || (++x > 5); // ++x viene eseguito
        System.out.println("  (x > 10) || (++x > 5) = " + result4);
        System.out.println("  Valore di x: " + x + " (incrementato)");

        // Confronto con & e | (non cortocircuito)
        System.out.println("\nConfronto con & e | (NON cortocircuito):");
        x = 5;
        boolean result5 = (x > 10) & (++x > 5); // ++x viene SEMPRE eseguito
        System.out.println("  (x > 10) & (++x > 5) = " + result5);
        System.out.println("  Valore di x: " + x + " (sempre incrementato con &)");

        // Uso pratico: evitare NullPointerException
        System.out.println("\nUso pratico - evitare NullPointerException:");
        String str = null;
        if (str != null && str.length() > 0) { // cortocircuito previene l'errore
            System.out.println("  Lunghezza: " + str.length());
        } else {
            System.out.println("  Stringa null o vuota (nessun errore grazie al cortocircuito)");
        }
    }

    /**
     * Operazioni Speciali e Casi Particolari
     */
    public static void operazioniSpeciali() {
        System.out.println("\n【 13. OPERAZIONI SPECIALI 】");
        System.out.println("─".repeat(50));

        // Divisione per zero
        System.out.println("Divisione per zero:");
        try {
            int risultato = 10 / 0; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("  10 / 0 → ArithmeticException: " + e.getMessage());
        }

        // Con double: non lancia eccezione, restituisce Infinity
        double risultatoDouble = 10.0 / 0.0;
        System.out.println("  10.0 / 0.0 = " + risultatoDouble + " (Infinity)");
        System.out.println("  -10.0 / 0.0 = " + (-10.0 / 0.0) + " (-Infinity)");
        System.out.println("  0.0 / 0.0 = " + (0.0 / 0.0) + " (NaN - Not a Number)");

        // Overflow
        System.out.println("\nOverflow:");
        int maxInt = Integer.MAX_VALUE;
        System.out.println("  Integer.MAX_VALUE = " + maxInt);
        System.out.println("  Integer.MAX_VALUE + 1 = " + (maxInt + 1) + " (overflow!)");
        System.out.println("  Integer.MIN_VALUE - 1 = " + (Integer.MIN_VALUE - 1) + " (overflow!)");

        // Underflow con floating point
        System.out.println("\nUnderflow:");
        double piccolo = 1e-323;
        System.out.println("  1e-323 / 10 = " + (piccolo / 10) + " (underflow a 0.0)");

        // Operazioni con NaN
        System.out.println("\nOperazioni con NaN:");
        double nan = Double.NaN;
        System.out.println("  NaN + 5 = " + (nan + 5));
        System.out.println("  NaN == NaN = " + (nan == nan) + " (sempre false!)");
        System.out.println("  Double.isNaN(NaN) = " + Double.isNaN(nan) + " (modo corretto)");

        // Casting e troncamento
        System.out.println("\nCasting e troncamento:");
        double d = 9.99;
        int i = (int) d; // troncamento
        System.out.println("  (int) 9.99 = " + i + " (troncato, non arrotondato)");
        System.out.println("  Math.round(9.99) = " + Math.round(d) + " (arrotondato)");

        // Promozione automatica
        System.out.println("\nPromozione automatica:");
        byte b = 10;
        short s = 20;
        int risultato = b + s; // byte e short promossi a int
        System.out.println("  byte(10) + short(20) = int(" + risultato + ")");

        System.out.println("\n" + "═".repeat(50));
        System.out.println("Fine degli esempi sugli operatori Java");
        System.out.println("═".repeat(50));
    }
}

