public class H8 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        int b2 = a1.nextInt();
        int[] x = new int[b1];
        for (int i = 0; i < b1; i++) {
            x[i] = a1.nextInt();
        }
        for (int i = 0; i < b2; i++) {
            int m = a1.nextInt();
            int n = a1.nextInt();
            int op = getL(n, x) - getR(m, x) - 1;
            if (op > 0) {
                out.println("YES" + " " + op);
            } else {
                out.println("NO");
            }
        }
        out.close();
    }

    public static int getL(int k, int[] b) {
        int i = 0, j = b.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (k <= b[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static int getR(int k, int[] b) {
        int i = 0, j = b.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (k < b[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return j;
    }
}
