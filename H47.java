import java.io.*;
import java.util.StringTokenizer;
public class H47 {
    public static long findQuery(AVLTree tree, long q, boolean isPre) {
        long p = Integer.MIN_VALUE;
        AVLTree u = tree;
        while (u != null) {
            if (u.values[0] == q) {
                p = q;
                return p;
            }
            if (isPre ? (u.values[0] > q) : (u.values[0] < q)) {
                u = isPre ? u.connected[0] : u.connected[1];
            } else {
                p = u.values[0];
                u = isPre ? u.connected[1] : u.connected[0];
            }
        }
        return p;
    }
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        AVLTree pet = new AVLTree(Integer.MIN_VALUE);
        AVLTree adopter = new AVLTree(Integer.MIN_VALUE);
        long answer = 0;
        for (int i = 0; i < n; i++) {
            int cases = in.nextInt();
            int value = in.nextInt();
            if (cases == 0) {
                answer = getAnswer(pet, adopter, answer, value);
            } else {
                answer = getAnswer(adopter, pet, answer, value);
            }

        }
        out.println(answer);
        out.close();
    }
    private static long getAnswer(AVLTree pet, AVLTree adopter, long answer, int value) {
        if (adopter.values[1] == 0) {
            AVLTree.add(pet, value);
        } else {
            long predecessor = findQuery(adopter, value, true);
            long successor = findQuery(adopter, value, false);
            if (Math.abs(predecessor - value) <= Math.abs(successor - value)) {
                answer += Math.abs(predecessor - value);
                AVLTree.remove(adopter, (int) predecessor);
            } else {
                answer += Math.abs(successor - value);
                AVLTree.remove(adopter, (int) successor);
            }
        }
        return answer;
    }
}
class AVLTree {
    public static int delete = 0;
    int[] values;
    AVLTree[] connected;
    AVLTree(int key) {
        connected = new AVLTree[3];
        values = new int[4];
        values[0] = key;
    }
    public static int add(AVLTree tree, int value) {
        tree.values[1]++;
        if (tree.values[0] >= value) {
            if (tree.connected[0] == null) {
                AVLTree newTree = new AVLTree(value);
                tree.connected[0] = newTree;
                newTree.connected[2] = tree;
                tree.values[2]++;
                return 1;
            } else {
                return getBalance(tree, 2, add(tree.connected[0], value) + 1);
            }
        } else {
            if (tree.connected[1] == null) {
                AVLTree newTree = new AVLTree(value);
                newTree.connected[2] = tree;
                tree.connected[1] = newTree;
                tree.values[3]++;
                return 1;
            } else {
                return getBalance(tree, 3, add(tree.connected[1], value) + 1);
            }
        }
    }
    private static int getBalance(AVLTree tree, int g, int t) {
        tree.values[g] = t;
        if (Math.abs(tree.values[2] - tree.values[3]) >= 2) {
            if (tree.values[2] > tree.values[3]) {
                int h = tree.values[3];
                if (tree.connected[0].values[2] >= tree.connected[0].values[3]) {
                    AVLTree[] m = build1(0, 1, tree);
                    newTemp(tree, m[3]);
                    tree.connected[0] = m[0];
                    m[0].connected[2] = tree;
                    tree.values[2] = h + 1;
                    tree.connected[1] = m[3];
                    m[3].connected[2] = tree;
                    m[3].values[1] = 0;
                    rotate(m[1], m[3], 0, 2);
                    rotate(m[2], m[3], 1, 3);
                    tree.values[3] = Math.max(tree.connected[1].values[3], tree.connected[1].values[2]) + 1;
                } else {
                    AVLTree[] m = build2(0, 1, tree);
                    int temp = tree.values[0];
                    tree.values[0] = m[4].values[0];
                    m[4].values[0] = temp;
                    m[5].values[1] = 0;
                    tree.connected[1] = m[4];
                    m[4].connected[2] = tree;
                    otherRotate(m[0], m[5], 2);
                    rotate(m[1], m[5], 1, 3);
                    m[4].values[1] = 0;
                    rotate(m[2], m[4], 0, 2);
                    rotate(m[3], m[4], 1, 3);
                    tree.values[2] = tree.values[3] = h + 1;
                }
            } else {
                int h = tree.values[2];
                if (tree.connected[1].values[3] >= tree.connected[1].values[2]) {
                    AVLTree[] m = build1(1, 0, tree);
                    newTemp(tree, m[3]);
                    tree.connected[1] = m[0];
                    m[0].connected[2] = tree;
                    tree.values[3] = h + 1;
                    tree.connected[0] = m[3];
                    m[3].connected[2] = tree;
                    m[3].values[1] = 0;
                    rotate(m[1], m[3], 1, 3);
                    rotate(m[2], m[3], 0, 2);
                    tree.values[2] = Math.max(tree.connected[0].values[3], tree.connected[0].values[2]) + 1;
                } else {
                    AVLTree[] m = build2(1, 0, tree);
                    int temp = tree.values[0];
                    tree.values[0] = m[4].values[0];
                    m[4].values[0] = temp;
                    m[5].values[1] = 0;
                    m[4].values[1] = 0;
                    tree.connected[0] = m[4];
                    m[4].connected[2] = tree;
                    otherRotate(m[0], m[5], 3);
                    rotate(m[1], m[5], 0, 2);
                    rotate(m[2], m[4], 1, 3);
                    rotate(m[3], m[4], 0, 2);
                    tree.values[2] = tree.values[3] = h + 1;
                }
            }
        }
        return Math.max(tree.values[2], tree.values[3]);
    }
    private static void otherRotate(AVLTree tree1, AVLTree b, int x) {
        if (tree1 == null) {
            b.values[x] = 0;
        } else {
            b.values[x] = Math.max(tree1.values[2], tree1.values[3]) + 1;
            b.values[1] += tree1.values[1] + 1;
            tree1.connected[2] = b;
        }
    }
    private static void rotate(AVLTree tree2, AVLTree tree4, int x, int x1) {
        if (tree2 == null) {
            tree4.connected[x] = null;
            tree4.values[x1] = 0;
        } else {
            tree4.connected[x] = tree2;
            tree4.values[x1] = Math.max(tree2.values[2], tree2.values[3]) + 1;
            tree4.values[1] += tree2.values[1] + 1;
            tree2.connected[2] = tree4;
        }
    }
    private static void newTemp(AVLTree tree, AVLTree tree4) {
        int temp = tree4.values[0];
        tree4.values[0] = tree.values[0];
        tree.values[0] = temp;
    }
    public static int remove(AVLTree tree, int value) {
        if (tree.values[0] == value) {
            if (tree.connected[0] == null && tree.connected[1] == null) {
                compile(tree, tree.connected[2], null);
                return -1;
            } else if (tree.connected[1] == null) {
                if (tree.connected[2] == null) {
                    tree.values[0] = tree.connected[0].values[0];
                    tree.values[1]--;
                    tree.values[2]--;
                    tree.connected[0].connected[2] = null;
                    tree.connected[0] = null;
                    return 0;
                }
                tree.connected[0].connected[2] = tree.connected[2];
                compile(tree, tree.connected[2], tree.connected[0]);
                return Math.max(tree.connected[0].values[2], tree.connected[0].values[3]);
            } else {
                tree.values[3] = find(tree.connected[1]) + 1;
                tree.values[1]--;
                return getBalance(tree, 0, delete);
            }
        } else if (tree.values[0] > value) {
            tree.values[1]--;
            return getBalance(tree, 2, remove(tree.connected[0], value) + 1);
        } else {
            tree.values[1]--;
            return getBalance(tree, 3, remove(tree.connected[1], value) + 1);
        }

    }
    public static int find(AVLTree tree) {
        if (tree.connected[0] != null) {
            tree.values[1]--;
            return getBalance(tree, 2, find(tree.connected[0]) + 1);
        } else {
            delete = tree.values[0];
            if (tree.connected[1] == null) {
                compile(tree, tree.connected[2], null);
                return -1;
            } else {
                AVLTree sub = tree.connected[1];
                sub.connected[2] = tree.connected[2];
                compile(tree, tree.connected[2], sub);
                return Math.max(sub.values[2], sub.values[3]);
            }
        }
    }
    private static void compile(AVLTree tree, AVLTree temp, AVLTree sub) {
        if (temp.connected[0] == tree) {
            temp.connected[0] = sub;
        } else {
            temp.connected[1] = sub;
        }
        tree.connected[2] = null;
    }
    public static AVLTree[] build1(int i, int j, AVLTree tree) {
        AVLTree[] m = new AVLTree[4];
        m[0] = tree.connected[i].connected[i];
        m[1] = tree.connected[i].connected[j];
        m[2] = tree.connected[j];
        m[3] = tree.connected[i];
        return m;
    }
    public static AVLTree[] build2(int i, int j, AVLTree tree) {
        AVLTree[] m = new AVLTree[6];
        m[0] = tree.connected[i].connected[i];
        m[1] = tree.connected[i].connected[j].connected[i];
        m[2] = tree.connected[i].connected[j].connected[j];
        m[3] = tree.connected[j];
        m[4] = tree.connected[i].connected[j];
        m[5] = tree.connected[i];
        return m;
    }
}