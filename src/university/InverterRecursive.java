package university;

import Prog1Tools.IOTools;

public class InverterRecursive {

    static int count = 0;

    public static void main(String[] args) {

        StringBuilder str = new StringBuilder();
        str.append("===============================\n");
        str.append("Inverter\n");
        str.append("===============================\n\n");
        str.append("Please input characters");
        System.out.println(str.toString());

        inverterRecursive();
        System.out.println();
        System.out.printf("The recursive method has been called %d times.", count);

    }

    public static void inverterRecursive() {
        System.out.printf("%d.Character: ", count + 1);
        char c = IOTools.readChar();
        count++;
        if (c == 'x' || c == 'X') {
            System.out.print("The inverted order is: ");
        } else {
            inverterRecursive();
        }
        // basically add every new element to the end of recursive "stack"
        // can be seen as "stack" dataclass
        System.out.print(c);
    }
}
