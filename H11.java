import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class H11 {
    public static void main(String[] args) {
        QReader scanner = new QReader();
        QWriter out = new QWriter();
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int s = scanner.nextInt();
        String t = scanner.next();
        ArrayList<String> m = new ArrayList<>();
        long periodX = 0;
        long periodY = 0;
        for (int i = 0; i <= t.length() - 1; i++) {
            m.add(t.substring(i, i + 1));
        }
        for (String g : m) {
            switch (g) {
                case "U" -> periodY += 1;
                case "D" -> periodY -= 1;
                case "L" -> periodX -= 1;
                case "R" -> periodX += 1;
            }
        }
        if (s == getDistance(x1, y1, x2 + periodX, y2 + periodY) - getDistance(x1, y1, x2, y2)) {
            System.out.println(-1);
        } else {
            long min = 1;
            long max = 1000000000000000000L;
            while (min < max) {
                long mid = min + ((max - min) >>> 1);
                if (getRemainder(mid, s, x2, y2, periodX, periodY, m, x1, y1) > 0) {
                    min = mid + 1;
                } else {
                    max = mid;
                }
            }
            if (getRemainder(max, s, x2, y2, periodX, periodY, m, x1, y1) > 0) {
                System.out.println(-1);
            } else {
                out.println(max);
            }
            out.close();
        }
    }

    private static long getRemainder(long mid, int s, int x2, int y2, long periodX, long periodY, ArrayList<String> m, int x1, int y1) {
        long Cyc = mid / s;
        long remain = mid % s;
        long X = x2 + periodX * Cyc;
        long Y = y2 + periodY * Cyc;
        for (int i = 0; i < remain; i++) {
            String t = m.get(i);
            switch (t) {
                case "U" -> Y += 1;
                case "D" -> Y -= 1;
                case "L" -> X -= 1;
                case "R" -> X += 1;
            }
        }
        return getDistance(x1, y1, X, Y) - mid;
    }

    private static long getDistance(int x1, int y1, long x2, long y2) {
        long x = x2 - x1;
        long y = y2 - y1;
        if (y < 0) {
            if (x >= 0) {
                return x - y;
            } else {
                return -x - y;
            }
        } else if (x < 0) {
            return y - x;
        }
        return x + y;
    }
}
