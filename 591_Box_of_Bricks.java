import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int setNumber = 1;
        int avgHeight;
        int i;
        int minimumMoves;
        int heightSum;
        int stackHeight;
        int stackCount;

        while(true){
            stackCount = in.nextInt();
            if(stackCount == 0){
                break;
            }
            int[] stackArray = new int[stackCount];
            heightSum = 0;
            minimumMoves = 0;
            for (i = 0; i < stackCount; i++) {
                stackHeight = in.nextInt();
                stackArray[i] = stackHeight;
                heightSum += stackHeight;
            }
            avgHeight = heightSum/stackCount;

            for (int stack :stackArray ) {
                if(stack > avgHeight){
                    minimumMoves += stack-avgHeight;
                }
            }
            System.out.println("Set #" + setNumber++);
            System.out.println("The minimum number of moves is " + minimumMoves + ".");
            System.out.println("");
        }
    }
}