package question62;

import java.util.ArrayList;
import java.util.List;

/**
 * 51.n皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
class Solution {

    private List<List<String>> result = new ArrayList<>();
    private List<StringBuilder> table = new ArrayList<>(); // 模拟棋盘

    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        for(int i = 0; i < n; i++){
            table.add(new StringBuilder());
            for(int j = 0; j < n; j++){
                table.get(i).append(".");
            }
        }

        backTrack(n,0);
        return result;
    }

    private void backTrack(int n, int cur){
        // 表示要返回最终结果了
        if(cur == n){
            List<String> temp = new ArrayList<>();
            for(StringBuilder s : table){
                temp.add(s.toString());
            }
            result.add(temp);
            return ;
        }

        // 对该行逐列回溯
        for(int j = 0; j < n; j++){
            if(isValid(n,cur,j)){
                // 注意StringBuilder的替换函数
                table.get(cur).setCharAt(j,'Q');
                backTrack(n,cur+1);
                table.get(cur).setCharAt(j,'.');
            }
        }
    }

    private boolean isValid(int n, int row, int col){
        // 判断同一列上有无棋子
        for(int i = 0; i < row; i++){
            if(table.get(i).charAt(col) == 'Q') return false;
        }
        // 判断左上对角线有无棋子
        for(int i = row - 1,j = col -1; i >= 0 && j >= 0; i--,j--){
            if(table.get(i).charAt(j) == 'Q') return false;
        }
        // 判断右上对角线有无棋子
        for(int i = row - 1,j = col + 1; i >= 0 && j < n; i--,j++){
            if(table.get(i).charAt(j) == 'Q') return false;
        }
        return true;
    }


    // 测试方法
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        List<List<String>> result = solution.solveNQueens(n);

        System.out.println("N = " + n + " 的所有解:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("解 " + (i + 1) + ":");
            List<String> solutionBoard = result.get(i);
            for (String row : solutionBoard) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}