import java.io.*;
import java.util.StringTokenizer;

public class H17 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int n = a1.nextInt();
        int p = a1.nextInt();
        int q = a1.nextInt();
        long[] h = new long[n];
        long[] s = new long[n];
        q = Math.min(q, n);
        boolean c = q == 0;
        if (c) {
            p = 0;
        }
        long[] difference = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = a1.nextLong();
            s[i] = a1.nextLong();
            difference[i] = h[i] - s[i];
        }
        long[] op1 = new long[n];
        long[] op2 = new long[n];
        long[] op3 = new long[n];
        sort(difference, op1, h, op2, s, op3, 0, n - 1);
        long max = 0;
        int nb = n - q;
        long w = getMax(n, p, h, s, c, difference, max, nb);
        int counter = 0;
        while (counter < n) {
            if (counter < nb || difference[counter] <= 0) {
                w = w + s[counter];
            } else {
                w = w + h[counter];
            }
            counter = counter + 1;
        }
        out.println(w);
        out.close();
    }

    private static long getMax(int n, int p, long[] h, long[] s, boolean c, long[] difference, long max, int nb) {
        if (!c) {
            int counter1 = n - 1;
            int counter2 = nb - 1;
            while (counter1 > nb - 1) {
                long t = (long) (h[counter1] * Math.pow(2, p));
                if (difference[counter1] < 0) {
                    t = t - s[counter1];
                } else {
                    t = t - h[counter1];
                }
                max = Math.max(max, t);
                counter1 = counter1 - 1;
            }
            long g;
            g = Math.max(0, difference[nb]);
            while (counter2 >= 0) {
                long t = (long) (h[counter2] * Math.pow(2, p));
                t = t - s[counter2] - g;
                max = Math.max(max, t);
                counter2 = counter2 - 1;
            }
        }
        return max;
    }

    private static void sort(long[] a, long[] op1, long[] b, long[] op2, long[] c, long[] op3, int min, int max) {
        if (max > min) {
            int mid = (max + min) >>> 1;
            sort(a, op1, b, op2, c, op3, min, mid);
            sort(a, op1, b, op2, c, op3, mid + 1, max);
            int i = 0;
            int j = min, k = mid + 1;
            while (j <= mid && k <= max) {
                if (a[j] <= a[k]) {
                    op1[i] = a[j];
                    op2[i] = b[j];
                    op3[i] = c[j];
                    i++;
                    j++;
                } else {
                    op1[i] = a[k];
                    op2[i] = b[k];
                    op3[i] = c[k];
                    i++;
                    k++;
                }
            }
            while (j <= mid) {
                op1[i] = a[j];
                op2[i] = b[j];
                op3[i] = c[j];
                i = i + 1;
                j = j + 1;
            }
            while (k <= max) {
                op1[i] = a[k];
                op2[i] = b[k];
                op3[i] = c[k];
                i = i + 1;
                k = k + 1;
            }
            System.arraycopy(op1, 0, a, min, i);
            System.arraycopy(op2, 0, b, min, i);
            System.arraycopy(op3, 0, c, min, i);
        }
    }
}
