import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.StringTokenizer;

public class H40 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        ArrayList<ArrayList<Integer>> length = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
            length.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            children.get(a).add(b);
            children.get(b).add(a);
            length.get(a).add(1);
            length.get(b).add(1);
        }
        int m = in.nextInt();
        int[] haveList = new int[n];
        for (int i = 0; i < m; i++) {
            haveList[in.nextInt() - 1]++;
        }
        int ans1 = 0;
        int[] isVisited = new int[n];
        isVisited[0]++;
        for (int j = 0; j < children.get(0).size(); j++) {
            int a = 0;
            ArrayList<Integer> index = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                index.add(0);
            }
            if (haveList[children.get(0).get(j)] != 0) {
                index.set(a++, 1);
            }
            Queue<Integer> queue = new LinkedList<>();
            int[] weight = new int[n];
            weight[0] = 0;
            isVisited[children.get(0).get(j)]++;
            queue.offer(children.get(0).get(j));
            weight[children.get(0).get(j)] = 1;
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                for (int i = 0; i < children.get(temp).size(); i++) {
                    if (isVisited[children.get(temp).get(i)] == 0) {
                        weight[children.get(temp).get(i)] = weight[temp] + length.get(temp).get(i);
                        queue.offer(children.get(temp).get(i));
                        if (haveList[children.get(temp).get(i)] != 0) {
                            index.set(a++, weight[children.get(temp).get(i)]);
                        }
                        isVisited[children.get(temp).get(i)]++;
                    }
                }
            }
            if (a > 0) {
                ans1 = Math.max(ans1, calculateMaxIndex(index, a));
            }
        }
        out.println(ans1);
        out.close();
    }

    public static int calculateMaxIndex(ArrayList<Integer> index, int length) {
        int maxIndex = index.get(0);
        for (int i = 1; i < length; i++) {
            if (index.get(i) <= maxIndex) {
                index.set(i, maxIndex + 1);
            }
            maxIndex = Math.max(maxIndex, index.get(i));
        }
        return maxIndex;
    }
}
