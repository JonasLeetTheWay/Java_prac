package university;

import Prog1Tools.Screen;
import Prog1Tools.TextScreen;

public class JumpingBall_OOP {

    static final String BALL = "O";
    static final int PAUSE_TIME = 100;
    static final int MAX_ROW = 24;
    static final int MAX_COLUMN = 79;

    public static int numberOfStepsToTake = 170;
    public static int step = 0;

    private int row = 0;
    private int column = 0;
    public boolean rowDescending = true;     // boolean TRUE = going down / right
    public boolean columnDescending = true;  // boolean FALSE = going up / left

    public JumpingBall_OOP(int row, int column) {
        setRow(row);
        setColumn(column);
    }
    public JumpingBall_OOP(){
    }

    public static void main(String[] args) {
        TextScreen screen = TextScreen.getInstance();

        // num of balls
        int n = 4;
        JumpingBall_OOP[] balls = new JumpingBall_OOP[n];      // inside is all null actually
        // give each ball coordinates
        int[][] posArr = {
                            {0,0},
                            {0, MAX_COLUMN},
                            {MAX_ROW, 0},
                            {MAX_ROW, MAX_COLUMN}
        };
        // create balls
        for (int i=0; i<balls.length; i++){
            balls[i] = new JumpingBall_OOP();
            balls[i].setRow(posArr[i][0]);
            balls[i].setColumn(posArr[i][1]);
        }

        for (step = 1; step <= numberOfStepsToTake; step++) {
            //track timesteps into floating window title
            screen.setTitle("JumpingBalls on step "+step);
            //simulating game physics of each ball
            for(JumpingBall_OOP b : balls){
                //printing the current position
                b.calculateNewPosition();
                screen.write(b.getRow(),b.getColumn(),BALL);
            }
            //pausing the screen for humans
            Screen.pause(PAUSE_TIME);
        }
        screen.write(MAX_ROW/2, MAX_COLUMN/2, "GAME ENDED");
    }

    public void calculateNewPosition() {
        //Checking for direction change
        rowDescending = directionRow(getRow(), rowDescending);
        columnDescending = directionColumn(getColumn(), columnDescending);
        //updating row and column
        setRow( rowChange(getRow(), rowDescending) );
        setColumn( columnChange(getColumn(), columnDescending) );
    }

    public void setRow(int y) {
        row = y;
    }

    public void setColumn(int x) {
        column = x;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * method that will return a new vertical (row) direction
     * if the maximum or minimum row (0 - 24) has been reached
     * will return the original value if no extrem has been reeched
     *
     * @param row
     * @param currentDirection
     * @return
     */
    public boolean directionRow(int row, boolean currentDirection) {
        if (row == 0) {
            return true;
        } else if (row == MAX_ROW) {
            return false;
        }
        return currentDirection;
    }

    /**
     * method that will return a new vertical (row) direction
     * if the maximum or minimum column (0 - 79) has been reached
     * will return the original value if no extrem has been reeched
     *
     * @param column
     * @param currentDirection
     * @return
     */
    public boolean directionColumn(int column, boolean currentDirection) {
        if (column == 0) {
            return true;
        } else if (column == MAX_COLUMN) {
            return false;
        }
        return currentDirection;
    }

    /**
     * method that will calculate and return the new row
     * depending on the current direction of a ball
     *
     * @param row
     * @param descending
     * @return
     */
    public int rowChange(int row, boolean descending) {
        if (descending) {
            row++;
        } else {
            row--;
        }
        return row;
    }

    /**
     * method that will calculate and return the new column
     * depending on the current direction of a ball
     *
     * @param column
     * @param descending
     * @return
     */
    public int columnChange(int column, boolean descending) {
        if (descending) {
            column++;
        } else {
            column--;
        }
        return column;
    }
}
