import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        BigInteger b, p, m;
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            b = in.nextBigInteger();
            p = in.nextBigInteger();
            m = in.nextBigInteger();
            System.out.println(b.modPow(p, m).toString());
        }
    }
}