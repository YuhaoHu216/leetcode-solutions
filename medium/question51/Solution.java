package question51;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
class Solution {
    public int numIslands(char[][] grid) {
        // 每列多少个
        // int r = grid.length;
        // 每行多少个
        // int c = grid[0].length;
        // 获取的结果
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
}
