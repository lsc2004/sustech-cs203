import java.io.*;
import java.util.StringTokenizer;
public class H46 {
    public static void main(String[] args) {
        QReader scanner = new QReader();
        QWriter out = new QWriter();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[m];
        AVL<Integer> T = new AVL<>();
        for (int i = 0; i < m; ++i) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < k; ++i) {
            T.add(arr[i]);
        }
        int kth;
        for (int i = 0; i < m - k + 1; ++i) {
            kth = scanner.nextInt();
            out.println(T.findKth(kth));
            T.remove(arr[i]);
            if (i == m - k) {
                break;
            }
            T.add(arr[k + i]);
        }
        out.close();
    }
}

class AVL<T extends Comparable<T>> {
    public class Node {
        T key;
        Node left;
        Node right;
        int height;
        int subSize;
        int count;

        Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 1;
            this.subSize = 1;
            this.count = 1;
        }
        int getSubSize(Node node) {
            if (node == null) {
                return 0;
            }
            return node.subSize;
        }

        void maintainSubSize() {
            this.subSize = getSubSize(this.left) + getSubSize(this.right) + this.count;
        }
    }

    private Node root;
    private int size;

    AVL() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void add(T key) {
        root = add(root, key);
    }

    public void remove(T key) {
        root = remove(root, key);
    }

    public int getHeight(Node node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    public int getSubSize(Node node) {
        if (node != null) {
            return node.subSize;
        }
        return 0;
    }

    public T findKth(int k) {
        if (k < 1 || k > root.subSize) {
            return null;
        }
        Node node = findKth(root, k);
        if (node != null) {
            return node.key;
        } else {
            return null;
        }
    }

    private Node rightRotate(Node b) {
        Node a = b.left;
        Node temp = a.right;
        a.right = b;
        b.left = temp;
        b.height = Math.max(getHeight(b.left), getHeight(b.right)) + 1;
        a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
        b.maintainSubSize();
        a.maintainSubSize();
        return a;
    }

    private Node leftRotate(Node b) {
        Node a = b.right;
        Node temp = a.left;
        a.left = b;
        b.right = temp;
        b.height = Math.max(getHeight(b.left), getHeight(b.right)) + 1;
        a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
        b.maintainSubSize();
        a.maintainSubSize();
        return a;
    }

    private Node add(Node node, T key) {
        if (node == null) {
            size++;
            return new Node(key);
        }
        if (node.key.compareTo(key) == 0) {
            node.count++;
            node.maintainSubSize();
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key);
        } else {
            node.right = add(node.right, key);
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        node.maintainSubSize();
        int result4;
        result4 = getHeight(node.left) - getHeight(node.right);
        if (result4 > 1) {
            int result = 0;
            if (node.left != null) {
                result = getHeight(node.left.left) - getHeight(node.left.right);
            }
            if (result >= 0) {
                return rightRotate(node);
            }
        }
        int result3;
        result3 = getHeight(node.left) - getHeight(node.right);
        if (result3 < -1) {
            int result = 0;
            if (node.right != null) {
                result = getHeight(node.right.left) - getHeight(node.right.right);
            }
            if (result <= 0) {
                return leftRotate(node);
            }
        }
        int result2;
        result2 = getHeight(node.left) - getHeight(node.right);
        if (result2 > 1) {
            int result = 0;
            if (node.left != null) {
                result = getHeight(node.left.left) - getHeight(node.left.right);
            }
            if (result < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        int result1;
        result1 = getHeight(node.left) - getHeight(node.right);
        if (result1 < -1) {
            int result = 0;
            if (node.right != null) {
                result = getHeight(node.right.left) - getHeight(node.right.right);
            }
            if (result > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
    private Node getMinNode(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return getMinNode(node.left);
        }
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        Node newRoot;
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            newRoot = node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            newRoot = node;
        } else {
            if (node.count > 1) {
                node.count--;
                node.maintainSubSize();
                return node;
            }
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                newRoot = right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                newRoot = left;
            } else {
                Node minNode = getMinNode(node.right);
                minNode.right = remove(node.right, minNode.key);
                minNode.left = node.left;
                node.left = null;
                node.right = null;
                newRoot = minNode;
            }
        }
        if (newRoot == null) {
            return null;
        }
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
        newRoot.maintainSubSize();
        int result4;
        result4 = getHeight(newRoot.left) - getHeight(newRoot.right);
        if (result4 > 1) {
            int result = 0;
            if (newRoot.left != null) {
                result = getHeight(newRoot.left.left) - getHeight(newRoot.left.right);
            }
            if (result >= 0) {
                return rightRotate(newRoot);
            }
        }
        int result3;
        result3 = getHeight(newRoot.left) - getHeight(newRoot.right);
        if (result3 < -1) {
            int result = 0;
            if (newRoot.right != null) {
                result = getHeight(newRoot.right.left) - getHeight(newRoot.right.right);
            }
            if (result <= 0) {
                return leftRotate(newRoot);
            }
        }
        int result2;
        result2 = getHeight(newRoot.left) - getHeight(newRoot.right);
        if (result2 > 1) {
            int result = 0;
            if (newRoot.left != null) {
                result = getHeight(newRoot.left.left) - getHeight(newRoot.left.right);
            }
            if (result < 0) {
                newRoot.left = leftRotate(newRoot.left);
                return rightRotate(newRoot);
            }
        }
        int result1;
        result1 = getHeight(newRoot.left) - getHeight(newRoot.right);
        if (result1 < -1) {
            int result = 0;
            if (newRoot.right != null) {
                result = getHeight(newRoot.right.left) - getHeight(newRoot.right.right);
            }
            if (result > 0) {
                newRoot.right = rightRotate(newRoot.right);
                return leftRotate(newRoot);
            }
        }
        return newRoot;
    }
    private Node findKth(Node node, int k) {
        if (node == null || k < 1 || k > node.subSize) {
            return null;
        }
        if (k < getSubSize(node.left) + 1) {
            return findKth(node.left, k);
        } else if (k > getSubSize(node.left) + node.count) {
            return findKth(node.right, k - getSubSize(node.left) - node.count);
        }
        return node;
    }

}
