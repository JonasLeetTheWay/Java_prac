package leetcode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class lc118_PascalTriangle {
    /*
                [1],
               [1,1],
              [1,2,1],
             [1,3,3,1],
            [1,4,6,4,1]
     */

    // print List<List<Integer>> into console

    // create empty triangle - resizable list -> ArrayList<>
    // ArrayList<> is datatype List
    // at declaration, dimension of ArrayList<> should be given
    // for example. List<List<Integer>>

    // create first row -> resizable List again
    // assign number 1

    // create algorithm to push next row, based on previous row
    // first and last element of next row = 1
    // other elements in between them = increment of two neighbour elements from last row

    public static void main(String[] args) {
        List<List<Integer>> arrList = generate(5);
        printArrayList(arrList);
    }

    public static List<List<Integer>> generate(int numRows){

        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows == 0 ) return triangle;

        List<Integer> first_row = new ArrayList<>();
        first_row.add(1);
        triangle.add(first_row); // triangle has the first row, UPDATED with Changes

        for (int i=1; i<numRows; i++){
            List<Integer> prev_row = triangle.get(i-1);
            List<Integer> curr_row = new ArrayList<>();

            curr_row.add(1); // 1st element of current row = 1
            for (int intList : prev_row) {
                System.out.println("prev_row elements:  "+intList);
            }
            System.out.println("before: ");
            printArrayList(triangle);

            for (int j=1; j<i; j++){
                System.out.println("inside: "+i);
                curr_row.add( prev_row.get(j-1) + prev_row.get(j) );
            }

            curr_row.add(1);  // last element of current row = 1
            triangle.add(curr_row);
            System.out.println("after: ");
            printArrayList(triangle);
            System.out.println("-------");
        }
        return triangle;
    }

    public static void printArrayList(List<List<Integer>> arrList){
        for (List<Integer> intList : arrList) {
            System.out.println(intList);
        }
    }
}
