import java.util.ArrayList;

public class H35 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        ArrayList<String> key = new ArrayList<>();
        for (int i = 0; i <= 'z'-'a'; i++) {
            key.add(in.next());
        }
        String t = in.next();
        char[] op = new char[t.length()];
        for (int i = 0; i < t.length(); i++) {
            op[i] = t.substring(i, i + 1).charAt(0);
        }
        int half = t.length() / 2 + t.length() % 2;
        for (int i = 0; i < t.length() - half; i++) {
            op[i + half] = key.get(op[i + half] - 'a').charAt(0);
        }
        ArrayList<Integer> p = new ArrayList<>();
        for (int i = 0; i < t.length(); i++) {
            p.add(0);
        }
        int tmp = 0;
        int counter = 0;
        while (true) {
            int compare = Integer.compare(counter, t.length());
            if (compare == 0 || compare == 1) {
                break;
            }
            if (counter == 0) {
                p.add(counter);
            } else {
                while (tmp > 0) {
                    if (op[counter] == op[tmp]) {
                        break;
                    }
                    tmp = getG(p, tmp);
                }
                if (op[counter] == op[tmp]) {
                    tmp += 1;
                }
                p.set(counter, tmp);
            }
            counter += 1;
        }
        int a1 = t.length();
        a1 = a1 - 1;
        int g = p.get(a1);
        while (true) {
            int compare = Integer.compare(g, t.length() - half);
            if (compare == -1 || compare == 0) {
                break;
            }
            g = getG(p, g);
        }
        out.print(t.length() - g);
        out.close();
    }

    private static int getG(ArrayList<Integer> p, int g) {
        int a = g;
        a = a - 1;
        g = p.get(a);
        return g;
    }

}