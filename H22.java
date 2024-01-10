import java.io.*;
import java.util.StringTokenizer;

//这是D题
@SuppressWarnings("ALL")
public class H22 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            node4 op;
            int[] a = new int[n];
            int[] b = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
                b[j] = j;
            }
            int[] op1 = new int[n];
            int[] op2 = new int[n];
            sort(a, op1, b, op2, 0, n - 1);
            node4[] Index = new node4[n];
            node4 head= new node4(a[0], b[0], null, null);
            op = head;
            Index[b[0]] = op;
            int index = 1;
            while (index < n) {
                op.next = new node4(a[index], b[index], op, null);
                op = op.next;
                Index[b[index]] = op;
                index++;
            }
            node4 fast = head;
            node4 slow = head;
            while (true) {
                if (fast.next == null || fast.next.next == null) {
                    break;
                }
                fast = fast.next.next;
                slow = slow.next;
            }
            int[] resalut = new int[(n / 2) + 1];
            int counter = 0;
            for (int j = n - 1; j > 1; j--) {
                if (j % 2 == 0) {
                    if (counter == 0) {
                        slow = slow;
                    } else if (counter > 0) {
                        slow = slow.next;
                    } else if (counter < 0) {
                        slow = slow.pre;
                    }
                    counter = 0;
                    resalut[j / 2] = slow.value;
                }
                if (Index[j].value > slow.value) {
                    counter--;
                } else if (Index[j].value < slow.value) {
                    counter++;
                } else if (Index[j].value == slow.value) {
                    if (j > slow.index) {
                        counter--;
                    } else if (j < slow.index) {
                        counter++;
                    }
                }
                remove(Index[j]);
            }
            out.print(Index[0].value+" ");
            for (int j = 1; j < resalut.length; j++) {
                out.print(resalut[j]+" ");
            }
            out.println(" ");
        }
        out.close();
    }

    public static boolean isNotnext(node4 a) {
        return a.next != null;

    }

    public static boolean isNotpre(node4 a) {
        return a.pre != null;
    }

    private static void remove(node4 a) {
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

class node4 {
    int value;
    int index;
    node4 next;
    node4 pre;

    public node4(int value, int index, node4 pre, node4 next) {
        this.value = value;
        this.index = index;
        this.pre = pre;
        this.next = next;
    }
}
