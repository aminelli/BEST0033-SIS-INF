package com.corso.samples.datatypes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo {


    public static void sample() {
        System.out.println("===========================================");
        System.out.println("  Dimostrazione BigDecimal in Java");
        System.out.println("===========================================\n");

        // 1. CREAZIONE DI BIGDECIMAL
        System.out.println("1. CREAZIONE DI BIGDECIMAL");
        System.out.println("-------------------------------------------");

        // Modo corretto: usando String
        BigDecimal bd1 = new BigDecimal("123.45");
        System.out.println("Da String: " + bd1);

        // Da int
        BigDecimal bd2 = new BigDecimal(100);
        System.out.println("Da int: " + bd2);

        // Da long
        BigDecimal bd3 = BigDecimal.valueOf(1000L);
        System.out.println("Da long: " + bd3);

        // ATTENZIONE: evitare double nel costruttore!
        BigDecimal bd4Wrong = new BigDecimal(0.1); // Impreciso!
        BigDecimal bd4Right = new BigDecimal("0.1"); // Corretto!
        System.out.println("Da double (SBAGLIATO): " + bd4Wrong);
        System.out.println("Da String (CORRETTO): " + bd4Right + "\n");

        // 2. OPERAZIONI ARITMETICHE
        System.out.println("2. OPERAZIONI ARITMETICHE");
        System.out.println("-------------------------------------------");

        BigDecimal a = new BigDecimal("10.50");
        BigDecimal b = new BigDecimal("3.25");

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // Addizione
        BigDecimal somma = a.add(b);
        System.out.println("Addizione: " + a + " + " + b + " = " + somma);

        // Sottrazione
        BigDecimal differenza = a.subtract(b);
        System.out.println("Sottrazione: " + a + " - " + b + " = " + differenza);

        // Moltiplicazione
        BigDecimal prodotto = a.multiply(b);
        System.out.println("Moltiplicazione: " + a + " * " + b + " = " + prodotto);

        // Divisione con scala e arrotondamento
        BigDecimal quoziente = a.divide(b, 2, RoundingMode.HALF_UP);
        System.out.println("Divisione: " + a + " / " + b + " = " + quoziente + "\n");

        // 3. CONFRONTI
        System.out.println("3. CONFRONTI");
        System.out.println("-------------------------------------------");

        BigDecimal num1 = new BigDecimal("100.00");
        BigDecimal num2 = new BigDecimal("100.0");
        BigDecimal num3 = new BigDecimal("99.99");

        // equals() confronta anche la scala!
        System.out.println("100.00 equals 100.0: " + num1.equals(num2));

        // compareTo() confronta solo il valore
        System.out.println("100.00 compareTo 100.0: " + (num1.compareTo(num2) == 0));
        System.out.println("100.00 > 99.99: " + (num1.compareTo(num3) > 0));
        System.out.println("99.99 < 100.00: " + (num3.compareTo(num1) < 0) + "\n");

        // 4. ARROTONDAMENTI
        System.out.println("4. ARROTONDAMENTI");
        System.out.println("-------------------------------------------");

        BigDecimal valore = new BigDecimal("123.456789");
        System.out.println("Valore originale: " + valore);

        System.out.println("HALF_UP (2 decimali): " + valore.setScale(2, RoundingMode.HALF_UP));
        System.out.println("HALF_DOWN (2 decimali): " + valore.setScale(2, RoundingMode.HALF_DOWN));
        System.out.println("CEILING (2 decimali): " + valore.setScale(2, RoundingMode.CEILING));
        System.out.println("FLOOR (2 decimali): " + valore.setScale(2, RoundingMode.FLOOR));
        System.out.println("UP (2 decimali): " + valore.setScale(2, RoundingMode.UP));
        System.out.println("DOWN (2 decimali): " + valore.setScale(2, RoundingMode.DOWN) + "\n");

        // 5. PRECISIONE E SCALA
        System.out.println("5. PRECISIONE E SCALA");
        System.out.println("-------------------------------------------");

        BigDecimal precisione = new BigDecimal("123.4567");
        System.out.println("Numero: " + precisione);
        System.out.println("Precisione (cifre significative): " + precisione.precision());
        System.out.println("Scala (decimali): " + precisione.scale() + "\n");

        // 6. CALCOLI FINANZIARI - ESEMPIO PRATICO
        System.out.println("6. ESEMPIO PRATICO: CALCOLO FINANZIARIO");
        System.out.println("-------------------------------------------");

        // Calcolo prezzo con IVA
        BigDecimal prezzoBase = new BigDecimal("100.00");
        BigDecimal iva = new BigDecimal("0.22"); // 22%
        BigDecimal importoIva = prezzoBase.multiply(iva).setScale(2, RoundingMode.HALF_UP);
        BigDecimal prezzoTotale = prezzoBase.add(importoIva);

        System.out.println("Prezzo base: €" + prezzoBase);
        System.out.println("IVA (22%): €" + importoIva);
        System.out.println("Prezzo totale: €" + prezzoTotale + "\n");

        // Sconto
        BigDecimal sconto = new BigDecimal("0.15"); // 15%
        BigDecimal importoSconto = prezzoTotale.multiply(sconto).setScale(2, RoundingMode.HALF_UP);
        BigDecimal prezzoScontato = prezzoTotale.subtract(importoSconto);

        System.out.println("Sconto (15%): €" + importoSconto);
        System.out.println("Prezzo finale: €" + prezzoScontato + "\n");

        // 7. ALTRI METODI UTILI
        System.out.println("7. ALTRI METODI UTILI");
        System.out.println("-------------------------------------------");

        BigDecimal numero = new BigDecimal("-123.456");
        System.out.println("Numero: " + numero);
        System.out.println("Valore assoluto: " + numero.abs());
        System.out.println("Negazione: " + numero.negate());
        System.out.println("Potenza (^2): " + numero.pow(2));
        System.out.println("Min con 0: " + numero.min(BigDecimal.ZERO));
        System.out.println("Max con 0: " + numero.max(BigDecimal.ZERO));

        // Conversioni
        System.out.println("\nConversioni:");
        System.out.println("A int: " + numero.intValue());
        System.out.println("A long: " + numero.longValue());
        System.out.println("A double: " + numero.doubleValue());
        System.out.println("A float: " + numero.floatValue() + "\n");

        // 8. COSTANTI PREDEFINITE
        System.out.println("8. COSTANTI PREDEFINITE");
        System.out.println("-------------------------------------------");
        System.out.println("ZERO: " + BigDecimal.ZERO);
        System.out.println("ONE: " + BigDecimal.ONE);
        System.out.println("TEN: " + BigDecimal.TEN + "\n");

        // 9. PERCHÉ USARE BIGDECIMAL
        System.out.println("9. PERCHÉ USARE BIGDECIMAL");
        System.out.println("-------------------------------------------");

        // Problema con double
        double d1 = 0.1;
        double d2 = 0.2;
        double resultDouble = d1 + d2;
        System.out.println("Con double: 0.1 + 0.2 = " + resultDouble);

        // Soluzione con BigDecimal
        BigDecimal bd5 = new BigDecimal("0.1");
        BigDecimal bd6 = new BigDecimal("0.2");
        BigDecimal resultBD = bd5.add(bd6);
        System.out.println("Con BigDecimal: 0.1 + 0.2 = " + resultBD);

        System.out.println("\nBigDecimal è essenziale per:");
        System.out.println("- Calcoli finanziari precisi");
        System.out.println("- Applicazioni bancarie");
        System.out.println("- Calcoli scientifici che richiedono precisione arbitraria");
        System.out.println("- Quando la precisione decimale è critica");
    }


}
