import java.io.*;
import java.util.StringTokenizer;

public class H44 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a1 = new int[n];
        int[] a2 = new int[m];
        int[] op1 = new int[n];
        for (int i = 0; i < n; i++) {
            a1[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            a2[i] = in.nextInt();
        }
        sort(a1, op1, 0, n - 1);
        minHeap g = new minHeap(5000001);
        for (int i = 0; i < a2.length; i++) {
            long v = (long) a1[0] * a2[i];
            g.inUp(0, i, v);
        }
        for (int i = 0; i < k; i++) {
            long[] r=g.poll();
            long x = r[1];
            long y = r[2];
            out.print(r[0] + " ");
            if (x < n - 1) {
                long v = (long) a1[Math.toIntExact(x + 1)] * a2[Math.toIntExact(y)];
                g.inUp(x + 1, y, v);
            }
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

class minHeap {
    long[] it;
    int[] index1;
    int[] index2;
    int size;

    public minHeap(int cap) {
        this.it = new long[cap];
        this.index1 = new int[cap];
        this.index2 = new int[cap];
    }

    public void inUp(long i, long j, long value) {
        size++;
        int nb = size;
        while (nb > 1) {
            int k = nb / 2;
            if (value < it[k]) {
                it[nb] = it[k];
                index1[nb] = index1[k];
                index2[nb] = index2[k];
            } else {
                break;
            }
            nb = k;
        }
        it[nb] = value;
        index1[nb] = Math.toIntExact(i);
        index2[nb] = Math.toIntExact(j);
    }

    public void Down(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= size && it[left] < it[max]) {
            max = left;
        }
        if (right <= size && it[right] < it[max]) {
            max = right;
        }
        if (max != i) {
            swapIt(max, i);
            Down(max);
        }
    }

    public long[] poll() {
        long[] r = new long[3];
        r[0] = it[1];
        r[1] = index1[1];
        r[2] = index2[1];
        swapIt(1, size);
        size--;
        Down(1);
        return r;
    }

    public void swapIt(int i, int j) {
        long t = it[i];
        int x = index1[i];
        int y = index2[i];
        it[i] = it[j];
        index1[i] = index1[j];
        index2[i] = index2[j];
        it[j] = t;
        index1[j] = x;
        index2[j] = y;
    }
}