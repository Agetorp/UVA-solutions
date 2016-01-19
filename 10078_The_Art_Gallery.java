import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int coords = in.nextInt();
        while(coords > 0) {

            int firstpx = in.nextInt();
            int firstpy = in.nextInt();
            int firstqx = in.nextInt();
            int firstqy = in.nextInt();
            int px = firstpx;
            int py = firstpy;
            int qx = firstqx;
            int qy = firstqy;
            int rx = in.nextInt();
            int ry = in.nextInt();

            int firstTurn = Turn(px,py,qx,qy,rx,ry);
            boolean turnFound = false;
            if (firstTurn != 0){
                turnFound = true;
            }
            boolean correct = true;
            for (int i = 3; i < coords; i++) {
                px = qx;
                py = qy;
                qx = rx;
                qy = ry;
                rx = in.nextInt();
                ry = in.nextInt();

                int t = Turn(px,py,qx,qy,rx,ry);

                if(!turnFound){
                    firstTurn = t;
                } else if( t != 0 && t != firstTurn){
                    correct = false;
                }

            }


            int t = Turn(rx,ry,firstpx,firstpy,firstqx,firstqy);

            if( t != 0 && t != firstTurn){
                correct = false;
            }

            if(!correct)
                System.out.println("Yes");
            else
                System.out.println("No");


            coords = in.nextInt();
        }
    }

    private static int Turn(int px, int py, int qx, int qy, int rx, int ry) {
        int t = ((qx-px) * (ry-qy)) - ((qy-py) * (rx-qx));
        if(t == 0)
            return 0;

        if(t > 0)
            return 1;

        return -1;

    }


}