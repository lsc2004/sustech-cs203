import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class H16 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int[] a = new int[n];
        int[] op = new int[n];
        ArrayList<Integer> m = new ArrayList<>();
        int counter1 = 0;
        int counter2 = 0;
        while (counter1 < n) {
            a[counter1] = in.nextInt();
            counter1 = counter1 + 1;
        }
        sort(a, op, 0, n - 1);
        int x = a[n / 3];
        while (a[counter2] < x) {
            m.add(a[counter2]);
            counter2++;
        }
        int[] order = new int[n];
        for (int i = 0; i < m.size(); i++) {
            order[3 * i] = m.get(i);
        }
        int j = m.size();
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 && i / 3 < m.size()) {
                i++;
            }
            order[i] = op[j];
            j++;
        }
        out.println(order[1]);
        for (int k : order) {
            out.print(k + " ");
        }
        out.close();
    }

    public static void sort(int[] a, int[] op, int min, int max) {
        if (max > min) {
            int mid = (max + min) >>> 1;
            sort(a, op, min, mid);
            sort(a, op, mid + 1, max);
            int i = 0;
            int j = min, k = mid + 1;
            while (j <= mid && k <= max) {
                op[i++] = (a[j] < a[k]) ? a[j++] : a[k++];
            }
            while (j <= mid) {
                op[i] = a[j];
                i = i + 1;
                j = j + 1;
            }
            while (k <= max) {
                op[i] = a[k];
                i = i + 1;
                k = k + 1;
            }
            System.arraycopy(op, 0, a, min, i);
        }
    }
}

