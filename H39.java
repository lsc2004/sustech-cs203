import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class H39 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] v = new int[n];
            for (int j = 0; j < n; j++) {
                v[j] = in.nextInt();
            }
            int[] sb=new int[n];
            sort(v,sb,0,n-1);
            ArrayList<treeH> m = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                m.add(new treeH(v[j], null, null));
            }
            ArrayList<Integer> op = new ArrayList<>();
            getOP(buildH(m), op);
            int result = 0;
            for (Integer integer : op) {
                result += integer;
            }
            out.println(result);
        }
        out.close();
    }

    public static treeH buildH(ArrayList<treeH> m) {
        treeH x = null;
        while (m.size() > 1) {
            treeH m1 = new treeH(m.get(0).value + m.get(1).value, m.get(0), m.get(1));
            m.remove(1);
            m.remove(0);
            int index = 0;
            for (int i = 0; i < m.size(); i++) {
                if (m.get(i).value > m1.value) {
                    index = i;
                    break;
                }
                if (m.get(m.size() - 1 - i).value < m1.value) {
                    index = m.size() - i;
                    break;
                }
            }
            m.add(index, m1);
            x = m1;
        }
        return x;
    }

    public static void getOP(treeH node, ArrayList<Integer> op) {
        if (node.left != null && node.right != null) {
            op.add(node.value);
            getOP(node.left, op);
            getOP(node.right, op);
        }
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

class treeH {
    int value;
    treeH left;
    treeH right;

    public treeH(int value, treeH left, treeH right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}