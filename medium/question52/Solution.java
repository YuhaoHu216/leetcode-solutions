package question52;

import java.util.LinkedList;

/**
 * 腐烂的橘子
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
class Solution {
    public int orangesRotting(int[][] grid) {

        // 用于记录每轮感染
        int step = 0;

        // 用于烂橘子感染周边,对应分别为{-1,0},{1,0},{0,-1},{0,1}:上,下,左,右
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        int fresh = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        // 遍历矩阵将所有烂橘子坐标入队,并且统计新鲜橘子数量
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == 2){
                    queue.offer(new int[]{r,c});
                    continue;
                }
                if(grid[r][c] == 1){
                    fresh++;
                }
            }
        }

        // 遍历所有烂橘子,然后感染,最外层为每轮感染
        while(fresh > 0 && !queue.isEmpty()){
            step++;
            int size = queue.size();
            // 每个烂橘子都去感染周边一次
            for(int i = 0; i < size;i++){
                int[] poll = queue.poll();
                // 感染单个烂橘子的周边
                for(int n = 0; n < 4; n++){
                    int x = poll[0] + dx[n];
                    int y = poll[1] + dy[n];
                    // 要判断越界问题
                    if(x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y] == 1){
                        grid[x][y] = 2;
                        queue.offer(new int[]{x,y});
                        fresh--;
                    }
                }
            }
        }

        // 当所有烂橘子将周围的橘子感染完之后判断有无烂橘子无法到达的好橘子
        if(fresh > 0){
            return -1;
        }
        return step;
    }
}