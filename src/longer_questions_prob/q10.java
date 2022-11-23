package longer_questions_prob;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

// Question PDF:
// https://drive.google.com/file/d/1ovbCiweseP_DNFm-fKUWVFrqdxNkfVGe/view?usp=sharing
public class q10 {

    static void run() {

        // test cases
        //double[] arr = {80,90.50,88.80,105,105,95,111.10,120};
        //double[] arr = {10, 78, 50, 20, 40, 5, 67, 35};
        //double[] arr = {1, 2, 50, 4, 7, 30, 4, 1};
        //double[] arr = {10, 78, 50, 20, 40, 5, 67, 35};
        //double[] arr = {7, 50, 30, 20, 10, 100, 60, 15};
        double[] arr = {45.50, 34.80, 23.90, 30.50, 42.50, 25.90, 14.80, 20.50};
//        double[] arr = {10, 10, 30, 5, 10, 20, 10, 10};
        //double[] arr = {10, 45, 20, 10, 10, 15, 27, 15};

        // sort the array
        double[] arr_s = new double[arr.length];
        System.arraycopy(arr, 0, arr_s, 0, arr.length);
        Arrays.sort(arr_s);

        // put all combinations of 3 nums that is closest to 80
        // NOTE: their sum - 80 >= 0
        LinkedList<Double> numsConsidered = new LinkedList<>();
        // else, put here
        LinkedList<Double> numsConsideredButNotCloseTo80 = new LinkedList<>();

        // find 1ST combinations of 3 nums that is closest to 80
        Pair pairFound = findThreeSumPairs(arr_s, 80, new HashMap<>());
        // remember not to revisit those 3 nums
        HashMap<Integer, Double> numsNotToRevisit1 = findHashMap_NumsNotToRevisit(pairFound, numsConsidered, numsConsideredButNotCloseTo80);

        System.out.println("------------------------------");
        // track all nums that are considered, regardless of their sum >=80 or not
        LinkedList<Double> numsConsideredAll = new LinkedList<>();
        numsConsideredAll.addAll(numsConsidered);
        numsConsideredAll.addAll(numsConsideredButNotCloseTo80);

        // find 2ND combinations of 3 nums that is closest to 80
        Pair pairFound2 = findThreeSumPairs(arr_s, 80, numsNotToRevisit1);
        // remember not to revisit those 3 nums
        HashMap<Integer, Double> numsNotToRevisit2 = findHashMap_NumsNotToRevisit(pairFound2, numsConsidered, numsConsideredButNotCloseTo80);

        System.out.println("------------------------------");
        // remember not to revisit those 6 nums (if combination 1 & 2 exist)
        HashMap<Integer, Double> numsNotToRevisit3 = new HashMap<>();
        numsNotToRevisit3.putAll(numsNotToRevisit1);
        numsNotToRevisit3.putAll(numsNotToRevisit2);
        numsConsideredAll = new LinkedList<>();
        numsConsideredAll.addAll(numsConsidered);
        numsConsideredAll.addAll(numsConsideredButNotCloseTo80);
        System.out.println(numsNotToRevisit3);

        // find out the rest of nums that are not of combinations (3 nums' sum >= 80)
        for (int i = 0; i < arr_s.length; i++) {
            double curr = arr_s[i];
            if (!numsNotToRevisit3.containsKey(i)) {
                numsConsideredButNotCloseTo80.add(curr);
            }
        }

        // final result:
        System.out.println(numsConsidered); // combination of 3 nums that are >= 80
        System.out.println(numsConsideredButNotCloseTo80); // the rest

        double sum = 0;
        double largest = -1;
        double largest_2nd = -1;
        switch (numsConsidered.size()) {

            // if no combination of 3 nums >= 80,
            // sum = all elements together
            case (0):
                for (double d : numsConsideredButNotCloseTo80) {
                    sum += d;
                }
                break;
            // if 1 combination of 3 nums >= 80,
            /*
            sum = all elements of combination
             + the rest except the largest
             + largest/2
             */
            case (3):
                largest = findLargestAndRemove(numsConsideredButNotCloseTo80);
                for (double d : numsConsidered) {
                    sum += d;
                }
                for (double d : numsConsideredButNotCloseTo80) {
                    sum += d;
                }
                sum += largest / 2;
                break;
            // if 2 combination of 3 nums >= 80,
            /*
            sum = all elements of combination
             + the rest except the largest & the 2nd largest
             + largest/2
             + largest_2nd/2
             */
            case (6):
                largest = findLargestAndRemove(numsConsideredButNotCloseTo80);
                largest_2nd = findLargestAndRemove(numsConsideredButNotCloseTo80);

                for (double d : numsConsidered) {
                    sum += d;
                }
                sum += largest / 2;
                sum += largest_2nd / 2;
                break;
        }

        System.out.printf("Final sum: %.2f \n",sum);

    }

    static double findLargestAndRemove(LinkedList<Double> numsConsidered) {
        double largest = numsConsidered.peekFirst();
        int i = 0;
        int i_largest = 0;
        while (i < numsConsidered.size()) {
            double curr = numsConsidered.get(i);
            if (curr > largest) {
                largest = curr;
                i_largest = i;
            }
            i++;
        }
        i = 0;

        numsConsidered.remove((Object) largest);

        return largest;
    }

