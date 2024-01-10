public class H5 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            String c1 = a1.next();
            int[] counter = new int[34];
            compile(c1, counter);
            int nb1 = 0;
            int[] copy1 = new int[counter.length];
            System.arraycopy(counter, 0, copy1, 0, counter.length);
            for (int j = 0; j < counter.length; j++) {
                if (counter[j] >= 2) {
                    counter[j] = counter[j] - 2;
                    int[] copy2 = new int[counter.length];
                    System.arraycopy(counter, 0, copy2, 0, counter.length);
                    for (int t = 0; t < 16; t++) {
                        int sb = 0;
                        if (t == 0) {
                            gets(counter);
                            gets(counter);
                            gets(counter);
                            gets(counter);
                        }
                        if (t == 1) {
                            getk(counter);
                            gets(counter);
                            gets(counter);
                            gets(counter);
                        }
                        if (t == 2) {
                            gets(counter);
                            getk(counter);
                            gets(counter);
                            gets(counter);
                        }
                        if (t == 3) {
                            gets(counter);
                            gets(counter);
                            getk(counter);
                            gets(counter);
                        }
                        if (t == 4) {
                            gets(counter);
                            gets(counter);
                            gets(counter);
                            getk(counter);
                        }
                        if (t == 5) {
                            getk(counter);
                            getk(counter);
                            gets(counter);
                            gets(counter);
                        }
                        if (t == 6) {
                            getk(counter);
                            gets(counter);
                            getk(counter);
                            gets(counter);
                        }
                        if (t == 7) {
                            getk(counter);
                            gets(counter);
                            gets(counter);
                            getk(counter);
                        }
                        if (t == 8) {
                            gets(counter);
                            getk(counter);
                            getk(counter);
                            gets(counter);
                        }
                        if (t == 9) {
                            gets(counter);
                            getk(counter);
                            gets(counter);
                            getk(counter);
                        }
                        if (t == 10) {
                            gets(counter);
                            gets(counter);
                            getk(counter);
                            getk(counter);
                        }
                        if (t == 11) {
                            getk(counter);
                            getk(counter);
                            getk(counter);
                            getk(counter);
                        }
                        if (t == 12) {
                            gets(counter);
                            getk(counter);
                            getk(counter);
                            getk(counter);
                        }
                        if (t == 13) {
                            getk(counter);
                            gets(counter);
                            getk(counter);
                            getk(counter);
                        }
                        if (t == 14) {
                            getk(counter);
                            getk(counter);
                            gets(counter);
                            getk(counter);
                        }
                        if (t == 15) {
                            getk(counter);
                            getk(counter);
                            getk(counter);
                            gets(counter);
                        }
                        nb1 = getNb1(counter, nb1, copy2, sb);
                        if (nb1==1){
                            break;
                        }
                    }
                    if (nb1 == 0) {
                        System.arraycopy(copy1, 0, counter, 0, copy1.length);
                    }
                }
            }
            if (nb1 != 0) {
                out.println("Blessing of Heaven");
            } else {
                out.println("Bad luck");
            }
        }
        out.close();
    }

    private static void compile(String c1, int[] counter) {
        for (int j = 0; j <= c1.length() - 2; j = j + 2) {
            String d1 = c1.substring(j, j + 2);
            if (d1.equals("1w")) {
                counter[0]++;
            }
            if (d1.equals("2w")) {
                counter[1]++;
            }
            if (d1.equals("3w")) {
                counter[2]++;
            }
            if (d1.equals("4w")) {
                counter[3]++;
            }
            if (d1.equals("5w")) {
                counter[4]++;
            }
            if (d1.equals("6w")) {
                counter[5]++;
            }
            if (d1.equals("7w")) {
                counter[6]++;
            }
            if (d1.equals("8w")) {
                counter[7]++;
            }
            if (d1.equals("9w")) {
                counter[8]++;
            }
            if (d1.equals("1b")) {
                counter[9]++;
            }
            if (d1.equals("2b")) {
                counter[10]++;
            }
            if (d1.equals("3b")) {
                counter[11]++;
            }
            if (d1.equals("4b")) {
                counter[12]++;
            }
            if (d1.equals("5b")) {
                counter[13]++;
            }
            if (d1.equals("6b")) {
                counter[14]++;
            }
            if (d1.equals("7b")) {
                counter[15]++;
            }
            if (d1.equals("8b")) {
                counter[16]++;
            }
            if (d1.equals("9b")) {
                counter[17]++;
            }
            if (d1.equals("1s")) {
                counter[18]++;
            }
            if (d1.equals("2s")) {
                counter[19]++;
            }
            if (d1.equals("3s")) {
                counter[20]++;
            }
            if (d1.equals("4s")) {
                counter[21]++;
            }
            if (d1.equals("5s")) {
                counter[22]++;
            }
            if (d1.equals("6s")) {
                counter[23]++;
            }
            if (d1.equals("7s")) {
                counter[24]++;
            }
            if (d1.equals("8s")) {
                counter[25]++;
            }
            if (d1.equals("9s")) {
                counter[26]++;
            }
            if (d1.equals("1z")) {
                counter[27]++;
            }
            if (d1.equals("2z")) {
                counter[28]++;
            }
            if (d1.equals("3z")) {
                counter[29]++;
            }
            if (d1.equals("4z")) {
                counter[30]++;
            }
            if (d1.equals("5z")) {
                counter[31]++;
            }
            if (d1.equals("6z")) {
                counter[32]++;
            }
            if (d1.equals("7z")) {
                counter[33]++;
            }
        }
    }

    private static int getNb1(int[] counter, int nb1, int[] copy2, int sb) {
        for (int value : counter) {
            if (value != 0) {
                sb++;
            }
        }
        if (sb == 0) {
            nb1++;
        } else {
            for (int k = 0; k < counter.length; k++) {
                System.arraycopy(copy2, 0, counter, 0, copy2.length);
            }
        }
        return nb1;
    }

    private static void getk(int[] counter) {
        for (int k = 0; k < counter.length; k++) {
            if (counter[k] >= 3) {
                counter[k] = counter[k] - 3;
                break;
            }
        }
    }

    private static void gets(int[] counter) {
        for (int k = 0; k <= 24; k++) {
            if ((k <= 6 || (k >= 9 && k <= 15) || k >= 18) && counter[k] != 0 && counter[k + 1] != 0 && counter[k + 2] != 0) {
                counter[k] = counter[k] - 1;
                counter[k + 1] = counter[k + 1] - 1;
                counter[k + 2] = counter[k + 2] - 1;
                break;
            }
        }
    }
}
