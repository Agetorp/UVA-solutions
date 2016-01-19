import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int rows;
        int cols;
        while( in.hasNext()) {

            rows = in.nextInt();
            cols = in.nextInt();
            if(rows < 1 || cols < 1)
                continue;
            Node[][] matrix = new Node[rows][cols];

            int colsminus = cols -1;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if(c == colsminus){
                        matrix[r][c] = new Node();
                        matrix[r][c].weight = in.nextInt();
                        matrix[r][colsminus].distance = matrix[r][colsminus].weight; //first columns weight is also the distance
                    } else {
                        matrix[r][c] = new Node();
                        matrix[r][c].weight = in.nextInt();
                        matrix[r][c].distance = Long.MAX_VALUE;
                    }

                }
            }

            for (int c = cols - 2; c >= 0; c--) {
                for (int r = 0; r < rows; r++) {
                    int up = (r == 0) ? rows - 1 : r - 1;
                    int down = (r + 1) % rows;
                    int nextcol = c + 1;

                    matrix[r][c].next = Integer.MAX_VALUE;
                    int[] rowsToTry = {up, r, down};
                    Arrays.sort(rowsToTry);
                    for (int rowToTry : rowsToTry) {
                        if (matrix[r][c].next == Integer.MAX_VALUE ||
                                (matrix[rowToTry][nextcol].distance < matrix[matrix[r][c].next][nextcol].distance)) {
                            matrix[r][c].next = rowToTry;
                            matrix[r][c].distance = matrix[r][c].weight + matrix[rowToTry][nextcol].distance;
                        }
                    }
                }
            }


            long minWeight = Long.MAX_VALUE;
            int startRow = 0;
            for (int r = 0; r < rows; r++) {
                if (matrix[r][0].distance < minWeight) {
                    startRow = r;
                    minWeight = matrix[r][0].distance;
                }
            }

            int r = startRow;
            for (int c = 0; c < cols; c++) {
                System.out.print((c > 0 ? " " : "") + (r + 1));
                r = matrix[r][c].next;
            }
            System.out.println();
            System.out.println(minWeight);
        }
    }

    private static class Node {
        public int weight;
        public long distance;
        public int next;
        public Node(){
            weight = 0;
            distance = 0;
            next = 0;
        }
    }
}