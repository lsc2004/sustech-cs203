import java.io.*;
import java.util.StringTokenizer;

public class H30 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String s = in.next();
            out.println((s.length() * (s.length() + 1)) / 2);
        }
        out.close();
    }
}
