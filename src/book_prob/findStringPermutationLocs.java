package book_prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class findStringPermutationLocs {

    static ArrayList<int[]> findAllPermutationLocs(){
        String small = "abbc";
        String big = "cbabadcbbabbcbabaabccbabc";

        // find all characters & their quantity in small string
        HashMap<Character, Integer> match_count = new HashMap<>();
        for (char c : small.toCharArray()){
            if (!match_count.containsKey(c)) match_count.put(c, 1);
            else match_count.put(c, match_count.get(c) + 1);
        }
        System.out.println(match_count);

        int match_length = 0;
        int idx_str = -1;
        int idx_end = -1;
        int p = 0;
        ArrayList<int[]> idx_str_end = new ArrayList<>();
        HashMap<Character, Integer> temp = new HashMap<>(match_count);

        // loop through array only once,
        // to find permutations of SMALL string in BIG string
        while (p < big.length()){

            char curr = big.charAt(p);
            // while first key exists in hashmap, check if next character exists in hashmap
            while (match_count.containsKey( curr ) && p < big.length()) {

                System.out.println("inside while loop");
                if (match_length == 0) // first key found
                    idx_str = p;

                curr = big.charAt(p);
                temp.put( curr, temp.get(curr) -1 );
                System.out.println(temp);

                match_length++; // increment until SMALL string length
                p++;

                if (match_length == small.length()) { // last key found

                    idx_end = p-1;

                    boolean found = true;
                    // check if all characters and their quantity ARE MATCHED
                    for (Map.Entry<Character,Integer> m : temp.entrySet()){
                        // as long as value != 0, meaning the matching character exceeds or not sufficient
                        if (m.getValue() != 0){
                            found = false; // unvalid
                            break;
                        }
                    }
                    // add to indices arr, only if it's valid
                    if (found)
                        idx_str_end.add(new int[]{idx_str, idx_end});

                    // reset
                    temp = new HashMap<>(match_count);
                    match_length = 0;

                    break;
                }
            }
            p++;
        }

        for (int[] idx_arr : idx_str_end)
            System.out.println(Arrays.toString(idx_arr));

        return idx_str_end;
    }

    public static void main(String[] args) {

        ArrayList<int[]> ans = findAllPermutationLocs();
        String big = "cbabadcbbabbcbabaabccbabc";

        for (int[] indices: ans) {
            int str = indices[0];
            int end = indices[1];
            for (int i = str; i <= end; i++)
                System.out.print(big.charAt(i)+" ");
            System.out.println("--------------------");
        }

    }
}
