import java.io.*;
import java.util.StringTokenizer;

public class H19 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {
            int n = in.nextInt();
            char[] m = in.next().toCharArray();
            node2 head = new node2(-1, null, null);
            node2 end = new node2(-2, head, null);
            head.next = end;
            node2 now = head.next;
            compile(n, m, head, end, now);
            now = head.next;
            while (now != end) {
                out.print(now.num);
                now = now.next;
            }
            out.println(" ");
        }
        out.close();
    }

    private static void compile(int n, char[] m, node2 head, node2 end, node2 now) {
        int counter = 0;
        while (counter < n) {
            if (m[counter] == 'r') {
                counter++;
                if (counter >= n) {
                    break;
                }
                if (now != end) {
                    now.num = Integer.parseInt(String.valueOf(m[counter]));
                } else {
                    Insert(now, m[counter]);
                    now = now.prev;
                }
            } else if (m[counter] == 'L') {
                if (isNotnext(now)) {
                    now = now.next;
                }
            } else if (m[counter] == 'H') {
                if (now.prev != head) {
                    now = now.prev;
                }
            } else if (m[counter] == 'I') {
                now = head.next;
            } else if (m[counter] == 'x') {
                if (now != end) {
                    remove(now);
                    now = now.next;
                }
            } else {
                Insert(now, m[counter]);
            }
            counter++;
        }
    }

    public static void Insert(node2 now, char m) {
        node2 last = now.prev;
        node2 insert = new node2(Integer.parseInt(String.valueOf(m)), last, now);
        last.next = insert;
        now.prev = insert;
    }
    public static boolean isNotnext(node2 a) {
        return a.next != null;

    }
    public static boolean isNotpre(node2 a) {
        return a.prev!= null;
    }
    private static void remove(node2 a) {
        if (isNotpre(a)) {
            a.prev.next = a.next;
        }
        if (isNotnext(a)) {
            a.next.prev = a.prev;
        }
    }
}

class node2 {
    node2 next;
    node2 prev;
    int num;

    public node2(int num, node2 prev, node2 next) {
        this.num = num;
        this.prev = prev;
        this.next = next;
    }
}