import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();

        for (int caseNumber = 0; caseNumber < cases; caseNumber++) {


            int coordPairs = in.nextInt();
            int graph[][] = new int[coordPairs][2];

            for (int i = 0; i < coordPairs; i++) {
                graph[i][0] = in.nextInt();
                graph[i][1] = in.nextInt();
            }

            Arrays.sort(graph, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            double currentPeak = 0;
            double bottomLeftAngle = 90;
            double totalLength = 0;
            for (int i = coordPairs-2; i >= 0; i--) {
                if (graph[i][1] > currentPeak){
                    int x = graph[i][0];
                    int y = graph[i][1];

                    //int lSideLengthBig = y - currentPeak;
                    double lSideLengthBig = y - graph[i+1][1] ;

                    double bottomLengthBig = graph[i+1][0] - x;

                    //a2 = b2 + c2 − 2bc cosA,  https://www.mathsisfun.com/algebra/trig-solving-sas-triangles.html
                    double a = Math.pow(lSideLengthBig, 2) + Math.pow(bottomLengthBig, 2) - (2 * lSideLengthBig * bottomLengthBig * Math.toDegrees(Math.cos(Math.toRadians(bottomLeftAngle))));
                    double sunSideBigTriangle = Math.sqrt(a);



                    //sin B / b = sin A / a,   https://www.mathsisfun.com/algebra/trig-solving-sas-triangles.html
                    //sin B = (sin(49°) × 5) / 5.298...
                    double  topLeftAngle;
                    double rightAngle;
                    if(bottomLengthBig == lSideLengthBig){
                        topLeftAngle = 45;
                        rightAngle = 45;
                    } else if(bottomLengthBig < lSideLengthBig){
                        double sinB = Math.toDegrees(Math.sin(Math.toRadians(bottomLeftAngle)) * bottomLengthBig) / sunSideBigTriangle;
                        rightAngle = Math.toDegrees(Math.asin(Math.toRadians(sinB)));
                        topLeftAngle = 180 - rightAngle - bottomLeftAngle;
                    } else {
                        double sinB = Math.toDegrees(Math.sin(Math.toRadians(bottomLeftAngle)) * lSideLengthBig) / sunSideBigTriangle;
                        topLeftAngle = Math.toDegrees(Math.asin(Math.toRadians(sinB)));
                        rightAngle = 180 - topLeftAngle - bottomLeftAngle;
                    }

                    //ABOVE THIS IS THE BIG TRIANGLE, NOW TO FIND THE SUNNY SIDE OF THE SMALL ONE

                    double lSideLengthSmall = y - currentPeak;


                    //AAS-triangle
                    //b/sin B = c/sin C    c=leftSideLength, C=rightAngle, b =sunnyside, B=90
                    //b/sin(83°) = 7/sin(62°)
                    //b = (7 × sin(83°))/sin(62°)
                    double result = (lSideLengthSmall * Math.toDegrees(Math.sin(Math.toRadians(bottomLeftAngle))))  /  Math.toDegrees(Math.sin(Math.toRadians(topLeftAngle)));

                    //Result borde vara svaret

                    totalLength += result;



                    currentPeak = y;
                }

            }
            System.out.printf("%.2f%n", totalLength);
        }
    }
}