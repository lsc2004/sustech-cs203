import java.io.*;
import java.util.StringTokenizer;

public class H14 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            int b2 = a1.nextInt();
            long[] x = new long[b2];
            for (int j = 0; j < b2; j++) {
                x[j] = a1.nextLong();
            }
            long[] op = new long[b2];
            out.println(sortCount(x, op, 0, b2 - 1));
        }
        out.close();
    }

    private static long sortCount(long[] a, long[] op, int min, int max) {
        long nb = 0;
        if (max > min) {
            int mid = (max + min) >>> 1;
            nb += sortCount(a, op, min, mid);
            nb += sortCount(a, op, mid + 1, max);
            int i = 0;
            int j = min, k = mid + 1;
            while (j <= mid && k <= max) {
                if (a[j] <= a[k]) {
                    op[i] = a[j];
                    i++;
                    j++;
                } else {
                    op[i] = a[k];
                    i++;
                    k++;
                    nb += mid - j + 1;
                }
            }
            while (j <= mid) {
                op[i] = a[j];
                i = i + 1;
                j = j + 1;

            }
            while (k <= max) {
                op[i] = a[k];
                i = i + 1;
                k = k + 1;

            }
            System.arraycopy(op, 0, a, min, i);
        }
        return nb;
    }
}
