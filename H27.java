import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class H27 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] a = new int[n + 1];
            int[] b = new int[n + 1];
            for (int j = 0; j < a.length; j++) {
                if (j < n) {
                    a[j] = in.nextInt();
                    continue;
                }
                a[j] = j + 1;
            }
            int min = a[n];
            for (int j = n; j >= 0; j--) {
                if (a[j] < min) {
                    min = a[j];
                }
                b[j] = min;
            }
            int[] c = new int[a[n]];
            ArrayList<Integer> m = new ArrayList<>();
            int top = 0;
            for (int j = 0; j < a[n]; j++) {
                while (top != 0) {
                    int target = c[(n + top) - ((n + top) / (a[n])) * (a[n])];
                    int compare = Integer.compare(target, b[j]);
                    if (compare == 1 || compare == 0) {
                        break;
                    }
                    m.add(target);
                    top = (n + top) - ((n + top) / (a[n])) * (a[n]);
                }
                int compare = Integer.compare(a[j], b[j]);
                if (compare == 1 || compare == -1) {
                    c[top] = a[j];
                    top = (top + 1) - ((top + 1) / n) * n;
                } else {
                    m.add(a[j]);
                }
            }
            for (int j = top; j != 0; j = (n + j) - ((n + j) / (a[n])) * (a[n])) {
                m.add(c[(n + j) - ((n + j) / (a[n])) * (a[n])]);
            }
            for (Integer integer : m) {
                if (integer != a[n]) {
                    out.print(integer + " ");
                }
            }
            out.println(" ");
        }
        out.close();
    }
}
