import java.util.Scanner;

public class H42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();
            int root = (n * (1 + n) / 2);
            int[][] nod = new int[110000][4];
            int[] tre = new int[110000];
            for (int i = 0; i < 110000; i++) {
                nod[i][0] = 0;
                nod[i][1] = 0;
                nod[i][2] = 0;
                nod[i][3] = 0;
                tre[i] = 0;
            }
            for (int i = 1; i <= n; i++) {
                nod[i][3] = sc.nextInt();
            }
            boolean t1 = true, t2 = false;
            int tmp, son;
            for (int i = 1; i < n; i++) {
                tmp = sc.nextInt();
                son = sc.nextInt();

                if (i == 1) {
                    t2 = (nod[tmp][3] <= nod[son][3]);
                } else if (t2 != (nod[tmp][3] <= nod[son][3])) {
                    t1 = false;
                }

                if (nod[tmp][0] > 1) {
                    t1 = false;
                    continue;
                }
                if (!t1) {
                    continue;
                }
                nod[tmp][0]++;
                nod[tmp][nod[tmp][0]] = son;
                root -= son;
            }
            boolean result = true;
            tre[1] = root;
            int tmp1;
            for (int i = 1; i <= n; i++) {
                if (tre[i] == 0) {
                    result = false;
                    break;
                }
                tmp1 = tre[i];
                if (nod[tmp1][1] != 0) {
                    if (i * 2 > n) {
                        result = false;
                        break;
                    }
                    tre[i * 2] = nod[tmp1][1];
                }
                if (nod[tmp1][2] != 0) {
                    if (i * 2 + 1 > n) {
                        result = false;
                        break;
                    }
                    tre[i * 2 + 1] = nod[tmp1][2];
                }
            }
            if (t1 && result) {
                System.out.printf("Case #%d: YES\n", t);
            } else {
                System.out.printf("Case #%d: NO\n", t);
            }
        }
        sc.close();
    }
}