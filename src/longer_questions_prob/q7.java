package longer_questions_prob;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class q7 {


    static class Matrix {
        private int M;
        private int N;
        private String[][] mat;
        private final String bomb = "*";

        Matrix(int M, int N) {
            this.M = M;
            this.N = N;
            // initialize matrix
            mat = new String[M][N];
            for (char i = 0; i < M; i++) {
                for (char j = 0; j < N; j++) {
                    mat[i][j] = ".";
                }
            }
        }
        String get(int x, int y){
            return mat[x][y];
        }
        void mapBomb(int x, int y){
            mat[x][y] = "*";
        }
        void setBombScore(){
            for (int i=0; i<M;i++){
                for (int j=0; j<N; j++){
                    mat[i][j] = findBombs(i,j);
                }
            }
        }
        String findBombs(int i, int j) {
            // calculating score by looking around neighborhood
            // if there is bomb, score += 1
            if (Objects.equals(mat[i][j], bomb)){
                return "*";
            }
            int score = 0;

            if (i <= M-1) {
                if (i != 0) { // NOT first row
                    if (Objects.equals(mat[i - 1][j], bomb)) //up
                        score++;
                }
                if (i != M-1){ // NOT last row
                    if (Objects.equals(mat[i + 1][j], bomb)) //down
                        score++;
                }
            }

            if (j <= N-1) {
                if (j != N-1) { // NOT right corner
                    if (Objects.equals(mat[i][j + 1], bomb)) //right
                        score++;
                }
                if (j != 0) { // NOT left corner
                    if (Objects.equals(mat[i][j - 1], bomb)) //left
                        score++;
                }
            }
            if ( i != 0 && j != N-1) { // NOT first row right corner
                if (Objects.equals(mat[i - 1][j + 1], bomb)) //right up
                    score++;
            }
            if ( i != M-1 && j != N-1) { // NOT last row right corner
                if (Objects.equals(mat[i + 1][j + 1], bomb)) //right down
                    score++;
            }
            if ( i != 0 && j != 0) { // NOT first row left corner
                if (Objects.equals(mat[i - 1][j - 1], bomb)) //left up
                    score++;
            }
            if ( i != M-1 && j != 0){ // NOT last row left corner
                if (Objects.equals(mat[i + 1][j - 1], bomb)) //left down
                    score++;
            }
            return String.valueOf(score);
        }
        @Override
        public String toString(){
            setBombScore();
            StringBuilder str = new StringBuilder();
            for (int i=0; i<M; i++){
                for (int j=0; j<N; j++){
                    str.append(mat[i][j]);
                }
                str.append("\n");
            }
            return str.toString();
        }


    }

    static void run() {
        Scanner scn = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Input: M, N, Z altogether (separate them with commas)");
        String[] infos = scn.next().replaceAll("\s+", "").split(",");
        // M: row, N: column, Z: mine quantity
        int M = Integer.parseInt(infos[0]);
        int N = Integer.parseInt(infos[1]);
        int Z = Integer.parseInt(infos[2]);
        System.out.printf("M, N, Z: %d, %d, %d \n", M, N, Z);

        Matrix mat = new Matrix(M,N);

        for (int i = 0; i < Z; i++) {
            System.out.println("Enter the mines location: x y (1 space in between)");
            scn = new Scanner(System.in).useDelimiter("\n");
            String[] x_y = scn.next().strip().split(" ");
            int x = Integer.parseInt(x_y[0]);
            int y = Integer.parseInt(x_y[1]);
            System.out.printf("x, y: %d, %d \n", x, y);
            mat.mapBomb(x,y);
        }
        System.out.println(mat);
    }
    static void start_debug(){
        Scanner scn = new Scanner(System.in).useDelimiter("`");
        String[] splits = scn.next().strip().split("\n");
        String[] MNZ = splits[0].strip().split(" ");
        String[][] bombs_xy = new String[splits.length-1][2];
        // M: row, N: column, Z: mine quantity
        int M = Integer.parseInt(MNZ[0]);
        int N = Integer.parseInt(MNZ[1]);
        int Z = Integer.parseInt(MNZ[2]);
        Matrix mat = new Matrix(M,N);

        for (int i=0; i<Z; i++){
            bombs_xy[i] = splits[i+1].strip().split(" ");
            int x = Integer.parseInt(bombs_xy[i][0]);
            int y = Integer.parseInt(bombs_xy[i][1]);
            System.out.println(Arrays.toString(bombs_xy[i]));
            System.out.printf("x, y: %d, %d \n", x, y);
            mat.mapBomb(x,y);
        }

        System.out.println(mat);

    }

    public static void main(String[] args) {
        run(); // enter test case data by hand in terminal

    }
}
