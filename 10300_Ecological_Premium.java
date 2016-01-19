import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();

        for (int i = 0; i < testCases; i++) {
            int farmerCount = in.nextInt();
            int total = 0;
            for (int j = 0; j < farmerCount; j++) {
                int size = in.nextInt();
                int animals  = in.nextInt();
                int env  = in.nextInt();
                total += size*env;
            }
            System.out.println(total);
        }
    }
}