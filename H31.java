import java.io.*;
import java.util.StringTokenizer;

public class H31 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String pattern=in.next();
        int[] next = getNext(pattern);
        for (int j : next) {
            out.println(j);
        }
        out.close();
    }

    private static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        int i = 1, j = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                next[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }
}
