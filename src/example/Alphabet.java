package example;

import java.util.ArrayList;

public class Alphabet {
    public static ArrayList<AlphabetSymbol> alphabet(long p, long q, long d) {
        boolean otladka = false;

        long c, s, m, e;

        long n = p * q;
        long deltaP = Finder.findDeltaP(p);
        long deltaQ = Finder.findDeltaQ(q);

        if (otladka) {
            System.out.println("\nw=" + " p=" + p + " q=" + q + " n=" + n + " d=" + d);
            System.out.println("deltaP=" + deltaP + " deltaQ=" + deltaQ);
        }

        if (deltaP != 0 && deltaQ != 0) {
            c = Finder.findC(p, q, deltaP, deltaQ);
            s = Finder.findS(p, q, n, c);
            m = Finder.findM(p, q, deltaP, deltaQ);
            e = Finder.findE(m, d);
            if (otladka) {
                System.out.println("c=" + c + " s=" + s + " m=" + m + " e=" + e);
            }
        } else {
            if (otladka) System.out.println("Не удалось получить deltaPQ!");
            return null;
        }

        int count = 0, number = 1;
        ArrayList<AlphabetSymbol> alphabet = new ArrayList<>();
        initAlphabet(alphabet);
        while (count != alphabet.size()) {
            if (otladka) System.out.println("\nИТЕРАЦИЯ: " + number);
            MyCipher cipher = Coding.coding(number, p, q, n, e, c, s, otladka);
            if (cipher != null) {
                if (Decoding.decoding(n, d, c, s, cipher, otladka) == number) {
                    alphabet.get(count).cipher = cipher;
                    ++count;
                }
            }
            ++number;
        }

        return alphabet;
    }

    private static void initAlphabet(ArrayList<AlphabetSymbol> alphabet) {
        alphabet.add(new AlphabetSymbol("a", null));
        alphabet.add(new AlphabetSymbol("b", null));
        alphabet.add(new AlphabetSymbol("c", null));
        alphabet.add(new AlphabetSymbol("d", null));
        alphabet.add(new AlphabetSymbol("e", null));
        alphabet.add(new AlphabetSymbol("f", null));
        alphabet.add(new AlphabetSymbol("g", null));
        alphabet.add(new AlphabetSymbol("h", null));
        alphabet.add(new AlphabetSymbol("i", null));
        alphabet.add(new AlphabetSymbol("j", null));
        alphabet.add(new AlphabetSymbol("k", null));
        alphabet.add(new AlphabetSymbol("l", null));
        alphabet.add(new AlphabetSymbol("m", null));
        alphabet.add(new AlphabetSymbol("n", null));
        alphabet.add(new AlphabetSymbol("o", null));
        alphabet.add(new AlphabetSymbol("p", null));
        alphabet.add(new AlphabetSymbol("q", null));
        alphabet.add(new AlphabetSymbol("r", null));
        alphabet.add(new AlphabetSymbol("s", null));
        alphabet.add(new AlphabetSymbol("t", null));
        alphabet.add(new AlphabetSymbol("u", null));
        alphabet.add(new AlphabetSymbol("v", null));
        alphabet.add(new AlphabetSymbol("w", null));
        alphabet.add(new AlphabetSymbol("x", null));
        alphabet.add(new AlphabetSymbol("y", null));
        alphabet.add(new AlphabetSymbol("z", null));
        alphabet.add(new AlphabetSymbol(" ", null));
        alphabet.add(new AlphabetSymbol(".", null));
        alphabet.add(new AlphabetSymbol(",", null));
    }
}
