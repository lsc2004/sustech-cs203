import java.io.*;
import java.util.StringTokenizer;

public class H26 {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        char[] c = in.next().toCharArray();
        int[] a = new int[176453];
        int top = 1;
        for (char value : c) {
            if (value == '(') {
                top += 1;
            } else {
                top -= 1;
                if (a[top] == 0) {
                    a[top - 1] = (a[top - 1] + 1) % 514329;
                } else {
                    a[top - 1] = (a[top - 1] + a[top] * 2) % 514329;
                    a[top] = 0;
                }
            }
        }
        out.print(a[0]);
        out.close();
    }
}
