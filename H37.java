import java.io.*;
import java.util.StringTokenizer;
public class H37 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n=in.nextInt();
        int[] l =new int[n+1];
        l[1]=1;
        for (int i = 0; i < n-1; i++) {
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            l[x1]++;
            l[x2]++;
        }
        for (int i = 0; i < l.length; i++) {
            if (l[i]==1){
                out.print(i+" ");
            }
        }
        out.close();
    }
}
