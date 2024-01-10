import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H34 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String a = in.nextLine();
        String b = in.nextLine();
        int[] a1 = new int[a.length()];
        int[] a2 = new int[b.length()];
        int k = Math.min(a.length(), b.length());
        compile(a, b, a1, a2);
        int seed = 100007;
        long[] pow = new long[100000];
        for (int i = 0; i < 100000; i++) {
            if (i == 0) {
                pow[i] = 1;
            } else {
                pow[i] = pow[i - 1] * seed;
            }
        }
        int min = 0;
        int max = k;
        while (min <= max) {
            int mid = (min + max) >>> 1;
            if (mid == 0) {
                break;
            }
            if (getK(a1, a2, pow, mid, seed)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        if (min == 0) {
            out.println(0);
        } else {
            if (!getK(a1, a2, pow, min - 1, seed)) {
                out.println(0);
            } else {
                out.println(min - 1);
            }
        }
        out.close();
    }

    public static void compile(String a, String b, int[] a1, int[] a2) {
        for (int i = 0; i < a.length(); i++) {
            a1[i] = a.charAt(i);
        }
        for (int i = 0; i < b.length(); i++) {
            a2[i] = b.charAt(i);
        }
    }

    public static boolean getK(int[] a1, int[] a2, long[] pow, int k, int seed) {
        int op = 0;
        long[] hashList1 = new long[a1.length - k + 1];
        long[] hashList2 = new long[a2.length - k + 1];
        toGetHash(a1, pow, k, seed, hashList1);
        toGetHash(a2, pow, k, seed, hashList2);
        Arrays.sort(hashList1);
        int left = 0;
        int right = hashList1.length - 1;
        for (long l : hashList2) {
            op += checkIt(left, right, l, hashList1);
        }
        return op != 0;
    }

    private static int checkIt(int left, int right, long target, long[] a1) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target < a1[mid]) {
                right = mid - 1;
            } else if (target > a1[mid]) {
                left = mid + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private static void toGetHash(int[] a1, long[] pow, int k, int seed, long[] hashList1) {
        for (int i = 0; i < hashList1.length; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    hashList1[i] += a1[j] * pow[k - 1 - j];
                }
            } else {
                hashList1[i] = (hashList1[i - 1] - a1[i - 1] * pow[k - 1]) * seed + a1[i + k - 1];
            }
        }
    }
}
