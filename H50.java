import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class H50 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList[] maps = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                maps[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                maps[a].add(b);
                maps[b].add(a);
            }
            int[] searched = new int[n + 1];
            int[] odd = new int[n + 1];
            int oddCount = 0;
            linkDeque deque = new linkDeque(200001);
            deque.addLast(1);
            odd[1] = 1;
            searched[1] = 1;
            oddCount++;
            while (deque.size != 0) {
                int city = deque.getFirst();
                deque.removeFirst();
                for (Object next : maps[city]) {
                    if (searched[(int) next] == 1) {
                        continue;
                    }
                    if (odd[city] == 1) {
                        odd[(int) next] = 0;
                    } else {
                        odd[(int) next] = 1;
                    }
                    if (odd[(int) next] == 1) {
                        oddCount++;
                    }
                    deque.addLast((Integer) next);
                    searched[(int) next] = 1;
                }
            }
            for (int j = 0; j < searched.length; j++) {
                searched[j] = 0;
            }
            out.println(Math.min(oddCount, n - oddCount));
            deque.addLast(1);
            searched[1] = 1;
            while (deque.size != 0) {
                int city = deque.getFirst();
                deque.removeFirst();
                if ((oddCount <= (n - oddCount) && odd[city] == 1) || (oddCount > (n - oddCount) && odd[city] == 0)) {
                    out.print(city + " ");
                }
                for (Object next : maps[city]) {
                    if (searched[(int) next] == 1) {
                        continue;
                    }
                    deque.addLast((Integer) next);
                    searched[(int) next] = 1;
                }
            }
            out.println(" ");
        }
        out.close();
    }
}
