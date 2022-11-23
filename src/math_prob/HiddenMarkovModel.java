package math_prob;

import java.util.Scanner;
import java.util.Arrays;
public class HiddenMarkovModel {

    /*
    FIRST, FILL IN PROBABILITIES HERE
     */
    // prediction probabilities A, B, C
    private final double[] prob_i_U = {0.6, 0.2, 0.7};
    private final double[] prob_i_V = {0.4, 0.8, 0.3};

    // innovation probabilities A, B, C
    private final double[] prob_p_A = {0.8, 0.2, 0.0}; // A->A, A->B, A->C
    private final double[] prob_p_B = {0.0, 0.0, 1.0}; // B->A, B->B, B->C
    private final double[] prob_p_C = {0.5, 0.5, 0.0}; // C->A, C->B, C->C

    private double valSum = 0;

    // only during innovation stage
    boolean isTotalProbONE (double[] arr){
        valSum = 0; // reset every run
        int toNthDecimal = 2;
        double precision = Math.pow(10,toNthDecimal);

        for (double d : arr){
            valSum+= d;
        }
        valSum = Math.round(valSum * precision) / precision;

        return valSum == 1;
    }

    // done changes to original arr
    void getTrueProb (double[] arr){
        for (int i=0; i<arr.length; i++){
            arr[i] = arr[i] / valSum;
        }
    }

    void goInnovation(double[] arr, char key){
        for (int i=0; i<arr.length; i++){
            switch (key) {
                case 'u' -> arr[i] = arr[i] * prob_i_U[i];
                case 'v' -> arr[i] = arr[i] * prob_i_V[i];
            }
        }
        if (!isTotalProbONE(arr)){
            System.out.println("The valSum is: "+valSum);
            getTrueProb(arr);
        }
    }

    void goPrediction(double[] arr){
        double A;
        double B;
        double C;

        A = arr[0] * prob_p_A[0] + arr[1] * prob_p_B[0] + arr[2] * prob_p_C[0];
        B = arr[0] * prob_p_A[1] + arr[1] * prob_p_B[1] + arr[2] * prob_p_C[1];
        C = arr[0] * prob_p_A[2] + arr[1] * prob_p_B[2] + arr[2] * prob_p_C[2];

        double[] arr_n = {A,B,C};
        System.out.println("The array arr_n is: "+Arrays.toString(arr_n));

        if (!isTotalProbONE(arr_n)){
            System.out.println("The valSum is: "+valSum);
            getTrueProb(arr_n);
        }

        System.arraycopy(arr_n, 0, arr, 0, arr.length); // same arr length
    }

    void query(double[] arr){
        Scanner scn = new Scanner(System.in);

        System.out.println("----- Before -----"); printState(arr);

        System.out.println("Proceed innovation or prediction first? (i / p)");
        switch(scn.next().charAt(0)){
            case 'i':
                System.out.println("Observation probabilities U or V? (u / v)");
                char key = scn.next().charAt(0);
                goInnovation(arr, key);
                break;
            case 'p':
                goPrediction(arr);
                break;
        };

        reduceTo2ndDecimal(arr);
        System.out.println("----- After -----"); printState(arr);
    }

    boolean repeat(double[] arr){
        Scanner scn = new Scanner(System.in);

        System.out.println("Proceed again? (y / n)");
        if (scn.next().charAt(0) == 'y') {
            query(arr);
            return true;
        }
        return false;
    }

    void printState(double[] arr){
        System.out.println(Arrays.toString(arr));
    }

    void reduceTo2ndDecimal(double[] arr){
        int toNthDecimal = 2;
        double precision = Math.pow(10,toNthDecimal);
        for (int i=0; i<arr.length; i++) {
            arr[i] = Math.round(arr[i] * precision) / precision;
        }
    }

    void starter(double[] arr){
        query(arr);
        while(repeat(arr)){
            repeat(arr);
        };
    }

    // driver code
    public static void main(String[] args) {
        // example
        HiddenMarkovModel h1 = new HiddenMarkovModel();
        double[] arr = {0.5, 0.5, 0.0};
        h1.starter(arr);
    }
}
