package longer_questions_prob;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class q2 {

    static void func() {
        String[] t_s = {"American Beech", "Basswood", "Common Horsechestnut", "Dogwood", "European White Birch", "White Fir"};
        double[] t_gf = {6, 3, 8, 7, 5, 7.5};
        Scanner askInt = new Scanner(System.in);

        boolean t_num_bool = false;
        int t_num = -1;

        try {
            System.out.print("Num of trees: ");
            t_num_bool = askInt.hasNextInt();

            if (t_num_bool) {
                t_num = askInt.nextInt();
            } else {
                assert false;
                throw new InputMismatchException();
            }
            System.out.println(t_num);
        } catch (InputMismatchException e) {
            System.out.println("You must specify a positive integer number for the number of trees!");
            func();
        }


        String[] t_s_input = new String[t_num];
        double[] t_circumference = new double[t_num];
        boolean[] t_s_input_bool = new boolean[t_num];
        double[] t_gf_res = new double[t_num];
        int t_num_temp = t_num;
        int i = 0;
        while (t_num_temp > 0) {
            Scanner ask = new Scanner(System.in).useDelimiter("\n");

            System.out.printf("Circumference of tree %d: ", i + 1);
            t_circumference[i] = ask.nextDouble();
            System.out.printf("Species of tree %d: ", i + 1);
            t_s_input[i] = ask.next();

            t_s_input_bool[i] = false;
            for (int j = 0; j < t_s.length; j++) {
                String t_s_ele = t_s[j];
                if (Objects.equals(t_s_input[i], t_s_ele)) {
                    t_s_input_bool[i] = true;
                    t_gf_res[i] = t_gf[j];
                    break;
                }
            }

            ++i;
            t_num_temp--;
        }

        // print results, with error warning
        System.out.println("[Tree Species] : [Circumference] : [Estimated Age]");
        for (int k = 0; k < t_num; k++) {
            if (!t_s_input_bool[k] && t_circumference[k] <= 0) {
                System.out.println("Species entered is not available!");
                System.out.printf("The circumference for %s must be greater than 0!\n", t_s_input[k]);
                continue;
            } else if (!t_s_input_bool[k]) {
                System.out.println("Species entered is not available!");
                continue;
            } else if (t_circumference[k] <= 0) {
                System.out.printf("The circumference for %s must be greater than 0!\n", t_s_input[k]);
                continue;
            }
            System.out.printf("%s : %.1f : %.1f \n", t_s_input[k], t_circumference[k], t_circumference[k] / Math.PI * t_gf_res[k]);
        }
        System.out.println("--------------------------\n");
    }

    static void run(){
                Scanner ask = new Scanner(System.in).useDelimiter("\n");
        boolean again = true;
        while (again) {
            q2.func();
            System.out.println("Run again? [true / false]");
            again = ask.nextBoolean();
        }
    }

    public static void main(String[] args) {
        run();
    }
}
