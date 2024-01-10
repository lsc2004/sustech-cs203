import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class H55 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out=new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        ArrayList<Integer> beginList = new ArrayList<>();
        ArrayList<Integer> endList = new ArrayList<>();
        ArrayList<Integer> lengthList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int[] parent = new int[n * m + 1];
        for (int i = 0; i < n * m + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == n - 1) {
                    if (j != m - 1) {
                        init(i * m + j + 1, i * m + j + 2, matrix[i][j] * matrix[i][j + 1], beginList, endList, lengthList);
                    }
                } else if (j == m - 1) {
                    if (i != n - 1) {
                        init(i * m + j + 1, i * m + j + 1 + m, matrix[i][j] * matrix[i + 1][j], beginList, endList, lengthList);
                    }
                } else {
                    init(i * m + j + 1, i * m + j + 2, matrix[i][j] * matrix[i][j + 1], beginList, endList, lengthList);

                    init(i * m + j + 1, i * m + j + 1 + m, matrix[i][j] * matrix[i + 1][j], beginList, endList, lengthList);
                }
            }
        }
        List<Integer> sortedIndices = new ArrayList<>();
        for (var i = 0; i < lengthList.size(); ++i) {
            sortedIndices.add(i);
        }
        sortedIndices.sort(Comparator.comparingInt(o -> -lengthList.get(o)));
        long answer = 0;
        for (int index : sortedIndices) {
            int root2 = endList.get(index);
            root2 = getRoot(parent, endList.get(index), root2);
            int root3 = beginList.get(index);
            root3 = getRoot(parent, beginList.get(index), root3);
            if (root3 != root2) {
                answer += lengthList.get(index);
                int a2 = beginList.get(index);
                int root1 = a2;
                root1 = getRoot(parent, a2, root1);
                int root = endList.get(index);
                root = getRoot(parent, endList.get(index), root);
                parent[root1] = root;
            }
        }
        out.print(answer);
        out.close();
    }
    private static void init(int a, int b, int c, ArrayList<Integer> beginList, ArrayList<Integer> endList, ArrayList<Integer> lengthList) {
        beginList.add(a);
        endList.add(b);
        lengthList.add(c);
    }
    private static int getRoot(int[] parent, int a2, int root1) {
        while (root1 != parent[root1]) {
            root1 = parent[root1];
        }
        while (a2 != root1) {
            int pa1 = parent[a2];
            parent[a2] = root1;
            a2 = pa1;
        }
        return root1;
    }
}