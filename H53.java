import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class H53 {
    static int size = 0;
    static Vertex9A[] node;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node = new Vertex9A[n];
        Vertex9A[] as = new Vertex9A[n + 1];
        for (int i = 0; i < n + 1; i++) {
            as[i] = new Vertex9A();
            as[i].children = new ArrayList<>();
            as[i].distance = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int weight = in.nextInt();
            as[x].children.add(new edge9A(y, weight));
        }
        node[++size] = as[1];
        as[1].index = size;
        as[1].distance = 0;
        Vertex9A tmp;
        Vertex9A tmpChild;
        while (size > 0) {
            tmp = node[1];
            tmp.isVisited++;
            node[1] = node[size];
            node[1].index = 1;
            size--;
            int cur1 = 1;
            while (cur1 * 2 <= size) {
                int min = cur1 * 2;
                if (cur1 * 2 + 1 <= size && node[cur1 * 2 + 1].distance < node[min].distance) {
                    min = cur1 * 2 + 1;
                }
                if (node[min].distance < node[cur1].distance) {
                    Vertex9A tmp2 = node[cur1];
                    cur1 = getCur(cur1, min, tmp2);
                } else {
                    break;
                }
            }
            for (int i = 0; i < tmp.children.size(); i++) {
                tmpChild = as[tmp.children.get(i).childIndex];
                if (tmpChild.isVisited == 0) {
                    long distance = tmp.distance + (long) tmp.children.get(i).weight;
                    if (tmpChild.index != 0) {
                        if (distance < tmpChild.distance) {
                            node[tmpChild.index].distance = distance;
                            int cur = tmpChild.index;
                            while (cur > 1 && node[cur / 2].distance > node[cur].distance) {
                                Vertex9A tmp1 = node[cur];
                                cur = getCur(cur, cur / 2, tmp1);
                            }
                        }
                    } else {
                        tmpChild.distance = distance;
                        node[++size] = tmpChild;
                        tmpChild.index = size;
                        int cur = size;
                        while (cur > 1 && node[cur / 2].distance > node[cur].distance) {
                            Vertex9A tmp1 = node[cur];
                            cur = getCur(cur, cur / 2, tmp1);
                        }
                    }
                }
            }
        }
        out.println(as[n].distance == Long.MAX_VALUE ? -1 : as[n].distance);
        out.close();
    }
    private static int getCur(int cur, int cur1, Vertex9A tmp1) {
        node[cur] = node[cur1];
        node[cur1] = tmp1;
        node[cur1].index = cur1;
        node[cur].index = cur;
        cur = cur1;
        return cur;
    }
}
class Vertex9A {
    int isVisited = 0;
    long distance;
    int index;
    ArrayList<edge9A> children = new ArrayList<>();

}
class edge9A {
    int childIndex;
    int weight;
    public edge9A(int childIndex, int weight) {
        this.childIndex = childIndex;
        this.weight = weight;
    }
}