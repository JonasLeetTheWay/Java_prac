package leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class lc217_ContainsDuplicate {

    public static void main(String[] args) {

        int[][] arrs = {{3,1,3,1},{1,2,3,4},{1,1,1,3,3,4,3,2,4,2}};
        for (int[] arr : arrs){
            if(findsDuplicate2(arr)){
                System.out.println(Arrays.toString(arr) +" is invalid.");
            }else{
                System.out.println(Arrays.toString(arr) +" is valid.");
            }
        }

    }
    public static boolean findsDuplicate(int[] arr){
        // create hashset
        // if curr_num not found in hashset, add curr_num to hashset
        // if done looping and curr_num not found, return false = duplicate not found
        // if curr_nom is  found in hashset, return true = duplicate found
        HashSet<Integer> hash = new HashSet<>();
        for (int num : arr) {
            if (hash.contains(num)){return true;}
            hash.add(num);
        }
        return false;
    }
    public static boolean findsDuplicate2(int[] arr){
        // sort the array
        // see if next_num == curr_num --> duplicate found
        // if not, keep searching next_num
        // if done looping, still found no equals --> duplicate not found
        Arrays.sort(arr); // original array has been changed
        for (int i=0; i<arr.length-1; i++){
            if (arr[i]==arr[i+1]) return true;
        }
        return false;
    }
}
