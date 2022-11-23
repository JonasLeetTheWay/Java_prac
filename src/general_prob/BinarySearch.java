package general_prob;

public class BinarySearch {

    static int[] nums = {-1, 0, 3, 5, 9, 12};
//    static int[] nums = {-1};
    static int target = 12;

    public static void main(String[] args) {
        int indexNum = binarySearch(nums, target);
        if (indexNum != -1){
            System.out.println(target +" exists in nums and its index is "+ indexNum);
        }else {
            System.out.println(target + " does not exist in nums so returns " + indexNum);
        }
    }

    public static int binarySearch(int[] nums, int target) {
        if (nums.length == 0) return -1;

        int mid = (nums.length-1) / 2;
        int left = 0;
        int right = (nums.length-1);

        while (left <= right) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;// update left-pointer
                mid = (right + left) / 2;
            } else if (nums[mid] > target) {
                right = mid - 1; // update right-pointer
                mid = (right + left) / 2;
            }
        }
        return -1;
    }
}
