import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class H28 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        ArrayList<int[]> a = new ArrayList<>();
        int k = in.nextInt();
        int counter = 0;
        int sum = 0;
        while (true) {
            int m = in.nextInt();
            int[] num = new int[2];
            num[0] = m;
            counter++;
            num[1] = counter;
            if (m == -1) {
                break;
            }
            if (a.isEmpty()) {
                a.add(num);
            } else {
                if (counter - a.get(0)[1] == k) {
                    a.remove(0);
                }
                for (int i = a.size() - 1; i >= 0; i--) {
                    if (num[0] > a.get(i)[0]) {
                        a.remove(i);
                    }
                }
                a.add(num);
                if (counter >= k) {
                    sum = sum ^ a.get(0)[0];
                }
            }
        }
        out.println(sum);
        out.close();
    }
}
