import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;
import java.util.StringTokenizer;
//卡常数，节点类和路径类的额外操作会tle
public class H49 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int target = in.nextInt();
            List<Vertex0> op = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                op.add(new Vertex0(j));
                op.get(j).edges = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                int a1 = in.nextInt();
                int a2 = in.nextInt();
                op.get(a1).edges.add(new edge0(op.get(a2), 1));
                op.get(a2).edges.add(new edge0(op.get(a1), 1));
            }
            BFS(op.get(target));
            for (Vertex0 vertex : op) {
                if (vertex.key != 0) {
                    if (vertex.dist == 114514) {
                        out.print(-1 + " ");
                    } else {
                        out.print(vertex.dist + " ");
                    }
                }
            }
            out.println(" ");
        }
        out.close();
    }

    private static void BFS(Vertex0 target) {
        Queue<Vertex0> vertexArrayList = new LinkedList<>();
        vertexArrayList.offer(target);
        target.dist = 0;
        target.visited++;
        while (!vertexArrayList.isEmpty()) {
            Vertex0 curr = vertexArrayList.poll();
            for (edge0 m : curr.edges) {
                System.out.println("sb");
                Vertex0 n = m.linked;
                if (n.visited == 0) {
                    n.dist = curr.dist + m.weight;
                    n.visited++;
                    vertexArrayList.offer(n);
                }
            }
        }
    }
}

class Vertex0 {
    List<edge0> edges;
    int key;
    long dist = Integer.MAX_VALUE;
    int visited = 0;

    public Vertex0(int key) {
        this.key = key;
    }
}

class edge0 {
    Vertex0 linked;
    int weight;

    public edge0(Vertex0 linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}