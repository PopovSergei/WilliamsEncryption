package example;

public class Utils {
    public static long obr(long d, long m) {
        d = d % m;
        if (d < 0)
            d = m + d;
        if (nod(d, m) != 1)
            return 0;
        int obrD = 1;
        while ((obrD * d) % m != 1)
            ++obrD;
        return obrD;
    }

    public static long nod(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static long leganr(long s, long pq) {
        s = s % pq;
        if (s < 0)
            s = pq + s;

        long answer = -1;
        long x;
        for (long i = 1; i < pq; ++i) {
            x = (i * i) % pq;
            //System.out.println(i + "^2=" + (i*i) + " mod" +pq + "=" + x + " s=" + s);
            //System.out.println((i*i) + "=" + x + " s=" + s);
            if (x == s) {
                answer = 1;
                break;
            }
        }
        //System.out.println("answer=" + answer);
        return answer;
    }
}
