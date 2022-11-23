package university;

import Prog1Tools.IOTools;

public class PasswordCheck {
    static char[] passwordInput;
    private static char plus = '+';
    private static char minus = '-';

    public static void main(String[] args) {
        //Do the following tasks step by step
        readPassword();
        while(!passwordCorrect()){
            System.out.println("The password is not correct, b/c:");
            System.out.println("1. it contains at least 6 characters.\n" +
                               "2. it contains at least 1 number.\n" +
                               "3. it doesn't contain the characters  '+' and  '-'.");
            readPassword();
        }
    }

    //method that returns true if the password in the passwordInput-array is valid
    // (contains at least one digit but neither '+' nor '-'
    public static boolean passwordCorrect() {
        boolean result = false;
        if(passwordInput.length >= 6){
            for(int i=0; i<passwordInput.length; i++) {
                if(!containsPlusMinus() & isADigit(passwordInput[i]))
                result = true;
            }
        }
        return result;
    }

    //reads a String from the console and save it as a char-Array in the global 'passwordInput'-Array
    public static void readPassword() {
        passwordInput = IOTools.readString("Please enter your new password: ").toCharArray();
    }

    //returns true if the global password input-Array contains the char '+' or '-'
    public static boolean containsPlusMinus() {
        boolean result = false;
        for (int i = 0; i < passwordInput.length && !result; i++) {
            if (passwordInput[i] == plus) {
                result = true;
            }
            if (passwordInput[i] == minus) {
                result = true;
            }
        }
        return result;
    }

    //returns true if the input char-value is a number-char
    public static boolean isADigit(char inputChar) {
        boolean result = false;
        if (Character.isDigit(inputChar) == true) {
            result = true;
        }
        return result;
    }
}
