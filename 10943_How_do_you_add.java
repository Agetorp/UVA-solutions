import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] memory = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] pair = in.readLine().trim().split(" ");
        int n = Integer.parseInt(pair[0]);
        int k = Integer.parseInt(pair[1]);

        for (int i = 0; i < 101; i++) {
            memory[i][1] = 1;
            memory[1][i] = i;
        }

        while (!(k == 0 && n == 0)){

            int res = calculate(n, k);
            System.out.println(res);
            pair = in.readLine().split(" ");
            n = Integer.parseInt(pair[0]);
            k = Integer.parseInt(pair[1]);
        }

    }

    private static int calculate(int n, int k) {
        if (memory[n][k] != 0)
            return memory[n][k];

        for (int i = 0; i <= n; ++i) {
            memory[n][k] =(memory[n][k] + calculate(i, k-1) ) % 1000000;
        }

        return memory[n][k];
    }

}




