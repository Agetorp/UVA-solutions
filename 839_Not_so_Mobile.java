import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine().trim());
        for (int i = 0; i < cases; i++) {
            in.readLine();
            binaryTree t = createTree(in);
            String result = (t.isBalanced()) ? "YES" : "NO";
            System.out.println(result);

            if(cases > 1){
                if(i != cases -1){
                    System.out.println();
                }
            }
        }
    }

    private static binaryTree createTree(BufferedReader in) throws IOException {

        String[] numbers = (in.readLine()).split(" ");
        binaryTree t = new binaryTree();
            int wl=Integer.parseInt(numbers[0]);
            int dl=Integer.parseInt(numbers[1]);
            int wr=Integer.parseInt(numbers[2]);
            int dr=Integer.parseInt(numbers[3]);


        if(wl != 0) {
            t.setWeightLeft(wl);
            t.setDistanceLeft(dl);
        } else {
            t.setLeft(createTree(in));
            t.setDistanceLeft(dl);
        }

        if(wr != 0) {
            t.setWeightRight(wr);
            t.setDistanceRight(dr);
        } else {
            t.setRight(createTree(in));
            t.setDistanceRight(dr);
        }
        return t;
    }

}

class binaryTree {
    binaryTree left;

    public void setDistanceRight(int distanceRight) {
        this.distanceRight = distanceRight;
    }

    public void setLeft(binaryTree left) {
        this.left = left;
    }


    public void setWeightLeft(int weightLeft) {
        this.weightLeft = weightLeft;
    }

    public void setDistanceLeft(int distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public void setRight(binaryTree right) {
        this.right = right;
    }

    public void setWeightRight(int weightRight) {
        this.weightRight = weightRight;
    }

    int weightLeft;
    int distanceLeft;

    binaryTree right;
    int weightRight;
    int distanceRight;

    public binaryTree (){

    }

    public int getTotalWeight(){
        return getRightWeight() + getLeftWeight();
    }

    private int getLeftWeight(){
        return (weightLeft == 0) ? left.getTotalWeight() : weightLeft;
    }
    private int getRightWeight(){
        return (weightRight == 0) ? right.getTotalWeight() : weightRight;
    }

    public boolean isBalanced() {

        if(weightLeft != 0 && weightRight != 0) { // finns inga subträd
            return ((weightLeft * distanceLeft) == (weightRight * distanceRight));
        } else if(weightLeft == 0 && weightRight == 0) { //Finns träd åt båda håll
            if(!left.isBalanced() || !right.isBalanced()) {
                return false;
            } else {
                return ((getLeftWeight() * distanceLeft) == (getRightWeight() * distanceRight));
            }
        } else if(weightLeft == 0) { //finns träd till vänster
            if (!left.isBalanced()) {
                return false;
            } else {
                return ((getLeftWeight() * distanceLeft) == (weightRight * distanceRight));
            }
        } else { //finns träd till höger
            if (!right.isBalanced()) {
                return false;
            } else {
                return ((weightLeft * distanceLeft) == (getRightWeight() * distanceRight));
            }
        }
    }
}




