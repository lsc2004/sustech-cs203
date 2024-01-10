import java.io.*;
import java.util.StringTokenizer;
public class H43 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] h = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            int cnt=0;
            h[i] = in.nextInt();
            int index = i;
            while (index > 1) {
                if (h[index] >= h[index / 2]) {
                    int tmp = h[index];
                    h[index] = h[index / 2];
                    h[index / 2] = tmp;
                    index = index / 2;
                    cnt++;
                } else {
                    break;
                }
            }
            out.print(cnt+" ");
        }
        out.close();
    }
}
