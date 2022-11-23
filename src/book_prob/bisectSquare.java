package book_prob;

import java.util.Arrays;

public class bisectSquare {

    protected static int size = 20;
    protected static char[][] mat = new char[size][size];

    /*
        Given two squares on a two-dimensional plane, find a line that would cut these two
        squares in half. Assume that the top and the bottom sides of the square run parallel to the x-axis.
     */
    public static void start() {
    }

    static class Square extends bisectSquare {

        public static void draw() {

            int w = randIdx(5);
            int h = randLen(5);

            int m = randIdx(size);
            int n = randIdx(size);

            for (char[] r : mat) {
                Arrays.fill(r, '.');
            }
            int[] leftTop = new int[]{m-w/2, n-h/2};
            int[] rightTop = new int[]{m+w/2, n-h/2};
            int[] leftBtm = new int[]{m-w/2, n+h/2};
            int[] rightBtm = new int[]{m+w/2, n+h/2};
        }

        public static void drawLine(int[] str, int[] end){
            int x1 = str[0];
            int y1 = str[1];
            int x2 = end[0];
            int y2 = end[1];
            int mGap = x2-x1;
            int nGap = y2-y1;
            int i=0;
            int j=0;
            int iStep;
            int jStep;
            if (mGap>0) iStep = 1;
            else if (mGap == 0) iStep = 0;
            else iStep = -1;
            if (nGap>0) jStep = 1;
            else if (nGap == 0) jStep = 0;
            else jStep = -1;

            while (i != mGap && j != nGap){

                System.out.printf("x1+i: %d, y1+j: %d, i: %d, j: %d \n", x1+i, y1+j, i,j);

                mat[x1+i][y1+j] = '@';

                if ( i + iStep != mGap) i += iStep;
                if ( j + jStep != nGap) j += jStep;
            }
        }

        public static int randLen(int maxRange){ return (int) (maxRange * Math.random() ) + 1; }
        public static int randIdx(int maxRange){ return (int) (maxRange * Math.random() ); }

        public static void findSquareCenter() {
        }

        public static void print(){
            for (char[] r : mat){
                for (char c : r){
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int[] a = {0,3};
        int[] b = {1,5};
        Square.drawLine(a,b);
        Square.print();
    }
}
