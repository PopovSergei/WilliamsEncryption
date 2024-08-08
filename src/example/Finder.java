package example;

public class Finder {
    public static long findDeltaP(long p) {
        long deltaP = (-1) * (p % 4);
        if (deltaP != -1) {
            deltaP = 4 + deltaP;
            if (deltaP != 1) {
                return 0;
            }
        }
        return deltaP;
    }

    public static long findDeltaQ(long q) {
        long deltaQ = (-1) * (q % 4);
        if (deltaQ != -1) {
            deltaQ = 4 + deltaQ;
            if (deltaQ != 1) {
                return 0;
            }
        }
        return deltaQ;
    }

    public static long findC(long p, long q, long deltaP, long deltaQ) {
        long c = 1;
        while (true) {
            if (Utils.leganr(c, p) == deltaP && Utils.leganr(c, q) == deltaQ)
                break;
            else
                ++c;
        }
        return c;
    }

    public static long findM(long p, long q, long deltaP, long deltaQ) {
        return (p - deltaP) * (q - deltaQ) / 4;
    }

    public static long findE(long m, long d) {
        return (((m + 1) / 2) * Utils.obr(d, m)) % m;
    }

    public static long findS(long p, long q, long n, long c) {
        long s = 2;
        while (true) {
            if (Utils.nod(s, n) != 1)
                ++s;
            else
                if (Utils.leganr(s * s - c, p) * Utils.leganr(s * s - c, q) == -1)
                    break;
                else
                    ++s;
        }
        return s;
    }

    public static long findXi(long e, long alphaA, long n) {
        if (e % 2 == 0 && e > 2) {
            long xi = findXi(e / 2, alphaA, n) % n;
            return (2 * xi * xi - 1) % n;
        }
        if (e % 2 == 1 && e > 2) {
            long xi = findXi((e - 1) / 2, alphaA, n) % n;
            long xip1 = findXi(((e - 1) / 2) + 1, alphaA, n) % n;
            return (2 * xi * xip1 - alphaA) % n;
        }

        if (e == 2)
            return (2 * alphaA * alphaA - 1) % n;
        if (e == 1)
            return alphaA;

        System.out.println("Ошибка Xi");
        return (e);
    }

    public static long findYi(long e, long alphaA, long alphaB, long n) {
        if (e % 2 == 0 && e > 2) {
            long xi = findXi(e / 2, alphaA, n) % n;
            long yi = findYi(e / 2, alphaA, alphaB, n) % n;
            return (2 * xi * yi) % n;
        }
        if (e % 2 == 1 && e > 2) {
            long xi = findXi((e - 1) / 2, alphaA, n) % n;
            long yip1 = findYi(((e - 1) / 2) + 1, alphaA, alphaB, n) % n;
            return (2 * xi * yip1 - alphaB) % n;
        }

        if (e == 2)
            return (2 * alphaA * alphaB) % n;
        if (e == 1)
            return alphaA;

        System.out.println("Ошибка Yi");
        return (e);
    }
}
