import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


    MyInt[] first;
    MyInt[] second;
    MyInt[] third;
    MyIntPointer[] myPointers;
    MyInt I;
    MyInt X;
    MyInt C;
    MyInt M;
    MyInt V;
    MyInt L;
    MyInt D;
    boolean hasI;
    boolean hasX;
    boolean hasC;
    boolean hasM;
    boolean hasV;
    boolean hasL;
    boolean hasD;


    int firstLength;
    int secondLength;
    int thirdLength;


    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.run(args);
    }

    private void run(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String input = in.readLine().trim();
        while(!input.equals("#")){

            String arabic = theFunctionThatSolvesAllProblems(input);
            String roman = thatOtherFunctionThatAlsoSolvesAlotOfProblems(input);
            System.out.println(roman + " " + arabic);
            input = in.readLine().trim();

        }


    }

    private String thatOtherFunctionThatAlsoSolvesAlotOfProblems(String input) {

        String first = input.split("\\+")[0];
        String second = input.split("\\+")[1].split("=")[0];
        String third = input.split("\\+")[1].split("=")[1];

        if(decode(first) + decode(second) == decode(third))
            return "Correct";
        return "Incorrect";

    }

    private String theFunctionThatSolvesAllProblems(String input) throws IOException {

        //Regel 1: x + x != VVV

        first = new MyInt[9];
        second = new MyInt[9];
        third = new MyInt[9];
        myPointers = new MyIntPointer[7];

        M = new MyInt();
        D = new MyInt();
        C = new MyInt();
        L = new MyInt();
        X = new MyInt();
        V = new MyInt();
        I = new MyInt();

        hasI = false;
        hasX = false;
        hasC = false;
        hasM = false;
        hasV = false;
        hasL = false;
        hasD = false;

        firstLength = 0;
        secondLength = 0;
        thirdLength = 0;

        initializeEquation(input);

        return arabic();

    }

    private String arabic(){
        int counter = calculateArabic();
        if(counter == 0)
            return "impossible";
        if(counter == 1)
            return "valid";

        return "ambiguous";
    }

    private int calculateArabic(){
        int counter = 0;

        for (int i1 = 0; i1 <= 9; i1++) {
            myPointers[0].myInt.value = i1;

            for (int i2 = 0; i2 <= 9; i2++) {
                if(i2 == i1)
                    continue;
                if(myPointers[1] == null){
                    counter = checkThatShit(counter);
                    break;
                }
                myPointers[1].myInt.value = i2;

                for (int i3 = 0; i3 <= 9; i3++) {
                    if(i3 == i2 || i3 == i1)
                        continue;
                    if(myPointers[2] == null){
                        counter = checkThatShit(counter);
                        break;
                    }
                    myPointers[2].myInt.value = i3;

                    for (int i4 = 0; i4 <= 9; i4++) {
                        if(i4 == i3 || i4 == i2 || i4 == i1)
                            continue;
                        if(myPointers[3] == null){
                            counter = checkThatShit(counter);
                            break;
                        }
                        myPointers[3].myInt.value = i4;

                        for (int i5 = 0; i5 <= 9; i5++) {
                            if(i5 == i4 || i5 == i3 || i5 == i2 || i5 == i1)
                                continue;
                            if(myPointers[4] == null){
                                counter = checkThatShit(counter);
                                break;
                            }
                            myPointers[4].myInt.value = i5;

                            for (int i6 = 0; i6 <= 9; i6++) {
                                if(i6 == i5 || i6 == i4 || i6 == i3 || i6 == i2 || i6 == i1)
                                    continue;
                                if(myPointers[5] == null){
                                    counter = checkThatShit(counter);
                                    break;
                                }
                                myPointers[5].myInt.value = i6;

                                for (int i7 = 0; i7 <= 9; i7++) {
                                    if(i7 == i6 || i7 == i5 || i7 == i4 || i7 == i3 || i7 == i2 || i7 == i1)
                                        continue;
                                    if(myPointers[6] == null){
                                        counter = checkThatShit(counter);
                                        break;
                                    }
                                    myPointers[6].myInt.value = i7;

                                    counter = checkThatShit(counter);
                                    if(counter >= 2)
                                        return 2;

                                }
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    private int checkThatShit(int counter){
        int f = calcFirst();
        int s = calcSecond();
        int t = calcThird();
        if(f == -1 || s == -1 || t == -1)
            return counter;
        if(f+s==t){
//                                        System.out.println("" + f + " + " + s + " = " + t);
            return counter+1;
        }
        return  counter;
    }

    private void initializeEquation(String input) throws IOException {
        char[] inputChars = input.toCharArray();

        int digitIndex = -1;
        int charIndex = 0;
        List<Character> theseExist = new ArrayList<Character>();

        for (int i = 0; i < inputChars.length; i++) {
            char c = inputChars[i];
            charIndex++;
            digitIndex++;
            if (c == '+') {
                firstLength = digitIndex;
                break;
            } else {
                switch (c) {
                    case 'I': {
                        first[digitIndex] = I;
                        hasI = true;
                        if(!theseExist.contains('I'))
                            theseExist.add('I');
                        break;
                    }
                    case 'X': {
                        first[digitIndex] = X;
                        if(!theseExist.contains('X'))
                            theseExist.add('X');
                        hasX = true;
                        break;
                    }
                    case 'C': {
                        first[digitIndex] = C;
                        if(!theseExist.contains('C'))
                            theseExist.add('C');
                        hasC = true;
                        break;
                    }
                    case 'M': {
                        first[digitIndex] = M;
                        if(!theseExist.contains('M'))
                            theseExist.add('M');
                        hasM = true;
                        break;
                    }
                    case 'V': {
                        first[digitIndex] = V;
                        if(!theseExist.contains('V'))
                            theseExist.add('V');
                        hasV = true;
                        break;
                    }
                    case 'L': {
                        first[digitIndex] = L;
                        if(!theseExist.contains('L'))
                            theseExist.add('L');
                        hasL = true;
                        break;
                    }
                    case 'D': {
                        first[digitIndex] = D;
                        if(!theseExist.contains('D'))
                            theseExist.add('D');
                        hasD = true;
                        break;
                    }
                }
            }
        }
        digitIndex = -1;
        for (int i = charIndex; i < inputChars.length; i++) {
            char c = inputChars[i];
            charIndex++;
            digitIndex++;
            if (c == '=') {
                secondLength = digitIndex;
                break;
            } else {
                switch (c) {
                    case 'I': {
                        second[digitIndex] = I;
                        hasI = true;
                        if(!theseExist.contains('I'))
                            theseExist.add('I');
                        break;
                    }
                    case 'X': {
                        second[digitIndex] = X;
                        if(!theseExist.contains('X'))
                            theseExist.add('X');
                        hasX = true;
                        break;
                    }
                    case 'C': {
                        second[digitIndex] = C;
                        if(!theseExist.contains('C'))
                            theseExist.add('C');
                        hasC = true;
                        break;
                    }
                    case 'M': {
                        second[digitIndex] = M;
                        if(!theseExist.contains('M'))
                            theseExist.add('M');
                        hasM = true;
                        break;
                    }
                    case 'V': {
                        second[digitIndex] = V;
                        if(!theseExist.contains('V'))
                            theseExist.add('V');
                        hasV = true;
                        break;
                    }
                    case 'L': {
                        second[digitIndex] = L;
                        if(!theseExist.contains('L'))
                            theseExist.add('L');
                        hasL = true;
                        break;
                    }
                    case 'D': {
                        second[digitIndex] = D;
                        if(!theseExist.contains('D'))
                            theseExist.add('D');
                        hasD = true;
                        break;
                    }
                }
            }
        }
        digitIndex = -1;

        for (int i = charIndex; i < inputChars.length; i++) {
            char c = inputChars[i];
            digitIndex++;
            switch (c) {
                case 'I': {
                    third[digitIndex] = I;
                    hasI = true;
                    if(!theseExist.contains('I'))
                        theseExist.add('I');
                    break;
                }
                case 'X': {
                    third[digitIndex] = X;
                    if(!theseExist.contains('X'))
                        theseExist.add('X');
                    hasX = true;
                    break;
                }
                case 'C': {
                    third[digitIndex] = C;
                    if(!theseExist.contains('C'))
                        theseExist.add('C');
                    hasC = true;
                    break;
                }
                case 'M': {
                    third[digitIndex] = M;
                    if(!theseExist.contains('M'))
                        theseExist.add('M');
                    hasM = true;
                    break;
                }
                case 'V': {
                    third[digitIndex] = V;
                    if(!theseExist.contains('V'))
                        theseExist.add('V');
                    hasV = true;
                    break;
                }
                case 'L': {
                    third[digitIndex] = L;
                    if(!theseExist.contains('L'))
                        theseExist.add('L');
                    hasL = true;
                    break;
                }
                case 'D': {
                    third[digitIndex] = D;
                    if(!theseExist.contains('D'))
                        theseExist.add('D');
                    hasD = true;
                    break;
                }
            }
            thirdLength = digitIndex+1;
        }


        for (int i1 = 0; i1 < theseExist.size(); i1++) {
            Character myChar = theseExist.get(i1);
            switch (myChar) {
                case 'I': {
                    myPointers[i1] = new MyIntPointer(I);
                    break;
                }
                case 'X': {
                    myPointers[i1] = new MyIntPointer(X);
                    break;
                }
                case 'C': {
                    myPointers[i1] = new MyIntPointer(C);
                    break;
                }
                case 'M': {
                    myPointers[i1] = new MyIntPointer(M);
                    break;
                }
                case 'V': {
                    myPointers[i1] = new MyIntPointer(V);
                    break;
                }
                case 'L': {
                    myPointers[i1] = new MyIntPointer(L);
                    break;
                }
                case 'D': {
                    myPointers[i1] = new MyIntPointer(D);
                    break;
                }

            }

        }
    }
    private int calcFirst(){
        if(first[0].value == 0)
            return -1;

        String result = "";
        for (int i = 0; i < firstLength; i++) {
            result += first[i].value;
        }
        return Integer.parseInt(result);
    }
    private int calcSecond(){
        if(second[0].value == 0)
            return -1;
        String result = "";
        for (int i = 0; i < secondLength; i++) {
            result += second[i].value;
        }
        return Integer.parseInt(result);
    }
    private int calcThird(){
        if(third[0].value == 0)
            return -1;

        String result = "";
        for (int i = 0; i < thirdLength; i++) {

            result += third[i].value;
        }
        return Integer.parseInt(result);
    }


    private int decodeSingle(char letter) {
        switch(letter) {
            case 'M': return 1000;
            case 'D': return 500;
            case 'C': return 100;
            case 'L': return 50;
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default: return 0;
        }
    }
    private  int decode(String roman) {
        int result = 0;
        String uRoman = roman.toUpperCase(); //case-insensitive
        for(int i = 0;i < uRoman.length() - 1;i++) {//loop over all but the last character
            //if this character has a lower value than the next character
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i+1))) {
                //subtract it
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                //add it
                result += decodeSingle(uRoman.charAt(i));
            }
        }
        //decode the last character, which is always added
        result += decodeSingle(uRoman.charAt(uRoman.length()-1));
        return result;
    }

    public class MyInt {
        public int value;

        public MyInt(){
            value = 0;
        }

        public MyInt(int i){
            value = i;
        }
    }
    public class MyIntPointer {
        public MyInt myInt;

        public MyIntPointer(MyInt i){
            myInt = i;
        }
    }

}
