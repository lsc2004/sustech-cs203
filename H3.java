import java.io.*;
import java.util.StringTokenizer;

public class H3 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            int c1 = a1.nextInt();
            int[] x = new int[c1];
            for (int j = 0; j < c1; j++) {
                int p = a1.nextInt();
                x[j] = p;
            }
            int k=0;
            int l=1;
            int m=x[0]-x[1];
            m = getMax(c1, x, k, l, m);
            out.println(m);
        }
        out.close();
    }

    private static int getMax(int c1, int[] x, int k, int l, int m) {
        while (l < c1){
            int n= x[k]- x[l];
            if (n<0){
                k = l;
            }
            if (n> m){
                m =n;
            }
            l = l +1;
        }
        return m;
    }
}

