import java.io.*;
import java.util.StringTokenizer;

public class H20 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Node3 head = new Node3(-1, null, null);
            Node3 o = head;
            Node3 end = new Node3(-1, null, null);
            Node3[] index = new Node3[n];
            int counter = 0;
            while (counter < n) {
                int value = in.nextInt();
                o.next = new Node3(value, o, null);
                o = o.next;
                index[value] = o;
                counter++;
            }
            end.pre = o;
            o.next = end;
            counter = 0;
            while (counter < m) {
                Node3 x1 = index[in.nextInt()];
                Node3 y1 = index[in.nextInt()];
                Node3 x2 = index[in.nextInt()];
                Node3 y2 = index[in.nextInt()];
                Node3 p1 = x1.pre;
                Node3 p2 = x2.pre;
                Node3 n1 = y1.next;
                Node3 n2 = y2.next;
                p1.next = x2;
                x2.pre = p1;
                if (p2 == y1) {
                    y2.next = x1;
                    x1.pre = y2;
                } else {
                    p2.next = x1;
                    x1.pre = p2;
                    y2.next = n1;
                    n1.pre = y2;
                }
                y1.next = n2;
                n2.pre = y1;
                counter++;
            }
            counter = 0;
            Node3 op = head.next;
            while (counter < n) {
                out.print(op.value + " ");
                op = op.next;
                counter++;
            }
            out.println(" ");
        }

        out.close();
    }

}

class Node3 {
    int value;
    Node3 next;
    Node3 pre;

    public Node3(int value, Node3 pre, Node3 next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }
}