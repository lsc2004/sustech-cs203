import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H57 {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        Node9E[] graph = new Node9E[n + 1];
        Node9E[] ds = new Node9E[n + 1];
        int[] scc1 = new int[n + 1];
        int[] pre = new int[m + 1];
        int[] next = new int[m + 1];
        int[] v1 = new int[n + 1];
        int[] v2 = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new Node9E(i);
            ds[i] = new Node9E(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            graph[u].children.add(graph[v]);
            ds[v].children.add(ds[u]);
            pre[i] = u;
            next[i] = v;
        }
        int[] sequence = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (v2[i] == 0) {
                dfs1(ds, i, sequence, v2);
            }
        }
        int scc = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (v1[sequence[i]] == 0) {
                ++scc;
                dfs2(graph, sequence[i], scc, v1, scc1);
            }
        }
        int op = 0;
        int[] ints = new int[scc + 1];
        for (int i = 0; i < m; i++) {
            if ((scc1[pre[i]] != scc1[next[i]]) && ints[scc1[next[i]]] == 0) {
                op++;
                ints[scc1[next[i]]] = 1;
            }
        }
        out.println(ints[scc1[s]] == 0 ? scc - op - 1 : scc - op);
        out.close();
    }
    public static void dfs1(Node9E[] graph, int cur, int[] sequence, int[] v2) {
        v2[cur]++;
        for (int i = 0; i < graph[cur].children.size(); i++) {
            if (v2[graph[cur].children.get(i).key] == 0) {
                dfs1(graph, graph[cur].children.get(i).key, sequence, v2);
            }
        }
        sequence[sequence[sequence.length - 1]++] = cur;
    }
    public static void dfs2(Node9E[] graph, int cur, int scc, int[] v1, int[] scc1) {
        v1[cur]++;
        scc1[cur] = scc;
        for (int i = 0; i < graph[cur].children.size(); i++) {
            if (v1[graph[cur].children.get(i).key] == 0) {
                dfs2(graph, graph[cur].children.get(i).key, scc, v1, scc1);
            }
        }
    }
}
class Node9E {
    int key;
    ArrayList<Node9E> children = new ArrayList();
    public Node9E(int index) {
        this.key = index;
    }
}


