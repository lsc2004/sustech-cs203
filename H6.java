public class H6 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        int[] x = new int[b1];
        for (int i = 0; i < b1; i++) {
            int c1 = a1.nextInt();
            x[i] = c1;
        }
        int d1 = a1.nextInt();
        for (int i = 0; i < d1; i++) {
            int a = a1.nextInt();
            int nb = 0;
            int t = 0, j = x.length - 1;
            while (t <= j) {
                int m = (t + j) >>> 1;
                if (a < x[m]) {
                    j = m - 1;
                    if (m >= 1 && a > x[j]) {
                        t = j + 3;
                    }
                } else if (a > x[m]) {
                    t = m + 1;
                    if (m <= x.length - 2 && a < x[t]) {
                        t = j + 3;
                    }
                } else if (a == x[m]) {
                    nb++;
                    break;
                }
            }
            if (nb == 0) {
                out.println("NO");
            } else {
                out.println("YES");
            }
        }
        out.close();
    }
}
