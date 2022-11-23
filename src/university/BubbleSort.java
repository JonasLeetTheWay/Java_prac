package university;

public class BubbleSort {

    // not static = instance of class
    // static = method within class
    public static int[] randomSequence;

    public static void main(String[] args) {
        int x = 5;
        randomSequence = generateNewRandomArray(x);
        printArrayToConsole(randomSequence);
        sortArray(randomSequence);
        // printArrayToConsole(randomSequence); // showing side-effect: original array has been CHANGED
    }

    // returns a new int-array of length x, filled with random INTEGER NUMBERS in range between 1 and 1000
    public static int[] generateNewRandomArray(int x){
        int[] arr = new int[x];
        for(int i=0; i<arr.length; i++){
            arr[i] = (int) (1 + 1000*Math.random());
        }
        return arr;
    }

// Create a method public static printArrayToConsole(int[] x) that can be used to print out any int array to the shell.
    public static void printArrayToConsole(int[] x){
        System.out.println();
        for(int element : x) {
            System.out.print(element+" ");
        }
    }

    // bubble sorting algorithm
    public static void sortArray(int[] a){
        boolean flagChg = true;
        int temp = 0;
        for(int n = a.length-1; n>=1 && flagChg; n--){
            for(int i=0; i<n; i++){
                if(a[i]>a[i+1]){
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    flagChg = true;
                }
            }
        }
        printArrayToConsole(a);
    }
}

