import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.run(args);
    }

    private void run(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while( (line = in.readLine()) != null){

            String[] sentences = line.split(" ");

            char[] first = sentences[0].toCharArray();
            char[] second = sentences[1].toCharArray();

            int charIndex = 0;
            boolean success = false;

            for (int i = 0; i < second.length; i++) {

                if(second[i] == first[charIndex]){
                    charIndex ++;
                }
                if(charIndex >= first.length){
                    success = true;
                    break;
                }

            }

            if(success){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }


    }
}