import java.io.*;
import java.util.StringTokenizer;

public class H32 {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        String s = in.nextLine();
        int[][] fsa = new int[s.length()][26];
        int[] compiled = new int[s.length()];
        int len = 1;
        for (int i = 0; i < s.length(); i++) {
            compiled[i] = compile(s, i, len);
            fsa[i][compiled[i]] = i + 1;
        }
        int next = 0;
        for (int i = 1; i < s.length(); i++) {
            if (i > 1) {
                next = fsa[next][compile(s, i - 1, len)];
            }
            for (int j = 0; j < 26; j++) {
                if (j != compiled[i]) {
                    fsa[i][j] = fsa[next][j];
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                out.print(fsa[i][j] + " ");
            }
            out.println(" ");
        }
        out.close();
    }

    private static int compile(String str, int i, int len) {
        String substring = str.substring(i, i + len);
        if (substring.equals("a")) {
            return 0;
        }
        if (substring.equals("b")) {
            return 1;
        }
        if (substring.equals("c")) {
            return 2;
        }
        if (substring.equals("d")) {
            return 3;
        }
        if (substring.equals("e")) {
            return 4;
        }
        if (substring.equals("f")) {
            return 5;
        }
        if (substring.equals("g")) {
            return 6;
        }
        if (substring.equals("h")) {
            return 7;
        }
        if (substring.equals("i")) {
            return 8;
        }
        if (substring.equals("j")) {
            return 9;
        }
        if (substring.equals("k")) {
            return 10;
        }
        if (substring.equals("l")) {
            return 11;
        }
        if (substring.equals("m")) {
            return 12;
        }
        if (substring.equals("n")) {
            return 13;
        }
        if (substring.equals("o")) {
            return 14;
        }
        if (substring.equals("p")) {
            return 15;
        }
        if (substring.equals("q")) {
            return 16;
        }
        if (substring.equals("r")) {
            return 17;
        }
        if (substring.equals("s")) {
            return 18;
        }
        if (substring.equals("t")) {
            return 19;
        }
        if (substring.equals("u")) {
            return 20;
        }
        if (substring.equals("v")) {
            return 21;
        }
        if (substring.equals("w")) {
            return 22;
        }
        if (substring.equals("x")) {
            return 23;
        }
        if (substring.equals("y")) {
            return 24;
        }
        if (substring.equals("z")) {
            return 25;
        }
        return -1;
    }
}