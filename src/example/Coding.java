package example;

public class Coding {
    public static MyCipher coding(long w, long p, long q, long n, long e, long c, long s, boolean otladka) {
        long b1, b2;
        long alphaA, alphaB;
        long E, Xe, Ye, obrYe;

        if (Utils.leganr(w * w - c, p) * Utils.leganr(w * w - c, q) == 1) {
            b1 = 0;
            long delitel = Utils.obr((w * w - c) % n, n);
            if (delitel == 0) {
                if (otladka) System.out.println("Обратного нет!");
                return null;
            }
            alphaA = (((w * w + c) % n) * delitel) % n;
            alphaB = (((w + w) % n) * delitel) % n;
            if (otladka) System.out.println("delitel=" + delitel);
        } else {
            b1 = 1;
            long delitel = Utils.obr(((w * w - c) * (s * s - c)) % n, n);
            if (delitel == 0) {
                if (otladka) System.out.println("Обратного нет!");
                return null;
            }
            alphaA = ((((w * w + c) * (s * s + c) + 4 * c * s * w) % n) * delitel) % n;
            alphaB = (((2 * s * (w * w + c) + 2 * w * (s * s + c)) % n) * delitel) % n;
            if (otladka) System.out.println("delitel=" + delitel);
        }

        b2 = alphaA % 2;
        Xe = Finder.findXi(e, alphaA, n);
        Ye = Finder.findYi(e, alphaA, alphaB, n);
        obrYe = Utils.obr(Ye, n);
        if (obrYe == 0) {
            if (otladka) System.out.println("Обратного нет!");
            return null;
        }
        E = (Xe * obrYe) % n;


        if (otladka) {
            System.out.println("alphaA=" + alphaA + " alphaB=" + alphaB);
            System.out.println("Xe=" + Xe + " Ye=" + Ye);
            System.out.println("E=" + E + " b1=" + b1 + " b2=" + b2);
            System.out.println("----------------------------");
        }

        return new MyCipher(E, b1, b2);
    }
}
