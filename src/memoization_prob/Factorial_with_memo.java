package memoization_prob;

import java.util.ArrayList;

public class Factorial_with_memo {

    static ArrayList<Integer> memo = new ArrayList<>();

    Integer calculate (Integer input){
        Integer factorial=0;
        if (input == 0) return 1;
        // check if recursive call input is already existed in the memo
        if (memo.size() >= input){
            System.out.println("Retrieved from memo: " + input);
            return memo.get(input-1); // since first push-in num is located at idx 0
        }
        System.out.println("Calculate for input: " + input);
        factorial = input * calculate(input - 1);
        memo.add(factorial);
        System.out.println("memo status: "+ memo.toString());
        return factorial;
    }

    public static void main(String[] args) {
        Factorial_with_memo f1 = new Factorial_with_memo();
        Factorial_with_memo f2 = new Factorial_with_memo();
        System.out.println(f1.calculate(10));
        System.out.println(f2.calculate(5)); // reuse memo content, solve bottleneck efficiency
        System.out.println(f2.calculate(3)); // reuse memo content, solve bottleneck efficiency
        System.out.println(f2.calculate(12)); // since it is > 10, memo has to reinclude new num
    }
}
