package university;

import Prog1Tools.Screen;
import Prog1Tools.TextScreen;

public class JumpingBall_Procedural {

    static final String BALL = "O";
    static final int PAUSE_TIME = 100;
    static final int MAX_ROW = 24;
    static final int MAX_COLUMN = 79;

    public static int numberOfStepsToTake = 175;
    public static int step = 0;

    public static int row = 0;
    public static int column = 0;
    public static boolean rowDescending = true;     // boolean TRUE = going down / right
    public static boolean columnDescending = true;  // boolean FALSE = going up / left

    public static void main(String[] args) {
        TextScreen screen = TextScreen.getInstance();

        screen.setTitle("Jumping Balls Procedural");

        for (step = 1; step <= numberOfStepsToTake; step++) {
            //Checking for direction change
            rowDescending = directionRow(row, rowDescending);
            columnDescending = directionColumn(column, columnDescending);
            //updating row and column
            row = rowChange(row, rowDescending);
            column = columnChange(column, columnDescending);

            //printing the current position
            screen.write(row, column, BALL);
            //pausing the screen for humans
            Screen.pause(PAUSE_TIME);
        }
        screen.write(MAX_ROW/2, MAX_COLUMN/2, "GAME ENDED");

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
    public static boolean directionRow(int row, boolean currentDirection) {
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
    public static boolean directionColumn(int column, boolean currentDirection) {
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
    public static int rowChange(int row, boolean descending) {
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
    public static int columnChange(int column, boolean descending) {
        if (descending) {
            column++;
        } else {
            column--;
        }
        return column;
    }
}
