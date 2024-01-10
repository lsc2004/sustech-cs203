import java.io.*;
import java.util.StringTokenizer;

public class H48 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] op = new int[n][n];
            for (int j = 0; j < m; j++) {
                int o = in.nextInt();
                int p = in.nextInt();
                op[o - 1][p - 1]++;
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    out.print(op[j][k] + " ");
                }
                out.println(" ");
            }
        }
        out.close();
    }
}
