import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();

        while(cases != 0){
            double[][] matrix = new double[cases][2];


            for (int i = 0; i < cases; i++) {
                matrix[i][0] = in.nextDouble();
                matrix[i][1] = in.nextDouble();
            }
            if(cases == 1){
                cases = in.nextInt();
                System.out.println("INFINITY");
                continue;
            }
            double shortestDistance = Double.MAX_VALUE;

            for (int i = 0; i < cases; i++) {
                for (int j = i+1; j < cases; j++) {
                    double dist = calcDist(matrix[i], matrix[j]);
                    if(dist < shortestDistance)
                        shortestDistance = dist;
                }
            }
            shortestDistance = Math.round (shortestDistance * 10000.0) / 10000.0;
            if(shortestDistance <= 10000.0000)
                System.out.printf("%.4f%n", shortestDistance);
            else
                System.out.println("INFINITY");
            cases = in.nextInt();
        }

    }

    private static double calcDist(double[] ints, double[] ints1) {

        double dx = Math.abs(ints[0] - ints1[0]);
        double dy = Math.abs(ints[1] - ints1[1]);
        return Math.sqrt((dx*dx)+(dy*dy));
    }
}