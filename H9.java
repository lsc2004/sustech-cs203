import java.io.*;
import java.util.StringTokenizer;

public class H9 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        long b2 = a1.nextLong();
        long[] x = new long[b1];
        for (int i = 0; i < b1; i++) {
            x[i] = a1.nextLong();
        }
        long counter = 0;
        for (int i = 0; i < b1 - 2; i++) {
            long test = b2 - x[i];
            if (test < 2 * x[i] || test > 2 * x[b1 - 1]) {
                continue;
            }
            for (int g = i + 1; g < x.length - 1; g++) {
                int n = g + 1;
                long op = getR(x, test, g, n) - getL(x, test, g, n) + 1;
                if (getR(x, test, g, n) != -1 && getL(x, test, g, n) != -1) {
                    counter += op;
                }
            }
        }
        out.println(counter);
        out.close();
    }

    private static int getL(long[] x, long test, int g, int n) {
        int nb1 = -1;
        int j = x.length - 1;
        while (n <= j) {
            int m = (n + j) >>> 1;
            if (test - x[g] < x[m]) {
                j = m - 1;
            } else if (test - x[g] > x[m]) {
                n = m + 1;
            } else if (test - x[g] == x[m]) {
                nb1 = m;
                j = m - 1;
            }
        }
        return nb1;
    }

    private static int getR(long[] x, long test, int g, int n) {
        int nb1 = -1;
        int j = x.length - 1;
        while (n <= j) {
            int m = (n + j) >>> 1;
            if (test - x[g] < x[m]) {
                j = m - 1;
            } else if (test - x[g] > x[m]) {
                n = m + 1;
            } else if (test - x[g] == x[m]) {
                nb1 = m;
                n = m + 1;
            }
        }
        return nb1;
    }
}
