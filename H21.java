import java.io.*;
import java.util.StringTokenizer;

//这是e题
@SuppressWarnings("ALL")
public class H21 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        node5 op;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = i;
        }
        int[] op1 = new int[n];
        int[] op2 = new int[n];
        sort(a, op1, b, op2, 0, n - 1);
        node5[] Index = getIndex(n, a, b);
        for (int i = 0; i < n - 1; i++) {
            if (i > 0) {
                remove(Index[i - 1]);
            }
            op = Index[i];
            if (isNotpre(op) && isNotnext(op)) {
                if (2 * op.value < op.next.value + op.pre.value) {
                    out.print(op.value - op.pre.value + " ");
                } else {
                    out.print(op.next.value - op.value + " ");
                }
            } else if (isNotpre(op)) {
                out.print(op.value - op.pre.value + " ");
            } else {
                out.print(op.next.value - op.value + " ");
            }
        }
        out.close();
    }

    private static node5[] getIndex(int n, int[] a, int[] b) {
        node5 op;
        node5[] Index = new node5[n];
        node5 head = new node5(a[0], null, null);
        op = head;
        Index[b[0]] = op;
        int index = 1;
        while (index < n) {
            op.next = new node5(a[index], op, null);
            op = op.next;
            Index[b[index]] = op;
            index++;
        }
        return Index;
    }

    public static boolean isNotnext(node5 a) {
        return a.next != null;

    }

    public static boolean isNotpre(node5 a) {
        return a.pre != null;
    }

    private static void remove(node5 a) {
        if (isNotpre(a)) {
            a.pre.next = a.next;
        }
        if (isNotnext(a)) {
            a.next.pre = a.pre;
        }
    }

    private static void sort(int[] a, int[] op1, int[] b, int[] op2, int min, int max) {
        if (max > min) {
            int mid = (max + min) >>> 1;
            sort(a, op1, b, op2, min, mid);
            sort(a, op1, b, op2, mid + 1, max);
            int i = 0;
            int j = min, k = mid + 1;
            while (j <= mid && k <= max) {
                if (a[j] <= a[k]) {
                    op1[i] = a[j];
                    op2[i] = b[j];
                    i++;
                    j++;
                } else {
                    op1[i] = a[k];
                    op2[i] = b[k];
                    i++;
                    k++;
                }
            }
            while (j <= mid) {
                op1[i] = a[j];
                op2[i] = b[j];
                i = i + 1;
                j = j + 1;
            }
            while (k <= max) {
                op1[i] = a[k];
                op2[i] = b[k];
                i = i + 1;
                k = k + 1;
            }
            System.arraycopy(op1, 0, a, min, i);
            System.arraycopy(op2, 0, b, min, i);
        }
    }
}

class node5 {
    int value;
    node5 next;
    node5 pre;

    public node5(int value, node5 pre, node5 next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }
}