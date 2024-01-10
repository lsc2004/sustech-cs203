import java.io.*;
import java.util.StringTokenizer;

public class H18 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int x = a1.nextInt();
        int y = a1.nextInt();
        Node1 head = new Node1(1000000001, 1000000001, null);
        Node1 o = head;
        for (int i = 0; i < x; i++) {
            int x1 = a1.nextInt();
            int x2 = a1.nextInt();
            o.next = new Node1(x1, x2, null);
            o = o.next;
        }
        o = head;
        findLast(head).next = new Node1(-1000000001, -1000000001, null);
        for (int i = 0; i < y; i++) {
            int y1 = a1.nextInt();
            int y2 = a1.nextInt();
            Node1 t = new Node1(y1, y2, null);
            while (t.value2 <= o.next.value2) {
                o = o.next;
            }
            if (t.value2 == o.value2) {
                o.value1 = o.value1 + t.value1;
            } else {
                t.next = o.next;
                o.next = t;
            }
        }
        o = head.next;
        int c = 0;
        while (o != null) {
            if (o.value1 != 0) {
                c++;
            }
            o = o.next;
        }
        out.println(c - 1);
        o = head.next;
        while (o.next != null) {
            if (o.value1 != 0) {
                out.println(o.value1 + " " + o.value2);
            }
            o = o.next;
        }
        out.close();
    }

    public static Node1 findLast(Node1 head) {
        if (head == null) {
            return null;
        }
        Node1 p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

}

class Node1 {
    int value1;
    int value2;
    Node1 next;

    public Node1(int value1, int value2, Node1 next) {
        this.value1 = value1;
        this.value2 = value2;
        this.next = next;
    }
}