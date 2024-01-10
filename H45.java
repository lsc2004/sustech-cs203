import java.util.Scanner;

public class H45 {

    static int[][] ls;
    static int[] h;
    static int sz;
    static int n;

    public static void main(String[] args)  {
        Scanner in=new Scanner(System.in);
        n = in.nextInt();
        ls = new int[n + 1][4];
        h = new int[500005];
        for (int i = 1; i <= n; ++i) {
            ls[i][0] = in.nextInt();
            ls[i][1] = i - 1;
            ls[i][2] = i + 1;
            h[++sz] = i;
            int p = sz;
            extracted(p);
        }
        ls[0][2] = 1;
        ls[n][2] = 0;
        while (--n > 0) {
            int p = pop();
            int lId = ls[p][1];
            int rId = ls[p][2];
            if (lId != 0 && rId != 0) {
                if ((ls[p][0] ^ ls[lId][0]) >= (ls[p][0] ^ ls[rId][0])) {
                    ls[p][0] = (ls[p][0] ^ ls[lId][0]) + 1;
                    h[++sz] = p;
                    int p1 = sz;
                    extracted(p1);
                    ls[p][1] = ls[lId][1];
                    if (ls[lId][1] != 0) {
                        ls[ls[lId][1]][2] = p;
                    }
                    ls[lId][3] = 1;
                } else {
                    ls[p][0] = (ls[p][0] ^ ls[rId][0]) + 1;
                    h[++sz] = p;
                    int p1 = sz;
                    extracted(p1);
                    ls[p][2] = ls[rId][2];
                    if (ls[rId][2] != 0) {
                        ls[ls[rId][2]][1] = p;
                    }
                    ls[rId][3] = 1;
                }
                continue;
            }
            if (lId != 0) {
                ls[p][0] = (ls[p][0] ^ ls[lId][0]) + 1;
                h[++sz] = p;
                int p1 = sz;
                extracted(p1);
                ls[p][1] = ls[lId][1];
                if (ls[lId][1] != 0) {
                    ls[ls[lId][1]][2] = p;
                }
                ls[lId][3] = 1;
            } else if (rId != 0) {
                ls[p][0] = (ls[p][0] ^ ls[rId][0]) + 1;
                h[++sz] = p;
                int p1 = sz;
                extracted(p1);
                ls[p][2] = ls[rId][2];
                if (ls[rId][2] != 0) {
                    ls[ls[rId][2]][1] = p;
                }
                ls[rId][3] = 1;
            }
        }
        System.out.println(ls[pop()][0]);
    }

    private static void extracted(int p1) {
        while (p1 > 1) {
            if (ls[h[p1 >> 1]][0] > ls[h[p1]][0] || (ls[h[p1 >> 1]][0] == ls[h[p1]][0] && h[p1 >> 1] > h[p1])) {
                h[p1 >> 1] = h[p1 >> 1] ^ h[p1];
                h[p1] = h[p1 >> 1] ^ h[p1];
                h[p1 >> 1] = h[p1 >> 1] ^ h[p1];
                p1 = p1 >> 1;
            } else {
                break;
            }
        }
    }

    public static int pop() {
        int topHasDel = ls[h[1]][3];
        int p = h[1];
        h[1] = h[sz--];
        int s = 2, t = 1;
        while (s <= sz) {
            if (s <= sz - 1 && (ls[h[s]][0] > ls[h[s + 1]][0] || (ls[h[s]][0] == ls[h[s + 1]][0] && h[s] > h[s + 1]))) {
                ++s;
            }
            if (ls[h[t]][0]> ls[h[s]][0] || (ls[h[t]][0] == ls[h[s]][0] && h[t] > h[s])) {
                h[t] = h[t] ^ h[s];
                h[s] = h[t] ^ h[s];
                h[t] = h[t] ^ h[s];
                t = s;
                s = s << 1;
            } else {
                break;
            }
        }
        return topHasDel != 0 ? pop() : p;
    }
}
