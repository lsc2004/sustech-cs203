import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H56 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt(), c = in.nextInt();
        Node[] nodes = new Node[n + 1];
        Node[][] queue = new Node[k + 1][n + 1];
        int[][] cost=new int[n+1][k+1];
        int[][]isVisited=new int[n+1][k+1];
        int[] frontIndex = new int[k + 1], rearIndex = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            int nodeColor = in.nextInt();
            nodes[i] = new Node(nodeColor, i);
            queue[nodeColor][rearIndex[nodeColor]++] = nodes[i];
            isVisited[i][nodeColor] ++;
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        for (int color = 1; color < k + 1; color++) {
            Node[] currentNodeQueue = queue[color];
            int currentFrontIndex = frontIndex[color], currentRearIndex = rearIndex[color];
            while (currentRearIndex != currentFrontIndex) {
                Node tmpNode = currentNodeQueue[currentFrontIndex++];
                for (Node childNode: tmpNode.children) {
                    if (isVisited[childNode.key][color]==0) {
                        cost[childNode.key][color] = cost[tmpNode.key][color] + 1;
                        currentNodeQueue[currentRearIndex++] = childNode;
                        isVisited[childNode.key][color] ++;
                    }
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            int sumOfCosts = 0;
            Arrays.sort(cost[i]);
            for (int j = 1; j <= c; j++)
                sumOfCosts += cost[i][j];
            out.print((i == n) ? sumOfCosts : sumOfCosts + " ");
        }

        out.close();
    }
}

class Node {
    int color;
    int key;
    ArrayList<Node> children = new ArrayList<>();
    public Node(int color,int key) {
        this.color = color;
        this.key=key;
    }
}