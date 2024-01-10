import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;

public class H49_1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int target = in.nextInt();
            ArrayList[] op = new ArrayList[n + 1];
            for (int j = 0; j < n + 1; j++) {
                op[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int a1 = in.nextInt();
                int a2 = in.nextInt();
                op[a1].add(a2);
                op[a2].add(a1);
            }
            int[] visited = new int[n + 1];
            int[] dist = new int[n + 1];
            Arrays.fill(dist, 114514);
            linkDeque g = new linkDeque(600001);
            g.addLast(target);
            visited[target]++;
            dist[target] = 0;
            while (g.size != 0) {
                int curr = g.getFirst();
                g.removeFirst();
                for (Object I : op[curr]) {
                    if (visited[(int) I] == 0) {
                        dist[(int) I] = dist[curr] + 1;
                        visited[(int) I]++;
                        g.addLast((Integer) I);
                    }
                }
            }
            for (int j = 1; j < dist.length; j++) {
                if (dist[j] == 114514) {
                    out.print(-1 + " ");
                } else {
                    out.print(dist[j] + " ");
                }
            }
            out.println(" ");
        }
        out.close();
    }
}
