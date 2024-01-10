import java.io.*;
import java.util.StringTokenizer;

public class H29 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        linkDeque max = new linkDeque(n);
        linkDeque min = new linkDeque(n);
        int start = 0;
        int end = 0;
        int maxL = 0;
        while (compare(end, n) < 0) {
            while ((compare(max.size, 0) == 1 || compare(max.size, 0) == -1) && compare(max.getLast(), a[end]) < 0) {
                max.removeLast();
            }
            while ((compare(min.size, 0) == 1 || compare(min.size, 0) == -1) && compare(min.getLast(), a[end]) > 0) {
                min.removeLast();
            }
            max.addLast(a[end]);
            min.addLast(a[end]);
            while ((compare(max.size, 0) == 1 || compare(max.size, 0) == -1) && (compare(min.size, 0) == 1 || compare(min.size, 0) == -1) && compare(max.getFirst() - min.getFirst(), k) > 0) {
                if (a[start] == max.getFirst()) {
                    max.removeFirst();
                }
                if (a[start] == min.getFirst()) {
                    min.removeFirst();
                }
                start++;
            }
            maxL = Math.max(maxL, end - start + 1);
            end++;
        }
        out.println(maxL);
        out.close();
    }

    public static int compare(int a, int b) {
        return Integer.compare(a, b);
    }
}

class linkDeque {
    public void addLast(int e) {
        if (size == capacity) {
            return;
        }
        Node a = sentinel.prev;
        Node b = sentinel;
        Node added = new Node(a, e, b);
        a.next = added;
        b.prev = added;
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        }
        Node a = sentinel;
        Node removed = sentinel.next;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            return;
        }
        Node b = sentinel;
        Node removed = sentinel.prev;
        Node a = removed.prev;
        a.next = b;
        b.prev = a;
        size--;
    }

    public int getFirst() {
        if (size == 0) {
            return -1;
        }
        return sentinel.next.value;
    }

    public int getLast() {
        if (size == 0) {
            return -1;
        }
        return sentinel.prev.value;
    }

    static class Node {
        Node prev;
        Node next;
        int value;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Node sentinel = new Node(null, 0, null);

    public linkDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
}
