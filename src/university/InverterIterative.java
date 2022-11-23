package university;

import Prog1Tools.IOTools;

public class InverterIterative {

    public static void main(String[] args) {

        char[] input_chars = new char[100];
        int count = 0;
        boolean flag = true;
        StringBuilder str = new StringBuilder();

        str.append("===============================\n");
        str.append("Inverter\n");
        str.append("===============================\n\n");
        str.append("Please input characters");
        System.out.println(str.toString());

        while (flag) {
            System.out.printf("%d.Character: ", count + 1);
            input_chars[count] = IOTools.readChar();
            if (input_chars[count] == 'x' || input_chars[count] == 'X') {
                flag = false;
            }
            count++;
        }

        System.out.print("The inverted order is: ");
        for (int i = count; i >= 0; i--) {
            System.out.print(input_chars[i]);
        }
        System.out.println();
    }
}