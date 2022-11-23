package leetcode;

import java.util.Map;
import java.util.HashMap;

public class lc_FirstMissingPositive {

    static void func(int... nums){
        // assume no duplicates
        HashMap<Integer, Integer> pos_tracker = new HashMap<>();


        int min = 0;
        int max = 0;
        for (int i = 0; i< nums.length; i++){
            if ( nums[i] >= 0) {
                pos_tracker.put(nums[i], i); // val, idx at which val exists
                if (nums[i] > max)
                    max = nums[i];
                if (nums[i] < min)
                    min = nums[i];
            }
        }

        // find gap
        int gap = -1;
        int val_rightBound = -1;
        int val_leftBound = -1;
        boolean leftBound = false;
        boolean rightBound = false;
        int len_longest = 0;
        int len_longest_left = 0;
        int len_longest_right = 0;

        for (Map.Entry<Integer, Integer> m : pos_tracker.entrySet()){

            int val = m.getKey();
            int len = 0;

            while ( pos_tracker.containsKey(val+len) ){
                len++;
            }
            if (pos_tracker.containsKey(val+len+1)){
                gap = val + len;
                len++;
                while (pos_tracker.containsKey(val+len)){
                    len++;
                }
                len_longest_right = Math.max ( len, len_longest_right);
            } else {
                val_rightBound = val + len - 1;
            }

            len = 0;
            while ( pos_tracker.containsKey(val+len) ){
                len--;
            }
            if (pos_tracker.containsKey(val+len-1)){
                gap = val + len;
                len--;
                while (pos_tracker.containsKey(val+len)){
                    len--;
                }
                len_longest_left = Math.max ( len, len_longest_left);
            } else {
                val_leftBound = val + len + 1;
            }

            if (gap == -1){
                len_longest = len_longest_left + len_longest_right;
            } else {
                len_longest = len_longest_left + len_longest_right + 1;
            }

            System.out.printf("leftbound, rightbound, " +
                    "len_longest_left, len_longest_right, " +
                    "len_longest, gap: %d %d %d %d %d %d\n", val_leftBound, val_rightBound, len_longest_left, len_longest_right, len_longest, gap);


            if (gap == -1 && val_leftBound != 0){
                System.out.println(val_leftBound-1);
            } else if (gap == -1 && val_leftBound == 0 ){
                System.out.println(val_rightBound+1);
            } else {
                System.out.println(gap);
            }


        }
    }

    public static void main(String[] args) {

        func(1,2,0);
        func(7,8,9,11,12);

    }
}
