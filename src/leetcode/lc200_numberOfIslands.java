package leetcode;

public class lc200_numberOfIslands {
    /*
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is
    surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
    assume all four edges of the grid are all surrounded by water.
     */

    /*
    knowledge tested: determine connectivity, 2d array
     */

    // 2d grids
    static char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'},
    };
    // output = 1
    static char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'},
    };
    //output = 3

    public static void main(String[] args) {
        char[][][] grids = {grid1,grid2};

        for (char[][] grid : grids){
            int answer = numIslands(grid);
            System.out.println(answer);
        }
    }

    public static int numIslands(char[][] grid){
        int count = 0;
        // i = row index
        // j = col index
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == '1'){
                    count += 1;
                    callBFS(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void callBFS(char[][] grid, int i, int j){
        if (i<0 || i>= grid.length || j<0 || j>=grid[i].length || grid[i][j] == '0') {return;}

        grid[i][j] = '0';
        callBFS(grid, i-1, j); // up
        callBFS(grid, i+1, j); // down
        callBFS(grid, i, j-1); // left
        callBFS(grid, i, j+1); // right
    }
}
