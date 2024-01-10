public class H1 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        int[] s = new int[100001];
        for (int i = 0; i < b1; i++) {
            int b2 = a1.nextInt();
            s[b2]++;
        }
        int b3 = a1.nextInt();
        DO(a1, out, s, b3);
        out.close();
    }

    private static void DO(QReader a1, QWriter out, int[] s, int b3) {
        int i = 0;
        while (i < b3) {
            i = i + 1;
            int b4 = a1.nextInt();
            if (s[b4] == 0) {
                out.println("no");
            } else {
                out.println("yes");
            }
        }
    }
}

