import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
//未知的错误，能过样例，但wa
public class H41 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int max = 0;
        int maxIndex = 0;
        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = in.nextInt();
            if (p[i] > max) {
                max = p[i];
                maxIndex = i;
            }
        }
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        int[] visited = new int[n + 1];
        visited[maxIndex]++;
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(maxIndex);
        while (!queue1.isEmpty()) {
            int tmp = queue1.poll();
            for (int child1 : graph.get(tmp)) {
                if (visited[child1] == 0) {
                    queue1.add(child1);
                    visited[child1]++;
                    tree.get(tmp).add(child1);
                }
            }
        }
        findSubMax(tree, maxIndex, p);
        ArrayList<Integer> counter=new ArrayList<>();
        int max1 = 0, max2 = 0;
        for (int child : tree.get(maxIndex)) {
            if (p[child] >= max) {
                counter.add(child);
            }
            if (p[child] > max1) {
                max2 = max1;
                max1 = p[child];
            } else if (p[child] > max2) {
                max2 = p[child];
            }
        }
        long ans = 0;
        for (int child : tree.get(maxIndex)) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(child);
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                if (tree.get(tmp).isEmpty()) {
                    ans += p[tmp];
                } else if (p[tmp] >= p[maxIndex]) {
                    int op = -1;
                    int index = -1;
                    for (int j = 0; j < tree.get(tmp).size(); j++) {
                        queue.add(tree.get(tmp).get(j));
                        if (p[tree.get(tmp).get(j)] > op) {
                            op = p[tree.get(tmp).get(j)];
                            index = j;
                        }
                    }
                    p[tree.get(tmp).get(index)] = p[tmp];
                } else {
                    queue.addAll(tree.get(tmp));
                }
            }
        }
        if (tree.get(maxIndex).size() == 1) {
            if (counter.isEmpty()) {
                System.out.println(ans + max - max1);
            } else {
                System.out.println(ans + max);
            }
        } else if (counter.size()==0||counter.size()==1) {
            System.out.println(ans + 2L * max - max1 - max2);
        }
    }

    public static void findSubMax(ArrayList<ArrayList<Integer>> tree, int node, int[] p) {
        if (!tree.isEmpty()) {
            int max = p[node];
            for (int child : tree.get(node)) {
                findSubMax(tree, child, p);
                max = Math.max(max, p[child]);
            }
            p[node] = max;
        }
    }
}
