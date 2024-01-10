import java.io.*;
import java.util.StringTokenizer;

public class H52 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] op = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    op[j][k] = in.nextInt();
                }
            }
            out.println(maxSum(op));
        }
        out.close();
    }
    public static int maxSum(int[][] grid){
        int cols = grid[0].length;
        int rows = grid.length;
        int[][] dp = new int[rows + 1][1 << cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < (1 << cols); j++){
                if (Integer.bitCount(j) > 1 && (j & (j >> 1)) != 0)
                    continue;
                for (int k = 0; k < (1 << cols); k++) {
                    if((k & j) != 0 || (k << 1 & j) != 0 || (j << 1 & k) != 0)
                        continue;
                    int sum = 0;
                    for(int z = 0; z < cols; z++)
                        if(((j >> z) & 1) == 1)
                            sum += grid[i - 1][z];
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + sum);
                }
            }
        }
        int maxSum = 0;
        for (int i : dp[rows])
            maxSum = Math.max(maxSum, i);
        return maxSum;
    }
}
