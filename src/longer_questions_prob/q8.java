package longer_questions_prob;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class q8 {

    static void start() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Input num of sensor inputs: ");
        int num_inputs = scn.nextInt();
        if (num_inputs == 0)
            return;
        String[] inputs = new String[num_inputs];

        for (int i = 0; i < num_inputs; i++) {
            scn = new Scanner(System.in);
            inputs[i] = scn.nextLine();
        }

        int p1 = 0;
        int p2 = 0;

        for (int i = 0; i < inputs.length; i++) {
            if (Objects.equals(inputs[i], "#")) {
                p1 = i;
                p2 = i;
                int p1_pos = 0;
                int p2_pos = 0;
                double prev = 0;
                double next = 0;
                while (p1 != 0) {
                    p1--;
                    // found prev
                    if (!Objects.equals(inputs[p1], "#")) {
                        p1_pos = p1;
                        prev = Double.parseDouble(inputs[p1]);
                        while (p2 != inputs.length - 1) {
                            p2++;
                            // found next
                            if (!Objects.equals(inputs[p2], "#")) {
                                p2_pos = p2;
                                next = Double.parseDouble(inputs[p2]);

                                // update value based on interpolation
                                if (p2_pos - p1_pos != 0) {
                                    double gap_diff = next - prev;
                                    int numToRecount = p2_pos - p1_pos - 1;
                                    for (int j = 0; j < numToRecount; j++) {
                                        double newval = Double.parseDouble(inputs[i + j - 1]) + gap_diff / (numToRecount + 1);
                                        inputs[i + j] = String.format( "%.2f", Math.rint(newval * 100.0)/100.0 );
                                        System.out.printf("element %dth has new interpolated val: %s \n", i+j, inputs[i+j]);
                                    }
                                    break;
                                }

                            }
                        }
                    }
                    if (p2_pos - p1_pos != 0) {
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(inputs));

    }

    public static void main(String[] args) {
        start();
    }
}
