package question200;

import java.util.Scanner;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * ACM 模式：
 * 输入：第一行为两个整数 m n，表示网格的行数和列数；接下来 m 行，每行 n 个字符（'1' 表示陆地，'0' 表示水），例如：
 * 4 5
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出：岛屿数量，例如：1
 */
class Solution {
    public int numIslands(char[][] grid) {
        int result = 0;
        // 遍历整个数组,如果遇到岛屿的一部分就开始深度搜索全岛
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){

                if(grid[r][c] == '1'){
                    dfs(grid,r,c);
                    result++;
                }
            }
        }

        return result;
    }

    void dfs(char[][] grid,int r,int c){
        // 判断终止条件(点在数组内)
        if(!(r>=0 && c>=0 && r < grid.length && c < grid[0].length)) return;

        // 判断点是否是岛屿的一部分
        if(grid[r][c] != '1') return;

        // 标记遍历过的岛屿的部分
        grid[r][c] = '2';

        // 向岛屿周围递归
        dfs(grid,r-1,c);
        dfs(grid,r+1,c);
        dfs(grid,r,c-1);
        dfs(grid,r,c+1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：m n，网格的行数和列数
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        // 接下来 m 行，每行 n 个字符，'1' 表示陆地，'0' 表示水
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = scanner.next();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        scanner.close();

        int result = new Solution().numIslands(grid);
        System.out.println(result);
    }
}
