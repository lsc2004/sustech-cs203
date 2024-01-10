import java.io.*;
import java.util.StringTokenizer;

public class H12 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            int c1 = a1.nextInt();
            int c2 = a1.nextInt();
            int[] x = new int[c1];
            int[] z = new int[c1 + c2];
            int[] y = new int[c2];
            for (int j = 0; j < c1; j++) {
                x[j] = a1.nextInt();
            }
            for (int j = 0; j < c2; j++) {
                y[j] = a1.nextInt();
            }
            for (int j = 0; j < z.length; j++) {
                if (j < c1) {
                    z[j] = x[j];
                } else {
                    z[j] = y[j - c1];
                }
            }
            int[] a = new int[c1 + c2];
            sort(z, a, 0, c1 + c2 - 1);
            for (int k : z) {
                out.print(k + " ");
            }
            out.println(" ");
        }
        out.close();
    }

    public static void sort(int[] a, int[] op, int min, int max) {
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
