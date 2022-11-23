package general_prob;

public class SelectionSort {
    /*
     two sections = unsorted & sorted sections
     intially, sorted sec = {}, unsorted sec = original list
     finds smallest element in unsorted sec
     puts it at the rightmost of sorted sections
     */
    // generate & return array of length int x, filled with random numbers ranging from 0~1000


    public static void main(String[] args) {
        int[] arr = generateRandomArr(3);
        int[] sorted = selectionSort(arr);
        printArr(arr);
        printArr(sorted);
    }

    public static int[] generateRandomArr(int len){
        int[] arr = new int[len];
        for (int i=0; i<arr.length; i++){
            arr[i] = (int) (1 + 1000*Math.random());
        }
        return arr;
    }

    public static int[] selectionSort(int[] arrInput){
        // array initialization
        int len = arrInput.length;
        int[] arr = arrInput.clone();

        // select smallest element in unsorted sec, puts it to rightmost of sorted sec
        for (int i = 0; i < len; i++) {
            int min_startIdx = i; // after finishing sorting the first smallest element, reset
            for (int j = 0; j < len; j++) {
                if (arr[j] > arr[min_startIdx]) {
                    // if it is larger, meaning current min_startIdx has the smallest index, can go to next index
                    min_startIdx = j;
                    // swap the found minimum element with first element
                    int temp = arr[min_startIdx];
                    arr[min_startIdx] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    public static void printArr(int[] arr){
        for (int i : arr){
            System.out.print(i+", ");
        }
        System.out.println();
    }
}
