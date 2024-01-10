import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H51 {
    static int enter = 0;
    static int go = 0;
    static linkDeque d = new linkDeque(150001);

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer>[] op = new ArrayList[n + 1];
            int[] visited = new int[n + 1];
            int[] inside = new int[n + 1];
            int[] outside = new int[n + 1];
            Arrays.fill(visited, 0);
            for (int j = 0; j < op.length; j++) {
                op[j] = new ArrayList<>();
            }
            long target = ((long) (1 + n) * n) / 2;
            for (int j = 0; j < n - 1; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                op[y].add(x);
                target = target - x;
            }
            dfs( target, op, visited, inside,outside);
            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (inside[x] >= inside[y] && outside[x] <= outside[y]) {
                    out.println("Yes");
                } else {
                    out.println("No");
                }
            }
        }
        out.close();
    }

    public static void dfs(long s, ArrayList<Integer>[] op, int[] visited, int[] inside,int[] outside) {
        visited[Math.toIntExact(s)]++;
        enter++;
        inside[Math.toIntExact(s)] = enter;
        d.addLast(Math.toIntExact(s));
        for (int i = 0; i < op[Math.toIntExact(s)].size(); i++) {
            if (visited[ op[Math.toIntExact(s)].get(i)] == 0) {
                dfs(op[Math.toIntExact(s)].get(i), op, visited, inside,outside);
            }
        }
        go++;
        outside[d.getLast()]=go;
        d.removeLast();
    }
}
