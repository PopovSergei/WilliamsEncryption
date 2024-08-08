package example;

public class Decoding {
    public static long decoding(long n, long d, long c, long s, MyCipher cipher, boolean otladka) {
        long E = cipher.E;
        long b1 = cipher.b1;
        long b2 = cipher.b2;

        long alpha2eA, alpha2eB;
        long delitel2e = Utils.obr((E * E - c) % n, n);
        if (delitel2e == 0) {
            if (otladka) System.out.println("Обратного нет!");
            return 0;
        }
        alpha2eA = (((E * E + c) % n) * delitel2e) % n;
        alpha2eB = (((2 * E) % n) * delitel2e) % n;

        long Xd, Yd;
        Xd = Finder.findXi(d, alpha2eA, n);
        Yd = Finder.findYi(d, alpha2eA, alpha2eB, n);

        long newAlphaA = Xd, newAlphaB = Yd;

        if (otladka) System.out.println("A=" + newAlphaA + " B=" + newAlphaB);
        if (newAlphaA % 2 == 0 && b2 == 1) {
            newAlphaA = n - newAlphaA;
            newAlphaB = n - newAlphaB;
        }
        if (newAlphaA % 2 == 1 && b2 == 0) {
            newAlphaA = n - newAlphaA;
            newAlphaB = n - newAlphaB;
        }

        long otv;
        long znam, obrZnam, chisl;
        if (b1 == 1) {
            long znam1 = (s * s - c) % n;
            long obrZnam1 = Utils.obr(znam1, n);
            if (obrZnam1 == 0) {
                if (otladka) System.out.println("Обратного нет!");
                return 0;
            }
            long slag1 = newAlphaA * (s * s + c);
            long slag1c = newAlphaB * (s * s + c);
            long slag2c = (-1) * newAlphaA * 2 * s;
            long slag2 = (-1) * newAlphaB * 2 * s * c;
            long chisl1 = slag1 + slag2;
            long chisl2 = slag1c + slag2c;
            newAlphaA = (chisl1 * obrZnam1) % n;
            newAlphaB = (chisl2 * obrZnam1) % n;
        }

        long chast1 = (newAlphaA + 1) * (newAlphaA - 1) % n;
        long chast2 = (newAlphaB * newAlphaB * c) % n;
        long chast3 = ((newAlphaA + 1) * newAlphaB) % n;
        long chast4 = ((newAlphaA - 1) * newAlphaB) % n;
        chisl = (((chast1 - chast2) % n) + (chast4 - chast3) * c) % n;
        if (chisl < 0)
            chisl = n + chisl;

        znam = (((newAlphaA - 1) * (newAlphaA - 1) % n) - ((newAlphaB * newAlphaB * c) % n)) % n;
        obrZnam = Utils.obr(znam, n);
        if (obrZnam == 0) {
            if (otladka) System.out.println("Обратного нет!");
            return 0;
        }
        otv = (chisl * obrZnam) % n;

        if (otladka) {
            System.out.println("newAlphaA=" + newAlphaA + " newAlphaB=" + newAlphaB);
            System.out.println("Xd=" + Xd + " Yd=" + Yd);
            System.out.println("obrZnam=" + obrZnam + " chisl=" + chisl + " otv=" + otv);
        }

        return otv;
    }
}
