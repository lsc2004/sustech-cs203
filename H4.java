public class H4 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        int c;
        int k;
        int g;
        for (int i = 0; i < b1; i++) {
            c = a1.nextInt();
            k = a1.nextInt();
            g = a1.nextInt();
            int[][] NB = new int[2 * g + 1 + 2 * k][2 * c + 1 + 2 * k];
            make(c, k, g, NB);
            DO(out, c, k, g, NB);
        }
        out.close();
    }

    private static void make(int c, int k, int g, int[][] NB) {
        for (int j = 0; j < 2 * g + 1 + 2 * k; j++) {
            boolean x1 = j % 2 == 0;
            for (int l = 0; l < 2 * c + 1 + 2 * k; l++) {
                boolean x2 = l % 2 == 0;
                boolean t1 = l <= 2 * c && j >= 2 * k;
                boolean t2 = j < 2 * k && l >= 2 * k - j && l <= 2 * c + 2 * k - j;
                boolean t3=((j >= 2 * k && l >= 2 * c) || (j < 2 * k && l > 2 * c + 2 * k - j)) && (l <= 2 * c + 2 * k + 2 * g - j);
                if (t1) {
                    if (x1 && x2) {
                        NB[j][l] = 1;
                    }
                    if (x1 && (!x2)) {
                        NB[j][l] = 2;
                    }
                    if ((!x1) && x2) {
                        NB[j][l] = 3;
                    }
                    if ((!x1) && (!x2)) {
                        NB[j][l] = 0;
                    }
                }
                if (t2) {
                    if (x1 && x2) {
                        NB[j][l] = 1;
                    }
                    if (x1 && (!x2)) {
                        NB[j][l] = 2;
                    }
                    if ((!x1) && x2) {
                        NB[j][l] = 0;
                    }
                    if ((!x1) && (!x2)) {
                        NB[j][l] = 4;
                    }
                }
                if (t3) {
                    if (x1 && x2) {
                        NB[j][l] = 1;
                    }
                    if (x1 && (!x2)) {
                        NB[j][l] = 0;
                    }
                    if ((!x1) && x2) {
                        NB[j][l] = 3;
                    }
                    if ((!x1) && (!x2)) {
                        NB[j][l] = 4;
                    }
                }
            }
        }
    }

    private static void DO(QWriter out, int c, int k, int g, int[][] NB) {
        for (int j = 0; j < 2 * g + 1 + 2 * k; j++) {
            for (int l = 0; l < 2 * c + 1 + 2 * k; l++) {
                if (NB[j][l] == 0) {
                    out.print(".");
                }
                if (NB[j][l] == 1) {
                    out.print("+");
                }
                if (NB[j][l] == 2) {
                    out.print("-");
                }
                if (NB[j][l] == 3) {
                    out.print("|");
                }
                if (NB[j][l] == 4) {
                    out.print("/");
                }
            }
            out.println(" ");
        }
    }
}
