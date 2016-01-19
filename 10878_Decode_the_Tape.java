import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String sentence = "";
        String row = in.nextLine();

        while(true){
            row = in.nextLine();
            char[] rowChars = row.toCharArray();
            String t = "";
            if(rowChars[0] == '_'){
                break;
            }
            for (char character :rowChars){
                if (character == 'o'){
                    t += "1";
                } else if (character == ' '){
                    t += "0";
                }

            }
            int decimal = Integer.parseInt(t, 2); //binary to decimal
            String letter = "";
            letter += (char)(decimal); //decimal to char
            sentence += letter;
        }
        System.out.print(sentence);
    }
}