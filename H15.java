import java.io.*;
import java.util.StringTokenizer;

public class H15 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        int b2= a1.nextInt();
        long[] x = new long[b1];
        long g=0;
        for (int i = 0; i < b1; i++) {
            x[i] = a1.nextLong();
        }
        long[] a = new long[b1];
        sort(x, a, 0, b1 - 1);
        for (int i = 0; i < b2; i++) {
            g=x[i];
        }

        out.println(g);
        out.close();
    }

    private static void sort(long[] a, long[] op, int min, int max) {
        if (max > min) {
            int mid = (max + min) >>> 1;
            sort(a, op, min, mid);
            sort(a, op, mid + 1, max);
            int i = 0;
            int j = min, k = mid + 1;
            while (j <= mid && k <= max) {
                op[i++] = (a[j] < a[k]) ? a[j++] : a[k++];
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
    }
}
