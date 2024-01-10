import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;
public class H54 {
    public static final int MAX_SIZE = 200005;
    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;
        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }
    static Edge[] e = new Edge[MAX_SIZE];
    static int n, m;
    static long minusSum = 0, sum = 0;
    static int[] t = new int[MAX_SIZE];
    public static int find(int p) {
        if (t[p] == -1) {
            return p;
        } else {
            return find(t[p]);
        }
    }
    public static void main(String[] args) throws Exception {
        QReader in = new QReader();
        QWriter out = new QWriter();
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < m; ++i) {
            e[i] = new Edge();
            e[i].u = in.nextInt();
            e[i].v = in.nextInt();
            e[i].w = in.nextInt();
            sum += Math.max(e[i].w, 0);
        }
        Arrays.sort(e,0, m);
        Arrays.fill(t, -1);
        int rdNum = 0;
        for (int i = 0; i < m; ++i) {
            boolean result;
            int h1 = find(e[i].u);
            int h2 = find(e[i].v);
            if (h1 == h2) {
                result = false;
            } else {
                t[h1] = h2;
                minusSum += Math.max(e[i].w, 0);
                result = true;
            }
            if (result) {
                if (++rdNum == n - 1) {
                    break;
                }
            }
        }
        out.println(sum - minusSum);
        out.close();
    }
}