import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import java.util.StringTokenizer;

public class H36 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(), num = in.nextInt();
        ArrayList[] c = new ArrayList[n];
        ArrayList[] l = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            c[i] = new ArrayList<>();
            l[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt(), b = in.nextInt(), w = in.nextInt();
            final AtomicBoolean add1 = new AtomicBoolean(false);
            add1.set(c[a - 1].add(b - 1));
            final AtomicBoolean add2 = new AtomicBoolean(false);
            add2.set(l[a - 1].add(w));
            final AtomicBoolean add3 = new AtomicBoolean(false);
            add3.set(c[b - 1].add(a - 1));
            final AtomicBoolean add4 = new AtomicBoolean(false);
            add4.set(l[b - 1].add(w));
        }

        int[] q = new int[n], p = new int[n];
        int f = 0, r = 0, cnt = 0;
        int[] v = new int[n];
        q[r++] = 0;
        out.println(r);
        v[0]++;
        while (r != f) {
            int t = q[f++];
            out.println(f+"sb");
            int counter = 0;
            while (counter < c[t].size()) {
                int d = (int) c[t].get(counter);
                if (v[d] == 0) {
                    p[d] = p[t] + (int) l[t].get(counter);
                    if (p[d] == num && c[d].size() == 1) {
                        cnt++;
                    }
                    q[r++] = d;
                    v[d]++;
                }
                counter++;
            }
        }
        out.println(cnt);
        out.close();
    }
}
