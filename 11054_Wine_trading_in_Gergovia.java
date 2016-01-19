import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.run(args);
    }

    private void run(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int nodes = Integer.parseInt(in.readLine().trim());
        while(nodes > 0){

            long cost = 0;

            long balance = 0;
            String[] instream = (in.readLine()).split(" ");

            for (int i = 0; i < nodes; i++) {
                int trade = Integer.parseInt(instream[i]);
                balance = balance + trade;
                if(balance < 0){
                    cost = cost - balance;
                } else {
                    cost = cost + balance;
                }

            }

            System.out.println(cost);
            nodes = Integer.parseInt(in.readLine().trim());
        }


    }
}