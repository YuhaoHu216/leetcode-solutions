package D1017;

import java.util.*;

/*
    小明在网格中布置了若干灯塔，每个字符1都是一座在时刻0同时启动的灯塔，#是不可通过岩石，0是空地
    没经过一分钟，灯塔可以向上，下，左，右四个方向的相邻格子扩散一次，某块空地的距离，是从所有灯塔分别出发到达它所需扩散次数的最小值。
    请输出所有可达空地中距离的最大值，以及到达该最大距离的空地数量，如果没有可达空地，输入0 0
    （1<=n,m<=200）分别表示网格行数和列数，后面n行，第i行输入一个长为m的字符串，只包含#,0和1。保证网格中至少有一个1
*/
public class Question3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int m = in.nextInt();


        in.nextLine();
        String[] input = new String[n];

        for(int i = 0; i < n; i++){
            input[i] = in.nextLine();
        }

        // bfs 广度优先 腐烂的桔子（多源 BFS）

        char[][] grid = new char[n][m];
        int[][] dist = new int[n][m]; // 到最近灯塔的扩散次数，-1 表示不可达
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], -1);
        }

        LinkedList<int[]> queue = new LinkedList<>(); // 将灯塔坐标放入队列
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grid[i][j] = input[i].charAt(j);
                if(grid[i][j] == '1'){
                    dist[i][j] = 0;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int[] x = {1,-1,0,0};
        int[] y = {0,0,1,-1};

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            // 将该坐标的四个方位全都变为灯塔
            for(int j = 0; j < 4; j++){
                int nx = x[j] + poll[0];
                int ny = y[j] + poll[1];
                if(nx >= 0 && ny >= 0 && nx < n && ny < m
                        && grid[nx][ny] == '0' && dist[nx][ny] == -1){
                    dist[nx][ny] = dist[poll[0]][poll[1]] + 1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        // 统计所有可达空地中距离的最大值及对应空地数量；无可达空地时输出 0 0
        int maxDist = 0;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '0' && dist[i][j] > 0){
                    if(dist[i][j] > maxDist){
                        maxDist = dist[i][j];
                        count = 1;
                    }else if(dist[i][j] == maxDist){
                        count++;
                    }
                }
            }
        }

        System.out.println(maxDist + " " + count);

    }


}