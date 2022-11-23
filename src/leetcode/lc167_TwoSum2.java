package leetcode;/*
    * Given array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
    *
    * The function twoSum should return indices of two numbers such that they add up to the target, where index1 must be less than index2.
    *
    * Note:
    *   Your returned answers (both index1 and index2) are not zero-based.
    *   You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */

public class lc167_TwoSum2 {
    public static void main(String[] args) {
        // numbers must be sorted, to use this 2-pointer search method
        int[] numbers = {2,7,9,20};
        int target = 9;
        for (int i : twoSum(numbers, target)){
            System.out.print(i+", ");
        }
    }
    public static int[] twoSum(int[] numbers, int target){
        int i = 0;
        int j = numbers.length-1;
        while (i <= j){
            int sum = numbers[i]+numbers[j];
            if (sum < target){
                i += 1;
            } else if (sum > target) {
                j -= 1;
            } else {
                return new int[] {i+1,j+1};
            }
        }
        return new int[] {i+1,j+1};
    }
}
