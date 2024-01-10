public class H7 {
    public static void main(String[] args) {
        QReader a1 = new QReader();
        QWriter out = new QWriter();
        int b1 = a1.nextInt();
        for (int i = 0; i < b1; i++) {
            long c1=a1.nextLong();
            out.println((c1*(c1+1)/2+c1*(c1+1)*(2*c1+1)/6)/2);
        }
        out.close();
    }
    /*public static int get(int a){
        if (a==1){
            return a*(a+1)/2;
        }
        return get(a-1)+a*(a+1)/2;
    }*/
}