    /*
    based on Pair that has 3 nums' sum >= 80, separate them to numsConsiderd & numsConsideredButNotCloseTo80
     if >=80, put them to numsConsiderd,
     else, put them to numsConsideredButNotCloseTo80
     */
    static HashMap<Integer, Double> findHashMap_NumsNotToRevisit(Pair pairFound, LinkedList<Double> numsConsidered, LinkedList<Double> numsConsideredButNotCloseTo80) {
        System.out.println(pairFound + ", " + pairFound.closenessTo80);

        HashMap<Integer, Double> numsNotToRevisit = new HashMap<>(pairFound.hashmap_data);
        if (pairFound.closenessTo80 >= 0) {
            for (Map.Entry<Integer, Double> m : numsNotToRevisit.entrySet()) {
                numsConsidered.add(m.getValue());
            }
        } else {
            for (Map.Entry<Integer, Double> m : numsNotToRevisit.entrySet()) {
                numsConsideredButNotCloseTo80.add(m.getValue());
            }
        }
        return numsNotToRevisit;
    }

    /*
    return a Pair that has sum - 80 >= 0, and its closeness to 80
     if closeness < 0, meaning their sum is < 80
     if closeness >=0, meaning their sum is >= 80
     the closeness will then be used to determine the pair will be thrown to numsConsidered or numsConsideredButNotCloseTo80
     */
    static Pair findThreeSumPairs(double[] arr, double target, HashMap<Integer, Double> numsNotToRevisit) {

        System.out.println(Arrays.toString(arr));
        for (Map.Entry<Integer, Double> m : numsNotToRevisit.entrySet()) {
            System.out.printf("NOT TO REVISIT - key, val: %d, %.2f \n", m.getKey(), m.getValue());
            //System.out.println("idx not to revisit: "+m.getValue());
        }
        // initialization
        Pair smallestPair = new Pair(arr[0], arr[1], arr[2]);
        double complement = new Pair(arr[0], arr[1], arr[2]).sum - target;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length && j != i; j++) {
                for (int k = 0; k < arr.length && k != j && k != i; k++) {
                    //System.out.printf("idx i,j,k: %d, %d, %d \n", i,j,k);
                    if (numsNotToRevisit.containsKey(i) || numsNotToRevisit.containsKey(j) || numsNotToRevisit.containsKey(k)) {
                        continue;
                    }
                    //System.out.printf("i, j, k, complement: %.2f, %.2f, %.2f, %.2f \n", arr[i], arr[j], arr[k], complement);
                    Pair curr_pair = new Pair(arr[i], arr[j], arr[k]);
                    if (complement < 0) {
                        if (curr_pair.sum - target > smallestPair.sum - target) {
                            smallestPair = curr_pair;
                            complement = curr_pair.sum - target;

                            smallestPair.saveIdx(i, j, k);
                            smallestPair.saveClosenessTo80(complement);
                        }
                        //System.out.printf("complement<0, smallest pair: %s \n", smallestPair);
                    } else if (complement > 0) {
                        if (curr_pair.sum - target < smallestPair.sum - target) {
                            smallestPair = curr_pair;
                            complement = curr_pair.sum - target;

                            smallestPair.saveIdx(i, j, k);
                            smallestPair.saveClosenessTo80(complement);
                        }
                        //System.out.printf("complement>0, smallest pair: %s \n", smallestPair);
                    } else {
                        if (curr_pair.sum - target < smallestPair.sum - target) {
                            smallestPair = curr_pair;
                            complement = curr_pair.sum - target;

                            smallestPair.saveIdx(i, j, k);
                            smallestPair.saveClosenessTo80(complement);
                            System.out.printf("complement==0, smallest pair: %s \n", smallestPair);
                            //System.out.printf("complement: %.2f \n", complement);
                            //System.out.println("break");
                        }

                    }
                    if (complement == 0) {
                        break;
                    }
                }
                if (complement == 0) {
                    break;
                }
            }
            if (complement == 0) {
                break;
            }
        }
        return smallestPair;
    }

    static class Pair {
        double x, y, z;
        double sum;
        int idx_x, idx_y, idx_z;
        double closenessTo80; // the closer the val to 0 is, the better
        HashMap<Integer, Double> hashmap_data = new HashMap<>() {{
            put(-1, x);
            put(-2, y);
            put(-3, z);
        }};
        public Pair(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.sum = x + y + z;
        }
        void saveIdx(int idx_x, int idx_y, int idx_z) {
            this.idx_x = idx_x;
            this.idx_y = idx_y;
            this.idx_z = idx_z;
            hashmap_data = new HashMap<>() {{
                put(idx_x, x);
                put(idx_y, y);
                put(idx_z, z);
            }};
        }

        void saveClosenessTo80(double complement) {this.closenessTo80 = complement;}

        @Override
        public String toString() {return hashmap_data.toString();}
    }
    /*
    static double[] trimArr(double[] arr, HashMap<Integer, Double> numsNotToRevisit, LinkedList<Double> numsConsidered) {
        int[] idxNotToRevisit = new int[3];
        int ptr = 0;
        for (Map.Entry<Integer, Double> m : numsNotToRevisit.entrySet()) {
            idxNotToRevisit[ptr] = m.getKey();
            ptr++;
            //System.out.println("idx not to revisit: "+m.getValue());
        }
        double[] arr_temp = new double[arr.length - idxNotToRevisit.length];
        int arr_temp_ptr = 0;
        for (int i = 0; i < arr.length; i++) {
            //System.out.println("idxNotToRevisit[ptr]: "+idxNotToRevisit[ptr]);
            if (i != idxNotToRevisit[0] && i != idxNotToRevisit[1] && i != idxNotToRevisit[2]) {
                //System.out.println("idx to revisit: "+i+", arr[i]: "+arr[i]);
                arr_temp[arr_temp_ptr] = arr[i];
                arr_temp_ptr++;
            }
        }
        Arrays.sort(arr_temp);
        return arr_temp;
    }
 */

    public static void main(String[] args) {
        run();
    }
}
