import java.util.ArrayList;

public class H2 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            ArrayList<Integer> k = new ArrayList<>();
            ArrayList<String> x = new ArrayList<>();
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            k.add(0);
            for (int j = 0; j < 13; j++) {
                String c1 = a1.next();
                x.add(c1);
                GET(k, j, c1);
            }
            order(k, x);
            for (String p : x) {
                out.print(p + " ");
            }
            out.println(" ");
        }
        out.close();
    }

    public static void order(ArrayList<Integer> list0, ArrayList<String> listq) {
        int a;
        String b;
        for (int i = 0; i < list0.size() - 1; i++) {
            for (int j = 0; j < list0.size() - 1; j++) {
                if (list0.get(j) > list0.get(j + 1)) {
                    a = list0.get(j);
                    list0.set(j, list0.get(j + 1));
                    list0.set(j + 1, a);
                    b = listq.get(j);
                    listq.set(j, listq.get(j + 1));
                    listq.set(j + 1, b);
                }
            }
        }
    }

    private static void GET(ArrayList<Integer> k, int j, String c1) {
        if (c1.equals("W1")) {
            k.set(j, 0);
        }
        if (c1.equals("W2")) {
            k.set(j, 1);
        }
        if (c1.equals("W3")) {
            k.set(j, 2);
        }
        if (c1.equals("W4")) {
            k.set(j, 3);
        }
        if (c1.equals("W5")) {
            k.set(j, 4);
        }
        if (c1.equals("W6")) {
            k.set(j, 5);
        }
        if (c1.equals("W7")) {
            k.set(j, 6);
        }
        if (c1.equals("W8")) {
            k.set(j, 7);
        }
        if (c1.equals("W9")) {
            k.set(j, 8);
        }
        if (c1.equals("T1")) {
            k.set(j, 9);
        }
        if (c1.equals("T2")) {
            k.set(j, 10);
        }
        if (c1.equals("T3")) {
            k.set(j, 11);
        }
        if (c1.equals("T4")) {
            k.set(j, 12);
        }
        if (c1.equals("T5")) {
            k.set(j, 13);
        }
        if (c1.equals("T6")) {
            k.set(j, 14);
        }
        if (c1.equals("T7")) {
            k.set(j, 15);
        }
        if (c1.equals("T8")) {
            k.set(j, 16);
        }
        if (c1.equals("T9")) {
            k.set(j, 17);
        }
        if (c1.equals("Y1")) {
            k.set(j, 18);
        }
        if (c1.equals("Y2")) {
            k.set(j, 19);
        }
        if (c1.equals("Y3")) {
            k.set(j, 20);
        }
        if (c1.equals("Y4")) {
            k.set(j, 21);
        }
        if (c1.equals("Y5")) {
            k.set(j, 22);
        }
        if (c1.equals("Y6")) {
            k.set(j, 23);
        }
        if (c1.equals("Y7")) {
            k.set(j, 24);
        }
        if (c1.equals("Y8")) {
            k.set(j, 25);
        }
        if (c1.equals("Y9")) {
            k.set(j, 26);
        }
        if (c1.equals("E")) {
            k.set(j, 27);
        }
        if (c1.equals("S")) {
            k.set(j, 28);
        }
        if (c1.equals("W")) {
            k.set(j, 29);
        }
        if (c1.equals("N")) {
            k.set(j, 30);
        }
        if (c1.equals("B")) {
            k.set(j, 31);
        }
        if (c1.equals("F")) {
            k.set(j, 32);
        }
        if (c1.equals("Z")) {
            k.set(j, 33);
        }
    }
}
