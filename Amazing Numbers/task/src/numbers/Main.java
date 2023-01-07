//Version 1.1 - remarks : One of my professor told me the art of programming is abstraction ,
// to limit/set the boundary of your programme
// here i just set the programme to run upto 5 properties
// i only use simple logic , and it is not a good practice to put everything together. should put into different class
// naming of functions should be isHappyNumber , isBuzzNumber etc.
package numbers;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    static long input=0;
    static int index=0;
    static String [] property ;
    static Property[] properties = Property.values();
    static String[] correctPrintProperties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD", "EVEN", "ODD", "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD", "-EVEN", "-ODD"};
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        menuTitle();
        boolean formatOne=false;
        boolean formatTwo=false;
        boolean formatThree=false;
        boolean formatFour=false;
        do {
            String inputString = menuValue();
            String[] inputElements = inputString.split(" ");

            if (inputString.isEmpty()) {
                menuTitle();
            } else {
                if (inputString.matches("[a-zA-Z\s]+")) { //testing
                    input = -999999999; // assume -999999 to hit the checknatural()
                    checkNatural(input);
//                    checkNatural(index);
                } else {
                    input = Long.parseLong(inputElements[0]);
                    if (input == 0) {
                        System.out.println("\nGoodbye!");
                    } else if (inputElements.length == 1) {
                        formatOne = true;
                    } else if (inputElements.length == 2) {
                        index = Integer.parseInt(inputElements[1]);
                        formatOne = false;
                        formatTwo = true;
                    } else if (inputElements.length == 3) {
                        index = Integer.parseInt(inputElements[1]);
                        property = new String[inputElements.length-2];
                        for (int i=0 ; i< inputElements.length-2 ;i++){
                            property[i]= new String(inputElements[i + 2]);
                        }
                        formatOne = false;
                        formatTwo = false;
                        formatThree = true;
                    } else if (inputElements.length >= 4) {
                        index = Integer.parseInt(inputElements[1]);
                        property = new String[inputElements.length-2];
                        for (int i=0 ; i< inputElements.length-2 ;i++){
                            property[i]= new String(inputElements[i + 2]);
//                            System.out.println(property[i]);//crosscheck
                        }
                        formatOne = false;
                        formatTwo = false;
                        formatThree = false;
                        formatFour = true;
                    }

                    if (input != 0 && checkNatural(input)) {
                        if (formatOne) {
                            showFormatOne(); // test success!
                        } else if (formatTwo) {
                            System.out.println();
                            for (int i = 0; i < index; i++) {
                                showFormatTwo(input + i); //test success!
                            }
                        } else if (formatThree) {
                                if (!checkProperty(property[0])){
                                System.out.println("The property [" + property[0] + "] is wrong.");
                                StringBuilder correctProperty1 = new StringBuilder(Arrays.toString(properties));
                                System.out.println("Available properties: " + correctProperty1);
                            } else {
                                showOneProperty(input, index, property[0]);
                            }
                        } else if (formatFour) {
                            StringBuilder correctProperty1 = new StringBuilder(Arrays.toString(properties));
                            if (!checkProperty(property[0]) && !checkProperty(property[1])) {
                                System.out.println("The properties [" + property[0] + ", " + property[1] + "] are wrong.");
                                System.out.println("Available properties: " + correctProperty1);
                            } else if (inputElements.length == 7){
                                if (arePropertiesValid(property)&&!isMutualExcl(property)){
                                showFiveProperty(input, index, property[0],property[1],property[2],property[3],property[4]);
                                }
                            } else if (inputElements.length == 6) {
                                if (arePropertiesValid(property)&&!isMutualExcl(property)){
                                showFourProperty(input, index, property[0], property[1], property[2],property[3]);
                                }
                            } else if (inputElements.length == 5){
                                if (arePropertiesValid(property)&&!isMutualExcl(property)){
                                showThreeProperty(input,index,property[0],property[1],property[2]);
                                }
                            }
                            else if (arePropertiesValid(property)&&!isMutualExcl(property)) {
                                showTwoProperty(input, index, property[0],property[1]);
                            }
                        }
                    }
                }
            }
        }while (input != 0) ;
    }
    private static boolean arePropertiesValid(String [] string){
        boolean Valid = true;
        boolean contLoop = true;
        for (int i = 0 ; i<string.length && contLoop; i++){
            if (!checkProperty(string[i])){
                System.out.println("The property [" + string[i] + "] is wrong.");
                System.out.println("Available properties: " + Arrays.toString(properties));
                Valid = false;
            }
            for (int j =i+1; j< string.length;j++){
                if (!checkProperty(string[j])){
//                    System.out.println("TestLoop");
                    System.out.println("The property [" + property[j] + "] is wrong.");
                    System.out.println("Available properties: " + Arrays.toString(properties));
                }
            }
        }
        return Valid;
    }
    private static void showFormatOne(){
        String formatted = NumberFormat.getInstance().format(input);
        System.out.println("\nProperties of " + formatted);
        for ( int i=0 ; i<properties.length;i++) {
            System.out.printf("%12s", String.valueOf(properties[i]).toLowerCase());
            System.out.println(": " + Property.checkProperty(input, String.valueOf(properties[i]).toLowerCase())); // test success!
        }
    }
    private static void showFormatTwo(long m_input){
        String [] s = new String[Property.values().length];
        StringBuilder printoutString = new StringBuilder(" is ");
        for ( int i=0 ; i<properties.length;i++){
            if (i<properties.length-2) {
                s[i] = Property.checkProperty(m_input, String.valueOf(properties[i])) ? String.valueOf(properties[i]).toLowerCase() + ", " : "";
            } else  s[i] = Property.checkProperty(m_input, String.valueOf(properties[i])) ? String.valueOf(properties[i]).toLowerCase() : "";
            printoutString.append(s[i]);
        }
        System.out.println("             " + NumberFormat.getInstance().format(m_input) + printoutString);
    }

    private static void showFiveProperty(long m_input, int m_index, String m_property1, String m_property2, String m_property3, String m_property4, String m_property5) {
        System.out.println();
        checkFiveProperty(m_input,m_index,m_property1,m_property2,m_property3,m_property4,m_property5);
    }

    private static void checkFiveProperty(long m_input, int m_index,String m_property1,String m_property2,String m_property3,String m_property4, String m_property5) {
        int sysCnt = 0;
//        System.out.println(m_index);
        for (int i = 0; i <= (m_index * 10000*10000 + m_input) && sysCnt < m_index; i++) {//ensure enough max no
            if (Property.checkProperty(m_input + i,m_property1)&&Property.checkProperty(m_input+i,m_property2)&&Property.checkProperty(m_input+i,m_property3)&&Property.checkProperty(m_input+i,m_property4)&&Property.checkProperty(m_input+i,m_property5)) {
                showFormatTwo(m_input + i);
                sysCnt++;
            }
        }
    }
    private static void showFourProperty(long m_input, int m_index, String m_property1, String m_property2, String m_property3, String m_property4) {
        System.out.println();
        checkFourProperty(m_input,m_index,m_property1,m_property2,m_property3,m_property4);
    }

    private static void checkFourProperty(long m_input, int m_index,String m_property1,String m_property2,String m_property3,String m_property4) {
        int sysCnt = 0;
//        System.out.println(m_index);
        for (int i = 0; i <= (m_index * 10000*10000 + m_input) && sysCnt < m_index; i++) {//ensure enough max no
            if (Property.checkProperty(m_input + i,m_property1)&&Property.checkProperty(m_input+i,m_property2)&&Property.checkProperty(m_input+i,m_property3)&&Property.checkProperty(m_input+i,m_property4)) {
                showFormatTwo(m_input + i);
                sysCnt++;
            }
        }
    }

    private static void showThreeProperty(long m_input, int m_index, String m_property1, String m_property2, String m_property3){
        System.out.println();
        checkThreeProperties(m_input,m_index,m_property1,m_property2,m_property3);
    }

    private static void checkThreeProperties(long m_input, int m_index, String m_property1,String m_property2,String m_property3) {
        int sysCnt = 0;
//        System.out.println(m_index);
        for (int i = 0; i <= (m_index * 10000*10000 + m_input) && sysCnt < m_index; i++) {//ensure enough max no
            if (Property.checkProperty(m_input + i,m_property1)&&Property.checkProperty(m_input+i,m_property2)&&Property.checkProperty(m_input+i,m_property3)) {
                showFormatTwo(m_input + i);
                sysCnt++;
            }
        }
    }
    private static void checkTwoProperties(long m_input,int m_index,String m_property1, String m_property2){
        int sysCnt = 0;
        for (int i = 0; i <= (m_index * 10000*10000 + m_input) && sysCnt < m_index; i++) {//ensure enough max no
            if (Property.checkProperty(m_input+i,m_property1)&&Property.checkProperty(m_input+i,m_property2)) {
                showFormatTwo(m_input + i);
                sysCnt++;
            }
        }
    }
    private static void showTwoProperty(long m_input, int m_index, String m_property1, String m_property2) {
        if (m_property1.equals(m_property2)){
            showOneProperty(m_input,m_index,m_property1);
        }
            System.out.println();
            checkTwoProperties(m_input,m_index,m_property1,m_property2);
    }
    private static void showOneProperty(long m_input, int m_index, String m_property1) {
        System.out.println();
        int sysCnt = 0;
        for (int i = 0; i <= (m_index * 10000*10000 + m_input) && sysCnt < m_index; i++) {//ensure enough max no
            if (checkProperty(m_property1)) {
                if (Property.checkProperty(m_input + i, m_property1)) { //Property.checkProperty**
                    showFormatTwo(m_input + i);
                    sysCnt++;
                }
            }
        }
    }
    public enum Property {
        BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD;
        private static boolean checkNegativeProperty(long m_input,String m_property){
            return (!checkProperty(m_input,m_property));
        }
        private static boolean checkProperty(long m_input,String m_property) {
            boolean checkProperty = false;
            if (m_property.startsWith("-")){ // here to return -property using return !property
                m_property = m_property.substring(1);
                checkProperty = !checkProperty(m_input,m_property);
            } else
            switch(m_property.toLowerCase()){
                case "even":
                    return m_input % 2 == 0;
                case "odd":
                    return m_input % 2 !=0;
                case "buzz":
                    return m_input % 7 == 0 || m_input % 10 == 7;
                case "duck":
                    boolean ducked = false;
                    while(m_input != 0) {// use loop to repeat steps
                        if(m_input%10 == 0)// check whether the last digit of the number is zero or not
                            ducked = true;    //return true if the number is Duck
                        m_input = m_input / 10; // divide the number by 10 to remove the last digit
                    }
                    return ducked;
                case "palindromic":
                    boolean palindromic = false;
                    long r,sum=0,temp;
                    temp=m_input;
                    while(m_input>0){
                        r=m_input%10;  //getting remainder
                        sum=(sum*10)+r;
                        m_input=m_input/10;
                    }
                    if(temp==sum)
                        palindromic = true;
                    return palindromic;
                case "gapful":
                    boolean gapful = false;
                    if (m_input>=100) {
                        int first_dig = firstDigit(m_input);
                        int last_dig = lastDigit(m_input);
                        int concatenation = first_dig * 10 +
                                last_dig;
                        gapful = (m_input % concatenation == 0); // Return true if n is gapful number
                    }
                    return gapful;
                case "spy":
                    int product = 1, ld;
                    sum =0 ;
                    while (m_input > 0) {// calculate sum and product of the number here.
                        ld = (int) (m_input % 10);
                        sum = sum + ld;
                        product = product * ld;
                        m_input = m_input / 10;
                    }
                    return sum==product;
                case "square":
                    double squareRoot = Math.sqrt(m_input);
                    return ((squareRoot-Math.floor(squareRoot))==0);
                case "sunny":
                    squareRoot = Math.sqrt(m_input+1);
                    return ((squareRoot-Math.floor(squareRoot))==0);
                case "jumping":
                    boolean flag = true;
                    while (m_input!=0){
                        int digit1 = (int) (m_input % 10);
                        m_input /= 10;
                        if (m_input!=0){
                            int digit2 = (int)(m_input % 10);
                            if(Math.abs(digit1 - digit2) != 1){
                                flag = false;
                                break;
                            }
                        }
                    }
                    return flag;
                case "happy":
                    long result = m_input;
                    while (result != 1 && result != 4)
                    {
                        result = checkHappyNumber(result);
                    }
                    return result==1;
                case "sad":
                    result = m_input;
                    while (result != 1 && result != 4)
                    {
                        result = checkHappyNumber(result);
                    }
                    return result!=1;
                default:
                    System.out.println("Test -ve"); // no use
            }
            return checkProperty;
        }
        private static int checkHappyNumber (long number){
            int rem = 0, sum = 0; // calculate the sum of squares of each digits
            while(number > 0) {
                rem = (int) (number %10);
                sum = sum+(rem*rem);
                number = number/10;
            }
            return sum;
        }
        private static int lastDigit(long mInput) {
            return (int) (mInput%10);
        }

        private static int firstDigit(long mInput) {
            int digits = (int)(Math.log(mInput) / Math.log(10)); // Find total number of digits - 1
            mInput = (int)(mInput / Math.pow(10, digits));// Find first digit
            return (int) mInput; // Return first digit
        }
        public String nameLowerCase(){
            return name().toLowerCase();
        }
    }
    private static boolean isMutualExcl(String [] m_property){
        for (int i=0 ; i< m_property.length;i++){
            if (m_property[i].equals("odd")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("even")){
                        System.out.println("The request contains mutually exclusive properties: [EVEN, ODD]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].equals("square")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("sunny")){
                        System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].equals("duck")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("spy")){
                        System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].equals("happy")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("sad")){
                        System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].equals("-happy")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("-sad")){
                        System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].equals("-odd")){
                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals("-even")){
                        System.out.println("The request contains mutually exclusive properties: [-ODD, -EVEN]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }
            if (m_property[i].startsWith("-")){
                String temp = m_property[i].substring(1);

                for (int j=0;j< m_property.length;j++){
                    if (m_property[j].equals(temp)){
                        System.out.println("The request contains mutually exclusive properties: [" + temp +", -" + temp + "]\n" +
                                "There are no numbers with these properties.");
                        return true;
                    }
                }
            }

        }
        return false;
    }
    private static boolean checkProperty(String m_property){
        boolean checkProperty = false ;
        for (int i=0 ; i< correctPrintProperties.length; i++){
//            System.out.println(properties[i]);
            if (m_property.toLowerCase().equals(correctPrintProperties[i].toLowerCase())){
                checkProperty = true;
            }
        }
        return checkProperty;
    }

    private static void menuTitle() {
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }
    private static String menuValue() {
        System.out.print("\nEnter a request: ");
        return scanner.nextLine();
    }

    private static boolean checkNatural(long m_input) {
        boolean natural = false;
        if (m_input<=0){
            System.out.println("\nThe first parameter should be a natural number or zero.");
        } else if (index<0){
            System.out.println("\n The second parameter should be a natural number.");
            index=0;
        } else  natural=true;

        return natural;
    }

}
