import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NodeOp {
    int p;
    int pa;
    int maxCh;
    int maxVal;
    boolean inner;
    List<Integer> children;
    public NodeOp() {
        children = new ArrayList<>();
    }
}
public class H41_1 {
    private static int[] isV;
    private static int n;
    private static int top;
    private static int first = 0;
    private static int second = 0;
    private static long ans = 0;
    private static NodeOp[] t;
    public static void main(String[] args) {
        int firstId = 0;
        Scanner scanner = new Scanner(System.in);
        setN(scanner.nextInt());
        setT(new NodeOp[getN() + 1]);
        setIsV(new int[getN() + 1]);

        for (int i = 0; i <= getN(); i++) {
            getT()[i] = new NodeOp();
        }
        int op = getN(), a, b;
        while (--op > 0) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            getT()[a].children.add(b);
            getT()[b].children.add(a);
        }
        for (int i = 1; i <= getN(); i++) {
            getT()[i].p = scanner.nextInt();

            if (getT()[i].p > getFirst()) {
                setFirst(getT()[i].p);
                firstId = i;
            }
        }
        setTop(firstId);
        buildDown(getTop());
        if (getT()[getTop()].children.size() == 1) {
            assignDown(getTop(), true);
            setAns(getAns() + getFirst());
        } else {
            getIsV()[getTop()]++;
            getT()[getT()[getTop()].maxCh].maxVal = getFirst();
            assignDown(getT()[getTop()].maxCh, true);
            for (int child : getT()[getTop()].children) {
                if (getIsV()[child] == 0) {
                    assignDown(child, false);
                }
            }
            setAns(getAns() + (getFirst() - getSecond()));
        }
        System.out.println(getAns());
    }
    public static void assignDown(int root, boolean isTopMaxCh) {
        getIsV()[root]++;
        int cur = root;
        while (getT()[cur].inner) {
            getIsV()[getT()[cur].maxCh]++;
            for (int child : getT()[cur].children) {
                if (getIsV()[child] == 0) {
                    assignDown(child, isTopMaxCh);
                }
            }
            cur = getT()[cur].maxCh;
        }
        if (!isTopMaxCh) {
            setSecond(Math.max(getSecond(), getT()[root].maxVal));
        }
        setAns(getAns() + getT()[root].maxVal);
    }
    public static void buildDown(int root) {
        getT()[root].maxVal = getT()[root].p;
        for (int child : getT()[root].children) {
            if (child != getT()[root].pa) {
                getT()[root].inner = true;
                getT()[child].pa = root;
                buildDown(child);
                if (getT()[child].maxVal > getT()[getT()[root].maxCh].maxVal) {
                    getT()[root].maxVal = getT()[child].maxVal;
                    getT()[root].maxCh = child;
                }
            }
        }
        getT()[root].maxVal = Math.max(getT()[root].maxVal, getT()[root].p);
    }
    public static int[] getIsV() {
        return isV;
    }
    public static void setIsV(int[] isV) {
        H41_1.isV = isV;
    }

    public static NodeOp[] getT() {
        return t;
    }
    public static void setT(NodeOp[] t) {
        H41_1.t = t;
    }
    public static long getAns() {
        return ans;
    }
    public static void setAns(long ans) {
        H41_1.ans = ans;
    }
    public static int getN() {
        return n;
    }
    public static void setN(int n) {
        H41_1.n = n;
    }
    public static int getTop() {
        return top;
    }
    public static void setTop(int top) {
        H41_1.top = top;
    }
    public static int getFirst() {
        return first;
    }
    public static void setFirst(int first) {
        H41_1.first = first;
    }
    public static int getSecond() {
        return second;
    }
    public static void setSecond(int second) {
        H41_1.second = second;
    }
}