import java.util.Arrays;
import java.util.Scanner;

public class H10 {
    public static void main(String[] args) {
        Scanner a1 = new Scanner(System.in);
        while (a1.hasNext()) {
            int L = a1.nextInt();
            int n = a1.nextInt();
            int m = a1.nextInt();
            int[] k = new int[n + 2];
            for (int i = 0; i < n + 2; i++) {
                if (i == 0) {
                    k[i] = 0;
                } else if (i == n + 1) {
                    k[i] = L;
                } else {
                    k[i] = a1.nextInt();
                }
            }
            Arrays.sort(k);
            int t = k[1] - k[0];
            for (int i = 0; i < n + 1; i++) {
                int i1 = k[i + 1] - k[i];
                if (i1 > t) {
                    t = i1;
                }
            }
            int counter = 1;
            int g = t;
            int h = L;
            while (g < h) {
                int mid = (g + h) >>> 1;
                if (test(k, counter, mid, m) == 1) {
                    g = mid + 1;
                } else if (test(k, counter, mid, m) == 0) {
                    h = mid - 1;
                }
            }
            System.out.println(g);
        }
    }

    public static int test(int[] k, int counter, int g, int m) {
        int x = 0;
        int y = 1;
        while (y <= k.length - 1) {
            if (k[y] - k[x] > g) {
                x = y - 1;
                y = y - 1;
                counter++;
            }
            y++;
        }
        if (counter < m) {
            return 0;
        }
        if (counter > m) {
            return 1;
        }
        return 0;
    }
}
