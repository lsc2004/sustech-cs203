import java.io.*;
import java.util.StringTokenizer;

public class H25 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int[] p1 = new int[p + 1];
        int[] q1 = new int[q + 1];
        int[] time = new int[100005];
        int[] condition = new int[100005];
        for (int i = 0; i < p; i++) {
            p1[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            q1[i] = in.nextInt();
        }
        int counter1 = 0;
        int counter2 = 0;
        for (int i = 0; i < Math.max(p, q); i++) {
            if (p1[counter1] == 0 && q1[counter2] == 0) {
                break;
            }
            if (p1[counter1] != 0) {
                while (p1[counter1] != 0 && condition[p1[counter1]] != 0) {
                    counter1++;
                }
                time[p1[counter1]] = i + 1;
                condition[p1[counter1]] = 1;
            }
            if (q1[counter2] != 0) {
                while (q1[counter2] != 0 && condition[q1[counter2]] != 0) {
                    if (q1[counter2]==p1[counter1]){
                        break;
                    }
                    counter2++;
                }
                time[q1[counter2]] = i + 1;
                condition[q1[counter2]] = 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            out.print(time[i] + " ");
        }
        out.close();
    }
}
